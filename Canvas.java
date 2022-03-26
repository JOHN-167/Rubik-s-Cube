import javax.swing.JPanel; import javax.swing.JComponent;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.Graphics; import java.awt.Graphics2D; 
import java.awt.geom.Path2D; import java.awt.geom.Path2D.Double;
import java.util.*;
import java.awt.Color; import java.awt.Font;
import java.awt.event.MouseListener; import java.awt.event.MouseEvent;
import java.awt.event.KeyListener; import java.awt.event.KeyEvent;

public class Canvas extends JPanel {

    protected Color[][] colors = new Color[6][9];
    protected Color[] colorContruct = new Color[]{
	Color.RED, Color.BLUE, new Color(255,100,0),
	Color.GREEN, Color.WHITE, Color.YELLOW};

    public Canvas() {
        setBackground(Color.GRAY);
        for (int i = 0; i < 6; i++)
            Arrays.fill(colors[i],colorContruct[i]);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double a = Math.min(getWidth(),getHeight()*Math.cos(Math.PI/6))/4;
        double length = a/Math.cos(Math.PI/6);
        LeftSide left = new LeftSide(a, 11*length/6, length/3, colors[0]);
        RightSide right = new RightSide(2*a, 7*length/3, length/3, colors[1]);
        TopSide top = new TopSide(5*a/3, 3*length/2, length/3, colors[4]);
        top.paintComponent(g);
        left.paintComponent(g);
        right.paintComponent(g);
    }

    public void bottomRight(){
        for (int i = 6; i < 9; i++){
            Color tmp = colors[3][i];
            for (int j = 2; j >= 0; j--)
                colors[j+1][i] = colors[j][i];
            colors[0][i] = tmp;
        }
        Color tmp = colors[5][0];
        colors[5][0] = colors[5][6];
        colors[5][6] = colors[5][8];
        colors[5][8] = colors[5][2];
        colors[5][2] = tmp;
        tmp = colors[5][1];
        colors[5][1] = colors[5][3];
        colors[5][3] = colors[5][7];
        colors[5][7] = colors[5][5];
        colors[5][5] = tmp;
        repaint();
    }
    public void bottomLeft(){
        for (int i = 6; i < 9; i++){
            Color tmp = colors[0][i];
            for (int j = 0; j < 4; j++)
                colors[j][i] = colors[j+1][i];
            colors[3][i] = tmp;
        }
        Color tmp = colors[5][0];
        colors[5][0] = colors[5][2];
        colors[5][2] = colors[5][8];
        colors[5][8] = colors[5][6];
        colors[5][6] = tmp;
        tmp = colors[5][1];
        colors[5][1] = colors[5][5];
        colors[5][5] = colors[5][7];
        colors[5][7] = colors[5][3];
        colors[5][3] = tmp;
        repaint();
    }
    public void midRight(){
        for (int i = 3; i < 6; i++){
            Color tmp = colors[3][i];
            for (int j = 2; j >= 0; j--)
                colors[j+1][i] = colors[j][i];
            colors[0][i] = tmp;
        }
        repaint();
    }
    public void midLeft(){
        for (int i = 3; i < 6; i++){
            Color tmp = colors[0][i];
            for (int j = 0; j < 4; j++)
                colors[j][i] = colors[j+1][i];
            colors[3][i] = tmp;
        }
        repaint();
    }
    public void topRight(){
        for (int i = 0; i < 3; i++){
            Color tmp = colors[3][i];
            for (int j = 2; j >= 0; j--)
                colors[j+1][i] = colors[j][i];
            colors[0][i] = tmp;
        }
        Color tmp = colors[4][0];
        colors[4][0] = colors[4][2];
        colors[4][2] = colors[4][8];
        colors[4][8] = colors[4][6];
        colors[4][6] = tmp;
        tmp = colors[4][1];
        colors[4][1] = colors[4][5];
        colors[4][5] = colors[4][7];
        colors[4][7] = colors[4][3];
        colors[4][3] = tmp;
        repaint();
    }
    public void topLeft(){
        for (int i = 0; i < 3; i++){
            Color tmp = colors[0][i];
            for (int j = 0; j < 4; j++)
                colors[j][i] = colors[j+1][i];
            colors[3][i] = tmp;
        }
        Color tmp = colors[4][0];
        colors[4][0] = colors[4][6];
        colors[4][6] = colors[4][8];
        colors[4][8] = colors[4][2];
        colors[4][2] = tmp;
        tmp = colors[4][1];
        colors[4][1] = colors[4][3];
        colors[4][3] = colors[4][7];
        colors[4][7] = colors[4][5];
        colors[4][5] = tmp;
        repaint();
    }
    public void leftDown(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3];
            colors[0][j*3] = colors[4][j*3];
            colors[4][j*3] = colors[2][j*3];
            colors[2][j*3] = colors[5][j*3];
            colors[5][j*3] = tmp;
        }
        int i = 3;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][6];
        colors[i][6] = colors[i][8];
        colors[i][8] = colors[i][2];
        colors[i][2] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][3];
        colors[i][3] = colors[i][7];
        colors[i][7] = colors[i][5];
        colors[i][5] = tmp;
        repaint();
    }
    public void leftUp(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3];
            colors[0][j*3] = colors[5][j*3];
            colors[5][j*3] = colors[2][j*3];
            colors[2][j*3] = colors[4][j*3];
            colors[4][j*3] = tmp;
        }
        int i = 3;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][2];
        colors[i][2] = colors[i][8];
        colors[i][8] = colors[i][6];
        colors[i][6] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][5];
        colors[i][5] = colors[i][7];
        colors[i][7] = colors[i][3];
        colors[i][3] = tmp;
        repaint();
    }
    public void midDown(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+1];
            colors[0][j*3+1] = colors[4][j*3+1];
            colors[4][j*3+1] = colors[2][j*3+1];
            colors[2][j*3+1] = colors[5][j*3+1];
            colors[5][j*3+1] = tmp;
        }
        repaint();
    }
    public void midUp(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+1];
            colors[0][j*3+1] = colors[5][j*3+1];
            colors[5][j*3+1] = colors[2][j*3+1];
            colors[2][j*3+1] = colors[4][j*3+1];
            colors[4][j*3+1] = tmp;
        }
        repaint();
    }
    public void rightDown(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+2];
            colors[0][j*3+2] = colors[4][j*3+2];
            colors[4][j*3+2] = colors[2][j*3+2];
            colors[2][j*3+2] = colors[5][j*3+2];
            colors[5][j*3+2] = tmp;
        }
        int i = 1;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][2];
        colors[i][2] = colors[i][8];
        colors[i][8] = colors[i][6];
        colors[i][6] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][5];
        colors[i][5] = colors[i][7];
        colors[i][7] = colors[i][3];
        colors[i][3] = tmp;
        repaint();
    }
    public void rightUp(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+2];
            colors[0][j*3+2] = colors[5][j*3+2];
            colors[5][j*3+2] = colors[2][j*3+2];
            colors[2][j*3+2] = colors[4][j*3+2];
            colors[4][j*3+2] = tmp;
        }
        int i = 1;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][6];
        colors[i][6] = colors[i][8];
        colors[i][8] = colors[i][2];
        colors[i][2] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][3];
        colors[i][3] = colors[i][7];
        colors[i][7] = colors[i][5];
        colors[i][5] = tmp;
        repaint();
    }
    public void frontRight(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3];
            colors[1][j*3] = colors[4][j+6];
            colors[4][j+6] = colors[3][j*3];
            colors[3][j*3] = colors[5][j];
            colors[5][j] = tmp;
        }
        int i = 0;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][6];
        colors[i][6] = colors[i][8];
        colors[i][8] = colors[i][2];
        colors[i][2] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][3];
        colors[i][3] = colors[i][7];
        colors[i][7] = colors[i][5];
        colors[i][5] = tmp;
        repaint();
    }
    public void frontLeft(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3];
            colors[1][j*3] = colors[5][j];
            colors[5][j] = colors[3][j*3];
            colors[3][j*3] = colors[4][j+6];
            colors[4][j+6] = tmp;
        }
        int i = 0;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][2];
        colors[i][2] = colors[i][8];
        colors[i][8] = colors[i][6];
        colors[i][6] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][5];
        colors[i][5] = colors[i][7];
        colors[i][7] = colors[i][3];
        colors[i][3] = tmp;
        repaint();
    }
    public void sideMidRight(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3+1];
            colors[1][j*3+1] = colors[4][j+3];
            colors[4][j+3] = colors[3][j*3+1];
            colors[3][j*3+1] = colors[5][j+3];
            colors[5][j+3] = tmp;
        }
        repaint();
    }
    public void sideMidLeft(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3+1];
            colors[1][j*3+1] = colors[5][j+3];
            colors[5][j+3] = colors[3][j*3+1];
            colors[3][j*3+1] = colors[4][j+3];
            colors[4][j+3] = tmp;
        }
        repaint();
    }
    public void backRight(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3+2];
            colors[1][j*3+2] = colors[4][j];
            colors[4][j] = colors[3][j*3+2];
            colors[3][j*3+2] = colors[5][j+6];
            colors[5][j+6] = tmp;
        }
        int i = 2;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][2];
        colors[i][2] = colors[i][8];
        colors[i][8] = colors[i][6];
        colors[i][6] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][5];
        colors[i][5] = colors[i][7];
        colors[i][7] = colors[i][3];
        colors[i][3] = tmp;
        repaint();
    }
    public void backLeft(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3+2];
            colors[1][j*3+2] = colors[5][j+6];
            colors[5][j+6] = colors[3][j*3+2];
            colors[3][j*3+2] = colors[4][j];
            colors[4][j] = tmp;
        }
        int i = 2;
        Color tmp = colors[i][0];
        colors[i][0] = colors[i][2];
        colors[i][2] = colors[i][8];
        colors[i][8] = colors[i][6];
        colors[i][6] = tmp;
        tmp = colors[i][1];
        colors[i][1] = colors[i][5];
        colors[i][5] = colors[i][7];
        colors[i][7] = colors[i][3];
        colors[i][3] = tmp;
        repaint();
    }
}

class LeftSide extends JComponent {
    protected double length, X, Y;
    protected Color[] colors = new Color[9];

    public LeftSide(double X, double Y, double length, Color[] colors){
        this.length = length; this.colors = colors.clone();
        this.X = X; this.Y = Y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < 3; i++) {
            double TLX = X, TLY = Y+length*i;
            for (int j = 0; j < 3; j++) {
                ParaLeft side = new ParaLeft(TLX,TLY,length,colors[i*3+j]);
                side.paintComponent(g);
                TLY += length*Math.sin(Math.PI/6);
                TLX += length*Math.cos(Math.PI/6);
            }
        }
    }
}

class RightSide extends JComponent {
    protected double length, X, Y;
    protected Color[] colors = new Color[9];
    
    public RightSide(double X, double Y, double length, Color[] colors){
        this.length = length; this.colors = colors;
        this.X = X; this.Y = Y;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < 3; i++) {
            double TLX = X, TLY = Y + length*i;
            for (int j = 0; j < 3; j++) {
                ParaRight right = new ParaRight(TLX,TLY,length,colors[i*3+j]);
                right.paintComponent(g);
                TLX += Math.cos(Math.PI/6)*length;
                TLY -= Math.sin(Math.PI/6)*length;
            }
        }
    }
}

class TopSide extends JComponent {
    protected double length, X, Y;
    protected Color[] colors = new Color[9];

    public TopSide(double X, double Y, double length, Color[]colors){
        this.length = length; this.colors = colors;
        this.X = X; this.Y = Y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < 3; i++) {
            double TLY = Y + Math.sin(Math.PI/6)*length*i, TLX = X - Math.cos(Math.PI/6)*length*i;
            for (int j = 0; j < 3; j++) {
                ParaTop top = new ParaTop(TLX,TLY,length,colors[i*3+j]);
                top.paintComponent(g);
                TLX += length*Math.cos(Math.PI/6);
                TLY += length*Math.sin(Math.PI/6);
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
        g2d.setColor(Color.BLACK); g2d.setStroke(new java.awt.BasicStroke(1));
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
        g2d.setColor(Color.BLACK); g2d.setStroke(new java.awt.BasicStroke(1));
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
        g2d.setColor(Color.BLACK); g2d.setStroke(new java.awt.BasicStroke(1));
        g2d.draw(parallelogram);
    }
}