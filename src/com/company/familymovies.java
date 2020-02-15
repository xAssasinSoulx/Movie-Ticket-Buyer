package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class familymovies extends actualpage {
    public void get_movies(String username){
        Connection con;
        ResultSet rs;
        Statement st;
        Scanner scan = new Scanner(System.in);
        actualpage ap = new actualpage();
        payingpage pp = new payingpage();
        try {

            // we start a connection with the database, entering the url username and the password that is associated to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "phpmyadmin", "erolASDF2001");

            //Using the DBTable class we print the "Family Movies" data base as a whole table
            net.efabrika.util.DBTablePrinter DBTablePrinter = null;
            DBTablePrinter.printTable(con, "users.familymovies");

            //Ask the user which movie wants to see
            System.out.println("Select the movie you would like to watch. (Press the indicated Id)");
            int select_movie = scan.nextInt();

            //Create a switch in order to give specific functions to every single input without using if's
            switch (select_movie){
                case 1:
                    System.out.println(" ");
                    System.out.println("Great choice " + username);
                    System.out.println(" ");

                    //Ask the user the number of person in order to calculate the total amount that the user needs to pay
                    System.out.println("We love that movie. How many persons will watch the movie?");
                    int number_users = scan.nextInt();
                    System.out.println(" ");
                    System.out.println("You are booking for " + number_users + " persons" );

                    //Confirm the selection
                    System.out.println("Please enter 1 to confirm your choice and continue to the payment page. If you want to chose another movie press 2");
                    int user_ready = scan.nextInt();
                    if (user_ready == 1){

                        //calculate the amount due depending on the number of persons
                        int amount = number_users * 10;

                        //create a new object from the paying page with the variables given in order to use them in that class later on
                        pp.payingpage(username, amount, number_users);
                    } else{

                        //if the user does not want to see that movie anymore, he'll be redirected to the main page
                        ap.actualPage(username);
                    }
                    break;
                case 2:
                    System.out.println(" ");
                    System.out.println("Great choice " + username);
                    System.out.println(" ");
                    System.out.println("We love that movie. How many persons will watch the movie?");
                    int number_users1 = scan.nextInt();
                    System.out.println(" ");
                    System.out.println("You are booking for " + number_users1 + " persons" );
                    System.out.println("Please enter 1 to confirm your choice and continue to the payment page. If you want to chose another movie press 2");
                    int user_ready2 = scan.nextInt();
                    if (user_ready2 == 1){
                        int amount1 = number_users1 * 15;
                        pp.payingpage(username, amount1, number_users1);
                    } else{
                        ap.actualPage(username);
                    }
                    break;
                case 3:
                    System.out.println(" ");
                    System.out.println("Great choice " + username);
                    System.out.println(" ");
                    System.out.println("We love that movie. How many persons will watch the movie?");
                    int number_users2 = scan.nextInt();
                    System.out.println(" ");
                    System.out.println("You are booking for " + number_users2 + " persons" );
                    System.out.println("Please enter 1 to confirm your choice and continue to the payment page. If you want to chose another movie press 2");
                    int user_ready3 = scan.nextInt();
                    if (user_ready3 == 1){
                        int amount2 = number_users2 * 9;
                        pp.payingpage(username, amount2, number_users2);
                    } else{
                        ap.actualPage(username);
                    }
                    break;
                case 4:
                    System.out.println(" ");
                    System.out.println("Great choice " + username);
                    System.out.println(" ");
                    System.out.println("We love that movie. How many persons will watch the movie?");
                    int number_users3 = scan.nextInt();
                    System.out.println(" ");
                    System.out.println("You are booking for " + number_users3 + " persons" );
                    System.out.println("Please enter 1 to confirm your choice and continue to the payment page. If you want to chose another movie press 2");
                    int user_ready4 = scan.nextInt();
                    if (user_ready4 == 1){
                        int amount3 = number_users3 * 8;
                        pp.payingpage(username, amount3, number_users3);
                    } else{
                        ap.actualPage(username);
                    }
                    break;
            }
        } catch (Exception a){
            a.getStackTrace();
        }
    }
}
