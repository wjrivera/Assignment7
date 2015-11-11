/**
 * Author:      7121
 * Date:        11/10/15
 * Class:       Operating Systems
 * Purpose:     Banker class for Bankers Algorithm with Semaphores.
 */

import java.util.concurrent.Semaphore;

public class Banker {

    private static final int BUFFER_SIZE = 2;

    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;

    public Banker(){

        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);

    }

}
