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

    // Semaphore maintains a set of permits.
    // Each acquire blocks if necessary until a permit is available, and then takes it.
    // Each release adds a permit, potentially releasing a blocking acquirer.
    static int n,m;
    static Banker bankers;
    static Semaphore mutex, user, banker;

    public static void main(String[] args) throws InterruptedException{
        n = 5;
        m = 5;
        mutex = new Semaphore(1, true);
        banker = new Semaphore(1, true);

        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        //BarberShop class is instantiated for the customers to use
        bankers = new Banker(m);

        for(int i = 0; i < n; i++){

            //Runs the thread Customer as a new instantiation a given amount of loop times
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

/*    public static void acquireUser(){

    	try {

			user.acquire();

		}
		catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

    }

	public static void releaseUser(){

		user.release();

	}*/
}

