package com.hyh.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;
import java.util.function.Supplier;

public final class ProductDynamicSupport {

    public static final Product product = new Product();
    public final SqlColumn<Short> id = product.id;
    public final SqlColumn<String> name = product.name;
    public final SqlColumn<String> description = product.description;
    public final SqlColumn<Short> price = product.price;


    public static final class Product extends AliasableSqlTable<Product> {

        public final SqlColumn<Short> id = column("ID", JDBCType.NUMERIC);
        public final SqlColumn<String> name = column("NAME", JDBCType.VARCHAR);
        public final SqlColumn<String> description = column("DESCRIPTION", JDBCType.VARCHAR);
        public final SqlColumn<Short> price = column("PRICE", JDBCType.NUMERIC);

        public Product() {
            super("HYH.PRODUCT", Product::new);
        }
    }
}
