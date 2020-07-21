package com.gusto.todo.controller;

import java.util.ArrayList;
import java.util.List;

import com.gusto.todo.dao.DAO;
import com.gusto.todo.dao.TarefaDao;
import com.gusto.todo.exception.ControllerException;
import com.gusto.todo.exception.DaoException;
import com.gusto.todo.model.Tarefa;

public class TarefaController {

	DAO<Tarefa> dao = new TarefaDao();
	List<Tarefa> tarefas = new ArrayList<Tarefa>();

	public void inserirTarefa(Tarefa t) throws ControllerException {
		try {
			dao.inserir(t);
			tarefas.clear();
			tarefas.addAll(dao.listar());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ControllerException();
		}
	}

	public void listarTarefas() throws ControllerException {
		try {
			tarefas.clear();
			tarefas.addAll(dao.listar());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ControllerException();
		}
	}

	public void editarTarefa(int id, Tarefa t) throws ControllerException {
		try {
			dao.editar(id, t);
			tarefas.clear();
			tarefas.addAll(dao.listar());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ControllerException();
		}
	}

	public void removerTarefa(int id) throws ControllerException {
		try {
			dao.remover(id);
			tarefas.clear();
			tarefas.addAll(dao.listar());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ControllerException();
		}
	}

	public List<Tarefa> getTarefas() {
		try {
			tarefas = dao.listar();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return tarefas;
	}

}
