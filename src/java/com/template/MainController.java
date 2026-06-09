package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private ComboBox<String> comboElemento;
    @FXML private ComboBox<String> comboRaridade;
    @FXML private TextArea txtEfeito;

    @FXML private TableView<HonkaiDTO> tabelaItens;
    @FXML private TableColumn<HonkaiDTO, Integer> colId;
    @FXML private TableColumn<HonkaiDTO, String> colNome;
    @FXML private TableColumn<HonkaiDTO, String> colElemento;
    @FXML private TableColumn<HonkaiDTO, String> colRaridade;
    @FXML private TableColumn<HonkaiDTO, String> colEfeito;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colElemento.setCellValueFactory(new PropertyValueFactory<>("elemento"));
        colRaridade.setCellValueFactory(new PropertyValueFactory<>("raridade"));
        colEfeito.setCellValueFactory(new PropertyValueFactory<>("efeito"));

        comboElemento.getSelectionModel().selectFirst();
        comboRaridade.getSelectionModel().select(2);
    }

    @FXML
    void onBotaoSalvarClick() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String elemento = comboElemento.getValue();
            String raridade = comboRaridade.getValue();
            String efeito = txtEfeito.getText();

            HonkaiDTO novoItem = new HonkaiDTO(id, nome, elemento, raridade, efeito);
            tabelaItens.getItems().add(novoItem);

            System.out.println("Item adicionado na tabela com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            System.out.println("ERRO: O campo ID precisa ser um número válido!");
        }
    }

    @FXML
    void onTabelaClicada() {
        HonkaiDTO itemSelecionado = tabelaItens.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            txtId.setText(String.valueOf(itemSelecionado.getId()));
            txtNome.setText(itemSelecionado.getNome());
            comboElemento.setValue(itemSelecionado.getElemento());
            comboRaridade.setValue(itemSelecionado.getRaridade());
            txtEfeito.setText(itemSelecionado.getEfeito());
        }
    }

    @FXML
    void onBotaoEditarClick() {
        HonkaiDTO itemSelecionado = tabelaItens.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            try {
                int index = tabelaItens.getItems().indexOf(itemSelecionado);

                HonkaiDTO itemAtualizado = new HonkaiDTO(
                        Integer.parseInt(txtId.getText()),
                        txtNome.getText(),
                        comboElemento.getValue(),
                        comboRaridade.getValue(),
                        txtEfeito.getText()
                );

                tabelaItens.getItems().set(index, itemAtualizado);
                System.out.println("Item atualizado com sucesso!");
                limparCampos();

            } catch (NumberFormatException e) {
                System.out.println("ERRO: O campo ID precisa ser um número!");
            }
        } else {
            System.out.println("Selecione um item na tabela primeiro para poder editar!");
        }
    }

    @FXML
    void onBotaoExcluirClick() {
        HonkaiDTO itemSelecionado = tabelaItens.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            tabelaItens.getItems().remove(itemSelecionado);
            System.out.println("Item excluído com sucesso!");
            limparCampos();
        } else {
            System.out.println("Selecione um item na tabela primeiro para poder excluir!");
        }
    }

    @FXML
    void onBotaoLimparClick() {
        limparCampos();
        System.out.println("Campos limpos e seleção desfeita!");
    }

    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        txtEfeito.clear();
        comboElemento.getSelectionModel().selectFirst();
        comboRaridade.getSelectionModel().select(2);
        tabelaItens.getSelectionModel().clearSelection();
    }
}