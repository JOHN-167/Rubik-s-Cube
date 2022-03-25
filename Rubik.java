import javax.swing.JFrame; import javax.swing.JPanel;
import javax.swing.JComponent; import javax.swing.Timer;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.JButton; import java.awt.Graphics;
import java.awt.Graphics2D; import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double; import java.util.*;
import java.awt.Color; import java.awt.Font;
import java.awt.event.MouseListener; import java.awt.event.MouseEvent;
import java.awt.event.KeyListener; import java.awt.event.KeyEvent;
import java.awt.event.ActionListener; import java.awt.event.ActionEvent;

//Consider using abstraction to define color for cubes


public class Rubik extends JFrame {

    public Rubik() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        // setResizable(false);
        
        add(new Cube());
		   

    }

    public static void main(String[] args) {
        Rubik cube = new Rubik();
        cube.setVisible(true);
    }
}

class Cube extends JPanel {

    protected Color[][] colors = new Color[6][9];
    protected Color[] colorContruct = new Color[]{
	Color.RED, Color.BLUE, Color.ORANGE,
	Color.GREEN, Color.WHITE, Color.YELLOW};

    public Cube() {
        setBackground(Color.GRAY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double startPosX = Math.min(getHeight(), getWidth())/12;
        double length = startPosX/Math.cos(Math.PI/6);
        double startPosY = 3*startPosX+length/2;
        LeftSide left = new LeftSide(startPosX, startPosY+5*length/2, length, Color.BLUE);
        RightSide right = new RightSide(4*startPosX, startPosY+4*length, length, Color.BLACK);
        TopSide top = new TopSide(startPosX, startPosY+length/2, length, Color.BLACK);
        top.paintComponent(g);
        left.paintComponent(g);
        right.paintComponent(g);
    }
}

class LeftSide extends JComponent {
    protected double length, X, Y;
    protected Color color;

    public LeftSide(double X, double Y, double length, Color color){
        this.length = length; this.color = color;
        this.X = X; this.Y = Y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (double i = 0; i < 3; i++) {
            double TLY = Y + length*Math.sin(Math.PI/6)*i, TLX = X + length*Math.cos(Math.PI/6)*i;
            for (double j = 0; j < 3; j++) {
                ParaLeft side = new ParaLeft(TLX,TLY,length,color);
                side.paintComponent(g);
                TLY -= length;
            }
        }
    }
}

class RightSide extends JComponent {
    protected double length, X, Y;
    protected Color color;
    
    public RightSide(double X, double Y, double length, Color color){
        this.length = length; this.color = color;
        this.X = X; this.Y = Y;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (double i = 0; i < 3; i++) {
            double TLY = Y - length*Math.sin(Math.PI/6)*i, TLX = X + length*Math.cos(Math.PI/6)*i;
            for (double j = 0; j < 3; j++) {
                ParaRight right = new ParaRight(TLX,TLY,length,color);
                right.paintComponent(g);
                TLY -= length;
            }
        }
    }
}

class TopSide extends JComponent {
    protected double length, X, Y;
    protected Color color;

    public TopSide(double X, double Y, double length, Color color){
        this.length = length; this.color = color;
        this.X = X; this.Y = Y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (double i = 0; i < 3; i++) {
            double TLY = Y + Math.sin(Math.PI/6)*length*i, TLX = X + Math.cos(Math.PI/6)*length*i;
            for (double j = 0; j < 3; j++) {
                ParaTop top = new ParaTop(TLX,TLY,length,Color.RED);
                top.paintComponent(g);
                TLY -= length*Math.sin(Math.PI/6);
                TLX += length*Math.cos(Math.PI/6);
            }
        }
    }
}

class ParaLeft extends JComponent {

    protected Color color;
    protected Path2D.Double parallelogram;

    public ParaLeft(double X1, double Y1, double length, Color color){
        double incX = length*Math.cos(Math.PI/6), incY = length*Math.sin(Math.PI/6);
        double X2 = X1 + incX, Y2 = Y1 + incY; double X3 = X2, Y3 = Y2 + length;
        double X4 = X1, Y4 = Y3 - incY; this.color = color;
        parallelogram = new Path2D.Double();
        parallelogram.moveTo(X1,Y1); parallelogram.lineTo(X2,Y2);
        parallelogram.lineTo(X3,Y3); parallelogram.lineTo(X4,Y4);
        parallelogram.closePath();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.color); g2d.fill(parallelogram);
        g2d.setColor(Color.WHITE); g2d.setStroke(new java.awt.BasicStroke(1));
        g2d.draw(parallelogram);
    }
}

class ParaRight extends JComponent {

    protected Color color;
    protected Path2D.Double parallelogram;

    public ParaRight(double X1, double Y1, double length, Color color){
        double incX = length*Math.cos(Math.PI/6), incY = length*Math.sin(Math.PI/6);
        double X2 = X1 + incX, Y2 = Y1 - incY; double X3 = X2, Y3 = Y2 + length;
        double X4 = X1, Y4 = Y3 + incY; this.color = color;
        parallelogram = new Path2D.Double();
        parallelogram.moveTo(X1,Y1); parallelogram.lineTo(X2,Y2);
        parallelogram.lineTo(X3,Y3); parallelogram.lineTo(X4,Y4);
        parallelogram.closePath();
    }    

    public void paintComponent(Graphics g){
        super.paintComponent(g); Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.color); g2d.fill(parallelogram);
        g2d.setColor(Color.WHITE); g2d.setStroke(new java.awt.BasicStroke(1));
        g2d.draw(parallelogram);
    }    
}    

class ParaTop extends JComponent {

    protected Color color;
    protected Path2D.Double parallelogram;

    public ParaTop(double X1, double Y1, double length, Color color) {
        double incX = length*Math.cos(Math.PI/6), incY = length*Math.sin(Math.PI/6);
        double X2 = X1 + incX, Y2 = Y1 + incY; double X3 = X2 + incX, Y3 = Y1;
        double X4 = X2, Y4 = Y1 - incY; this.color = color;
        parallelogram = new Path2D.Double();
        parallelogram.moveTo(X1,Y1); parallelogram.lineTo(X2,Y2);
        parallelogram.lineTo(X3,Y3); parallelogram.lineTo(X4,Y4);
        parallelogram.closePath();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.color); g2d.fill(parallelogram);
        g2d.setColor(Color.WHITE); g2d.setStroke(new java.awt.BasicStroke(1));
        g2d.draw(parallelogram);
    }
}
 
