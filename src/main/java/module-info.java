module com.chat.threading {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;


    opens com.chat.threading to javafx.fxml;
    exports com.chat.threading;
}