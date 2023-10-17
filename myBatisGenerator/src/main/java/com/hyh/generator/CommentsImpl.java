package com.hyh.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class CommentsImpl implements CommentGenerator {

    private Properties properties = new Properties();
    private boolean suppressDate = false;
    private boolean suppressAllComments = false;
    private boolean addRemarkComments = false;
    private SimpleDateFormat dateFormat;
    private boolean useLegacyGeneratedAnnotation = false;


    private void addUniversalComment(JavaElement element, IntrospectedTable table, IntrospectedColumn column, Boolean b, Set<FullyQualifiedJavaType> set) {
        if (!suppressAllComments) {
            element.addJavaDocLine("/**");
            element.addJavaDocLine(suppressDate ? "" : (dateFormat != null ? dateFormat.format(new Date()) : new Date().toString()));
            element.addJavaDocLine((element.getClass() == null ? "NULL" : element.getClass()) + ": " + (table == null ? "" : table.getFullyQualifiedTable()) + " " +
                    (column == null ? "" : column.getActualColumnName()) + " " +
                    (b == null ? "" : "deletable?" + b.booleanValue())
            ); //这里的三目运算符必须用括号括起来，否则还是会检查b.booleanValue()是否可执行，或者说没有成功识别到三目运算符
            if (set != null)
                set.forEach(i -> element.addJavaDocLine(i.getFullyQualifiedName()));
            if (addRemarkComments) {
                element.addJavaDocLine("tables: " + (table == null ? "none" : table.getRemarks()) + " cols: " + (column == null ? "none" : column.getRemarks()));
            }

            element.addJavaDocLine("*/");
        }

//        System.out.println(element.getClass().getName());
        if (element.getClass().getName().equals(Method.class.getName()) ||
                element.getClass().getName().equals(Field.class.getName())) {
            set.add(useLegacyGeneratedAnnotation ? new FullyQualifiedJavaType("javax.annotation.Generated") : new FullyQualifiedJavaType("jakarta.annotation.Generated"));
            StringBuilder sb = new StringBuilder();
            element.getJavaDocLines().forEach(i -> sb.append(i));
            if (sb.length() > 5) element.addAnnotation(this.getGeneratedAnnotation(sb.substring(3, sb.toString().length() - 2)));
        }
    }

    //org.mybatis.generator.internal.DefaultComment
    private String getGeneratedAnnotation(String comment) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("@Generated(");
        if (this.suppressAllComments) {
            buffer.append('"');
        } else {
            buffer.append("value=\"");
        }

        buffer.append(MyBatisGenerator.class.getName());
        buffer.append('"');
        if (!this.suppressDate && !this.suppressAllComments) {
            buffer.append(", date=\"");
            buffer.append(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now()));
            buffer.append('"');
        }

        if (!this.suppressAllComments) {
            buffer.append(", comments=\"");
            buffer.append(comment);
            buffer.append('"');
        }

        buffer.append(')');
        return buffer.toString();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate")); //忽略大小写的比较（toLowerCases & toUpperCases）
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
        this.useLegacyGeneratedAnnotation = StringUtility.isTrue(properties.getProperty("useLegacyGeneratedAnnotation"));
        String dateFormatString = properties.getProperty("dateFormat");
        if (StringUtility.stringHasValue(dateFormatString)) {
            this.dateFormat = new SimpleDateFormat(dateFormatString);
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addUniversalComment(field, introspectedTable, introspectedColumn, null, null);
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        addUniversalComment(field, introspectedTable, null, null, null);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addUniversalComment(topLevelClass, introspectedTable, null, null, null);
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        addUniversalComment(innerClass, introspectedTable, null, null, null);
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {
        addUniversalComment(innerClass, introspectedTable, null, b, null);
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        addUniversalComment(innerEnum, introspectedTable, null, null, null);
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addUniversalComment(method, introspectedTable, introspectedColumn, null, null);
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addUniversalComment(method, introspectedTable, introspectedColumn, null, null);
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        addUniversalComment(method, introspectedTable, null, null, null);
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (!suppressAllComments) {
            compilationUnit.addFileCommentLine("/**fileComments*/");
        }
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        if (!suppressAllComments) {
            xmlElement.addElement(new XmlElement("<!--xlmComment-->"));
        }
    }

    @Override
    public void addRootComment(XmlElement xmlElement) {
        if (!suppressAllComments) {
            xmlElement.addElement(new XmlElement("<!--xlmRootComment-->"));
        }
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
        addUniversalComment(method, introspectedTable, null, null, set);
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
        addUniversalComment(method, introspectedTable, introspectedColumn, null, set);
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
        addUniversalComment(field, introspectedTable, null, null, set);
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
        addUniversalComment(field, introspectedTable, introspectedColumn, null, set);
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
        addUniversalComment(innerClass, introspectedTable, null, null, set);
    }
}
