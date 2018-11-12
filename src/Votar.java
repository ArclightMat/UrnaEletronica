import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Votar extends JFrame implements ActionListener {

    SQLConnect votar = new SQLConnect();
    String campoEntrada = "", partido = "", nome = "", nomeVice = "";
    JTextField visual = new JTextField(1);
    boolean dirty = false, completa = false;
    JButton[] botoes = new JButton[9];
    JButton b0 = new JButton("0");
    JButton bBranco = new JButton("Branco");
    JButton bCorrige = new JButton("Corrige");
    JButton bConfirma = new JButton("Confirma");
    ImageIcon fotoCandidato;
    JLabel lCargo, lPartido, lNome, lNomeVice, lFoto;
    String cargo;

    public Votar() {
        cargo = "Governador";
        votar.setCargo(cargo);
        // Inicializa o JFrame
        setTitle("Eleições 20XX - Votação");
        setSize(1200, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria paineis e layouts
        lCargo = new JLabel(cargo.toUpperCase());
        lNome = new JLabel(nome);
        lPartido = new JLabel(partido);
        lNomeVice = new JLabel(nomeVice);
        JPanel numpad = new JPanel(new GridLayout(4, 3, 5, 7));
        JPanel opcao = new JPanel(new GridLayout(1,3, 5, 0));
        JPanel numPadFull = new JPanel();
        JPanel interfaceVotar = new JPanel();
        interfaceVotar.setLayout(new BoxLayout(interfaceVotar, BoxLayout.Y_AXIS));
        numPadFull.setLayout(new BoxLayout(numPadFull, BoxLayout.Y_AXIS));
        // Inicializa array de botões
        for (int i = 0; i < botoes.length; i++) {
            botoes[i] = new JButton(String.valueOf(i+1));
            addBotaoUrna(botoes[i], numpad);
        }
        // Inicializa botões non-standard
        b0.setAlignmentX(Component.CENTER_ALIGNMENT);
        b0.setMinimumSize(new Dimension(50, 10));
        b0.setPreferredSize(new Dimension(100,50));
        b0.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));

        bBranco.setBackground(new Color(255,255,255));
        bCorrige.setBackground(new Color(255,69,0));
        bConfirma.setBackground(new Color(0,128,0));

        bBranco.setForeground(new Color(0,0,0));
        bCorrige.setForeground(new Color(0,0,0));
        bConfirma.setForeground(new Color(0,0,0));

        addBotaoUrna(b0);
        opcao.add(bBranco);
        opcao.add(bCorrige);
        opcao.add(bConfirma);
        bBranco.addActionListener(this);
        bCorrige.addActionListener(this);
        bConfirma.addActionListener(this);

        // Cria parte visual sobre o candidato
        visual.setEditable(false);
        visual.setColumns(2);
        visual.setPreferredSize(new Dimension(1500,50));
        visual.setMaximumSize(new Dimension(1800, visual.getPreferredSize().height));
        visual.setFont(new Font("Sans", Font.BOLD, 22));
        //visual.setHorizontalAlignment(SwingConstants.LEFT);
        lCargo.setFont(new Font("Sans", Font.BOLD, 22));
        fotoCandidato = new ImageIcon("pic/0.jpg");
        lFoto = new JLabel("", fotoCandidato, JLabel.CENTER);
        lNome.setFont(new Font("Sans", Font.BOLD, 16));
        lPartido.setFont(new Font("Sans", Font.BOLD, 16));
        lNomeVice.setFont(new Font("Sans", Font.BOLD, 16));


        interfaceVotar.add(lCargo);
        interfaceVotar.add(Box.createRigidArea(new Dimension(0,100)));
        interfaceVotar.add(visual);
        interfaceVotar.add(Box.createRigidArea(new Dimension(0,30)));
        interfaceVotar.add(lNome);
        interfaceVotar.add(Box.createRigidArea(new Dimension(0,30)));
        interfaceVotar.add(lPartido);
        interfaceVotar.add(Box.createRigidArea(new Dimension(0,30)));
        interfaceVotar.add(lNomeVice);

        /*
        setLayout(new BorderLayout(10,10));
        add(visual, BorderLayout.PAGE_START);
        add(numpad, BorderLayout.CENTER);
        add(opcao, BorderLayout.PAGE_END);
        */


        // setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        numPadFull.add(numpad);
        numPadFull.add(b0);
        numPadFull.add(Box.createRigidArea(new Dimension(0,100)));
        numPadFull.add(opcao);
        add(interfaceVotar);
        add(Box.createRigidArea(new Dimension(15,0)));
        add(lFoto);
        add(Box.createHorizontalGlue());
        add(numPadFull);
    }

    private void addBotaoUrna(JButton botao, JPanel painel) {
        botao.addActionListener(this);
        botao.setBackground(new Color(0,0,0));
        botao.setForeground(new Color(255,255,255));
        botao.setPreferredSize(new Dimension(50,50));
        painel.add(botao);
    }
    private void addBotaoUrna(JButton botao) {
        botao.addActionListener(this);
        botao.setBackground(new Color(0,0,0));
        botao.setForeground(new Color(255,255,255));
        botao.setPreferredSize(new Dimension(50,50));
    }

    private void getValores() {
        nome = votar.getNome();
        partido = votar.getPartido();
        cargo = votar.getCargo();
        nomeVice = votar.getVice();
        lNome.setText("NOME: " + nome);
        lPartido.setText("PARTIDO: " + partido);
        lNomeVice.setText("VICE: " + nomeVice);
        int numPart = votar.getNumPart();
        fotoCandidato = new ImageIcon("pic/" + numPart + ".jpg");
        lFoto.setIcon(fotoCandidato);

    }
    private void TrocaModo() {
        cargo = "Presidente";
        votar.reinicializa();
        votar.setCargo(cargo);
        lCargo.setText(cargo.toUpperCase());

        lNome.setText("");
        lPartido.setText("");
        lNomeVice.setText("");
        visual.setText("");
        campoEntrada = "";
        nome = "";
        partido = "";
        nomeVice = "";
        fotoCandidato = new ImageIcon("pic/0.jpg");
        lFoto.setIcon(fotoCandidato);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botoes[0] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "1";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[1] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "2";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[2] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "3";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[3] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "4";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[4] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "5";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[5] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "6";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[6] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "7";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[7] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "8";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == botoes[8] && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "9";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == b0 && !dirty && campoEntrada.length() < 2) {
            campoEntrada += "0";
            visual.setText(campoEntrada);
            if (campoEntrada.length() == 2) {
                votar.setNumPart(Integer.parseInt(campoEntrada));
                votar.verificar();
                getValores();
            }
        } else if (e.getSource() == bCorrige) {
            campoEntrada = "";
            dirty = false;
            visual.setText(campoEntrada);
            lNome.setText("");
            lPartido.setText("");
            lNomeVice.setText("");
            fotoCandidato = new ImageIcon("pic/0.jpg");
            lFoto.setIcon(fotoCandidato);
        } else if (e.getSource() == bBranco && campoEntrada.length() < 1) {
            campoEntrada = "VOTO BRANCO";
            visual.setText(campoEntrada);
            votar.setNumPart(2);
            votar.verificar();
            getValores();
            dirty = true;
        } else if (e.getSource() == bConfirma) {
            votar.atualizar();
            if (cargo.toUpperCase().equals("GOVERNADOR")) {
                TrocaModo();
            }
            else {
                System.exit(0);
            }
        }
    }
}