import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;

public class FondoPanel extends JFrame{

    JLabel label;

    public FondoPanel(){
        java.awt.Container base = getContentPane();
        base.setBackground(new Color(40, 42, 54));
        base.setLayout(new BorderLayout());
        label = new JLabel("Etiqueta");
        base.add(label);
        this.setVisible(true);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void initComponents(){
        principal();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200,300);
        this.setVisible(true);

    }

    public JPanel principal(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 42, 54));
        panel.setSize(200, 300);
        panel.setLayout(new BorderLayout());
        JButton boton = new JButton("Boton");
        panel.add(boton, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String args[]){
        new FondoPanel();
    }
}
