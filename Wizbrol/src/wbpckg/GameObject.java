package wbpckg;

import java.awt.Graphics;
import java.awt.Point; 

public abstract class GameObject 
{ 
   protected int x; 
   protected int y; 
   protected GameObjectID id; 
   protected double velX; 
   protected double velY; 
   protected int turnVel; 
   protected double heading; 
   protected double going; 
   protected int size; 
   protected int radius; 
   protected double angle;
   protected final double SPEED; 
   protected double apothom; 
    
  public GameObject(int x, int y, GameObjectID id, int size, double speed) { 
     this.x = x; 
     this.y = y; 
     this.id = id; 
     radius = size; 
     this.size = size; 
     SPEED = speed; 
   } 
    
 
 
 
 
  public abstract void tick(double paramDouble); 
    
 
 
 
 
  public abstract void render(Graphics paramGraphics); 
    
    
 
 
 
  public abstract void angle(int paramInt); 
  public abstract double getAngle();
  public abstract void setMouseLoc(Point p);
  public abstract void accelerate(int paramX, int paramY);
    
 
 
 
 
  public abstract void shoot(int paramInt); 
    
 
 
 
  public abstract void setColor(String paramString); 
    
 
 
 
  public int getX() 
   { 
     return x; 
   } 
    
  public int getRadius() 
   { 
     return radius; 
   } 
    
  public double getApothom() 
   { 
     return apothom; 
   } 
  public int getY() 
   { 
     return y; 
   } 
    
  public GameObjectID getID() 
   { 
     return id; 
   } 
    
  public double getHeading() 
   { 
     return heading; 
   } 
    
  public double getVelX() 
   { 
     return velX; 
   } 
    
  public double getVelY() 
   { 
     return velY; 
   } 
    
  public double getGoing() 
   { 
     return going; 
   } 
    
 
  public void setVelX(int velX) 
   { 
     this.velX = velX; 
   } 
    
  public void setVelY(int velY) 
   { 
     this.velY = velY; 
   } 
    
  public void setHeading(int heading) 
   { 
     this.heading = heading; 
   } 
    
  public void setX(int x) 
   { 
     this.x = x; 
   } 
    
  public void setY(int y) 
   { 
     this.y = y; 
   } 
    
  public void setID(GameObjectID id) 
   { 
     this.id = id; 
   } 
    
  public void setGoing(double going) 
   { 
     this.going = going; 
   } 
} 
