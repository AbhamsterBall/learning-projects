package com.hyh.controller;

//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyh.model.Emp;
import com.hyh.service.EmpService;
import com.hyh.service.EntityService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/index")
    public String handle() {
        return "index";
    }

//    @RequestMapping("/values/text")
//    @ResponseBody
//    public String test() {
//        String r = "";
//        try {
//            FileInputStream in = new FileInputStream("F:\\JAVA\\20231010\\ssmAjaxUpdate_Delete\\src\\ajax_info.txt");
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] is = new byte[1024];
//            while (in.read(is) != -1)
//                bos.write(is);
//            r = bos.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return r;
//    }

    @RequestMapping(value = "values/table", produces = "application/json")
    public @ResponseBody String handleTable(@RequestParam(value = "page", defaultValue = "1") int page,
                                 HttpServletRequest rq) {

        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.serializeNulls();
        Gson gson = gsonbuilder.create();

        return gson.toJson(empService.cQuery(rq, page));
    }

    @RequestMapping("return/delete/{pk}")
    public @ResponseBody String handleDelete(@PathVariable("pk") int pk) {
        return empService.cDeleteById(pk);
    }
}
