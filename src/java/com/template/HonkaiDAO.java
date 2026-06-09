package com.template;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HonkaiDAO {

    public void cadastrar(HonkaiDTO honkai) {
        String sql = "INSERT INTO honkai (nome, elemento, efeito, raridade) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, honkai.getNome());
            stmt.setString(2, honkai.getElemento());
            stmt.setString(3, honkai.getEfeito());
            stmt.setString(4, honkai.getRaridade());

            stmt.executeUpdate();
            System.out.println("Personagem cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Falha ao cadastrar dados: " + e.getMessage());
        }
    }

    public List<HonkaiDTO> listar() {
        String sql = "SELECT * FROM honkai";
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
            System.out.println("Falha ao listar dados: " + e.getMessage());
        }

        return listaPersonagens;
    }

    public void editar(HonkaiDTO honkai) {
        String sql = "UPDATE honkai SET nome = ?, elemento = ?, efeito = ?, raridade = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, honkai.getNome());
            stmt.setString(2, honkai.getElemento());
            stmt.setString(3, honkai.getEfeito());
            stmt.setString(4, honkai.getRaridade());
            stmt.setInt(5, honkai.getId());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Personagem atualizado com sucesso!");
            } else {
                System.out.println("ID não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Falha ao atualizar dados: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM honkai WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Personagem excluído com sucesso!");
            } else {
                System.out.println("ID não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Falha ao excluir dados: " + e.getMessage());
        }
    }
}