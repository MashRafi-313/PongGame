import java.awt.*;
import java.awt.event.*;

interface RectangularPaddle {
    void move();

    void draw(Graphics g);

    int getYDirection();

    void setYDirection(int yDirection);

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);
}

public class Paddle extends Rectangle implements RectangularPaddle {
    int id;
    int yVelocity;
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYVelocity(-speed);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYVelocity(speed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYVelocity(-speed);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYVelocity(speed);
                    move();
                }
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYVelocity(0);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYVelocity(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYVelocity(0);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYVelocity(0);
                    move();
                }
                break;
        }
    }

    public void setYVelocity(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y = y + yVelocity;
    }

    public int getYDirection() {
        return y;
    }

    public void setYDirection(int yDirection) {
        y = yDirection;
    }


    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);

        g.fillRect(x, y, width, height);
    }
}
