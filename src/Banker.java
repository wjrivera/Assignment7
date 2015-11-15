/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     Banker class for Bankers Algorithm with Semaphores.
 */

import java.util.ArrayList;

public class Banker implements Runnable {

    public ArrayList<User> threads;
    public ArrayList<int[]> request;

    public Banker(int m){

        threads = new ArrayList<User>();
        request = new ArrayList<int[]>();

    }

    @Override
    public void run() {

        // TODO Auto-generated method stub

        while(true){

            try {

                Thread.sleep(2000);

            }
            catch (InterruptedException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            //Threads complete
            for(int j = 0; j < threads.size(); j++){

                threads.get(j).user.release();

            }

            break;

        }

    }

}