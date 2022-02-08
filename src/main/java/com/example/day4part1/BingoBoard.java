package com.example.day4part1;

import java.util.ArrayList;

public class BingoBoard {

        ArrayList<Integer> rows;
        ArrayList<Integer> columns = new ArrayList();

        public BingoBoard (ArrayList<Integer> bingoBoardNumbers) {

            rows = bingoBoardNumbers;

            for (int i = 0; i < 5; ++i) {
                for (int j = i; j < bingoBoardNumbers.size(); j+=5) {
                    columns.add(rows.get(j));
                }
            }
        }

        public void checkBoardForDraw (int drawNumber) {
            for (int i = 0; i < rows.size(); ++i) {
                if (drawNumber == rows.get(i)) {
                    rows.set(i, -1);
                }
            }
            for (int i = 0; i < columns.size(); ++i) {
                if (drawNumber == columns.get(i)) {
                    columns.set(i, -1);
                }
            }
        }

        public boolean checkBoardForBingo () {
            for (int i = 0; i < rows.size(); i+=5) {
                int counter = 0;
                for (int j = i; j < i + 5; ++j) {
                    if (rows.get(j) != -1){
                        break;
                    }
                    else {
                        ++counter;
                    }
                    if (counter == 5) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < columns.size(); i+=5) {
                int counter = 0;
                for (int j = i; j < i + 5; ++j) {
                    if (columns.get(j) != -1){
                        break;
                    }
                    else {
                        ++counter;
                    }
                    if (counter == 5) {
                        return true;
                    }
                }
            }
            return false;
        }
}
