package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dominio.Fornecedor;

public class FornecedorDAO {
    private final Connection conexaoBD;

	public FornecedorDAO(Connection connection) throws Exception {
		 this.conexaoBD = connection;
    }
	 
	public void inserirFornecedor(Fornecedor fornecedor) {
		try {
			PreparedStatement preStm = conexaoBD.prepareStatement(
					"insert into fornecedor (nome, cnpj, telefone, endereco, cidade, estado, observacao) values (?,?,?,?,?,?,?)");
            preStm.setString(1, fornecedor.getNome().toUpperCase());
            preStm.setString(2, fornecedor.getCnpj().toUpperCase());
            preStm.setString(3, fornecedor.getTelefone().toUpperCase());
            preStm.setString(4, fornecedor.getEndereco().toUpperCase());
            preStm.setString(5, fornecedor.getCidade().toUpperCase());
            preStm.setString(6, fornecedor.getEstado().toUpperCase());
            preStm.setString(7, fornecedor.getObservacao().toUpperCase());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	 }
	 
	 public ArrayList<?> listarFornecedores() {				    
        PreparedStatement preStm;
        ArrayList<Object> lista = new ArrayList<>();
        try {
			preStm = conexaoBD.prepareStatement("select * from fornecedor order by 2", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet resultado = preStm.executeQuery();
		    while (resultado.next()) {
            	lista.add(new Object[] {
       				 resultado.getInt("idFornecedor"), 
       				 resultado.getString("nome"), 
       				 resultado.getString("telefone"), 
       				 resultado.getString("cnpj"), 
       				 resultado.getString("endereco"), 
       				 resultado.getString("cidade"), 
       				 resultado.getString("estado"), 
       				 resultado.getString("observacao")
       			});
        	}
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return lista;
    }
	 
	 public ArrayList<?> buscarFornecedor(String stringBusca) {
        PreparedStatement preStm;
        ArrayList<Object> lista = new ArrayList<>();

        try {
            preStm = conexaoBD.prepareStatement(
            		"select * from fornecedor where nome LIKE ? OR  cnpj LIKE ? OR telefone LIKE ? OR cidade LIKE ? OR estado LIKE ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preStm.setString(1, stringBusca+"%");
            preStm.setString(2, stringBusca+"%");
            preStm.setString(3, stringBusca+"%");
            preStm.setString(4, stringBusca+"%");
            preStm.setString(5, stringBusca+"%");
            ResultSet resultado = preStm.executeQuery();
        	while (resultado.next()) {
        		lista.add(new Object[] {
	   				 resultado.getInt("idFornecedor"), 
	   				 resultado.getString("nome"), 
	   				 resultado.getString("telefone"), 
	   				 resultado.getString("cnpj"), 
	   				 resultado.getString("endereco"), 
	   				 resultado.getString("cidade"), 
	   				 resultado.getString("estado"), 
	   				 resultado.getString("observacao")
       			});
        	}

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return lista;
    }

	public void atualizarFornecedor(Fornecedor fornecedor) {
		try {
            PreparedStatement preStm = conexaoBD.prepareStatement(
            		"update fornecedor set nome = ?, telefone = ?, cnpj = ?, endereco = ?, cidade = ?, estado = ?, observacao = ? where idFornecedor = ?");
            preStm.setString(1, fornecedor.getNome());
            preStm.setString(2, fornecedor.getTelefone());
            preStm.setString(3, fornecedor.getCnpj());
            preStm.setString(4, fornecedor.getEndereco());
            preStm.setString(5, fornecedor.getCidade());
            preStm.setString(6, fornecedor.getEstado());
            preStm.setString(7, fornecedor.getObservacao());
            preStm.setInt(8, fornecedor.getId());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	
	}

	public void excluirFornecedor(int idFornecedor) {
		try {
            PreparedStatement preStm = conexaoBD.prepareStatement(
            		"delete from fornecedor where idFornecedor = ?");
            preStm.setInt(1, idFornecedor);
            preStm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	
	
	 public ArrayList<Fornecedor> listarFornecedoresComboBoxProduto() {
        PreparedStatement preStm;
        ArrayList<Fornecedor> lista = new ArrayList<>();
        try {
			preStm = conexaoBD.prepareStatement("select * from fornecedor", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    ResultSet resultado = preStm.executeQuery();
		    while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(resultado.getString("nome"));
                lista.add(fornecedor);
            }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return lista;
	 }

	public int buscarIdFornecedorProduto(String nomeFornecedor) {
        PreparedStatement preStm;
        int id = 0;
        try {
           preStm = conexaoBD.prepareStatement(
            		"select * from fornecedor where nome = ? ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           preStm.setString(1, nomeFornecedor);
           ResultSet resultado = preStm.executeQuery();
           resultado.next();
           id = resultado.getInt("idFornecedor"); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return id;
	}

	public String buscarNomeFornecedorProduto(int idFornecedor) {
		PreparedStatement preStm;
        String nome = null;
        try {
           preStm = conexaoBD.prepareStatement(
            		"select * from fornecedor where idFornecedor = ? ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           preStm.setInt(1, idFornecedor);
           ResultSet resultado = preStm.executeQuery();
           resultado.next();
           nome = resultado.getString("nome"); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return nome;
	}

		 
	 
		
}

