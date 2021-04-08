/**
 * @author "Ozymandias", Caleb Copeland
 * @version 4/8/21
 * @since 4/7/21
 */
public class DrawingStuff {
    

    public static void main(String[] args) {
        EasyFrame frame = new EasyFrame("Algologo");
        frame.setSize(Defaults.WINDOWSIZE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        //EasyPanel gameScreen = new EasyPanel(Defaults.WINDOWSIZE);
        //frame.add(gameScreen);
        World w = new World();
        frame.add(w);
        
        
        frame.setVisible(true);
        
    }

}