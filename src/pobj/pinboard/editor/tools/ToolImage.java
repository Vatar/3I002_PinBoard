package pobj.pinboard.editor.tools;


import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool {
	private File filename;
	private double left,top;
	private Image img;

	public ToolImage(File filename){
		this.filename=filename;
		img=new Image("file://"+filename.getAbsolutePath());
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		left=e.getX();
		top=e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		left=e.getX();
		top=e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Board b=i.getBoard();
		b.addClip(new ClipImage(left, top, filename ));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		//gc.setFill(Color.WHITE);
		//gc.fillRect(left, top, img.getWidth()+left, top+img.getHeight());
		gc.drawImage(img, left, top);
	}

	@Override
	public String getName() {
		return "Image tool";
	}

}
