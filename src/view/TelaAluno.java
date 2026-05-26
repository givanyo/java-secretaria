package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.AlunoController;

public class TelaAluno extends JFrame {
	private static final long serialVersionUID = 10L;
	
	private JLabel lblId;
	private JLabel lblNomeAluno;
	private JLabel lblCPF;
	private JLabel lblDataNascimento;

	private JLabel lblGenero;
	private JLabel lblNomeResponsavel;
	
	private JLabel lblAfro;
	private JLabel lblEscolaridade;
	
	private JTextField txtId;
	private JTextField txtNomeAluno;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txtGenero;
	private JTextField txtNomeResponsavel;
	
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnLimparCampos;
	
	private JCheckBox checkAfro;
	private JCheckBox checkEscolaridadePublica;
	
	private JTable tabelaAlunos;
	private DefaultTableModel modeloTabela;
	
	private AlunoController controller;
	
	public TelaAluno() {
		setTitle("Secretaria - Cadastrar alunos");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		criarComponentes();
		controller = new AlunoController(this);
		configurarEventos();
		controller.carregarTabela();
	}
	
	private void criarComponentes() {
		JPanel painelFormulario = new JPanel(new GridLayout(8, 2, 16, 24));
		painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do aluno"));
		
		lblId = new JLabel("ID:");
		txtId = new JTextField();
		txtId.setEditable(false);
		
		lblNomeAluno = new JLabel("Nome do aluno:");
		txtNomeAluno = new JTextField();
		
		lblCPF = new JLabel("CPF do aluno:");
		txtCPF = new JTextField();
		
		lblDataNascimento = new JLabel("Data de nascimento:");
		txtDataNascimento = new JTextField();
		
		lblGenero = new JLabel("Gênero (M/F):");
		txtGenero = new JTextField();
		
		lblNomeResponsavel = new JLabel("Nome do responsável:");
		txtNomeResponsavel = new JTextField();
		
		lblAfro = new JLabel("Afrodescendente: ");
		checkAfro = new JCheckBox();
		
		lblEscolaridade = new JLabel("Escolaridade Pública:");
		checkEscolaridadePublica = new JCheckBox();
		
		painelFormulario.add(lblId);
		painelFormulario.add(txtId);
		
		painelFormulario.add(lblNomeAluno);
		painelFormulario.add(txtNomeAluno);
		
		painelFormulario.add(lblCPF);
		painelFormulario.add(txtCPF);
		
		painelFormulario.add(lblDataNascimento);
		painelFormulario.add(txtDataNascimento);
		
		painelFormulario.add(lblGenero);
		painelFormulario.add(txtGenero);
		
		painelFormulario.add(lblNomeResponsavel);
		painelFormulario.add(txtNomeResponsavel);
		
		painelFormulario.add(lblAfro);
		painelFormulario.add(checkAfro);
		
		painelFormulario.add(lblEscolaridade);
		painelFormulario.add(checkEscolaridadePublica);
		
		add(painelFormulario, BorderLayout.NORTH);
		
        modeloTabela = new DefaultTableModel(new Object[] { "ID", "NomeAluno", "CPF", "DataNascimento", "Genero", "NomeResponsavel", "Afro", "E. P." }, 0) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaAlunos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Alunos"));
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        btnSalvar = new JButton("Salvar");
        btnExcluir = new JButton("Excluir");
        btnLimparCampos = new JButton("Limpar Campos");
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimparCampos);
        
        add(painelBotoes, BorderLayout.SOUTH);
		
	}
	
    private void configurarEventos() {

        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.salvar();
            }
        });

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.excluir();
            }
        });

        btnLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.limpar();
            }
        });

        tabelaAlunos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controller.preencherFormulario();
            }
        });
    }
	
	public JTextField getTxtId() {
		return txtId;
	}

	public JTextField getTxtNomeAluno() {
		return txtNomeAluno;
	}

	public JTextField getTxtCPF() {
		return txtCPF;
	}

	public JTextField getTxtDataNascimento() {
		return txtDataNascimento;
	}

	public JTextField getTxtGenero() {
		return txtGenero;
	}

	public JTextField getTxtNomeResponsavel() {
		return txtNomeResponsavel;
	}

	public JCheckBox getCheckAfro() {
		return checkAfro;
	}

	public JCheckBox getCheckEscolaridadePublica() {
		return checkEscolaridadePublica;
	}
	public JTable getTabelaAlunos() {
		return tabelaAlunos;
	}
	
}
