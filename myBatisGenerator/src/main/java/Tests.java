import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.PageObjectUtil;
import com.hyh.dao.ProductDAO;
import com.hyh.dao.ProductDynamicSupport;
import com.hyh.pojo.Product;
import example.mapper0.EmpDynamicSqlSupport;
import example.mapper0.EmpDynamicSqlSupport.Emp;
import example.mapper0.EmpMapper;
import exampleDetail.dao.scott.partitionDynamicSqlSupport;
import exampleDetail.dao.scott.partitionMapper;
import exampleDetail.model0.scott.partition;
import exampleExtra.mapper0.PartitionByRangeDynamicSqlSupport;
import exampleExtra.mapper0.PartitionByRangeMapper;
import exampleExtra.model0.PartitionByRange;
import exampleM3.EmpMapperM3;
import exampleM3.model.EmpExampleM3;
import exampleM3.model.EmpM3;
import exampleSimple.mapper.EmpMapperSimple;
import exampleSimple.model.EmpSimple;
import org.apache.ibatis.session.SqlSession;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.mybatis.dynamic.sql.AbstractColumnComparisonCondition;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlCriterion;
import org.mybatis.dynamic.sql.VisitableCondition;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.where.condition.IsBetween;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.sql.Date;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import static exampleExtra.mapper0.PartitionByRangeDynamicSqlSupport.*;


public class Tests {

    SqlSession session = Utils.sessionUtil(); //每次生成类的时候的session已经不同了，所以还是要在这里取出
    EmpMapper mapper = session.getMapper(EmpMapper.class);
    EmpMapperM3 mapperM3 = session.getMapper(EmpMapperM3.class);
    EmpMapperSimple mapperSimple = session.getMapper(EmpMapperSimple.class);
    partitionMapper mapperpartition = session.getMapper(partitionMapper.class);
    PartitionByRangeMapper mapperPBR = session.getMapper(PartitionByRangeMapper.class);
    ProductDAO productMapper = session.getMapper(ProductDAO.class);

    /**
     * 如果你不想使用maven结构，可以运行tomcat容器，在out/artifacts/mybatisGenerator_war_exploded/WEB-INF/lib下面可以生成所有包
     *  当然你得先把包放入tomcat的artifacts里面
     */
    @Test
    public void testMyBatis3DynamicSQL() {
        Emp emp = EmpDynamicSqlSupport.emp;
        SelectStatementProvider ssp = select(emp.allColumns()).
                from(emp).build().render(RenderingStrategies.MYBATIS3);
        List<example.model0.Emp> emps = mapper.selectMany(ssp);
        emps.forEach(System.out::println);

        ssp = select(emp.sal).from(emp).build().render(RenderingStrategies.MYBATIS3);
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


        //insert multiple 通过对线程的控制提高效率
//        employer.insertMultiple()
        List<partition> records = Arrays.asList(new partition((short) 7, new Date(1634000000)), new partition((short) 8, new Date(1634000001)));

        System.out.println(records);

        partitionDynamicSqlSupport.partition par = partitionDynamicSqlSupport.partition;
        BatchInsert.Builder builder = new BatchInsert.Builder().withInsertStatement(SqlBuilder.insert(records).into(par).map(par.iiiddd).toProperty("iiiddd").map(par.insertDate).toProperty("insertDate").build().render(RenderingStrategies.MYBATIS3).getInsertStatement());
        builder = builder.withRecords(records);
        BatchInsert<partition> batchInsert = builder.build();

//        System.out.println(SqlBuilder.insert(records).into(par).map(par.iiiddd).toProperty("iiiddd").map(par.insertDate).toProperty("insertDate").build().render(RenderingStrategies.MYBATIS3).getInsertStatement());
        System.out.println(batchInsert.insertStatements());
        batchInsert.insertStatements().forEach(mapperpartition::insert);
//        System.out.println(mapperpartition.insert(batchInsert.insertStatements().get(0)));

//        session.commit();
        session.rollback();

        // LocalDate.now().atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli())
        UpdateStatementProvider update = update(partitionByRange).
                set(insertDate).equalTo(new Date(1734000000)).
                where(cId, isEqualTo(cId)).build().render(RenderingStrategies.MYBATIS3);
        mapperPBR.update(update);
        session.commit();

        List record = new ArrayList<Product>();
        for (int i = 22 ; i < 31 ; i++) {
            record.add(new Product(i, "popo", "mimi", 9000));
        }

        ProductDynamicSupport.Product pro = ProductDynamicSupport.product;
        BatchInsert.Builder builder1 = new BatchInsert.Builder().
                withInsertStatement(SqlBuilder.insert(record).into(pro).
                        map(pro.id).toProperty("id").
                        map(pro.description).toProperty("description").
                        map(pro.name).toProperty("name").
                        map(pro.price).toProperty("price").
                        build().render(RenderingStrategies.MYBATIS3).getInsertStatement());
        builder1 = builder1.withRecords(record);
        BatchInsert<Product> batchInsert0 = builder1.build();

        batchInsert0.insertStatements().forEach(productMapper::insert);

//        session.commit();

//        PageHelper.startPage(2, 2);
//        List<EmpSimple> l = mapperSimple.selectAll();

//        mapperSimple.selectByPageNumSize(mapperSimple.selectAll().get(1), 1, 10);
//        Page listPage = (Page) mapperSimple.selectAll();
//        System.out.println(listPage.getResult());
//        while (page.isHasNextPage()) {
//            System.out.println(page.getNextPage());
//            System.out.println("NEXT PAGE-----------------");
//        }
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
    public void runningMyBatisDetail() {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\MyBatisDetailConfig.xml");
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
    public void runningMyBatisExtra() {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File("F:\\JAVA\\20231010\\myBatisGenerator\\src\\main\\resources\\MyBatisExtra.xml");
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
