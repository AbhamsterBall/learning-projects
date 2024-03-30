package com.hyh.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyh.mapper.EmpBaseMapper;
import com.hyh.mapper.EmpDynamicSqlSupport;
//import com.hyh.mapper.EmpMapperdeleted;
import com.hyh.mapper.EmpMapper;
import com.hyh.pojo.Emp;
import com.hyh.service.EmpService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpBaseMapper, Emp> implements EmpService {

    @Autowired
    private Integer empNo;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpBaseMapper empBaseMapper;

    @Autowired
    private SqlSessionFactory sessionFactory;

    private SqlSession session;

    private EmpDynamicSqlSupport.Emp emp = EmpDynamicSqlSupport.emp;

    private ArrayList<Emp> records = new ArrayList<>();

    public EmpServiceImpl() {

    }

    @Override
    public void selectColleagueForEmpNo() {
        // batch insert
    }

    private void getSession() {
        try {
            this.session = sessionFactory.openSession(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int findBiggestId() { //autowired的数据在第一次类加载的时候拿不到，要等appContext加载完，下一次才可以拿到值
        getSession();
        int id = session.selectOne("selectMaxEmpNo");
        return id;
    }

    public void setRecordsNo(int i) {
        recordsNo = i;
    }

    private int recordsNo = 100;

    private void putDataIntoRecords(int maxNext) {
        records = new ArrayList<>();
        for (int i = 0 ; i < recordsNo ; i++) {
            records.add(new Emp((short)(maxNext + i), "defualt", "new", (short)1, new Date(), 1000, 10, (short)20));
        }
    }

    @Override
    @Transactional
    public void insertBatchForEmpNormalBatch() { //MBG生成的动态sql和mybatis xml可能造成冲突，也就说xml的内容最好自己写
        int maxNext = findBiggestId() + 1;

        putDataIntoRecords(maxNext);

        BatchInsert.Builder<Emp> builder = new BatchInsert.Builder().withInsertStatement(
                SqlBuilder.insert(records).into(emp).
                        map(emp.empEmpno).toProperty("empEmpno").
                        map(emp.empJob).toProperty("empJob").
                        map(emp.empComm).toProperty("empComm").
                        map(emp.empEname).toProperty("empEname").
                        map(emp.empMgr).toProperty("empMgr").
                        map(emp.empDeptno).toProperty("empDeptno").
                        map(emp.empHiredate).toProperty("empHiredate").
                        map(emp.empSal).toProperty("empSal").
                        build().render(RenderingStrategies.MYBATIS3).getInsertStatement());
        builder.withRecords(records);
        BatchInsert<Emp> batch = builder.build();

        batch.insertStatements().forEach(empMapper::insert); //8281
//        System.out.println(empNo);
    }

    @Override
    @Transactional
    public void insertBatchForEmpBatchService() {
        int maxNext = findBiggestId() + 1;
        putDataIntoRecords(maxNext);

        this.saveBatch(records); //可能和自动生成的冲突 FASTEST
    }

    @Transactional
    public void insertMultipleWithoutBatch() {
        int maxNext = findBiggestId() + 1;
        putDataIntoRecords(maxNext);

        empMapper.inMultiple(records); //dynamicSQL EXCEED LIMIT
    }

    @Override
    @Transactional
    public void insertWithBaseMapper() {
        int maxNext = findBiggestId() + 1;
        putDataIntoRecords(maxNext);

        for (Emp i : records) {
            empBaseMapper.insert(i); //9282
        }
    }

    @Override
    public List<Emp> testIPage(int Page) {
//        return this.page(page, query);
        LambdaQueryWrapper<Emp> qw = new LambdaQueryWrapper<>();
        qw.gt(Emp::getEmpDeptno, 10).like(Emp::getEmpEname, "f");

        IPage<Emp> page = new Page<>(Page, 3);
        empBaseMapper.selectPage(page, qw);

        return page.getRecords();
    }

}
