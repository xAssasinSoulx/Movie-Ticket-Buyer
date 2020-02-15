package com.company;

import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class passwordreset extends login{
    Connection con;
    Statement st;
    ResultSet rs;
    Scanner scan = new Scanner(System.in);
    login login = new login();


    //This helps the user recover and reset his old password
    public void passwordreset() {
        try {

            //create a new connection with the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "phpmyadmin", "erolASDF2001");
            st = con.createStatement();

            System.out.println("We are sorry to see that you forgot your password but don't worry, we are here to help you");
            System.out.println("First let's verify if you are the real user that wants to reset his password");
            System.out.println("Enter your email address: ");
            String email_passreset = scan.next();
            System.out.println("And finally your username: ");
            String username_passreset = scan.next();

            //the input by the user will be compared with the information on the database
            //in this case will start comparing the email
            String sql_email = "select * from users.user where email='" + email_passreset + "' and username='"+ username_passreset +"'";
            rs = st.executeQuery(sql_email);

            try{

                //if all the information provided by the user is the same as the information on the database, this will execute and the user
                //will be able reset his password
                if (rs.next()){

                    //we now ask for more personal data about the user in order to protect the user
                    System.out.println("Please enter your name: ");
                    String name_passreset = scan.next();
                    System.out.println("Now you last name: ");
                    String last_name_passreset = scan.next();

                    //we determine the username and go to the row of the provided username
                    String sql = "select * from users.user where name='" + name_passreset + "' and surname='" + last_name_passreset + "' ";
                    rs = st.executeQuery(sql);
                    try{

                        //if the name and username coincidences with the database's information the user now will have the total access to
                        //reset his password
                        if (rs.next()) {
                            System.out.println("All the information matches the data base");

                            //we check both password to prevent future accidents related to the password
                            System.out.println("Enter the new password please: ");
                            String new_pass = scan.next();
                            System.out.println("Confirm your new password: ");
                            String new_passconfirm = scan.next();

                            //if both the passwords equal the user can update his password
                            if (new_pass.equals(new_passconfirm)) {

                                //we update the users variables with the information provided
                                String update_db = "update users.user set password='" + new_pass + "' where username='" + username_passreset + "' ";
                                st.executeUpdate(update_db);
                                System.out.println("You have now changed your password. Press 1 to go to the login page");
                                int passreset_login = scan.nextInt();
                                if (passreset_login == 1){
                                    login.getLogin();
                                }

                            } else {

                                //if the name and the last name doesn't coincidences with the information of the database
                                //then the program will ask the user to try again or create a new account
                                System.out.println("Sorry but the information that you have just provided are not correct");
                                System.out.println("Press 1 to go to the Register section or press 2 to try again");
                                int pass_false = scan.nextInt();
                                if (pass_false == 1) {
                                    login.getLogin();
                                } else
                                    passwordreset();
                            }
                        } else {

                            //if the name and the last name doesn't coincidences with the information of the database
                            //then the program will ask the user to try again or create a new account
                            System.out.println("Sorry but the information that you have just provided are not correct");
                            System.out.println("Press 1 to go to the Register section or press 2 to try again");
                            int pass_false = scan.nextInt();
                            if (pass_false == 1) {
                                login.getLogin();
                            } else
                                passwordreset();
                        }
                    } catch(Exception ee){
                        ee.printStackTrace();
                    }
                } else{

                    //if the information provided does not matches with the information of the database, the program
                    //will ask the user to try again
                    System.out.println("Your email does not match");
                    System.out.println("Press 1 to try again or 2 to register");
                    int try_again = scan.nextInt();
                    if (try_again == 1){
                        passwordreset();
                    } else {
                        register register = new register();
                        register.getForm();
                    }
                }
            }catch (SQLException a){
                a.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
