import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import pingpong.GamePanel;


public class GameFrame extends JFrame{
    GamePanel panel;
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void newBall(){
        
    }public void newPaddles(){
        
    }public void paint(Graphics g){
        
    }public void draw(Graphics g){
        
    }public void move(){
        
    }public void checkCollision(){
        
    }
    public void run(){
        
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            
        }
        public void keyReleased(KeyEvent e){
            
        }
    }
}
