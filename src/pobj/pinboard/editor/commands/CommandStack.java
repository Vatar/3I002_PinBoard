package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandStack {
	private List<Command> redo;
	private List<Command> undo;
	
	public CommandStack() {
		redo=new ArrayList<Command>();
		undo=new ArrayList<Command>();
	}
	
	public void addCommand(Command cmd){
		redo.clear();
		cmd.execute();
		undo.add(cmd);	
	}
	
	public void undo(){
		if(isUndoEmpty()){return;}
		Command cmd=undo.remove(undo.size()-1);
		cmd.undo();
		redo.add(cmd);
	}
	
	public void redo(){
		if(isRedoEmpty()){return;}
		Command cmd=redo.remove(redo.size()-1);
		cmd.execute();
		undo.add(cmd);
	}
	
	public boolean isUndoEmpty(){
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty(){
		return redo.isEmpty();
	}

}
