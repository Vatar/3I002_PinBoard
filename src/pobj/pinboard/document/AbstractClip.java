package pobj.pinboard.document;

import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip {
	private double left, top, right, bottom;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color){
		this.setLeft(left);
		this.setTop(top);
		this.setRight(right);
		this.setBottom(bottom);
		this.setColor(color);
	}
	
	
	@Override
	public double getTop() {
		return top;
	}

	@Override
	public double getLeft() {
		return left;
	}

	@Override
	public double getBottom() {
		return bottom;
	}

	@Override
	public double getRight() {
		return right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.setLeft(left);
		this.setTop(top);
		this.setRight(right);
		this.setBottom(bottom);
	}

	@Override
	public void move(double x, double y) {
		setLeft(getLeft()+x);
		setRight(getRight()+x);
		setTop(getTop()+y);
		setBottom(getBottom()+y);
	}

	@Override
	public boolean isSelected(double x, double y) {
		if(x>getLeft() && x<getRight() && y<getTop() && y>getBottom()){
			return true;
		}
		return false;
	}

	@Override
	public void setColor(Color c) {
		color=c;
	}

	@Override
	public Color getColor() {
		return color;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public void setTop(double top) {
		this.top = top;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public void setBottom(double bottom) {
		this.bottom = bottom;
	}
	

}
