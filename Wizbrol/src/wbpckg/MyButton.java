package wbpckg;

public class MyButton 
{
	String command;
	
	public int width;
	public int height;
	
	public int x;
	public int y;
	
	public int leftBound;
	public int rightBound;
	public int topBound;
	public int bottomBound;
	
	public MyButton(int x, int y, int width, int height, String command)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.command = command;
		
		leftBound = (int) (x - width/2.0);
		rightBound = (int) (x + width/2.0);
		topBound = (int) (y - height/2.0);
		bottomBound = (int) (y + height/2.0);
	}
	
	public boolean contains(int x, int y)
	{
		if((x > leftBound) && (x < rightBound) && (y > topBound) && (y < bottomBound))
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
	
	public String getCommand()
	{
		return command;
	}
}