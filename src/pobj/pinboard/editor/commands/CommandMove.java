package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorWindow;

public class CommandMove implements Command {
	private EditorWindow editor;
	private List<Clip> toAdd;
	private double x;
	private double y;
	
	public CommandMove(EditorWindow editor, List<Clip> toAdd, double x, double y){
		this.editor=editor;
		this.toAdd=toAdd;
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void execute() {
		for(Clip c:editor.getSelection().getContents()){	
			c.move(x,y);
		}
	}

	@Override
	public void undo() {
		for(Clip c:editor.getSelection().getContents()){	
			c.move(-x,-y);
		}
	}

}
