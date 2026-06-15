package com.template;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.List;

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

    @FXML private TableView<HonkaiDTO> tabelaItens;
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
        try {
            List<HonkaiDTO> lista = dao.listar();
            ObservableList<HonkaiDTO> dados = FXCollections.observableArrayList(lista);
            tabelaItens.setItems(dados);
        } catch (SQLException e) {
            exibirMensagem("Erro ao carregar dados do banco.", true);
        }
    }

    @FXML
    void onBotaoSalvarClick() {
        if (txtNome.getText().isEmpty() || txtEfeito.getText().isEmpty()) {
            exibirMensagem("Preencha todos os campos antes de salvar!", true);
            return;
        }

        try {
            HonkaiDTO novoItem = new HonkaiDTO(
                    0,
                    txtNome.getText(),
                    comboElemento.getValue(),
                    comboRaridade.getValue(),
                    txtEfeito.getText()
            );

            dao.cadastrar(novoItem);
            exibirMensagem("Personagem cadastrado com sucesso!", false);
            limparCampos();
            carregarTabela();

        } catch (SQLException e) {
            exibirMensagem("Erro ao salvar no banco de dados.", true);
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

            btnSalvar.setDisable(true);
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
            lblMensagem.setText("");
        }
    }

    @FXML
    void onBotaoEditarClick() {
        if (txtId.getText().isEmpty()) return;

        try {
            HonkaiDTO itemAtualizado = new HonkaiDTO(
                    Integer.parseInt(txtId.getText()),
                    txtNome.getText(),
                    comboElemento.getValue(),
                    comboRaridade.getValue(),
                    txtEfeito.getText()
            );

            dao.editar(itemAtualizado);
            exibirMensagem("Personagem atualizado com sucesso!", false);
            limparCampos();
            carregarTabela();

        } catch (SQLException e) {
            exibirMensagem("Erro ao atualizar o personagem.", true);
        }
    }

    @FXML
    void onBotaoExcluirClick() {
        if (txtId.getText().isEmpty()) return;

        try {
            int id = Integer.parseInt(txtId.getText());
            dao.excluir(id);
            exibirMensagem("Personagem excluído com sucesso!", false);
            limparCampos();
            carregarTabela();

        } catch (SQLException e) {
            exibirMensagem("Erro ao excluir personagem.", true);
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
        tabelaItens.getSelectionModel().clearSelection();

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
        }
    }
}