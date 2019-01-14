package dev.codenmore.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	//private int width, height;
	
	public Display(String title, int width, int height){
		this.title = title;
		//this.width = width;
		//this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay(){
		frame = new JFrame(title);
        frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setDefaultLookAndFeelDecorated(true);
		//frame.setUndecorated(true);
		//frame.setPreferredSize(new Dimension(1300, 850));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(frame.getWidth(), frame.getWidth()));
		canvas.setMaximumSize(new Dimension(frame.getWidth(), frame.getWidth()));
		canvas.setMinimumSize(new Dimension(frame.getWidth(), frame.getWidth()));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
}
