package ru.vitalygolikov.java2.lesson5;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main( String[] args )
    {
        singleThreadCalculation();
        doubleThreadCalculation();
    }


    static void singleThreadCalculation()
    {
        float[] arr = new float[size];

        Runnable r = new Runnable() {
            @Override
            public void run() {
                fillWithOne(arr);
                calcSinMulValues(arr);
            }
        };

        Thread thread = new Thread( r, "Single Thread" );
        thread.setPriority(10);
        thread.start();

    }

    static void doubleThreadCalculation()
    {
        float[] arr = new float[size];

        float[] a1 = new float[h];
        float[] a2 = new float[h];



        Runnable rSplit = new Runnable() {
            @Override
            public void run() {
                splitArray(arr, a1, a2);
            }
        };

        Thread tSplit = new Thread(rSplit, "Split arrays");
        tSplit.start();



        Thread[] threads = procDoubleThreadArrCalc(arr, a1, a2);

        if(threads[0] != null && threads[1] != null)
        {
            while(tSplit.isAlive());

            threads[0].start();
            threads[1].start();
        }


        Runnable rGlue = new Runnable() {
            @Override
            public void run() {
                glueArrays(a1, a2, arr);
            }
        };
        Thread tGlue = new Thread(rGlue, "Glue arrays");

        while (threads[0].isAlive() && threads[1].isAlive());

        tGlue.start();



    }

    //==============================================================
    //Common methods
    //===============================================================
    synchronized static void fillWithOne(float[] arr)
    {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = 1f;
        }

        long execTime = System.currentTimeMillis() - startTime;
        printExecuteTime( "Fill array with 1",execTime);
    }

    synchronized static void calcSinMulValues(float[] arr)
    {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long execTime = System.currentTimeMillis() - startTime;
        printExecuteTime( "Fill array with sin/cos multiplications", execTime);
    }

    synchronized static void printExecuteTime(String name, long time)
    {
        System.out.println(Thread.currentThread().getName() + ":\n" + name + " execution time = " + time);
    }
    //End common methods


    //=================================================
    //Double thread methods
    //==================================================

    static Thread[] procDoubleThreadArrCalc( float[] arr, float[] a1, float[] a2)
    {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                fillWithOne(a1);
                calcSinMulValues(a1);
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                fillWithOne(a2);
                calcSinMulValues(a2);
            }
        };

        Thread[] threads = {
                new Thread(r1, "First part"),
                new Thread(r2,"Second part")
        };

        return threads;
    }

    static void splitArray(float[] arr, float[] a1, float[] a2)
    {
        long startTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        long execTime = System.currentTimeMillis() - startTime;
        printExecuteTime("Split arrays", execTime);

    }

    static void glueArrays(float[] a1, float[] a2, float[] arr)
    {
        long startTime = System.currentTimeMillis();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long execTime = System.currentTimeMillis() - startTime;
        printExecuteTime("Glue arrays", execTime);
    }
}
