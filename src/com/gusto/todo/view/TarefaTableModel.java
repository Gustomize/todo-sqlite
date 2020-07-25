package com.gusto.todo.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gusto.todo.controller.TarefaController;
import com.gusto.todo.model.Tarefa;

public class TarefaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final int ID = 0;
	private final int TAREFA = 1;
	private final int CONCLUIDO = 2;
	private String[] colunas = { "ID", "Tarefa", "Concluído" };

	private TarefaController controller = new TarefaController();

	private List<Tarefa> dados;

	public TarefaTableModel() {
		this.dados = controller.getTarefas();
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case ID:
			return dados.get(rowIndex).getId();
		case TAREFA:
			return dados.get(rowIndex).getTitulo();
		case CONCLUIDO:
			return dados.get(rowIndex).isConcluido();
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case ID:
			return int.class;
		case TAREFA:
			return String.class;
		case CONCLUIDO:
			return Boolean.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == ID) {
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Tarefa tarefa = dados.get(rowIndex);

		switch (columnIndex) {
		case TAREFA:
			tarefa.setTitulo((String) aValue);
			break;
		case CONCLUIDO:
			tarefa.setConcluido((boolean) aValue);
			break;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	public Tarefa getValue(int rowIndex) {
		return dados.get(rowIndex);
	}

	public int indexOf(Tarefa empregado) {
		return dados.indexOf(empregado);
	}

	public void onAdd(Tarefa tarefa) {
		dados = controller.getTarefas();
		fireTableRowsInserted(indexOf(tarefa), indexOf(tarefa));
	}

	public void onRemove(int rowIndex) {
		dados.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void onRemove(Tarefa tarefa) {
		int indexBefore = indexOf(tarefa);
		dados.remove(tarefa);
		fireTableRowsDeleted(indexBefore, indexBefore);
	}

}
