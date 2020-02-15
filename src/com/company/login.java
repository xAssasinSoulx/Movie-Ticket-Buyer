package com.company;
import java.sql.*;


public class login extends register{
    Connection con;
    Statement st;
    ResultSet rs;


    public void getLogin(){

        try {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("LOG IN");
            System.out.println(" ");

            //create a connection with the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","phpmyadmin", "onlytesting123"); //change the url, user and the password according to your database information
            st = con.createStatement();

            //we get the username from the user
            System.out.print("Enter your username: ");
            String username_login = scan.next();

            //we check the database if we the database has that username
            String sql_wrongusername = "select * from users.user where username='" + username_login +"'";
            rs = st.executeQuery(sql_wrongusername);


            //if the porgram can't find the username provided, this will run
            if (rs.next() == false){

                //we consider if it was an error so we ask if the user wants to try again
                System.out.println("You are not registered or you entered wrong your username. (1- Try again | 2- Register)");
                int wrongusername = scan.nextInt();

                //if the user wants to create an account, we redirect him to the register page
                if (wrongusername == 2){
                    getForm();
                } else {
                    //we let him try again
                    getLogin();
                }


              // if we find the username, this will run
            } else {

                //we get the password
                System.out.print("Enter your password: ");
                String password_login = scan.next();

                //we check if the password provided equals to the password on the database
                String sql = "select * from users.user where username='" + username_login + "' and password='" + password_login + "' ";
                rs = st.executeQuery(sql);

                //if the program finds the password and it matches with the input, this will run
                if (rs.next()) {
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("Login Succesfull");
                    System.out.println(" ");

                    //redirect him to the main page
                        actualpage ap = new actualpage();
                        ap.actualPage(username_login);
                } else {

                    //if the password is incorrect, we let the user either try again or reset his password or register
                    System.out.println("The password that you have entered is not correct. Try again or reset your password. (1- Try again | 2- Forgot Password | 3- Register)");
                    int login_restart = scan.nextInt();

                    if (login_restart == 1) {
                        getLogin();
                    }
                    else if (login_restart == 2){
                        passwordreset pr = new passwordreset();
                        pr.passwordreset();
                    } else
                        getForm();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
