package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dominio.ItemVenda;

public class ItemVendaDAO {
	private final Connection conexaoBD;
	
	public ItemVendaDAO(Connection connection) throws Exception {
		this.conexaoBD = connection;
	}
	
	public void inserirItemVenda(ItemVenda itemVenda) {
		 try {
			 PreparedStatement preStm= conexaoBD.prepareStatement(
					 "insert into item_venda(idVenda, codigoProduto, valorUnitario, quantidadeComprada, valorTotal) values(?, ?, ?, ?, ?)");
             preStm.setInt(1, itemVenda.getVenda().getIdVenda());
             preStm.setString(2, itemVenda.getProduto().getCodigoProduto());
             preStm.setFloat(3, itemVenda.getValorUnitario());
             preStm.setInt(4, itemVenda.getQuantidadeComprada());
             preStm.setFloat(5, itemVenda.getValorTotal());
             preStm.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	public ArrayList<?> listarItensVenda(int codigoVenda) {
		PreparedStatement preStm;
        ArrayList<Object> lista = new ArrayList<>();

        try {
            preStm = conexaoBD.prepareStatement(
            		"select idVenda, item_venda.codigoproduto, tipo, modelo, tamanho, valorunitario, quantidadecomprada, valortotal, observacao \r\n"
            		+ "from produto inner join item_venda on item_venda.codigoProduto = produto.codigoProduto \r\n"
            		+ "where item_venda.idVenda = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preStm.setInt(1, codigoVenda);
            ResultSet resultado = preStm.executeQuery();
        	while (resultado.next()) {
        		lista.add(new Object[] {
        			resultado.getInt("idVenda"),
        			resultado.getString("codigoproduto"), 
        			resultado.getString("tipo"), 
   	   				resultado.getString("modelo"), 
   	   				resultado.getString("tamanho"),
   	   				resultado.getFloat("valorunitario"), 
   	   				resultado.getInt("quantidadecomprada"),
   	   				resultado.getFloat("valortotal"),
   	   				resultado.getString("observacao")
       			});
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return lista;
	}

	public void removerItemVenda(int idVenda, String codigoProduto) {
		try {
			 PreparedStatement preStm= conexaoBD.prepareStatement(
					 "delete from item_venda where idVenda = ? AND codigoProduto = ?");
            preStm.setInt(1, idVenda);
            preStm.setString(2, codigoProduto);
            preStm.executeUpdate(); 
       } catch (SQLException e) {
           e.printStackTrace();
       }
		
	}

	public void atualizarQtdItemVenda(int idVenda, String codigoProduto, int quantidade) {
		try {
			 PreparedStatement preStm= conexaoBD.prepareStatement(
					 "update item_venda set quantidadeComprada = quantidadeComprada - ? where idVenda = ? and codigoProduto = ?");
	       preStm.setInt(1, quantidade);
           preStm.setInt(2, idVenda);
           preStm.setString(3, codigoProduto);
           preStm.executeUpdate(); 
	     } catch (SQLException e) {
	          e.printStackTrace();
	     }
			
	}

	public void atualizarTotalItemVenda(int idVenda, String codigoProduto) {
		try {
			 PreparedStatement preStm= conexaoBD.prepareStatement(
					 "update item_venda set valortotal = (quantidadecomprada * valorunitario) where idVenda = ? and codigoProduto = ?");
          preStm.setInt(1, idVenda);
          preStm.setString(2, codigoProduto);
          preStm.executeUpdate(); 
	    } catch (SQLException e) {
	         e.printStackTrace();
	    }
		
	}

	public ArrayList<ItemVenda> buscarItensVenda(int idVenda) {
		PreparedStatement preStm;
        ArrayList<ItemVenda> lista = new ArrayList<>();
        
        try {
			preStm = conexaoBD.prepareStatement("select * from item_venda where idVenda = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        preStm.setInt(1, idVenda);
			ResultSet resultado = preStm.executeQuery();
		    while (resultado.next()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setValorTotal(resultado.getFloat("valorTotal"));
                lista.add(itemVenda);
            }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return lista;
	}
}
