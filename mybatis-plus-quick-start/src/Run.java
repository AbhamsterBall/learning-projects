import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyh.mapper.EmpBaseMapper;
import com.hyh.mapper.EmpMapper;
import com.hyh.pojo.Emp;
import com.hyh.service.EmpService;
import com.hyh.service.impl.EmpServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.baomidou.mybatisplus.extension.injector.methods.*;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class Run {

    @Autowired
    EmpService empService;

    @Autowired
    EmpMapper empMapper;

    @Autowired
    EmpBaseMapper empBaseMapper;

    @Autowired
    DataSource dataSource;

    @Autowired
    ApplicationContext context;

//    private ILoggerFactory loggerFactory = org.slf4j.LoggerFactory.getILoggerFactory();
    private static final Logger LOGGER = Logger.getLogger(Run.class);

//    @Test
    public void runGenerator() {
        List<String> warnings = new ArrayList<>();
        try {
            Configuration conf = new ConfigurationParser(warnings).parseConfiguration(
                    new File("F:\\JAVA\\20231010\\mybatis-plus-quick-start\\src\\dynamicGenerator.xml"));
            MyBatisGenerator generator = new MyBatisGenerator(conf, new DefaultShellCallback(false),
                    warnings);
            generator.generate(new VerboseProgressCallback());
        } catch (IOException | XMLParserException | InvalidConfigurationException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testOne() {
        empService.setRecordsNo(5000);

        long start = System.currentTimeMillis();
        empService.insertBatchForEmpNormalBatch();
        long time = System.currentTimeMillis() - start;
        System.out.println("FunckedUpTimer" + time);
//        System.out.println(LOGGER.isInfoEnabled());
        LOGGER.info("one: " + time); //8281
    }

    @Test
    public void testTwo() {
        empService.setRecordsNo(5000);

        long start = System.currentTimeMillis();
        empService.insertBatchForEmpBatchService();
        long time = System.currentTimeMillis() - start;
        System.out.println("FunckedUpTimer" + time);
        LOGGER.info("two: " + time); //3303 service最快
    }

    @Test
    public void testThree() {
        empService.setRecordsNo(5000);

        long start = System.currentTimeMillis();
        empService.insertMultipleWithoutBatch();
        long time = System.currentTimeMillis() - start;
        System.out.println("FunckedUpTimer" + time);
        LOGGER.info("three: " + time); //Socket read timed out
    }

    @Test
    public void testFour() {
        empService.setRecordsNo(5000);

        long start = System.currentTimeMillis();
        empService.insertWithBaseMapper();
        LOGGER.info("four: " + (System.currentTimeMillis() - start)); //9282
    }

    @Test
    public void testLog() {
        LOGGER.info("123123123123");
    }

    @Test
    public void selectService() {

        int numClients = 30; // 设置客户端数量
        ExecutorService executor = Executors.newFixedThreadPool(numClients);

        // 创建一个计数器来等待所有客户端完成
        CountDownLatch latch = new CountDownLatch(numClients);

        // 创建多个客户端任务
        for (int i = 0; i < numClients; i++) {
            executor.submit(() -> {
                try {
                    while (true) {
                        LambdaQueryWrapper<Emp> lambda = new LambdaQueryWrapper<>();
                        lambda.gt(Emp::getEmpDeptno, 10).like(Emp::getEmpEname, 'f');
                        System.out.println(dataSource);
                        empService.getMap(lambda);
                        empService.getObj(lambda, Arrays::asList);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        // 等待所有客户端完成
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 可以在这里添加断言来验证测试的结果

    }

    @Autowired
    private Integer empNo;

    @Test
    public void testIPage() {

        LambdaQueryWrapper<Emp> qw = new LambdaQueryWrapper<>();
        qw.gt(Emp::getEmpDeptno, 10).like(Emp::getEmpEname, "f");

        IPage<Emp> page = new Page<>(1, 3);
        empBaseMapper.selectPage(page, qw);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
//        pageConfig = empService.testIPage(pageConfig, qw);
//
//        System.out.println(pageConfig.getPages());

        System.out.println(empNo);
    }



}
