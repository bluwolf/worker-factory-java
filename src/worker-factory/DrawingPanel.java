package xiaohanc_CSCI201_Assignment5a;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

class DrawingPanel extends JPanel {
	
	 JLabel woodPic = new JLabel();
	 JLabel metalPic = new JLabel();
	 JLabel plasticPic = new JLabel();
	 JLabel screwPic = new JLabel();
	 JLabel hammerPic = new JLabel();
	 JLabel brushPic = new JLabel();
	 JLabel pliersPic = new JLabel();
	 JLabel scissorsPic = new JLabel();
	 JLabel anvilPic1 = new JLabel();
	 JLabel anvilPic2 = new JLabel();
	 JLabel workbenchPic1 = new JLabel();
	 JLabel workbenchPic2 = new JLabel();
	 JLabel workbenchPic3 = new JLabel();
	 JLabel tablesawPic1 = new JLabel();
	 JLabel tablesawPic2 = new JLabel();
	 JLabel tablesawPic3 = new JLabel();
	 JLabel furnacePic1 = new JLabel();
	 JLabel furnacePic2 = new JLabel();
	 JLabel paintingPic1 = new JLabel();
	 JLabel paintingPic2 = new JLabel();
	 JLabel paintingPic3 = new JLabel();
	 JLabel paintingPic4 = new JLabel();
	 JLabel pressPic = new JLabel();

	 
	 
	 
	 JLabel woodName = new JLabel("Wood");
	 JLabel metalName = new JLabel("Metal");
	 JLabel plasticName = new JLabel("Plastic");
	 JLabel screwName = new JLabel("Screwdriver");
	 JLabel hammerName = new JLabel("Hammer");
	 JLabel brushName = new JLabel("Paintbrush");
	 JLabel pliersName = new JLabel("Pliers");
	 JLabel scissorsName = new JLabel("Scissors");
	 JLabel anvilName = new JLabel("Anvils");
	 JLabel workbenchName = new JLabel("Work benches");
	 JLabel furnaceName = new JLabel("Furnaces");
	 JLabel tablesawName = new JLabel("Table Saws");
	 JLabel paintingstationName = new JLabel("Painting Stations");
	 JLabel pressName = new JLabel("Press");
	 
	 static JLabel woodAmount = new JLabel("999");
	 static JLabel metalAmount = new JLabel("999");
	 static JLabel plasticAmount = new JLabel("999");
	 JLabel screwAmount = new JLabel();
	 JLabel hammerAmount = new JLabel();
	 JLabel brushAmount = new JLabel();
	 JLabel pliersAmount = new JLabel();
	 JLabel scissorsAmount = new JLabel();
	 

	 JLabel press = new JLabel();
	 JLabel anvils  []= new JLabel[2];
	 JLabel workbenches  []= new JLabel[3];
	 JLabel furnaces  []= new JLabel[2];
	 JLabel tablesaws  []= new JLabel[3];
	 JLabel painting  []= new JLabel[4];
	 
	 JLabel anvilStatus  []= new JLabel[2];
	 JLabel workbenchStatus  []= new JLabel[3];
	 JLabel furnaceStatus  []= new JLabel[2];
	 JLabel tablesawStatus  []= new JLabel[3];
	 JLabel paintingStatus  []= new JLabel[4];
	 JLabel pressStatus = new JLabel();
	 

 
	 File workingFolder;
	 
	 Vector<Worker> workers;
	 Vector<Thread> workerThread;
	 static Vector<Station> stations;
	 int numWorkers;
//	 Worker worker;
	 Lock lock = new ReentrantLock();


    
	 public DrawingPanel(File workingFolder) throws IOException{
	        setOpaque(true);
	        setLayout(null);

	        this.workingFolder = workingFolder;
	        numWorkers = getNumWorkers(workingFolder);
//	        System.out.println(numWorkers);
	        workers = new Vector<Worker>(numWorkers);
	        workerThread = new Vector<Thread>(numWorkers);
	        stations = new Vector<Station>(15);

	        
	        for(int i=0;i<numWorkers;i++){
	        	Worker worker = new Worker();
	        	add(worker);
	        	workers.add(worker);
				Thread t1 = new Thread(worker);
//				t1.start();
//				t1.start();
				workerThread.add(t1);
	        }

	        parseFiles(workingFolder);

	        
//	        for(int i=0;i<numWorkers;i++){
//	        	workerThread.get(i).start();
////		 
//	        }
	        Thread t1 = new Thread(workers.get(0));
	        t1.start();


//			 System.out.println(workers.get(0).instructions);
//	        (workerThread.get(0)).start();
	        	       
	        paintIcons();

   
	        
	        
//	        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
//	            public void mouseMoved(java.awt.event.MouseEvent evt) {
//	            	System.out.println("(" + evt.getX() + "," + evt.getY() + ")");
//	            }
//	        });

	  }	   
	 public int getNumWorkers(final File folder) throws NumberFormatException, IOException{
		 for (final File fileEntry : folder.listFiles()) {
		    	FileReader fr = new FileReader(fileEntry.getAbsolutePath());
				BufferedReader textReader = new BufferedReader(fr);
				
		    	String filename = fileEntry.getName();
		    	String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		    	String aLine;
		    	
		    	
		    	if(extension.equals("factory")){
		    		while((aLine = textReader.readLine())!= null){
		    			String tool = aLine.substring(aLine.indexOf("[") + 1, aLine.indexOf(":"));
		    			String amt = aLine.substring(aLine.indexOf(":") + 1, aLine.indexOf("]"));
		    			amt =amt.replaceAll("\\s+",""); 
			    		int amount =Integer.parseInt(amt);
		    			switch (tool){
		    			case "Workers":
		    				return amount;

		    			}
		    		}
		    	}

		    	

		    }
		return 0;
	 }
//	 boolean hi = false;
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
//		 revalidate();
//		 worker
//		 for(int i=0;i<numWorkers;i++){
//	        	
//				workerThread.get(i)
//	        }
//		 workers.get(0).setLocation(workers.get(0).initialToBoard());
//		 if(workers.get(0).currentLocation() == boardP){
//	     workers.get(0).boardToWood();
//		 }


	 }
	 
		public void parseFiles(final File folder) throws IOException {
		    for (final File fileEntry : folder.listFiles()) {
		    	FileReader fr = new FileReader(fileEntry.getAbsolutePath());
				BufferedReader textReader = new BufferedReader(fr);
				
		    	String filename = fileEntry.getName();
		    	String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		    	String aLine;
		    	System.out.println("reading in file: " +filename);
		    	
		    	
		    	if(extension.equals("factory")){
		    		while((aLine = textReader.readLine())!= null){
		    			String tool = aLine.substring(aLine.indexOf("[") + 1, aLine.indexOf(":"));
		    			String amt = aLine.substring(aLine.indexOf(":") + 1, aLine.indexOf("]"));
		    			amt =amt.replaceAll("\\s+",""); 
			    		int amount =Integer.parseInt(amt);
		    			switch (tool){
		    			case "Workers":
		    				numWorkers = amount;
		    				break;
		    			case "Hammers":
		    				hammerAmount.setText(amt);
		    				break;
		    			case "Screwdrivers":
		    				screwAmount.setText(amt);
		    				break;
		    			case "Pliers":
		    				pliersAmount.setText(amt);
		    				break;
		    			case "Scissors":
		    				scissorsAmount.setText(amt);
		    				break;
		    			case "Paintbrushes":
		    				brushAmount.setText(amt);
		    				break;
		    			}
		    		}
		    	}
		    		if(extension.equals("rcp")){
		    		aLine = textReader.readLine();
//		    		String type = aLine.substring(aLine.indexOf("x") + 1, aLine.length());
		    		String amt = aLine.substring(aLine.indexOf("x") + 1, aLine.length());
		    		amt =amt.replaceAll("\\s+",""); 
		    		int amount =Integer.parseInt(amt);
		    		int assignNext = amount -1;
//		    		System.out.println(workers.size());
		    		for(int i=0;i<workers.size();i++){
		    			if((workers.get(i)).isIdle){
		    				System.out.println("Initializing worker number: " +i);
		    				while((aLine = textReader.readLine())!= null){
		    					if(!aLine.contains("and") &&!aLine.contains("at")){
		    						(workers.get(i).instructions).add(aLine);
		    						System.out.println(aLine);
		    					}
		    					if(aLine.contains("and") &&aLine.contains("at")){
		    						String string = aLine;
		    						String[] parts = string.split("and");
		    						String part1 = parts[0]; // 004
		    						(workers.get(i).instructions).add(part1);
		    						String part2 = parts[1]; // 034556
		    						parts = part2.split("at");
		    						(workers.get(i).instructions).add(parts[0]);
		    						(workers.get(i).instructions).add(parts[1]);
		    						System.out.println(part1);
		    						System.out.println(parts[0]);
		    						System.out.println(parts[1]);
		    						
		    					}
		    					if(!aLine.contains("and") &&aLine.contains("at")){
		    						String string = aLine;
		    						String[] parts = string.split("at");
		    						(workers.get(i).instructions).add(parts[0]);
		    						(workers.get(i).instructions).add(parts[1]);
		    						System.out.println(parts[0]);
		    						System.out.println(parts[1]);
		    					}
		    				}
		    			}
//		    			System.out.println("Job assigned to Worker " +i);
		    			workers.get(i).isIdle = false;
		    		}
		    		for(int i=0;i<workers.size();i++){
		    			if(assignNext>=0&&workers.get(i).isIdle){
		    				System.out.println("Initializing worker number: " +i);
		    				workers.get(i).instructions = workers.get(i-1).instructions;
		    				workers.get(i).isIdle = false;
		    				assignNext--;
		    			}
		    		}



		    	}
		    	

		    }
		}
	 
	 void paintIcons() throws IOException{
		 BufferedImage pic = ImageIO.read(new File("wood.png"));
		 Image buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 ImageIcon image = new ImageIcon(buffered);
		 woodPic.setIcon(image);
		 add(woodName);
		 add(woodAmount);
		 add(woodPic);
		 
		pic = ImageIO.read(new File("metal.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 metalPic.setIcon(image);
		 add(metalName);
		 add(metalAmount);
		 add(metalPic);
		 
		 pic = ImageIO.read(new File("plastic.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 plasticPic.setIcon(image);
		 add(plasticName);
		 add(plasticAmount);
		 add(plasticPic);
		 
		 pic = ImageIO.read(new File("screwdriver.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 screwPic.setIcon(image);
		 add(screwName);
		 add(screwAmount);
		 add(screwPic);
		 
		 pic = ImageIO.read(new File("hammer.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 hammerPic.setIcon(image);
		 add(hammerName);
		 add(hammerAmount);
		 add(hammerPic);
		 
		 pic = ImageIO.read(new File("paintbrush.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 brushPic.setIcon(image);
		 add(brushName);
		 add(brushAmount);
		 add(brushPic);
		 
		 pic = ImageIO.read(new File("pliers.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 pliersPic.setIcon(image);
		 add(pliersName);
		 add(pliersAmount);
		 add(pliersPic);
		 
		 pic = ImageIO.read(new File("scissors.png"));
		 buffered = pic.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
		 scissorsPic.setIcon(image);
		 add(scissorsName);
		 add(scissorsAmount);
		 add(scissorsPic);
		 
		 for(int i = 0;i<anvils.length;i++){
			 pic = ImageIO.read(new File("anvil.png"));
			 buffered = pic.getScaledInstance(40,40,Image.SCALE_SMOOTH);
			 image = new ImageIcon(buffered);
			 anvilStatus[i] = new JLabel();
			 anvils[i] = new JLabel();
			 anvils[i].setIcon(image);
			 add(anvilStatus[i]);
			 add(anvils[i]); 
		 }
		 for(int i = 0;i<workbenches.length;i++){
			 pic = ImageIO.read(new File("workbench.png"));
			 buffered = pic.getScaledInstance(40,40,Image.SCALE_SMOOTH);
			 image = new ImageIcon(buffered);
			 workbenchStatus[i] = new JLabel();
			 workbenches[i] = new JLabel();
			 workbenches[i].setIcon(image);
			 add(workbenchStatus[i]);
			 add(workbenches[i]); 
		 }
		 
		 for(int i = 0;i<furnaces.length;i++){
			 pic = ImageIO.read(new File("furnace.png"));
			 buffered = pic.getScaledInstance(40,40,Image.SCALE_SMOOTH);
			 image = new ImageIcon(buffered);
			 furnaceStatus[i] = new JLabel();
			 furnaces[i] = new JLabel();
			 furnaces[i].setIcon(image);
			 add(furnaceStatus[i]);
			 add(furnaces[i]); 
		 }
		 for(int i = 0;i<tablesaws.length;i++){
			 pic = ImageIO.read(new File("tablesaw.png"));
			 buffered = pic.getScaledInstance(40,40,Image.SCALE_SMOOTH);
			 image = new ImageIcon(buffered);
			 tablesawStatus[i] = new JLabel();
			 tablesaws[i] = new JLabel();
			 tablesaws[i].setIcon(image);
			 add(tablesawStatus[i]);
			 add(tablesaws[i]); 
		 }
		 for(int i = 0;i<painting.length;i++){
			 pic = ImageIO.read(new File("paintingstation.png"));
			 buffered = pic.getScaledInstance(40,40,Image.SCALE_SMOOTH);
			 image = new ImageIcon(buffered);
			 paintingStatus[i] = new JLabel();
			 painting[i] = new JLabel();
			 painting[i].setIcon(image);
			 add(paintingStatus[i]);
			 add(painting[i]); 
		 }
		 
		 pic = ImageIO.read(new File("press.png"));
		 buffered = pic.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		 image = new ImageIcon(buffered);
//		 pressStatus = new JLabel("q2");
		 press = new JLabel();
		 press.setIcon(image);
		 add(pressStatus);
		 add(press); 

		 
		 Insets insets = this.getInsets();
		 woodName.setBounds(150 + insets.left, 0 + insets.top,50, 50);
		 woodPic.setBounds(150 + insets.left, 40 + insets.top,50, 50);
		 woodAmount.setBounds(170 + insets.left, 40 + insets.top,50, 50);
		 
		 metalName.setBounds(290 + insets.left, 0 + insets.top,50, 50);
		 metalPic.setBounds(290 + insets.left, 40 + insets.top,50, 50);
		 metalAmount.setBounds(310 + insets.left, 40 + insets.top,50, 50);
		 
		 plasticName.setBounds(430 + insets.left, 0 + insets.top,100, 50);
		 plasticPic.setBounds(430+ insets.left, 40 + insets.top,50, 50);
		 plasticAmount.setBounds(450 + insets.left, 40 + insets.top,50, 50);
		 
		 screwName.setBounds(10 + insets.left, 95 + insets.top,100, 50);
		 screwPic.setBounds(20+ insets.left, 130 + insets.top,50, 50);
		 screwAmount.setBounds(35 + insets.left, 130 + insets.top,50, 50);
		 
		 hammerName.setBounds(20 + insets.left, 170 + insets.top,100, 50);
		 hammerPic.setBounds(20+ insets.left, 205 + insets.top,50, 50);
		 hammerAmount.setBounds(35 + insets.left, 205 + insets.top,50, 50);
		 
		 brushName.setBounds(15 + insets.left, 245 + insets.top,100, 50);
		 brushPic.setBounds(20+ insets.left, 280 + insets.top,50, 50);
		 brushAmount.setBounds(35 + insets.left, 280 + insets.top,50, 50);
		 
		 pliersName.setBounds(30 + insets.left, 320 + insets.top,100, 50);
		 pliersPic.setBounds(20+ insets.left, 355 + insets.top,50, 50);
		 pliersAmount.setBounds(35 + insets.left, 355 + insets.top,50, 50);
		 
		 scissorsName.setBounds(20 + insets.left, 395 + insets.top,100, 50);
		 scissorsPic.setBounds(20+ insets.left, 430 + insets.top,50, 50);
		 scissorsAmount.setBounds(35 + insets.left, 430 + insets.top,50, 50);
		 
		 
		 add(anvilName);
		 add(workbenchName);
		 add(furnaceName);
		 add(tablesawName);
		 add(paintingstationName);
		 add(pressName);
		 
		 anvilName.setBounds(120+105+ insets.left, 190 + insets.top,50, 50);
		 workbenchName.setBounds(120+250+ insets.left, 190 + insets.top,150, 50);
		 furnaceName.setBounds(120+105+ insets.left, 320 + insets.top,150, 50);
		 tablesawName.setBounds(120+250+ insets.left, 320 + insets.top,150, 50);
		 paintingstationName.setBounds(120+140+ insets.left, 450 + insets.top,150, 50);
		 pressName.setBounds(120+350+ insets.left, 450 + insets.top,150, 50);
		 
		 for(int i = 0;i<anvils.length;i++){
			 anvilStatus[i].setBounds(130+70*(i+1)+ insets.left, 120 + insets.top,50, 50);
			 anvilStatus[i].setForeground(Color.MAGENTA);
			 anvilStatus[i].setText("Open");
			 anvils[i].setBounds(120+70*(i+1)+ insets.left, 150 + insets.top,50, 50);
			 Station station = new Station("anvil",anvils[i].getLocation(),anvilStatus[i]);
	         stations.add(station);
		 }
		 for(int i = 0;i<workbenches.length;i++){
			 workbenchStatus[i].setBounds(130+70*(i+3)+ insets.left, 120 + insets.top,50, 50);
			 workbenchStatus[i].setForeground(Color.MAGENTA);
			 workbenchStatus[i].setText("Open");
			 workbenches[i].setBounds(120+70*(i+3)+ insets.left, 150 + insets.top,50, 50);
			 Station station = new Station("workbench",workbenches[i].getLocation(),workbenchStatus[i]);
	         stations.add(station);
		 }
		 for(int i = 0;i<furnaces.length;i++){
			 furnaceStatus[i].setBounds(130+70*(i+1)+ insets.left, 250 + insets.top,50, 50);
			 furnaceStatus[i].setForeground(Color.MAGENTA);
			 furnaceStatus[i].setText("Open");
			 furnaces[i].setBounds(120+70*(i+1)+ insets.left, 280 + insets.top,50, 50);
			 Station station = new Station("furnace",furnaces[i].getLocation(),furnaceStatus[i]);
	         stations.add(station);
		 }
		 for(int i = 0;i<tablesaws.length;i++){
			 tablesawStatus[i].setBounds(130+70*(i+3)+ insets.left, 250 + insets.top,50, 50);
			 tablesawStatus[i].setForeground(Color.MAGENTA);
			 tablesawStatus[i].setText("Open");
			 tablesaws[i].setBounds(120+70*(i+3)+ insets.left, 280 + insets.top,50, 50);
			 Station station = new Station("tablesaw",tablesaws[i].getLocation(),tablesawStatus[i]);
	         stations.add(station);
		 }
		 
		 for(int i = 0;i<painting.length;i++){
			 paintingStatus[i].setBounds(130+70*(i+1)+ insets.left, 380 + insets.top,50, 50);
			 paintingStatus[i].setForeground(Color.MAGENTA);
			 paintingStatus[i].setText("Open");
			 painting[i].setBounds(120+70*(i+1)+ insets.left, 410 + insets.top,50, 50);
			 Station station = new Station("painting",painting[i].getLocation(),paintingStatus[i]);
	         stations.add(station);
		 }
		 
		 pressStatus.setBounds(130+70*(5)+ insets.left, 380 + insets.top,50, 50);
		 pressStatus.setForeground(Color.MAGENTA);
		 pressStatus.setText("Open");
		 press.setBounds(120+70*(5)+ insets.left, 410 + insets.top,50, 50);
		 Station station = new Station("press",press.getLocation(),pressStatus);
         stations.add(station);
		 
//	        for(int i=0;i<anvils.length;i++){
//	        	Station station = new Station("anvil",anvils[i].getLocation());
//	        	stations.add(station);
//	        }
//	        anvils[i].setBounds(120+70*(i+1)+ insets.left, 150 + insets.top,50, 50);
	 }



		
	 
}
