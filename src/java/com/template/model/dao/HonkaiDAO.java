package com.template.model.dao;

import com.template.model.ConexaoBD;
import com.template.model.dto.HonkaiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HonkaiDAO {

    public void cadastrar(HonkaiDTO honkai) throws SQLException {
        String sql = "INSERT INTO honkai (nome, elemento, efeito, raridade) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, honkai.getNome());
            stmt.setString(2, honkai.getElemento());
            stmt.setString(3, honkai.getEfeito());
            stmt.setString(4, honkai.getRaridade());

            stmt.executeUpdate();
        }
    }

    public List<HonkaiDTO> listar() throws SQLException {
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
        }
        return listaPersonagens;
    }

    public void editar(HonkaiDTO honkai) throws SQLException {
        String sql = "UPDATE honkai SET nome = ?, elemento = ?, efeito = ?, raridade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, honkai.getNome());
            stmt.setString(2, honkai.getElemento());
            stmt.setString(3, honkai.getEfeito());
            stmt.setString(4, honkai.getRaridade());
            stmt.setInt(5, honkai.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM honkai WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}