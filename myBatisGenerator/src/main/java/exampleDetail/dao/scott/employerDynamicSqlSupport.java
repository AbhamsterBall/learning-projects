package exampleDetail.dao.scott;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class employerDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.332+08:00", comments="Source Table: SCOTT.EMP")
    public static final employer employer = new employer();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.333+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public static final SqlColumn<Short> empno = employer.empno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.334+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public static final SqlColumn<String> ename = employer.ename;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.335+08:00", comments="Source field: SCOTT.EMP.JOB")
    public static final SqlColumn<String> job = employer.job;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.335+08:00", comments="Source field: SCOTT.EMP.MGR")
    public static final SqlColumn<Short> mgr = employer.mgr;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.335+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public static final SqlColumn<Date> startDate = employer.startDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.336+08:00", comments="Source field: SCOTT.EMP.SAL")
    public static final SqlColumn<BigDecimal> sal = employer.sal;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.336+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public static final SqlColumn<Short> deptno = employer.deptno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.333+08:00", comments="Source Table: SCOTT.EMP")
    public static final class employer extends SqlTable {
        public final SqlColumn<Short> empno = column("EMPNO", JDBCType.NUMERIC);

        public final SqlColumn<String> ename = column("ENAME", JDBCType.VARCHAR);

        public final SqlColumn<String> job = column("JOB", JDBCType.VARCHAR);

        public final SqlColumn<Short> mgr = column("MGR", JDBCType.NUMERIC);

        public final SqlColumn<Date> startDate = column("HIREDATE", JDBCType.TIMESTAMP);

        public final SqlColumn<BigDecimal> sal = column("SAL", JDBCType.NUMERIC);

        public final SqlColumn<Short> deptno = column("DEPTNO", JDBCType.NUMERIC);

        public employer() {
            super("SCOTT.EMP");
        }
    }
}