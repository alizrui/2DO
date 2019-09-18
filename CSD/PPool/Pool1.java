// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {   //no kids alone
    protected int kids = 0;
    protected int teachers = 0;

    public void init(int ki, int cap)           {}
    public synchronized void kidSwims()  throws InterruptedException  {
        while(teachers == 0) {
            log.waitingToSwim();
            wait();
        }
        kids++;
        notifyAll();
        log.swimming();
    }
    public synchronized void kidRests()      {
        kids--;
        notifyAll();
        log.resting();
    }
    public synchronized void instructorSwims()   {
        teachers++;
        notifyAll();
        log.swimming();
    }
    public synchronized void instructorRests() throws InterruptedException  {
        while(kids > 0 && teachers == 1)  {
            log.waitingToRest();
            wait();
        }
        teachers--;
        notifyAll();
        log.resting();
    }
}
