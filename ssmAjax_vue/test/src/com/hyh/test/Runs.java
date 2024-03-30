package com.hyh.test;

import com.hyh.Boot;
import com.hyh.Config;
import com.hyh.controller.UploadController;
import com.hyh.service.EmpService;
import com.hyh.service.impl.EmpServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:ApplicationContext.xml")
//@RunWith(SpringRunner.class)
@SpringBootTest
public class Runs {

    @Autowired
    private EmpService empService;

    @Test
    public void test() {
        MockHttpServletRequest mock = new MockHttpServletRequest();
        mock.setParameter("ename", "de");

        empService.cQuery(mock, 1);
        mock = new MockHttpServletRequest();
        mock.setParameter("page", "2");
        empService.cQuery(mock, 2);
    }

    @Test
    public void test0() {
        empService.cQueryById(1);

        MockHttpServletRequest mock = new MockHttpServletRequest();
        mock.setParameter("ename", "defffffffffffffff");
        mock.setParameter("job", "Manager");
        mock.setParameter("hiredate", "Jan 1, 1970");

        empService.cUpdateById(mock, 1);
    }

    @Test
    public void test1() {
        empService.cDeleteById(200);
    }

    @Test
    public void test2() {
        MockHttpServletRequest mocked = new MockHttpServletRequest();
        mocked.setParameter("empno", "8003");
        mocked.setParameter("ename", "DDDD");
        mocked.setParameter("job", "");
        mocked.setParameter("hiredate", "Oc");
        mocked.setParameter("sal", "8001");
        mocked.setParameter("comm", "");
        empService.cInsert(mocked);
    }

    @Test
    public void testFile() {
        MockHttpServletRequest mocked = new MockHttpServletRequest();
        mocked.setParameter("empno", "8003");
    }
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext webApplicationContext;

    @Test
    public void testFileUpdate() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) webApplicationContext).build();
        List<MockMultipartFile> files = new ArrayList<MockMultipartFile>() {{
            add(new MockMultipartFile("files", "file1.txt", MediaType.TEXT_PLAIN_VALUE, "File content".getBytes()));
            add(new MockMultipartFile("files", "file2.txt", MediaType.TEXT_PLAIN_VALUE, "Another file content".getBytes()));
            }
        };

//        mockMvc.
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/return/upload")
//                        .file(files.get(0)).file(files.get(1)));

        MultipartFile[] l = new MultipartFile[files.size()];
        for (int i = 0 ; i < files.size() ; i++) {
            l[i] = files.get(i);
        }
//        upController.upload(l);

        empService.upload(l);
    }

    @Test
    public void test05() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        // 设置最大文件大小，单位为字节  所有文件
        commonsMultipartResolver.setMaxUploadSize(209715200000L);
        // 设置单个文件上传最大字节
        commonsMultipartResolver.setMaxUploadSizePerFile(10245760000L);
    }

    @Test
    public void getPath() {
        String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        classpath = classpath.substring(0, classpath.indexOf("/out/"));
        System.out.println(classpath);
    }

    @Test
    public void test7() {
//        System.out.println(empService.fUploaded().toString());
        Arrays.asList(empService.fUploaded()).forEach(System.out::println);
    }

    @Test
    public void getDownLoad() {
        MockHttpServletResponse mocked = new MockHttpServletResponse();
//        try {
//            ServletOutputStream os = mocked.getOutputStream();
//            os.write("hello".getBytes());
//            os.flush();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        empService.download("file1.txt", mocked);
    }
}
