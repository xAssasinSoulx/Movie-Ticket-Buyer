package com.company;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.util.*;

public class payingpage{

    Scanner scan = new Scanner(System.in);
    protected String name_card, lastname_card, date, card_number, ccv;
    int a = 1;
    Statement st;
    ResultSet rs;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public void payingpage(String username, int amount, int viewers){

        try{

            //create a connection with the database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306","phpmyadmin","erolASDF2001");

            //select the elements desired from the table named creditcard under the database named users
            String no_card = "select * from users.creditcard where username='"+ username +"'"; //we select the elements related to the username
            st = myCon.createStatement();
            rs = st.executeQuery(no_card);

            //if the program can't find any information related to the username then this part will run
            if (rs.next() == false){
                System.out.println("You don't have any cards in your account, please add a new card." +
                        "" +
                        "Press 1 to continue with the process");
                        int add_card = scan.nextInt();
                        if (add_card == 1){

                            //we create a new statement in oder to update the database with the new information
                            String sql = "insert into users.creditcard (username, name, last_name, card_number , date, ccv)" + "values (?, ?, ?, ?, ?, ?)";
                            PreparedStatement ps = myCon.prepareStatement(sql);
                            System.out.println("Let's start");
                            System.out.println("Enter the Name on the card please: "); //we get the name on the credit card
                            name_card = scan.next();
                            System.out.println("Enter the Last Name on the card please: "); //we take the last name on the credit card
                            lastname_card = scan.next();
                            System.out.println("Enter the card number: "); //we take the cards number
                            card_number = scan.next();
                            System.out.println("Enter the expiry date of the card (MM/YY): "); //we take the expiry date
                            date = scan.next();
                            System.out.println("Enter the ccv of the card: "); //and finally the ccv code of the card
                            ccv = scan.next();


                            //we put all the information provided into the database
                            ps.setString(1, username);
                            ps.setString(2, name_card.toUpperCase());
                            ps.setString(3, lastname_card.toUpperCase());
                            ps.setString(4, card_number);
                            ps.setString(5, date);
                            ps.setString(6, ccv);
                            ps.executeUpdate();

                            System.out.println(" ");
                            System.out.println("You have added your new card to the system. Great");
                            System.out.println("Now you can continue to check out");

                            //we redirect the user to the next step
                            already_card_user(username, amount, viewers);


                        } else{

                            //if the user enters another value than "1" the program kills it self
                            System.exit(0);
                        }
            } else {

                //if the program finds information related to the username, then it means that the user has already registered his card details
                System.out.println(" ");
                System.out.println("You already have a card registered in the system. This is your card registered displayed in the following order: ");
                System.out.println("(Name, Last Name, Card Number, Expiry Date, CCV)");
                already_card_user(username, amount, viewers);

            }
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    public void already_card_user(String username, int amount, int viewers){
        ticket tk = new ticket();
        try{

            //create a connection with the database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306","phpmyadmin","erolASDF2001");

            //we will print the elements related to the username again
            String get_card = "select * from users.creditcard where username='"+ username +"'";
            rs = st.executeQuery(get_card);
            ResultSetMetaData rsmd = rs.getMetaData();

            //we get how many column we have in the database
            int columnNumber = rsmd.getColumnCount();


            //print each column and the values stored in it
            while (rs.next()){
                for (int c = 2 ; c <= columnNumber; c++)
                    System.out.print(rs.getString(c) + " ");
                System.out.println();
            }
            System.out.println(" ");

            //we print the amount that the user has to pay
            System.out.println("The total amount is: $" + amount);

            //we confirm the transaction
            System.out.println("Please confirm the transaction by pressing 1");
            int confirm_payment = scan.nextInt();
            if (confirm_payment == 1){
                System.out.println("Thank you for the payment " + username.toUpperCase());
                System.out.println(" ");

                //we print the ticket to the user
                tk.getTicket(username, viewers, amount);
            }else{
                System.exit(0);
            }


        } catch (Exception bb ){
            bb.printStackTrace();
        }
    }
}

