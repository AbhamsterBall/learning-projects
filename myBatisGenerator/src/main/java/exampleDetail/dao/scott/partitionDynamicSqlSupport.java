package exampleDetail.dao.scott;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class partitionDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.39+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    public static final partition partition = new partition();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.391+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.C_ID")
    public static final SqlColumn<Short> iiiddd = partition.iiiddd;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.391+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.INSERT_DATE")
    public static final SqlColumn<Date> insertDate = partition.insertDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.39+08:00", comments="Source Table: SCOTT.PARTITION_BY_RANGE")
    public static final class partition extends SqlTable {
        public final SqlColumn<Short> iiiddd = column("C_ID", JDBCType.NUMERIC);

        public final SqlColumn<Date> insertDate = column("INSERT_DATE", JDBCType.TIMESTAMP);

        public partition() {
            super("SCOTT.PARTITION_BY_RANGE");
        }
    }
}