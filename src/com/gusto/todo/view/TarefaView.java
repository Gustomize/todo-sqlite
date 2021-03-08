package com.gusto.todo.view;

import com.gusto.todo.controller.TarefaController;
import com.gusto.todo.exception.ControllerException;
import com.gusto.todo.model.Tarefa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.FlowLayout.CENTER;
import static java.awt.event.KeyEvent.VK_ENTER;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class TarefaView extends JFrame {

    private static final long serialVersionUID = 1L;

    private TarefaController controller = new TarefaController();

    private JTable tableTarefas;
    private JTextField textFieldNovaTarefa;
    private TarefaTableModel tableModel;
    private JButton btnAlterarTarefa;
    private JButton btnExcluirTarefa;
    private JButton btnRenomearTarefa;

    public TarefaView() {
        renderizarTela();
    }

    private void renderizarTela() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelNovatarefa = new JPanel();
        contentPane.add(panelNovatarefa, BorderLayout.NORTH);
        panelNovatarefa.setLayout(new BorderLayout(0, 0));

        JLabel lblNovaTarefa = new JLabel("Nova tarefa: ");
        panelNovatarefa.add(lblNovaTarefa, BorderLayout.WEST);

        textFieldNovaTarefa = new JTextField();
        textFieldNovaTarefa.addKeyListener(getKeyListener());
        panelNovatarefa.add(textFieldNovaTarefa, BorderLayout.CENTER);
        textFieldNovaTarefa.setColumns(10);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> adicionarTarefa());
        panelNovatarefa.add(btnAdicionar, EAST);

        JPanel panelTodasTarefas = new JPanel();
        contentPane.add(panelTodasTarefas, BorderLayout.CENTER);
        panelTodasTarefas.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panelTodasTarefas.add(scrollPane, BorderLayout.CENTER);

        tableTarefas = new JTable();
        tableTarefas.setFillsViewportHeight(true);
        tableTarefas.addMouseListener(getMouseClickedEvent());
        tableModel = new TarefaTableModel(controller.getTarefas());
        tableTarefas.setModel(tableModel);
        tableTarefas.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tableTarefas.setSelectionMode(SINGLE_SELECTION);

        scrollPane.setViewportView(tableTarefas);

        JPanel panelOpcoes = new JPanel();
        contentPane.add(panelOpcoes, SOUTH);
        panelOpcoes.setLayout(new FlowLayout(CENTER, 5, 5));

        btnAlterarTarefa = new JButton("Concluir Tarefa");
        btnAlterarTarefa.setEnabled(false);
        btnAlterarTarefa.addActionListener(this::getActionListener);

        btnRenomearTarefa = new JButton("Renomear Tarefa");
        btnRenomearTarefa.addActionListener(e -> actionListenerRenomearTarefa());
        btnRenomearTarefa.setEnabled(false);
        panelOpcoes.add(btnRenomearTarefa);
        panelOpcoes.add(btnAlterarTarefa);

        btnExcluirTarefa = new JButton("Excluir Tarefa");
        btnExcluirTarefa.setEnabled(false);
        btnExcluirTarefa.addActionListener(e -> actionListenerRemoverTarefa());
        panelOpcoes.add(btnExcluirTarefa);
}

    private void actionListenerRemoverTarefa() {
        Tarefa tarefaExcluida = tableModel.getValue(tableTarefas.getSelectedRow());
        removerTarefa(tarefaExcluida);
    }

    private void actionListenerRenomearTarefa() {
        Tarefa tarefaRenomeada = tableModel.getValue(tableTarefas.getSelectedRow());
        String tituloTarefa = showInputDialog("Digite o nome da nova tarefa");
        renomearTarefa(tarefaRenomeada, tituloTarefa);
    }

    private KeyAdapter getKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    adicionarTarefa();
                }
            }
        };
    }

    private MouseAdapter getMouseClickedEvent() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tableTarefas.getSelectedRow() >= 0) {
                    btnRenomearTarefa.setEnabled(true);
                    btnAlterarTarefa.setEnabled(true);
                    btnExcluirTarefa.setEnabled(true);
                    alteraTextoBtn();
                }
            }
        };
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

    private void removerTarefa(Tarefa tarefa) {
        try {
            controller = new TarefaController();
            controller.removerTarefa(tarefa.getId());
            tableModel.onRemove(tableModel.indexOf(tarefa));
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void alteraTextoBtn() {
        if (tableModel.getValueAt(tableTarefas.getSelectedRow(), 1) == Boolean.TRUE) {
            btnAlterarTarefa.setText("Refazer Tarefa");
        } else {
            btnAlterarTarefa.setText("Concluir Tarefa");
        }
    }

    private void concluirTarefa(Tarefa tarefa) {
        try {
            controller = new TarefaController();
            tarefa.setConcluido(true);
            controller.editarTarefa(tarefa.getId(), tarefa);
            tableModel.onUpdate(tarefa);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void refazerTarefa(Tarefa tarefa) {
        try {
            controller = new TarefaController();
            tarefa.setConcluido(false);
            controller.editarTarefa(tarefa.getId(), tarefa);
            tableModel.onUpdate(tarefa);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void renomearTarefa(Tarefa tarefa, String novoTitulo) {
        try {
            controller = new TarefaController();
            tarefa.setTitulo(novoTitulo);
            controller.editarTarefa(tarefa.getId(), tarefa);
            tableModel.onUpdate(tarefa);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private void getActionListener(ActionEvent e) {
        Tarefa tarefaEditada = tableModel.getValue(tableTarefas.getSelectedRow());

        if (tableModel.getValueAt(tableTarefas.getSelectedRow(), 1) == Boolean.TRUE) {
            refazerTarefa(tarefaEditada);
        } else {
            concluirTarefa(tarefaEditada);
        }
    }
}
