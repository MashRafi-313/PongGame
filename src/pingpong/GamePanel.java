import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import pingpong.Ball;
import pingpong.Paddle;
import pingpong.Score;



public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*(5/9));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1,paddle2;
    Ball ball;
    Score score;
            
    GamePanel(){
        
    }
}
