import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.lang.Thread;
import java.io.*;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics2D;
import java.io.File;
import javax.swing.filechooser.FileFilter;



 public class Main
{
    public static void main(String[] args) 
    {


        // Définition de la frame
        JFrame frame = new JFrame("Application Dessin Vectoriel");       


       

          
        // Définir le menu principal
        JMenuBar menu = new JMenuBar();
        menu.setOpaque(true);
        JMenu file = new JMenu("Fichier");
        JMenu edit = new JMenu("Edition");
        JMenu help = new JMenu("Aide");
         


           // Définir le sous-menu pour Fichier
            Icon icon_new=new ImageIcon("new.png");
          JMenuItem newf = new JMenuItem("Nouveau dessin", icon_new);
          newf.setHorizontalTextPosition(SwingConstants.RIGHT);

          //Quand Nouveau dessin est cliqué, on ferme la fenêtre ouverte et on ouvre une nouvelle pour dessiner
           newf.addActionListener(e -> {
                 frame.dispose();
                 frame.setVisible(true);
               
           }
           );


            Icon icon_open=new ImageIcon("open.png");
           JMenuItem open = new JMenuItem("Ouvrir un dessin", icon_open);
            open.setHorizontalTextPosition(SwingConstants.RIGHT);

     //ajout d'une action; lorsque Ouvrir un dessin est cliquer une fenêtre de choix apparaît
            open.addActionListener(new ActionListener() {
                  
                  public void actionPerformed(ActionEvent e) {
                    //on ouvre un JFileChooser pour permettre à l'utilisateur de choisir le fichier à ouvrir
                         JFileChooser fc = new JFileChooser();
                     // affichage du dialog et test si le bouton ok est pressé 
                     if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
                       try {
                               //demande au système d'ouvrir le fichier précédemment séléctionné 
                                   Desktop.getDesktop().open(fc.getSelectedFile());
                             } catch (IOException e1) {
                                    e1.printStackTrace();
                             }
            }      
           });




           Icon icon_save=new ImageIcon("saving.png");
           JMenuItem save = new JMenuItem("Enregistrer le dessin", icon_save);
            save.setHorizontalTextPosition(SwingConstants.RIGHT);
            save.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ouv){

            //ajout d'une action; lorsque Enregistrer le dessin est cliquer une fenêtre de choix apparait 
            String sb = "Enregitré";
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("/home/me/Documents"));
    int retrival = chooser.showSaveDialog(null);
    if (retrival == JFileChooser.APPROVE_OPTION) {
        try {
            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".png");
            fw.write(sb.toString());
             fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
        }
    });









          // Définir le sous-menu pour Aide
              Icon icon_help=new ImageIcon("help.png");
          JMenuItem aide = new JMenuItem("Besoin d'aide", icon_help);
          aide.setHorizontalTextPosition(SwingConstants.RIGHT);

            //Quand Aide est cliqué, un message d'aide apparaît
                aide.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    JFrame frame3=new JFrame("Aide du site");
                    frame3.setBounds(200,200,700,100);
                    frame3.setVisible(true);
                    JTextField t1;
                    t1=new JTextField("Si vous recontrez un problème sur notre site de dessin vectoriel, rendez-vous sur notre site : https://aide.fr");
                    frame3.add(t1);
                  }
               });

 
         file.add(newf);
         file.add(save);
         file.add(open);

         help.add(aide);
         
        menu.add(file);
        menu.add(edit);
        menu.add(help);
         
   
        frame.add(menu);
        frame.setJMenuBar(menu);
        frame.setSize(1400,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 




//création de la JToolBar haut
     JToolBar toolbar_haut = new JToolBar(JToolBar.HORIZONTAL);
     toolbar_haut.setFloatable(false);
     toolbar_haut.setRollover(true); 
     toolbar_haut.setBackground(new Color(167,199,231));

      //creation du bouton Formes
        Icon icon=new ImageIcon("formes.png");
      JButton btn2 = new JButton("Formes", icon);
      btn2.setHorizontalTextPosition(SwingConstants.RIGHT);
      toolbar_haut.add(btn2);
      toolbar_haut.addSeparator();
      frame.add(toolbar_haut, BorderLayout.NORTH);

      //Creation du bouton Dessiner
        Icon icon_dessin=new ImageIcon("pinceau.png");
      JButton dessin = new JButton("Dessiner", icon_dessin);
       toolbar_haut.add(dessin);
         toolbar_haut.addSeparator();
      dessin.setHorizontalTextPosition(SwingConstants.RIGHT);

      // ouvrir une page de dessin lorsqu'on clique sur le bouton dessin 
        dessin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==dessin){
                    new dessiner();
                }
            }
        });

        //creation du bouton Supprimer 
        Icon icon_supp=new ImageIcon("supprimer.png");
        JButton supprimer = new JButton ("Supprimer", icon_supp);
     
        supprimer.setHorizontalTextPosition(SwingConstants.RIGHT);

        //supprimer l'élément quand le bouton est cliqué
        supprimer.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent supp){
      
             frame.getContentPane().removeAll();
             frame.repaint();
          }
          
        });


      //creation du bouton Couleur 
      Icon icon_coul=new ImageIcon("couleurs1.png");
      JButton btn = new JButton("Couleurs ", icon_coul);
      btn.setHorizontalTextPosition(SwingConstants.RIGHT);

       //on crée un événement sur le bouton Couleur, dès qu'il est cliqué on affiche le panel de choix de couleurs

    btn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event)
        {    
              JColorChooser colorChooser = new JColorChooser();
              Color color = JColorChooser.showDialog(null,"Choix de couleur ", Color.RED); 
              Color couleur = colorChooser.getColor();

            }
        });


       //creation du bouton Ligne
      Icon icon_bord=new ImageIcon("bordure.png");
      JButton bord = new JButton("Épaisseur", icon_bord);
      bord.setHorizontalTextPosition(SwingConstants.RIGHT);

         //on crée un événement sur le bouton Couleur, dès qu'il est cliqué on affiche le panel de choix de couleurs

    bord.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event)
        {  
           JOptionPane jop = new JOptionPane();
           String epaisseur = jop.showInputDialog(null, "Veuillez choisir une épaisseur", "Épaisseur", JOptionPane.QUESTION_MESSAGE);
            }
        });




   

        //création de la JToolBar du côté droit 
        JToolBar toolbar_droit = new JToolBar(JToolBar.VERTICAL);
        toolbar_droit.setFloatable(false);
        toolbar_droit.setRollover(true);
        toolbar_droit.add(btn);
        toolbar_droit.addSeparator();
        toolbar_droit.add(bord);
        frame.add(toolbar_droit, BorderLayout.EAST);
      



     //on ajoute un événement au bouton, lorsqu'il est cliqué une nouvelle fenêtre apparaît pour le choix de forme du dessin
      btn2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae)
        {

            JFrame frame2=new JFrame("Choix de la forme");
            frame2.setBounds(400,300,400,200);
            


            //je crée un Panel pour le choix de formes du dessin avec des boutons pour chaque choix
              Panel c = new Panel();
             c.setLayout(new GridLayout(2,4,3,3));
             c.setBackground(new Color(167,199,231));

             //boutons pour chaque choix
             Icon icon_ligne=new ImageIcon("line-icon.png");
             JButton ligne = new JButton("Ligne", icon_ligne);
             ligne.setVerticalTextPosition(SwingConstants.TOP);
             c.add(ligne);

             //Dès que le bouton ligne est appuyé une ligne est dessiné 
              ligne.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent ea)
                  {
                       frame.getContentPane().add(new DrawMyLine());
                        toolbar_haut.add(supprimer);

                  }
               });


             Icon icon_carre=new ImageIcon("carre.png");
             JButton carre = new JButton("Carré", icon_carre);
             carre.setVerticalTextPosition(SwingConstants.TOP);
             c.add(carre);

             //Dès que le bouton ligne est appuyé une ligne est dessiné 
              carre.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent eea)
                  {
                       frame.getContentPane().add(new DrawMyCarre());
                        toolbar_haut.add(supprimer);
                  }
               });



            Icon icon_rect=new ImageIcon("rect.png");
            JButton rect = new JButton("Rectangle", icon_rect);
            rect.setVerticalTextPosition(SwingConstants.TOP);
             c.add(rect);

             //Dès que le bouton ligne est appuyé une ligne est dessiné 
              rect.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent eea)
                  {
                       frame.getContentPane().add(new DrawMyRect());
                        toolbar_haut.add(supprimer);
                      
                  }
               });

              Icon icon_triangle=new ImageIcon("triangle.png");
             JButton triangle = new JButton("Triangle", icon_triangle);
             triangle.setVerticalTextPosition(SwingConstants.TOP);
             c.add(triangle);

             triangle.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent eea)
                  {
                       frame.getContentPane().add(new DrawMyTriangle());
                        toolbar_haut.add(supprimer);

                  }
               });

             Icon icon_cercle=new ImageIcon("circle.png");
             JButton cercle = new JButton("Cercle", icon_cercle);
             cercle.setVerticalTextPosition(SwingConstants.TOP);
             c.add(cercle);

             //Dès que le bouton ligne est appuyé une ligne est dessiné 
              cercle.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent aae)
                  {    
                        frame.getContentPane().add(new DrawMyCircle());
                        toolbar_haut.add(supprimer);
                  }
               });


             //j'ajoute mon Panel c de choix à ma frame2 et le rend visible
              frame2.add(c);
              frame2.setVisible(true);
                 c.setVisible(true);
              
        }
      });
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                frame.setVisible(true);   

    }

}


//class qui permet de dessiner au stylo 

class dessiner extends JFrame implements MouseMotionListener {
        dessiner(){
            super("Dessin au stylo");
            addMouseMotionListener(this);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pack();
            setSize(500,500);
            setLayout(null);
            setVisible(true);
             
        }
        JColorChooser colorChooser = new JColorChooser();
        Color couleur = JColorChooser.showDialog(null,"Choix de couleur ", Color.RED);
    
        public void mouseDragged(java.awt.event.MouseEvent evt){
            
            Graphics g=getGraphics();
            g.setColor(couleur);
            g.fillOval(evt.getX(), evt.getY(), 10, 10);
        }
    
        public void mouseMoved(MouseEvent e) {
            
        }   
       
        public static void main(String[]args){
             
            new dessiner();
        }
    }