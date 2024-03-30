package com.hyh.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.kotlin.KotlinFunction;

import java.util.List;

public class GeneratorPlugin extends org.mybatis.generator.api.PluginAdapter {
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // TODO Auto-generated method stub
        System.out.println("Field generated");
        return true;
    }

    @Override
    public boolean validate(List<String> list) {
        return false;
    }
}
