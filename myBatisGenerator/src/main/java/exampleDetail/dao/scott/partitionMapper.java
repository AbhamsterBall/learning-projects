package exampleDetail.dao.scott;

import static exampleDetail.dao.scott.partitionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import exampleDetail.model0.scott.partition;
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
public interface partitionMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.394+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    BasicColumn[] selectList = BasicColumn.columnList(iiiddd, insertDate);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.391+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.391+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.391+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<partition> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.392+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<partition> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.392+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("partitionResult")
    Optional<partition> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.392+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="partitionResult", value = {
        @Result(column="C_ID", property="iiiddd", jdbcType=JdbcType.NUMERIC, id=true),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<partition> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.392+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.393+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, partition, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.393+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, partition, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.393+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int deleteByPrimaryKey(Short iiiddd_) {
        return delete(c -> 
            c.where(iiiddd, isEqualTo(iiiddd_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.393+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int insert(partition record) {
        return MyBatis3Utils.insert(this::insert, record, partition, c ->
            c.map(iiiddd).toProperty("iiiddd")
            .map(insertDate).toProperty("insertDate")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.394+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int insertMultiple(Collection<partition> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, partition, c ->
            c.map(iiiddd).toProperty("iiiddd")
            .map(insertDate).toProperty("insertDate")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.394+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int insertSelective(partition record) {
        return MyBatis3Utils.insert(this::insert, record, partition, c ->
            c.map(iiiddd).toPropertyWhenPresent("iiiddd", record::getIiiddd)
            .map(insertDate).toPropertyWhenPresent("insertDate", record::getInsertDate)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.394+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default Optional<partition> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, partition, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.394+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default List<partition> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, partition, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.394+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default List<partition> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, partition, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.395+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default Optional<partition> selectByPrimaryKey(Short iiiddd_) {
        return selectOne(c ->
            c.where(iiiddd, isEqualTo(iiiddd_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.395+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, partition, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.395+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    static UpdateDSL<UpdateModel> updateAllColumns(partition record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(iiiddd).equalTo(record::getIiiddd)
                .set(insertDate).equalTo(record::getInsertDate);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.395+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(partition record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(iiiddd).equalToWhenPresent(record::getIiiddd)
                .set(insertDate).equalToWhenPresent(record::getInsertDate);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.395+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int updateByPrimaryKey(partition record) {
        return update(c ->
            c.set(insertDate).equalTo(record::getInsertDate)
            .where(iiiddd, isEqualTo(record::getIiiddd))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.396+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    default int updateByPrimaryKeySelective(partition record) {
        return update(c ->
            c.set(insertDate).equalToWhenPresent(record::getInsertDate)
            .where(iiiddd, isEqualTo(record::getIiiddd))
        );
    }
}