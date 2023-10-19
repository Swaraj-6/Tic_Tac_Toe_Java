/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.*;
import java.time.*;
import java.time.format.*;
/**
 *
 * @author Swaraj kakade
 */
public class Game extends JFrame implements ActionListener {
    JLabel heading, clockLabel;
    JPanel mainPanel;
    private final JButton[] btns = new JButton[9];
    private final Font font = new Font("", Font.BOLD,40);
    
    private int[] gameChances = {2,2,2,2,2,2,2,2,2};
    private int activePlayer = 0;
    private final int[][] wps = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    private int winner = 2;
    private boolean gameOver=false;
    
    
    public Game(){
        System.out.println("Game instance created.");
        setTitle("My Tic Tac Toe Game.");
        setSize(750, 650);
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/Title.png"));
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createGUI();
        setVisible(true);
    }
    
    private void createGUI(){
        this.getContentPane().setBackground(Color.decode("#687EFF"));
        
        this.setLayout(new BorderLayout());
        
        // North heading
        
        heading = new JLabel("Tic Tac Toe...");
//        heading.setIcon(new ImageIcon("src/img/Title.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.decode("#B6FFFA"));
        
        this.add(heading, BorderLayout.NORTH);
        
        // South heading
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.decode("#B6FFFA"));
        
        this.add(clockLabel, BorderLayout.SOUTH);
        
        // Making clock
        Thread t = new Thread(){
            public void run(){
                try{
                    while(true){
                        LocalDateTime currentDateTime = LocalDateTime.now();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
                        String formattedDateTime = currentDateTime.format(formatter);
                        clockLabel.setText(formattedDateTime);
                        
                        Thread.sleep(1000);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
        
        // Panel Section
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        
        for(int i=1; i<=9; i++){
            JButton btn = new JButton();
            
            btn.setBackground(Color.decode("#98E4FF"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton =(JButton)e.getSource();
        String nameStr = currentButton.getName();
        System.out.println(nameStr.trim());
        int name_idx = Integer.parseInt(nameStr.trim());
        
        if(gameOver){
            JOptionPane.showMessageDialog(this, "Game Over.");
            return;
        }
        
        
        if(gameChances[name_idx] == 2){
            if(activePlayer==1){
                currentButton.setIcon(new ImageIcon(getClass().getResource("/img/X.png")));
                gameChances[name_idx] = activePlayer;
                activePlayer=0;
            }else{
                currentButton.setIcon(new ImageIcon(getClass().getResource("/img/O.png")));
                gameChances[name_idx] = activePlayer;
                activePlayer=1;
            }
            
            // Winner checking
            for(int[] temp: wps){
            if((gameChances[temp[0]] == gameChances[temp[1]]) && (gameChances[temp[1]] == gameChances[temp[2]]) && (gameChances[temp[2]] != 2)){
                winner = gameChances[temp[2]] + 1;
                gameOver=true;
                JOptionPane.showMessageDialog(null, "Player "+ winner +" has won the game.");
                int i = JOptionPane.showConfirmDialog(this, "Do you wnt to play more ??");
                options(i);
                
                break;
            }
        }
            int c=0 ;
            for(int x:gameChances){
                if( x == 2){
                   c++;
                   break;
                }
            }
            if(c==0 && gameOver==false){
                JOptionPane.showMessageDialog(null, "It's draw...");
                int i = JOptionPane.showConfirmDialog(this, "Do you wnt to play more ??");
                options(i);
                gameOver = true;
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Position already occupied...");
        }
        
    }
    
    private void options(int i){
        if(i == 0){
            this.setVisible(false);
            new Game();
        }else if(i == 1){
            System.exit(0);
        }else{
                    
        }
    }
        
}
