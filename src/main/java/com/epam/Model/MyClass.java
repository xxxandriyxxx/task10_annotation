package com.epam.Model;

import com.epam.annotation.MyAnnotation;


public class MyClass {
    @MyAnnotation
    private String name;
    @MyAnnotation(name = "HelloName")
    private int intValue;
    private boolean booleanValue;

    private void getInfo() {
        System.out.println("Hello from getInfo()!!!");
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBoolenValue() {
        return booleanValue;
    }

    public void setBoolenValue(boolean boolenValue) {
        this.booleanValue = boolenValue;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                ", intValue=" + intValue +
                ", booleanValue=" + booleanValue +
                '}';
    }


    public String[] myMethod(String str, String... args) {
        String[] stringArray = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArray[i] = str + " --> " + args[i] + " --> " + i;
        }
        return stringArray;
    }


}
