package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {

	
	public ClipRect(double left, double top, double right, double bottom, Color color){
		super(left,top,right,bottom,color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(this.getColor());
		ctx.fillRect(getLeft(), getTop(), Math.abs(getRight()-getLeft()), Math.abs(getTop()-getBottom()));
		
	}

	@Override
	public Clip copy() {
		return new ClipRect(getLeft(),getTop(),getRight(),getBottom(),this.getColor());
	} 
	
	@Override
	public boolean isSelected(double x, double y) {
		if(x>getLeft() && x<getRight() && y>getTop() && y<getBottom()){
			return true;
		}
		return false;
	}

}
