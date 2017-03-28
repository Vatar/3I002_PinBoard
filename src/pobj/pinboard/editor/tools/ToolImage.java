package pobj.pinboard.editor.tools;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.EditorWindow;
import pobj.pinboard.editor.commands.CommandAdd;

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
		List<Clip> list= new ArrayList<Clip>();
		list.add(new ClipImage(left, top, filename ));
		i.getStack().addCommand(new CommandAdd((EditorWindow) i,list));
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
