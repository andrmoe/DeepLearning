package oving1;

import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Hangman extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		
		Pane hangmanPane = new Pane();
		hangmanPane.setPrefSize(500, 500);
		
		Line ground = new Line(50,400,450,400);
		Line leg1 = new Line(200,370,250,250);
		Line leg2 = new Line(250,250,300,370);
		Line vPole = new Line(100,400,100,20);
		Line hPole = new Line(100,20,250,20);
		Line rope = new Line(250,20,250,50);
		Ellipse head = new Ellipse(250,100,50,50);
		head.setFill(Color.WHITE);
		head.setStroke(Color.BLACK);
		Line body = new Line(250,150,250,250);
		Line arm1 = new Line(250,175,150,250);
		Line arm2 = new Line(250,175,350,250);
		Line eye11 = new Line(225,75,240,90);
		Line eye12 = new Line(225,90,240,75);
		Line eye21 = new Line(265,75,280,90);
		Line eye22 = new Line(265,90,280,75);
		QuadCurve mouth = new QuadCurve();
		mouth.setStartX(230);
		mouth.setStartY(125);
		mouth.setEndX(270);
		mouth.setEndY(125);
		mouth.setControlX(250);
		mouth.setControlY(100);
		mouth.setStroke(Color.BLACK);
		mouth.setFill(Color.WHITE);
		
		
		hangmanPane.getChildren().addAll(ground, leg1, leg2, vPole, hPole, rope, head, body,
										arm1, arm2, eye11, eye12, eye21, eye22, mouth);
		root.setCenter(hangmanPane);
		Scene scene = new Scene(root,500,500);
        
		stage.setScene(scene);
        stage.setTitle("BorderPaneApplication");
        stage.show();
	}
	
	public static void main(String[] args) {
        launch(Hangman.class, args);
    }
}
