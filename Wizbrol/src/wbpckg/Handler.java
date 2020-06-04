package wbpckg;

import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.LinkedList; 
  
 
public class Handler 
{ 
	private String menuState;         // "mainMenu", "game", "gameMenu", "dead"
	Game game;
	MyMenu menu;
	public static LinkedList<GameObject> objects = new LinkedList<GameObject>(); 
	
	private Challenger currentChallenger;
	private double currentBufferTime;
	private boolean tickChallenger;
	private boolean cTitleIsDisplayed;
	public static boolean onlyPlayer;
	
	Point mouseLoc;
	
	private boolean eventOne;
   
	public static boolean showTimer;
    public static double handlerTime; 
    public double pauseTime;
    public int size;
    private int playerIsShooting;
    
   public Handler(Game game) 
   {
      System.out.println("Handler constructed.");
	  menuState = "mainMenu";
	  this.game = game;
	  menu = new MyMenu(this.game, this, "mainMenu");
	  currentBufferTime = 0;
	  cTitleIsDisplayed = false;
	  Handler.onlyPlayer = true;
	  
	  Handler.showTimer = false;
	  
	  eventOne=true;
   } 
    
 
   public void render(Graphics g) 
   { 
     
      if(menuState.equals("mainMenu"))
	  {
	     menu.render(g);
	  }
	  else
	  {
	     for (int i = 0; i < objects.size(); i++) 
	     { 
	        GameObject tempObject = (GameObject)objects.get(i); 
	        
	        tempObject.render(g); 
	     } 
	     
	 }
	 
   } 
 
   public void restartTimer()
   {
	   Game.startTime = System.currentTimeMillis(); 
	   Game.time = 0;
	   handlerTime = 0.0;
	   Game.pauseRunningTime = 0.0;
   }
   
   public void restart()
   { 
      size = 1;
 
      while (objects.size() > 1) 
      { 
         GameObject tempObject = (GameObject)objects.get(objects.size() - 1); 
         removeObject(tempObject); 
      } 
      
      Game.startTime = System.currentTimeMillis(); 
      Game.time = 0;
      handlerTime = 0.0;
      Game.pauseRunningTime = 0.0;
      eventOne = true;
      /* 
      eventFifteen = true; 
      eventTwenty = true; 
      eventTwentySeven = true; 
      eventThirtyFive = true; 
      eventFortyFive = true; 
      eventFiftyFive = true; 
      eventSixtyFive = true; 
      eventSeventyFive = true; 
      eventEightyFive = true; 
      eventNinetyFive = true; 
      eventHundredFive = true; 
      eventHundredTwenty = true;*/ 
      addObject(new Player(800, 450, GameObjectID.Player, 25, 2.0D, this, "blue")); 
      this.unpause();
   } 
    
   public void clearBoard() 
   { 
      if(!Game.debugMode)
      {
    	  while (objects.size() > 1)                                                // each iteration of this loop removes the last game object in the linkedlist "objects" until there is only the first object of the linkedlist remaining, which is always the "ghost" object 
          { 
             GameObject tempObject = (GameObject)objects.get(objects.size() - 1); 
             removeObject(tempObject); 
          } 
         
          size = objects.size();                                                    // profound, almost like size var doesn't hold much purpose when you have objects.size()
          this.pause();
      }
	  
   } 
    
   public void addObject(GameObject object) 
   { 
      objects.add(object); 
   } 
    
   public void removeObject(GameObject object) 
   { 
      objects.remove(object); 
   } 
   public void removeObject(int i) 
   { 
      objects.remove(i); 
   } 
  
   public void pause()
   {
	  pauseTime = handlerTime;
	  if(!playerIsDead())
	     menuState = "gameMenu";
	  else if(playerIsDead())
		 menuState = "dead";
		  
   }
   public void unpause()
   {
	  pauseTime = 0;
	  menuState = "game";
  }
  
  public boolean playerIsDead() 
  {
	  if (size == 1)
		  return true;
	  else
		  return false;
  }
  public int getSize()
  {
	  return objects.size();
  }
  public void setMenuState(String state)
  {
	  menuState = state;
  }
  public String getMenuState()
  {
	  return menuState;
  }
  public void setMenu(MyMenu menu)
  {
	  this.menu = menu;
  }
  public void setMouse(Point p)
  {
	  mouseLoc = p;
  }
  public void printMouseLoc()
  {
	  System.out.println(this.mouseLoc);  
  }
  
  public void setPlayerShooting(int paramInt)
  {
	  playerIsShooting = paramInt;
  }
  
  public static void setShowTimer(boolean bool)
  {
	  showTimer = bool;
  }
  
  public static boolean getShowTimer()
  {
	  return showTimer;
  }

  public void manageCStatus(GameObject aChallenger, double delta)                       // Decides what to do regarding Challengers
  {
	 //if(this.challengerIsDead()) {}
     
	 if(true)
     {
		 if(currentBufferTime >= 0)
	     {
			 currentBufferTime = handlerTime;
			 if(currentBufferTime >= 3 && cTitleIsDisplayed)
	         {
	        	 System.out.println("RANDOM PLACEHOLDER TEXT");
				 displayCTitle();
				 cTitleIsDisplayed = true;
	         }
	    	 else if(currentBufferTime >= 6)
	    	 {
	    		 System.out.println("Phase " + Game.cPhase + " has been initiated.");
	    		 this.restartTimer();
	    		 showTimer = true;
	    		 this.spawnNextChallenger();
	    		 Handler.onlyPlayer = false;
	        	 Game.cPhase += 1;               //
	        	 currentBufferTime = -1;
	    	 }
	     }
     }
  }
  
  public void displayCTitle()
  {
	   
  }
  
  public void spawnNextChallenger()
  {
	  
  }
  
  public void killChallenger()
  {
	  this.restartTimer();
	  currentBufferTime = 0;
  }
  
  public void tick(double delta)  
  {  
      if(true)
	  {
	     size = objects.size(); 
		 for (int i = 0; i < objects.size(); i++) 
	     {
         GameObject tempObject = (GameObject)objects.get(i);
			tempObject.tick(delta);
			if (tempObject.getID() == GameObjectID.Player)
			{
				tempObject.setMouseLoc(mouseLoc);
				tempObject.shoot(playerIsShooting);
			}
			else if(tempObject.getID() == GameObjectID.Challenger)
			{
				
			}
			else if(!(tempObject.getID() == GameObjectID.Challenger) && currentBufferTime == -1)
			{
				this.addObject(new Challenger());
			}
			
			handlerTime = Game.time;
			this.manageCStatus(tempObject, delta);
	     } 
	     
		 if(!Game.debugMode)
		 {
			 
			 
			 switch((int)handlerTime)
			 {
			 	case 1:
			 	{
			 		if(eventOne)
			 		{
			 			eventOne = false; 
				        
				        addObject(new enemySentry(GameInfo.getWidthCodes()[32], GameInfo.getHeightCodes()[18], GameObjectID.enemySentry, 200 , 1.0D, this, "red")); 
				        System.out.println("Event Seven Initiated. Codes: " + GameInfo.getWidthCodes()[5] + ", " + GameInfo.getHeightCodes()[3]); 
			 		}	
			 		break;
			 	}
			 	
			 	
			 }
		 }
	  }
  	}
}
	  
  
