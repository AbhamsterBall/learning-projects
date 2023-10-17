package exampleExtra.mapper0;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EmpDynamicSqlSupport {
    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP  
    java.sql.JDBCType
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: none
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.783+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP  java.sql.JDBCTypeorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: none")
    public static final Emp emp = new Emp();

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP EMPNO 
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: eno comment
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.784+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP EMPNO java.sql.JDBCTypejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: eno comment")
    public static final SqlColumn<Short> funkmpno = emp.funkmpno;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP ENAME 
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.785+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP ENAME java.sql.JDBCTypejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<String> funkname = emp.funkname;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP JOB 
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.785+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP JOB java.sql.JDBCTypejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<String> job = emp.job;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP MGR 
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.785+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP MGR java.sql.JDBCTypejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<Short> mgr = emp.mgr;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP HIREDATE 
    java.sql.JDBCType
    java.util.Date
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.785+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP HIREDATE java.sql.JDBCTypejava.util.Datejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<Date> hiredate = emp.hiredate;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP SAL 
    java.math.BigDecimal
    java.sql.JDBCType
    java.util.Date
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.785+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP SAL java.math.BigDecimaljava.sql.JDBCTypejava.util.Datejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<BigDecimal> sal = emp.sal;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP COMM 
    java.math.BigDecimal
    java.sql.JDBCType
    java.util.Date
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.786+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP COMM java.math.BigDecimaljava.sql.JDBCTypejava.util.Datejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<BigDecimal> comm = emp.comm;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP DEPTNO 
    java.math.BigDecimal
    java.sql.JDBCType
    java.util.Date
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: null
    */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-16T11:52:55.786+08:00", comments="10-16-2023 52:55class org.mybatis.generator.api.dom.java.Field: SCOTT.EMP DEPTNO java.math.BigDecimaljava.sql.JDBCTypejava.util.Datejavax.annotation.Generatedorg.mybatis.dynamic.sql.SqlColumnorg.mybatis.dynamic.sql.SqlTabletables: commentary cols: null")
    public static final SqlColumn<Short> deptno = emp.deptno;

    /**
    10-16-2023 52:55
    class org.mybatis.generator.api.dom.java.InnerClass: SCOTT.EMP  
    java.sql.JDBCType
    javax.annotation.Generated
    org.mybatis.dynamic.sql.SqlColumn
    org.mybatis.dynamic.sql.SqlTable
    tables: commentary cols: none
    */
    public static final class Emp extends SqlTable {
        public final SqlColumn<Short> funkmpno = column("EMPNO", JDBCType.NUMERIC);

        public final SqlColumn<String> funkname = column("ENAME", JDBCType.VARCHAR);

        public final SqlColumn<String> job = column("JOB", JDBCType.VARCHAR);

        public final SqlColumn<Short> mgr = column("MGR", JDBCType.NUMERIC);

        public final SqlColumn<Date> hiredate = column("HIREDATE", JDBCType.TIMESTAMP);

        public final SqlColumn<BigDecimal> sal = column("SAL", JDBCType.NUMERIC);

        public final SqlColumn<BigDecimal> comm = column("COMM", JDBCType.NUMERIC);

        public final SqlColumn<Short> deptno = column("DEPTNO", JDBCType.NUMERIC);

        public Emp() {
            super("SCOTT.EMP");
        }
    }
}