package com.PizzaOrder;

public class Main {
    public static void main (String[] args) {
        int c=0;
        for(int i=0;i<10;i++) {
            for (int j = 0; j < i; j++){
                System.out.println(++c);
            }
        }
    }
}
