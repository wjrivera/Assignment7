/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     Banker class for Bankers Algorithm with Semaphores.
 */

import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Arrays;

public class Banker implements Runnable {

    //private Semaphore mutex;
    //private Semaphore empty;
    //private Semaphore full;
    public ArrayList<User> threads;
    public ArrayList<int[]> request;
    int available[];

    public Banker(int m){

        //mutex = new Semaphore(1);
        //empty = new Semaphore(BUFFER_SIZE);
        //full = new Semaphore(0);

        threads = new ArrayList<User>();
        request = new ArrayList<int[]>();
        available = new int[m];

        for(int i = 0; i < available.length; i++){

            available[i] = Utility.rand.nextInt((50 - 1) - 1) + 1;

        }

        //System.out.println("Available thread " + Utility.arrayToString(available));

    }

    @Override
    public void run() {

        // TODO Auto-generated method stub
        //System.out.println("Banker is running");

        while(true){

            try {

                Thread.sleep(2000);

            }
            catch (InterruptedException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            for(int i = 0; i < request.size(); i++){

                //System.out.println(Arrays.toString(request.get(i)));

            }

            for(int j = 0; j < threads.size(); j++){

                threads.get(j).user.release();
                //System.out.println("thread : release " + threads.get(j).id);
                System.out.println("****** Thread " + threads.get(j).id + " completes. ******");

            }

            break;

        }

    }

}