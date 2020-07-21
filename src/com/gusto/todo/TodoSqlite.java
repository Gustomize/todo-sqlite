package com.gusto.todo;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.gusto.todo.view.TarefaView;

public class TodoSqlite {

	private static TarefaView frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				String defaultLookAndFeel = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(defaultLookAndFeel);
				frame = new TarefaView();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
