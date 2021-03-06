import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.JApplet;
public class Board extends Applet implements ActionListener{

    private final int WIDTH = 304;
    private final int HEIGHT = 304;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    int c1=0;
    int c2=0;
    int c3=0;
     
   
    
    
    private int x1[] = new int[ALL_DOTS];
    private int y1[] = new int[ALL_DOTS];
    
    private int x2[] = new int[ALL_DOTS];
    private int y2[] = new int[ALL_DOTS];
    private int dots1;
    private int dots2;
    private int apple_x;
    private int apple_y;
    
    public boolean left1 = false;
    public boolean right1 = true;
    public boolean up1 = false;
    public boolean down1 = false;
    public boolean inGame = true;
    public boolean left2 = true;
    public boolean right2 = false;
    public boolean up2 = false;
    public boolean down2 = false;
    
    Image wall;
    private Timer timer;
public void init(){}
public void start(){
addKeyListener(new TAdapter());
      
        setBackground(Color.black);
        
        setFocusable(true);
        initGame();
}
 public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        


        if (inGame) {
            g.setColor(Color.orange);
                    g.fillRect(apple_x, apple_y,10,10);
          

            for (int z = 0; z < dots1; z++) {
                if (z == 0){
                    g.setColor(Color.red);
                    g.fillOval(x1[z], y1[z],10,10);}
                else{
                    g.setColor(Color.green);
                    g.fillOval(x1[z], y1[z],10,10);}
                    
          }

            for (int z = 0; z < dots2; z++) {
                if (z == 0){
                    g.setColor(Color.red);
                    g.fillOval(x2[z], y2[z],10,10);}
                else{
                    g.setColor(Color.blue);
                    g.fillOval(x2[z], y2[z],10,10 );}
                   
                }

            Toolkit.getDefaultToolkit().sync();
            
           
            g.dispose();

        } 
        
        else {
            Button b=new Button("Play again");
            this.add(BorderLayout.SOUTH,b);
            if(c1==1){
                String a="PLAYER 1 WINS!!!!";
                  g.setColor(Color.gray);
           
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(small);
        g.setFont(small);
        g.drawString(a,70,50);}
            if(c2==1){
                 String a="PLAYER 2 WINS!!!!";
                   g.setColor(Color.gray);
           
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(small);
        g.setFont(small);
        g.drawString(a,70,50);}
         if(c3==1){
                 String a="DRAW";
                   g.setColor(Color.gray);
           
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(small);
        g.setFont(small);
        g.drawString(a,120,50);}
                  
         
            
            gameOver(g);
            
            Toolkit.getDefaultToolkit().sync();
            
           
            g.dispose();
            
              
        }
        
    }
    
    public void initGame() {

        dots1 = 3;

        for (int z = 0; z < dots1; z++) {
            x1[z] = 50 - z*10;
            y1[z] = 50;
        }
        
        dots2 = 3;

        for (int z = 0; z < dots2; z++) {
            x2[z] = 250 - z*10;
            y2[z] = 250;
        } 
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }
      public void gameOver(Graphics g) {
     
        String msg = "Game Over. Score: "+(dots1-3)+" "+(dots2-3);
        
        Font small = new Font("Helvetica", Font.BOLD, 
14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth
(msg)) / 2,
                     HEIGHT / 2);
        
         
       
    }


    public void checkApple() {

        if ((x1[0] == apple_x) && (y1[0] == apple_y)) {
            dots1++;
            locateApple();
        }
         else if ((x2[0] == apple_x) && (y2[0] == apple_y)) {
            dots2++;
            locateApple();
        }
    }


    public void move() {

        for (int z = dots1; z > 0; z--) {
            x1[z] = x1[(z - 1)];
            y1[z] = y1[(z - 1)];
        }

        if (left1) {
            x1[0] -= DOT_SIZE;
        }

        if (right1) {
            x1[0] += DOT_SIZE;
        }

        if (up1) {
            y1[0] -= DOT_SIZE;
        }

        if (down1) {
            y1[0] += DOT_SIZE;
        }
    for (int z = dots2; z > 0; z--) {
            x2[z] = x2[(z - 1)];
            y2[z] = y2[(z - 1)];
        }

        if (left2) {
            x2[0] -= DOT_SIZE;
        }

        if (right2) {
            x2[0] += DOT_SIZE;
        }

        if (up2) {
            y2[0] -= DOT_SIZE;
        }

        if (down2) {
            y2[0] += DOT_SIZE;
        }
    }


    public void checkCollision() {
        
    

          for (int z = dots1; z >0; z--) {

              if ((z > 4) && (x1[0] == x1[z]) && (y1[0] == y1[z])) {
                  
                  c1=1;
                  inGame = false;
                  
              }}
              for(int z=dots1;z>0;z--){
              if((x2[0]==x1[z]) && (y2[0]==y1[z])){
                  
                  c1=1;

                  inGame = false;}
          }
          
         for (int z = dots2; z >0; z--) {

              if ((z > 4) && (x2[0] == x2[z]) && (y2[0] == 
y2[z])) {
c2=1;
                  

                  inGame = false;
              }}
              for(int z=dots2;z>0;z--){
                  
         
          if((x1[0]==x2[z])&&(y1[0]==y2[z])){
                  c2=1;
                  inGame = false;}
            }
        if((x1[0]==x2[0])&&(y1[0]==y2[0])){
              c3=1;
              inGame=false;}
        else if (y1[0] > HEIGHT) {
            y1[0]=0;
            
        }

        else if (y1[0] < 0) {
            y1[0]=300;
            
        }

        else if (x1[0] > WIDTH) {
            x1[0]=0;
            
        }

        else if (x1[0] < 0) {
            x1[0]=300;
            
        }
         else if (y2[0] > HEIGHT) {
            y2[0]=0;
            
        }

        else if (y2[0]<0) {
            
            y2[0]=300;
        }

        else if (x2[0] > WIDTH) {
            
            x2[0]=0;
        }

        else if (x2[0] < 0) {
            
            x2[0]=300;
        }
        
    

}
        
        
        
        
        
        
    public void locateApple() {
        int r1 = (int) (Math.random() * RAND_POS);
        int r2 = (int) (Math.random() * RAND_POS);
        apple_x = ((r1 * DOT_SIZE));
        apple_y = ((r2 * DOT_SIZE));
        
    }
    
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
            repaint();
        }
    }


    class TAdapter extends KeyAdapter {

        
        public void keyPressed(KeyEvent e) {

            int key1 = e.getKeyCode();
            int key2 = e.getKeyCode();
            if ((key1 == KeyEvent.VK_LEFT) && (!right1)) {
                left1 = true;
                up1 = false;
                down1 = false;
            }

            if ((key1 == KeyEvent.VK_RIGHT) && (!left1)) {
                right1 = true;
                up1 = false;
                down1 = false;
            }

            if ((key1 == KeyEvent.VK_UP) && (!down1)) {
                up1 = true;
                right1 = false;
                left1 = false;
            }

            if ((key1 == KeyEvent.VK_DOWN) && (!up1)) {
                down1 = true;
                right1 = false;
                left1 = false;
            }
         if ((key2 == KeyEvent.VK_A) && (!right2)) {
                left2 = true;
                up2 = false;
                down2 = false;
            }

            if ((key2 == KeyEvent.VK_D) && (!left2)) {
                right2 = true;
                up2 = false;
                down2 = false;
            }

            if ((key2 == KeyEvent.VK_W) && (!down2)) {
                up2 = true;
                right2 = false;
                left2 = false;
            }

            if ((key2 == KeyEvent.VK_S) && (!up2)) {
                down2 = true;
                right2 = false;
                left2 = false;
            }
        }}
    }
