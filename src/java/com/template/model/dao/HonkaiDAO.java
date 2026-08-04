package com.template.model.dao;

import com.template.model.ConexaoBD;
import com.template.model.dto.HonkaiDTO;
import com.template.util.DialogUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HonkaiDAO {

    private static final Logger logger = Logger.getLogger(HonkaiDAO.class.getName());

    public boolean cadastrar(HonkaiDTO honkai) {
        String sql = "INSERT INTO honkai (nome, elemento, efeito, raridade) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, honkai.getNome());
            stmt.setString(2, honkai.getElemento());
            stmt.setString(3, honkai.getEfeito());
            stmt.setString(4, honkai.getRaridade());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar personagem", e);
            DialogUtil.showError("Erro ao cadastrar personagem no banco de dados.");
            return false;
        }
    }

    public List<HonkaiDTO> listar() {
        String sql = "SELECT * FROM honkai ORDER BY id ASC";
        List<HonkaiDTO> listaPersonagens = new ArrayList<>();

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HonkaiDTO personagem = new HonkaiDTO();
                personagem.setId(rs.getInt("id"));
                personagem.setNome(rs.getString("nome"));
                personagem.setElemento(rs.getString("elemento"));
                personagem.setEfeito(rs.getString("efeito"));
                personagem.setRaridade(rs.getString("raridade"));

                listaPersonagens.add(personagem);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar personagens", e);
            DialogUtil.showError("Erro ao listar dados do banco.");
        }
        return listaPersonagens;
    }

    public boolean editar(HonkaiDTO honkai) {
        String sql = "UPDATE honkai SET nome = ?, elemento = ?, efeito = ?, raridade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, honkai.getNome());
            stmt.setString(2, honkai.getElemento());
            stmt.setString(3, honkai.getEfeito());
            stmt.setString(4, honkai.getRaridade());
            stmt.setInt(5, honkai.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao editar personagem", e);
            DialogUtil.showError("Erro ao atualizar o personagem no banco de dados.");
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM honkai WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir personagem", e);
            DialogUtil.showError("Erro ao excluir personagem do banco de dados.");
            return false;
        }
    }
}