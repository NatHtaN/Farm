package environment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Earth {
	public final static int WIDTH = 10000;
	public final static int HEIGHT = 100;
	private int goldRaven = 0;
	private int march;
	private static int steps;
	private static Land[][] lands = new Land[WIDTH][HEIGHT];
	public Earth(){
		march = 100;
	}
	public void cruise(){
		for (Land subLands : lands[goldRaven]) {
			subLands.setLighten(true);
		}
		for (Land subLands : lands[getTail()]) {
			subLands.setLighten(false);
		}
		//WIDTH - 1 -> 0
		goldRaven = ++goldRaven == WIDTH? 0: goldRaven;
		steps++;
	}
	private int getTail() {
		// TODO Auto-generated method stub
		return goldRaven > march ? goldRaven - march : goldRaven + WIDTH - march;
	}
	
}
class Land{
	private boolean isLighten;
	private boolean isBurdened;
	private Object appearance;
	private int engergyPool;
	private LinkedList<Object> burdenedStuff = new LinkedList<Object>();//every piece of land will burden layer by layer
	private Location location;
	public Land(int x, int y){
		this.setBurden(false);
		this.setLighten(false);
		location = new Location(x, y);
		add(this);
		add("mud");
	}
	public void add(Object o) {
		burdenedStuff.add(o);
		appearance = o;
	}
	public Object move(){
		Object o = burdenedStuff.removeLast();
		appearance = burdenedStuff.getLast();
		return o;
	}
	public boolean isLighten() {
		return isLighten;
	}
	public void setLighten(boolean isLighten) {
		this.isLighten = isLighten;
	}
	public boolean isBurdened() {
		return isBurdened;
	}
	public void setBurden(boolean isBurdened) {
		this.isBurdened = isBurdened;
	}
}