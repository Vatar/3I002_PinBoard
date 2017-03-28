package pobj.pinboard.editor.commands;

import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorWindow;
import pobj.pinboard.editor.Selection;

public class CommandUngroup implements Command {

	private EditorWindow editor;
	private Selection select;
	private ClipGroup groupe;
	
	public CommandUngroup(EditorWindow editor,ClipGroup groupe){
		this.editor=editor;
		this.groupe=groupe;
	}
	
	@Override
	public void execute() {
		//editor.getBoard().removeClip(select.getContents());
		//ClipGroup groupe=new ClipGroup(0, 0, 0, 0, Color.WHITE);
		//for(Clip c:select.getContents()){
		//	groupe.addClip(c);
		//}
		editor.getBoard().removeClip(groupe);
		editor.getBoard().addClip(groupe.getClips());
	}

	@Override
	public void undo() {
			editor.getBoard().removeClip(groupe.getClips());
			editor.getBoard().addClip(groupe);
	}
	
}
