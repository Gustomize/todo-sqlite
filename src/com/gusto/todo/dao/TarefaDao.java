package com.gusto.todo.dao;

import com.gusto.todo.connection.Conexao;
import com.gusto.todo.exception.DaoException;
import com.gusto.todo.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDao implements DAO<Tarefa> {

    @Override
    public void inserir(Tarefa t) throws DaoException {
        String sql = "insert into tarefa (titulo, concluido) values (?, ?)";

        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, t.getTitulo());
            preparedStatement.setBoolean(2, false);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

    }

    @Override
    public List<Tarefa> listar() throws DaoException {
        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "select id, titulo, concluido from tarefa";

        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setTitulo(resultSet.getString("titulo"));
                tarefa.setConcluido(resultSet.getBoolean("concluido"));
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return tarefas;
    }

    @Override
    public void editar(int id, Tarefa t) throws DaoException {
        String sql = "update tarefa set titulo = ?, concluido = ? where id = ?";

        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, t.getTitulo());
            preparedStatement.setBoolean(2, t.isConcluido());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

    }

    @Override
    public void remover(int id) throws DaoException {
        String sql = "delete from tarefa where id = ?";

        try (Connection conn = Conexao.getConexao().conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

    }

}
