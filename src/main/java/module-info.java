module com.yannic1992.sheetmusicmanager {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;

    opens com.yannic1992.sheetmusicmanager to javafx.graphics, javafx.fxml;

    exports com.yannic1992.sheetmusicmanager;
}