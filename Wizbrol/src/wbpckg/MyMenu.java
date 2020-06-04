package wbpckg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

// Menu Object with stored menu scheme, sub-menus

public class MyMenu 
{
	MyButton[] buttons;
	Game game;
	Handler handler;
	String type;
	int fontSize;
	
	public MyMenu(Game game, Handler handler, String type)
	{
		this.game = game;
		this.handler = handler;
		this.type = type;
		fontSize = 20;
		
		//startButton = new int[]{0, 0, 100, 100};                         // button is {x, y, width, height}
		
		if(type.equals("mainMenu"))
		{
			buttons = new MyButton[1];
			MyButton startButton = new MyButton(GameInfo.getWidthCodes()[32], GameInfo.getHeightCodes()[18], (int)GameInfo.windowWidth/6, (int)GameInfo.windowHeight/15, "Start Game");
			buttons[0] = startButton;
		}
		else
		{
			
		}
	}
	
	public void render(Graphics g)
	{
		String[] differentFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 
	    String comicSans = differentFonts[4];
	    Font sans = new Font(comicSans, 0, fontSize); 
	    
	    g.setFont(sans);
		g.setColor(Color.CYAN);
		
		//g.drawRoundRect(startButton[0], startButton[1], startButton[2], startButton[3], 5, 5);
		
		for(int i = 0; i < buttons.length; i++)
		{
			MyButton thisButton = buttons[i];
			drawButton(thisButton, g);
		}
	}
	
	public void click(int x, int y)
	{
		for(int i = 0; i < buttons.length; i++)
		{
			MyButton thisButton = buttons[i];
			if(thisButton.contains(x, y))
			{
				interpret(thisButton.getCommand());
			}
		}
	}
	
	public void interpret(String command)
	{
		if(command.equals("Start Game"))
		{
			game.constructGame();
		}
	}
	
	public void drawButton(MyButton button, Graphics g)
	{
		for(int i = 0; i < 5; i++)
		{
			g.drawRoundRect(button.leftBound - i, button.topBound - i, button.width + 2 * i, button.height + 2* i, 10 + i, 10 + i);
		}
		String command = new String(button.getCommand());
		g.drawString(command, (int)(button.x - fontSize/4 * command.length()), button.y + fontSize/4);
	}
	
	
}
