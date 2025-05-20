import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    RectangularPaddle paddle1, paddle2;
    Ball ball;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.drawScore(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        //bounce ball upper
        if (ball.y <= 0) {
            int yVelocityOfBall = ball.getYVelocity();
            ball.setYVelocity(-yVelocityOfBall);
        }
        //bounce ball down
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            int yVelocityOfBall = ball.getYVelocity();
            ball.setYVelocity(-yVelocityOfBall);
        }
        //collision  between ball & paddle1
        if (ball.intersects((Rectangle) paddle1)) {
            int xVelocityOfBall = ball.getXVelocity();
            int yVelocityOfBall = ball.getYVelocity();

            xVelocityOfBall = Math.abs(xVelocityOfBall);
            xVelocityOfBall++;//ball moving left previous now right move
            ball.setXVelocity(xVelocityOfBall);//speed increase after collision
            if (yVelocityOfBall > 0) {
                //ball moving down
                yVelocityOfBall++;
                ball.setYVelocity(yVelocityOfBall);//down has positive point of y
            } else {
                //ball moving up
                yVelocityOfBall++;
                ball.setYVelocity(yVelocityOfBall);  //up has negative point of y
            }

            ball.setXVelocity(xVelocityOfBall);
            ball.setYVelocity(yVelocityOfBall);
        }

        //collision  between ball & paddle2
        if (ball.intersects((Rectangle) paddle2)) {
            //ball moving right previous now left move
            int xVelocityOfBall = -ball.getXVelocity();
            xVelocityOfBall--;
            ball.setXVelocity(xVelocityOfBall);

            int yVelocityOfBall = -ball.getYVelocity();//speed increase after collision
            if (yVelocityOfBall > 0) {
                //ball moving down
                yVelocityOfBall++;//down has positive point of y
            } else//ball moving up
                yVelocityOfBall--;//up has negative point of y
            ball.setXVelocity(xVelocityOfBall);
            ball.setYVelocity(yVelocityOfBall);
        }


        //if paddle move upwards & downwards
        if (paddle1.getYDirection() <= 0) paddle1.setYDirection(0);//upper point reached
        if (paddle1.getYDirection() >= (GAME_HEIGHT - PADDLE_HEIGHT))//lower point reached
            paddle1.setYDirection(GAME_HEIGHT - PADDLE_HEIGHT);
        if (paddle2.getYDirection() <= 0) paddle2.setYDirection(0);
        if (paddle2.getYDirection() >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.setYDirection(GAME_HEIGHT - PADDLE_HEIGHT);


        // give player point
        if (ball.x <= 0) {// player 1 misses the shot
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2 : " + score.player2);
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) { //player 2 misses the shot
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1:" + score.player1);
        }
    }

    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
                //System.out.println("TEST");
            }
        }
    }

    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
