package com.epam.model;

import com.epam.annotation.MyAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BusinessLogic implements Model {

    private static Logger logger = LogManager.getLogger(BusinessLogic.class);

    @Override
    public void printAnnotatedFields() {
        Class clazz = MyClass.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = (MyAnnotation) f.getAnnotation(MyAnnotation.class);
                String name = myAnnotation.name();
                int amount = myAnnotation.amount();
                System.out.println("-----------");
                System.out.println("field: " + f.getName());
                System.out.println("  @MyAnnotation(name = " + name + ")");
                System.out.println("  @MyAnnotation(amount = " + amount + ")");
            }
        }
    }

    @Override
    public void testUnknownObj() {
        MyClass object = new MyClass();
        try {
            Class clazz = object.getClass();
            Constructor constructor = clazz.getConstructor();
            Method[] methods = clazz.getMethods();
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("-----------");
            System.out.println("Simple name of class: " + clazz.getSimpleName());
            System.out.println("Name of class: " + clazz.getName());
            System.out.println("-----------");
            System.out.println("Name of constructor: " + constructor);
            System.out.println("-----------");
            System.out.println("ALL methods of class:");
            for (Method m : methods) {
                System.out.println("- " + m.getName());
            }
            System.out.println("-----------");
            System.out.println("DECLARED fields of class:");
            for (Field f : fields) {
                System.out.println("- " + f.getName());
            }

            fields[1].setAccessible(true);
            if (fields[1].getType() == int.class) {
                fields[1].setInt(object, 1000);
            }
            System.out.println("-----------");
            System.out.println("Set fields[1] of object - (fields[1].setAccessible(true)):");
            System.out.println(object);

            System.out.println("-----------");
            System.out.println("Invoke private method getInfo():");
            Method method = clazz.getDeclaredMethod("getInfo");
            method.setAccessible(true);
            method.invoke(object);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public void testGeneric() {
        MyGenericClass<MyClass> obj = new MyGenericClass<>(MyClass.class);
    }

    @Override
    public void setValueUnknownType() {
        MyClass object = new MyClass();
        try {
            Class clazz = object.getClass();
            System.out.println("-----------");
            System.out.println("Simple name of class: " + clazz.getSimpleName());
            System.out.println("-----------");
            System.out.println("Declared fields of class:");
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                System.out.println("- " + f.getName() + " | type: " + f.getType());
            }
            if (fields[0].getType() == String.class) {
                fields[0].setAccessible(true);
                fields[0].set(object, "new value");
            }
            if (fields[1].getType() == int.class) {
                fields[1].setAccessible(true);
                fields[1].setInt(object, 500);
            }
            if (fields[2].getType() == boolean.class) {
                fields[2].setAccessible(true);
                fields[2].setBoolean(object, true);
            }
            System.out.println("-----------");
            System.out.println("Object: " + object);

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public void invokeMethodsDifParam() {
        MyClass myClass = new MyClass();
        Class clazz = myClass.getClass();
        try {
            Method method1 = clazz.getDeclaredMethod("method1");
            method1.setAccessible(true);
            method1.invoke(myClass);

            Method method2 = clazz.getDeclaredMethod("method2", String.class);
            method2.setAccessible(true);
            String str = (String) method2.invoke(myClass, "Call method2 --> ");
            System.out.println(str);

            Method method3 = clazz.getDeclaredMethod("method3");
            method3.setAccessible(true);
            int number = (Integer) method3.invoke(myClass);
            System.out.println("Call method3 --> " + number);

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public void invokeMyMethod() {
        MyClass myClass = new MyClass();
        Class clazz = myClass.getClass();
        String[] strs = {"one", "two", "three"};
        try {
            Method method1 = clazz.getDeclaredMethod("myMethod", new Class[]{String.class, String[].class});
            method1.setAccessible(true);
            String[] strs2 = (String[]) method1.invoke(myClass, "Hello", strs);
            System.out.println("-----------");
            for (String s : strs2) {
                System.out.println(s);
            }

            Method method2 = clazz.getDeclaredMethod("myMethod", String[].class);
            method2.setAccessible(true);
            String[] strs3 = (String[]) method2.invoke(myClass, new Object[]{strs});
            System.out.println("-----------");
            for (String s : strs3) {
                System.out.println(s);
            }
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }
}
