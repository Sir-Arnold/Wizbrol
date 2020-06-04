package wbpckg;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon; 
import java.util.LinkedList; 
import java.util.Vector; 
  
 
 
 
public class Player 
   extends GameObject 
{ 
   private Handler handler; 
   protected int radius; 
   protected double speed; 
   protected double speedGuage; 
   protected double angle; 
   protected double vel; 
   protected double maxVel; 
   protected double velXGoal; 
   protected double velYGoal; 
   protected double velGoal; 
   protected int turnDirection; 
   protected double velTurn; 
   protected double velTurnGoal; 
   protected double velTurnMax; 
   protected double angleChange; 
   protected double acceleration;
   protected double accelX;
   protected double accelY;
   protected double direction; 
   protected int shooting; 
   protected double delta; 
   protected double bulletCoolDown; 
   
   protected Point mouseLoc;
   
   protected boolean canShoot;
   
   private int VHX; 
   private int VHY; 
   private int VAX; 
   private int VAY; 
   private int VCX; 
   private int VCY; 
   private int VFX; 
   private int VFY; 
   private int VEX; 
   private int VEY; 
   
   private Color color; 
   
   int[] xArray = new int[6]; 
   int[] yArray = new int[6]; 
    
 
 
 
  public Player(int x, int y, GameObjectID id, int size, double speed, Handler handler, String color) 
   { 
    super(x, y, id, size, speed); 
      
    radius = ((int)Math.sqrt(0 + (size - 0) * (size - 0))); 
    apothom = Math.cos(Math.toRadians(45.0D)); 
    angle = 0; 
      
    delta = 1.0D; 
      
    vel = 0.0D; 
    velX = 0.0D; 
    velY = 0.0D; 
      
    velTurn = 0; 
    velTurnMax = 0.25; 
    mouseLoc = new Point(0, 0);
      
    maxVel = 100.0D; 
    acceleration = 0.75D; 
    
    angleChange = 0.005; 
      
    this.color = Color.blue; 
      
 
    shooting = 0; 
      
    speedGuage = 1.0D; 
      
    buildBody(); 
      
    this.handler = handler; 
   } 

 
   public void tick(double delta) 
   { 
    this.delta = delta; 
    
    
    move();
    
    turn(); 
 
    buildBody(); 
      
    if (bulletCoolDown > 0.0D) 
     { 
       bulletCoolDown -= delta; 
       color = Color.red; 
       canShoot = false;
     } 
     else 
     { 
       bulletCoolDown = 0.0D; 
       color = Color.blue; 
       canShoot = true;
        
      if (shooting == 1 && canShoot) 
       { 
         makeProjectile(x, y, angle); 
         bulletCoolDown = 20.0D; 
       } 
     } 
   } 
    
 
 
  public void render(Graphics g) 
   { 
     if ((handler.objects.size() > 1) && (id == GameObjectID.Player)) 
     { 
       Polygon tempShape = new Polygon(xArray, yArray, 6); 
       g.setColor(Color.CYAN); 
        
      g.drawPolygon(tempShape); 
       g.fillPolygon(tempShape); 
     } 
   } 
  
  public void move()
  {
	  if(accelX == 0)
	  {
		  if(velX > 0)
		  {
			  velX -= acceleration;
		  }
		  else if(velX < 0)
		  {
			  velX += acceleration;
		  }
	  }
	  else
	  {
		  velX += accelX;  
	  }
	  if(accelY == 0)
	  {
		  if(velY > 0)
		  {
			  velY -= acceleration;
		  }
		  else if(velY < 0)
		  {
			  velY += acceleration;
		  }
	  }
	  else
	  {
		  velY += accelY;
	  }
	  
	  
	  
	  
	  if(Math.abs(velX) > 11.25)
	  {
		  if(velX > 11.25)
			  velX = 11.25;
		  else if(velX < -11.25)
			  velX = -11.25;
	  }
	  
	  if(Math.abs(velY) > 11.25)
	  {
		  if(velY > 11.25)
			  velY = 11.25;
		  else if(velY < -11.25)
			  velY = -11.25;
	  }
	  
	  if((((x + velX > 0) && (x + velX < GameInfo.getWindowWidth()))) && (((y + velY > 0) && (y + velY < GameInfo.getWindowHeight()))))
	  {
		  x += velX;
		  y += velY;
	  }
	  else 
	  {
		  if(x + velX <= 0 || x + velX >= GameInfo.getWindowWidth())
			  accelX = 0;
		  if(y + velY <= 0 || y + velY >= GameInfo.getWindowHeight())
			  accelY = 0;  
	  }
	  
	  
  }
    
 
  public double approach(double goal, double current, double deltaTime) 
   { 
     double difference = goal - current; 
      
    if (difference > deltaTime) 
       return current + deltaTime; 
     if (difference < -deltaTime) { 
       return current - deltaTime; 
     } 
     return goal; 
   } 
    
  public void resolveAcceleration()
  {
	  
  }
  
  public void accelerate(int x, int y) 
  { 
	  accelX = x * acceleration;
	  accelY = -1 * y * acceleration;
	  
  } 
    
  public void angle(int direction) 
   { } 
  
  public void setMouseLoc(Point p)
  {
	  mouseLoc = p;
  }
  public void turn()
  {
	  int xOnScreen = x + ((GameInfo.getScreenWidth() - GameInfo.getWindowWidth())/2);
	  int yOnScreen = y + ((GameInfo.getScreenHeight() - GameInfo.getWindowHeight())/2);
	  double relX =  SpecialAlgorithms.roundToTenThousandths(mouseLoc.x - xOnScreen);
	  double relY =  SpecialAlgorithms.roundToTenThousandths(mouseLoc.y - yOnScreen);
	  double distance = (int)Math.sqrt(Math.pow(relX, 2)+ Math.pow(relY, 2));
	  if(relX > 0)
	  {
		  angle = SpecialAlgorithms.roundToTenThousandths(Math.asin(relY/(distance + 0.001)));
	  }
	  else if(relX < 0 && relY > 0)
	  {
		  angle = SpecialAlgorithms.roundToTenThousandths(Math.acos((relX/(distance + 0.001))));
	  }
	  else
	  {
		  angle = (Math.PI) + -1 * SpecialAlgorithms.roundToTenThousandths(Math.asin((relY/(distance + 0.001))));
	  }
  }
  public void buildBody() 
   { 
     VHX = ((int)(x + (radius * Math.cos(angle)))); 
     VHY = ((int)(y + (radius * Math.sin(angle)))); 
      
    VHX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[0]; 
     VHY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[1]; 
      
    VAX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, -135)[0]; 
     VAY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, -135)[1]; 
      
    VCX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, -110)[0]; 
     VCY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, -110)[1]; 
  
    VFX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 110)[0]; 
     VFY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 110)[1]; 
      
    VEX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 135)[0]; 
     VEY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 135)[1]; 
      
    VHX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[0]; 
     VHY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[1]; 
      
    xArray[0] = VHX;xArray[1] = VCX;xArray[2] = VAX;xArray[3] = VEX;xArray[4] = VFX;xArray[5] = VHX; 
     yArray[0] = VHY;yArray[1] = VCY;yArray[2] = VAY;yArray[3] = VEY;yArray[4] = VFY;yArray[5] = VHY; 
   } 
    
  public void shoot(int val) 
   { 
     shooting = (val == 1) ? 1:0;
   } 
    
  public void makeProjectile(int x, int y, double angle) 
   { 
     handler.addObject(new Projectile((int)(x + radius * Math.cos(angle)) + 2, (int)(y + radius * Math.sin(angle)) + 2, GameObjectID.Projectile, 4, 6.0D, angle, handler, GameObjectID.Player)); 
   } 
    
  public void setColor(String color) 
   { 
     this.color = Color.getColor(color); 
   }
  
  public boolean canShoot()
  {
	  return canShoot;
  }






@Override
public double getAngle() 
{
	return angle;
} 
} 
