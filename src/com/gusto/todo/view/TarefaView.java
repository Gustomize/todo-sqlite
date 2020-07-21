package com.gusto.todo.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TarefaView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelNovatarefa;
	private JPanel panelTodasTarefas;
	
	private JScrollPane scrollPane;
	private JTable tableTarefas;

	public TarefaView() {
		renderizarTela();
	}
	
	public void renderizarTela() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNovatarefa = new JPanel();
		contentPane.add(panelNovatarefa, BorderLayout.NORTH);
		panelNovatarefa.setLayout(new BorderLayout(0, 0));
		
		panelTodasTarefas = new JPanel();
		contentPane.add(panelTodasTarefas, BorderLayout.CENTER);
		panelTodasTarefas.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelTodasTarefas.add(scrollPane, BorderLayout.CENTER);
		
		tableTarefas = new JTable();
		scrollPane.setViewportView(tableTarefas);

		this.setTitle("Tarefas");
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
