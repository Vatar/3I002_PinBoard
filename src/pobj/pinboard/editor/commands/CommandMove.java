package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorWindow;

public class CommandMove implements Command {
	private List<Clip> toMove;
	private double x;
	private double y;
	
	public CommandMove(List<Clip> toMove, double x, double y){
		this.toMove=toMove;
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void execute() {
		for(Clip c:toMove){	
			c.move(x,y);
		}
	}

	@Override
	public void undo() {
		for(Clip c:toMove){	
			c.move(-x,-y);
		}
	}

}
