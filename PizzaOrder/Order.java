package com.PizzaOrder;

import java.util.*;

public class Order {

    private String[] info = new String[10];
    private boolean discount = false; // flag, true if user is eligible for discount
    public String date = CurrentDate();
    public String time = CurrentTime();
    private String ID = generateCode();

    Order(){
        Scanner keyboard = new Scanner(System.in);	//Create a Scanner object to read input
        char choice;                  		// user's choice
        String input;                 		// user input
        String[] orders = new String[10];	// full info about order(s); NOTE: String default value is null
        int numOfOrders = 0;				// number of pizzas ordered
        System.out.print("Today is: ");
        System.out.println(date);		// prints current date (today)
        System.out.print("> Is it your BIRTHDAY? (10% discount available on presenting ID)  (Y/N):  ");
        input = keyboard.nextLine();

        choice = input.charAt(0);
        if (choice == 'y' || choice == 'Y') discount = true;

        orders[numOfOrders++] = orderPizza(numOfOrders);  // get first order
        previewOrder(orders);  // view order info

        do{
            printMenu();	// print action menu options
            input = keyboard.nextLine();
            choice = input.charAt(0);
            do{
                switch (choice) {
                    case '1' -> confirmOrder(orders);            // complete order
                    case '2' -> {
                        orders[numOfOrders++] = orderPizza(numOfOrders); // save new order
                        previewOrder(orders);                    // view order info
                    }
                    case '3' -> System.exit(0);// exit program
                    default -> System.out.println("Can't find the action, try again.");
                }
            }while (choice != '1'&&choice!='2');
        }while (choice == '2');

    }

    public void printMenu(){
        //prompt user for the next operation
        System.out.println("------------MENU-------------");
        System.out.println("1 - Complete");
        System.out.println("2 - Add another order");
        System.out.println("3 - Exit");
        System.out.print("> Choose one of the above (Enter a number): ");
    }

    public String orderPizza(int num){
        Scanner keyboard = new Scanner(System.in);
        String input;                 	//user input
        char choice;                  	//user's choice
        int size;                   	//size of the pizza
        int cost = 0;          			//cost of the pizza
        int numberOfToppings = 0;     	//number of toppings
        String toppings = "Cheese";  	//list of toppings
        String result = "";				// resultant String object to be returned
        final int toppingCost = 200;	// cost for one topping

        System.out.println("-----------------------------");
        System.out.println("Pizza Size (cm)      Cost");
        System.out.println("       20            1000 T");
        System.out.println("       30            1500 T");
        System.out.println("       40            2000 T");
        System.out.println("What size pizza would you like?");
        System.out.print("> 20, 30, or 40 (enter the number only): ");
        size = keyboard.nextInt();

        do{
            switch (size) {
                case 20 -> cost += 1000;
                case 30 -> cost += 1500;
                case 40 -> cost += 2000;
                default -> System.out.println("Error! Choose a size 20, 30 or 40 only, try again.");
            }
        }while (size!=20&&size!=30&&size!=40);

        keyboard.nextLine();

        System.out.println("-----------------------------");
        System.out.println("All pizzas come with cheese.");
        System.out.println("Additional toppings are 200T each," + " choose from:");
        System.out.println("- Meat, Sausage, Vegetables, Mushroom");

        String desc = ""; //temp description of toppings

        System.out.print("> Do you want Meat?  (Y/N):  ");
        input = keyboard.nextLine();
        choice = input.charAt(0);
        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + " + Meat";
            desc += "Yes ";
        }
        else desc += "No ";
        System.out.print("> Do you want Sausage?  (Y/N):  ");
        input = keyboard.nextLine();
        choice = input.charAt(0);
        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + " + Sausage";
            desc += "Yes ";
        }
        else desc += "No ";
        System.out.print("> Do you want Vegetables?  (Y/N):  ");
        input = keyboard.nextLine();
        choice = input.charAt(0);
        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + " + Vegetables";
            desc += "Yes ";
        }
        else desc += "No ";
        System.out.print("> Do you want Mushroom?  (Y/N):  ");
        input = keyboard.nextLine();
        choice = input.charAt(0);
        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + " + Mushroom";
            desc += "Yes";
        }
        else desc += "No";
        cost += numberOfToppings*toppingCost;
        result += size + "cm pizza, " + toppings;
        result += ":" + cost;

        info[num] = "";
        if(discount) info[num] += ID+ " "+date+" "+time+" "+cost+" Yes "+size+" "+desc;
        else info[num] += ID+ " "+date+" "+time+" "+cost+" No "+size+" "+desc;

        return result;
    }

    public void previewOrder(String[] orders){
        System.out.println("-----------------------------");
        System.out.println("Your order: ");

        for (int i=0;i<10;i++){
            if (orders[i]!=null) System.out.printf("%d) %s\n",i+1,orders[i]);
        }

        // print total cost
        System.out.println("Total: " + getTotalCost(orders) + " T");
    }

    public int getTotalCost(String[] orders){
        //++ returns total cost
        int total = 0;
        for (int i=0;i<10;i++){
            if (orders[i]!=null) total += Integer.parseInt(orders[i].substring(orders[i].lastIndexOf(":")+1));
        }
        return total;
    }

    public void confirmOrder(String[] orders){
        final int DISCOUNT_AMOUNT = 10;	// discount amount in percentage

        //display order confirmation
        System.out.println("#############################################");
        previewOrder(orders);

        // calculate total cost
        int cost = getTotalCost(orders);

        if (discount){
            cost = cost*(100-DISCOUNT_AMOUNT)/100;
            System.out.println("-----------------------------");
            System.out.println("TOTAL with DISCOUNT (on presenting ID only!):");
            System.out.println(cost + " T");
        }

        System.out.println("-----------------------------");
        System.out.println("Your order will be ready for pickup in 30 minutes.");

        System.out.print("Date: ");
        System.out.println(date);			// prints current date
        System.out.print("Time: ");
        System.out.println(time);				// prints current time

        System.out.println("Order ID: " + ID);	// generates random ID
    }

    private String CurrentDate(){
        // print current date
        return java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy").format(java.time.LocalDate.now());
    }

    private String CurrentTime(){
        return java.time.format.DateTimeFormatter.ofPattern("HH:mm").format(java.time.LocalTime.now());
    }

    private String generateCode(){
        //++ return 4-digit random code
        return String.format("%04d",(int)(Math.random()*1000));
    }

    public String[] getOrder(){
        return info;
    }
}
