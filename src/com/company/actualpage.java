package com.company;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public class actualpage extends login {
    Scanner scan = new Scanner(System.in);

    public void actualPage(@NotNull String username){

            System.out.println("Hello "+ username.toUpperCase() + ", good to see you again.");
            System.out.println(" ");
            System.out.println("What would you like to book for today?");
            System.out.println("1-Top Movies" +
                    " " + " " +
                    "2-New Releases" +
                    " " + " " +
                    "3-For adults" +
                    " " + " " +
                    "4-Family movies");
            int movie_selection = scan.nextInt();

            switch (movie_selection){
                case 1:
                    topmovies tm = new topmovies();
                    System.out.println(" ");
                    System.out.println("Here are the movies that we have in our database right now: ");
                    tm.get_movies(username);
                    break;

                case 2:
                    newreleases nr = new newreleases();
                    System.out.println(" ");
                    System.out.println("Here are the movies that we have in our database right now: ");
                    nr.get_movies(username);
                    break;

                case 3:
                    foradults fa = new foradults();
                    System.out.println(" ");
                    System.out.println("Here are the movies that we have in our database right now: ");
                    fa.get_movies(username);
                    break;

                case 4:
                    familymovies fm = new familymovies();
                    System.out.println(" ");
                    System.out.println("Here are the movies that we have in our database right now: ");
                    fm.get_movies(username);
                    break;
            }
    }
}
