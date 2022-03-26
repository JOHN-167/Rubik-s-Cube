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
import java.awt.FlowLayout; import java.awt.GridLayout;

public class Rubik extends JFrame {

    public Rubik() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        // setResizable(false);
        setLayout(new GridLayout(1,2));
        Canvas canvas = new Canvas();
        add(canvas);
        
        JButton bottomRight = new JButton("->");
        bottomRight.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                canvas.bottomRight();
            }
        });
        bottomRight.setVisible(true);
        // bottomRight.setSize(getWidth()/5,getHeight());
        add(bottomRight);
    }

    public static void main(String[] args) {
        Rubik rubik = new Rubik();
        rubik.setVisible(true);
    }
}

class Canvas extends JPanel {

    protected Color[][] colors = new Color[6][9];
    protected Color[] colorContruct = new Color[]{
	Color.RED, Color.BLUE, new Color(255,100,0),
	Color.GREEN, Color.WHITE, Color.YELLOW};
    Color[] colorsLeft = new Color[9];
    Color[] colorsRight= new Color[9];
    Color[] colorsTop = new Color[9];

    public Canvas() {
        setBackground(Color.GRAY);
        for (int i = 0; i < 6; i++)
            Arrays.fill(colors[i],colorContruct[i]);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 9; i++)
            colorsLeft[i] = colors[0][i];
        for (int i = 0; i < 9; i++)
            colorsRight[i] = colors[1][i];
        for (int i = 0; i < 9; i++)
            colorsTop[i] = colors[4][i];
        double startPosX = Math.min(getHeight(), getWidth())/8;
        double length = startPosX/Math.cos(Math.PI/6);
        double startPosY = 3*startPosX+length/2;
        LeftSide left = new LeftSide(startPosX, startPosY+5*length/2, length, colorsLeft);
        RightSide right = new RightSide(4*startPosX, startPosY+4*length, length, colorsRight);
        TopSide top = new TopSide(startPosX*3, startPosY+length*3/2, length, colorsTop);
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
            colors[4][i] = tmp;
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
            colors[4][i] = tmp;
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
            colors[4][i] = tmp;
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
            colors[2][j*3] = colors[500000][j*3];
            colors[5][j*3] = tmp;
        }
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
        repaint();
    }
    public void sideFrontRight(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+1];
            colors[1][j*3] = colors[4][j*3];
            colors[4][j*3] = colors[3][j*3];
            colors[3][j*3] = colors[5][j*3];
            colors[5][j*3] = tmp;
        }
    }
    public void sideFrontLeft(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+1];
            colors[0][j*3] = colors[4][j*3];
            colors[4][j*3] = colors[2][j*3];
            colors[2][j*3] = colors[5][j*3];
            colors[5][j*3] = tmp;
        }
        repaint();
    }
    public void sideMidRight(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+1];
            colors[1][j*3+1] = colors[4][j*3+1];
            colors[4][j*3+1] = colors[3][j*3+1];
            colors[3][j*3+1] = colors[5][j*3+1];
            colors[5][j*3+1] = tmp;
        }
        repaint();
    }
    public void sideMidLeft(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3+1];
            colors[1][j*3+1] = colors[5][j*3+1];
            colors[5][j*3+1] = colors[3][j*3+1];
            colors[3][j*3+1] = colors[4][j*3+1];
            colors[4][j*3+1] = tmp;
        }
        repaint();
    }
    public void sideBackRight(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[0][j*3+1];
            colors[1][j*3+2] = colors[4][j*3+2];
            colors[4][j*3+2] = colors[3][j*3+2];
            colors[3][j*3+2] = colors[5][j*3+2];
            colors[5][j*3+2] = tmp;
        }
        repaint();
    }
    public void sideBackLeft(){
        for (int j = 0; j < 3; j++){
            Color tmp = colors[1][j*3+1];
            colors[1][j*3+2] = colors[5][j*3+2];
            colors[5][j*3+2] = colors[3][j*3+2];
            colors[3][j*3+2] = colors[4][j*3+2];
            colors[4][j*3+2] = tmp;
        }
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