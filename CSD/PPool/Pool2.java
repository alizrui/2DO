// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    protected int kids = 0;
    protected int teachers = 0;
    protected int MAXK = 0;
    
    public void init(int ki, int cap)           {}
    public synchronized void  kidSwims()  throws InterruptedException    {
        while(MAXK ==  kids) {
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
    public synchronized void  instructorSwims()   {
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
