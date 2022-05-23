package com.PizzaOrder;

import java.util.*;

public class PizzaOrder {

    public static void pizzaOrder (String[] args) {
        Scanner input = new Scanner(System.in);
        Order order;

        while(true){
            System.out.println("====================================");
            System.out.println("Welcome to \"Eat&Chat\" Pizza Order!");
            System.out.println("====================================");
            System.out.println("Choose an action:");
            System.out.println("Click 1 if you want to order");
            System.out.println("Click 2 if you want to see the most popular size");
            System.out.println("Click 3 if you want to see the most popular pizza type");
            System.out.println("Click 4 to open administration's interface");
            System.out.println("Click 5 to exit");
            byte act = input.nextByte();
            switch (act){
                case 1 -> {
                    order = new Order();
                    OrderManagement.add(order.getOrder());
                }
                case 2 -> OrderManagement.mostPopularSize();
                case 3 -> OrderManagement.mostPopularPizzaType();
                case 4 -> {
                    System.out.println("Note: you have one try");
                    System.out.println("Access key:");
                    String temp = input.next();
                    if(OrderManagement.checkKey(temp)){
                        System.out.println("Administration's actions:");
                        System.out.println("Click 1 to get to a total cost of all orders");
                        System.out.println("Click 2 to sort orders by ID");
                        System.out.println("Click 3 to sort orders by date and time");
                        System.out.println("Click 4 to search by ID");
                        System.out.println("Click 5 to search by Date");
                        System.out.println("Click 6 to exit");
                        System.out.println("Click 7 to close program");
                        System.out.println("Click 8 to change an administration's key");
                        System.out.println("Click 9 to get list of all orders");
                        byte actAd;
                        do{
                            actAd = input.nextByte();
                            switch (actAd){
                                case 1 -> OrderManagement.getTotalPrice();
                                case 2 -> OrderManagement.sortByID();
                                case 3 -> OrderManagement.sortByDateAndTime();
                                case 4 -> {
                                    System.out.print("Type an ID to find: ");
                                    String ID = input.next();
                                    OrderManagement.searchByID(ID);
                                }
                                case 5 -> {
                                    System.out.println("Type a Date to find in format DD.MM.YYYY: ");
                                    String date = input.next();
                                    OrderManagement.searchByDate(date);
                                }
                                case 6 -> System.out.println("Back to main menu");
                                case 7 -> System.exit(0);
                                case 8 -> {
                                    System.out.print("Enter a new key: ");
                                    String key = input.next();
                                    OrderManagement.setKey(key);
                                }
                                case 9 -> OrderManagement.getData();
                                default -> System.out.println("Can't find the action, try again.");
                            }
                        }while (actAd!=6);
                    } else System.out.println("The key is incorrect, back to main menu.");
                }
                case 5 -> System.out.println("Good Bye!");
                default -> System.out.println("Can't find the action, try again.");

            }
        }
    }
}

