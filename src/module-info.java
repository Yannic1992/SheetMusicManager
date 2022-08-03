module SheetMusicManager {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	//opens application to javafx.fxml;
	
	exports application;
}
