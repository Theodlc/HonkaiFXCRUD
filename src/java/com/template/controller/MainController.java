package com.template.controller;

import com.template.model.dao.HonkaiDAO;
import com.template.model.dto.HonkaiDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

import com.template.DialogUtil;

public class MainController {

    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private ComboBox<String> comboElemento;
    @FXML private ComboBox<String> comboRaridade;
    @FXML private TextArea txtEfeito;
    @FXML private Label lblMensagem;

    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;

    @FXML private TableView<HonkaiDTO> tblItens;
    @FXML private TableColumn<HonkaiDTO, Integer> colId;
    @FXML private TableColumn<HonkaiDTO, String> colNome;
    @FXML private TableColumn<HonkaiDTO, String> colElemento;
    @FXML private TableColumn<HonkaiDTO, String> colRaridade;
    @FXML private TableColumn<HonkaiDTO, String> colEfeito;

    private HonkaiDAO dao = new HonkaiDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colElemento.setCellValueFactory(new PropertyValueFactory<>("elemento"));
        colRaridade.setCellValueFactory(new PropertyValueFactory<>("raridade"));
        colEfeito.setCellValueFactory(new PropertyValueFactory<>("efeito"));

        comboElemento.getSelectionModel().selectFirst();
        comboRaridade.getSelectionModel().select(2);

        carregarTabela();
    }

    private void carregarTabela() {
        List<HonkaiDTO> lista = dao.listar();
        if (lista != null) {
            ObservableList<HonkaiDTO> dados = FXCollections.observableArrayList(lista);
            tblItens.setItems(dados);
        }
    }

    @FXML
    void onBotaoSalvarClick() {
        if (txtNome.getText().isEmpty() || txtEfeito.getText().isEmpty()) {
            exibirMensagem("Preencha todos os campos antes de salvar!", true);
            return;
        }

        HonkaiDTO novoItem = new HonkaiDTO(
                0,
                txtNome.getText(),
                comboElemento.getValue(),
                comboRaridade.getValue(),
                txtEfeito.getText()
        );

        boolean sucesso = dao.cadastrar(novoItem);
        if (sucesso) {
            exibirMensagem("Personagem cadastrado com sucesso!", false);
            limparCampos();
            carregarTabela();
        }
    }

    @FXML
    void onTabelaClicada() {
        HonkaiDTO itemSelecionado = tblItens.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            txtId.setText(String.valueOf(itemSelecionado.getId()));
            txtNome.setText(itemSelecionado.getNome());
            comboElemento.setValue(itemSelecionado.getElemento());
            comboRaridade.setValue(itemSelecionado.getRaridade());
            txtEfeito.setText(itemSelecionado.getEfeito());

            btnSalvar.setDisable(true);
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
            lblMensagem.setText("");
        }
    }

    @FXML
    void onBotaoEditarClick() {
        if (txtId.getText().isEmpty()) return;

        boolean confirmacao = DialogUtil.showConfirmation("Tem certeza que deseja salvar as alterações neste personagem?");
        if (!confirmacao) return;

        HonkaiDTO itemAtualizado = new HonkaiDTO(
                Integer.parseInt(txtId.getText()),
                txtNome.getText(),
                comboElemento.getValue(),
                comboRaridade.getValue(),
                txtEfeito.getText()
        );

        boolean sucesso = dao.editar(itemAtualizado);
        if (sucesso) {
            exibirMensagem("Personagem atualizado com sucesso!", false);
            limparCampos();
            carregarTabela();
        }
    }

    @FXML
    void onBotaoExcluirClick() {
        if (txtId.getText().isEmpty()) return;

        boolean confirmacao = DialogUtil.showConfirmation("Tem certeza que deseja excluir este personagem? Esta ação não pode ser desfeita.");
        if (!confirmacao) return;

        int id = Integer.parseInt(txtId.getText());
        boolean sucesso = dao.excluir(id);

        if (sucesso) {
            exibirMensagem("Personagem excluído com sucesso!", false);
            limparCampos();
            carregarTabela();
        }
    }

    @FXML
    void onBotaoLimparClick() {
        limparCampos();
        lblMensagem.setText("");
    }

    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        txtEfeito.clear();
        comboElemento.getSelectionModel().selectFirst();
        comboRaridade.getSelectionModel().select(2);
        tblItens.getSelectionModel().clearSelection();

        btnSalvar.setDisable(false);
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }

    private void exibirMensagem(String mensagem, boolean erro) {
        lblMensagem.setText(mensagem);
        if (erro) {
            lblMensagem.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        } else {
            lblMensagem.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
            DialogUtil.showInfo(mensagem);
        }
    }
}