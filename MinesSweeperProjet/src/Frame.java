import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Frame extends JFrame  {

    MessageFrame message;//fenetre de message(ganger ou perdre)
    int bombNumber=0;
    boolean lost=false;
    square[] labels = new square[90];//liste de tous les labels contenants les bouttons
    JButton[] buttons = new JButton[90];//liste de tous les bouttons
    ImageIcon bomb = new ImageIcon("bomb1.png");

    ImageIcon redflag = new ImageIcon("redflag.png");
    ArrayList<square> opened = new ArrayList<square>();//la liste des bouttons ouverts





//fenetre principale
    Frame() {



        int i = 0;
        for (i = 0; i < 90; i++) {

            //creation de chaque boutton
            JButton button = new JButton();


            button.setVisible(true);

            button.setBounds(0, 0, 100, 100);

            button.setBackground(Color.GRAY);
            //clique-droit sur le button ajout l'icone du drapeau
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON3) {
                        if(button.getIcon() == null) {
                            button.setIcon(redflag);
                        } else {
                            button.setIcon(null);
                        }
                    }
                }
            });

            buttons[i] = button;


            //creation de chaque label
            square label = new square(labels,i);


            label.setBounds((i % 10) * 100, (i / 10) * 100, 100, 100);

            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            label.setBackground(Color.white);

            label.setOpaque(true);


            label.add(buttons[i]);

            label.setVisible(true);

            //l'ajout du boutton au label
            label.b = buttons[i];


            //creation des labels bombés d'une maniere random
            Random rand = new Random();
            int randomInt = rand.nextInt(6) + 1;
            int finalI = i;
            if (randomInt == 1) {
                bombNumber++;
                label.isBombed = true;
                label.setIcon(bomb);
                buttons[i].addActionListener(e->lose(labels[finalI]));
            }

            else{

            buttons[i].addActionListener(e -> click(labels[finalI]));

            }

            labels[i] = label;
            this.add(labels[i]);


        }





        //configuration des voisins de chaque carreau
        for (i = 0; i <90 ; i++) {
            labels[i].setBoarders();
        }


        // text des labels non bombés (1,2,3,4,5)
        for (i=0;i<90;i++){

            if (!(labels[i].isBombed)){


                int bombCount=0;
                //compter le nombre des bombes dans les voisins de chaque carreau
                if(labels[i].up!=null &&labels[i].up.isBombed ) bombCount++;
                if(labels[i].left!=null &&labels[i].left.isBombed ) bombCount++;
                if(labels[i].right!=null &&labels[i].right.isBombed ) bombCount++;
                if(labels[i].down!=null &&labels[i].down.isBombed ) bombCount++;
                if(labels[i].upright!=null &&labels[i].upright.isBombed ) bombCount++;
                if(labels[i].upleft!=null &&labels[i].upleft.isBombed ) bombCount++;
                if(labels[i].downright!=null &&labels[i].downright.isBombed ) bombCount++;
                if(labels[i].downleft!=null &&labels[i].downleft.isBombed ) bombCount++;

                //ajouter le text au label (nombre de bombes des voisins)
                if(bombCount!=0){
                labels[i].setText(Integer. toString(bombCount));
                labels[i].setFont(new Font("",Font.BOLD,30));
                labels[i].setHorizontalAlignment(JLabel.CENTER);


            }
                else{
                labels[i].isZero=true;
                }

            }


        }
        //configuration de frame
        this.setTitle("Mine Sweeper Projet");
        this.setIconImage(bomb.getImage());
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setSize(1016, 938);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


//fonction de perdre
    public void lose(square l){
        if(!lost){
        l.b.setVisible(false);

        lost=true;
            for (int i = 0; i <90 ; i++) {
                if(labels[i].isBombed)labels[i].b.setVisible(false);
            }
            //apparition du fenetre message
            message = new MessageFrame("Vous avez perdu!");
            message.setVisible(true);
            message.principale=this;

    }
        }

//fonction de chaque carreau
    public void click(square l) {
        if(!(lost)){

        if (!(l.isZero)) {
            l.b.setVisible(false);

            l.isShown=true;
        }
        else{
            l.b.setVisible(false);

            //ouvrir tous les voisins de chaque button zero si on clique sur un button zero
            //button zero est un boutton qui n'admet auqune voisine bombée
            l.isShown=true;
            opened.add(l);
            for(square boarder:l.boarders){
                if(!(opened.contains(boarder)))click(boarder);

            }
        }

        int c=0;
        //compter tous les carreau ouverts
            for(int j=0;j<90;j++){
                if(labels[j].isShown && !labels[j].isBombed){
                    c++;
                }

            }
            //tous les carreaux non-bombés sont ouverts --> condition de gagne
            if(c+bombNumber==90) {
                //creation de fenetre de message
                message = new MessageFrame("Vous avez gagné!");
                message.setVisible(true);
                message.principale=this;

                }
            }
    }



}












