package xiaohanc_CSCI201_Assignment5a;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

// working stations
public class Station implements Runnable{
	String type;
	boolean occupied = false;
	JLabel stationStatus;
	Point locationP;
	Timer timer;
	int waitSeconds=0;
	
	
	Station(String t, Point p, JLabel status){
		this.type=t;
		this.locationP = p;
		this.stationStatus = status;
//		Thread t1 = new Thread(this);
//		t1.start();
		
//		 timer = new Timer(waitSeconds*1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	while(waitSeconds!=0){
//            		stationStatus.setText(Integer.toString(waitSeconds--));
//            	}
//            }
//        });
		
	}
	
	public synchronized void setTimeLabel(){
		
		if(waitSeconds==0){
			stationStatus.setText("Open");
		}
		
		
		if(waitSeconds>0){
			stationStatus.setText(waitSeconds+"s");
			waitSeconds--;
		}
		
//		if(waitSeconds==0){
//			stationStatus.setText("Open");
//		}
		
	}

	public void run() {
		setTimeLabel();
//		  new Timer(waitSeconds*1000, new ActionListener() {
//		         @Override
//		         public void actionPerformed(ActionEvent e) {
//		        	 stationStatus.setText(Integer.toString(waitSeconds--));
//		         }
//		      }).start();
		
		
	}

}
