import java.util.*;

public class KlientMake extends Thread{
    private int id=0;
    public int x = 650;
    public int y = 0;
    public int N;
    public Kasa[] kasa;
    public KlientMake(int N){
        this.N = N;
    }
    public static final List<Klient> klienci = new ArrayList<>();
    public Klient create(){
        id++;
        Klient klient = new Klient(id,x, y,false, false, kasa);
        klienci.add(klient);
        return klient;
    }
    public Klient[] getKlienciTab(){
        Klient[] tablica = new Klient[klienci.size()];
        klienci.toArray(tablica);
        return tablica;
    }
    public void run(){
        for(int i=0;i<N;i++){
            Klient k;
            k = create();
            k.start();
        }
    }
}
