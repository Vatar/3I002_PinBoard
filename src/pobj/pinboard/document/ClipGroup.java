package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite {
	private List<Clip> list;
	
	public ClipGroup(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
		list=new ArrayList<Clip>();
	}

	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip c:list){
			c.draw(ctx);
		}
	}

	@Override
	public boolean isSelected(double x, double y) {
		if(x>getLeft() && x<getRight() && y>getTop() && y<getBottom()){
			return true;
		}
		return false;
	}

	@Override
	public List<Clip> copyList() {
		List<Clip> nouv=new ArrayList<Clip>();
		
		for(Clip c:list){
			nouv.add(c.copy());
		}
		return nouv;
	}

	@Override
	public List<Clip> getClips() {
		return list;
	}

	private void checkClip(Clip compare){
	
		if(compare.getLeft()<this.getLeft()){
			this.setLeft(compare.getLeft());
		}
		
		if(compare.getRight()>this.getRight()){
			this.setRight(compare.getRight());
		}
		if(compare.getTop()<this.getTop()){
			this.setTop(compare.getTop());
		}
		if(compare.getBottom()>this.getBottom()){
			this.setBottom(compare.getBottom());
		}
		
		if(list.isEmpty()){
			this.setLeft(compare.getLeft());
			this.setRight(compare.getRight());
			this.setTop(compare.getTop());
			this.setBottom(compare.getBottom());
		}
		
	}
	
	@Override
	public void addClip(Clip toAdd) {
		checkClip(toAdd);
		list.add(toAdd);
	}

	@Override
	public void removeClip(Clip toRemove) {
		checkClip(toRemove);
		list.remove(toRemove);
	}

	
	@Override
	public void move(double x, double y) {
		for(Clip c:list){
			c.move(x, y);
		}
		setLeft(getLeft()+x);
		setRight(getRight()+x);
		setTop(getTop()+y);
		setBottom(getBottom()+y);
	}

		
	@Override
	public Clip copy() {
		ClipGroup nouv=new ClipGroup(getLeft(), getTop(), getRight(), getBottom(), getColor());
	
		for(Clip c:list){
			nouv.addClip(c);
		}
		
		return nouv;
	}

}
