package pobj.pinboard.editor;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JToolBar;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;
import pobj.pinboard.editor.tools.ToolImage;


public class EditorWindow extends javafx.application.Application
 implements EditorInterface,ClipboardListener{
	
	private Board board;
	private Tool tool;
	private Selection select=new Selection();
	private int wheight=600;
	private int wwidth=400;

	private Menu menuedit;
	
	public EditorWindow(Stage stage) throws Exception{
		start(stage);
	}
	
	
	 private class MousePressed implements EventHandler<MouseEvent> {
	     private EditorInterface ei;
	     private GraphicsContext gc;
	     
	     public MousePressed(EditorInterface ei, GraphicsContext gc){
	    	 this.ei=ei;
	    	 this.gc=gc;
	     }
		 public void handle( MouseEvent e ) {
	        tool.press(ei, e);
	        tool.drawFeedback(ei, gc);
	      }
	  }
	
	 private class MouseDragged implements EventHandler<MouseEvent> {
	     private EditorInterface ei;
	     private GraphicsContext gc;
	     
	     public MouseDragged(EditorInterface ei, GraphicsContext gc){
	   
	    	 this.ei=ei;
	    	 this.gc=gc;
	     }
		 public void handle( MouseEvent e ) {
	        tool.drag(ei, e);
	        tool.drawFeedback(ei, gc);
	      }
	  }
	 
	 private class MouseReleased implements EventHandler<MouseEvent> {
	     private EditorInterface ei;
	     private GraphicsContext gc;
	     
	     public MouseReleased(EditorInterface ei, GraphicsContext gc){
	    	 this.ei=ei;
	    	 this.gc=gc;
	     }
		 public void handle( MouseEvent e ) {
			if(tool!=null){
				tool.release(ei, e);
	        	tool.drawFeedback(ei, gc);
	        	gc.setFill(Color.WHITE);
	        	gc.fillRect(0, 0, wheight, wwidth);
	    		ei.getBoard().draw(gc);
			}
	      }
	  }
	 
	public Node[] initButton(Stage primaryStage,Label label){
		
		int nbbutton=4;
		
		Node[] list=new Button[nbbutton];
		
		Button box=new Button("Box");
		box.setOnAction( (e) -> {
			tool=new ToolRect();
			label.setText(tool.getName());
		} );
		list[0]=box;
		
		Button ellipse=new Button("Ellipse");
		ellipse.setOnAction( (e) -> {
			tool=new ToolEllipse();
			label.setText(tool.getName());
		 } );
		list[1]=ellipse;
		
		Button bimg=new Button("Img...");
		bimg.setOnAction( (e) -> {
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Image File");
			 fileChooser.getExtensionFilters().addAll(
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
			         new ExtensionFilter("All Files", "*.*"));
			 File selectedFile = fileChooser.showOpenDialog(primaryStage);
			 if(selectedFile!=null){
				 tool=new ToolImage(selectedFile);
				 label.setText(tool.getName());
			 }
			} );
		list[2]=bimg;
		
		Button selection=new Button("Selection");
		selection.setOnAction( (e) -> {
			tool=new ToolSelection();
			label.setText(tool.getName());
		 } );
		list[3]=selection;
		
		return list;
	}

	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		board = new Board();
		Canvas canvas = new Canvas(wheight, wwidth);
		canvas.getGraphicsContext2D().setFill(Color.WHITE);
		canvas.getGraphicsContext2D().fill();
		canvas.setOnMousePressed(new MousePressed(this,canvas.getGraphicsContext2D()));
		canvas.setOnMouseDragged(new MouseDragged(this,canvas.getGraphicsContext2D()));
		canvas.setOnMouseReleased(new MouseReleased(this,canvas.getGraphicsContext2D()));
		
		Clipboard.getInstance().addListener(this);
		
		VBox vbox = new VBox();
		
		Label label=new Label("Idle");
		
		Menu f=new Menu("File");
		MenuItem nouv=new MenuItem("New");
		
		nouv.setOnAction( (e) -> {try {
			new EditorWindow(new Stage());
		} catch (Exception e1) {
			System.out.println("Error while opening a new Window \n");
			e1.printStackTrace();;
		}} );
		
		MenuItem close=new MenuItem("Close");
		close.setOnAction( (e) -> {
			Clipboard.getInstance().removeListener(this);
			primaryStage.close();
		} );
		
		
		f.getItems().addAll(nouv,close);
		
		
		Menu edit=new Menu("Edit");
		
		MenuItem copy=new MenuItem("Copy");
		
		copy.setOnAction( (e) -> {
			Clipboard.getInstance().copyToClipboard(select.getContents());
		});
		
		MenuItem paste=new MenuItem("Paste");
		
		paste.setOnAction( (e) -> {	
			select.getContents().addAll(Clipboard.getInstance().copyFromClipboard());
		});
		
		
		MenuItem delete=new MenuItem("Delete");
		
		delete.setOnAction( (e) -> {
			Clipboard.getInstance().clear();
		});
		
		MenuItem group=new MenuItem("Group");
		
		group.setOnAction( (e) -> {
			board.removeClip(select.getContents());
			ClipGroup groupe=new ClipGroup(0, 0, 0, 0, Color.WHITE);
			for(Clip c:select.getContents()){
				groupe.addClip(c);
			}
			board.addClip(groupe);
		});
		
		MenuItem ungroup=new MenuItem("Ungroup");
		
		ungroup.setOnAction( (e) -> {
			if(select.getContents().get(0) instanceof ClipGroup){
				board.removeClip(select.getContents().get(0));
				board.addClip( ( (ClipGroup) select.getContents().get(0)).getClips() );
			}
			
		});
		
		edit.getItems().addAll(copy,paste,delete,group,ungroup);
		
		menuedit=edit;

		clipboardChanged();
		
		Menu tools=new Menu("Tools");
		MenuItem mbox=new MenuItem("Box");
		
		mbox.setOnAction( (e) -> {
			tool=new ToolRect();
			label.setText(tool.getName());
		} );
		
		MenuItem mellipse=new MenuItem("Ellipse");
		
		mellipse.setOnAction( (e) -> {
			tool=new ToolEllipse();
			label.setText(tool.getName());
		} );
		tools.getItems().addAll(mbox,mellipse);
		
		
		vbox.getChildren().add(new MenuBar(f,edit,tools));
		
		
		ToolBar toolbar=new ToolBar(initButton(primaryStage, label));
		vbox.getChildren().add(toolbar);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(new Separator());
		vbox.getChildren().add(label);
		primaryStage.setScene(new javafx.scene.Scene(vbox));
		primaryStage.setTitle("Dreamland");
		
		board.draw(canvas.getGraphicsContext2D());
		primaryStage.show();
	}

	public Selection getSelection(){
		return select;
	}
	
	@Override
	public Board getBoard() {
		return board;
	}


	@Override
	public void clipboardChanged() {
		if(Clipboard.getInstance().isEmpty()){
			menuedit.getItems().get(1).setDisable(true);
		}else{
		menuedit.getItems().get(1).setDisable(false);
		}
	}

}
