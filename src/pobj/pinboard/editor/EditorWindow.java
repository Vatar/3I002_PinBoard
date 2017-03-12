package pobj.pinboard.editor;


import javax.swing.JToolBar;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;

public class EditorWindow extends javafx.application.Application
 implements EditorInterface{
	
	private Board board;
	
	public EditorWindow(Stage stage) throws Exception{
		start(stage);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(600, 400);
		VBox vbox = new VBox();
		
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
		
		
		vbox.getChildren().add(new MenuBar(f,new Menu("Edit"),new Menu("Tools")));
		
		
		ToolBar toolbar=new ToolBar(new Button("Box"),new Button("Ellipse"),new Button("Img..."));
		vbox.getChildren().add(toolbar);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(new Separator());
		vbox.getChildren().add(new Label("Idle"));
		primaryStage.setScene(new javafx.scene.Scene(vbox));
		primaryStage.setTitle("Dreamland");
		
		board = new Board();
		board.addClip(new ClipRect(100, 100, 300, 200, Color.BLUE));
		board.addClip(new ClipEllipse(200, 150, 400, 250, Color.RED));
		board.draw(canvas.getGraphicsContext2D());
		primaryStage.show();
	}

	@Override
	public Board getBoard() {
		return board;
	}

}
