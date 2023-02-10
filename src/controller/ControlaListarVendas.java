package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.dao.ItemVendaDAO;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;
import model.dominio.ItemVenda;
import model.dominio.Produto;
import model.dominio.Venda;
import utils.Tabela;
import view.JPanelListarVendas;

public class ControlaListarVendas implements MouseListener, ActionListener, KeyListener {
	private final JPanelListarVendas JPANEL_LISTARVENDAS;
	private static ProdutoDAO produtoDAO = null;
	private static ItemVendaDAO itemVendaDAO = null;
	private static VendaDAO vendaDAO = null;
	private static Venda venda;
	private static ItemVenda itemVenda, itemTroca;
	private static final ArrayList<ItemVenda> itensTroca = new ArrayList<>();
	private ArrayList<?> linhasItensVenda;
	private float valorHaver = 0;
	
	public ControlaListarVendas(JPanelListarVendas JPANEL_LISTARVENDAS, Connection connection) {
		this.JPANEL_LISTARVENDAS = JPANEL_LISTARVENDAS;
		try {
			produtoDAO = new ProdutoDAO(connection);
			itemVendaDAO = new ItemVendaDAO(connection);
			vendaDAO = new VendaDAO(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}


	// FUNCOES DE ACTIONS, MOUSE E KEY LISTERNERs ---------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnBuscarVendas())) {
			retornarVendas();
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnTrocarProduto())) {
			ativarTrocaItem();
			JPANEL_LISTARVENDAS.getBtnCancelar().setEnabled(true);
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnAdd())) {
			inserirItemTroca();
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnRemover())) {
			removerItemTroca();
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnFinalizar())) {
			finalizarTroca();
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnCancelar())) {
			limparDadosTabelas();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(JPANEL_LISTARVENDAS.getTableVendas())) {
			buscarItensVenda((int) JPANEL_LISTARVENDAS.getTableVendas().getValueAt(JPANEL_LISTARVENDAS.getTableVendas().getSelectedRow(), 0));
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getTableItensVenda())) {
			JPANEL_LISTARVENDAS.getBtnTrocarProduto().setEnabled(true);
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getTableItensTroca())) {
			JPANEL_LISTARVENDAS.getBtnRemover().setEnabled(true);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(JPANEL_LISTARVENDAS.getSpinnerDev())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeySpinnerDev();
			}
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getTextFieldCodigoProduto())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeyCodigoProduto();
			}
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getSpinnerTroca())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeySpinnerTroca();
			}
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnAdd())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				inserirItemTroca();
				JPANEL_LISTARVENDAS.getBtnFinalizar().setEnabled(true);
				JPANEL_LISTARVENDAS.getBtnFinalizar().requestFocus();
			} 
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnFinalizar())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				finalizarTroca();
				JPANEL_LISTARVENDAS.getDateChooser().requestFocus();
			} else if (e.getKeyCode() == KeyEvent.VK_F1) {
				JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().requestFocus();
			}
		} else if (e.getSource().equals(JPANEL_LISTARVENDAS.getBtnCancelar())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				limparDadosTabelas();
				JPANEL_LISTARVENDAS.getDateChooser().requestFocus();
			}
		}
	}
	
	// FUNCOES DE KEY LISTENERs ---------------------------------------------------------------------
	private void validaFocusKeySpinnerDev() {
		if (validaSpinnerDev()) {
			calcularHaver();
			JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().requestFocus();
		} else {
			JPANEL_LISTARVENDAS.getSpinnerDev().requestFocus();
		}
	}
	
	private void validaFocusKeyCodigoProduto() {
		if(validaCodigoProduto()) {
			retornaDadosProduto();
			JPANEL_LISTARVENDAS.getSpinnerTroca().requestFocus();
		} else {
			JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().requestFocus();
		}
	}

	private void validaFocusKeySpinnerTroca() {
		if (validaSpinnerTroca()) {
			JPANEL_LISTARVENDAS.getBtnAdd().requestFocus();
		} else {
			JPANEL_LISTARVENDAS.getSpinnerTroca().requestFocus();
		}
	}

	// FUNCOES LOGICAS ----------------------------------------------------------------------------------------
	private void retornarVendas() {
		Date date = JPANEL_LISTARVENDAS.getDateChooser().getDate();
		if (date != null) {
			buscarVendas(converterDataTeste(date));
		} else {
			JOptionPane.showMessageDialog(null, "INFORME UMA DATA VÁLIDA!");
		}
	}
	
	private String converterDataTeste(Date date) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dataString = formatter.format(date);
	    return dataString;
	}
	
	private void buscarVendas(String data) {
		try {
			ArrayList<?> linhas = vendaDAO.listarVendas(data);
			listarVendas(linhas);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void listarVendas(ArrayList<?> linhas) {
		String[] colunas = new String[] {"Nº VENDA", "DATA", "SUB-TOTAL", "- R$", "- %", "PARCELAS", "PAGAMENTO", "TOTAL"};
		Tabela tabelaModel = new Tabela(linhas, colunas);
		JPANEL_LISTARVENDAS.getTableVendas().setModel(tabelaModel);
		ajustarTabelaVendas();
	}
	
	private void buscarItensVenda(int codVenda) {
		try {
			if (linhasItensVenda == null) {
				linhasItensVenda = itemVendaDAO.listarItensVenda(codVenda);
				listarItensVenda(linhasItensVenda);
			} else {
				linhasItensVenda.clear();
				linhasItensVenda = itemVendaDAO.listarItensVenda(codVenda);
				listarItensVenda(linhasItensVenda);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void listarItensVenda(ArrayList<?> linhas) {
		String[] colunas = new String[] {"Nº VENDA", "COD PROD", "TIPO", "MODELO", "TAM", "R$ UNIT", "QTD", "TOTAL", "OBSERVAÇÃO"};
		Tabela tabelaModel = new Tabela(linhas, colunas);
		JPANEL_LISTARVENDAS.getTableItensVenda().setModel(tabelaModel);
		ajustarTabelaItensVendas();
	}
	
	private void ativarTrocaItem() {
		String codigoProduto = JPANEL_LISTARVENDAS.getTableItensVenda().getValueAt(JPANEL_LISTARVENDAS.getTableItensVenda().getSelectedRow(), 1).toString();
		int codigoVenda = (int) JPANEL_LISTARVENDAS.getTableItensVenda().getValueAt(JPANEL_LISTARVENDAS.getTableItensVenda().getSelectedRow(), 0);
		ativarCampos();
		salvarItemDevolvido(codigoProduto, codigoVenda);		
		JPANEL_LISTARVENDAS.getSpinnerDev().requestFocus();
	}
	
	private void salvarItemDevolvido(String codigoProduto, int codigoVenda) {
		Produto produto = produtoDAO.buscarProdutoVenda(codigoProduto);
		venda = vendaDAO.buscarCodigoVenda(codigoVenda);
		
		itemVenda = new ItemVenda();
		itemVenda.setVenda(venda);
		itemVenda.setProduto(produto);
		itemVenda.setValorUnitario((float) JPANEL_LISTARVENDAS.getTableItensVenda().getValueAt(JPANEL_LISTARVENDAS.getTableItensVenda().getSelectedRow(), 5));
		itemVenda.setQuantidadeComprada((int) JPANEL_LISTARVENDAS.getTableItensVenda().getValueAt(JPANEL_LISTARVENDAS.getTableItensVenda().getSelectedRow(), 6));
		itemVenda.setValorTotal((float) JPANEL_LISTARVENDAS.getTableItensVenda().getValueAt(JPANEL_LISTARVENDAS.getTableItensVenda().getSelectedRow(), 7));
		calcularHaver();
	}
	
	private void calcularHaver() {
		valorHaver = itemVenda.getValorUnitario() * Integer.parseInt(JPANEL_LISTARVENDAS.getSpinnerDev().getValue().toString());
		JPANEL_LISTARVENDAS.getTextFieldValorHaver().setText(String.valueOf(valorHaver));
	}

	private void retornaDadosProduto() {
		Produto produto = produtoDAO.buscarProdutoVenda(JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().getText());
		JPANEL_LISTARVENDAS.getTextFieldTipo().setText(produto.getTipo());
		JPANEL_LISTARVENDAS.getTextFieldModelo().setText(produto.getModelo());
		JPANEL_LISTARVENDAS.getTextFieldTamanho().setText(produto.getTamanho());
		JPANEL_LISTARVENDAS.getTextFieldQtdProd().setText(String.valueOf(produto.getQuantidade()));
		JPANEL_LISTARVENDAS.getTextFieldPrecoUnit().setText(String.valueOf(produto.getPrecoVenda()));
		JPANEL_LISTARVENDAS.getTextAreaObservacao().setText(produto.getObservacao());
	}
	
	private Produto buscarProduto() {
		if (!JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().getText().isBlank()) {
			Produto produto = produtoDAO.buscarProdutoVenda(JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().getText());
			
			if (produto != null) {
				if (produto.getQuantidade() == 0) {
					JOptionPane.showMessageDialog(null, "PRODUTO " + produto.getCodigoProduto() + " FORA DE ESTOQUE!");
					return null;
				} else {
					return produto;
				}
			} else {
				JOptionPane.showMessageDialog(null, "PRODUTO NÃO EXISTE!");
				return null;
			}
		} 
		else {
			return null;
		}
	}
	
	private void inserirItemTroca() {
		Produto produto = buscarProduto();
		if (produto != null && validaSpinnerTroca()) {
			adicionarItemTroca(produto);
			calculaItemTroca();
			listarItensTroca();
			limparDadosItem();
		}
	}
	
	private void adicionarItemTroca(Produto produto) {
		itemTroca = new ItemVenda();
		itemTroca.setProduto(produto);
		itemTroca.setVenda(venda);
		itemTroca.setQuantidadeComprada(Integer.parseInt(JPANEL_LISTARVENDAS.getSpinnerTroca().getValue().toString()));
		itemTroca.setValorTotal(itemTroca.getValorUnitario() * itemTroca.getQuantidadeComprada());
		itensTroca.add(itemTroca);
	}

	private void calculaItemTroca() {
		Float totalItemTroca = ((Integer.parseInt(JPANEL_LISTARVENDAS.getSpinnerTroca().getValue().toString())) * (Float.parseFloat(JPANEL_LISTARVENDAS.getTextFieldPrecoUnit().getText())));		
		valorHaver -= totalItemTroca;
		JPANEL_LISTARVENDAS.getTextFieldValorHaver().setText(String.valueOf(valorHaver));
	}
	
	private void listarItensTroca() {
		ArrayList<Object> linhasObject = new ArrayList<>();
		for (ItemVenda item : itensTroca) {
			linhasObject.add(new Object[] {
					item.getProduto().getCodigoProduto(), 
					item.getProduto().getTipo(), 
					item.getProduto().getModelo(),
					item.getProduto().getTamanho(),
					item.getValorUnitario(), 
					item.getQuantidadeComprada(), 
					item.getValorTotal()
			});
		}
		ArrayList<?> linhas = linhasObject;
		String[] colunas = new String[] {"COD PROD", "TIPO", "MODELO", "TAM", "R$ UNIT", "QTD", "R$ TOTAL"};
		Tabela tabelaModel = new Tabela(linhas, colunas);
		JPANEL_LISTARVENDAS.getTableItensTroca().setModel(tabelaModel);	
		ajustarTabelaItensTroca();
	}
	
	private void finalizarTroca() {
		Object[] options = { "CONFIRMAR", "Cancelar" };
		int teste = JOptionPane.showOptionDialog(null, "CONFIRMAR TROCA?", "Confirmar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (teste == 0) {
			try {
				int idVenda = itemVenda.getVenda().getIdVenda();
				String codigoProduto = itemVenda.getProduto().getCodigoProduto();
				int quantidade = Integer.parseInt(JPANEL_LISTARVENDAS.getSpinnerDev().getValue().toString());
				atualizarItensVenda(quantidade, idVenda, codigoProduto);
				salvarItensTroca(itensTroca);
				diminuirQuantia(itensTroca);
				atualizarTotalVenda(idVenda);
				limparDadosTabelas();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	private void atualizarItensVenda(int quantidade, int idVenda, String codigoProduto) {
		if (quantidade == itemVenda.getQuantidadeComprada()) {
			itemVendaDAO.removerItemVenda(idVenda, codigoProduto);
		} else if (quantidade < itemVenda.getQuantidadeComprada()) {
			itemVendaDAO.atualizarQtdItemVenda(idVenda, codigoProduto, quantidade);
			itemVendaDAO.atualizarTotalItemVenda(idVenda, codigoProduto);
		}
		produtoDAO.aumentarQtdProduto(codigoProduto, quantidade);
	}


	private static void salvarItensTroca(ArrayList<ItemVenda> itenstroca) {
		for (ItemVenda item : itenstroca) {
			itemVendaDAO.inserirItemVenda(item);
		}
	}
	
	private static void diminuirQuantia(ArrayList<ItemVenda> itenstroca) {
		for (ItemVenda item : itenstroca) {
			produtoDAO.diminuirQtdProduto(item.getProduto().getCodigoProduto(), item.getQuantidadeComprada());
		}
	}
	
	private static void atualizarTotalVenda(int idVenda) {
		Float novoValor = (float) 0;
		ArrayList<ItemVenda> itens = itemVendaDAO.buscarItensVenda(idVenda);
		for (ItemVenda item : itens) {
			novoValor += item.getValorTotal();
		}
		vendaDAO.atualizarValorVenda(novoValor, idVenda);
	}
	
	private void removerItemTroca() {
		atualizarTotalTroca();
		itensTroca.remove(JPANEL_LISTARVENDAS.getTableItensTroca().getSelectedRow());
		JPANEL_LISTARVENDAS.getBtnRemover().setEnabled(false);
		listarItensTroca();
	}

	private void atualizarTotalTroca() {
		Float valorItemTroca = (Float) JPANEL_LISTARVENDAS.getTableItensTroca().getValueAt(JPANEL_LISTARVENDAS.getTableItensTroca().getSelectedRow(), 6);
		valorHaver += valorItemTroca;
		JPANEL_LISTARVENDAS.getTextFieldValorHaver().setText(String.valueOf(valorHaver));
	}

	private void limparDadosTabelas() {
		limparDadosItem();
		limparDadosTroca();
		listarItensTroca();
		retornarVendas();
		linhasItensVenda.clear();
		listarItensVenda(linhasItensVenda);
		desabilitarBotoesFields();
	}

	private void desabilitarBotoesFields() {
		JPANEL_LISTARVENDAS.getBtnTrocarProduto().setEnabled(false);
		JPANEL_LISTARVENDAS.getSpinnerDev().setEnabled(false);
		JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().setEnabled(false);
		JPANEL_LISTARVENDAS.getSpinnerTroca().setEnabled(false);
		JPANEL_LISTARVENDAS.getBtnRemover().setEnabled(false);
		JPANEL_LISTARVENDAS.getBtnFinalizar().setEnabled(false);
		JPANEL_LISTARVENDAS.getBtnCancelar().setEnabled(false);
	}


	// FUNCOES DE VALIDACAO ------------------------------------------------------------------------------------------
	private boolean validaCodigoProduto() {
		if(!JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().getText().isBlank() && 
				JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().getText().length() >= 8) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validaSpinnerDev() {
		if (Integer.parseInt(JPANEL_LISTARVENDAS.getSpinnerDev().getValue().toString())  >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validaSpinnerTroca() {
		if (Integer.parseInt(JPANEL_LISTARVENDAS.getSpinnerTroca().getValue().toString())  >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// FUNCOES DE ESTILIZACAO ----------------------------------------------------------------------------------------
	private void limparDadosItem() {
		JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().setText("");
		JPANEL_LISTARVENDAS.getSpinnerTroca().setValue(1);
		JPANEL_LISTARVENDAS.getTextFieldTipo().setText("");
		JPANEL_LISTARVENDAS.getTextFieldModelo().setText("");
		JPANEL_LISTARVENDAS.getTextFieldTamanho().setText("");
		JPANEL_LISTARVENDAS.getTextFieldQtdProd().setText("");
		JPANEL_LISTARVENDAS.getTextFieldPrecoUnit().setText("");
		JPANEL_LISTARVENDAS.getTextAreaObservacao().setText("");
	}
	
	private void limparDadosTroca() {
		itensTroca.clear();
		valorHaver = 0;
		JPANEL_LISTARVENDAS.getTextFieldValorHaver().setText(String.valueOf(""));
	}

	public void ajustarTabelaVendas() {
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int numCol = 0; numCol < JPANEL_LISTARVENDAS.getTableVendas().getColumnCount(); numCol++) {
			JPANEL_LISTARVENDAS.getTableVendas().getColumnModel().getColumn(numCol).setCellRenderer(cellRender);
			JPANEL_LISTARVENDAS.getTableVendas().getColumnModel().getColumn(numCol).setMaxWidth(100);
		}
		JPANEL_LISTARVENDAS.getTableVendas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPANEL_LISTARVENDAS.getTableVendas().setRowHeight(25);
		JPANEL_LISTARVENDAS.getTableVendas().setGridColor(Color.pink);
	}
	
	public void ajustarTabelaItensVendas() {
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int numCol = 0; numCol < JPANEL_LISTARVENDAS.getTableItensVenda().getColumnCount(); numCol++) {
			JPANEL_LISTARVENDAS.getTableItensVenda().getColumnModel().getColumn(numCol).setCellRenderer(cellRender);
			JPANEL_LISTARVENDAS.getTableItensVenda().getColumnModel().getColumn(numCol).setMaxWidth(100);
		}
		JPANEL_LISTARVENDAS.getTableItensVenda().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPANEL_LISTARVENDAS.getTableItensVenda().setRowHeight(25);
		JPANEL_LISTARVENDAS.getTableItensVenda().setGridColor(Color.pink);
	}
	
	public void ajustarTabelaItensTroca() {
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int numCol = 0; numCol < JPANEL_LISTARVENDAS.getTableVendas().getColumnCount(); numCol++) {
			JPANEL_LISTARVENDAS.getTableVendas().getColumnModel().getColumn(numCol).setCellRenderer(cellRender);
			JPANEL_LISTARVENDAS.getTableVendas().getColumnModel().getColumn(numCol).setMaxWidth(100);
		}
		JPANEL_LISTARVENDAS.getTableVendas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPANEL_LISTARVENDAS.getTableVendas().setRowHeight(25);
		JPANEL_LISTARVENDAS.getTableVendas().setGridColor(Color.pink);
	}
	
	private void ativarCampos() {
		JPANEL_LISTARVENDAS.getSpinnerDev().setEnabled(true);
		JPANEL_LISTARVENDAS.getSpinnerDev().setValue(1);
		JPANEL_LISTARVENDAS.getTextFieldCodigoProduto().setEnabled(true);
		JPANEL_LISTARVENDAS.getSpinnerTroca().setEnabled(true);
		JPANEL_LISTARVENDAS.getSpinnerTroca().setValue(1);
		JPANEL_LISTARVENDAS.getBtnAdd().setEnabled(true);
	}
	
	
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}







	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
