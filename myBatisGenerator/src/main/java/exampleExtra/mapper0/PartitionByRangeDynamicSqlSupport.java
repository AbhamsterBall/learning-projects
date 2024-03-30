package exampleExtra.mapper0;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PartitionByRangeDynamicSqlSupport {
    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE  
    java.sql.JDBCType
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: null cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.817+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE  java.sql.JDBCTypeorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: null cols: none")
    public static final PartitionByRange partitionByRange = new PartitionByRange();

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE C_ID 
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: null cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.818+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE C_ID java.sql.JDBCTypejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: null cols: null")
    public static final SqlColumn<Short> cId = partitionByRange.cId;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE INSERT_DATE 
    java.sql.JDBCType
    java.util.Date
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: null cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.818+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.PARTITION_BY_RANGE INSERT_DATE java.sql.JDBCTypejava.util.Datejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: null cols: null")
    public static final SqlColumn<Date> insertDate = partitionByRange.insertDate;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.InnerClass: SCOTT.PARTITION_BY_RANGE  
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: null cols: none
    */
    public static final class PartitionByRange extends SqlTable {
        public final SqlColumn<Short> cId = column("C_ID", JDBCType.NUMERIC);

        public final SqlColumn<Date> insertDate = column("INSERT_DATE", JDBCType.TIMESTAMP);

        public PartitionByRange() {
            super("SCOTT.PARTITION_BY_RANGE");
        }
    }
}