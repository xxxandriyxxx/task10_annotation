package com.epam.view;

import com.epam.model.BusinessLogic;
import com.epam.model.Model;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {
    private Model model;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public View() {
        model = new BusinessLogic();
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
        model.printAnnotatedFields();
    }

    private void testUnknownObj() {
        model.testUnknownObj();
    }

    private void testGeneric() {
        model.testGeneric();
    }

    private void setValueUnknownType() {
        model.setValueUnknownType();
    }

    private void invokeMethodsDifParam() {
        model.invokeMethodsDifParam();
    }

    private void invokeMyMethod() {
        model.invokeMyMethod();
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

