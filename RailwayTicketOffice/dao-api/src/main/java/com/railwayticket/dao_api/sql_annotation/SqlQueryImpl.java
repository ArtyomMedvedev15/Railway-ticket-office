package com.railwayticket.dao_api.sql_annotation;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Scanner;

public abstract class SqlQueryImpl {

    public SqlQueryImpl(){setSqlFilename(this.getClass());}

    public void setSqlFilename(Class clazz){
        if (clazz.isAnnotationPresent(SqlQuery.class)) {
            SqlQuery annotation = (SqlQuery) clazz.getAnnotation(SqlQuery.class);
            try {
                Field field = clazz.getDeclaredField("name");
                field.setAccessible(true);

                InputStream input = SqlQueryImpl.class.getResourceAsStream("/"+annotation.sqlfilename());
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String sql_query="";
                String buff;
                while((buff = reader.readLine())!=null){
                    sql_query = buff;
                }
                field.set(this,sql_query);

            } catch (IllegalAccessException | NoSuchFieldException | IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //Processing annotations on properties
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            SqlQuery annotation = declaredField.getAnnotation(SqlQuery.class);
            if (null == annotation) {
                continue;
            }
            declaredField.setAccessible(true);
            try {
                InputStream input = SqlQueryImpl.class.getResourceAsStream("/"+annotation.sqlfilename());
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String sql_query="";
                String buff;
                while((buff = reader.readLine())!=null){
                    sql_query = buff;
                }
                declaredField.set(this,sql_query);
             } catch (IllegalAccessException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
