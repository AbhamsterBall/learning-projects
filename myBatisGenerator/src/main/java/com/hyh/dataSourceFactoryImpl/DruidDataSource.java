package com.hyh.dataSourceFactoryImpl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.type.Alias;

import javax.sql.DataSource;
import java.util.Properties;

@Alias("DRUID")
// https://blog.csdn.net/m0_70031574/article/details/131947655
public class DruidDataSource implements DataSourceFactory { // java: illegal character: '\ufeff' remove BOM in File Editor and recreate file

    private DataSource dataSource;
    private Properties properties;

    public DruidDataSource() {
//        try {
//            this.dataSource = DruidDataSourceFactory.createDataSource(properties);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.dataSource = new ComboPooledDataSource();
//        this.dataSource = DataSourceUtil.getDataSource();
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        try {
            this.dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
