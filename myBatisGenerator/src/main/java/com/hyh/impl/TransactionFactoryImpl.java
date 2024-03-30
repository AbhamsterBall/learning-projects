package com.hyh.impl;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class TransactionFactoryImpl implements TransactionFactory {

    public boolean autoCommit;

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    @Override
    public void setProperties(Properties props) {
        TransactionFactory.super.setProperties(props); //当前版本3.5.8, 3.5.2之后变为默认方法
    }

    @Override
    public Transaction newTransaction(Connection connection) {
        return new TransactionImpl(connection, autoCommit);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
        return new TransactionImpl(dataSource, autoCommit);
    }
}
