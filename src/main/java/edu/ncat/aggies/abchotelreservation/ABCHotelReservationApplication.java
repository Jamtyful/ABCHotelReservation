package edu.ncat.aggies.abchotelreservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

//Required example of single inheritance
public class ABCHotelReservationApplication extends Application {

    //Required example of dynamic polymorphism
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(new URL(ABCHotelReservationApplication.class.getResource("reservation-view.fxml").toExternalForm())), 320, 240);
        stage.setTitle("ABC Hotel Reservation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}