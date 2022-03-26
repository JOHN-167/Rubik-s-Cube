import javax.swing.JFrame; import javax.swing.Timer;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.JButton; import java.util.*;
import java.awt.event.ActionListener; import java.awt.event.ActionEvent;
import java.awt.FlowLayout; import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class Rubik extends JFrame {

    Canvas canvas = new Canvas();

    public Rubik() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,(int) (300/Math.cos(Math.PI/6)));
        setResizable(false);
        // setLayout(new GridLayout(1,2));
        add(canvas);
    }

    public class Buttons extends JFrame { 
        public Buttons(){
            setSize(1000,100);
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            JButton bottomRight = new JButton("bottomRight");
            bottomRight.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.bottomRight();
                }
            });
            bottomRight.setVisible(true);
            add(bottomRight);
            JButton bottomLeft = new JButton("bottomLeft");
            bottomLeft.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.bottomLeft();
                }
            });
            bottomLeft.setVisible(true);
            add(bottomLeft);
            JButton midRight = new JButton("midRight");
            midRight.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.midRight();
                }
            });
            midRight.setVisible(true);
            add(midRight);
            JButton midLeft = new JButton("midLeft");
            midLeft.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.midLeft();
                }
            });
            midLeft.setVisible(true);
            add(midLeft);
            JButton topRight = new JButton("topRight");
            topRight.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.topRight();
                }
            });
            topRight.setVisible(true);
            add(topRight);
            JButton topLeft = new JButton("topLeft");
            topLeft.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.topLeft();
                }
            });
            topLeft.setVisible(true);
            add(topLeft);
            JButton leftUp = new JButton("LeftUp");
            leftUp.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.leftUp();
                }
            });
            leftUp.setVisible(true);
            add(leftUp);
            JButton leftDown = new JButton("leftDown");
            leftDown.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.leftDown();
                }
            });
            leftDown.setVisible(true);
            add(leftDown);
            JButton midUp = new JButton("midUp");
            midUp.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.midUp();
                }
            });
            midUp.setVisible(true);
            add(midUp);
            JButton midDown = new JButton("midDown");
            midDown.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.midDown();
                }
            });
            midDown.setVisible(true);
            add(midDown);
            JButton rightUp = new JButton("rightUp");
            rightUp.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.rightUp();
                }
            });
            rightUp.setVisible(true);
            add(rightUp);
            JButton rightDown = new JButton("rightDown");
            rightDown.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.rightDown();
                }
            });
            rightDown.setVisible(true);
            add(rightDown);
            JButton frontRight = new JButton("frontRight");
            frontRight.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.frontRight();
                }
            });
            frontRight.setVisible(true);
            add(frontRight);
            JButton frontLeft = new JButton("frontLeft");
            frontLeft.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.frontLeft();
                }
            });
            frontLeft.setVisible(true);
            add(frontLeft);
            JButton sideMidRight = new JButton("sideMidRight");
            sideMidRight.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.sideMidRight();
                }
            });
            sideMidRight.setVisible(true);
            add(sideMidRight);
            JButton sideMidLeft = new JButton("sideMidLeft");
            sideMidLeft.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.sideMidLeft();
                }
            });
            sideMidLeft.setVisible(true);
            add(sideMidLeft);
            JButton backRight = new JButton("backRight");
            backRight.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.backRight();
                }
            });
            backRight.setVisible(true);
            add(backRight);
            JButton backLeft = new JButton("backLeft");
            backLeft.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    canvas.backLeft();
                }
            });
            backLeft.setVisible(true);
            add(backLeft);
        }
    }

    public static void main(String[] args) {
        Rubik rubik = new Rubik();
        rubik.setVisible(true);
        Rubik.Buttons buttons = rubik.new Buttons();
        buttons.setVisible(true);
    }
}

