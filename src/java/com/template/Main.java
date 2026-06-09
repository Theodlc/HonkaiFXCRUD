package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Carrega o arquivo visual (main.fxml)
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));

        // Define o título da janela do Windows
        stage.setTitle("Terminal de Dados Honkai");

        // --- ADICIONANDO O ÍCONE DA APLICAÇÃO ---
        try {
            // Busca a imagem icon.png dentro da pasta resources/com/template
            Image icone = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/template/icon.png")));
            stage.getIcons().add(icone);
        } catch (Exception e) {
            System.out.println("Aviso: O ícone não foi carregado. Verifique se icon.png está na pasta correta.");
        }
        // ----------------------------------------

        // Prepara a cena com as dimensões e exibe a tela
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Impede que o usuário redimensione a janela (opcional, deixa o layout fixo e bonito)
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}