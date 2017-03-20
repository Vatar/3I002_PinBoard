package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	private List<Clip> clip;
	private static Clipboard clipboard=new Clipboard();
	private List<ClipboardListener> window; 
	
	private Clipboard(){
		clip=new ArrayList<Clip>();
		window=new ArrayList<ClipboardListener>();
	}
	
	public static Clipboard getInstance(){
		return clipboard;
	}
	
	public void copyToClipboard(List<Clip> clips){
		for(Clip c:clips){
			clip.add(c);
		}
		for(ClipboardListener c:window){
			c.clipboardChanged();
		}
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> newclip=new ArrayList<Clip>();
		
		for(Clip c:clip){
			newclip.add(c);
		}
		return newclip;
	}
	
	public void clear(){
		this.clear();
		for(ClipboardListener c:window){
			c.clipboardChanged();
		}
	}
	
	public boolean isEmpty(){
		return clip.isEmpty();
	}
	
	public void addListener(ClipboardListener listener){
		window.add(listener);
	}
	public void removeListener(ClipboardListener listener){
		window.remove(listener);
	}
	
}
