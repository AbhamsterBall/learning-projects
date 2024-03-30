package com.hyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyh.model.Emp;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface EmpService extends EntityService<Emp> {
    String upload(MultipartFile[] files);
    String[] fUploaded();
    String download(String file, HttpServletResponse rs);
}
