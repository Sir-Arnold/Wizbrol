package wbpckg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Vector;

public class enemySentry extends GameObject
{	
	private Handler handler;
	
	protected double radius;
	protected double halfRadius;
	protected double speed;
	protected double speedGuage;
	protected double angle;
	protected double velX; protected double velY;
	protected double maxVel;
	protected int turnDirection; //
	protected double velTurn;
	protected double velTurnMax;
	protected double turnSensitivity;
	protected double angleChange; //
	protected double acceleration; ////
	protected double direction;
	protected Color color;
	protected double minTimeToNextShot;
	protected int bulletCoolDownIncrement;
	
	protected int shooting;
	
	protected double delta;
	
	protected double bulletCoolDown;
	
	private int VHX, VHY;
	private int VHXH, VHYH;
	private int VAX, VAY;
	private int VBX, VBY;
	private int VCX, VCY;
	private int VDX, VDY;
	private int VEX, VEY;
	private int VFX, VFY;
	
	int[] xArray = new int[8];
	int[] yArray = new int[8];
	
	public enemySentry(int x, int y, GameObjectID id, int size, double speed, Handler handler, String COLOR)
	{
		super(x, y, id, size, speed);
		
		radius = Math.sqrt(((0) * (0)) + ((size - 0) * (size - 0)));
		halfRadius = radius / 2;
		apothom = Math.cos(Math.toRadians(45));
		angle = 0;
		
		delta = 1;

		velX = 0;
		velY = 0;
		
		velTurn = 0.001;
		maxVel = 0.05;
		
		angleChange = 0.2;
		
		
		if(COLOR.equals("orange"))
			this.color= Color.orange;
		else if(COLOR.equals("red"))
			this.color = Color.red;
		else if(COLOR.equals("white"))
			this.color = Color.white;
		else if(COLOR.equals("cyan"))
			this.color = Color.cyan;
		
		bulletCoolDown = 150;
		bulletCoolDownIncrement = 2;
		minTimeToNextShot = 50;
		
		shooting = 1;
		
		speedGuage = 0.75;
		turnSensitivity = 0.75;
		
		buildBody();
		
		this.handler = handler;
		
		
	}
	
	public void tick(double delta)
	{	
		
		velTurn = approach(maxVel, velTurn, delta);
		angle += velTurn * delta;
		
		if((angle > 2*Math.PI))
			angle -= 2*Math.PI;
		else if((angle < 2*Math.PI))
			angle += 2*Math.PI;
		
		
		
		buildBody();
		
		if(bulletCoolDown > 0)
		{
			bulletCoolDown -= delta;
		}
		else
		{
			bulletCoolDown = 0;
			
			if(shooting == 1)
			{
				makeProjectile(x, y, angle);
				
				bulletCoolDown = 100 - bulletCoolDownIncrement;
				if(bulletCoolDown <= minTimeToNextShot)
				{
					bulletCoolDown = minTimeToNextShot;
				}
				else
				{
					bulletCoolDownIncrement++;
				}
				//System.out.println(angle);
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(this.color);
		Polygon tempShape = new Polygon(xArray, yArray, 8 );
		//System.out.print(5);
		g.drawPolygon(tempShape);
		g.fillPolygon(tempShape);
	}
	
	public double approach(double goal, double current, double deltaTime)
	{
		double difference = goal - current;
		
		if(difference > deltaTime)
			return current + deltaTime;
		else if(difference < -deltaTime)
			return current - deltaTime;
		else
			return goal;
	}
	
	
	public void accelerate(int x, int y)
	{
		
	}
	
	public void angle(int direction)
	{
		
	}
	
	public void buildBody()
	{
		VHX = (int) (x + radius * Math.cos(angle));                    // head point
		VHY = (int) (y + radius * Math.sin(angle));
		
		VHXH = (int) (x + (radius / 2) * Math.cos(angle));             // halfway to head point
		VHYH = (int) (y + (radius / 2) * Math.sin(angle));
		
		VHX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[0];                         
		VHY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[1];
		
		VAX = SpecialAlgorithms.arcRotation(VHXH, VHYH, x, y, 90)[0];
		VAY = SpecialAlgorithms.arcRotation(VHXH, VHYH, x, y, 90)[1];
		
		VBX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 90)[0];
		VBY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 90)[1];
		
		VCX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 155)[0];
		VCY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 155)[1];
		
		VDX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 205)[0];
		VDY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 205)[1];
		
		VEX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 270)[0];
		VEY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 270)[1];
		
		VFX = SpecialAlgorithms.arcRotation(VHXH, VHYH, x, y, 270)[0];
		VFY = SpecialAlgorithms.arcRotation(VHXH, VHYH, x, y, 270)[1];
		
		VHX = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[0];
		VHY = SpecialAlgorithms.arcRotation(VHX, VHY, x, y, 0)[1];
		
		xArray[0] = (int) VHX; xArray[1] = (int) VAX; xArray[2] =  (int) VBX; xArray[3] = (int) VCX; xArray[4] = (int) VDX; xArray[5] = (int) VEX; xArray[6] = (int) VFX; xArray[7] = (int) VHX;
		yArray[0] = (int) VHY; yArray[1] = (int) VAY; yArray[2] =  (int) VBY; yArray[3] = (int) VCY; yArray[4] = (int) VDY; yArray[5] = (int) VEY; yArray[6] = (int) VFY; yArray[7] = (int) VHY;
	}
	
	public void shoot(int val)
	{
		shooting = 1;
	}
	
	public void makeProjectile(int x, int y, double angle)
	{
		this.handler.addObject(new Projectile((int) (x + radius * Math.cos(angle)) + 1, (int) (y + radius * Math.sin(angle)) + 1, GameObjectID.Projectile, 4, 5.0, angle, this.handler, GameObjectID.enemySentry));
	}

	@Override
	public void setColor(String COLOR) {
		this.color = Color.getColor(COLOR);
		
	}

	@Override
	public double getAngle() 
	{
		return 0;
	}

	@Override
	public void setMouseLoc(Point p) {
		// TODO Auto-generated method stub
		
	}

}