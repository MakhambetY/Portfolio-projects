package com.PizzaOrder;

import java.util.*;

public class OrderManagement {
    private static String key = "SuperDiffucultParoL123456789";
    private static ArrayList<String> data = new ArrayList<>();

    public OrderManagement(){
    }

    static void getData(){
        System.out.println("List of all orders till now:");
        for(String s: data) System.out.println(s);
    }

    static boolean checkKey(String input){
        return input.equals(key);
    }

    static void setKey(String newKey){
        key = newKey;
    }

    static void add(String[] temp){
        for (String s : temp) {
            if (s != null) data.add(s);
        }
    }

    static void getTotalPrice() {

        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        int totalCost = 0;
        for (int i = 0; i < info.length; i++)
            totalCost += Integer.parseInt(info[i][3]);
        System.out.printf("Total Price:\n%d\n", totalCost);
    }

    static void searchByID(String idOrDate) {
        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        boolean exist = false;
        System.out.println("Search by ID: " + idOrDate);
        for (int i = 0; i < info.length; i++) {
            if (Integer.parseInt(info[i][0]) == Integer.parseInt(idOrDate)) {
                for (int j = 0; j < 10; j++)
                    System.out.print(info[i][j] + " ");
                System.out.println();
                exist = true;
            }
        }
        if (!exist) System.out.println("No result");
    }

    static void searchByDate(String idOrDate) {
        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        boolean exist = false;
        System.out.println("Search by date: " + idOrDate);
        for (int i = 0; i < info.length; i++) {
            if (info[i][1].equals(idOrDate)) {
                for (int j = 0; j < 10; j++)
                    System.out.print(info[i][j] + " ");
                System.out.println();
                exist = true;
            }
        }
        if (!exist) System.out.println("No result");
    }

    static void sortByID() {
        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        boolean nextPass = true;
        for (int k = 1; k < info.length && nextPass; k++) {
            nextPass = false;
            for (int i = 0; i < info.length - k; i++) {
                if (Integer.parseInt(info[i][0]) > Integer.parseInt(info[i + 1][0])) {
                    for (int j = 0; j < 10; j++) {
                        String temp = info[i][j];
                        info[i][j] = info[i + 1][j];
                        info[i + 1][j] = temp;
                    }
                    nextPass = true;
                }
            }
        }
        System.out.println("Sort by ID:");
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < 10; j++) System.out.print(info[i][j] + " ");
            System.out.println();
        }
    }

    static void sortByDateAndTime() {
        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        boolean nextPass = true;
        for (int k = 1; k < info.length && nextPass; k++) {
            nextPass = false;
            for (int i = 0; i < info.length - k; i++) {
                if (
                        Integer.parseInt(info[i][2].substring(3)) > Integer.parseInt(info[i + 1][2].substring(3))
                ) {
                    for (int j = 0; j < 10; j++) {
                        String temp = info[i][j];
                        info[i][j] = info[i + 1][j];
                        info[i + 1][j] = temp;
                    }
                    nextPass = true;
                }
            }
        }
        nextPass = true;
        for (int k = 1; k < info.length && nextPass; k++) {
            nextPass = false;
            for (int i = 0; i < info.length - k; i++) {
                if (
                        Integer.parseInt(info[i][2].substring(0, 2)) > Integer.parseInt(info[i + 1][2].substring(0, 2))
                ) {
                    for (int j = 0; j < 10; j++) {
                        String temp = info[i][j];
                        info[i][j] = info[i + 1][j];
                        info[i + 1][j] = temp;
                    }
                    nextPass = true;
                }
            }
        }
        nextPass = true;
        for (int k = 1; k < info.length && nextPass; k++) {
            nextPass = false;
            for (int i = 0; i < info.length - k; i++) {
                if (
                        Integer.parseInt(info[i][1].substring(0, 2)) > Integer.parseInt(info[i + 1][1].substring(0, 2))
                ) {
                    for (int j = 0; j < 10; j++) {
                        String temp = info[i][j];
                        info[i][j] = info[i + 1][j];
                        info[i + 1][j] = temp;
                    }
                    nextPass = true;
                }
            }
        }
        nextPass = true;
        for (int k = 1; k < info.length && nextPass; k++) {
            nextPass = false;
            for (int i = 0; i < info.length - k; i++) {
                if (
                        Integer.parseInt(info[i][1].substring(3, 5)) > Integer.parseInt(info[i + 1][1].substring(3, 5))
                ) {
                    for (int j = 0; j < 10; j++) {
                        String temp = info[i][j];
                        info[i][j] = info[i + 1][j];
                        info[i + 1][j] = temp;
                    }
                    nextPass = true;
                }
            }
        }
        nextPass = true;
        for (int k = 1; k < info.length && nextPass; k++) {
            nextPass = false;
            for (int i = 0; i < info.length - k; i++) {
                if (
                        Integer.parseInt(info[i][1].substring(6)) > Integer.parseInt(info[i + 1][1].substring(6))
                ) {
                    for (int j = 0; j < 10; j++) {
                        String temp = info[i][j];
                        info[i][j] = info[i + 1][j];
                        info[i + 1][j] = temp;
                    }
                    nextPass = true;
                }
            }
        }
        System.out.println("Sort by date and time:");
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < 10; j++) System.out.print(info[i][j] + " ");
            System.out.println();
        }
    }

    static void mostPopularSize() {
        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        int[] count = new int[41];
        for (int i = 0; i < info.length; i++) {
            count[Integer.parseInt(info[i][5])]++;
        }
        int max = 0;
        for (int i : count) {
            if (i > max) max = i;
        }
        System.out.println("Most popular size(s):");
        for (int i = 0; i < count.length; i++) {
            if (count[i] == max) System.out.println(i);
        }
    }

    static void mostPopularPizzaType() {
        String[][] info = new String[data.size()][10];
        for(int i=0;i<data.size();i++)
            info[i] = data.get(i).split(" ");

        String[] combo = new String[info.length];
        for (int i = 0; i < info.length; i++) {
            for (int j = 6; j < 9; j++)
                combo[i] += info[i][j] + " ";
            combo[i] += info[i][9];
        }
        ArrayList<String> newCombo = new ArrayList<>();
        newCombo.add(combo[0]);
        int[] counts = new int[combo.length];
        for(int i=0;i< combo.length;i++){
            boolean exist = false;
            for(int j=0;j< newCombo.size();j++){
                if(combo[i].equals(newCombo.get(j))){
                    counts[j]++;
                    exist = true;
                }
            }
            if(!exist){
                newCombo.add(combo[i]);
                counts[newCombo.size()-1]++;
            }
        }
        int maxCount = 0;
        for (int i : counts) maxCount = Math.max(maxCount, i);
        System.out.println("Most popular pizza type(s):");
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == maxCount) {
                String maxCombo = newCombo.get(i);
                String[] tops = maxCombo.split(" ");
                String result = "";
                for (int j = 0; j < 3; j++) {
                    if (tops[j].contains("Yes")) {
                        switch (j) {
                            case 0 -> result += "Meat+";
                            case 1 -> result += "Sausage+";
                            case 2 -> result += "Vegetables+";
                        }
                    }
                }
                if (tops[3].contains("Yes")) result += "Mushroom";
                else result = result.substring(0, result.length() - 1);
                System.out.println(result);
            }
        }
    }
}
