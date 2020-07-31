package com.gusto.todo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gusto.todo.view.TarefaView;

public class TodoSqlite {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {

			try {
				String SystemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(SystemLookAndFeel);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				JOptionPane.showMessageDialog(null, "Não foi possível usar a biblioteca padrão do seu SO.", "Aviso", JOptionPane.WARNING_MESSAGE);
				System.out.println("");
			} finally {
				TarefaView frame = new TarefaView();
				frame.setTitle("Tarefas");
				frame.setSize(600, 800);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
