import javax.swing.*;

public class Main {
    static int N = 10; //Liczba klientów
    static int M = 0; //Liczba kas
    static boolean x = true;
    private static SetValues sv;

    public static void main(String [] args) throws InterruptedException {
        //GUI
        JFrame GUI = new JFrame("GUI");
        GUI.setResizable(false);
        GUI.add(new GUI(M,N));
        GUI.setSize(220,200);
        GUI.setLocationRelativeTo(null);
        GUI.setVisible(true);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //PROGRAM
        while(x){
            try{
                Thread.currentThread().sleep(1000);
                M=sv.getM();
                N=sv.getN();
                x=sv.xch();
            }catch(InterruptedException e){
                System.out.println("Blad");
            }
        }
        GUI.setVisible(false);

        Kasa []kasa = new Kasa[M];
        Kasjer []kasjer = new Kasjer[M];
        int x = 320;
        int y = 900;
        int zm = 900/(M+1);
        for(int i=0;i<M;i++){
            y=y-zm;
            kasa[i] = new Kasa(i,x,y,0, false,false);//, listaKlientow
        }
        sv.setKasa(kasa);

        JFrame frame = new JFrame("Sklep");
        for(int i=0;i<M;i++){
            kasjer[i] = new Kasjer(i,false, kasa,M,frame, 200,900);
        }

        for(int i=0;i<M;i++) {
            Thread.sleep(100);
            kasjer[i].start();
        }

        KlientMake km = new KlientMake(N);

        km.start();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Frame(1200,1200, M, N, kasa,kasjer));
        frame.setVisible(true);
        frame.setLocation(262,0);
        frame.pack();
    }
}
