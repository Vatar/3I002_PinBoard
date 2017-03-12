package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip implements Clip {
	private File filename;
	private Image img;
	
	public ClipImage(double left, double top, File filename){
		super(left,top,100,100,Color.WHITE);
	
		img=new Image("file://"+filename.getAbsolutePath());
		
		this.setRight(left+img.getWidth());
		this.setBottom(top+img.getHeight());
		
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(img, getLeft(), getTop());
	}

	@Override
	public Clip copy() {
		return new ClipImage(getLeft(), getTop(),filename);
	}

}
