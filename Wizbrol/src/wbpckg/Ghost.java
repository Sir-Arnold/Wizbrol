package wbpckg;

import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.LinkedList; 
  
 
public class Ghost 
   extends GameObject 
{ 
   Graphics g;
   Handler handler; 
   //private int x = 0; 
   //private int y = 0; 
   private GameObjectID id = GameObjectID.Ghost; 
   //private int radius = 1; 
   //private double SPEED = 1.0D; 
    
   protected static float redVal; 
   protected int redMult; 
   protected static float greenVal; 
   protected int greenMult; 
   protected static float blueVal; 
   protected int blueMult; 
    
  public Ghost(int x, int y, GameObjectID id, int size, double SPEED, Handler handler) 
   { 
     super(x, y, id, size, SPEED); 
     x = 0; 
     y = 0; 
     id = GameObjectID.Ghost; 
     radius = 1; 
     SPEED = 1.0D; 
     
     redVal = 0.01F; 
     redMult = 1; 
     greenVal = 0.05F; 
     greenMult = 1; 
     blueVal = 0.07F; 
     blueMult = 1; 
      
    this.handler = handler; 
   } 
    
 
 
 
 
  public void tick(double delta) {} 
    
 
 
 
  public void render(Graphics g) 
   { 
     this.g = g;
	 redVal = (float)(redVal + 0.001D * redMult); 
     if (redVal > 0.995D) { 
       redMult = -1; 
     } else if (redVal < 0.005D) { 
       redMult = 1; 
     } 
     greenVal = (float)(greenVal + 0.002D * greenMult); 
     if (greenVal > 0.995D) { 
       greenMult = -1; 
     } else if (greenVal < 0.005D) { 
       greenMult = 1; 
     } 
     blueVal = (float)(blueVal + 0.003D * blueMult); 
     if (blueVal > 0.995D) { 
       blueMult = -1; 
     } else if (blueVal < 0.005D) { 
       blueMult = 1; 
     } 
     
     String[] differentFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 
     String comicSans = differentFonts[4];
     Font fontOne = new Font(comicSans, 0, 30); 
     g.setFont(fontOne); 
      
 
    Color color = new Color(redVal, greenVal, blueVal); 
      
    g.setColor(color); 
    
    if(!Handler.onlyPlayer)
    {
    	g.drawString(handler.handlerTime + "", GameInfo.getWindowWidth()/2, GameInfo.getWindowWidth()/22);
    }
    else if(handler.playerIsDead())
    {
    	g.drawString("Press \"R\" to restart", GameInfo.getWindowWidth()/2 - 100, GameInfo.getWindowHeight()/2); 
        String aFont = differentFonts[6]; 
        Font fontTwo = new Font(aFont, 0, 30); 
        g.setFont(fontTwo); 
        g.drawString("Your time was:    " + Handler.handlerTime + " seconds", GameInfo.getWindowWidth()/2 - 140, GameInfo.getWindowHeight() * 3/5);
    }
    
     
    if(handler.getSize() > 1) // Basically the same as if(!playerIsDead) except for the fact that if(!playerIsDead) turns up true when the player hasn't spawned yet, causing an error
    { 
    	g.drawLine(
   			  (int)((((GameObject)handler.objects.get(1)).getX()) + (((GameObject)handler.objects.get(1)).getRadius() * Math.cos(((GameObject)handler.objects.get(1)).getAngle()))),
   			  (int)((((GameObject)handler.objects.get(1)).getY()) + (((GameObject)handler.objects.get(1)).getRadius() * Math.sin(((GameObject)handler.objects.get(1)).getAngle()))),
   			  (int)((((GameObject)handler.objects.get(1)).getX()) + (45 * Math.cos(((GameObject)handler.objects.get(1)).getAngle()))),
   			  (int)((((GameObject)handler.objects.get(1)).getY()) + (45 * Math.sin(((GameObject)handler.objects.get(1)).getAngle())))
   			  );
    }
    
    if(Game.debugMode)
    {
        g.setColor(Color.black);
    	 
    	String defaultFont = differentFonts[1];
        Font fontThree = new Font(defaultFont, 0, 20);
        g.setFont(fontThree);
        g.drawString("Debug Mode On", 5, 20);
        fontThree = new Font(defaultFont, 0, 10);
        g.drawString("Line", 5, 60);
        g.drawString("Line", 5, 80);
        
        if(handler.getSize() > 1) // Basically the same as if(!playerIsDead) except for the fact that if(!playerIsDead) turns up true when the player hasn't spawned yet, causing an error
        { 
        	g.drawLine
        	(
        	    (int)((((GameObject)handler.objects.get(1)).getX()) + (((GameObject)handler.objects.get(1)).getRadius() * Math.cos(((GameObject)handler.objects.get(1)).getAngle()))),
        	    (int)((((GameObject)handler.objects.get(1)).getY()) + (((GameObject)handler.objects.get(1)).getRadius() * Math.sin(((GameObject)handler.objects.get(1)).getAngle()))),
        		(int)((((GameObject)handler.objects.get(1)).getX()) + (2000 * Math.cos(((GameObject)handler.objects.get(1)).getAngle()))),
        	    (int)((((GameObject)handler.objects.get(1)).getY()) + (2000 * Math.sin(((GameObject)handler.objects.get(1)).getAngle())))
        	);
        }
    }
    } 
    
 
 
 

 
  public static float getRedVal() 
   { 
     return redVal; 
   } 
    
  public static float getGreenVal() 
   { 
     return greenVal; 
   } 
    
  public static float getBlueVal() 
   { 
     return blueVal; 
   } 
    
  public void accelerate(int direction) {} 
    
  public void angle(int direction) {} 
    
  public void shoot(int val) {} 
    
  public void setColor(String color) {}





@Override
public double getAngle() 
{
	return 0;
}



@Override
public void setMouseLoc(Point p) {
	// TODO Auto-generated method stub
	
}





@Override
public void accelerate(int paramX, int paramY) {
	// TODO Auto-generated method stub
	
} 
} 