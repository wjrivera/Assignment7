/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     User class for Bankers Algorithm with Semaphores.
 */

import java.util.Random;
import java.util.concurrent.Semaphore;

public class User implements Runnable {

    int needs[], request[], id;
    Banker banker;
    Semaphore user;
    public static Random rand;
    int counter = 5;

    //Constructor
    public User(int id, int m, Banker bankers){

        this.id = id;
        needs = new int[m];
        banker = bankers;
        request = new int[m];
        user = new Semaphore(1, true);
        rand = new Random();

    }

    //Make need vector
    public void need(){

        for(int i = 0; i < needs.length; i++){

            //Randomly create new vector
            needs[i] = rand.nextInt(10);

        }

    }

    //Substract Request Vector from Need Vector
    public void substractNumbers(){

        if(Utility.isZero(needs) == false) {

            if (Utility.isLessThanOrEqualTo(request, needs)) {

                Utility.subtract(needs, request);
            }
        }

    }

    //If all elements in the array are zeroes
    public boolean allZeroes(int[] needArray){

        int innerCounter = 0;

        for(int i = 0; i < needArray.length; i++){

            if(needArray[i] == 0){

                innerCounter++;

            }

        }

        if(innerCounter == needArray.length){

            return true;

        }
        else{

            return false;

        }

    }

    //Randomly create request submit vector
    public void makeRequest(){

        //Get Mutex, add array, release Mutex
        Driver.acquireMutex();
        Driver.acquireBanker();

        while(allZeroes(needs) == false){

            Utility.randomize(request, needs);

            System.out.println("Thread " + id + " asks for " + Utility.arrayToString(request));

            //Substract from Need Vector
            substractNumbers();
            System.out.println("Thread " + id + " got " + Utility.arrayToString(request));

            counter --;

        }

        System.out.println("****** Thread " + id + " completes ******");

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

    }
    @Override
    public void run() {

        //Get Mutex and Add to banker thread
        Driver.acquireBanker();
        banker.threads.add(this);
        Driver.releaseBanker();

        // Make need vector array and print its contents
        need();
        System.out.println("Thread " + id + " begins to run and needs " + Utility.arrayToString(needs));

        makeRequest();

    }

}