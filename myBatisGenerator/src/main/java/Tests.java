import example.mapper0.EmpDynamicSqlSupport;
import example.mapper0.EmpDynamicSqlSupport.Emp;
import example.mapper0.EmpMapper;
import exampleM3.EmpMapperM3;
import exampleM3.model.EmpExampleM3;
import exampleM3.model.EmpM3;
import exampleSimple.mapper.EmpMapperSimple;
import org.junit.Test;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Tests {

    EmpMapper mapper = Utils.sessionUtil().getMapper(EmpMapper.class);
    EmpMapperM3 mapperM3 = Utils.sessionUtil().getMapper(EmpMapperM3.class);
    EmpMapperSimple mapperSimple = Utils.sessionUtil().getMapper(EmpMapperSimple.class);

    /**
     * 如果你不想使用maven结构，可以运行tomcat容器，在out/artifacts/mybatisGenerator_war_exploded/WEB-INF/lib下面可以生成所有包
     *  当然你得先把包放入tomcat的artifacts里面
     */
    @Test
    public void testMyBatis3DynamicSQL() {
        Emp emp = EmpDynamicSqlSupport.emp;
        SelectStatementProvider ssp = SqlBuilder.select(emp.allColumns()).from(emp).build().render(RenderingStrategies.MYBATIS3);
        List<example.model0.Emp> emps = mapper.selectMany(ssp);
        emps.forEach(System.out::println);

        ssp = SqlBuilder.select(emp.sal).from(emp).build().render(RenderingStrategies.MYBATIS3);
        emps = mapper.selectMany(ssp);
        emps.forEach(item -> System.out.print(item.getSal() + " "));
        System.out.println();
        emps.forEach(item -> System.out.print(item.getEname() + " "));


        EmpExampleM3 eem = new EmpExampleM3();
        EmpExampleM3.Criteria c = eem.createCriteria();
        c.andSalLessThan(new BigDecimal(10000));

        List<EmpM3> empM3s = mapperM3.selectByExample(eem);

        empM3s.forEach(System.out::println);


        mapperSimple.selectAll().forEach(System.out::println);

    }

    @Test
    public void runningMyBatis3() {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\MyBatis3.xml");
            FileInputStream inputStream = new FileInputStream("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            Configuration config = new ConfigurationParser(properties, warnings).parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite); // 回调的时候是否覆盖旧文件
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            VerboseProgressCallback vpc = new VerboseProgressCallback();
            myBatisGenerator.generate(vpc);

//            System.out.println(warnings);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runningMyBatis3Simple() {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\MyBatis3Simple.xml");
            FileInputStream inputStream = new FileInputStream("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            Configuration config = new ConfigurationParser(properties, warnings).parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite); // 回调的时候是否覆盖旧文件
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            VerboseProgressCallback vpc = new VerboseProgressCallback();
            myBatisGenerator.generate(vpc);

//            System.out.println(warnings);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runningMyBatis3DynamicSQLXMLThroughJAVA() {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\MyBatis3DynamicSql.xml");
            FileInputStream inputStream = new FileInputStream("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            Configuration config = new ConfigurationParser(properties, warnings).parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite); // 回调的时候是否覆盖旧文件
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            VerboseProgressCallback vpc = new VerboseProgressCallback();
            myBatisGenerator.generate(vpc);

//            System.out.println(warnings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runningJAVAMBGConfigurationThroughJava() {
        MyBatisGenerator generator = null;
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            Configuration config = new Configuration();

            config.addContext(configContextForMybatis3DynamicSQL());

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(new VerboseProgressCallback());
        } catch (InvalidConfigurationException | SQLException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Context configContextForMybatis3DynamicSQL() {
        Context c = new Context(ModelType.CONDITIONAL); //模型结构

        c.setId("configContextForMybatis3DynamicSQL"); //必须设置ID否则报错

        c.setJdbcConnectionConfiguration(configJDBCConnection());

        JavaClientGeneratorConfiguration jcgc = new JavaClientGeneratorConfiguration();
        jcgc.setTargetProject("src/main/java");
        jcgc.setTargetPackage("javaConfigExample");
        c.setJavaClientGeneratorConfiguration(jcgc);
        SqlMapGeneratorConfiguration sqlMapper = new SqlMapGeneratorConfiguration();
        sqlMapper.setTargetPackage("javaConfigExample");
        c.setSqlMapGeneratorConfiguration(sqlMapper);
        JavaModelGeneratorConfiguration jmgc = new JavaModelGeneratorConfiguration();
        jmgc.setTargetProject("src/main/java");
        jmgc.setTargetPackage("javaConfigModel");
        c.setJavaModelGeneratorConfiguration(jmgc);

        TableConfiguration table = new TableConfiguration(c);
        table.setSchema("scott");
        table.setTableName("emp");
        c.addTableConfiguration(table);

        return c;
    }

    public JDBCConnectionConfiguration configJDBCConnection() {
        JDBCConnectionConfiguration jdbc = new JDBCConnectionConfiguration();
        jdbc.setDriverClass("oracle.jdbc.driver.OracleDriver");
        jdbc.setConnectionURL("jdbc:oracle:thin:@localhost:1521:orcl");
        jdbc.setUserId("scott");
        jdbc.setPassword("123456");
        return jdbc;
    }


}
