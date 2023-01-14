import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//class de fenetre de message (gagne ou perte)
public class MessageFrame extends JFrame {

    public Frame principale;
    public MessageFrame(String message) {
        //configuration du fenetre
        this.setVisible(false);
        setTitle("Message");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 200));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Sortir");
        JButton replayButton = new JButton("Rejouer");

        exitButton.addActionListener(e->sortir(this));
        replayButton.addActionListener(e->rejouer(this));

        buttonPanel.add(exitButton);
        buttonPanel.add(replayButton);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        add(messageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    public void regenerate(Frame t) {
        //fonction pour regénerer une fenetre principale
        t=new Frame();
    }
    public void sortir(MessageFrame f){
        //si on clique sur sortir: on ferme tous les fenetres(principale et message)
        f.principale.dispose();
        f.dispose();
    }

    public void rejouer(MessageFrame f){
        //si on clique sur rejouer: on ferme la fenetre de message
       f.principale.dispose();
       //puis on regenére la fenetre principale
        regenerate(f.principale);
        //on ferme la fenetre principale ancienne
        f.dispose();

    }



}
