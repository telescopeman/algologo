import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.io.Serial;

/**
 * Write a description of class Game here.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/12/21
 * @since 4/8/21
 */
@SuppressWarnings("SuspiciousNameCombination")
public class Game extends Canvas implements Runnable
{
    // instance variables - replace the example below with your own
    @Serial
    private static final long serialVersionUID = -3944939127227443376L;
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH;
    
    private Thread thread;
    private boolean running = false;
    
    private final Handler handler;
    private final HUD hud;
    private final Player player;

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
     @SuppressWarnings("unused")
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
    
    public static void main(String[] args)
    {
        new Game();
    }
    
    /**
     * Starts a game.
     */
    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH, HEIGHT, "Algologo", this);
        
        hud = new HUD();
        player =new Player(WIDTH/2,HEIGHT/2,handler);
        
        handler.addObject(player);
        handler.addObject(makeShape("1+1",0,0, DrawType.FILL_BELOW)); //find a way to avoid needing the -3000
        handler.addObject(makeShape("x/10",-600,0,new Style(DrawType.FILL_BELOW, new Dimension(WIDTH,HEIGHT))));

        //handler.addObject
    }
    
    private AlgoShape makeShape(String s, int x, int y, DrawType drw)
    {
        return new AlgoShape(AlgoShapeHelper.parse(s),new Style(drw),x,y);
    }

    private AlgoShape makeShape(String s, int x, int y, Style style)
    {
        return new AlgoShape(AlgoShapeHelper.parse(s),style,x,y);
    }

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


        String disp = player.getVelocityX() + " spX, " + player.getVelocityY() + "spY" + player.getX() + " X, " + player.getY() + "Y";
        hud.render(g,disp);
        
        g.dispose();
        bs.show();
    }
}
