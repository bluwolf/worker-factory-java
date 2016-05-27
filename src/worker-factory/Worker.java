package xiaohanc_CSCI201_Assignment5a;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Worker  extends JLabel implements Runnable{
	
	int x = 20;
    int y = 20; 
    boolean isIdle=true;
    Point currentLocation = new Point(x,y);
    boolean turnFlag = false;
    ArrayDeque<String> instructions = new ArrayDeque<String>();
	Station s = null;
   
    Point startPoint = new Point (20,20) ;
	 Point woodP = new Point(200,40);
	 Point metalP = new Point (340,40);
	 Point plasticP = new Point (480,40);
	 Point screwP = new Point (70,130);
	 Point hammerP = new Point (70,205);
	 Point brushP = new Point (70,280);
	 Point pliersP = new Point (70,355);
	 Point scissorsP = new Point (70,430);
	 Point boardP= new Point(600,100);
	 Vector<Station> stations;
	 String from;
	 int step=0;
	 boolean going = false;
	 
	 int waitTime = 0;
	 
	 Lock lock = new ReentrantLock();
	 
    Worker() throws IOException{
    	instructions = new ArrayDeque<String>();
        BufferedImage pic = ImageIO.read(new File("worker.png"));
		 Image buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 ImageIcon image = new ImageIcon(buffered);
		 setIcon(image);
		 setSize(50,50);
		 setLocation(startPoint);
		
		 this.stations = DrawingPanel.stations;
		 
//		 combination();

    }
    
    Point currentLoc(){   	
		return new Point(this.getLocation()); 	
    }
    
//    public Point combination(){
//    	if(!turnFlag){
//    		initialToBoard();
//    		if(x==600) turnFlag=true;
//    	}
//
//    	if(turnFlag){
//    		boardToPlastic();
//    	}
//		return new Point(x,y);
//    	
//    }
    public Point initialToBoard(){
    	if(x ==600){
    		
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y<100) y+=1;  	
        x+=1;     
        this.setLocation(new Point(x,y));
		return new Point(x,y);  

//		return initialToBoard();  
    }
    public Point boardToWood(){
    	if(y==40){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==200){
    		y-=1;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        x-=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point boardToMetal(){
    	if(currentLoc().equals(metalP)){
    		going=false;
    		System.out.println("Goign on the inside"+going);
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==40){

    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==340){
    		y-=1;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        x-=1;   
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point materialstoTools(Point destination){
    	if(currentLoc().equals(destination)){
    		going=false;
    		this.setLocation(new Point(x,y));
    	}
    	if(x==destination.getX()){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);
    }
    public Point toolstoTools(Point destination){
    	if(currentLoc().equals(destination)){
    		going=false;
    		this.setLocation(new Point(x,y));
    	}
    	if(y<destination.getY()){
    		y+=1;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	else{
    		y-=1;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    }

    public Point upperStationstoTools(Point destination){
    	if(currentLoc().equals(destination)){
    		going=false;
    		this.setLocation(new Point(x,y));
    	}

    	if((currentLoc().getX())==destination.getX()){
    		if(y<destination.getY()){
    			y+=1;
    		}
    		else{
    			y-=1;
    		}
    		this.setLocation(new Point(x,y));
     		return new Point(x,y);
    	}
    	if(y==225){
    		x-=1;
    		this.setLocation(new Point(x,y));
     		return new Point(x,y);
    	}
    	if(y<225){
    		y+=1;
    		this.setLocation(new Point(x,y));
     		return new Point(x,y);
    	}
    	this.setLocation(new Point(x,y));
 		return new Point(x,y);
   	
    }
	    public Point middleStationstoTools(Point destination){
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    	}

	    	if((currentLoc().getX())==destination.getX()){
	    		if(y<destination.getY()){
	    			y+=1;
	    		}
	    		else{
	    			y-=1;
	    		}
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y==225){
	    		x-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y>225){
	    		y-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	this.setLocation(new Point(x,y));
	 		return new Point(x,y);
	    	
	    	
	    }
	    public Point lowerStationstoTools(Point destination){
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    	}

	    	if((currentLoc().getX())==destination.getX()){
	    		if(y<destination.getY()){
	    			y+=1;
	    		}
	    		else{
	    			y-=1;
	    		}
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y==490){
	    		x-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y<490){
	    		y+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	this.setLocation(new Point(x,y));
	 		return new Point(x,y);
	    	
	    }
	    public Point toolstoUpperStations(Point destination){
	    	System.out.println("in tools to upperStatoins");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    	}
	       	if(x==destination.getX()){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	       	}
	       	if(y==100){
	       		x+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	       	}
	    	if(y>100){
	    		y-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y<100){
	    		y+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y); 
	    	
	    }
		public Point toolstomiddleStations(Point destination){
			System.out.println("in tools to middle Stations");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    	}
	       	if(x==destination.getX()){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	       	}
	       	if(y==225){
	       		x+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	       	}
	    	if(y>225){
	    		y-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y<225){
	    		y+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y);  
			    	
		}
		public Point toolstolowerStations(Point destination){
			System.out.println("in tools to lower Statoins");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    	}
	       	if(x==destination.getX()){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	       	}
	       	if(y==490){
	       		x+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	       	}
	    	if(y>490){
	    		y-=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	if(y<490){
	    		y+=1;
	    		this.setLocation(new Point(x,y));
	     		return new Point(x,y);
	    	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y);  
		 	
		}

		public Point upperStationstoMiddleStations(Point destination){
			System.out.println("in upperstations to middle statiosn");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x == destination.getX()){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	
	       	if(y==255){
	       		if(x>destination.getX()){
	       			x-=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       		else{
	       			x+=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       	}
	       	if(y<225){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y);		 	
		}
		public Point materialtoMaterial(Point destination){
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x == destination.getX()){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	if(y==100){
	       		if(x>destination.getX()){
	       			x-=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       		else{
	       			x+=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);	       			
	       		}
	       	}
	       	y+=1;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
			
		}
		public Point stationtoSameRow(Point destination){
			if(y>0){
			System.out.println("in station to same row");}
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x ==destination.getX()){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	
	       	if(destination.getY()<225 && y ==100){
	       		if(x>destination.getX()){
	       			x-=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       		else{
	       			x+=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       	}
	       	if(destination.getY()>225 && destination.getY()<350 && y==225){
	       		if(x>destination.getX()){
	       			x-=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       		else{
	       			x+=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}

	       	}
	       	if(destination.getY()>350 && y ==350){
	       		if(x>destination.getX()){
	       			x-=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       		else{
	       			x+=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}

	       	}
	       	y-=1;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);

		}
		boolean turnFlag1 = false;
		public Point upperStationstoLowerStations(Point destination){
			System.out.println("in upper stations to lower stations");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		turnFlag1 = false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x==destination.getX() && turnFlag1==true){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}	       	
	       	if(y==490){
	       		x+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}	       	
	       	if(x == 70){
	       		turnFlag1=true;
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	
	       	if(y==255){
	       		x-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	if(y<225){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y);		 	
		}
		public Point middleStationstoOtherStations(Point destination){
			System.out.println("in middle stations to other statiosn");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x==destination.getX() && destination.getY()>350){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	if(x==destination.getX() && destination.getY()<225){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	
	       	if(y==225){
	       		if(x<destination.getX()){
	       			x+=1;
	        		this.setLocation(new Point(x,y));
	        		return new Point(x,y);
	       		}
	       		else{
	       			x-=1;
	        		this.setLocation(new Point(x,y));
	        		return new Point(x,y);
	       		}
	       	}
	       	if(y==350){
	       		if(x<destination.getX()){
	       			x+=1;
	        		this.setLocation(new Point(x,y));
	        		return new Point(x,y);
	       		}
	       		else{
	       			x-=1;
	        		this.setLocation(new Point(x,y));
	        		return new Point(x,y);
	       		}
	       	}
	       	
	       	if(y<destination.getY()){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	if(y>destination.getY()){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       		
	       	}
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
		 	
		}
		public Point lowerStationstoMiddleStations(Point destination){
			System.out.println("in lower stations to middle statiosn");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x == destination.getX()){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	
	       	if(y==350){
	       		if(x>destination.getX()){
	       			x-=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       		else{
	       			x+=1;
		    		this.setLocation(new Point(x,y));
		    		return new Point(x,y);
	       		}
	       	}
	       	if(y>350){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y);
		 	
		}
		
		public Point lowerStationstoUpperStations(Point destination){
			System.out.println("in lowerstations to upper stations");
	       	if(currentLoc().equals(destination)){
	    		going=false;
	    		turnFlag1 = false;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	    	}
	       	if(x==destination.getX() && turnFlag1==true){
	       		y+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}	       	
	       	if(y==100){
	       		x+=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}	       	
	       	if(x == 70){
	       		turnFlag1=true;
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	
	       	if(y==350){
	       		x-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	       	if(y>350){
	       		y-=1;
	    		this.setLocation(new Point(x,y));
	    		return new Point(x,y);
	       	}
	    	this.setLocation(new Point(x,y));
			return new Point(x,y);	
		}
		
	    
	    
    public Point boardToPlastic(){
    	if(y==40){
    		going=false;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==480){
    		y-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        x-=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point plastictoHammer(){
    	if(currentLoc().equals(hammerP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point woodtoHammer(){
    	if(currentLoc().equals(hammerP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point metaltoHammer(){

    	if(currentLoc().equals(hammerP)){   
        	going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point woodtoScrew(){
    	if(currentLoc().equals(screwP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point metaltoScrew(){
    	if(currentLoc().equals(screwP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point plastictoScrew(){
    	if(currentLoc().equals(screwP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point plastictoBrush(){
    	if(currentLoc().equals(brushP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point woodtoBrush(){
    	if(currentLoc().equals(brushP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point metaltoBrush(){
    	if(currentLoc().equals(brushP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point metaltoPliers(){
    	if(currentLoc().equals(pliersP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point woodtoPliers(){
    	return metaltoPliers();  	
    }
    public Point plastictoPliers(){
    	return metaltoPliers();  	
    }
    public Point metaltoScissors(){
    	if(currentLoc().equals(scissorsP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==70){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==100){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}

        y+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y);    	
    }
    public Point woodtoScissors(){
    	return metaltoScissors ();  	
    }
    public Point plastictoScissors(){
    	return metaltoScissors() ;  	
    }
    public Point screwtoAnvil(Point p){
    	if(currentLoc().equals(p)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==p.getX()){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        x+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    public Point hammertoAnvil(Point p){
    	if(currentLoc().equals(p)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==p.getX()){
    		y-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        x+=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    public Point brushtoAnvil(Point p){
    	if(currentLoc().equals(p)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==p.getY()){
    		x+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        y-=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    public Point pliertoAnvil(Point p){
    	return brushtoAnvil(p);
    }
    public Point scissorstoAnvil(Point p){
    	return brushtoAnvil(p);
    }
    boolean leftFlag;
    public Point anviltoFurnace(Point p){
    	if(currentLoc().equals(p)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==p.getX()){ 
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	
    	else{

    		if(p.getX()>250){
    			leftFlag = true;
    		}else{
    			leftFlag = false;
    		}
    		if(leftFlag){
    			if(x==p.getX()){
    				y+=1;
    				this.setLocation(new Point(x,y));
    		    	return new Point(x,y);
    			}
    			if(y==230){
    				x-=1;
    				this.setLocation(new Point(x,y));
    		    	return new Point(x,y);
    			}
    		}else{
    			if(x==p.getX()){
    				y+=1;
    				this.setLocation(new Point(x,y));
    		    	return new Point(x,y);
    			}
    			if(y==230){
    				x+=1;
    				this.setLocation(new Point(x,y));
    		    	return new Point(x,y);
    			}
    			
    		}
    		y+=1;
//    		
    	}
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    public Point furnacetoHammer(Point from){
    	if(currentLoc().equals(hammerP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(y==hammerP.getY()){
    		x-=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        y-=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    public Point anviltoPliers(){
    	if(currentLoc().equals(pliersP)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==pliersP.getX()){
    		y+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        x-=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    
    public Point pliertoWorkbench(Point p){

    	if(currentLoc().equals(p)){
    		going=false;
    		this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
    	if(x==p.getX()){
    		y+=1;
   		 this.setLocation(new Point(x,y));
   		return new Point(x,y);
    	}
    	if(y==100){
    		x+=1;
    		 this.setLocation(new Point(x,y));
    		return new Point(x,y);
    	}
        y-=1; 
        this.setLocation(new Point(x,y));
		return new Point(x,y); 
    	
    }
    
 
    
    public synchronized void  makeMove(String aLine) throws InterruptedException{
//		String object = aLine.substring(aLine.indexOf("[") + 1, aLine.indexOf("]"));
//		String amt = aLine.substring(aLine.indexOf("x") + 1, aLine.length());
//		amt =amt.replaceAll("\\s+",""); 
//		int amount =Integer.parseInt(amt);
//		aLine = instructions.getFirst();
				try{
					aLine = instructions.getFirst();
//					System.out.println("aLine "+aLine);
				}
				catch(Exception e){
//					System.out.println("End of Job");
				}

    			int k=0;
				if(currentLoc().equals(boardP) && going ==false){
					from = "board";	
					TaskBoard.data[k++][1]="in Progress";
					going=true;
				}

				if(currentLoc().equals(woodP) && going ==false){
					from = "wood";	
					going=true;
				}
				if(currentLoc().equals(metalP) && going ==false){
					from = "metal";
					going=true;
					
				}
				if(currentLoc().equals(plasticP) && going ==false){
					from = "plastic";
					going=true;
				}
				if(currentLoc().equals(screwP) && going ==false){
					from = "screw";
					going=true;
				}
				if(currentLoc().equals(hammerP) && going ==false){
					from = "hammer";
					going=true;
				}
				if(currentLoc().equals(pliersP) && going ==false){
					from = "plier";
					going=true;
				}
				if(currentLoc().equals(brushP) && going ==false){
					from = "brush";	
					going=true;
				}
				if(currentLoc().equals(scissorsP) && going ==false){
					from = "scissors";
					going=true;
				}
				for(int i=0;i<stations.size();i++){ // get 
					if(currentLoc().equals(stations.get(i).locationP) && going ==false){
						from = stations.get(i).type;
						going=true;
					}
				}
				
				if(aLine.contains("Metal")){
					if(from.equals("board")){
						going = true;
						boardToMetal();
						going = false;
					}	
					else{
						materialtoMaterial(metalP);							
					}
					if(currentLoc().equals(metalP)){
						 String metalAmount = aLine.substring(aLine.indexOf(":") + 1, aLine.indexOf("]"));
						 int minusAmount = Integer.parseInt(metalAmount);
						 int originalAmount = Integer.parseInt(DrawingPanel.metalAmount.getText());
						 originalAmount = originalAmount-minusAmount;
						 DrawingPanel.metalAmount.setText(Integer.toString(originalAmount));
						 
						 step++;
						 instructions.removeFirst();
	//						instructions.removeFirst();
					}
				}
					
		
				if(aLine.contains("Plastic")){
					if(from.equals("board")){
						going = true;
						boardToPlastic();
						going = false;
					}
					else{
						materialtoMaterial(plasticP);							
					}
					if(currentLoc().equals(plasticP)){
						 String plasticAmount = aLine.substring(aLine.indexOf(":") + 1, aLine.indexOf("]"));
						 int minusAmount = Integer.parseInt(plasticAmount);
						 int originalAmount = Integer.parseInt(DrawingPanel.plasticAmount.getText());
						 originalAmount = originalAmount-minusAmount;
						 DrawingPanel.plasticAmount.setText(Integer.toString(originalAmount));
							step++;
							instructions.removeFirst();
					}
					
				}
				if(aLine.contains("Wood")){
					if(from.equals("board")){
						going = true;
						boardToWood();
						going = false;
					}
					else{
						materialtoMaterial(woodP);							
					}
					if(currentLoc().equals(woodP)){
						String woodAmount = aLine.substring(aLine.indexOf(":") + 1, aLine.indexOf("]"));
						 int minusAmount = Integer.parseInt(woodAmount);
						 int originalAmount = Integer.parseInt(DrawingPanel.woodAmount.getText());
						 originalAmount = originalAmount-minusAmount;
						 DrawingPanel.woodAmount.setText(Integer.toString(originalAmount));
							step++;
							instructions.removeFirst();
					}
					
				}

//			System.out.println("current location: "+currentLoc());
//
//			System.out.println("from: "+from);
//			System.out.println("aLine: "+aLine);
//			System.out.println("Step: "+step);
//			System.out.println("Going? "+going);
			


			if(aLine.contains("x")){
				
				if(aLine.contains("Hammer")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).locationP).equals(currentLoc())){
							s = stations.get(i);
							p=s.locationP;
						}
					}
					if(currentLoc().equals(hammerP)){
						step++;
						instructions.removeFirst();						
					}					
					toHammer();
					
				}
				if(aLine.contains("Screwdriver")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).locationP).equals(currentLoc())){
							s = stations.get(i);
							p=s.locationP;
						}
					}
					if(currentLoc().equals(screwP)){
						step++;
						instructions.removeFirst();						
					}
					toScrewdrivers();
									
				}
				if(aLine.contains("Paintbrush")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).locationP).equals(currentLoc())){
							s = stations.get(i);
							p=s.locationP;
						}
					}
					if(currentLoc().equals(brushP)){
						step++;
						instructions.removeFirst();						
					}
					toBrushes();
				}
				if(aLine.contains("Pliers")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).locationP).equals(currentLoc())){
							s = stations.get(i);
							p=s.locationP;
						}
					}
					if(currentLoc().equals(pliersP)){
						step++;
						instructions.removeFirst();						
					}
					toPliers();
				
				}
				if(aLine.contains("Scissors")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).locationP).equals(currentLoc())){
							s = stations.get(i);
							p=s.locationP;
						}
					}
					if(currentLoc().equals(scissorsP)){
						step++;
						instructions.removeFirst();						
					}
					toScissors();
					
				}
	
			}
			else{
				if(aLine.contains("for")){{
					String stringTime = aLine.substring(aLine.indexOf("for") + 4, aLine.indexOf("s]"));
					
					waitTime = Integer.parseInt(stringTime);
//					System.out.println("WaitTime: "+ waitTime);
					
				}
				if(aLine.contains("Anvil")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).type).equals("anvil")&& !stations.get(i).occupied){
							s = stations.get(i);
							p= s.locationP;
						}
					}					

					if(currentLoc().equals(s.locationP)){
						step++;	
						s.occupied=true;
						try{
						instructions.removeFirst();	
						}catch (Exception e){
							System.out.println("Task Finished for one worker, Moving on to next task");
							step =0;
						}
						s.waitSeconds = waitTime;
						while(s.waitSeconds!=0){
							s.setTimeLabel();
							Thread.sleep(1000);
						}
						s.setTimeLabel();
						s.occupied=false;	
					
					}
					toAnvil(s.locationP);
					
					
				}
				if(aLine.contains("Workbench")){
					Point p = null;
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).type).equals("workbench")&& !stations.get(i).occupied){
							s = stations.get(i);
							p=s.locationP;
						}
					}

					if(currentLoc().equals(p)){
						step++;	
						s.occupied=true;
						try{
						instructions.removeFirst();	
						}catch (Exception e){
							System.out.println("Task Finished for one worker, Moving on to next task");
							step =0;
						}
						s.waitSeconds = waitTime;
//						new Thread(s).start();
						while(s.waitSeconds!=0){
							s.setTimeLabel();
							Thread.sleep(1000);
						}
						s.setTimeLabel();
						s.occupied=false;
				
					}
					toWorkbench(p);
					
				}
				
				if(aLine.contains("Furnace")){
	
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).type).equals("furnace")&& !stations.get(i).occupied){
							s = stations.get(i);
						}
					}

					if(currentLoc().equals(s.locationP)){
						step++;	
						s.occupied=true;
						try{
						instructions.removeFirst();	
						}catch (Exception e){
							System.out.println("Task Finished for one worker, Moving on to next task");
							step =0;
						}
						s.waitSeconds = waitTime;
//						new Thread(s).start();
						while(s.waitSeconds!=0){
							s.setTimeLabel();
							Thread.sleep(1000);
						}
						s.setTimeLabel();
						s.occupied=false;						
					}
					
					toFurnace(s.locationP);

					
				}
				
				if(aLine.contains("Tablesaw")){
					
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).type).equals("furnace")&& !stations.get(i).occupied){
							s = stations.get(i);
						}
					}

					if(currentLoc().equals(s.locationP)){
						step++;	
						s.occupied=true;
						try{
						instructions.removeFirst();	
						}catch (Exception e){
							System.out.println("Task Finished for one worker, Moving on to next task");
							step =0;
						}
						s.waitSeconds = waitTime;
//						new Thread(s).start();
						while(s.waitSeconds!=0){
							s.setTimeLabel();
							Thread.sleep(1000);
						}
						s.setTimeLabel();
						s.occupied=false;						
					}
					toTablesaw(s.locationP);
					
				}
				
				if(aLine.contains("Paintingstation")){
					
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).type).equals("furnace")&& !stations.get(i).occupied){
							s = stations.get(i);
						}
					}

					if(currentLoc().equals(s.locationP)){
						step++;	
						s.occupied=true;
						try{
						instructions.removeFirst();	
						}catch (Exception e){
							System.out.println("Task Finished for one worker, Moving on to next task");
							step =0;
						}
						s.waitSeconds = waitTime;
//						new Thread(s).start();
						while(s.waitSeconds!=0){
							s.setTimeLabel();
							Thread.sleep(1000);
						}
						s.setTimeLabel();
						s.occupied=false;						
					}
					toPaintingstation(s.locationP);
					
				}
				
				if(aLine.contains("Press")){
					
					for(int i=0;i<stations.size();i++){
						if((stations.get(i).type).equals("furnace")&& !stations.get(i).occupied){
							s = stations.get(i);
						}
					}

					if(currentLoc().equals(s.locationP)){
						step++;	
						s.occupied=true;
						try{
						instructions.removeFirst();	
						}catch (Exception e){
							System.out.println("Task Finished for one worker, Moving on to next task");
							step =0;
						}
						s.waitSeconds = waitTime;
//						new Thread(s).start();
						while(s.waitSeconds!=0){
							s.setTimeLabel();
							Thread.sleep(1000);
						}
						s.setTimeLabel();
						s.occupied=false;						
					}
					
					toPress(s.locationP);
					
				}
				
				}}


    }
	public void run() {
		String aLine = null;
		while(true){
		try{
			 aLine = instructions.getFirst();
		}
		catch(Exception e){
//			System.out.println("Job finished");
			this.isIdle =true;
		}

			
//			System.out.println(aLine);


			if(step==0){
//				System.out.println("aLine: " +aLine);
				initialToBoard();
				if(currentLoc().equals(boardP)){
					
		    		if(aLine.contains("Cog")){
		    			System.out.println("making cog");
		    			
		    		}
					if(aLine.contains("Gadget")){
						System.out.println("making gadget");
						
					}

					if(aLine.contains("Widget")){
						System.out.println("making widget");
					    			
					}
					step++;
//					instructions.removeFirst();
				}
			
			}
//			 System.out.println(step);
			switch(step){
			case 1:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 3:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 4:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 5:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 6:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 7:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 8:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 9:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 10:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 11:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 12:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 13:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 14:
				try {
					makeMove(aLine);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
//			if(step==1){
//				
////				
//			}
//			if(step==2){
//				makeMove(aLine);
////				instructions.removeFirst();
//			}
//			if(step==3){
//				makeMove(aLine);
////				instructions.removeFirst();
//			}
			 try { Thread.sleep(1); }  
	         catch (InterruptedException e) { System.err.println("sleep exception"); } 
//		 lock.unlock();
		}
	}

	public void toScrewdrivers(){
//		System.out.println("to screwdrivers");
		Point destinationPoint = screwP;
		if(from.equals("plastic") ||from.equals("metal")||from.equals("wood")){	
			going = true;
			materialstoTools(screwP);			
		}

		
		if(from.equals("scissors") ||from.equals("brush")||from.equals("hammer")||from.equals("plier")){
			going = true;			
			toolstoTools(destinationPoint);
//			going = false;
		}


		if(from.equals("workbench")||from.equals("anvil")){ 
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("furnace") || from.equals("tablesaw")){//
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}

		if(from.equals("painting")||from.equals("press")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
		
	}
	
	
	public void toHammer(){
		Point destinationPoint = hammerP;
		if(from.equals("plastic")){	
			going = true;
			plastictoHammer();
			
		}
		if(from.equals("metal")){
			going = true;
			metaltoHammer();
//			going = true;

		}
		if(from.equals("wood")){
			going = true;
			woodtoHammer();
//			going = true;
		}
				
		if(from.equals("screw")){
			going = true;			
			toolstoTools(destinationPoint);
		}
		if(from.equals("plier")){
			going = true;
			toolstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("brush")){
			going = true;
			toolstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("scissors")){
			going = true;
			toolstoTools(destinationPoint);
			going = true;
		}

		if(from.equals("anvil")){ // first row
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("workbench")){ 
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("furnace")){//
			going = true;
			furnacetoHammer(destinationPoint);
//			going = true;
		}
		if(from.equals("tablesaw")){
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("painting")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("press")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
	}
	public void toBrushes(){
		Point destinationPoint = brushP;
		if(from.equals("plastic") ||from.equals("metal")||from.equals("wood")){	
			going = true;
			materialstoTools(brushP);
			
		}
		if(from.equals("screw")){
			going = true;			
			toolstoTools(destinationPoint);
		}
		if(from.equals("plier")){
			going = true;
			toolstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("hammer")){
			going = true;
			toolstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("scissors")){
			going = true;
			toolstoTools(destinationPoint);
			going = true;
		}
		if(from.equals("anvil")){ // first row
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("workbench")){ 
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("furnace")){//
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("tablesaw")){
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("painting")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("press")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
		
	}
	
	public void toPliers(){
		Point destinationPoint = pliersP;
		if(from.equals("plastic") ||from.equals("metal")||from.equals("wood")){	
			going = true;
			materialstoTools(pliersP);
			
		}

		if(from.equals("screw")){
			going = true;			
			toolstoTools(destinationPoint);
		}
		if(from.equals("brush")){
			going = true;
			toolstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("hammer")){
			going = true;
			toolstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("scissors")){
			going = true;
			toolstoTools(destinationPoint);
			going = true;
		}
		if(from.equals("anvil")){
			going = true;
			anviltoPliers();
//			going = true;
		}
		if(from.equals("workbench")){ 
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("furnace")){//
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("tablesaw")){
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("painting")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("press")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
	}
	
	public void toScissors(){
		Point destinationPoint = scissorsP;
		if(from.equals("plastic") ||from.equals("metal")||from.equals("wood")){	
			going = true;
			materialstoTools(scissorsP);
			
		}
		
		if(from.equals("screw") ||from.equals("brush")||from.equals("hammer")||from.equals("plier")){
			going = true;			
			toolstoTools(destinationPoint);
//			going = true;
		}

		if(from.equals("anvil")){
			going = true;
			anviltoPliers();
//			going = true;
		}
		if(from.equals("workbench")){ 
			going = true;
			upperStationstoTools(destinationPoint);
//			going = true;
		}
		if(from.equals("furnace") || from.equals("tablesaw")){//
			going = true;
			middleStationstoTools(destinationPoint);
//			going = true;
		}

		if(from.equals("painting")||from.equals("press")){
			going = true;
			lowerStationstoTools(destinationPoint);
//			going = true;
		}
		
	}
	
	public void toAnvil(Point destination){
		if(from.equals("screw")){
			going = true;
			screwtoAnvil(destination);
			
		}
		if(from.equals("hammer")){
			going = true;
			hammertoAnvil(destination);
			

		}
		if(from.equals("plier")){
			going = true;
			pliertoAnvil(destination);
			
		}
		if(from.equals("brush")){
			going = true;
			brushtoAnvil(destination);
			
		}
		if(from.equals("scissors")){
			going = true;
			scissorstoAnvil(destination);						
		}
		
		if(from.equals("workbench")){
			going = true;
			stationtoSameRow(destination);
		}
		if(from.equals("furnace") || from.equals("tablesaw")){
			going = true;
			middleStationstoOtherStations(destination);
		}
		if(from.equals("painting") || from.equals("press")){
			going = true;
			lowerStationstoUpperStations(destination);
		}
	}
	
	public void toWorkbench(Point destination){
//		System.out.println("from: "+from);
		if(from.equals("plier")){
			going = true;
			pliertoWorkbench(destination);						
		}
		if(from.equals("brush") ||from.equals("screw") ||from.equals("scissors") ||from.equals("hammer") ){
			going = true;
			toolstoUpperStations(destination);
		}
		if(from.equals("anvil") ){
			going = true;
			stationtoSameRow(destination);
		}
		if(from.equals("furnace") || from.equals("tablesaw")){
			going = true;
			middleStationstoOtherStations(destination);
		}
		if(from.equals("painting") || from.equals("press")){
			going = true;
			lowerStationstoUpperStations(destination);
		}
	}
	
	public void toFurnace(Point destination){
		if(from.equals("anvil")){
			going = true;
			anviltoFurnace(s.locationP);
			
		}
		if(from.equals("brush") ||from.equals("screw") ||from.equals("scissors") ||from.equals("hammer")||from.equals("plier") ){
			going = true;
			toolstomiddleStations(destination);
		}
		if(from.equals("workbench") ){
			going = true;
			upperStationstoMiddleStations(destination);
		}
		if( from.equals("tablesaw")){
			going = true;
			stationtoSameRow(destination);
		}
		if(from.equals("painting") || from.equals("press")){
			going = true;
			lowerStationstoMiddleStations(destination);
		}
	}
	
	public void toTablesaw(Point destination){

		if(from.equals("brush") ||from.equals("screw") ||from.equals("scissors") ||from.equals("hammer")||from.equals("plier") ){
			going = true;
			toolstomiddleStations(destination);
		}
		if(from.equals("anvil") ||from.equals("workbench")){
			going = true;
			upperStationstoMiddleStations(destination);
		}
		if( from.equals("furnace") ){
			going = true;
			stationtoSameRow(destination);
		}
		if(from.equals("painting") || from.equals("press")){
			going = true;
			lowerStationstoMiddleStations(destination);
		}
	}
	public void toPaintingstation(Point destination){

		if(from.equals("brush") ||from.equals("screw") ||from.equals("scissors") ||from.equals("hammer")||from.equals("plier") ){
			going = true;
			toolstolowerStations(destination);
		}
		if(from.equals("anvil") ||from.equals("workbench")){
			going = true;
			upperStationstoLowerStations(destination);
		}
		if( from.equals("press") ){
			going = true;
			stationtoSameRow(destination);
		}
		if(from.equals("furnace") || from.equals("tablesaw")){
			going = true;
			middleStationstoOtherStations(destination);
		}
	}
	public void toPress(Point destination){

		if(from.equals("brush") ||from.equals("screw") ||from.equals("scissors") ||from.equals("hammer")||from.equals("plier") ){
			going = true;
			toolstolowerStations(destination);
		}
		if(from.equals("anvil") ||from.equals("workbench")){
			going = true;
			upperStationstoLowerStations(destination);
		}
		if( from.equals("painting") ){
			going = true;
			stationtoSameRow(destination);
		}
		if(from.equals("furnace") || from.equals("tablesaw")){
			going = true;
			middleStationstoOtherStations(destination);
		}
	}
	

}
