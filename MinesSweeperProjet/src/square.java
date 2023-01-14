import javax.swing.*;
import java.util.ArrayList;

//calss d'un carreau (chaque label contenant un button dans le jeu)
public class square extends JLabel {


    public boolean isShown=false;
    boolean isBombed=false;
    boolean isZero=false;

    JButton b;
    square[] tableau;
    int indice;

    //les voisins d'un carreau
    square up=null;
    square right=null;
    square left=null;
    square down=null;

    square upright=null;

    square upleft=null;

    square downright=null;

    square downleft=null;

    ArrayList<square> boarders = new ArrayList<square>();


    square(square[] tableau,int indice) {
        //chaque carreau connait la liste des carreau et son indice dans la liste
        //pour faciliter trouver ses voisins
        this.tableau = tableau;
        this.indice = indice;

    }



//fonction pour configurer tous les voisins d'un tableau
    void setBoarders(){

        if(indice>9) {
            this.up = tableau[indice - 10];
            this.boarders.add(this.up);
            if(indice%10!=9){this.upright=tableau[indice - 9];
                this.boarders.add(this.upright);
            }
            if(indice%10!=0){this.upleft=tableau[indice - 11];
                this.boarders.add(this.upleft);}
        }
        if(indice%10!=9){
            this.right=tableau[indice + 1];
            this.boarders.add(this.right);
        }

        if(indice<80){
            this.down=tableau[indice + 10];
            this.boarders.add(this.down);
            if(indice%10!=9){
                this.downright=tableau[indice + 11];
                this.boarders.add(this.downright);
            }
            if(indice%10!=0){
                this.downleft=tableau[indice + 9];
                this.boarders.add(this.downleft);
            }

        }

        if (indice%10!=0){
            this.left=tableau[indice - 1];
            this.boarders.add(this.left);
        }


    }


}
