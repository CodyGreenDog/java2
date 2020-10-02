package ru.vitalygolikov.java2.lesson2;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class HomeworkApp {
    final static int REQUIRED_X_SIZE = 4;
    final static int REQUIRED_Y_SIZE = 4;



    static class IllegalMatrixSizeException extends  IllegalArgumentException {
        public IllegalMatrixSizeException()
        {
            super("Illegal matrix size: must be 4x4");
        }

    }

    static class IllegalTypeException extends  NumberFormatException {
        public IllegalTypeException()
        {
            super("Illegal Type: not a number");
        }
    }

    public static void main(String[] args)
    {
        String lineArray = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";

        //init variables
        int[] sizes = null;
        String[][] array2D = null;
        int[][] intArray = null;
        float halfSum;

        /*----------------------------*/


        //main part
        try {
            sizes = calcSizes(lineArray);
        }catch(IllegalMatrixSizeException e) {
            throw e;
        }

        array2D = lineTo2DArray(lineArray, sizes.length);

        try{
            intArray = stringToInt2DArray(array2D);
        }
        catch (IllegalTypeException e)
        {
            throw e;
        }

        halfSum = getHalfSum(intArray);
        System.out.println("Half sum of the matrix elements equals " + halfSum);


    }


    public static int[] calcSizes(String lineArray) throws IllegalMatrixSizeException
    {
        int rowsVal = 0;
        ArrayList<Integer> rowSizes = new ArrayList<Integer>();

        int maxRowSize = 0;
        int minRowSize = REQUIRED_X_SIZE*2;
        int currentRowSize = 1;
        for(int i = 0; i < lineArray.length(); i++)
        {
            if( lineArray.charAt(i) != '\n' && i != lineArray.length() - 1)
            {
                if(lineArray.charAt(i) == ' ')
                {
                    currentRowSize++;
                }
            }
            else
            {
                rowSizes.add(currentRowSize);
                maxRowSize = maxRowSize < currentRowSize ? currentRowSize : maxRowSize;
                minRowSize = minRowSize > currentRowSize ? currentRowSize : minRowSize;
                currentRowSize = 1;
                rowsVal++;
            }
        }

        Integer[] sizes = new Integer[rowsVal];

        rowSizes.toArray(sizes);

        int[] result = new int[rowsVal];
        for(int i = 0; i < rowsVal; i++)
        {
            result[i] = sizes[i].intValue();
        }
        if(result[0] != REQUIRED_X_SIZE || result[1] != REQUIRED_Y_SIZE ||
                maxRowSize != REQUIRED_X_SIZE || minRowSize != REQUIRED_X_SIZE)
        {
            throw new IllegalMatrixSizeException();
        }

        return result;
    }

    public static String[][] lineTo2DArray(String lineArray, int size)
    {
        String[][] array2D = new String[ size ][ size ];

        int x = 0;
        int y = 0;
        array2D[y][x] = "";
        for(int i = 0; i < lineArray.length(); i++)
        {
            if( lineArray.charAt(i) != '\n' && lineArray.charAt(i) != ' ' )
            {
                array2D[y][x] = array2D[y][x] + lineArray.charAt(i);
            }
            else
            {
                if( ++x >= 4)
                {
                    x = 0;
                    ++y;
                }

                if(y < 4) { array2D[y][x] = ""; }
            }
        }
        return array2D;
    }

    public static int[][] stringToInt2DArray(String[][] stringArray) throws IllegalTypeException
    {
        int[][] intArray = new int[stringArray.length][];
        for(int y = 0; y < stringArray.length; y++ )
        {
            intArray[y] = new int[ stringArray[y].length ];
            for(int x = 0; x < stringArray[y].length; x++)
            {
                try{
                    intArray[y][x] = Integer.parseInt(stringArray[y][x]);
                } catch(NumberFormatException e)
                {
                    throw new IllegalTypeException();
                }

            }
        }
            return intArray;
    }

    public static float getHalfSum(int[][] array)
    {
        int tmp = 0;
        for(int y = 0; y < array.length; y++)
        {
            for(int x = 0; x < array[y].length; x++)
            {
                tmp += array[y][x];
            }
        }
        return (float)tmp/2f;
    }
}
