package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorWindow;

public class CommandAdd implements Command {
	private EditorWindow editor;
	private List<Clip> toAdd;
	
	public CommandAdd(EditorWindow editor, List<Clip> toAdd){
		this.editor=editor;
		this.toAdd=toAdd;
	}
	
	@Override
	public void execute() {
		editor.getBoard().addClip(toAdd);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(toAdd);
	}

}
