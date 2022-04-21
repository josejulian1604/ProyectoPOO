import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;

public class Mapa extends JFrame implements ActionListener{

   public ArrayList<JButton> botonesAgente = new ArrayList<JButton>();
   public ArrayList<JButton> botonesRecurso = new ArrayList<JButton>();
   public ArrayList<JButton> botonesAmenaza = new ArrayList<JButton>();
   public ArrayList<JButton> botonesObstaculo = new ArrayList<JButton>();
   public JButton botonBase = new JButton();

   public ArrayList<Agente> agentes = new ArrayList<Agente>();
   public ArrayList<Recursos> recursos = new ArrayList<Recursos>();
   public ArrayList<Amenaza> amenazas = new ArrayList<Amenaza>();
   public ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
   public Base base;

   final int CASILLAS = 40; //40
   final int WIDTH = 1000; // 1000
   final int HEIGHT = 840;
   final int OBJECTS = 7;

   public int X;
   public int Y;

   JPanel gameArea;
   JButton boton;
   JButton move;


   public Mapa() {

      this.setLayout(null);
      boton = new JButton();
      move = new JButton();
      move.addActionListener(this);
      gameArea = new JPanel();

      move.setBounds(1000, 0, 90, 50);
      move.setText("Move");
        
       
      gameArea.setLayout(null);
      gameArea.setSize(WIDTH, HEIGHT);
      gameArea.add(boton);
      gameArea.setBackground(Color.darkGray);

      
      spawnBase();
      spawnAgents();
      spawnObstaculos();
      spawnRecursos(OBJECTS);
      spawnAmenaza(OBJECTS);
      printArray();

      this.setTitle("Simulacion Enjambre");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setSize(1100, 1000);
      this.setResizable(false);
      this.add(gameArea, 0, 0);
      this.add(move);
        
   }

   // SPAWN METHODS ###################################################################################
   public void spawnBase() {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;

      base = new Base(0, 0);
      botonBase.setText("Base");
      botonBase.setBounds(base.getXPos() * xSize, base.getYPos() * ySize, xSize * 2, ySize * 2);
      botonBase.setBackground(Color.WHITE);

      gameArea.add(botonBase);
   }

   public void spawnAgents() {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;

      for(int i = 0; i < 5; i++) {
         positionAgenteTaken();
         agentes.add(i, new Recolectores("R" + (i + 1), this.X, this.Y));
      }
      
      for(int i = 5; i < 10; i++) {
         positionAgenteTaken();
         agentes.add(i, new Defensores("D" + (i - 4), this.X, this.Y));
      }
      
      for(int i = 0; i < agentes.size(); i++) {
         JButton agenteBoton = new JButton();
         
         agenteBoton.setText(agentes.get(i).name);
         agenteBoton.setBounds(agentes.get(i).xPos * xSize, agentes.get(i).yPos * ySize, xSize, ySize);
         if(i < 5)
            agenteBoton.setBackground(Color.GREEN);
         
         else
            agenteBoton.setBackground(Color.RED);

         botonesAgente.add(agenteBoton);
         gameArea.add(agenteBoton);
      }
   }

   public void spawnRecursos(int objects) {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;

      for(int i = 0; i < objects; i++) {
         positionRecursoTaken();
         JButton recursoBoton = new JButton();
         recursos.add(new Recursos(10, this.X, this.Y));

         recursoBoton.setText("" + i);//"" + recursos.get(i).getCantidad());
         recursoBoton.setBounds(recursos.get(i).getXPos() * xSize, recursos.get(i).getYPos() * ySize, xSize * 2, ySize * 2);
         recursoBoton.setBackground(Color.MAGENTA);
         
         botonesRecurso.add(recursoBoton);
         gameArea.add(recursoBoton);
         gameArea.repaint();
      }
   }

   public void spawnAmenaza(int objects) {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;

      for(int i = 0; i < objects; i++) {
         positionAmenazaTaken();
         JButton amenazaBoton = new JButton();
         amenazas.add(new Amenaza(10, this.X, this.Y));

         amenazaBoton.setText("A" + i);//amenazas.get(i).getVida());
         amenazaBoton.setBounds(amenazas.get(i).getXPos() * xSize, amenazas.get(i).getYPos() * ySize, xSize * 2, ySize * 2);
         amenazaBoton.setBackground(Color.ORANGE);
         amenazaBoton.setVisible(true);

         botonesAmenaza.add(amenazaBoton);
         gameArea.add(amenazaBoton);
         gameArea.revalidate();
         gameArea.repaint();
      }
   }

   public void spawnObstaculos() {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;

      for(int i = 0; i < OBJECTS; i++) {
         positionObstaculoTaken();
         JButton obstaculoBoton = new JButton();
         obstaculos.add(new Obstaculo(this.X, this.Y));

         obstaculoBoton.setBounds(obstaculos.get(i).getXPos() * xSize, obstaculos.get(i).getYPos() * ySize, xSize * 2, ySize * 2);
         obstaculoBoton.setBackground(Color.CYAN);

         botonesObstaculo.add(obstaculoBoton);
         gameArea.add(obstaculoBoton);
      }
   }

   // VERIFICATION METHODS ###################################################################################
   public void positionAgenteTaken() {
      int x = new Random().nextInt(CASILLAS);
      int y = new Random().nextInt(CASILLAS);

      for(int i = 0; i < base.getPositions().length; i++) {
         if(x == base.getPositions()[i][0] & y == base.getPositions()[i][1]) {
            positionAgenteTaken();
            return;
         }
      }

      for(int i = 0; i < agentes.size(); i++) {
         if(x == agentes.get(i).xPos & y == agentes.get(i).yPos) {
            positionAgenteTaken();
            return;
         }
      }
      this.X = x;
      this.Y = y;
   }

   public void positionObstaculoTaken() {
      int[][] array = new int[4][2];
      this.X = new Random().nextInt(CASILLAS - 1);
      this.Y = new Random().nextInt(CASILLAS - 1);

      array[0][0] = this.X;
      array[0][1] = this.Y;
      array[1][0] = this.X + 1;
      array[1][1] = this.Y;
      array[2][0] = this.X;
      array[2][1] = this.Y + 1;
      array[3][0] = this.X + 1;
      array[3][1] = this.Y + 1;

      for(int i = 0; i < base.getPositions().length; i++) {
         for(int j = 0; j < array.length; j++) {
            if(base.getPositions()[i][0] == array[j][0] & base.getPositions()[i][1] == array[j][1]) {
               positionRecursoTaken();
               return;
            }
         }
      }

      for(int i = 0; i < agentes.size(); i++) {
         for(int j = 0; j < array.length; j++) {
            if(array[j][0] == agentes.get(i).xPos & array[j][1] == agentes.get(i).yPos) {
               positionRecursoTaken();
               return;
            }
         }
      }

      for(int i = 0; i < obstaculos.size(); i++) {
         for(int j = 0; j < obstaculos.get(0).getPositions().length; j++) {
            for(int k = 0; k < array.length; k++) {
               if(obstaculos.get(i).getPositions()[j][0] == array[k][0] & obstaculos.get(i).getPositions()[j][1] == array[k][1]) {
                  positionObstaculoTaken();
                  return;
               }
            }
         }
      }
   }

   public void positionRecursoTaken() {
      positionObstaculoTaken();
      int[][] array = new int[4][2];

      array[0][0] = this.X;
      array[0][1] = this.Y;
      array[1][0] = this.X + 1;
      array[1][1] = this.Y;
      array[2][0] = this.X;
      array[2][1] = this.Y + 1;
      array[3][0] = this.X + 1;
      array[3][1] = this.Y + 1;

      for(int i = 0; i < recursos.size(); i++) {
         for(int j = 0; j < recursos.get(0).getPositions().length; j++) {
            for(int k = 0; k < array.length; k++) {
               if(recursos.get(i).getPositions()[j][0] == array[k][0] & recursos.get(i).getPositions()[j][1] == array[k][1]) {
                  positionRecursoTaken();
                  return;
               }
            }
         }
      }
   }

   public void positionAmenazaTaken() {
      positionRecursoTaken();
      int[][] array = new int[4][2];
      array[0][0] = this.X;
      array[0][1] = this.Y;
      array[1][0] = this.X + 1;
      array[1][1] = this.Y;
      array[2][0] = this.X;
      array[2][1] = this.Y + 1;
      array[3][0] = this.X + 1;
      array[3][1] = this.Y + 1;

      for(int i = 0; i < amenazas.size(); i++) {
         for(int j = 0; j < amenazas.get(0).getPositions().length; j++) {
            for(int k = 0; k < array.length; k++) {
               if(amenazas.get(i).getPositions()[j][0] == array[k][0] & amenazas.get(i).getPositions()[j][1] == array[k][1]) {
                  positionAmenazaTaken();
                  return;
               }
            }
         }
      }
   }

   //MOVE METHODS#######################################################################

   public void moveAgents() {
      int agenteX;
      int agenteY;
      int botonX;
      int botonY;
      for(int i = 0; i < agentes.size(); i++) {
         int agenteX1 = agentes.get(i).xPos;
         int agenteY1 = agentes.get(i).yPos;
         agentes.get(i).move(agentes, recursos, amenazas, obstaculos, base);
         agenteX = agentes.get(i).xPos;
         agenteY = agentes.get(i).yPos;
         botonX = botonesAgente.get(i).getX();
         botonY = botonesAgente.get(i).getY();

         if(agenteX > agenteX1)
            botonesAgente.get(i).setLocation(botonX + WIDTH / CASILLAS, botonY);

         else if(agenteX < agenteX1)
            botonesAgente.get(i).setLocation(botonX - WIDTH / CASILLAS, botonY);
         
         else if(agenteY > agenteY1)
            botonesAgente.get(i).setLocation(botonX, botonY + HEIGHT / CASILLAS);
         
         else if(agenteY < agenteY1)
            botonesAgente.get(i).setLocation(botonX, botonY - HEIGHT / CASILLAS);
      }
   }

   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(move)) {
         moveAgents();
         deleteObjects();
         refreshTexts();
      }
   }

   void setResourceNull(Recursos recurso) {
      for(int i = 0; i < agentes.size(); i++) {
         if(agentes.get(i).recurso == recurso)
            agentes.get(i).recurso = null;
      }
   }

   public void deleteObjects() {
      for(int i = 0; i < OBJECTS; i++) {
         if(recursos.get(i).getCantidad() <= 0) {
            setResourceNull(recursos.get(i));
            recursos.remove(i);
            gameArea.remove(botonesRecurso.remove(i));
            spawnButton(true);
            gameArea.repaint();
         }
         if(amenazas.get(i).getVida() <= 0) {
            amenazas.remove(i);
            gameArea.remove(botonesAmenaza.remove(i));
            spawnButton(false);
            printArray();
         }
      }
   }

   public void spawnButton(boolean recurso) {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;
      positionAmenazaTaken();
      if(!recurso) {
         Amenaza amenaza = new Amenaza(10, this.X, this.Y);
         JButton amenazaBoton = new JButton();
         amenazas.add(amenaza);

         amenazaBoton.setText("" + amenaza.getVida());
         amenazaBoton.setBounds(amenaza.getXPos() * xSize, amenaza.getYPos() * ySize, xSize * 2, ySize * 2);
         amenazaBoton.setBackground(Color.ORANGE);
         amenazaBoton.setVisible(true);

         botonesAmenaza.add(amenazaBoton);
         gameArea.add(amenazaBoton);
         gameArea.repaint();
      }
      else {
         Recursos resource = new Recursos(10, this.X, this.Y);
         JButton recursoBoton = new JButton();
         recursos.add(resource);

         recursoBoton.setText("" + resource.getCantidad());
         recursoBoton.setBounds(resource.getXPos() * xSize, resource.getYPos() * ySize, xSize * 2, ySize * 2);
         recursoBoton.setBackground(Color.MAGENTA);
         recursoBoton.setVisible(true);

         botonesRecurso.add(recursoBoton);
         gameArea.add(recursoBoton);
         gameArea.repaint();
      }
   }

   public void refreshTexts() {
      for(int i = 0; i < OBJECTS; i++) {
         botonesRecurso.get(i).setText("" + recursos.get(i).getCantidad());
         botonesAmenaza.get(i).setText("" + amenazas.get(i).getVida());
      }
      botonBase.setText("" + base.getRecursos());
   }

   public void printArray() {
      for(int i = 0; i < agentes.size(); i++) {
         System.out.println(agentes.get(i).name + " X: " + agentes.get(i).xPos +
         " Y: " + agentes.get(i).yPos);  
      }
      for(int i = 0; i < recursos.size(); i++) {
         System.out.print("RE" + i + ": [ ");
         for(int j = 0; j < recursos.get(i).getPositions().length; j++) {
            System.out.print("[ " + recursos.get(i).getPositions()[j][0] + ", " + recursos.get(i).getPositions()[j][1] + " ], ");
         }
         System.out.print(" ]");
         System.out.println("");
      }
      for(int i = 0; i < amenazas.size(); i++) {
         System.out.print("A" + i + ": [ ");
         for(int j = 0; j < amenazas.get(i).getPositions().length; j++) {
            System.out.print("[ " + amenazas.get(i).getPositions()[j][0] + ", " + amenazas.get(i).getPositions()[j][1] + " ], ");
         }
         System.out.print(" ]");
         System.out.println("");
      }
   }

   public void printButtons() {
      Component[] x = gameArea.getComponents();
      for(int i = 0; i < x.length; i++) {
         if(x[i] instanceof JButton) {
            System.out.println(((JButton)x[i]).getText());
         }
      }
   }
}
