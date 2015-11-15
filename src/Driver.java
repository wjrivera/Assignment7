/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     Driver class for Bankers Algorithm with Semaphores.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Driver {

    static int n,m;
    static Banker bankers;
    static Semaphore mutex, banker;

    public static void main(String[] args) throws InterruptedException{

        n = 5;
        m = 5;
        mutex = new Semaphore(1, true);
        banker = new Semaphore(1, true);

        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        bankers = new Banker(m);

        for(int i = 0; i < n; i++){

            //Runs the number of customer threads
            User user = new User(i, m, bankers);
            threadExecutor.execute(user);

        }

        System.out.println("Banker is running...");

        threadExecutor.execute(bankers);
        threadExecutor.shutdown();
        threadExecutor.awaitTermination(30, TimeUnit.SECONDS);

    }

    //Get Mutex function
    public static void acquireMutex(){

        try {

            mutex.acquire();

        }
        catch (InterruptedException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

    //Release Mutex Function
    public static void releaseMutex(){

        mutex.release();

    }

    public static void acquireBanker(){

        try {

            banker.acquire();

        }
        catch (InterruptedException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

    public static void releaseBanker(){

        banker.release();

    }

}

