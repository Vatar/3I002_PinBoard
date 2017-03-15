package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color){
		super(left,top,right,bottom,color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(this.getColor());
		ctx.fillOval( ((getLeft()+getRight())/2) - ((getRight()-getLeft()) /2), ((getTop()+getBottom())/2) - ((getBottom()-getTop() )/2),
				(getRight()-getLeft()), (getBottom()-getTop()));
	}

	@Override
	public Clip copy() {
		return new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}

	@Override
	public boolean isSelected(double x,double y){
		double res= ( Math.pow((x - ((getLeft()+getRight())/2) )/ ((getRight()-getLeft() /2)),2) )
				+ ( Math.pow((x - ((getTop()+getBottom())) )/ ((getBottom()-getTop())),2) ) ;
		if(res <= 1){
			return true;
		}		
		return false;
	}
	
	
	
}
