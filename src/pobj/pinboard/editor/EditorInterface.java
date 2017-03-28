package pobj.pinboard.editor;

import pobj.pinboard.document.Board;
import pobj.pinboard.editor.commands.CommandStack;

public interface EditorInterface {
	public Board getBoard();
	public Selection getSelection();
	public CommandStack getStack();
}
