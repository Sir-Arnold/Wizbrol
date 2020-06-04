package wbpckg;
import java.awt.Canvas; 
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy; 
import java.io.PrintStream;
import java.util.LinkedList;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
  
public class Game extends Canvas implements Runnable 
{ 
   private static final long serialVersionUID = 1L; 
   public static int WIDTH; 
   public static int HEIGHT; 
   public GameInfo gameInfo;
   private Thread thread; 
   protected boolean running = false; 
   
   private Handler handler; 
   public MyMenu menu;
   
   public static boolean debugMode;
   
   public int mouseX;
   public int mouseY;
   
   public static LinkedList cStats;                     // for each challenger battle, the stats for the fight are recorded
   public static LinkedList cTimes;                     // the time it took to kill each challenger
   public static LinkedList cShotCounts;                // the number of shots fired by the player
   public static LinkedList cAccuracies;                // the shot accuracy of the player
   
   public static int cPhase;                         // keeps track of what challenger the player is on
   
   public static double startTime; 
   public static double time; 
   public static double runningTime; 
   public static double timeTimer; 
   public static double pauseRunningTime;
    
  public Game() 
  { 
	 GameInfo.initialize();
	 WIDTH = GameInfo.getWindowWidth();
     HEIGHT = GameInfo.getWindowHeight();
	 debugMode = false;
	 timeTimer = 0;
	 cPhase = 0;
	 handler = new Handler(this);
	 menu = new MyMenu(this, handler, "mainMenu");
     Window window = new Window(WIDTH, HEIGHT, "Shoot Gun", this);
	 addKeyListener(new KeyInput(handler, this, window));
     addMouseListener(new MouseAdapter() {
    	public void mousePressed(MouseEvent e)
    	{
    		clickEvent(e, e.getX(), e.getY());
    	}
    	public void mouseReleased(MouseEvent e)
    	{
    		clickRelease(e, e.getX(), e.getY());
    	}
    });
    
    constructMainMenu();
    
   } 
    
  public synchronized void start() 
   { 
     System.out.println("Thread starting..."); 
     thread = new Thread(this); 
     thread.start(); 
     running = true; 
     System.out.println("Thread started."); 
   } 
    
  public synchronized void stop() 
   { 
     try { 
       System.out.println("Thread stopping..."); 
       thread.join(); 
       running = false; 
       System.out.println("Thread stopped."); 
     } 
     catch (Exception e) 
     { 
       e.printStackTrace(); 
     } 
   } 
    
  
  
  
   public void run() 
   { 
	 long lastTime = System.nanoTime();                                            // lastTime is measured in nanoseconds
	 double amountOfTicks = 60.0;                                                  // amount of ticks per second
	 double ns = 1000000000 / amountOfTicks;                                       // time in nanoseconds for a single tick to pass (ideally)
	 double delta = 0.0;                                                           // delta stores the deviation between how long the while loop did take to iterate versus how long it should take
	 long timer = System.currentTimeMillis();                                      // used to tell how many milliseconds has passed
	 //startTime = System.currentTimeMillis();                                     // the stored millisecond value of time when the game started
	 int frames = 0;                                                               // FPS tracker
	 //pauseRunningTime = 0.0;
	   
	 while(handler.getMenuState().equals("mainMenu"))
     {
		 Point p = MouseInfo.getPointerInfo().getLocation();
		 long now = System.nanoTime(); 
         delta += (now - lastTime) / ns; 
         lastTime = now;  
    
         while (delta >= 1.0) 
         { 
           tick(delta); 
           handler.setMouse(p);
           delta -= 1.0; 
         } 
         if (running) 
           render(); 
         frames++; 
     }
	   
	 lastTime = System.nanoTime();                                     
     amountOfTicks = 60.0;                                        
     delta = 0.0;                                                  
     timer = System.currentTimeMillis();      
     startTime = System.currentTimeMillis();                              
     frames = 0;                                                               
     pauseRunningTime = 0.0;
     
     while (running) 
     { 
    	   Point p = MouseInfo.getPointerInfo().getLocation();
    	   if(handler.getMenuState().equals("gameMenu"))
		   {
    		   pauseRunningTime = runningTime - handler.pauseTime;
		   }
    	   
    	   long now = System.nanoTime(); 
           delta += (now - lastTime) / ns; 
           lastTime = now; 
           runningTime = (System.currentTimeMillis() - startTime) / (double) 1000; 
           
           while (delta >= 1.0) 
           { 
             tick(delta); 
             handler.setMouse(p);
             delta -= 1.0; 
           } 
           if (running) 
             render(); 
           frames++; 
                
           if (System.currentTimeMillis() - timer > 1000) 
           { 
             timer += 1000; 
             time = (int) (runningTime + 0.5); 
             System.out.println("Frames per second: " + frames); 
             frames = 0; 
           } 
           
           time = SpecialAlgorithms.roundToHundredths(runningTime - pauseRunningTime);                                 // Used by the handler to know when to spawn enemies
       
     }
     
     stop(); 
   } 
  
  
  
  
  
    
  private void tick(double delta) 
   { 
     handler.tick(delta); 
   } 
    
  private void render() 
   { 
     BufferStrategy bs = getBufferStrategy(); 
     if (bs == null) 
     { 
       createBufferStrategy(3); 
       return; 
     } 
      
    Graphics g = bs.getDrawGraphics(); 
    
    
    Graphics2D g2 = (Graphics2D) g;
    RenderingHints rh = new RenderingHints(                  // antialiasing makes things smooth looking
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);  
    g2.setRenderingHints(rh);
    g.setColor(Color.WHITE); 
    g.fillRect(0, 0, 1600, 900); 
      
    handler.render(g2); 
    
    g.dispose(); 
    bs.show(); 
   } 
    
 
  
  public void clickEvent(MouseEvent e, int x, int y) 
  {
	  System.out.println("Click! At: " + x + ", " + y);
	  
	  if(handler.getMenuState().equals("mainMenu"))
	  {
		  menu.click(x, y);
	  }
	  else if(handler.getMenuState().equals("game"))
	  {
		  handler.setPlayerShooting(1);
	  }
	  
  }
  public void clickRelease(MouseEvent e, int x, int y) 
  {
	  System.out.println("Click realease! At: " + x + ", " + y);
	  
	  if(handler.getMenuState().equals("game"))
	  {
		  handler.setPlayerShooting(0);
	  }
	  
  }
  
  
  
  
  public boolean isRunning() 
   { 
     return running; 
   } 
  
  
 
  
  public void constructMainMenu()
  {
	  handler.setMenuState("mainMenu");
	  handler.setMenu(menu);
	  
	  System.out.println("Main Menu Constructed.");
  }
  
  public void constructGame()
  {
	  System.out.println("Game constructing..."); 
	  
      handler.setMenuState("game");
	  time = 0.0;
	  timeTimer = 1.0;                                                                                  // handler starts getting the time
	      
	  handler.addObject(new Ghost(0, 0, GameObjectID.Ghost, 0, 0.0, handler)); 
	  handler.addObject(new Player(800, 450, GameObjectID.Player, 25, 2.0, handler, "blue")); 
	    
	      
	  System.out.println("Game constructed."); 
	  gameRunning = true;
  }
  
  public int getMouseX()
  {
	  return mouseX;
  }
  public int getMouseY()
  {
	  return mouseY;
  }
  
  public static void main(String[] args) 
   { 
     boolean running = true; 
     while (running) 
     { 
       Game game = new Game(); 
       while (game.isRunning()) 
       { 
         if (!game.isRunning())  
        { 
           break; 
        } 
       } 
     } 
   } 
  
  
  public static void setDebugMode(boolean bool)
  {
	  debugMode = bool;
  }
  
  protected boolean gameRunning;
} 