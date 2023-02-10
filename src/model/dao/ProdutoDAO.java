package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dominio.Produto;

public class ProdutoDAO {
	private final Connection conexaoBD;
	
	public ProdutoDAO(Connection connection) throws Exception {
		 this.conexaoBD = connection;
	}

	public void inserirProduto(Produto produto) {
		try {
			PreparedStatement preStm = conexaoBD.prepareStatement(
					"insert into produto (codigoProduto, tipo, modelo, tamanho, quantidade, precoPago, precoVenda, idFornecedor, observacao) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preStm.setString(1, produto.getCodigoProduto());
            preStm.setString(2, produto.getTipo());
            preStm.setString(3, produto.getModelo());
            preStm.setString(4, produto.getTamanho());
            preStm.setInt(5, produto.getQuantidade());
            preStm.setFloat(6, produto.getPrecoPago());
            preStm.setFloat(7, produto.getPrecoVenda());
            preStm.setInt(8, produto.getIdFornecedor());
            preStm.setString(9, produto.getObservacao());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void atualizarProduto(Produto produto) {
		try {
            PreparedStatement preStm = conexaoBD.prepareStatement(
            		"update produto set tipo = ?, modelo = ?, tamanho = ?, quantidade = ?, precoPago = ?, precoVenda = ?, idFornecedor = ?, observacao = ? where codigoProduto = ?");
            preStm.setString(1, produto.getTipo());
            preStm.setString(2, produto.getModelo());
            preStm.setString(3, produto.getTamanho());
            preStm.setInt(4, produto.getQuantidade());
            preStm.setFloat(5, produto.getPrecoPago());
            preStm.setFloat(6, produto.getPrecoVenda());
            preStm.setInt(7, produto.getIdFornecedor());
            preStm.setString(8, produto.getObservacao());
            preStm.setString(9, produto.getCodigoProduto());
            preStm.executeUpdate();
   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	public void excluirProduto(String codigo) {
		try {
            PreparedStatement preStm = conexaoBD.prepareStatement(
            		"delete from produto where codigoProduto = ?");
            preStm.setString(1, codigo);
            preStm.execute();
   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public ArrayList<?> listarProdutos() {
		PreparedStatement preStm;
        ArrayList<Object> lista = new ArrayList<>();

        try {
            preStm = conexaoBD.prepareStatement(
            		"select * from produto order by 3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultado = preStm.executeQuery();
        	while (resultado.next()) {
        		lista.add(new Object[] {
	   				 resultado.getString("codigoProduto"), 
	   				 resultado.getString("tipo"), 
	   				 resultado.getString("modelo"), 
	   				 resultado.getString("tamanho"), 
	   				 resultado.getInt("quantidade"),
	   				 resultado.getFloat("precoPago"), 
	   				 resultado.getFloat("precoVenda"),
	   				 resultado.getString("observacao"),
	   				 resultado.getInt("idFornecedor")
       			});
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return lista;
	}
	
	public ArrayList<?> buscarProduto(String stringBusca) {
		PreparedStatement preStm;
        ArrayList<Object> lista = new ArrayList<>();

        try {
            preStm = conexaoBD.prepareStatement(
            		"select * from produto where codigoProduto LIKE ? OR tipo LIKE ? OR modelo LIKE ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            preStm.setInt(1, Integer.parseInt(stringBusca));
            preStm.setString(1, stringBusca+"%");
            preStm.setString(2, stringBusca+"%");
            preStm.setString(3, stringBusca+"%");
            ResultSet resultado = preStm.executeQuery();
        	while (resultado.next()) {
        		lista.add(new Object[] {
        			resultado.getInt("codigoProduto"), 
   	   				 resultado.getString("tipo"), 
   	   				 resultado.getString("modelo"), 
   	   				 resultado.getString("tamanho"),
   	   				 resultado.getString("quantidade"),
   	   				 resultado.getString("precoPago"), 
   	   				 resultado.getString("precoVenda"),
   	   				 resultado.getString("observacao"),
   	   				 resultado.getString("idFornecedor")
       			});
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return lista;
	}
	
	
	
	
	
	
	// Para Venda e Troca
	public Produto buscarProdutoVenda(String codigoProduto) {
		PreparedStatement preStm;

        try {
            preStm = conexaoBD.prepareStatement(
            		"select * from produto where codigoProduto = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preStm.setString(1, codigoProduto);
            ResultSet resultado = preStm.executeQuery();
            while (resultado.next()) {
            	Produto produto = new Produto();
            	produto.setCodigoProduto(resultado.getString("codigoProduto"));
            	produto.setTipo(resultado.getString("tipo"));
            	produto.setModelo(resultado.getString("modelo"));
            	produto.setTamanho(resultado.getString("tamanho"));
            	produto.setQuantidade(resultado.getInt("quantidade"));
            	produto.setPrecoVenda(resultado.getFloat("precoVenda"));
            	produto.setObservacao(resultado.getString("observacao"));
            	return produto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public void diminuirQtdProduto(String produto, int quantidade) {
		 try {
            PreparedStatement preStm = conexaoBD.prepareStatement("" +
                    "update produto set quantidade = quantidade - ?  WHERE produto.codigoProduto = ?");
            preStm.setInt(1, quantidade);
            preStm.setString(2, produto);
            preStm.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public void aumentarQtdProduto(String produto, int quantidade) {
		 try {
           PreparedStatement preStm = conexaoBD.prepareStatement("" +
                   "update produto set quantidade = quantidade + ?  WHERE produto.codigoProduto = ?");
           preStm.setInt(1, quantidade);
           preStm.setString(2, produto);
           preStm.executeUpdate();
          
       } catch (SQLException e) {
           e.printStackTrace();
       }
		
	}

}
