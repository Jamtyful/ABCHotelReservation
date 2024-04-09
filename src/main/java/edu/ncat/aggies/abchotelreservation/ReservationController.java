package edu.ncat.aggies.abchotelreservation;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    private RoomManager manager = new ABCRoomManager();

    @FXML
    private Label totalCost;

    @FXML
    private ComboBox<RoomType> roomTypeComboBox;

    @FXML
    private TextField customerField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    protected void onRoomTypeChanged(){
        updateCost();
    }

    @FXML
    protected void onStartDatePicked() {
        if (datesConsistent())
            updateCost();
        else endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
    }

    @FXML
    protected void onEndDatePicked() {
        if (datesConsistent() && startDatePicker.getValue() != null)
            updateCost();
        else {

            endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
            showAlert("The end date must be after the start date!", Alert.AlertType.ERROR);
        }
    }


    private void updateCost() {
        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) totalCost.setText("");
        else
            totalCost.setText(roomTypeComboBox.getValue().getCost() * Duration.between(startDatePicker.getValue().atStartOfDay(), endDatePicker.getValue().atStartOfDay()).toDays() + " dollars");
    }

    private boolean datesConsistent() {
        if (endDatePicker.getValue() == null || startDatePicker.getValue() == null) return true;
        return endDatePicker.getValue().isAfter(startDatePicker.getValue());
    }

    private static void showAlert(String errorText, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(errorText);
        alert.show();
    }

    @FXML
    protected void makeReservation() {
        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null)
            showAlert("Pick a start and end date.", Alert.AlertType.INFORMATION);
        if (manager.makeReservation(new RoomReservation(Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), customerField.getText()), roomTypeComboBox.getValue()) < 0)
            showAlert("No " + roomTypeComboBox.getValue() + " rooms available for selected dates!", Alert.AlertType.ERROR);
        else
            showAlert(String.format("Name: %s \nArrival Time: %s \nDeparture Tine: %s \nCost: %s", customerField.getText(), startDatePicker.getValue().toString(), endDatePicker.getValue().toString(), totalCost.getText()), Alert.AlertType.INFORMATION);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomTypeComboBox.setItems(FXCollections.observableArrayList(RoomType.values()));
        roomTypeComboBox.setValue(RoomType.REGULAR);
    }
}