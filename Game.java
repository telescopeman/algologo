import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.io.Serial;

/**
 * Runs the highest-level functions of the game.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/13/21
 * @since 4/8/21
 */

public class Game extends Canvas implements Runnable
{
    @Serial
    private static final long serialVersionUID = -3944939127227443376L;
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH;
    public final Color BACKGROUND_COLOR = Color.black;
    public static final Color TERRAIN_COLOR = Color.white;

    
    private Thread thread;
    private volatile boolean running;

    private final HUD hud;

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();

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
     * Starts the game.
     */
    public Game()
    {
        this.addKeyListener(new KeyInput());
        final String TITLE = "Algologo";
        new Window(WIDTH, HEIGHT, TITLE, this);
        
        hud = new HUD();
        LevelManager.loadLevel(LevelManager.LEVELS.BETA_LEVEL);
        running = true;
    }
    


    public void run()
    {
        while (!running) {
            Thread.onSpinWait();
            // wait for things to actually be set up
        }

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
        Handler.tick();
        //hud.tick();
    }
    
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0,0,WIDTH,HEIGHT);

        Handler.render(g);

//        String debugDisplay = LevelManager.player.getVelocityX() + " spX, "
//                + LevelManager.player.getVelocityY() + "spY"
//                + LevelManager.player.getX() + " X, "
//                + LevelManager.player.getY() + "Y";

        //String debugDisplay = LevelManager.player.getHealth() + "HP";
        //hud.render(g,debugDisplay);
        
        g.dispose();
        bs.show();
    }
}
