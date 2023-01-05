module com.visualnovel.novel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.visualnovel.novel to javafx.fxml;
    exports com.visualnovel.novel;
    exports com.visualnovel.controllers;
    opens com.visualnovel.controllers to javafx.fxml;
}