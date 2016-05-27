package xiaohanc_CSCI201_Assignment5a;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;


public class Factory extends JFrame implements Runnable{

	File workingFolder;
	static TaskBoard t;
	Factory() throws IOException{
		super("Factory");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIManager.put("Label.font", UIManager.getFont("Label.font").deriveFont((float) 17.0));
		SwingUtilities.updateComponentTreeUI(this);
		
		JMenuBar jmb = new JMenuBar();
		final JMenuItem openFolderMenu = new JMenuItem("Open Folder...");
		openFolderMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 JFileChooser chooser = new JFileChooser();
				    File workingDirectory = new File(System.getProperty("user.dir"));
				    chooser.setCurrentDirectory(workingDirectory);
				    
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    chooser.setAcceptAllFileFilterUsed(false);

//				    int returnVal = chooser.showOpenDialog(openFolderMenu);
				    if (chooser.showOpenDialog(openFolderMenu) == JFileChooser.APPROVE_OPTION) { 
//				        System.out.println("getCurrentDirectory(): " 
//				           +  chooser.getCurrentDirectory());
//				        System.out.println("getSelectedFile() : " 
//				           +  chooser.getSelectedFile());   
				        workingFolder = chooser.getSelectedFile();
						try {t = new TaskBoard(workingFolder);
							add(new DrawingPanel(workingFolder), BorderLayout.CENTER);
							add(t, BorderLayout.EAST);
						} catch (IOException | BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						revalidate();
				        }
				      else {
				        System.out.println("No Selection ");
				        }

			}

		});
		
		jmb.add(openFolderMenu);
		setJMenuBar(jmb);

		setVisible(true);
		setResizable(false);
		
	}
	public void run(){
		while(true){
			repaint();
		}

	}
	public static void main(String[] args) throws IOException{
		Factory f = new Factory();
		Thread t1 = new Thread(f);
		t1.start();
		
	}
}
