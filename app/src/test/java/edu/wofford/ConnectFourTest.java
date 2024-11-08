/*
 * This source file was generated by the Gradle 'init' task
 */
package edu.wofford;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConnectFourTest {

    @Test
    public void testGetTopOfColumn() {
        ConnectFour cf = new ConnectFour();
        int passes = 0;
        boolean ans = true;
        for(int col = 0; col < 7; col++) {
        boolean v = (cf.getTopOfColumn(col) == ConnectFour.Location.EMPTY);
        if (v) passes++;
        ans = ans && v;
        for (int row = 5; row >= 0; row--) {
            if (row % 2 == 0) cf.setLocation(row, col, ConnectFour.Location.RED);
            else cf.setLocation(row, col, ConnectFour.Location.BLACK);
            v = (cf.getTopOfColumn(col) == ((row % 2 == 0)? ConnectFour.Location.RED : ConnectFour.Location.BLACK));
            if (v) passes++;
            ans = ans && v;
        }
        }
        assertTrue(ans, "Passes " + passes + " of 49 tests.");
    }



    @Test
    public void testGetHeightOfColumn() {
        ConnectFour cf = new ConnectFour();
        int passes = 0;
        boolean ans = true;
        for(int col = 0; col < 7; col++) {
        boolean v = (cf.getHeightOfColumn(col) == 0);
        if (v) passes++;
        ans = ans && v;
        for (int row = 5; row >= 0; row--) {
            if (row % 2 == 0) cf.setLocation(row, col, ConnectFour.Location.RED);
            else cf.setLocation(row, col, ConnectFour.Location.BLACK);
            v = (cf.getHeightOfColumn(col) == (6 - row));
            if (v) passes++;
            ans = ans && v;
        }
        }
        assertTrue(ans, "Passes " + passes + " of 49 tests.");
    }


    @Test
    public void testDropToken1() {
        ConnectFour cf = new ConnectFour();
        int passes = 0;
        boolean ans = true;
        for(int col = 0; col < 7; col++) {
        for (int row = 5; row >= 0; row--) {
            cf.dropToken(col);
            ConnectFour.Location loc = (row % 2 == 1)? ConnectFour.Location.RED : ConnectFour.Location.BLACK;
            boolean v = (cf.getLocation(row, col) == loc);
            if (v) passes++;
            ans = ans && v;
        }
        }
        assertTrue(ans, "Passes " + passes + " of 42 tests.");
    }




    @Test
    public void testDropToken2() {
        ConnectFour cf = new ConnectFour();
        int passes = 0;
        boolean ans = true;

        cf.dropToken(5);
        boolean v = (cf.getLocation(5, 5) == ConnectFour.Location.RED);
        if (v) passes++;
        ans = ans && v;
        cf.dropToken(7);  // This column is invalid and should be ignored.
        cf.dropToken(5);
        v = (cf.getLocation(4, 5) == ConnectFour.Location.BLACK) && (cf.getLocation(5, 5) == ConnectFour.Location.RED);
        if (v) passes++;
        ans = ans && v;
        cf.dropToken(-1);  // This column is invalid and should be ignored.
        cf.dropToken(5);
        v = (cf.getLocation(3, 5) == ConnectFour.Location.RED) && (cf.getLocation(4, 5) == ConnectFour.Location.BLACK) && (cf.getLocation(5, 5) == ConnectFour.Location.RED);
        if (v) passes++;
        ans = ans && v;
        for (int i = 0; i < 10; i++) { cf.dropToken(i - 20); cf.dropToken(i + 20); }
        cf.dropToken(5);
        v = (cf.getLocation(0, 5) == ConnectFour.Location.EMPTY) && 
            (cf.getLocation(1, 5) == ConnectFour.Location.EMPTY) && 
            (cf.getLocation(2, 5) == ConnectFour.Location.BLACK) && 
            (cf.getLocation(3, 5) == ConnectFour.Location.RED) && 
            (cf.getLocation(4, 5) == ConnectFour.Location.BLACK) && 
            (cf.getLocation(5, 5) == ConnectFour.Location.RED);
        if (v) passes++;
        ans = ans && v;

        boolean w = true;
        for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 6; j++) {
            if (i != 5) {
                w = w && (cf.getLocation(j, i) == ConnectFour.Location.EMPTY);
            }
        }
        }
        if (w) passes++;
        ans = ans && w;

        assertTrue(ans, "Passes " + passes + " of 5 tests.");
    }




    @Test
    public void testDropToken3() {
        ConnectFour cf = new ConnectFour();
        int passes = 0;
        boolean ans = true;
        int i = 0;
        while(i < 6) {
            cf.dropToken(1);
            i++;
        }
        try {
            cf.dropToken(1);
            ans = false;
        } catch (ColumnFullException e) {
            ans = true;
            passes++;
        }

        assertTrue(ans, "Passes " + passes + " of 1 tests.");
    }




    @Test
    public void testGetResult() {
        ConnectFour cf = new ConnectFour();
        int passes = 0;
        boolean ans = true;
        boolean v = true;
        for(int col = 0; col < 7; col++) {
        ConnectFour.Location loc = (col % 2 == 0)? ConnectFour.Location.RED : ConnectFour.Location.BLACK;
        cf.setLocation(5, col, loc);
        v = (cf.getResult() == ConnectFour.Result.NONE);
        if (v) passes++;
        ans = ans && v;
        }
        cf.setLocation(4, 2, ConnectFour.Location.RED);
        v = (cf.getResult() == ConnectFour.Result.NONE);
        if (v) passes++;
        ans = ans && v;
        cf.setLocation(3, 2, ConnectFour.Location.RED);
        v = (cf.getResult() == ConnectFour.Result.NONE);
        if (v) passes++;
        ans = ans && v;
        cf.setLocation(4, 3, ConnectFour.Location.BLACK);
        v = (cf.getResult() == ConnectFour.Result.NONE);
        if (v) passes++;
        ans = ans && v;
        cf.setLocation(3, 3, ConnectFour.Location.BLACK);
        v = (cf.getResult() == ConnectFour.Result.NONE);
        if (v) passes++;
        ans = ans && v;
        cf.setLocation(2, 2, ConnectFour.Location.RED);
        v = (cf.getResult() == ConnectFour.Result.REDWIN);
        if (v) passes++;
        ans = ans && v;
        cf.setLocation(2, 2, ConnectFour.Location.EMPTY);
        v = (cf.getResult() == ConnectFour.Result.NONE);
        if (v) passes++;
        ans = ans && v;
        cf.setLocation(2, 3, ConnectFour.Location.BLACK);
        v = (cf.getResult() == ConnectFour.Result.BLACKWIN);
        if (v) passes++;
        ans = ans && v;

        assertTrue(ans, "Passes " + passes + " of 14 tests.");
    }




    @Test
    public void testToString() {

        ConnectFour cf = new ConnectFour();

        String expected = "| | | | | | | |\n" +
                        "| | | | | | | |\n" +
                        "| | | | | | | |\n" +
                        "| | | | |R| | |\n" +
                        "|B|B| | |R|R| |\n" +
                        "|R|B|B|R|B|R| |\n" +
                        "---------------";
        int[][] drops = {{0, 5, 0}, 
                        {1, 5, 1},
                        {5, 5, 0},
                        {4, 5, 1},
                        {4, 4, 0},
                        {2, 5, 1},
                        {3, 5, 0},
                        {1, 4, 1},
                        {5, 4, 0},
                        {0, 4, 1},
                        {4, 3, 0}};
        for(int i = 0; i < drops.length; i++) {
        ConnectFour.Location loc = (drops[i][2] == 0)? ConnectFour.Location.RED : ConnectFour.Location.BLACK;
        cf.setLocation(drops[i][1], drops[i][0], loc);
        }
        int passes = 0;
        boolean ans = (cf.toString().trim().equals(expected));
        if (ans) passes++;

        assertTrue(ans, "Passes " + passes + " of 1 tests.");
    }


}
