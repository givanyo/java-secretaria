package controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AlunoDAO;
import model.Aluno;
import view.TelaAluno;

public class AlunoController {
	TelaAluno tela;
	AlunoDAO alunoDAO;
	
	public AlunoController(TelaAluno tela) {
		this.tela = tela;
		this.alunoDAO = new AlunoDAO();
	}
	
	public void salvar() {
		String nomeAluno = tela.getTxtNomeAluno().getText().trim();
		String cpf = tela.getTxtCPF().getText().trim();
		String dataNascimentoTexto = tela.getTxtDataNascimento().getText().trim();
		String genero = tela.getTxtGenero().getText().trim().toUpperCase();
		String nomeResponsavel = tela.getTxtNomeResponsavel().getText().trim();
		Boolean afro = tela.getCheckAfro().isSelected();
		Boolean escolaridadePublica = tela.getCheckEscolaridadePublica().isSelected();
		LocalDate dataNascimento;
		if(nomeAluno.isEmpty() ||
		   cpf.isEmpty() ||
		   dataNascimentoTexto.isEmpty() ||
		   genero.isEmpty() ||
		   nomeResponsavel.isEmpty()) {
		     JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos de texto.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
             );
             return;
		 }
		if(!cpf.matches("\\d+")) {
			JOptionPane.showMessageDialog(
					tela,
					"O cpf deve ter apenas caracteres numéricos.",
					"Atenção",
					JOptionPane.WARNING_MESSAGE
				);
			return;
		}
		
		if(cpf.length() != 11) {
			JOptionPane.showMessageDialog(
					tela, 
					"O cpf deve ter exatamente 11 caracteres. Não use pontuações.",
					"Atenção",
					JOptionPane.WARNING_MESSAGE
					);
			return;
		}
		
		if(nomeAluno.length() > 100) {
			
			JOptionPane.showMessageDialog(
					tela,
					"O nome do aluno não pode ter mais de 100 caracteres.",
					"Atenção",
					JOptionPane.WARNING_MESSAGE
					);
			return;
					
		}
		
		if(nomeResponsavel.length() > 100) {
			
			JOptionPane.showMessageDialog(
					tela,
					"O nome do responsável não pode ter mais de 100 caracteres.",
					"Atenção",
					JOptionPane.WARNING_MESSAGE
					);
			return;
					
		}
		if(genero.length() != 1 || (!genero.equals("M") && !genero.equals("F"))
		) {
			JOptionPane.showMessageDialog(
					tela,
					"O gênero deve ter apenas um caractere: M ou F.",
					"Atenção",
					JOptionPane.WARNING_MESSAGE
				);
			return;
		}
		
		try {
			dataNascimento = LocalDate.parse(dataNascimentoTexto);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(
					tela,
					"Preencha a data de nascimento no formato AAAA-MM-DD. A data precisa ser válida.",
					"Atenção",
					JOptionPane.WARNING_MESSAGE
				);
				return;
		}
		
		try {
			String idTexto = tela.getTxtId().getText().trim();
			if(idTexto.isEmpty()) {
				Aluno aluno = new Aluno(nomeAluno, cpf, dataNascimento, genero, nomeResponsavel, afro, escolaridadePublica);
				alunoDAO.salvar(aluno);
				JOptionPane.showMessageDialog(tela, "Aluno salvo com sucesso.");
			} else {
				Aluno aluno = new Aluno(Integer.parseInt(idTexto), nomeAluno, cpf, dataNascimento, genero, nomeResponsavel, afro, escolaridadePublica);
				alunoDAO.atualizar(aluno);
				JOptionPane.showMessageDialog(tela, "Aluno atualizado com sucesso.");
			}
			
			limpar();
			carregarTabela();
			
		} catch(Exception e) {
            JOptionPane.showMessageDialog(
                    tela,
                    "Erro ao salvar: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
		}
		
	}
	
    public void excluir() {
        int linha = tela.getTabelaAlunos().getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(
                tela,
                "Selecione um aluno na tabela para excluir.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
            tela,
            "Deseja realmente excluir o aluno selecionado?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(tela.getTxtId().getText());
            alunoDAO.excluir(id);
            JOptionPane.showMessageDialog(tela, "Aluno excluído com sucesso.");
            limpar();
            carregarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                tela,
                "Erro ao excluir: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void limpar() {
        tela.getTxtId().setText("");
        tela.getTxtNomeAluno().setText("");
        tela.getTxtCPF().setText("");
        tela.getTxtDataNascimento().setText("");
        tela.getTxtGenero().setText("");
        tela.getTxtNomeResponsavel().setText("");
        
        tela.getCheckAfro().setSelected(false);
        tela.getCheckEscolaridadePublica().setSelected(false);
        
        tela.getTxtNomeAluno().requestFocus();
        tela.getTabelaAlunos().clearSelection();
    }
    
    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tela.getTabelaAlunos().getModel();
        modelo.setRowCount(0);

        try {
            List<Aluno> alunos = alunoDAO.listar();

            int i;
            for (i = 0; i < alunos.size(); i++) {
                Aluno a = alunos.get(i);
                modelo.addRow(new Object[] {
                    a.getId(),
                    a.getNomeAluno(),
                    a.getCpf(),
                    a.getDataNascimento(),
                    a.getGenero(),
                    a.getNomeResponsavel(),
                    a.getAfro(),
                    a.getEscolaridadePublica(),
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                tela,
                "Erro ao carregar tabela: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void preencherFormulario() {
        int linha = tela.getTabelaAlunos().getSelectedRow();

        if (linha != -1) {
            tela.getTxtId().setText(tela.getTabelaAlunos().getValueAt(linha, 0).toString());
            tela.getTxtNomeAluno().setText(tela.getTabelaAlunos().getValueAt(linha, 1).toString());
            tela.getTxtCPF().setText(tela.getTabelaAlunos().getValueAt(linha, 2).toString());
            tela.getTxtDataNascimento().setText(tela.getTabelaAlunos().getValueAt(linha, 3).toString());
            tela.getTxtGenero().setText(tela.getTabelaAlunos().getValueAt(linha, 4).toString());
            tela.getTxtNomeResponsavel().setText(tela.getTabelaAlunos().getValueAt(linha, 5).toString());
            tela.getCheckAfro().setSelected(
            	    (Boolean) tela.getTabelaAlunos().getValueAt(linha, 6)
            	);
            tela.getCheckEscolaridadePublica().setSelected(
            	    (Boolean) tela.getTabelaAlunos().getValueAt(linha, 7)
            	);
        }
    }
}
