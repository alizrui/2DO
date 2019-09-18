// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    public LimitedTable(StateManager state) {super(state);}
    
    public synchronized void enter(int id) throws InterruptedException {  
        while (philosos == 4) {
            state.wenter(id); wait();
        }
        philosos++;
        state.enter(id);
    }
    public synchronized void exit(int id)  {
        philosos--;
        
        state.exit(id);
        notifyAll();
    }
}
