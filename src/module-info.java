module CabinetMedical {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
	opens controllers to javafx.graphics, javafx.fxml,javafx.base;
	opens connecte    to javafx.graphics, javafx.fxml,javafx.base;
	opens models      to javafx.graphics, javafx.fxml,javafx.base;
	opens Icons      to javafx.graphics, javafx.fxml,javafx.base;
	opens images      to javafx.graphics, javafx.fxml,javafx.base;
	
}
