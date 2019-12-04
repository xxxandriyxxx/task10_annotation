package com.epam.View;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);



    public View() {
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - ");
        menu.put("2", " 2 - ");
        menu.put("3", " 3 - ");
        menu.put("4", " 4 - ");
        menu.put("5", " 5 - ");
        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::pressButton1);
        methodsMenu.put("2", this::pressButton2);
        methodsMenu.put("3", this::pressButton3);
        methodsMenu.put("4", this::pressButton4);
        methodsMenu.put("5", this::pressButton5);
    }

    private void pressButton1() {

    }

    private void pressButton2() {

    }

    private void pressButton3() {

    }

    private void pressButton4() {

    }

    private void pressButton5() {

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

