package com.railwayticket.dao_api.sql_annotation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                File directory = new File("./");
                String final_filepath = directory.getCanonicalPath()+ "/dao/src/main/resources/";
                FileReader fr= new FileReader(final_filepath+annotation.sqlfilename());
                Scanner scan = new Scanner(fr);
                String test = "t";
                while (scan.hasNextLine()) {
                    test = scan.nextLine();

                }
                field.set(this,test);
                fr.close();

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
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
                File directory = new File("./");
                String final_filepath = directory.getCanonicalPath()+ "/dao/src/main/resources/";
                FileReader frdecl= new FileReader(final_filepath+annotation.sqlfilename());
                Scanner scan = new Scanner(frdecl);
                String test = "t";
                while (scan.hasNextLine()) {
                    test = scan.nextLine();

                }
                declaredField.set(this, test);
                frdecl.close();
            } catch (IllegalAccessException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
