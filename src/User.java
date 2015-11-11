/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     User class for Bankers Algorithm with Semaphores.
 */

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class User implements Runnable{

    Banker banker;
    Random rand = new Random();


    //constructor
    public User(Banker b){

        this.banker = b;

    }

    @Override
    public void run() {


    }
}
