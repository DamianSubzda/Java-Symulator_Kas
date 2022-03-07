import javax.swing.*;
import java.util.Random;

public class Kasjer extends Thread {
    private boolean working;
    public Kasa []kasa;
    public int M;
    JFrame frame;
    public int x;
    public int y;
    private int id;
    
    public Kasjer(int id, boolean working, Kasa[] kasa, int M, JFrame frame, int x, int y){
        super("Kasjer" + id);
        this.working = working;
        this.kasa = kasa;
        this.M=M;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public void run() {

        while(true){
            int id=0;
            while(working==false){
                Random rand = new Random();
                id = rand.nextInt(M);
                if(kasa[id].zajeta==true){
                    working = false;
                }else{
                    working=true;
                    kasa[id].zajeta=true;
                }
            }
            idzdopracy(kasa[id]);

            pracuj(kasa[id]);

            przerwa(kasa[id]);
        }
    }

    public void idzdopracy(Kasa k2) {
        while (k2.y != y) {
            y--;
            try {
                Thread.currentThread().sleep(30*1/(k2.number+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(x!= 315){
            x++;
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        k2.przyjmuje = true;
    }
    public void pracuj(Kasa k2){
        k2.releaseKasa();
        Random rand = new Random();
        int result1 = 3 + rand.nextInt(6); //Przyjmuje od 3 do 8 klientów + reszta
        for(int i=0;i<result1;i++) {
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getKlient(k2);
        }
        k2.acquireKasa();
        k2.przyjmuje = false;

        if(k2.liczbaKlientow!=0){
            while(k2.liczbaKlientow!=0){
                try {
                    Thread.currentThread().sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getKlient(k2);
            }
        }
    }
    public void getKlient(Kasa k2){
        Klient k1=null;
        if(k2.listaKlientow.isEmpty()){
            //System.out.println("Brak klientow w kolejce");
        }else{
            if(k2.listaKlientow.get(0) ==null){

            }else{
                k1 = k2.listaKlientow.get(0);
                k2.listaKlientow.remove(0);
            }
            if(k1 == null){
            }else {
                k1.releaseKlient();
            }
        }
    }

    public void przerwa(Kasa k2){
        k2.zajeta = false;
        working = false;
        while(x!= 200){
            x--;
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(y!=900){
            y++;
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Random rand = new Random();
        int result = 2500 + rand.nextInt(2001);
        try {
            Thread.currentThread().sleep(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: Idę na przerwe"+ Thread.currentThread().getName());
        }
    }
}
