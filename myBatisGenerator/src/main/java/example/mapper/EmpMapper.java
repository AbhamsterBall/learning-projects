package example.mapper;

import static example.mapper.EmpDynamicSqlSupport.*;

import example.model.Emp;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface EmpMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Emp>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6749642+08:00", comments="Source Table: SCOTT.EMP")
    BasicColumn[] selectList = BasicColumn.columnList(empno, ename, job, mgr, hiredate, sal, comm, deptno);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6593392+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EmpResult", value = {
        @Result(column="EMPNO", property="empno", jdbcType=JdbcType.NUMERIC),
        @Result(column="ENAME", property="ename", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="MGR", property="mgr", jdbcType=JdbcType.NUMERIC),
        @Result(column="HIREDATE", property="hiredate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SAL", property="sal", jdbcType=JdbcType.NUMERIC),
        @Result(column="COMM", property="comm", jdbcType=JdbcType.NUMERIC),
        @Result(column="DEPTNO", property="deptno", jdbcType=JdbcType.NUMERIC)
    })
    List<Emp> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6622644+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EmpResult")
    Optional<Emp> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6632401+08:00", comments="Source Table: SCOTT.EMP")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.664216+08:00", comments="Source Table: SCOTT.EMP")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6661689+08:00", comments="Source Table: SCOTT.EMP")
    default int insert(Emp row) {
        return MyBatis3Utils.insert(this::insert, row, emp, c ->
            c.map(empno).toProperty("empno")
            .map(ename).toProperty("ename")
            .map(job).toProperty("job")
            .map(mgr).toProperty("mgr")
            .map(hiredate).toProperty("hiredate")
            .map(sal).toProperty("sal")
            .map(comm).toProperty("comm")
            .map(deptno).toProperty("deptno")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6700888+08:00", comments="Source Table: SCOTT.EMP")
    default int insertMultiple(Collection<Emp> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, emp, c ->
            c.map(empno).toProperty("empno")
            .map(ename).toProperty("ename")
            .map(job).toProperty("job")
            .map(mgr).toProperty("mgr")
            .map(hiredate).toProperty("hiredate")
            .map(sal).toProperty("sal")
            .map(comm).toProperty("comm")
            .map(deptno).toProperty("deptno")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6720286+08:00", comments="Source Table: SCOTT.EMP")
    default int insertSelective(Emp row) {
        return MyBatis3Utils.insert(this::insert, row, emp, c ->
            c.map(empno).toPropertyWhenPresent("empno", row::getEmpno)
            .map(ename).toPropertyWhenPresent("ename", row::getEname)
            .map(job).toPropertyWhenPresent("job", row::getJob)
            .map(mgr).toPropertyWhenPresent("mgr", row::getMgr)
            .map(hiredate).toPropertyWhenPresent("hiredate", row::getHiredate)
            .map(sal).toPropertyWhenPresent("sal", row::getSal)
            .map(comm).toPropertyWhenPresent("comm", row::getComm)
            .map(deptno).toPropertyWhenPresent("deptno", row::getDeptno)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6759293+08:00", comments="Source Table: SCOTT.EMP")
    default Optional<Emp> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6769047+08:00", comments="Source Table: SCOTT.EMP")
    default List<Emp> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6769047+08:00", comments="Source Table: SCOTT.EMP")
    default List<Emp> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6778797+08:00", comments="Source Table: SCOTT.EMP")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, emp, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6778797+08:00", comments="Source Table: SCOTT.EMP")
    static UpdateDSL<UpdateModel> updateAllColumns(Emp row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(empno).equalTo(row::getEmpno)
                .set(ename).equalTo(row::getEname)
                .set(job).equalTo(row::getJob)
                .set(mgr).equalTo(row::getMgr)
                .set(hiredate).equalTo(row::getHiredate)
                .set(sal).equalTo(row::getSal)
                .set(comm).equalTo(row::getComm)
                .set(deptno).equalTo(row::getDeptno);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6789047+08:00", comments="Source Table: SCOTT.EMP")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Emp row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(empno).equalToWhenPresent(row::getEmpno)
                .set(ename).equalToWhenPresent(row::getEname)
                .set(job).equalToWhenPresent(row::getJob)
                .set(mgr).equalToWhenPresent(row::getMgr)
                .set(hiredate).equalToWhenPresent(row::getHiredate)
                .set(sal).equalToWhenPresent(row::getSal)
                .set(comm).equalToWhenPresent(row::getComm)
                .set(deptno).equalToWhenPresent(row::getDeptno);
    }
}