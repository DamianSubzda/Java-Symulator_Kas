import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Frame extends JPanel implements ActionListener {
    private final int width;
    private final int height;
    private int M;
    private int N;
    Kasa []kasa;
    Kasjer []kasjer;
    KlientMake km = new KlientMake(N);
    public static List<Klient> klienci= new ArrayList<>();
    private final Timer timer = new Timer();
    private final int renderInterval = 5;
    Image kasjerImage = new ImageIcon("kasjer1.png").getImage();
    SetValues sv;

    Frame(int width, int height, int M, int N, Kasa kasa[], Kasjer kasjer[]){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(1000, 1000));

        this.M=M;
        this.N=N;
        this.kasa=kasa;
        this.kasjer = kasjer;
        timer.schedule(renderTask(), 0, renderInterval);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);

        paintSklep(g);
        paintKasy(g);
        paintKasjer(g);
        try {
            paintKasjerzy(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
        paintKlinci(g);
        paintKolejki(g);

    }
    private void paintSklep(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 100, 200, 10); // Od ekranu do drzwi
        g.fillRect(300, 100, 300, 10); //Pomiędzy
        g.fillRect(700, 100, 500, 10); //Od drzwi do ekranu
        g.fillRect(320, 100, 10, 900);
        g.setColor(Color.gray);
        g.fillRect(500,150,500,850);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.drawString("Ilosc klientow w sklepie: "+ sv.getIloscKlientow(),550,500);
    }
    private void paintKasy(Graphics g){
        g.setColor(Color.BLACK);
        for(int i=0;i<M;i++) {
            g.setColor(Color.BLACK);
            g.fillRect(kasa[i].x, kasa[i].y-20, 10, 45);
            g.setColor(Color.WHITE);
            g.fillRect(kasa[i].x, kasa[i].y, 5, 5); //środek kasy

        }
    }
    public void paintKasjer(Graphics g){
        int i = M-1;
        while(i>=0) {
            if (kasa[i].przyjmuje == true) {
                g.setColor(Color.GREEN);
                g.fillRect(kasa[i].x, kasa[i].y-10, 9, 9);
            } else {
                g.setColor(Color.RED);
                g.fillRect(kasa[i].x, kasa[i].y-10, 5, 5);
            }
            i--;
        }
    }
    public void paintKasjerzy(Graphics g) throws IOException {
        int i = M-1;
        while(i>=0) {
            g.drawImage(kasjerImage,kasjer[i].x, kasjer[i].y, null, null);
            i--;
        }
    }
    public void paintKlinci(Graphics g){
        g.setColor(Color.WHITE);
        Klient tab[] = km.getKlienciTab();
        int i = 0;
        while(i<tab.length){
            if(tab[i].visible==false){
                g.setColor(Color.BLACK);
                g.fillRect(tab[i].x, tab[i].y,5, 5);
            }else {
                g.setColor(Color.WHITE);
                g.fillRect(tab[i].x, tab[i].y,5, 5);
            }
            g.fillRect(tab[i].x, tab[i].y,5, 5);
            i++;
        }

    }
    public void paintKolejki(Graphics g){
        g.setColor(Color.WHITE);
        for(int i=0;i<M;i++) {
            for(int j=0;j<kasa[i].liczbaKlientow;j++)
                g.fillRect(20+kasa[i].x+j*10, kasa[i].y, 5, 5);
        }
    }
    private TimerTask renderTask() {
        return new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };
    }
}
