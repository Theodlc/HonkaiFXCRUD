package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<String> comboElemento;

    @FXML
    private ComboBox<String> comboRaridade;

    @FXML
    private TextArea txtEfeito;


    @FXML
    private TableView<Item> tabelaItens;

    @FXML
    private TableColumn<Item, Integer> colId;

    @FXML
    private TableColumn<Item, String> colNome;

    @FXML
    private TableColumn<Item, String> colElemento;

    @FXML
    private TableColumn<Item, String> colRaridade;

    @FXML
    private TableColumn<Item, String> colEfeito;


    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colElemento.setCellValueFactory(new PropertyValueFactory<>("elemento"));
        colRaridade.setCellValueFactory(new PropertyValueFactory<>("raridade"));
        colEfeito.setCellValueFactory(new PropertyValueFactory<>("efeito"));

        comboElemento.getSelectionModel().selectFirst(); // Seleciona o primeiro elemento ("Físico")
        comboRaridade.getSelectionModel().select(2);      // Seleciona o terceiro item ("5 Estrelas")
    }

    @FXML
    void onBotaoSalvarClick() {
        String id = txtId.getText();
        String nome = txtNome.getText();
        String elemento = comboElemento.getValue();
        String raridade = comboRaridade.getValue();
        String efeito = txtEfeito.getText();

        System.out.println("\n--- [ NOVO ITEM CAPTURADO ] ---");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Elemento: " + elemento);
        System.out.println("Raridade: " + raridade);
        System.out.println("Efeito: " + efeito);
        System.out.println("--------------------------------");
    }
}