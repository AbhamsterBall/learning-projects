package work.hampster.filter;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import work.hampster.util.AjaxResult;
import work.hampster.util.Jwt;
import work.hampster.util.ObjectHandler;
import work.hampster.util.RSA;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String[] EXCLUDE_URL = {
//            "/actuator",
//            "/getUnionPayCodeList",
//            "/spi-api",
//            "/store", // TODO: DELETE AFTER TEST   fubei ER CODE GENERATOR
////            "/dispatchQrCodeQuery", // TODO: DELETE AFTER TEST   fubei ER CODE GENERATOR
////            "/ali", // TODO: DELETE AFTER TEST   ali auth generator
////            "/aliThirdParty", // TODO: DELETE AFTER TEST   ali third party generator
////            "/aliN7H" // TODO: DELETE AFTER TEST   ali n7h
//            "/aliN7H/setDevice", // android api
//            "/aliN7H/getShopList", // android api
//            "/aliN7H/getInternalShops", // android api
//            "/aliN7H/getDeviceList",
//            "/aliN7H/getAliShopList"
    };

    public static final String[] USER_ONLY_URL = {

    };

    private final Jwt jwtUtil;

    @Autowired
    public JwtRequestFilter(Jwt jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // TODO: filter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "12000");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        response.setHeader("Access-Control-Expose-Headers", "*");

//         logger.info("authorization: " + request.getHeader("Authorization"));

//        final String authorizationHeader = request.getHeader("Authorization");
// //
//        String jwt = null;
// //
//        if (ObjectHandler.isStrArrContains(request.getRequestURI(), EXCLUDE_URL)) {
//            chain.doFilter(request, response);
//            return;
//        }


//        if (ObjectUtils.isNotEmpty(authorizationHeader)) {

//            // generate token for /token/get request


//            if (authorizationHeader.startsWith("APIBearer ")) {

                logger.info("request: " + request.getRequestURI());

//                 jwt = authorizationHeader.substring(10);
//                 isTokenApplyValid(jwt, request, response, chain);
//            }

//            logger.info("token received:" + request.getRequestURI());

//            if (authorizationHeader.startsWith("Bearer ")) {
// //                System.out.println("in jwt");
//                jwt = authorizationHeader.substring(7);
//                isTokenValid(jwt, request, response, chain);
//            } else
//                response.sendError(AjaxResult.UNAUTHORIZED, "unauthorized request");

//        } else
//            response.sendError(AjaxResult.UNAUTHORIZED, "unauthorized request");
        chain.doFilter(request, response);
    }

    private void returnNewToken(HttpServletResponse response, String newTokens) throws IOException {
        if (!newTokens.equals("unauthorized request")) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":200,\"message\":\"Success\",\"data\":{\"token\":" + newTokens + "}}");
        } else
            response.sendError(AjaxResult.UNAUTHORIZED, "unauthorized request");
    }

    private void isTokenApplyValid(String jwt, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String decryptURL = null;
        String decrypt = null;
        Long timestamp = null;

        if (ObjectUtils.isNotEmpty(jwt)) {
            String subURL = request.getRequestURI()
            .substring(1);
            decryptURL = RSA.decryptURL(subURL, Jwt.rsa.getPrivateKey());
            logger.info("decrypt URL: " + decryptURL);
            decrypt = RSA.decrypt(jwt, Jwt.rsa.getPrivateKey());
            logger.info("decrypt: " + decrypt);

            String[] decryptInfo = decrypt.split(".");
            if (ObjectUtils.isNotNull(decrypt))
                timestamp = Long.valueOf(decryptInfo[1]);

            if (ObjectUtils.isNotNull(decrypt) && ObjectUtils.isNotNull(decryptURL) &&
                    (decryptURL.equals("continue") ?
                            Objects.equals(decrypt, subURL + "." + timestamp) :
                            Objects.equals(decryptURL, "tokenGet." + timestamp))) {
                returnNewToken(response, jwtUtil.generateToken(jwt, request.getRequestURI(), decryptInfo[2]));
                return;
            }
        }
    }
    
    private void isTokenValid(String jwt, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String res = "";
        boolean validateToken = jwtUtil.validateToken(jwt);
        Claims cls = jwtUtil.extractAllClaims(jwt);
        if (ObjectUtils.isNotEmpty(jwt) && validateToken
            && (cls.get("user_token") != null 
                || !ObjectHandler.isStrArrContains(request.getRequestURI(), USER_ONLY_URL))) {
//            System.out.println("in servlet");
            chain.doFilter(request, response);
            return;
        } else if (!validateToken) {
            res = "invalid token";
        } else if (cls.get("user_token") == null) { 
            res = "no user authentication info";
        }
//        System.out.println("RES " + res);
        response.sendError(AjaxResult.FORBIDDEN,
                !res.isEmpty() ? res : "forbidden request");
    }


}
