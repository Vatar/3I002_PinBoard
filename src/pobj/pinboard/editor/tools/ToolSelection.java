package pobj.pinboard.editor.tools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {
	private double xorg;
	private double yorg;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		if(e.isShiftDown()){
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY()); //Ne fonctionne pas, seulement 1 élément
		}else{
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
			xorg=e.getX();
			yorg=e.getY();
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		//if(e.isShiftDown()){return;}
		i.getStack().addCommand(new CommandMove(i.getSelection().getContents(),
				e.getX()-xorg,e.getY()-yorg));
		//for(Clip c:i.getSelection().getContents()){	
			//c.move(e.getX()-xorg, e.getY()-yorg);
		//}
		xorg=e.getX();
		yorg=e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		return;
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName() {
		return "Select tool";
	}

}
