package br.com.etec.model; // Pacote onde a classe está localizada




import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent; // Importa a classe para tratar eventos de ação
import javafx.fxml.FXML; // Importa a anotação FXML para associar elementos da interface
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert; // Importa a classe para criar mensagens de alerta
import javafx.scene.control.Button; // Importa a classe para criar botões
import javafx.scene.control.PasswordField; // Importa a classe para campos de senha
import javafx.scene.control.TextField; // Importa a classe para campos de texto
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Operacoes { // Classe que controla a lógica da interface gráfica
	 @FXML
	 private Stage primaryStage;
	
    @FXML
    private TextField txfUsuario; // Campo de texto para o nome de usuário

    @FXML
    private PasswordField psfUsuario; // Campo de senha para a senha do usuário

    @FXML
    private Button btnAcessar; // Botão para acionar o login
    
    @FXML 
    private Button btnFechar; // Botão para Fechar
    
    @FXML
    private Stage acpPalco;
    
    

    @FXML
    private void acessarConta(ActionEvent event) throws SQLException, IOException { // Método chamado quando o botão é clicado
        
        // Obtém o texto inserido pelo usuário no campo de nome
        String nomeUsuario = txfUsuario.getText();
        
        // Obtém o texto inserido pelo usuário no campo de senha
        String senhaUsuario = psfUsuario.getText();
        
        // Verifica se algum dos campos está vazio
        if(nomeUsuario.isEmpty() || senhaUsuario.isEmpty()) {
            
            // Se o campo de nome estiver vazio
            if(nomeUsuario.isEmpty()) {
                mostrarMensagem(Alert.AlertType.WARNING, 
                        "FALTANDO DADOS", "INFORMAR O USUÁRIO"); // Exibe uma mensagem de alerta
            } else {
                // Se o campo de senha estiver vazio
                if(senhaUsuario.isEmpty()) {
                    mostrarMensagem(Alert.AlertType.WARNING, 
                            "FALTANDO DADOS", "INFORMAR A SENHA"); // Exibe uma mensagem de alerta
                    txfUsuario.clear();
                    psfUsuario.clear();
                }
            }
        } else { // Se ambos os campos estiverem preenchidos
            // Verifica se o nome de usuário e senha estão corretos
            if(verificarUsuarioSenha(nomeUsuario,senhaUsuario)) {
                mostrarMensagem(Alert.AlertType.CONFIRMATION, 
                        "ACESSO PERMITIDO", "Logado no sistema.");// Exibe uma mensagem de confirmação
                acessarTelaPrincipal(event);
            } else {
                mostrarMensagem(Alert.AlertType.ERROR, 
                        "ERRO DE ACESSO", "Usuário ou senha errada."); // Exibe uma mensagem de erro
            }
        }
    }

    //----------------------------------------------------------------
    private void mostrarMensagem(Alert.AlertType tipo, String titulo, String mensagem) {
        // Cria um alerta com o tipo especificado (por exemplo, WARNING, CONFIRMATION, ERROR)
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo); // Define o título do alerta
        alerta.setHeaderText(null); // Remove o cabeçalho do alerta
        alerta.setContentText(mensagem); // Define a mensagem a ser exibida
        alerta.showAndWait(); // Exibe o alerta e espera o usuário interagir com ele
    }
    
    @FXML 
    private void fecharTelaLogin(ActionEvent event) {
    	acpPalco = (Stage) btnFechar.getScene().getWindow();
    	acpPalco.close();
    }
    
    private boolean verificarUsuarioSenha(String usuario, String senha) throws SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean usuarioValido = false;

        try {
            conexao = Conexao.conectar();
            String sql = "SELECT * FROM login_tb WHERE usuario = ? AND senha = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuarioValido = true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            Conexao.fechar(conexao);
        }

        return usuarioValido;
    }
    
    private void acessarTelaPrincipal(ActionEvent event) throws IOException {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/com/etec/view/TelaPrincipal.fxml"));
            
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/br/com/etec/view/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Adicione isto para ver o erro no console
            mostrarMensagem(Alert.AlertType.ERROR, "ERRO", "Não foi possível carregar a tela principal.");
        }
    }


}

