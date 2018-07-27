/***
***Author: James Paolo W. Menguito
***Description: Horse race simulation using lambda expressions, parallel streams, and optionals
***/
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Optional;

import java.util.InputMismatchException;
public class Main {
	
	public static void main (String[] args) {
			
		List<Horse> horses = new ArrayList<Horse>();
		List<String> warcry = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);

		int choice = 1;
		int horses_count;
		int max_distance;
		
		warcry = initialize(warcry);
		
		System.out.println(warcry.toString());
		//main loop
		while(choice!=0) {
			System.out.println("Horse Race");			
			
			System.out.print("Enter number of horses: ");
			horses_count = inputInt();
			if(!checkInput(horses_count)) {
				continue;
			}
			
		 	System.out.print("\nEnter max distance: ");
		 	max_distance = inputInt();
		 	if(!checkInput(max_distance)) {
		 		continue;
		 	 }
		 	
		 	//set up the horses
		 	horses = addHorses(warcry, horses_count, max_distance);
		 	
		 	//filter the unhealthy horses
		 	horses = filterHorses(horses);
		 	
		 	//If there are only 2 or less healthy horses, restart program
		 	if(horses.size()<=2) {
		 		//repeat loop
		 		continue;
		 	 }	  

		 	//prepare them for the race									
		 	startRace(horses);	//collect the horses after race finishes
		 	
		 	//After finishing, shout the war cry
		 	//shout(horses);
		 	
		 	//compute the distances
		 	computeDistance(horses);
		 	choice = 0;
		 	break;
		 } while(choice!=0);
	 }
	public static int inputInt(){
		try {
			Scanner scan = new Scanner(System.in);
			return scan.nextInt();
		 } catch(InputMismatchException e) {
			return -1;
		
		 }
	}
	
	public static List<Horse> addHorses(List<String> warcry,int horses_count, int max_distance){
		List<Horse> horses = new ArrayList<Horse>();
		Random rand = new Random();	
			//set up the horses
		 	for(int i=0;i<horses_count;i++) { 
				//Randomly select a warcry 
				int randomWarcry = rand.nextInt(4); 		
		 		String phrase = warcry.get(randomWarcry);
		 		
		 		//randomly assign speed and health status of horse
		 		int speed = rand.nextInt(10) + 1;
		 		boolean health = rand.nextBoolean();
		 		
		 		//add horse object
		 		horses.add(new Horse("horse"+(i+1),phrase,health,speed, max_distance));	
		 	 	
		 	 }
		return horses;
	 }
	 
		
	public static List<Horse> filterHorses(List<Horse> horses) {
		horses = horses.parallelStream()								//will open a parallel stream
		 		  					  	.filter(h ->	h.isHealthy())	//accept the horse stream, test each horse if healthy
		 		  					  	.map(h -> h.upperCasify())		//convert to upperCase names of healthy horses
		 		  					  	.collect(Collectors.toList());  //make into a list
		return horses;
	 }
	 
	public static void startRace(List<Horse> horses) {
		horses.parallelStream()						//will open a stream
		 			   .forEach(h -> h.race());					    //each horse will start to race
		 		       //.collect(Collectors.toList());	
		//return horses;
	 }
	public static List<String> initialize(List<String> warcry) {
		//initialize warcry		
		warcry.add("Victory !");
		warcry.add("YAAAAAAAAAS !");
		warcry.add("Praise the sun !");
		warcry.add(null);
		
		return warcry;
	 }
	public static void computeDistance(List<Horse> horses) {
		int total_distance = horses.stream()						//open a stream
								   
	 							   .map(h -> h.getDistance())	//get distances
	 							   .reduce((a, b) -> a + b).get();
	 							   //.sum();							//get sum
	 	System.out.println("Total distance travelled by all horses: "+total_distance+"m"); 			 
	
	 }
	public static void shout(List<Horse> horses) {
		horses.parallelStream()
		 	  .forEach(h -> h.shoutWarcry());
	
	 }
	public static boolean checkInput(int input) {
		return input>1;
	 }
	 
	

}
