package wbpckg;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList; 
  
public class Projectile extends GameObject 
{ 
   private Handler handler; 
   protected int x; 
   protected int y; 
   protected double xDub;
   protected double yDub;
   protected double velX;
   protected double velY;
   protected int size; 
   protected double speed; 
   protected double angle; 
   protected Color color; 
   protected float redVal;
   protected float greenVal;
   protected float blueVal;
   protected GameObjectID parentID; 
   protected int timer; 
   
   public static int runningID = 0;
   protected int ID;
   
  public Projectile(int x, int y, GameObjectID id, int size, double speed, double someAngle, Handler handler, GameObjectID parentID) 
  { 
    super(x, y, id, size, speed); 
     
    this.x = x; 
    this.y = y; 
    this.xDub = (double) x;
    this.yDub = (double) y;
    this.size = size; 
    this.speed = speed;
    this.angle = someAngle;
    this.velX = (speed * Math.cos(angle)); 
    this.velY = (speed * Math.sin(angle)); 
    super.velX = this.velX;
    super.velY = this.velY;
    timer = 600; 
    
     
    this.parentID = parentID; 
     
    if (parentID == GameObjectID.Player) 
    { 
      redVal = 0.10F;
      greenVal = 0.10F;
      blueVal = 0.90F;
    } else 
    { 
    	redVal = 0.999F;
        greenVal = 0.00F;
        blueVal = 0.00F;
    } 
    this.color = new Color(redVal, greenVal, blueVal);
    
    this.handler = handler; 
  } 
    
 
 
  public void tick(double delta) 
   { 
	  
	  if ((xDub + velX > 0.0D) && (xDub + velX < GameInfo.windowWidth)) 
     { 
       if ((yDub + velY > 0.0D) && (yDub + velY < GameInfo.windowHeight - 20)) 
       {
    	 xDub = ((xDub + velX * delta)); 
         yDub = ((yDub + velY * delta)); 
       } 
       else if (yDub + velY < 0.0D) 
       { 
         //velX = velX;
         velY *= -1;
       } 
       else if (yDub + velY > GameInfo.windowHeight - 20) 
       { 
         //velX = velX
         velY *= -1; 
       } 
    } 
     else if (x + velX < 0.0D) 
     { 
       velX *= -1;
       //velY = velY;
     } 
     else if (x + velX > GameInfo.windowWidth) 
     { 
       velX *= -1;
       //velY = velY;
     } 
      
	  xDub = ((xDub + velX * delta)); 
      yDub = ((yDub + velY * delta)); 
      
	  x = (int) xDub;
	  y = (int) yDub;
    checkTimer(); 
    checkCollision(); 
   } 
    
 
 
 
  public void checkCollision() 
   { 
     for (int i = 0; i < Handler.objects.size(); i++) 
     { 
       GameObject tempObject = (GameObject)Handler.objects.get(i); 
       if (tempObject.getID() == GameObjectID.Player) 
       { 
  
        if (Math.hypot(x - tempObject.getX(), y - tempObject.getY()) < tempObject.getRadius() - 5) 
         { 
           
         } 
       } 
       else if (tempObject.getID() == GameObjectID.Projectile)
       {
    	   if (Math.abs(x - tempObject.getX()) <= 2 && Math.abs(y - tempObject.getY()) <= 2) 
           { 
             //This didn't work
           } 
       }
        
 
 
 
 
 
      if (tempObject.getID() == GameObjectID.enemySentry) 
       { 
         if (parentID == GameObjectID.Player) 
         { 
           if (Math.hypot(x - tempObject.getX(), y - tempObject.getY()) < tempObject.getRadius() - 5) 
           { 
  
            handler.removeObject(tempObject); 
             handler.removeObject(this); 
           } 
         } 
       } 
     } 
   } 
    
 
 
  public void checkTimer() 
   { 
     if (timer > 210) 
     { 
       timer -= 1; 
       if (parentID == GameObjectID.Player && redVal <= 1.00F - 0.8/timer) 
       {
    	   redVal += 0.8/timer;
    	   greenVal += 0.8/timer;
       }
       else if(parentID == GameObjectID.enemySentry && blueVal <= 1.00F - 0.999/timer) 
       {
    	   blueVal += 0.999/timer;
    	   greenVal += 0.999/timer;
       }
       else
       {
    	   
       }
       
       this.color = new Color(redVal, greenVal, blueVal);
       
     } 
     else 
     { 
       handler.removeObject(this); 
     } 
   } 
    
  public void render(Graphics g) { 
     g.setColor(color); 
     g.drawArc(x, y, size, size, 0, 360); 
     g.fillArc(x, y, size, size, 0, 360); 
   } 
    
 
 
 
  public void angle(int direction) {} 
    
 
 
 
  public void shoot(int val) {} 
    
 
 
 
  public void accelerate(int x, int y) {} 
    
 
 
 
  public void setColor(String color) 
   { 
     this.color = Color.getColor(color); 
   }



@Override
public double getAngle() 
{
	return angle;
}

public int getX()
{
	return x;
}

public int getY()
{
	return y;
}



@Override
public void setMouseLoc(Point p) {
	// TODO Auto-generated method stub
	
} 
} 
