package work.hampster.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.hampster.config.MinioConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class Minio {
    public static MinioClient minioClient = null;

    private static final String[] allowedExtension = { "jpg", "jpeg", "png" };

    @Autowired
    public Minio(MinioClient minio) {
        minioClient = minio;
    }

    public static AjaxResult uploadFileBase64(String prefix, String file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        file = file.split(":")[1];

        assert Arrays.stream(allowedExtension).toList()
                .contains(file.split(",")[0].split(";")[0].split("/")[1]) :
                "extension must be jpg/jpeg/png";

        byte[] imageBytes = Base64.getDecoder().decode(file.split(",")[1]);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

        String nameSuffix = prefix + generateUniqueId(new ByteArrayInputStream(imageBytes)) +
                "." + file.split(",")[0].split(";")[0].split("/")[1];

        PutObjectArgs build = PutObjectArgs.builder().bucket(MinioConfig.bucketName)
                .object(nameSuffix)
                .stream(byteArrayInputStream, imageBytes.length, -1)
                .contentType(file.split(",")[0].split(";")[0]).build();
        minioClient.putObject(build);

        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(MinioConfig.bucketName).expiry(5 * 60)
                        .object(nameSuffix).method(Method.GET)
                        .build());
        url = URLDecoder.decode(url, StandardCharsets.UTF_8);
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", nameSuffix);
        data.put("url", url);

        return AjaxResult.success(data);
    }

    public static String generateUniqueId(ByteArrayInputStream stream) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = new byte[8192];
        int bytesRead;

        try (InputStream fis = stream) {
            while ((bytesRead = fis.read(bytes)) != -1) {
                digest.update(bytes, 0, bytesRead);
            }
        }

        byte[] hash = digest.digest();
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static AjaxResult deleteFile(String name) {
        try {
            RemoveObjectArgs build = RemoveObjectArgs.builder().bucket(MinioConfig.bucketName).object(name).build();
            minioClient.removeObject(build);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(AjaxResult.ERROR, e.getMessage());
        }
    }

    public static String convertToBase64(InputStream inputStream) throws IOException {
        // 将输入流转换为 Base64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        inputStream.close();
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // 打印 Base64 字符串
        return base64Image;
    }

    public static String getFileBase64(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 获取对象（图片）
        try {
            InputStream inputStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(MinioConfig.bucketName)
                            .object(name)
                            .build()
            );

            return convertToBase64(inputStream);
        } catch (MinioException e) {
            // When file not exist, do nothing
            return "";
        }
    }

    public static String getTempUrl(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(MinioConfig.bucketName)
                        .object(name).method(Method.GET)
                        .expiry(5 * 60)
                        .build()
        );
    }

    public static String getDownloadUrl(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String contentDisposition = URLEncoder.encode("attachment; filename=\"%s\"".formatted(name), StandardCharsets.UTF_8);
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .extraQueryParams(Map.of("response-content-disposition", contentDisposition))
                        .bucket(MinioConfig.bucketName)
                        .object(name).method(Method.GET)
                        .build()
        );
    }

    public static ObjectWriteResponse addQRCode(String content, String objectName, int width, int height) throws WriterException, IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        byte[] byteArray = pngOutputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        return minioClient.putObject(PutObjectArgs.builder()
                .bucket(MinioConfig.bucketName)
                .object(objectName)
                .stream(inputStream, byteArray.length, -1)
                .contentType("image/png")
                .build());
    }

}
