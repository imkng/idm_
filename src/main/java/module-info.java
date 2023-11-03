module com.programming {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.programming to javafx.fxml;
    exports com.programming;
}