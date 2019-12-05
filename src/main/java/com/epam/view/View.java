package com.epam.view;

import com.epam.model.MyClass;
import com.epam.model.MyGenericClass;
import com.epam.annotation.MyAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);


    public View() {
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - Print fields in MyClass that are annotated by MyAnnotation");
        menu.put("2", " 2 - Test unknown object");
        menu.put("3", " 3 - Test generic class");
        menu.put("4", " 4 - Set value into field not knowing its type");
        menu.put("5", " 5 - Invoke method (three method with different parameters and return types)");
        menu.put("6", " 6 - Invoke myMethod(String a, int ... args) and myMethod(String … args)");

        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::printAnnotatedFields);
        methodsMenu.put("2", this::testUnknownObj);
        methodsMenu.put("3", this::testGeneric);
        methodsMenu.put("4", this::setValueUnknownType);
        methodsMenu.put("5", this::invokeMethodsDifParam);
        methodsMenu.put("6", this::invokeMyMethod);

    }

    private void printAnnotatedFields() {
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

    private void testUnknownObj() {
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
            e.printStackTrace();
        }
    }

    private void testGeneric() {
        MyGenericClass<MyClass> obj = new MyGenericClass<>(MyClass.class);
    }

    private void setValueUnknownType() {
    }

    private void invokeMethodsDifParam() {
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
            e.printStackTrace();
        }
    }

    private void invokeMyMethod() {
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
            e.printStackTrace();
        }
    }

    private void outputMenu() {
        System.out.println("\n==================== MENU ====================");
        for (String str : menu.values()) {
            System.out.println(str);
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("----------------------------------------------");
            System.out.println("Enter the menu point:");
            keyMenu = input.nextLine().toUpperCase();
            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }
}

