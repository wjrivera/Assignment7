/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     Driver class for Bankers Algorithm with Semaphores.
 */

public class Driver {

    public static void main(String args[]){

        Banker banker = new Banker();

        Thread userThread =  new Thread(new User(banker));

        userThread.start();

    }

}
