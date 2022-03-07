import java.util.*;
import java.util.concurrent.Semaphore;

public class Kasa {
    public static int x;
    public int y;
    int liczbaKlientow;
    boolean zajeta;
    int number;
    boolean przyjmuje;
    Semaphore kasalock;

    public List<Klient> listaKlientow = new LinkedList<>();

    public Kasa(int number, int x, int y, int liczbaKlientow, boolean zajeta, boolean przyjmuje){
        this.number = number;
        this.x = x;
        this.y = y;
        this.liczbaKlientow = liczbaKlientow;
        this.zajeta = zajeta;
        this.przyjmuje = przyjmuje;
        kasalock = new Semaphore(1);
    }
    public void acquireKasa(){
        try {
            kasalock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void releaseKasa(){
        kasalock.release();
    }
    public int avKasa(){
        return kasalock.availablePermits();
    }

}
