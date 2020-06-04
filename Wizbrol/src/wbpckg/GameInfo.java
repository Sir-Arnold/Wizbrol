package wbpckg;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GameInfo 
{
	public static int screenWidth;
	public static int screenHeight;
	
	public static int windowWidth;
	public static int windowHeight;
	
	public static int[] widthCodes;
	public static int[] heightCodes;
	
	public static void initialize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;
		
		windowWidth = (int) (screenWidth * 0.75);
		windowHeight = (int) (windowWidth * 9/16);
		
		resolveWidthCodes();
		resolveHeightCodes();
	}
	
	private static void resolveWidthCodes()
	{
		widthCodes = new int[65];
		for(int i = 0; i <= 64; i++)
		{
			widthCodes[i] = (int) (windowWidth * (i/64.0));
		}
	}
	private static void resolveHeightCodes()
	{
		heightCodes = new int[37];
		for(int i = 0; i <= 36; i++)
		{
			heightCodes[i] = (int) (windowHeight * (i/36.0));
		}
	}
	
	public static int getScreenWidth()
	{
		return screenWidth;
	}
	public static int getScreenHeight()
	{
		return screenHeight;
	}
	public static int getWindowWidth()
	{
		return windowWidth;
	}
	public static int getWindowHeight()
	{
		return windowHeight;
	}
	public static int[] getWidthCodes()
	{
		return widthCodes;
	}
	public static int[] getHeightCodes()
	{
		return heightCodes;
	}
}