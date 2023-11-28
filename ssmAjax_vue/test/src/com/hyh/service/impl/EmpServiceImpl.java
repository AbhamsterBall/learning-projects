package com.hyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyh.mapper.EmpMapper;
import com.hyh.model.Emp;
import com.hyh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    EntityServiceImpl entityService = new EntityServiceImpl(this, Emp.class);

    @Override
    public Map<String, Object> cQuery(HttpServletRequest rq, int page) {
        return entityService.cQuery(rq, page);
    }

    @Override
    public Map<String, Object> cQueryById(int pk) {
        return entityService.cQueryById(pk);
    }

    @Override
    public String cUpdateById(HttpServletRequest rq, int pk) {
        return entityService.cUpdateById(rq, pk);
    }

    @Override
    public String cDeleteById(int pk) {
        return entityService.cDeleteById(pk);
    }

    @Override
    public String cInsert(HttpServletRequest rq) {
        return entityService.cInsert(rq);
    }

    @Autowired
    private String currentPath;

    private String innerUploadPath = "/web/vue-new/public/uploaded/";

    @Override
    public String upload(MultipartFile[] files) {
        System.out.println(files);

        String s = "上传成功";
        System.out.println("文件 " +  files);
        if (files == null) {
            s =  "请先选择文件";
        } else {
            for (MultipartFile file : files) {

//            String classpath = "F:\\JAVA\\20231010\\ssmAjax_vue\\test";
                File f = new File(currentPath + innerUploadPath, file.getOriginalFilename());
                System.out.println(f.getAbsolutePath());

                try {
                    if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
                    if (!f.exists()) f.createNewFile();

                    file.transferTo(f);
                } catch (IOException e) {
                    s = "上传失败";
                    e.printStackTrace();
                }
            }
        }

        return s;
    }

    @Override
    public String[] fUploaded() {
        File dir = new File(currentPath + innerUploadPath);
        if (!dir.exists()) dir.mkdirs();
        String fileNames[] = null;
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            fileNames = new String[files.length];
            for (int i = 0 ; i < files.length ; i++) {
                fileNames[i] = files[i].getName();
            }
        }
        return fileNames;
    }

    @Override
    public String download(String file, HttpServletResponse rs) {
        String s = "下载成功";
        File f = new File(currentPath + innerUploadPath + file);
        System.out.println(currentPath + innerUploadPath + file);

        try {
            if (f.exists()) {
                System.out.println(rs.getOutputStream());
                rs.setContentType("multipart/form-data");
                rs.setCharacterEncoding("utf-8");
                rs.setHeader("Content-Disposition", "attachment;filename=" + f.getName());

                // 读取文件内容并写入响应输出流
                FileInputStream fileInputStream = new FileInputStream(f);
                ServletOutputStream out = rs.getOutputStream();
                rs.setCharacterEncoding("UTF-8");
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                fileInputStream.close();
                out.flush();
                out.close();

            }
        } catch (Exception e) {
            s = "下载失败：" + e.getCause().getMessage();
            System.out.println("用户选择");
        }

        return s;
    }
}
