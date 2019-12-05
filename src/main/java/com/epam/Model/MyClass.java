package com.epam.Model;

import com.epam.annotation.MyAnnotation;


public class MyClass {
    @MyAnnotation
    private String name;
    @MyAnnotation(name = "HelloName")
    private int intValue;
    private boolean booleanValue;

    private void getInfo(){
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
}
