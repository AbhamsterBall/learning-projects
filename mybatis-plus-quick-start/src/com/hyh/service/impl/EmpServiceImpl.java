package com.hyh.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyh.mapper.EmpDynamicSqlSupport;
//import com.hyh.mapper.EmpMapperdeleted;
import com.hyh.mapper.EmpMapper;
import com.hyh.pojo.Emp;
import com.hyh.service.EmpService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Autowired
    private Integer empNo;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SqlSessionFactory sessionFactory;

    private SqlSession session;

    private EmpDynamicSqlSupport.Emp emp = EmpDynamicSqlSupport.emp;

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

    @Override
    @Transactional
    public void insertBatchForEmpNormalBatch() { //MBG生成的动态sql和mybatis xml可能造成冲突，也就说xml的内容最好自己写
        int maxNext = findBiggestId() + 1;

        ArrayList<Emp> records = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++) {
            records.add(new Emp((short)(maxNext + i), "defualt", "new", (short)1, new Date(), 1000, 10, (short)20));
        }

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

        batch.insertStatements().forEach(empMapper::insert);
//        System.out.println(empNo);
    }

    @Override
    public void insertBatchForEmpBatchService() {

    }

    @Override
    public void insertMultipleWithoutBatch() {

    }

}
