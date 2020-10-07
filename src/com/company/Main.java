package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private int playerScore = 0;
    private int playerShips = 0;
    private int computerScore = 0;
    private int computerShips = 0;
    private int playerShots;
    private int computerShots;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Integer> controlShipPosition = new ArrayList<>();

    private String[][] grid = {
            { "   ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 " },
            { " 1 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]" },
            { " 2 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]" },
            { " 3 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]" },
            { " 4 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]" },
            { " 5 ", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]" }
    };

    public static void main(String[] args) {
        new Main().play();
    }
    void play(){
        System.out.println("new code");
        printBoard();
        deployPlayerShip();
        deployComputerShips();

        do{
            battle();
        }
        while(playerShips > 0 || computerShips > 0);
        gameOver();
    }
    void battle(){
        printBoard();

        playerShots = 3;
        playerTurn();

        computerShots = 3;
        computerTurn();

        System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
        System.out.println();
    }
    void playerTurn(){
        do{
            System.out.println("Your turn, you have " + playerShots+ " shots");
            System.out.print("x - coordinate: ");
            int shotx = sc.nextInt();
            System.out.print("y - coordinate: ");
            int shoty = sc.nextInt();
            int gotX = 0;
            int gotY = 0;

            for(int i = 0; i < controlShipPosition.size(); i+=2){
                if(shotx == controlShipPosition.get(i)){
                    //System.out.println(controlShipPosition);
                    gotX = controlShipPosition.get(i);
                    if(shoty == controlShipPosition.get(i+1)){
                        gotY = controlShipPosition.get(i+1);
                    }
                }
            }
            if(controlShipPosition.contains(gotX) && controlShipPosition.contains(gotY)){
                computerShips--;
                System.out.println("Boom! " + computerShips + " ships left");
                grid[shotx][shoty] = "[!]";
                playerScore += 2;
                printBoard();
                playerShots--;
                if (computerShips <1 || playerShips <1){
                    gameOver();
                }
            }
            else{
                System.out.println("Miss");
                grid[shotx][shoty] = "[@]";
                printBoard();
                playerShots--;
            }
        }
       while(playerShots > 0);
    }
    void computerTurn(){
        if (computerShips < 1 || playerShips < 1){
            gameOver();
        }
        do{
            System.out.println("Computers turn " + computerShots + " shots");
            int shotx = (int)(Math.random()*5)+1;
            int shoty = (int)(Math.random()*5)+1;

            if(grid[shotx][shoty] == "[X]"){
                playerShips--;
                System.out.println("Boom" + playerShips + "st ships lefts");
                grid[shotx][shoty] = "[-]";
                printBoard();
                computerShots--;
                computerScore +=2;
            }
            else{
                System.out.println("Miss!");
                grid[shotx][shoty] = "[*]";
                printBoard();
                computerShots--;
            }
        } while(computerShots > 0);
    }

    void printBoard() {
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++){
                System.out.print(grid[col][row]);
            }
            System.out.println();
        }
    }
    void deployPlayerShip(){
        System.out.println("Deploy your ships: " + playerShips  + " ships");
        System.out.print(" first ship, x - coordinate: ");
        int firstShipx = sc.nextInt();
        System.out.print(" first ship, y - coordinate: ");
        int firstShipy = sc.nextInt();

        System.out.print(" second ship, x - coordinate: ");
        int secondShipx = sc.nextInt();
        System.out.print(" second ship, y - coordinate: ");
        int secondShipy = sc.nextInt();

        System.out.print(" third ship, x - coordinate: ");
        int thirdShipx = sc.nextInt();
        System.out.print(" third ship, y - coordinate: ");
        int thirdShipy = sc.nextInt();

        System.out.print(" Forth ship, x - coordinate: ");
        int ForthShipx = sc.nextInt();
        System.out.print(" Forth ship, y - coordinate: ");
        int ForthShipy = sc.nextInt();

        grid[firstShipx][firstShipy] = "[X]";
        grid[secondShipx][secondShipy] = "[X]";
        grid[thirdShipx][thirdShipy] = "[X]";
        grid[ForthShipx][ForthShipy] = "[X]";
        playerShips = 4;

        System.out.println("Your ships as been deployed! " + playerShips + " st");
        printBoard();
    }
    void deployComputerShips(){
        while(controlShipPosition.size() < 8){
            for(int i = 0; i < 8; i++){
                controlShipPosition.add((int)(Math.random() * 5) +1);
            }
            for(int i = 0; i < controlShipPosition.size()/2; i++){
                if(grid[controlShipPosition.get(i)][controlShipPosition.get(i+1)] != "[X]"){
                    grid[controlShipPosition.get(i)][controlShipPosition.get(i+1)] = "[ ]";
                    computerShips++;
                }
                else if(grid[controlShipPosition.get(i)][controlShipPosition.get(i+1)] == "[X]"){
                    System.out.println("Spot is taken!");
                    controlShipPosition.remove(controlShipPosition.get(i+1));
                    controlShipPosition.add((int)(Math.random() * 5) +1);
                }
            }
        }
        //System.out.println(controlShipPosition);
        System.out.println(computerShips + " st computer ships has been deployed!");
    }
    void gameOver(){
        if(playerShips > 0 && computerShips <= 0){
            System.out.println("You won! Score: " + playerScore);
            System.exit(0);
        }
        else{
            System.out.println("You lost Score: " + playerScore);
            System.exit(0);
        }
    }
}