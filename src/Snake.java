import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake extends JFrame{

    int widht = 640;
    int height = 480;

    Point snake;
    Point comida;

    int widhtPoint = 10;
    int heightPoint = 10;

    boolean gameover = false;

    ArrayList<Point> lista;

    ImagenSnake imagenSnake;
    long frecuencia = 80;
    int direccion = KeyEvent.VK_LEFT;

    public Snake(){
        java.awt.Container base = getContentPane();
        base.setBackground(new Color(40, 42, 54));

        Teclas teclas = new Teclas();
        this.addKeyListener(teclas);

        startGame();

        imagenSnake = new ImagenSnake();
        base.add(imagenSnake);
        
        this.setTitle("Snake");
        this.setSize(widht+30, height+30);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(40, 42, 54));
        this.setVisible(true);

        Momento momento = new Momento();
        Thread hilo = new Thread(momento);
        hilo.start();
    }
    
    public void actualizar(){
        imagenSnake.repaint();

        lista.add(0, new Point(snake.x, snake.y));
        lista.remove((lista.size()-1));

        for (int i = 1; i < lista.size(); i++) {
            Point point = lista.get(i);
            if((snake.x == point.x) && (snake.y == point.y)){
                gameover = true; 
            }
        }

        if((snake.x > (comida.x - 10)) && (snake.x < (comida.x + 10)) && (snake.y > (comida.y - 10)) && (snake.y < (comida.y + 10))){
            lista.add(0, new Point(snake.x,snake.y));
            crearComida();
        }
    } 
 
    public void startGame(){
        comida = new Point(200, 200);
        snake = new Point(widht/2, height/2);

        lista = new ArrayList<Point>();
        //lista.add(snake);

        crearComida();

    }

    public void crearComida(){
        Random rdm = new Random();

        comida.x= rdm.nextInt(widht);
        if((comida.x % 5) > 0){
            comida.x = comida.x -(comida.x % 5);    
        }
        if(comida.x <5){
            comida.x = comida.x + 10;
        }
        // para que aparezca en la posicion y
        comida.y= rdm.nextInt(height);
        if((comida.y % 5) > 0){
            comida.y = comida.y -(comida.y % 5);    
        }
        if(comida.y <5){
            comida.y = comida.y + 10;
        }
    }

    public class Teclas extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                System.exit(0);
            } else if(e.getKeyCode() == KeyEvent.VK_UP){
                if(direccion != KeyEvent.VK_DOWN){
                    direccion = KeyEvent.VK_UP;
                }
            } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(direccion != KeyEvent.VK_UP){
                    direccion = KeyEvent.VK_DOWN;
                }
            } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(direccion != KeyEvent.VK_RIGHT){
                    direccion = KeyEvent.VK_LEFT;
                }
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(direccion != KeyEvent.VK_LEFT){
                    direccion = KeyEvent.VK_RIGHT;
                }
            }
        }
    }

    public class ImagenSnake extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            g.setColor(new Color(80, 250, 123));
            g.fillRect(snake.x, snake.y, widhtPoint, heightPoint);
            for (int i = 0; i < lista.size(); i++) {
                Point point = (Point)lista.get(i);
                g.fillRect(point.x, point.y, widhtPoint, heightPoint);
                
            }
            

            g.setColor(new Color(133, 77, 188));
            g.fillRect(comida.x, comida.y, widhtPoint, heightPoint);

            if(gameover){
                g.drawString( "GAME OVER", 200, 320);
            }
        }
    }

    public class Momento extends Thread{
        long last = 0;
        public void run(){
            while (true) {
                if(java.lang.System.currentTimeMillis() - last > frecuencia){
                    if(!gameover){
                        if(direccion == KeyEvent.VK_UP){
                            snake.y = snake.y - heightPoint;
                            if(snake.y > height){
                                snake.y = 0;
                            }
                            if(snake.y < 0){
                                snake.y = height - heightPoint;
                            }
                        } else if(direccion == KeyEvent.VK_DOWN){
                            snake.y = snake.y + heightPoint;
                            if(snake.y > height){
                                snake.y = 0;
                            }
                            if(snake.y < 0){
                                snake.y = height - heightPoint;
                            }
                        } else if(direccion == KeyEvent.VK_LEFT){
                            snake.x = snake.x - widhtPoint;
                            if(snake.x > widht){
                                snake.x = 0;
                            }
                            if(snake.x < 0){
                                snake.x = widht - widhtPoint;
                            }
                        } else if(direccion == KeyEvent.VK_RIGHT){
                            snake.x = snake.x + widhtPoint;
                            if(snake.x > widht){
                                snake.x = 0;
                            }
                            if(snake.x < 0){
                                snake.x = widht - widhtPoint;
                            }
                        }
                    } 
                    actualizar();
                    last = java.lang.System.currentTimeMillis();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Snake();
    }
}
