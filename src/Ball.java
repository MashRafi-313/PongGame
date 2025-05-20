import java.awt.*;
import java.util.*;

class CircularBall extends Rectangle {
    CircularBall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }
}

public class Ball extends CircularBall {
    Random random;
    int initialSpeed = 2;
    private int xVelocity;
    private int yVelocity;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        // 0 for left 1 for right
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) randomXDirection--;
        setXVelocity(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) randomYDirection--;
        setYVelocity(randomYDirection * initialSpeed);

    }

    public void setXVelocity(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setYVelocity(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

}
