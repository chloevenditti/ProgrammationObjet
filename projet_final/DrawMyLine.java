import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class DrawMyLine extends JPanel implements MouseMotionListener
{
  
  
//permet le déplacement de la forme avec la souris
  private int mA,mB;

  public DrawMyLine(){
    addMouseMotionListener(this);
    setVisible(true);
  }

  

  public void mouseMoved(MouseEvent me){
  }

  public void mouseDragged(MouseEvent me){
    mouseRelease(me);
  }


  public void mousePressed(MouseEvent me){ 
     this.repaint();
  }

    
   public void mouseRelease(MouseEvent me){ 
     this.mA = (int) me.getPoint().getX();
     this.mB = (int) me.getPoint().getY();
    this.repaint();
  }

      
//permet à l'utilisateur de modifier la couleur de la forme
      JColorChooser colorChooser = new JColorChooser();
      Color couleur = JColorChooser.showDialog(null,"Choix de couleur ", Color.RED);

//permet à l'utilisateur de modifier les paramètres de taille de la forme

       //épaisseur
      JOptionPane jop = new JOptionPane();
      //je convertie le String du JOptionPane initial en un int pour nos valeurs
      int epaisseur = Integer.parseInt(jop.showInputDialog(null, "Veuillez choisir une épaisseur", "Épaisseur", JOptionPane.QUESTION_MESSAGE));

      //hauteur
      JOptionPane jop2 = new JOptionPane();
      //je convertie le String du JOptionPane initial en un int pour nos valeurs
      int hauteur = Integer.parseInt(jop2.showInputDialog(null, "Veuillez choisir une hauteur", "Hauteur", JOptionPane.QUESTION_MESSAGE));


  public void paint(Graphics g){
   
    g.setColor(couleur);
    g.drawLine(epaisseur, hauteur, mA, mB);
      }
 
}