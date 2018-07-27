/***
***Author: James Paolo W. Menguito
***Description: Horse entity for Horse race simulation
***/
import java.util.Optional;
import java.util.Random;
import java.time.LocalTime;
public class Horse{

	private String name;			//Horse name
	private Optional<String> optionalWarcry;			//Horse's warcry after finishing
	
	private int speed;				//Horse speed
	private int distance;			//Horse current distance
	private int max_distance;		//Max distance horse will travel
	private boolean isHealthy;		//checks if the horse is healthy or not
	private boolean isFinished;		//checks if the horse has reached the finish line
	
	public Horse(String name, String warcry, boolean isHealthy, int speed, int max_distance) {
		this.name = name;
		this.optionalWarcry = Optional.ofNullable(warcry);
		this.isHealthy = isHealthy;
		this.speed = speed;
		this.max_distance = max_distance;
	 
	 }
	 
	//Update the current distance of the horse 
	public void gallop() {
		Random rand = new Random();
		int newSpeed = rand.nextInt(10)+1;
		this.distance = distance + speed;
		speed = newSpeed;
	 }
	
	//Horse gallops until race is finished (current distance >= max distance); 
	public void race() {
		preRun();
		while(!isFinished()) {
			gallop();
			printStats();	
		 }
		
		finished();
		 //return this; 	
	 }
	 
	public Horse upperCasify() {
		setName(this.name.toUpperCase());
		return this;
	 }

	//Getters & Setters
	public String getName() { 
		return name;
	 }
	 
	public int getSpeed() {
	 	return speed;
	 }
	  
	public int getDistance() {
		return distance;
	 }

	public boolean isHealthy() {
		return isHealthy;
	 }
	
	public boolean isFinished() {
		return (max_distance <= distance);
	 }
	
	public Optional<String> getWarcry() {
		return optionalWarcry;
	 }
	 
	 public void setName(String name) { 
		this.name = name;	 
	 }
	public int distanceLeft() { 
		return max_distance-distance;	
	 }
	//Some printing
	public void printStats() {
		System.out.println(Thread.currentThread().getName() + " " + LocalTime.now() + " " + this.name+" galloped "+this.speed+"m, Distance covered: "+ this.distance+", Distance left: "+distanceLeft());
	 }
	
	public void preRun() {
		System.out.println(this.name+" is racing. "+"[Healthy: "+isHealthy+" | Speed: "+speed+"m]");
	 }
	
	public void shoutWarcry() {
		System.out.println(this.name+": "+optionalWarcry.orElse("(stays quiet)"));
	 	
	 }
	public void finished() {
		System.out.println(this.name + " has finished racing");
	 }
 }
