package com.hyh.controller;

import com.google.gson.Gson;
import com.hyh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    EmpService empService;

    @PostMapping("/return/upload")
    @CrossOrigin(origins = "http://localhost:8081")
//    @RequestMapping(value = "/return/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(MultipartFile[] files) {
        return empService.upload(files);
    }

    @RequestMapping(value = "/values/uploaded", produces = "application/json; charset=utf-8")
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String handleSelect() {
        return new Gson().toJson(empService.fUploaded());
//        return null;
    }

    @RequestMapping("/download") //告诉浏览器下载文件
    @CrossOrigin(origins = "http://localhost:8081")
    public void downloadFile(@RequestParam("file") String file, HttpServletResponse rs) {
        empService.download(file, rs);
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
