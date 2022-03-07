import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener {
    public int M;
    public int N;
    public int height;
    public int width;
    boolean x;
    SetValues sv = new SetValues();
    final TextField tf1=new TextField("0");
    final TextField tf2=new TextField("0");

    GUI(int M, int N){
        this.M=M;
        this.N=N;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(220, 200));

        tf1.setBounds(50,50, 80,20);
        tf2.setBounds(50,80, 80,20);

        Font font = new Font("Jokerman", Font.PLAIN, 20);
        JLabel textLabel1 = new JLabel("M: ");
        textLabel1.setFont(font);
        textLabel1.setBounds(10,50, 80,20);
        JLabel textLabel2 = new JLabel("N: ");
        textLabel2.setFont(font);
        textLabel2.setBounds(10,80, 80,20);
        Button b = new Button("Zatwierdz");
        b.setBounds(50,100,60,30);
        add(b);
        add(tf1);
        add(tf2);
        add(textLabel1);
        add(textLabel2);

        setLayout(null);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = tf1.getText();
        String s2 = tf2.getText();
        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        sv.setM(a);
        sv.setN(b);
        sv.xchange();

    }

}