package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.dominio.Venda;

public class VendaDAO {
	private final Connection conexaoBD;
	
	
	public VendaDAO(Connection connection) throws Exception {
		this.conexaoBD = connection;	
	}
	
	public int buscarCodigoVenda() {
		int codigoVenda = 0;

        PreparedStatement preStm;
        try {
            preStm = conexaoBD.prepareStatement("select idVenda from venda where idVenda = (SELECT MAX(idVenda) FROM venda);");
            ResultSet resultado = preStm.executeQuery();
            while (resultado.next()) {
            	codigoVenda = resultado.getInt("idVenda");
                return codigoVenda;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigoVenda;
	}
	
	public void inserirVenda(Venda venda) throws ParseException {
		try {
			PreparedStatement preStm = conexaoBD.prepareStatement(
					"insert into venda (idVenda, dataVenda, valorVenda, descontoReais, descontoPorcentagem, parcelas, valorVendaFinal, formaPagamento) values (?, ?, ?, ?, ?, ?, ?, ?)");
            preStm.setInt(1, venda.getIdVenda());
            preStm.setFloat(3, venda.getValorVenda());
            preStm.setFloat(4, venda.getDescontoReais());
            preStm.setInt(5, venda.getDescontoPorcentagem());
            preStm.setInt(6, venda.getParcelas());
            preStm.setFloat(7, venda.getValorFinal());
            preStm.setString(8, venda.getFormaPagamento());
            
            DateFormat datanormal = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatoHumano = new Date(datanormal.parse(venda.getDataVenda()).getTime());
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataBanco = simpleDateFormat.format(dataFormatoHumano);
            preStm.setDate(2, new Date(simpleDateFormat.parse(dataBanco).getTime()));
            
            
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	public ArrayList<?> listarVendas(String data) {
		PreparedStatement preStm;
        ArrayList<Object> lista = new ArrayList<>();

        try {
            preStm = conexaoBD.prepareStatement(
            		"select * from venda where dataVenda = ? order by 1", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preStm.setDate(1, java.sql.Date.valueOf(data));
            ResultSet resultado = preStm.executeQuery();
        	while (resultado.next()) {
        		lista.add(new Object[] {
        			resultado.getInt("idVenda"),
        			resultado.getDate("dataVenda"), 
        			resultado.getFloat("valorVenda"), 
   	   				resultado.getFloat("descontoReais"), 
   	   				resultado.getInt("descontoPorcentagem"),
   	   				resultado.getInt("parcelas"),
   	   				resultado.getString("formaPagamento"), 
   	   				resultado.getFloat("valorVendaFinal"),
       			});
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return lista;
	}

	public Venda buscarCodigoVenda(int codigoVenda) {
		PreparedStatement preStm;

        try {
            preStm = conexaoBD.prepareStatement(
            		"select * from venda where idVenda = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preStm.setInt(1, codigoVenda);
            ResultSet resultado = preStm.executeQuery();
            while (resultado.next()) {
            	Venda venda = new Venda();
            	venda.setIdVenda(resultado.getInt("idVenda"));
            	return venda;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public void atualizarValorVenda(Float novoValor, int idVenda) {
		try {
			 PreparedStatement preStm= conexaoBD.prepareStatement(
					 "update venda set valorvendafinal = ? where idVenda = ?");
         preStm.setFloat(1, novoValor);
         preStm.setInt(2, idVenda);
         preStm.executeUpdate(); 
	    } catch (SQLException e) {
	         e.printStackTrace();
	    }
		
	}

}
