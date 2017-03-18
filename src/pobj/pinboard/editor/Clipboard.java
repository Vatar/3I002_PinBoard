package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	private List<Clip> clip;
	private static Clipboard clipboard=new Clipboard();
	
	private Clipboard(){
		clip=new ArrayList<Clip>();
	}
	
	public static Clipboard getInstance(){
		return clipboard;
	}
	
	public void copyToClipboard(List<Clip> clips){
		
	}
	
	public List<Clip> copyFromClipboard(){
		
		return null;
	}
	
	public void clear(){
		this.clear();
	}
	
	public boolean isEmpty(){
		return clip.isEmpty();
	}
	
}
