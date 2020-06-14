package BeatBox;

import javax.swing.*;
import java.awt.*;

public class MyPanelMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

//        frame.getContentPane().add(BorderLayout.CENTER, new MyDrawPanel());
//        frame.getContentPane().add(BorderLayout.CENTER, new MyImagePanel());
//        frame.getContentPane().add(BorderLayout.CENTER, new MyRandomGradPanel());
        frame.getContentPane().add(BorderLayout.CENTER, new MyBlackPanel());
    }
}

class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(20, 50, 100, 100);
    }
}

class MyImagePanel extends JPanel {
    public void paintComponent(Graphics g) {
        Image image = new ImageIcon("me.jpeg").getImage();
        g.drawImage(image, 3, 4, this);
    }
}

class MyRandomGradPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color startColor = new Color(red, green, blue);

        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        Color endColor = new Color(red, green, blue);

        GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 100);
    }
}

class MyBlackPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.fillRect(0,0,this.getWidth(), this.getHeight());

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color ovalColor = new Color(red, green, blue);
        g2d.setColor(ovalColor);
        g2d.fillOval(0,0,100,100);

        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        ovalColor = new Color(red, green, blue);
        g2d.setColor(ovalColor);
        g2d.draw3DRect(0,0,100,100,true);
    }
}