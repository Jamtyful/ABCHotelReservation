module edu.ncat.aggies.abchotelreservation {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ncat.aggies.abchotelreservation to javafx.fxml;
    exports edu.ncat.aggies.abchotelreservation;
}