package com.gusto.todo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.gusto.todo.controller.TarefaController;
import com.gusto.todo.exception.ControllerException;
import com.gusto.todo.model.Tarefa;

public class TarefaView extends JFrame {

	private static final long serialVersionUID = 1L;

	private TarefaController controller = new TarefaController();

	private JPanel contentPane;
	private JPanel panelNovatarefa;
	private JPanel panelTodasTarefas;
	private JScrollPane scrollPane;
	private JTable tableTarefas;
	private JLabel lblNovaTarefa;
	private JTextField textFieldNovaTarefa;
	private JButton btnAdicionar;
	
	private TarefaTableModel tableModel;

	public TarefaView() {
		renderizarTela();
	}

	private void renderizarTela() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelNovatarefa = new JPanel();
		contentPane.add(panelNovatarefa, BorderLayout.NORTH);
		panelNovatarefa.setLayout(new BorderLayout(0, 0));

		lblNovaTarefa = new JLabel("Nova tarefa: ");
		panelNovatarefa.add(lblNovaTarefa, BorderLayout.WEST);

		textFieldNovaTarefa = new JTextField();
		textFieldNovaTarefa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					adicionarTarefa();
				}
			}
		});
		panelNovatarefa.add(textFieldNovaTarefa, BorderLayout.CENTER);
		textFieldNovaTarefa.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(e -> {
			adicionarTarefa();
		});
		panelNovatarefa.add(btnAdicionar, BorderLayout.EAST);

		panelTodasTarefas = new JPanel();
		contentPane.add(panelTodasTarefas, BorderLayout.CENTER);
		panelTodasTarefas.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelTodasTarefas.add(scrollPane, BorderLayout.CENTER);

		tableTarefas = new JTable();
		tableModel = new TarefaTableModel(controller.getTarefas());
		tableTarefas.setModel(tableModel);
		tableTarefas.setPreferredScrollableViewportSize(new Dimension(500,300));
		tableTarefas.setFillsViewportHeight(true);
		tableTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableTarefas);

		this.setTitle("Tarefas");
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void adicionarTarefa() {
		try {
			Tarefa novaTarefa = new Tarefa();
			controller = new TarefaController();
			novaTarefa.setTitulo(textFieldNovaTarefa.getText().trim());
			novaTarefa.setConcluido(false);
			controller.inserirTarefa(novaTarefa);
			tableModel.onAdd(novaTarefa);
			textFieldNovaTarefa.setText("");
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
}
