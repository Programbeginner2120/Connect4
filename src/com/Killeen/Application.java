package com.Killeen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Application {

    static String asciiArt = "\n" +
            " _______  _______  _        _        _______  _______ _________      ___   \n" +
            "(  ____ \\(  ___  )( (    /|( (    /|(  ____ \\(  ____ \\\\__   __/     /   )  \n" +
            "| (    \\/| (   ) ||  \\  ( ||  \\  ( || (    \\/| (    \\/   ) (       / /) |  \n" +
            "| |      | |   | ||   \\ | ||   \\ | || (__    | |         | |      / (_) (_ \n" +
            "| |      | |   | || (\\ \\) || (\\ \\) ||  __)   | |         | |     (____   _)\n" +
            "| |      | |   | || | \\   || | \\   || (      | |         | |          ) (  \n" +
            "| (____/\\| (___) || )  \\  || )  \\  || (____/\\| (____/\\   | |          | |  \n" +
            "(_______/(_______)|/    )_)|/    )_)(_______/(_______/   )_(          (_)  \n" +
            "                                                                           \n";

    public static GameBoard createBoard(BufferedReader buffRead){
        int numRows = 0;
        int numColumns = 0;
        boolean invalidInput = true;
        while (invalidInput){
            try{
                System.out.println("Please enter number of rows:");
                numRows = Integer.parseInt(buffRead.readLine());
                System.out.println("Please enter number of columns:");
                numColumns = Integer.parseInt(buffRead.readLine());
                invalidInput = false;
            }
            catch (NumberFormatException ex){
                System.out.println("Invalid input given, please try again."); //TODO: Make multiple exception catches
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }

        GameBoard gb = new GameBoard(numRows, numColumns);
        return gb;
    }

    public static void connect4Turn(GameBoard gb, BufferedReader buffRead){
        final char userSymbol = 'X';
        final char computerSymbol = 'O';
        boolean userWins = false;
        boolean computerWins = false;
        while (!(userWins || computerWins)){
                userWins = userTurn(userSymbol, gb, buffRead);
                if (userWins)
                    break;
                computerWins = computerTurn(computerSymbol, gb);
            }
    }

    public static boolean userTurn(char uSymbol, GameBoard gb, BufferedReader buffRead){
        System.out.println("User turn:");
        boolean invalidChoice = true;
        while (invalidChoice){
            int userColumn;
            try{
                System.out.println("Please choose the column you want to place your symbol:");
                userColumn = Integer.parseInt(buffRead.readLine());
                gb.placeSymbol(userColumn, uSymbol);
                invalidChoice = false;
            }
            catch (ImproperPlacementException ex){
                System.out.println(ex.getMessage());
            }
            catch (NumberFormatException ex){
                System.out.println("Invalid input given, please try again.");
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(gb.toString());
        if (gb.checkDiagonalWin(uSymbol) || gb.checkHorizontalWin(uSymbol) ||
                gb.checkVerticalWin(uSymbol)){
            System.out.println("User wins!");
            return true;
        }
        return false;
    }

    public static boolean computerTurn(char cSymbol, GameBoard gb){
        System.out.println("Computer turn:");
        Random rb = new Random();
        boolean invalidChoice = true;
        while (invalidChoice){
            try{
                int randomNumber;
                do{
                    randomNumber = rb.nextInt(gb.getColumns() + 1);
                }
                while (randomNumber == 0);
                gb.placeSymbol(randomNumber, cSymbol);
                invalidChoice = false;
            }
            catch (ImproperPlacementException ex){
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(gb.toString());
        if (gb.checkDiagonalWin(cSymbol) || gb.checkHorizontalWin(cSymbol) ||
                gb.checkVerticalWin(cSymbol)){
            System.out.println("Computer wins!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(asciiArt);

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            GameBoard gameBoard = Application.createBoard(br);
            System.out.println(gameBoard.toString());

            Application.connect4Turn(gameBoard, br);

            br.close();
        }

        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}