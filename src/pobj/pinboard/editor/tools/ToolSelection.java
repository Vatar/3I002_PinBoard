package pobj.pinboard.editor.tools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;

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
		if(e.isShiftDown()){return;}
		for(Clip c:i.getSelection().getContents()){
			if(c instanceof ClipRect){				
				((ClipRect) c).setLeft(c.getLeft()+(e.getX()-xorg));
				((ClipRect) c).setTop(c.getTop()+(e.getY()-yorg));
				((ClipRect)c).setRight(c.getRight()+(e.getX()-xorg));
				((ClipRect) c).setBottom(c.getBottom()+(e.getY()-yorg));
			}else{
				if(c instanceof ClipEllipse){
					((ClipEllipse) c).setLeft(c.getLeft()+(e.getX()-xorg));
					((ClipEllipse) c).setTop(c.getTop()+(e.getY()-yorg));
					((ClipEllipse) c).setRight(c.getRight()+(e.getX()-xorg));
					((ClipEllipse) c).setBottom(c.getBottom()+(e.getY()-yorg));
				}else{
					if(c instanceof ClipImage){
						((ClipImage) c).setLeft(c.getLeft()+(e.getX()-xorg));
						((ClipImage) c).setRight();
						((ClipImage) c).setTop(c.getTop()+(e.getY()-yorg));
						((ClipImage) c).setBottom();
					}
				}
			}
		}
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
