package br.com.etec.model; // Pacote onde a classe está localizada

import javafx.event.ActionEvent; // Importa a classe para tratar eventos de ação
import javafx.fxml.FXML; // Importa a anotação FXML para associar elementos da interface
import javafx.scene.control.Alert; // Importa a classe para criar mensagens de alerta
import javafx.scene.control.Button; // Importa a classe para criar botões
import javafx.scene.control.PasswordField; // Importa a classe para campos de senha
import javafx.scene.control.TextField; // Importa a classe para campos de texto

public class Operacoes { // Classe que controla a lógica da interface gráfica

    @FXML
    private TextField txfUsuario; // Campo de texto para o nome de usuário

    @FXML
    private PasswordField psfUsuario; // Campo de senha para a senha do usuário

    @FXML
    private Button btnAcessar; // Botão para acionar o login

    @FXML
    private void acessarConta(ActionEvent event) { // Método chamado quando o botão é clicado
        
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
                }
            }
        } else { // Se ambos os campos estiverem preenchidos
            // Verifica se o nome de usuário e senha estão corretos
            if(nomeUsuario.equals("admin") && senhaUsuario.equals("123456")) {
                mostrarMensagem(Alert.AlertType.CONFIRMATION, 
                        "ACESSO PERMITIDO", "Logado no sistema."); // Exibe uma mensagem de confirmação
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
}

