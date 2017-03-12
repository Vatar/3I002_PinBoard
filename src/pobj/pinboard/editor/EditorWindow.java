package pobj.pinboard.editor;


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
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow extends javafx.application.Application
 implements EditorInterface{
	
	private Board board;
	private Tool tool;
	
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
	        tool.release(ei, e);
	        tool.drawFeedback(ei, gc);
	        gc.setFill(Color.WHITE);
	        gc.fillRect(0, 0, 600, 400);
	    	ei.getBoard().draw(gc);

	      }
	  }
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		board = new Board();
		Canvas canvas = new Canvas(600, 400);
		canvas.getGraphicsContext2D().setFill(Color.WHITE);
		canvas.getGraphicsContext2D().fill();
		canvas.setOnMousePressed(new MousePressed(this,canvas.getGraphicsContext2D()));
		canvas.setOnMouseDragged(new MouseDragged(this,canvas.getGraphicsContext2D()));
		canvas.setOnMouseReleased(new MouseReleased(this,canvas.getGraphicsContext2D()));
		
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
			primaryStage.close();
		} );
		
		
		f.getItems().addAll(nouv,close);
		
		Button box=new Button("Box");
		box.setOnAction( (e) -> {
			tool=new ToolRect();
			label.setText(tool.getName());
		} );
		
		Button ellipse=new Button("Ellipse");
		ellipse.setOnAction( (e) -> {
			tool=new ToolEllipse();
			label.setText(tool.getName());
		 } );
		
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
		
		vbox.getChildren().add(new MenuBar(f,new Menu("Edit"),tools));
		
		
		
		ToolBar toolbar=new ToolBar(box,ellipse,new Button("Img..."));
		vbox.getChildren().add(toolbar);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(new Separator());
		vbox.getChildren().add(label);
		primaryStage.setScene(new javafx.scene.Scene(vbox));
		primaryStage.setTitle("Dreamland");
		
		board.addClip(new ClipEllipse(200, 150, 400, 250, Color.RED));
		board.draw(canvas.getGraphicsContext2D());
		primaryStage.show();
	}

	@Override
	public Board getBoard() {
		return board;
	}

}
