import java.util.Random;
import java.util.concurrent.Semaphore;

public class Klient extends Thread{
    public int x;
    public int y;
    public boolean visible;
    private int id;
    private boolean kupuje;
    public Kasa[] kasa;
    private SetValues sv;
    private Semaphore lock;

    public Klient(int id,int x,int y, boolean visible, boolean kupuje, Kasa[] kasa){
        super("Klient "+ id);
        this.x=x;
        this.y=y;
        this.visible=visible;
        this.id=id;
        this.kupuje = kupuje;
        this.kasa = kasa;
        lock = new Semaphore(0);
    }
    public void run() {
        while (true){
            Random rand = new Random();
            int result = 1500 + rand.nextInt(5001);
            try {
                Thread.currentThread().sleep(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enter();
            result = 3500 + rand.nextInt(4001);
            try {
                Thread.currentThread().sleep(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            putKlient();
        }
    }

    public void enter(){
        x = 650;
        visible = true;

        while(y!=145){
            y++;
            if(y==100){
                sv.setIloscKlientow();
            }
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        kupuje = true;
        visible = false;
    }
    public void putKlient(){
        visible = true;
        kasa = sv.getKasa();

        Random rand = new Random();
        int result = rand.nextInt(kasa.length);
        /*
        while(kasa[result].przyjmuje==false){
            result = rand.nextInt(kasa.length);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         */
        while(kasa[result].avKasa()==1){ // ?
            result = rand.nextInt(kasa.length);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        kasa[result].listaKlientow.add((Klient) Thread.currentThread());
        kasa[result].liczbaKlientow++;

        x = 500;
        y = kasa[result].y;
        while(x!=400){
            x--;
            try {
                Thread.currentThread().sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        visible = false;
        acquireKlient();
        out(result);
    }

    public void out(int result){
        visible = true;
        kasa[result].liczbaKlientow--;
        x = 310;
        while(x!=250){
            x--;
            try {
                Thread.currentThread().sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(y!=0){
            y--;
            try {
                Thread.currentThread().sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        visible = false;
        kupuje = false;
        kasa = null;
        sv.setIloscKlientowMin();
    }
    public void acquireKlient() {
        try {
            lock.acquire();
        } catch (InterruptedException e) {
        }
    }

    public void releaseKlient(){
        lock.release();
    }

}
