/**fileComments*/
package exampleExtra.mapper0;

import static exampleExtra.mapper0.PartitionByRangeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import exampleExtra.model0.PartitionByRange;
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
public interface PartitionByRangeMapper {
    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.BasicColumn[]
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.82+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.BasicColumn[]tables: null cols: none")
    BasicColumn[] selectList = BasicColumn.columnList(cId, insertDate);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.select.render.SelectStatementProvider
    org.apache.ibatis.annotations.SelectProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.818+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.select.render.SelectStatementProviderorg.apache.ibatis.annotations.SelectProvidertables: null cols: none")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
    org.apache.ibatis.annotations.DeleteProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.818+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.mybatis.dynamic.sql.delete.render.DeleteStatementProviderorg.apache.ibatis.annotations.DeleteProvidertables: null cols: none")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    exampleExtra.model0.PartitionByRange
    org.apache.ibatis.annotations.InsertProvider
    org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.818+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.SqlProviderAdapterexampleExtra.model0.PartitionByRangeorg.apache.ibatis.annotations.InsertProviderorg.mybatis.dynamic.sql.insert.render.InsertStatementProvidertables: null cols: none")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<PartitionByRange> insertStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    exampleExtra.model0.PartitionByRange
    org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
    org.apache.ibatis.annotations.InsertProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.SqlProviderAdapterexampleExtra.model0.PartitionByRangeorg.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProviderorg.apache.ibatis.annotations.InsertProvidertables: null cols: none")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<PartitionByRange> multipleInsertStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    java.util.Optional<exampleExtra.model0.PartitionByRange>
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    exampleExtra.model0.PartitionByRange
    org.mybatis.dynamic.sql.select.render.SelectStatementProvider
    org.apache.ibatis.annotations.SelectProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  java.util.Optional<exampleExtra.model0.PartitionByRange>org.mybatis.dynamic.sql.util.SqlProviderAdapterexampleExtra.model0.PartitionByRangeorg.mybatis.dynamic.sql.select.render.SelectStatementProviderorg.apache.ibatis.annotations.SelectProvidertables: null cols: none")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PartitionByRangeResult")
    Optional<PartitionByRange> selectOne(SelectStatementProvider selectStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    java.util.List
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    exampleExtra.model0.PartitionByRange
    org.mybatis.dynamic.sql.select.render.SelectStatementProvider
    org.apache.ibatis.annotations.SelectProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  java.util.Listorg.mybatis.dynamic.sql.util.SqlProviderAdapterexampleExtra.model0.PartitionByRangeorg.mybatis.dynamic.sql.select.render.SelectStatementProviderorg.apache.ibatis.annotations.SelectProvidertables: null cols: none")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PartitionByRangeResult", value = {
        @Result(column="C_ID", property="cId", jdbcType=JdbcType.NUMERIC, id=true),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<PartitionByRange> selectMany(SelectStatementProvider selectStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.SqlProviderAdapter
    org.apache.ibatis.annotations.UpdateProvider
    org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.SqlProviderAdapterorg.apache.ibatis.annotations.UpdateProviderorg.mybatis.dynamic.sql.update.render.UpdateStatementProvidertables: null cols: none")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    org.mybatis.dynamic.sql.select.CountDSLCompleter
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsorg.mybatis.dynamic.sql.select.CountDSLCompletertables: null cols: none")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, partitionByRange, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    org.mybatis.dynamic.sql.delete.DeleteDSLCompleter
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsorg.mybatis.dynamic.sql.delete.DeleteDSLCompletertables: null cols: none")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, partitionByRange, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.819+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  tables: null cols: none")
    default int deleteByPrimaryKey(Short cId_) {
        return delete(c -> 
            c.where(cId, isEqualTo(cId_))
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    exampleExtra.model0.PartitionByRange
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.82+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3UtilsexampleExtra.model0.PartitionByRangetables: null cols: none")
    default int insert(PartitionByRange record) {
        return MyBatis3Utils.insert(this::insert, record, partitionByRange, c ->
            c.map(cId).toProperty("cId")
            .map(insertDate).toProperty("insertDate")
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    exampleExtra.model0.PartitionByRange
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.82+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3UtilsexampleExtra.model0.PartitionByRangetables: null cols: none")
    default int insertMultiple(Collection<PartitionByRange> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, partitionByRange, c ->
            c.map(cId).toProperty("cId")
            .map(insertDate).toProperty("insertDate")
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    exampleExtra.model0.PartitionByRange
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.82+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3UtilsexampleExtra.model0.PartitionByRangetables: null cols: none")
    default int insertSelective(PartitionByRange record) {
        return MyBatis3Utils.insert(this::insert, record, partitionByRange, c ->
            c.map(cId).toPropertyWhenPresent("cId", record::getcId)
            .map(insertDate).toPropertyWhenPresent("insertDate", record::getInsertDate)
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    java.util.Optional<exampleExtra.model0.PartitionByRange>
    org.mybatis.dynamic.sql.select.SelectDSLCompleter
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.82+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  java.util.Optional<exampleExtra.model0.PartitionByRange>org.mybatis.dynamic.sql.select.SelectDSLCompleterorg.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilstables: null cols: none")
    default Optional<PartitionByRange> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, partitionByRange, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.select.SelectDSLCompleter
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    java.util.List<exampleExtra.model0.PartitionByRange>
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.82+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.select.SelectDSLCompleterorg.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsjava.util.List<exampleExtra.model0.PartitionByRange>tables: null cols: none")
    default List<PartitionByRange> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, partitionByRange, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.select.SelectDSLCompleter
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    java.util.List<exampleExtra.model0.PartitionByRange>
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.select.SelectDSLCompleterorg.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsjava.util.List<exampleExtra.model0.PartitionByRange>tables: null cols: none")
    default List<PartitionByRange> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, partitionByRange, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    java.util.Optional<exampleExtra.model0.PartitionByRange>
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  java.util.Optional<exampleExtra.model0.PartitionByRange>tables: null cols: none")
    default Optional<PartitionByRange> selectByPrimaryKey(Short cId_) {
        return selectOne(c ->
            c.where(cId, isEqualTo(cId_))
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils
    org.mybatis.dynamic.sql.update.UpdateDSLCompleter
    int
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utilsorg.mybatis.dynamic.sql.update.UpdateDSLCompleterinttables: null cols: none")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, partitionByRange, completer);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    exampleExtra.model0.PartitionByRange
    org.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  exampleExtra.model0.PartitionByRangeorg.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>tables: null cols: none")
    static UpdateDSL<UpdateModel> updateAllColumns(PartitionByRange record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cId).equalTo(record::getcId)
                .set(insertDate).equalTo(record::getInsertDate);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    exampleExtra.model0.PartitionByRange
    org.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  exampleExtra.model0.PartitionByRangeorg.mybatis.dynamic.sql.update.UpdateDSL<org.mybatis.dynamic.sql.update.UpdateModel>tables: null cols: none")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(PartitionByRange record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cId).equalToWhenPresent(record::getcId)
                .set(insertDate).equalToWhenPresent(record::getInsertDate);
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    exampleExtra.model0.PartitionByRange
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  exampleExtra.model0.PartitionByRangetables: null cols: none")
    default int updateByPrimaryKey(PartitionByRange record) {
        return update(c ->
            c.set(insertDate).equalTo(record::getInsertDate)
            .where(cId, isEqualTo(record::getcId))
        );
    }

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  
    exampleExtra.model0.PartitionByRange
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.821+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Method: SCOTT.PARTITION_BY_RANGE  exampleExtra.model0.PartitionByRangetables: null cols: none")
    default int updateByPrimaryKeySelective(PartitionByRange record) {
        return update(c ->
            c.set(insertDate).equalToWhenPresent(record::getInsertDate)
            .where(cId, isEqualTo(record::getcId))
        );
    }
}