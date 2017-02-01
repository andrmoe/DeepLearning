package testing;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Test1Controller {
	
	private String realPassword = "hallaballa";
	
	@FXML
	TextField username, password;
	
	@FXML
	void handleUNChange(StringProperty prop, String oldValue, String newValue) {
		username.setText(newValue.toLowerCase());
	}
	
	@FXML
	void handleLoginButton(ActionEvent event) {
		if (password.getText().equals("hallaballa")) {
			System.out.println("Login successful!");
			System.out.println("Welcome, " + username.getText());
			loginSuccessful();
		}
		else{
			System.out.println("Login failed!");
			System.out.println(password.getText() + " is not the correct pw.");
		}
		
	}
	
	void loginSuccessful() {
		username.setText("LOGIN successful");
	}
}
