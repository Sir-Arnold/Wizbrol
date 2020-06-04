package wbpckg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Challenger extends GameObject {

	public String name;
	public int type;
	public int health;
	
	protected double radius;
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
	
	protected double maxVel;
	protected double velTurn;
	
	protected Color color;
	
	
	public Challenger(int x, int y, GameObjectID id, int size, double speed)
	{
		super(x, y, id, size, speed);
		type = Game.cPhase;
	}
	
	public Challenger()
	{
		super(GameInfo.windowWidth/2, GameInfo.windowHeight/2, GameObjectID.Challenger, 25, 2.0);
		type = Game.cPhase;
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
	}
	
	public void buildBody()
	{
		VHX = (int) (x + radius * Math.cos(angle));
		VHY = (int) (y + radius * Math.sin(angle));
		
		VHXH = (int) (x + (radius / 2) * Math.cos(angle));
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

	@Override
	public void angle(int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
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

	@Override
	public void shoot(int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(String paramString) {
		// TODO Auto-generated method stub
		
	}
	

}
