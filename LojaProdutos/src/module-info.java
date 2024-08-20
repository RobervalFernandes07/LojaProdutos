module LojaProdutos {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	
	opens br.com.etec.model to javafx.graphics, javafx.fxml;
}
