/**fileComments*/
package exampleExtra.mapper0;

import static exampleExtra.mapper0.EmpDynamicSqlSupport.*;

import exampleExtra.model0.Emp;
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
public interface EmpMapper {
    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP  
    org.mybatis.dynamic.sql.BasicColumn[]
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.804+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP  org.mybatis.dynamic.sql.BasicColumn[]tables: commentary cols: none")
    BasicColumn[] selectList = BasicColumn.columnList(funkmpno, funkname, job, mgr, hiredate, sal, comm, deptno);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.select.render.SelectStatementProvider
    org.apache.ibatis.annotations.SelectProvider
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.787+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.select.render.SelectStatementProviderorg.apache.ibatis.annotations.SelectProvidertables: commentary cols: none")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
    org.apache.ibatis.annotations.DeleteProvider
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.789+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.delete.render.DeleteStatementProviderorg.apache.ibatis.annotations.DeleteProvidertables: commentary cols: none")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.apache.ibatis.annotations.InsertProvider
    exampleExtra.model0.Emp
    org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.789+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.apache.ibatis.annotations.InsertProviderexampleExtra.model0.Emporg.mybatis.dynamic.sql.insert.render.InsertStatementProvidertables: commentary cols: none")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Emp> insertStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
    org.apache.ibatis.annotations.InsertProvider
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.793+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProviderorg.apache.ibatis.annotations.InsertProviderexampleExtra.model0.Emptables: commentary cols: none")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Emp> multipleInsertStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    java.util.Optional<exampleExtra.model0.Emp>
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.select.render.SelectStatementProvider
    org.apache.ibatis.annotations.SelectProvider
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.794+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  java.util.Optional<exampleExtra.model0.Emp>org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.select.render.SelectStatementProviderorg.apache.ibatis.annotations.SelectProviderexampleExtra.model0.Emptables: commentary cols: none")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EmpResult")
    Optional<Emp> selectOne(SelectStatementProvider selectStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    java.util.List
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.select.render.SelectStatementProvider
    org.apache.ibatis.annotations.SelectProvider
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.795+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  java.util.Listorg.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.select.render.SelectStatementProviderorg.apache.ibatis.annotations.SelectProviderexampleExtra.model0.Emptables: commentary cols: none")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EmpResult", value = {
        @Result(column="EMPNO", property="funkmpno", jdbcType=JdbcType.NUMERIC),
        @Result(column="ENAME", property="funkname", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="MGR", property="mgr", jdbcType=JdbcType.NUMERIC),
        @Result(column="HIREDATE", property="hiredate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SAL", property="sal", jdbcType=JdbcType.NUMERIC),
        @Result(column="COMM", property="comm", jdbcType=JdbcType.NUMERIC),
        @Result(column="DEPTNO", property="deptno", jdbcType=JdbcType.NUMERIC)
    })
    List<Emp> selectMany(SelectStatementProvider selectStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.apache.ibatis.annotations.UpdateProvider
    org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.797+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.apache.ibatis.annotations.UpdateProviderorg.mybatis.dynamic.sql.update.render.UpdateStatementProvidertables: commentary cols: none")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    org.mybatis.dynamic.sql.select.CountDSLCompleter
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.798+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsorg.mybatis.dynamic.sql.select.CountDSLCompletertables: commentary cols: none")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, emp, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    org.mybatis.dynamic.sql.delete.DeleteDSLCompleter
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.798+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsorg.mybatis.dynamic.sql.delete.DeleteDSLCompletertables: commentary cols: none")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, emp, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.799+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3UtilsexampleExtra.model0.Emptables: commentary cols: none")
    default int insert(Emp record) {
        return MyBatis3Utils.insert(this::insert, record, emp, c ->
            c.map(funkmpno).toProperty("funkmpno")
            .map(funkname).toProperty("funkname")
            .map(job).toProperty("job")
            .map(mgr).toProperty("mgr")
            .map(hiredate).toProperty("hiredate")
            .map(sal).toProperty("sal")
            .map(comm).toProperty("comm")
            .map(deptno).toProperty("deptno")
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.8+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3UtilsexampleExtra.model0.Emptables: commentary cols: none")
    default int insertMultiple(Collection<Emp> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, emp, c ->
            c.map(funkmpno).toProperty("funkmpno")
            .map(funkname).toProperty("funkname")
            .map(job).toProperty("job")
            .map(mgr).toProperty("mgr")
            .map(hiredate).toProperty("hiredate")
            .map(sal).toProperty("sal")
            .map(comm).toProperty("comm")
            .map(deptno).toProperty("deptno")
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.801+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3UtilsexampleExtra.model0.Emptables: commentary cols: none")
    default int insertSelective(Emp record) {
        return MyBatis3Utils.insert(this::insert, record, emp, c ->
            c.map(funkmpno).toPropertyWhenPresent("funkmpno", record::getFunkmpno)
            .map(funkname).toPropertyWhenPresent("funkname", record::getFunkname)
            .map(job).toPropertyWhenPresent("job", record::getJob)
            .map(mgr).toPropertyWhenPresent("mgr", record::getMgr)
            .map(hiredate).toPropertyWhenPresent("hiredate", record::getHiredate)
            .map(sal).toPropertyWhenPresent("sal", record::getSal)
            .map(comm).toPropertyWhenPresent("comm", record::getComm)
            .map(deptno).toPropertyWhenPresent("deptno", record::getDeptno)
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    java.util.Optional<exampleExtra.model0.Emp>
    org.mybatis.dynamic.sql.select.SelectDSLCompleter
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.806+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  java.util.Optional<exampleExtra.model0.Emp>org.mybatis.dynamic.sql.select.SelectDSLCompleterorg.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilstables: commentary cols: none")
    default Optional<Emp> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, emp, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.select.SelectDSLCompleter
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    java.util.List<exampleExtra.model0.Emp>
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.807+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.select.SelectDSLCompleterorg.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsjava.util.List<exampleExtra.model0.Emp>tables: commentary cols: none")
    default List<Emp> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, emp, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.select.SelectDSLCompleter
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    java.util.List<exampleExtra.model0.Emp>
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.807+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.select.SelectDSLCompleterorg.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsjava.util.List<exampleExtra.model0.Emp>tables: commentary cols: none")
    default List<Emp> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, emp, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    org.mybatis.dynamic.sql.update.UpdateDSLCompleter
    int
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.809+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsorg.mybatis.dynamic.sql.update.UpdateDSLCompleterinttables: commentary cols: none")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, emp, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.81+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>exampleExtra.model0.Emptables: commentary cols: none")
    static UpdateDSL<UpdateModel> updateAllColumns(Emp record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(funkmpno).equalTo(record::getFunkmpno)
                .set(funkname).equalTo(record::getFunkname)
                .set(job).equalTo(record::getJob)
                .set(mgr).equalTo(record::getMgr)
                .set(hiredate).equalTo(record::getHiredate)
                .set(sal).equalTo(record::getSal)
                .set(comm).equalTo(record::getComm)
                .set(deptno).equalTo(record::getDeptno);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  
    org.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>
    exampleExtra.model0.Emp
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.812+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.EMP  org.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>exampleExtra.model0.Emptables: commentary cols: none")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Emp record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(funkmpno).equalToWhenPresent(record::getFunkmpno)
                .set(funkname).equalToWhenPresent(record::getFunkname)
                .set(job).equalToWhenPresent(record::getJob)
                .set(mgr).equalToWhenPresent(record::getMgr)
                .set(hiredate).equalToWhenPresent(record::getHiredate)
                .set(sal).equalToWhenPresent(record::getSal)
                .set(comm).equalToWhenPresent(record::getComm)
                .set(deptno).equalToWhenPresent(record::getDeptno);
    }
}