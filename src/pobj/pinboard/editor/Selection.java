package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private List<Clip> selected=new ArrayList<Clip>();
	
	public void select(Board board, double x, double y){
	
		boolean found=false;
		int i=0;
		
		clear();
		
		while(i<board.getContents().size() && !found){
			if(board.getContents().get(i).isSelected(x, y)){
				selected.add(board.getContents().get(i));
				found=true;
			}
			i++;
		}
	}
	
	public void toogleSelect(Board board, double x, double y){
		boolean found=false;
		int i=0;
		

		while(i<board.getContents().size() && !found){
			if(board.getContents().get(i).isSelected(x, y)){

				if(!selected.remove(board.getContents().get(i))){
					selected.add(board.getContents().get(i));
				}
			}
			found=true;
			i++;
		}
	}

	public void clear(){
		selected.clear();
	}
	
	public List<Clip> getContents(){
		return selected;
	}
	
	public void drawFeedback(GraphicsContext gc){
		gc.setStroke(Color.BLUE);
		for(Clip c: selected){
			gc.strokeRect(c.getLeft(), c.getTop(),Math.abs(c.getRight()-c.getLeft()), Math.abs(c.getTop()-c.getBottom()));
		}
		
	}
	
	
}
