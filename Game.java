import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Write a description of class Game here.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/8/21
 * @since 4/8/21
 */
public class Game extends Canvas implements Runnable
{
    // instance variables - replace the example below with your own
    private static final long serialVersionUID = 1852555291L;
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH;
    
    private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    private HUD hud;

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
     public synchronized void stop()
    {
        try{
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        new Game();
    }
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH, HEIGHT, "Algologo", this);
        
        hud = new HUD();
        
        handler.addObject(new Player(WIDTH/2,HEIGHT/2,handler));
        Term myTerm = new MathExpression(new Term(null),Operator.POWER,new Term(2.0));
        Term myTerm2 = new MathExpression(myTerm,Operator.MULTIPLY,new Term(0.1));
        handler.addObject(new AlgoShape(myTerm2,new Style(),-WIDTH/2,HEIGHT/2));
    }
    
    

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void run()
    {
        //boolean running = true;
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }

            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
    
    
    
    private void tick()
    {
        handler.tick();
        hud.tick();
    }
    
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        handler.render(g);
        hud.render(g);
        
        g.dispose();
        bs.show();
    }
}
