package com.example.day4part2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class Day4part2Application {

    public static void main(String[] args) throws FileNotFoundException {

        int lines = 0;

        Scanner linescanner = new Scanner(new File("day4/input.txt"));
        while (linescanner.hasNextLine()) {
            String str = linescanner.nextLine();
            ++lines;
        }
        linescanner.close();

        Scanner scanner = new Scanner(new File("day4/input.txt"));
        String  str= scanner.nextLine();
        String[] drawArrayStr = str.split(",");
        int [] drawArray = new int [drawArrayStr.length];
        for (int i = 0; i < drawArrayStr.length; ++i) {
            drawArray[i] = Integer.parseInt(drawArrayStr[i]);
        }

        //Skip empty line after draw line.
        scanner.nextLine();

        ArrayList<BingoBoard> bingoBoardArray = new ArrayList<>();

        int numberOfBingoBoards = 0;
        for (int i = 0; i < lines - 1; i+=6){
            ArrayList<Integer> bingoBoardNumbers = new ArrayList<>();
            for (int j = 0; j < 5; ++j) {
                for (int k = 0; k < 5; ++k) {
                    int number = scanner.nextInt();
                    bingoBoardNumbers.add(number);
                }
                scanner.nextLine();
            }
            BingoBoard bingoboard = new BingoBoard(bingoBoardNumbers);
            bingoBoardArray.add(bingoboard);
            ++numberOfBingoBoards;
        }

        //Find which Bingo Board gets Bingo first
        BingoBoard correctBingoBoard = null;
        int bingoNumber = 0;
        outerloop:
        for (int i = 0; i < drawArray.length; ++i) {
            for (int j = 0; j < bingoBoardArray.size(); ++j) {
                bingoBoardArray.get(j).checkBoardForDraw(drawArray[i]);
                if (bingoBoardArray.get(j).checkBoardForBingo()) {
//                    System.out.println(bingoBoardArray.get(j).columns + "\n" + bingoBoardArray.get(j).rows);
//                    System.out.println("bingo board = " + j);
                    bingoNumber = drawArray[i];
                    correctBingoBoard = new BingoBoard(bingoBoardArray.get(j).rows);
                    bingoBoardArray.remove(j);
                    --j;
                }
                if (bingoBoardArray.size() == 0) {
                    break outerloop;
                }
            }
        }

        // Calculates answer based off unmarked numbers
        int sum = 0;
        for (int i = 0; i < correctBingoBoard.rows.size(); ++i) {
            if (correctBingoBoard.rows.get(i) != -1) {
                sum += correctBingoBoard.rows.get(i);
            }
        }

        System.out.println("Final Answer: " + (sum * bingoNumber));
    }
}
