package com.company;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.*;

public class ticket {
    Statement st;
    ResultSet rs;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //will be used to create the date on the ticket
    LocalDateTime now = LocalDateTime.now();
    Scanner scan = new Scanner(System.in);
    login lg = new login();

    public void getTicket(String username, int viewers, int amount){ // we tale the variables provided by the previous class
        try{
            String uusername = username;
            //create a new connection with the database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306","phpmyadmin","erolASDF2001");

            //select every element from the table named creditcard related to the username provided
            String getname = "select * from users.creditcard where username='"+ uusername +"' ";
            st = myCon.createStatement();
            rs = st.executeQuery(getname);
            if (rs.next()){ //if the Result Set can continue, this statement will execute

                String name = rs.getString(2); //we print the 2nd element which is the name that the user provided while registering the credit card
                String lastname = rs.getString(3); //print the 3rd element which is the last name that the user provided while registering the credit card
                String arr[] = new String[] {name, lastname, String.valueOf(viewers), String.valueOf(amount)}; //we create an array with the variables in order to grab the element needed later on
                System.out.println(" ");
                System.out.println("|************************************************|");
                System.out.println("| Payment recieved for: " + arr[0] + " " + arr[1] + "          |");
                System.out.println("| Number of persons: " + arr[2] + "                          |");
                System.out.println("| Payment time: "+dtf.format(now) + "              |");
                System.out.println("| Thank you for choosing us. Enjoy your movie    |");
                System.out.println("| Total amount: $" + arr[3] + "                             |");
                System.out.println("|************************************************|");
                System.out.println("           ||||| | |||| | ||| | |||||| |");
                System.out.println("           23456 1 4324 6 876 9 002341 9");

                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("Press 1 to log in again or press 2 to exit");
                int again = scan.nextInt();

                if (again == 1){
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println(" ");
                    lg.getLogin();
                } else
                    System.exit(0); //the program kills if the user wants
            }
        } catch (Exception aa){ //catch exceptions
            aa.getStackTrace();
        }
    }
}
