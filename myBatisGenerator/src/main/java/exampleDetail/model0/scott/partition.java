package exampleDetail.model0.scott;

import java.util.Date;
import javax.annotation.Generated;

public class partition {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.386+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.C_ID")
    private Short iiiddd;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.389+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.INSERT_DATE")
    private Date insertDate;

    public partition(Short iiiddd, Date insertDate) {
        this.iiiddd = iiiddd;
        this.insertDate = insertDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.386+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.C_ID")
    public Short getIiiddd() {
        return iiiddd;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.386+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.C_ID")
    public void setIiiddd(Short iiiddd) {
        this.iiiddd = iiiddd;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.389+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.INSERT_DATE")
    public Date getInsertDate() {
        return insertDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.39+08:00", comments="Source field: SCOTT.PARTITION_BY_RANGE.INSERT_DATE")
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}