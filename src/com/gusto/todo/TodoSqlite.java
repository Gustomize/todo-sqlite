package com.gusto.todo;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.gusto.todo.view.TarefaView;

public class TodoSqlite {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				String defaultLookAndFeel = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(defaultLookAndFeel);
				TarefaView frame = new TarefaView();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
