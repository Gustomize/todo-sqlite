package com.gusto.todo.dao;

import java.util.List;

import com.gusto.todo.exception.DaoException;

public interface DAO<T> {

	public void inserir(T t) throws DaoException;

	public List<T> listar() throws DaoException;

	public void editar(int id, T t) throws DaoException;

	public void remover(int id) throws DaoException;

}
