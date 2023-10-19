import com.hyh.service.impl.EmpServiceImpl;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:ApplicationContext.xml")
public class Run {

//    @Autowired
//    EmpService empService;

    @Test
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
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        System.out.println(app.getBean("dataSource"));
        System.out.println(app.getBean("sessionFactory"));
        app.getBean("empServiceImpl", EmpServiceImpl.class).insertBatchForEmpNormalBatch();
//        empService.insertBatchForEmpNormalBatch();
    }
}
