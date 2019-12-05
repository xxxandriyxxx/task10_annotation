package com.epam.Model;

import java.lang.reflect.Field;

public class MyGenericClass<T> {

    private T object;

    private final Class<T> clazz;

    public MyGenericClass(Class<T> clazz) {
        this.clazz = clazz;
        try {
//            object = clazz.newInstance();
            object = clazz.getConstructor().newInstance();
            System.out.println("Declared fields of class " + clazz.getSimpleName() + " are:");
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                System.out.println("- " + f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
