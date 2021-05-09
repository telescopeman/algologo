import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

/**
 * Runs the highest-level functions of the game.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/27/21
 * @since 4/8/21
 */

public class Game extends Canvas implements Runnable
{
    @Serial
    private static final long serialVersionUID = -3944939127227443376L;

    public static final Color TERRAIN_COLOR = Color.green;
    public static final boolean debugging = true;

    
    private Thread thread;
    public volatile boolean running;

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

        new Window(-100,100,-100,100, this);
        running = true;
        setUpDisplay();
        Handler.queueForAddition(
                new AlgoShape(MathExpression.parse("x^0.5")));
        Handler.queueForAddition(new Spawner(
                new SimpleParticle(10,10,Color.GRAY),
                        new Rectangle(-3,3,2,3),5000
                        ));
    }

    public static void setUpDisplay()
    {
        // load axes
        Handler.queueForAddition(new Axes());
    }

    public static void printDebug(String str)
    {
        if (debugging)
        {
            System.out.println(str);
        }
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

            if(running) {
                try {
                    render();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
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
    
    private void render() throws CloneNotSupportedException {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //g.translate(Window.getDimension().width,Window.getDimension().height);
        GraphicsHelper helper = new GraphicsHelper(g);
        Handler.render(helper);
        g.dispose();
        bs.show();
    }
}
