module WalleterWhite {
	
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	exports loginPage; 
	exports dashboard;
	
	opens loginPage to javafx.graphics,javafx.fxml;
	opens dashboard to javafx.graphics,javafx.fxml,loginPage;
	opens application to javafx.graphics, javafx.fxml;
}
