package xiaohanc_CSCI201_Assignment5a;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;

public class TaskBoard extends JPanel implements Runnable{
	JTable taskBoard = new JTable();
	ArrayList buildStatus;
	File workingFolder;
	int factoryCount =0;
	int rcpCount =0;
	Vector<Product> productsToMake = new Vector<Product>();
	static Object [][] data;
	
	TaskBoard(File workingFolder) throws IOException, BadLocationException{
		productsToMake = new Vector<Product>();
		this.workingFolder = workingFolder;
		taskBoard = new JTable();		
		parseFiles(workingFolder);
		if(factoryCount>1 || factoryCount==0){
			System.out.println("Error: Your have more than one factory file in the directory, or there is no factory file");
			System.exit(1);
		}
		if(rcpCount==0){
			System.out.println("Error: You have no rcp file in directory");
			System.exit(1);
		}
		int size = productsToMake.size();
		String[] columnNames = {"Product","Status"};
		data = new Object[size][2];
		 for(int i=0;i<size;i++){
			 data[i][0]= productsToMake.get(i).type;
			 data[i][1] = "...Not Built";
		 }
		 JTextField tf = new JTextField();
		 tf.setEditable(false);
		 
//		 data[0].setCellEditor(new DefaultCellEditor(tf));
//		 DefaultTableModel model = new DefaultTableModel(new Object[]{"column1","column2"},0);
	     taskBoard = new JTable(data,columnNames);
//		 String s[] = taskBoard.getText().split("\\r?\\n");

//		 buildStatus = new ArrayList<String>(Arrays.asList(s)) ;
//		 Object o = buildStatus.get(1);
//		 String ss  = o.toString();
//		 System.out.println(buildStatus);
//		 taskBoard.replaceRange("in Progress", ss.lastIndexOf(".N")+1, ss.length());
//		 taskBoard.getDocument().insertString(5, "New line entered " + "\n", null);
		 
		
		
		this.setLayout(new BorderLayout());
		JLabel tbLabel = new JLabel ("Task Board");
//		taskBoard.setModel(taskBoard().getModel(){
//			   isCellEditable(int rowIndex, int columnIndex) {
//			       return false;
//			   }
//			});
//		 taskBoard.setEditable(false);
	     JScrollPane jsp = new JScrollPane(taskBoard);
	     jsp.setPreferredSize(new Dimension(150,600));	     
		 Border border = BorderFactory.createLineBorder(Color.BLACK);
		 this.setBorder(border);
		 Font font = new Font("Verdana", Font.BOLD, 10);
		 taskBoard.setFont(font);
		 taskBoard.setForeground(Color.BLACK);
		 

		 
		 
	     jsp.revalidate();
	     add(tbLabel, BorderLayout.NORTH);
	     add(jsp,BorderLayout.CENTER);
	}
	public class setCellsUneditable extends AbstractTableModel {

	      public boolean isCellEditable(int row, int column){  
	          return false;  
	      }

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

	}
	public void parseFiles(final File folder) throws IOException {
	    for (final File fileEntry : folder.listFiles()) {
	    	FileReader fr = new FileReader(fileEntry.getAbsolutePath());
			BufferedReader textReader = new BufferedReader(fr);
			
	    	String filename = fileEntry.getName();
	  	String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
	    	String aLine;
	    	if(extension.equals("rcp")){
	    		rcpCount++;
	    		aLine = textReader.readLine();
	    		String type = aLine.substring(aLine.indexOf("[") + 1, aLine.indexOf("]"));
	    		String amt = aLine.substring(aLine.indexOf("x") + 1, aLine.length());
	    		amt =amt.replaceAll("\\s+",""); 
	    		int amount =Integer.parseInt(amt);
//	    		System.out.println(amount);
	    		for(int i=0;i<amount;i++){
//	   			 taskBoard.append(type+"...Not Built"+"\n");
	    			productsToMake.add(new Product(type));
	   			 
	   		 	}

	    	}
	    	if(extension.equals("factory")){
	    		factoryCount++;
	    	}
	    	
	        if (fileEntry.isDirectory()) {
	            System.out.println("You have a subfolder in your directory but I'm going to ignore it");
	        }
	    }
	}
	public void run(){
		revalidate();
	}
}
