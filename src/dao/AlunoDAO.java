package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import util.Conexao;

public class AlunoDAO {
	
	public void salvar(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO aluno (nomeAluno, cpf, dataNascimento, genero, nomeResponsavel, afro, escolaPublica) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aluno.getNomeAluno());
			stmt.setString(2, aluno.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(aluno.getDataNascimento()));
			stmt.setString(4, aluno.getGenero());
			stmt.setString(5, aluno.getNomeResponsavel());
			stmt.setBoolean(6, aluno.getAfro());
			stmt.setBoolean(7, aluno.getEscolaridadePublica());
			stmt.executeUpdate();
			
		} catch(Exception e) {
			throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizar(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE aluno SET nomeAluno = ?, cpf = ?, dataNascimento = ?, genero = ?, nomeResponsavel = ?, afro = ?, escolaPublica = ? WHERE id = ?";
		
		try {
			conn = Conexao.conectar();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aluno.getNomeAluno());
			stmt.setString(2, aluno.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(aluno.getDataNascimento()));
			stmt.setString(4, aluno.getGenero());
			stmt.setString(5, aluno.getNomeResponsavel());
			stmt.setBoolean(6, aluno.getAfro());
			stmt.setBoolean(7, aluno.getEscolaridadePublica());
			stmt.setInt(8, aluno.getId());
			stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void excluir(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM aluno WHERE id = ?";
        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	public List<Aluno> listar() {
		List<Aluno> lista = new ArrayList<Aluno>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT id, nomeAluno, cpf, dataNascimento, genero, nomeResponsavel, afro, escolaPublica FROM aluno ORDER BY id DESC";
        try {
        	conn = Conexao.conectar();
        	stmt = conn.prepareStatement(sql);
        	rs = stmt.executeQuery();
        	
        	while(rs.next()) {
        		Aluno aluno = new Aluno();
        		aluno.setId(rs.getInt("id"));
        		aluno.setNomeAluno(rs.getString("nomeAluno"));
        		aluno.setCpf(rs.getString("cpf"));
        		aluno.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
        		aluno.setGenero(rs.getString("genero"));
        		aluno.setNomeResponsavel(rs.getString("nomeResponsavel"));
        		aluno.setAfro(rs.getBoolean("afro"));
        		aluno.setEscolaridadePublica(rs.getBoolean("escolaPublica"));
        		lista.add(aluno);
        	}
        } catch(Exception e) {
        	throw new RuntimeException("Erro ao listar alunos: " + e.getMessage());
        } finally { 
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
	}
}
