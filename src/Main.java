import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    JButton Votar, Apurar, Sair;
    JLabel Inst;

    public Main() {
        setTitle("Eleições 20XX - Urna Eletrônica");
        setSize(500,250);
        // setLocation(50,50);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Inst = new JLabel("Escolha uma opção");
        Inst.setLocation(130,20);
        Inst.setSize(490,30);
        Inst.setFont(new Font("Sans", Font.BOLD, 22));
        Inst.setForeground(new Color(255,255,255));

        Votar = new JButton("Votar");
        Votar.setSize(200,50);
        Votar.setLocation(30,80);
        Votar.addActionListener(this);
        Votar.setFont(new Font("Sans", Font.PLAIN, 22));
        Votar.setBackground(new Color(255,87,34));
        Votar.setForeground(new Color(255,255,255));

        Apurar = new JButton("Apurar");
        Apurar.setSize(200,50);
        Apurar.setLocation(270,80);
        Apurar.addActionListener(this);
        Apurar.setFont(new Font("Sans", Font.PLAIN, 22));
        Apurar.setBackground(new Color(255,87,34));
        Apurar.setForeground(new Color(255,255,255));

        Sair = new JButton("Sair");
        Sair.setSize(80,40);
        Sair.setLocation(210,150);
        Sair.addActionListener(this);
        Sair.setFont(new Font("Sans", Font.PLAIN, 18));
        Sair.setBackground(new Color(255,	120,	78));
        Sair.setForeground(new Color(255,255,255));

        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(96,125,139));
        getContentPane().add(Inst);
        getContentPane().add(Votar);
        getContentPane().add(Apurar);
        getContentPane().add(Sair);
    }

    public static void main (String arg[])
    {
        new Main().setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Votar) {
            new Votar().setVisible(true);
        }
        else if (e.getSource() == Apurar) {
            String resultadoGov, resultadoPrez;
            SQLConnect apuracao = new SQLConnect();
            resultadoGov = apuracao.apurarGov();
            resultadoPrez = apuracao.apurarPrez();
            JOptionPane.showMessageDialog(null, resultadoGov);
            JOptionPane.showMessageDialog(null, resultadoPrez);
        }
        else if (e.getSource() == Sair)
            System.exit(0);
    }
}