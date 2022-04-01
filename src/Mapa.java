import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Mapa extends JFrame implements ActionListener{
   public ArrayList<JButton> botonAgente = new ArrayList<JButton>();
   public ArrayList<Agente> agentes = new ArrayList<Agente>();
   public ArrayList<Recursos> recursos = new ArrayList<Recursos>();
   public ArrayList<Amenaza> amenazas = new ArrayList<Amenaza>();
   public ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
   public Base base;

   final int CASILLAS = 20;
   final int WIDTH = 1000;
   final int HEIGHT = 820;
   
   Agente[][] matrix = new Agente[4][4];

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
      //gameArea.setPreferredSize(new Dimension(500, 500));
      //gameArea.findComponentAt(x, y) FIND COMPONENT PUEDE SERVIR PARA ENCONTRAR UN BOTON
      gameArea.add(boton);
      gameArea.setBackground(Color.darkGray);

      spawnAgents();
      printArray();

      this.setTitle("Simulacion Enjambre");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setSize(1100, 1000);
      this.setResizable(true);
      this.add(gameArea, 0, 0);
      this.add(move);
        
   }

   public void spawnAgents() {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;

      for(int i = 0; i < 5; i++)
         agentes.add(i, new Recolectores("R" + (i + 1), new Random().nextInt(20), new Random().nextInt(20)));
      
      for(int i = 5; i < 10; i++)
         agentes.add(i, new Defensores("D" + (i - 4), new Random().nextInt(20), new Random().nextInt(20)));
      
      for(int i = 0; i < agentes.size(); i++) {
         JButton agenteBoton = new JButton();
         
         agenteBoton.setText(agentes.get(i).name);
         agenteBoton.setBounds(agentes.get(i).xPos * xSize, agentes.get(i).yPos * ySize, xSize, ySize);
         agenteBoton.setBackground(Color.RED);
         agenteBoton.addActionListener(this);

         botonAgente.add(agenteBoton);
         gameArea.add(agenteBoton);
      }
   }

   public void spawnObjects() {
      int xSize = gameArea.getWidth() / CASILLAS;
      int ySize = gameArea.getHeight() / CASILLAS;
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(move)) {
         for(int i = 0; i < agentes.size(); i++) {
            agentes.get(i).move();
         }
      }
   }

   public void printArray() {
      for(int i = 0; i < agentes.size(); i++) {
         System.out.println(agentes.get(i).name + " X: " + agentes.get(i).xPos +
         " Y: " + agentes.get(i).yPos);
      }
   }
}