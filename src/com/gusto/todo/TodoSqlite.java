package com.gusto.todo;

import com.gusto.todo.view.TarefaView;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TodoSqlite {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            try {
                String systemLookAndFeel = getSystemLookAndFeelClassName();
                setLookAndFeel(systemLookAndFeel);
            } catch (ClassNotFoundException |
                     InstantiationException |
                     IllegalAccessException |
                     UnsupportedLookAndFeelException e) {

                showMessageDialog(null,
                        "N�o foi poss�vel usar a biblioteca padr�o do seu SO.",
                        "Aviso",
                        WARNING_MESSAGE);

                System.out.println("");
            } finally {
                TarefaView frame = new TarefaView();
                frame.setTitle("Tarefas");
                frame.setSize(600, 800);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
