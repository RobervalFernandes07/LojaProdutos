module LojaProdutos {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	
	opens br.com.etec.model to javafx.graphics, javafx.fxml;
}
