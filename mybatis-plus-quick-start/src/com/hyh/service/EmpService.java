package com.hyh.service;

//import com.baomidou.mybatisplus.extension.service.IService;
//import com.hyh.pojo.Emp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyh.pojo.Emp;

import java.util.List;

public interface EmpService extends IService<Emp> {

    //transaction
    void selectColleagueForEmpNo(); //类似于selectCoursesForStudentId，考虑到同事还包括不同部门的上司

    void insertBatchForEmpNormalBatch();
    void insertBatchForEmpBatchService();
    void insertMultipleWithoutBatch();
    void insertWithBaseMapper();

    List<Emp> testIPage(int Page);

    void setRecordsNo(int i);
}
