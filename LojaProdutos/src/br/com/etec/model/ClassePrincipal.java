package br.com.etec.model;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ClassePrincipal implements Initializable {
	
	@FXML
	ComboBox<String> cmbTipo;
	
	@FXML
	ComboBox<String> cmbMarca;
	
	@FXML
	ComboBox<String> cmbModelo;
	
	@FXML
	Button btnTipo;
	
	@FXML
	Button btnMarca;
	
	@FXML
	Button btnModelo;
	
	public void initialize(URL url, ResourceBundle rb) {
		
//		ObservableList<String> lista = FXCollections.observableArrayList("Chinelo" , "Tênis");
//		cmbTipo.setItems(lista);
//		
//		ObservableList<String> lista2 = FXCollections.observableArrayList("Nike" , "Adidas");
//		cmbTipo.setItems(lista2);
//		
//		ObservableList<String> lista3 = FXCollections.observableArrayList("TEN001" ,"CHN" ,"TEA002" , "CHA001");
//		cmbTipo.setItems(lista3);
		
		// Chamar o metodo com as informações 
		
		carregarComboBox(cmbTipo,"tipo");
		carregarComboBox(cmbMarca,"marca");
		carregarComboBox(cmbModelo,"modelo");
		
		
	}
	
	// Método para inserir dados no banco de dados
//    public void inserirDados(String valor) {
//        String sql = "INSERT INTO produto (tipo, marca, modelo) VALUES (?, ?, ?)";
//        
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        
//        try {
//            conn = Conexao.conectar();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, tipoProduto);
//            pstmt.setString(2, marcaProduto);
//            pstmt.setString(3, modeloProduto);
//            pstmt.executeUpdate();
//            System.out.println("Inserção bem-sucedida!");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//        	Conexao.fechar(conn);
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
		private void carregarComboBox(ComboBox<String> comBox, String coluna){
			// Variavel lista = collections (guardar várias informações)
			ObservableList<String> lista = FXCollections.observableArrayList();
			
			try (Connection conn = Conexao.conectar()){
			// Pra executar o comando SQL e guardar na variável 
			String sql = "SELECT DISTINCT" + coluna + "FROM minhatabela";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				lista.add(rs.getString(coluna));
			}
			comBox.setItems(lista);
			
		}// try 
			
			catch(Exception e ) {
				e.printStackTrace();
			}
		}
}
