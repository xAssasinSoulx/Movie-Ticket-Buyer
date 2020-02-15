/*
Database Table Printer
Copyright (C) 2020  Muhammed Erdem Calikoglu aka xAssasinSoulx
Email: admin@erdemcalikoglu.cf
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
This is one the very first proyects that I worked on with Java
but the first one using SQL and phpMyAdmin. It is really simple, adds and uses the existing users
information gathering from the Data Base created. You can reset the password, add credit card
information and make virtual payments with it. At the end it displays your ticket to the movie.
I incorporated the program of Hami which is https://github.com/htorun/dbtableprinter to be able
to print the movies of the selected genre, which is basically displaying the Data Base to the user.
Don't forget to change the sql connection information while using this program
Also, you can change everything but don't forget to make the changes in your DataBase too
 */

package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        register register = new register();
        login login = new login();


        //ask the user what he wants
        System.out.println("Welcome to the Culminating proyect of Erdem. " +
                "If you are an existing user, press 1. If you want to register press 2");
        int register_login = scan.nextInt();


        switch (register_login){
            case 1:
                //if the user is already an user, we will run the login class
                login.getLogin();
                break;
            case 2:
                //if the user is not an existing user, we will run the register class
                register.getForm();
                break;
        }
    }
}
