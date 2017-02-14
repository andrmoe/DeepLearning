package oving1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WordfeudController {
	
	@FXML
	Button clickButton;
	
	@FXML
	void handleClickButton(ActionEvent event) {
		System.out.println("HALLA");
	}
}