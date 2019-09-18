// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    protected int kids = 0;
    protected int teachers = 0;
    protected int MAXK = 0;
    
    public void init(int ki, int cap)           {}
    public synchronized void  kidSwims()  throws InterruptedException    {
        while(MAXK ==  kids || teachers + kids == 5) {
            log.waitingToSwim();
            wait();
        }
        kids++;
        notifyAll();
        log.swimming();
    }
    public synchronized void  kidRests()      {
        kids--;
        notifyAll();
        log.resting(); 
    }
    public synchronized void  instructorSwims()  throws InterruptedException {
        while(teachers + kids == 5) {
            log.waitingToSwim();
            wait();
        }
        MAXK = MAXK+2;
        teachers++;
        notifyAll();
        log.swimming();
    }
    public synchronized void  instructorRests() throws InterruptedException  {
        while(kids >= MAXK - 1){
            log.waitingToRest();
            wait();
        }
        teachers--;
        MAXK = MAXK - 2;
        notifyAll();
        log.resting(); 
    }
}
