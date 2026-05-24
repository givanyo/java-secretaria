package app;

import javax.swing.SwingUtilities;

import view.TelaAluno;

public class Main {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaAluno tela = new TelaAluno();
                tela.setVisible(true);
            }
        });

	}

}
