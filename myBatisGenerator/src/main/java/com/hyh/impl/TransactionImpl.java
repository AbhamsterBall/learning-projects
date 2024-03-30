package com.hyh.impl;

import org.apache.ibatis.transaction.Transaction;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 参考：org.mybatis.spring.transaction.SpringManagedTransaction
 */
public class TransactionImpl implements Transaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionImpl.class); //通过mybatis获取日志
    private DataSource dataSource;

    private Connection connection;
    public Boolean autoCommit; //connection.setAutoCommit  包装类才可以设置为空(默认为null)

    private int commitCount = 0;
    private int rollbackCount = 0;

    public TransactionImpl(Connection connection, boolean autoCommit) {
        this.connection = connection;
        this.autoCommit = autoCommit;
        LOGGER.debug(() -> {
            return "initialize HAMSTER Connection through connection AUTOCOMMIT " + this.autoCommit;
        });
    }

    public TransactionImpl(DataSource dataSource, boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
        LOGGER.debug(() -> {
            return "initialize HAMSTER Connection through datasource AUTOCOMMIT " + this.autoCommit;
        });
    }

    @Override
    public Connection getConnection() throws SQLException {

        if (this.connection == null) {
            this.openConnection();
        }

        return this.connection;
    }

    @Override
    public void commit() throws SQLException {
        if (this.connection != null &&  !this.autoCommit) {
            LOGGER.debug(() -> {
                return ++commitCount + "Committing HAMSTER Connection [" + this.connection + "]";
            });
            this.connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (this.connection != null && !this.autoCommit) {
            LOGGER.debug(() -> {
                return ++rollbackCount + "Rolling back HAMSTER Connection [" + this.connection + "]";
            });
            this.connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        LOGGER.debug(() -> {
            return "HAMSTER Connection [" + this.connection + "] is closing";
        });
        this.connection.close();
    }

    @Override
    public Integer getTimeout() throws SQLException {
        LOGGER.debug(() -> {
            return "HAMSTER Connection [" + this.connection + "] is checking timeout";
        });
        return this.connection.createStatement().getQueryTimeout(); // 因为DBCP没有实现获取网络超时时间，就只能获取sql语句执行超时时间
    }

    /**
     * Sets this connection's auto-commit mode to the given state.
     * If a connection is in auto-commit mode, then all its SQL statements will be executed and committed as individual
     * transactions. Otherwise, its SQL statements are grouped into transactions that are terminated by a call to either
     * the method commit or the method rollback. By default, new connections are in auto-commit mode.
     * 大概是说：自动提交为真，那么就单个sql每次都提交，自动提交为假，那么就手动提交，并将本次提交前的所有sql称为一个事务，这个事务会被提交方法或者回滚方法终止。
     * @throws SQLException
     */
    private void openConnection() {
        try {
            this.connection = dataSource.getConnection();
            this.autoCommit = this.autoCommit == null? this.connection.getAutoCommit() : this.autoCommit;
            this.connection.setAutoCommit(this.autoCommit); // 设置/传入值
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.debug(() -> {
            return "HAMSTER Connection [" + this.connection + "] will be managed by Hamster";
        });
    }
}
