package com.company;

import java.util.*;
import java.sql.*;

public class register {
    Scanner scan = new Scanner(System.in);
    protected String username, name, last_name, email, password, passwordcheck, truepass;
    int a = 1;


    public void getForm(){
        try {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("REGISTER");
            System.out.println(" ");

            //Create a new connection with the database
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306","phpmyadmin","erolASDF2001");

            //we will be putting new data into the table named user from the users database
            String sql = "insert into users.user (username, name, surname, password, email)" + "values (?, ?, ?, ?, ?)";
            PreparedStatement ps = myCon.prepareStatement(sql);
            System.out.print("Enter your name: ");
            name = scan.next();
            System.out.print("Enter your last name: ");
            last_name = scan.next();
            System.out.print("Enter your username: ");
            username = scan.next();
            System.out.print("Enter your email address: ");
            email = scan.next();

            //we will check if the both passwords are the same in order to prevent future accidents while writing the password
            //This will run until the user enters the correct password 2 times in a row
            while (a == 1){
                System.out.print("Enter your password: ");
                password = scan.next();
                System.out.print("Confirm your password: ");
                passwordcheck = scan.next();

                boolean passCorrect = password.equals(passwordcheck); //if both passwords are the same then this boolean will be true


                //only if the boolean returns true will execute and complete the registration process
                if (passCorrect == true){

                    ps.setString (1, username);
                    ps.setString(2, name);
                    ps.setString(3, last_name);
                    ps.setString(4, password);
                    ps.setString(5, email);
                    ps.executeUpdate();

                    a = 2; //we set the variable to another value than the while loop in order to finish the loop
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("Thank you for registering");

                    //ask the user to continue
                    System.out.println("Press 1 to continue to the Login page: ");
                    int logincheck = scan.nextInt();

                    if (logincheck == 1){
                        login li = new login();
                        li.getLogin();
                    }
                } else {
                    System.out.println("Try again. Your password are not matching");
                }
            }
        } catch (SQLException e) {

            //if the username entered by the user is already taken, we will run the registration process again
            System.out.println("That username is already taken, please try again ");
            getForm();
        }

    }
}
