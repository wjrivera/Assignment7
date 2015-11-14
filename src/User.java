/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     User class for Bankers Algorithm with Semaphores.
 */

import java.util.concurrent.Semaphore;

public class User implements Runnable {

    int needs[], request[], id;
    Banker banker;
    Semaphore user;

    //Constructor
    public User(int id, int m, Banker bankers){

        this.id = id;
        needs = new int[m];
        banker = bankers;
        request = new int[m];
        user = new Semaphore(1, true);
    }

    //Make vector
    public void need(){

        for(int i = 0; i < needs.length; i++){

            needs[i] = Driver.s.nextInt((100 - 1) + 1) - 1;

        }

    }

    public void makeRequest(){

        Utility.randomize(request, needs);

        Driver.acquireMutex();
        System.out.println("Thread "+id+" requested "+Utility.arrayToString(request));
        banker.request.add(request);
        Driver.releaseMutex();
        Driver.releaseBanker();

        try {

            user.acquire();

        }
        catch (InterruptedException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        System.out.println("Im waiting in thread "+id+" permits available are: "+user.availablePermits());


    }
    @Override
    public void run() {

        //First thing the user must do is add its self to the bankers thread list
        Driver.acquireMutex();
        banker.threads.add(this);
        Driver.releaseMutex();
        int sa[] = new int[5];
        for(int i = 0; i < 5; i++){

            sa[i] = 2;

        }
        // Now the user must generate a need array and print out who he is and
        // what it needs
        need();
        System.out.println("Thread "+ id +" begins to run and needs "+Utility.arrayToString(needs));
        makeRequest();

        //Now we need to create a request until needs are not fullfilled

    }

}