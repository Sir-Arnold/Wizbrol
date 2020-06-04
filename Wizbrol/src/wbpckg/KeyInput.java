package wbpckg;

import java.awt.event.KeyEvent; 
import java.util.LinkedList; 
  
public class KeyInput extends java.awt.event.KeyAdapter 
{ 
   private Handler handler; 
   public boolean checkingPress; 
   public boolean checkingRelease; 
    
   private int leftIsDown; 
   private int rightIsDown; 
   private int upIsDown; 
   private int downIsDown; 
   private int shootIsDown; 
   private int clickIsDown;
    
   private int xSum; 
   private int ySum; 
    
   public KeyInput(Handler handler, Game game, Window window) 
   { 
      System.out.println("Key Input Constructing..."); 
      this.handler = handler; 
      System.out.println("Key Input Constructed."); 
      checkingPress = true; 
      checkingRelease = false; 
   } 
    
 
   public void keyPressed(KeyEvent e) 
   { 
     
      int key = e.getKeyCode(); 
	  switch(key)
	  {
	  	case KeyEvent.VK_P:
	  	{
	  		if(!handler.playerIsDead())
	      	{
	      	   if(handler.getMenuState().equals("game"))
	      		   handler.pause(); 
	      	   else
	      		   handler.unpause();
	      	}
	  		break;
	  	}
	  	case KeyEvent.VK_R:
	  	{
	  		handler.restart(); 
	        upIsDown = 0;
	        rightIsDown = 0;
	        leftIsDown = 0;
	        downIsDown = 0;
	        shootIsDown = 0;
	        break;
	  	}
	  	case KeyEvent.VK_G:
	  	{
	  		System.out.print("\n"); 
	        System.out.println("Game Objects: "); 
	        for (int j = 0; j < handler.objects.size(); j++) 
	        { 
	           GameObject thisObject = (GameObject)handler.objects.get(j); 
	           System.out.println(thisObject.getID()); 
	        } 
	        System.out.print("\n"); 
	        break;
	  	}
	  	case KeyEvent.VK_H:
	  	{
	  		Game.setDebugMode(!Game.debugMode);
	  		System.out.println("Debug Mode Now " + Game.debugMode);
	  		break;
	  	}
	  }
      
	  
      for (int i = 0; i < handler.objects.size(); i++) 
      { 
         GameObject tempObject = (GameObject)handler.objects.get(i); 
         if(tempObject.getID() == GameObjectID.Player && handler.getMenuState().equals("game"))
         { 
        	 switch(key)
             {
             	case (KeyEvent.VK_W):{
             		upIsDown = 1; 
             		break;
             	}
             	case (KeyEvent.VK_D):{
             		rightIsDown = 1;
             		break;
             	}
             	case (KeyEvent.VK_A):{
             		leftIsDown = 1; 
             		break;
             	}
             	case (KeyEvent.VK_S):{
             		downIsDown = 1; 
             		break;
             	}
             	case (KeyEvent.VK_SPACE):{
             		shootIsDown = 1; 
             		break;
             	}
             	case (KeyEvent.VK_F):{
             		System.out.println(tempObject.getID() + ":  " + " Heading: " + tempObject.getHeading() + " - Going: " + tempObject.getGoing() + " - Velocity of X: " + tempObject.getVelX() + ", Velocity for Y: " + tempObject.getVelY() + " X: " + tempObject.getX() + " Y: " + tempObject.getY()); 
                    handler.printMouseLoc();
             	}
             }
             keyInterpret(tempObject);
         } 
          
        
         
           
      
      } 
   } 
  
 
   public void keyReleased(KeyEvent e) 
   { 
      int key = e.getKeyCode(); 
         
      switch(key)
      {
      	case (KeyEvent.VK_W):{
      		upIsDown = 0; 
      		break;
      	}
      	case (KeyEvent.VK_D):{
      		rightIsDown = 0;
      		break;
      	}
      	case (KeyEvent.VK_A):{
      		leftIsDown = 0; 
      		break;
      	}
      	case (KeyEvent.VK_S):{
      		downIsDown = 0; 
      		break;
      	}
      	case (KeyEvent.VK_SPACE):{
      		shootIsDown = 0; 
      		break;
      	}
      }
      
      
      for (int i = 0; i < handler.objects.size(); i++) 
      { 
         GameObject tempObject = (GameObject)handler.objects.get(i); 
        
         if (tempObject.getID() == GameObjectID.Player) 
         { 
            keyInterpret(tempObject); 
          } 
        } 
    } 
     
   public void keyInterpret(GameObject tempObject)  // at this point we know exactly what buttons are being held down
   { 
	  xSum = rightIsDown - leftIsDown; 
	  ySum = upIsDown - downIsDown; 
      
        
      tempObject.accelerate(xSum, ySum); 
      tempObject.angle(0); 
      tempObject.shoot(shootIsDown); 
   } 
} 