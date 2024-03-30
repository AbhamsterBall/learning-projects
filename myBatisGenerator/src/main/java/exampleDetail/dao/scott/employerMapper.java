package exampleDetail.dao.scott;

import static exampleDetail.dao.scott.employerDynamicSqlSupport.*;

import exampleDetail.model0.scott.employer;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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
public interface employerMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.366+08:00", comments="Source Table: SCOTT.EMP")
    BasicColumn[] selectList = BasicColumn.columnList(empno, ename, job, mgr, startDate, sal, deptno);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.339+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.342+08:00", comments="Source Table: SCOTT.EMP")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.343+08:00", comments="Source Table: SCOTT.EMP")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<employer> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.345+08:00", comments="Source Table: SCOTT.EMP")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<employer> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.347+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("employerResult")
    Optional<employer> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.349+08:00", comments="Source Table: SCOTT.EMP")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="employerResult", value = {
        @Result(column="EMPNO", property="empno", jdbcType=JdbcType.NUMERIC),
        @Result(column="ENAME", property="ename", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="MGR", property="mgr", jdbcType=JdbcType.NUMERIC),
        @Result(column="HIREDATE", property="startDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SAL", property="sal", jdbcType=JdbcType.NUMERIC),
        @Result(column="DEPTNO", property="deptno", jdbcType=JdbcType.NUMERIC)
    })
    List<employer> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.353+08:00", comments="Source Table: SCOTT.EMP")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.354+08:00", comments="Source Table: SCOTT.EMP")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, employer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.355+08:00", comments="Source Table: SCOTT.EMP")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, employer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.357+08:00", comments="Source Table: SCOTT.EMP")
    default int insert(employer record) {
        return MyBatis3Utils.insert(this::insert, record, employer, c ->
            c.map(empno).toProperty("empno")
            .map(ename).toProperty("ename")
            .map(job).toProperty("job")
            .map(mgr).toProperty("mgr")
            .map(startDate).toProperty("startDate")
            .map(sal).toProperty("sal")
            .map(deptno).toProperty("deptno")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.359+08:00", comments="Source Table: SCOTT.EMP")
    default int insertMultiple(Collection<employer> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, employer, c ->
            c.map(empno).toProperty("empno")
            .map(ename).toProperty("ename")
            .map(job).toProperty("job")
            .map(mgr).toProperty("mgr")
            .map(startDate).toProperty("startDate")
            .map(sal).toProperty("sal")
            .map(deptno).toProperty("deptno")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.361+08:00", comments="Source Table: SCOTT.EMP")
    default int insertSelective(employer record) {
        return MyBatis3Utils.insert(this::insert, record, employer, c ->
            c.map(empno).toPropertyWhenPresent("empno", record::getEmpno)
            .map(ename).toPropertyWhenPresent("ename", record::getEname)
            .map(job).toPropertyWhenPresent("job", record::getJob)
            .map(mgr).toPropertyWhenPresent("mgr", record::getMgr)
            .map(startDate).toPropertyWhenPresent("startDate", record::getStartDate)
            .map(sal).toPropertyWhenPresent("sal", record::getSal)
            .map(deptno).toPropertyWhenPresent("deptno", record::getDeptno)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.37+08:00", comments="Source Table: SCOTT.EMP")
    default Optional<employer> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, employer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.372+08:00", comments="Source Table: SCOTT.EMP")
    default List<employer> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, employer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.373+08:00", comments="Source Table: SCOTT.EMP")
    default List<employer> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, employer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.374+08:00", comments="Source Table: SCOTT.EMP")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, employer, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.376+08:00", comments="Source Table: SCOTT.EMP")
    static UpdateDSL<UpdateModel> updateAllColumns(employer record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(empno).equalTo(record::getEmpno)
                .set(ename).equalTo(record::getEname)
                .set(job).equalTo(record::getJob)
                .set(mgr).equalTo(record::getMgr)
                .set(startDate).equalTo(record::getStartDate)
                .set(sal).equalTo(record::getSal)
                .set(deptno).equalTo(record::getDeptno);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.377+08:00", comments="Source Table: SCOTT.EMP")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(employer record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(empno).equalToWhenPresent(record::getEmpno)
                .set(ename).equalToWhenPresent(record::getEname)
                .set(job).equalToWhenPresent(record::getJob)
                .set(mgr).equalToWhenPresent(record::getMgr)
                .set(startDate).equalToWhenPresent(record::getStartDate)
                .set(sal).equalToWhenPresent(record::getSal)
                .set(deptno).equalToWhenPresent(record::getDeptno);
    }
}