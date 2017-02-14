package oving2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SkjemaController {
	
	private String errorcode;
	private Date currentDate = new Date();
	
	@FXML
	Label message;
	
	@FXML
	TextField formaal, rom, repIntervall;
	
	@FXML
	DatePicker dato, sluttDato;
	
	@FXML
	ChoiceBox<String> fraTimer, fraMin, tilTimer, tilMin;
	
	@FXML
	void handleButtonClick(ActionEvent event) {
		errorcode = "Ugyldig:";
		
		if (formaal.getText().equals("")) {
			errorcode += " Formål";
		}
		if (!rom.getText().matches("[-A-Za-z ]+ [0-9]+")) {
			errorcode += " Rom";
		}
		if (dato.getValue() == null){
			errorcode += " Dato";
		} else {
			int year = Integer.parseInt(dato.getValue().toString().substring(0, 4));
			int month = Integer.parseInt(dato.getValue().toString().substring(5, 7));
			int day = Integer.parseInt(dato.getValue().toString().substring(8, 10));
			Calendar cal = new GregorianCalendar();
			if (year < cal.get(Calendar.YEAR) || month < cal.get(Calendar.MONTH)+1 || day < cal.get(Calendar.DAY_OF_MONTH)) {
				errorcode += " Dato";
			}	
		}
		if (fraTimer.getValue().equals("Timer") || fraMin.getValue().equals("Minutter") ||
			tilTimer.getValue().equals("Timer") || tilMin.getValue().equals("Minutter")) {
			errorcode += " Tidsrom";
		} else {
			int fra = Integer.parseInt(fraTimer.getValue()+fraMin.getValue());
			int til = Integer.parseInt(tilTimer.getValue()+tilMin.getValue());
			if (fra > til) {
				errorcode += " Tidsrom";
			}
			
		}
		
		if (!repIntervall.getText().matches("[1-9]{1}[0-9]?") && !repIntervall.getText().isEmpty()) {
			errorcode += " Repetisjon";
			System.out.println(repIntervall.getText());
		} else if (sluttDato.getValue() == null && !repIntervall.getText().isEmpty()){
			errorcode += " Slutt-Dato";
		} else if (repIntervall.getText().matches("[1-9]{1}[0-9]?")) {
				int year = Integer.parseInt(dato.getValue().toString().substring(0, 4));
				int month = Integer.parseInt(dato.getValue().toString().substring(5, 7));
				int day = Integer.parseInt(dato.getValue().toString().substring(8, 10));
				int sluttYear = Integer.parseInt(sluttDato.getValue().toString().substring(0, 4));
				int sluttMonth = Integer.parseInt(sluttDato.getValue().toString().substring(5, 7));
				int sluttDay = Integer.parseInt(sluttDato.getValue().toString().substring(8, 10));
				if (sluttYear < year || sluttMonth < month || sluttDay < day) {
					errorcode += " Slutt-dato";
				}	
			}
		
		if (!errorcode.equals("Ugyldig:"))
			message.setText(errorcode);
		else {
			message.setText("Rom reservert!");
		}
		//System.out.println("year: " + year + " | " + cal.get(Calendar.YEAR));
		//System.out.println("month: " + month + " | " + (cal.get(Calendar.MONTH)+1));
		//System.out.println("day: " + day + " | "+ cal.get(Calendar.DAY_OF_MONTH));
		//System.out.println(fra);
		//System.out.println("RepIntervall empty: " + repIntervall.getText().isEmpty());
	}
	
}
