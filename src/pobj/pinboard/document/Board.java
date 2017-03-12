package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board { 
	private List<Clip> board;
	
	public Board(){
		board=new ArrayList<Clip>();
	}
	
	public List<Clip> getContents(){
		return board;
	}
	
	public void addClip(Clip clip){
		board.add(clip);
	}
	
	public void addClip(List<Clip> clip){
		board.addAll(clip);
	}
	
	public void removeClip(Clip clip){
		board.remove(clip);
	}
	
	public void removeClip(List<Clip> clip){
		board.removeAll(clip);
	}
	
	public void draw(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		gc.fill();
		
		for(Clip c:board){
			c.draw(gc);
		}
		
	}
	
}
