package com.hyh.mapper;

import static com.hyh.mapper.EmpDynamicSqlSupport.*;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyh.pojo.Emp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.658+08:00", comments="Source Table: SCOTT.EMP")
    BasicColumn[] selectList = BasicColumn.columnList(empEmpno, empEname, empJob, empMgr, empHiredate, empSal, empComm, empDeptno);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.629+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.632+08:00", comments="Source Table: SCOTT.EMP")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.634+08:00", comments="Source Table: SCOTT.EMP")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Emp> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.635+08:00", comments="Source Table: SCOTT.EMP")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Emp> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.636+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EmpResult")
    Optional<Emp> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.639+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EmpResult", value = {
        @Result(column="EMPNO", property="empEmpno", jdbcType=JdbcType.NUMERIC),
        @Result(column="ENAME", property="empEname", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB", property="empJob", jdbcType=JdbcType.VARCHAR),
        @Result(column="MGR", property="empMgr", jdbcType=JdbcType.NUMERIC),
        @Result(column="HIREDATE", property="empHiredate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SAL", property="empSal", jdbcType=JdbcType.NUMERIC),
        @Result(column="COMM", property="empComm", jdbcType=JdbcType.NUMERIC),
        @Result(column="DEPTNO", property="empDeptno", jdbcType=JdbcType.NUMERIC)
    })
    List<Emp> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.642+08:00", comments="Source Table: SCOTT.EMP")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.643+08:00", comments="Source Table: SCOTT.EMP")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.644+08:00", comments="Source Table: SCOTT.EMP")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.646+08:00", comments="Source Table: SCOTT.EMP")
    default int insert(Emp record) {
        return MyBatis3Utils.insert(this::insert, record, emp, c ->
            c.map(empEmpno).toProperty("empEmpno")
            .map(empEname).toProperty("empEname")
            .map(empJob).toProperty("empJob")
            .map(empMgr).toProperty("empMgr")
            .map(empHiredate).toProperty("empHiredate")
            .map(empSal).toProperty("empSal")
            .map(empComm).toProperty("empComm")
            .map(empDeptno).toProperty("empDeptno")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.65+08:00", comments="Source Table: SCOTT.EMP")
    default int insertMultiple(Collection<Emp> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, emp, c ->
            c.map(empEmpno).toProperty("empEmpno")
            .map(empEname).toProperty("empEname")
            .map(empJob).toProperty("empJob")
            .map(empMgr).toProperty("empMgr")
            .map(empHiredate).toProperty("empHiredate")
            .map(empSal).toProperty("empSal")
            .map(empComm).toProperty("empComm")
            .map(empDeptno).toProperty("empDeptno")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.652+08:00", comments="Source Table: SCOTT.EMP")
    default int insertSelective(Emp record) {
        return MyBatis3Utils.insert(this::insert, record, emp, c ->
            c.map(empEmpno).toPropertyWhenPresent("empEmpno", record::getEmpEmpno)
            .map(empEname).toPropertyWhenPresent("empEname", record::getEmpEname)
            .map(empJob).toPropertyWhenPresent("empJob", record::getEmpJob)
            .map(empMgr).toPropertyWhenPresent("empMgr", record::getEmpMgr)
            .map(empHiredate).toPropertyWhenPresent("empHiredate", record::getEmpHiredate)
            .map(empSal).toPropertyWhenPresent("empSal", record::getEmpSal)
            .map(empComm).toPropertyWhenPresent("empComm", record::getEmpComm)
            .map(empDeptno).toPropertyWhenPresent("empDeptno", record::getEmpDeptno)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.66+08:00", comments="Source Table: SCOTT.EMP")
    default Optional<Emp> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.661+08:00", comments="Source Table: SCOTT.EMP")
    default List<Emp> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.663+08:00", comments="Source Table: SCOTT.EMP")
    default List<Emp> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.664+08:00", comments="Source Table: SCOTT.EMP")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.665+08:00", comments="Source Table: SCOTT.EMP")
    static UpdateDSL<UpdateModel> updateAllColumns(Emp record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(empEmpno).equalTo(record::getEmpEmpno)
                .set(empEname).equalTo(record::getEmpEname)
                .set(empJob).equalTo(record::getEmpJob)
                .set(empMgr).equalTo(record::getEmpMgr)
                .set(empHiredate).equalTo(record::getEmpHiredate)
                .set(empSal).equalTo(record::getEmpSal)
                .set(empComm).equalTo(record::getEmpComm)
                .set(empDeptno).equalTo(record::getEmpDeptno);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.667+08:00", comments="Source Table: SCOTT.EMP")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Emp record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(empEmpno).equalToWhenPresent(record::getEmpEmpno)
                .set(empEname).equalToWhenPresent(record::getEmpEname)
                .set(empJob).equalToWhenPresent(record::getEmpJob)
                .set(empMgr).equalToWhenPresent(record::getEmpMgr)
                .set(empHiredate).equalToWhenPresent(record::getEmpHiredate)
                .set(empSal).equalToWhenPresent(record::getEmpSal)
                .set(empComm).equalToWhenPresent(record::getEmpComm)
                .set(empDeptno).equalToWhenPresent(record::getEmpDeptno);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Wed Oct 18 08:23:35 CST 2023
     */
//    long countByExample(EmpExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table SCOTT.EMP
//     *
//     * @mbg.generated Wed Oct 18 08:23:35 CST 2023
//     */
//    int deleteByExample(EmpExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table SCOTT.EMP
//     *
//     * @mbg.generated Wed Oct 18 08:23:35 CST 2023
//     */
//    List<Emp> selectByExample(EmpExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table SCOTT.EMP
//     *
//     * @mbg.generated Wed Oct 18 08:23:35 CST 2023
//     */
//    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table SCOTT.EMP
//     *
//     * @mbg.generated Wed Oct 18 08:23:35 CST 2023
//     */
//    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);
}