package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import view.JPanelNovaVenda;

public class ControlaNovaVenda implements ActionListener, FocusListener, MouseListener, KeyListener {
	private final JPanelNovaVenda JPANEL_NOVAVENDA;
	private static Venda venda;
	private static ItemVenda itemVenda;
	private static ProdutoDAO produtoDAO = null;
	private static ItemVendaDAO itemVendaDAO = null;
	private static VendaDAO vendaDAO = null;
	private static final ArrayList<ItemVenda> itensVenda = new ArrayList<>();
	private float total = 0;
	
	public ControlaNovaVenda(JPanelNovaVenda JPANEL_NOVAVENDA, Connection connection) {
		this.JPANEL_NOVAVENDA = JPANEL_NOVAVENDA;
		try {
			produtoDAO = new ProdutoDAO(connection);
			itemVendaDAO = new ItemVendaDAO(connection);
			vendaDAO = new VendaDAO(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// FUNCOES DE ACTIONS LISTENER, FOCUS LISTENER, MOUSE LISTENER E KEY LISTENER -----------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnAdicionarItem())) {
			inserirItemVenda();
			JPANEL_NOVAVENDA.getTextFieldDesconto().requestFocus();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnFinalizarVenda())) {
			finalizarVenda();
			JPANEL_NOVAVENDA.getTextFieldCodigoProduto().requestFocus();
		}  else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnCancelar())) {
			limparDados();
			JPANEL_NOVAVENDA.getTextFieldCodigoProduto().requestFocus();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnRemoverItem())) {
			confirmarExclusãoItemVenda();
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(JPANEL_NOVAVENDA.getCheckboxPorcentagem())) {
			validaMouseCheckbox();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getTable())) {
			JPANEL_NOVAVENDA.getBtnRemoverItem().setEnabled(true);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(JPANEL_NOVAVENDA.getTextFieldCodigoProduto())) {
			validaFocusKeyCodigoProduto();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getSpinnerQuantidade())) {
			validaFocusKeySpinnerQuantidade();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getTextFieldDesconto())) {
			validaFocusKeyDesconto();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getSpinnerParcelas())) {
			validaFocusKeySpinnerParcelas();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getComboBoxFormaPagamento())) {
			validaFocusKeyFormaPagamento();
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getTextFieldValorRecebido())) {
			validaFocusKeyValorRecebido();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(JPANEL_NOVAVENDA.getTextFieldCodigoProduto())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeyCodigoProduto();
			}
		}
		else if (e.getSource().equals(JPANEL_NOVAVENDA.getSpinnerQuantidade())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeySpinnerQuantidade();
			}
		}
		else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnAdicionarItem())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				inserirItemVenda();
				JPANEL_NOVAVENDA.getTextFieldDesconto().requestFocus();
			}
		}
		else if (e.getSource().equals(JPANEL_NOVAVENDA.getTextFieldDesconto())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeyDesconto();
		} else if (e.getKeyCode() == KeyEvent.VK_F1) {
				JPANEL_NOVAVENDA.getTextFieldCodigoProduto().requestFocus();
			}
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getSpinnerParcelas())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeySpinnerParcelas();
			}
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getComboBoxFormaPagamento())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeyFormaPagamento();
			}
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getTextFieldValorRecebido())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				validaFocusKeyValorRecebido();
			}
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnFinalizarVenda())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				finalizarVenda();
				JPANEL_NOVAVENDA.getTextFieldCodigoProduto().requestFocus();
			}
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnCancelar())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				limparDados();
			}
		} else if (e.getSource().equals(JPANEL_NOVAVENDA.getBtnRemoverItem())) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				confirmarExclusãoItemVenda();
			}
		}
	}

	// FUNCOES DE FOCUS LISTENER E KEY LISTENER --------------------------------------------------------------------------------
	private void validaFocusKeyCodigoProduto() {
		if(validaCodigoProduto()) {
			retornaDadosProduto();
			JPANEL_NOVAVENDA.getSpinnerQuantidade().requestFocus();
		} else {
			JPANEL_NOVAVENDA.getTextFieldCodigoProduto().requestFocus();
		}
	}
	
	private void validaFocusKeySpinnerQuantidade() {
		if (validaSpinnerQuantidade()) {
			JPANEL_NOVAVENDA.getBtnAdicionarItem().requestFocus();
		} else {
			JPANEL_NOVAVENDA.getSpinnerQuantidade().requestFocus();
		}
	}
	
	private void validaFocusKeyDesconto() {
		if (validaDesconto()) {
			calcularDesconto();
			JPANEL_NOVAVENDA.getSpinnerParcelas().requestFocus();
		} else {
			JPANEL_NOVAVENDA.getTextFieldDesconto().setText("0");
			validaFocusKeyDesconto();
		}
	}
	
	private void validaMouseCheckbox() {
		validaFocusKeyDesconto();
	}
	
	private void validaFocusKeySpinnerParcelas() {
		if (validaParcelas()) {
			JPANEL_NOVAVENDA.getComboBoxFormaPagamento().requestFocus();
		} else {
			JPANEL_NOVAVENDA.getSpinnerParcelas().requestFocus();
		}
	}
	
	private void validaFocusKeyFormaPagamento() {
		if (JPANEL_NOVAVENDA.getComboBoxFormaPagamento().getSelectedItem().equals("Dinheiro") || JPANEL_NOVAVENDA.getComboBoxFormaPagamento().getSelectedItem().equals("Dinheiro/Cartão")) {
			JPANEL_NOVAVENDA.getTextFieldValorRecebido().setEnabled(true);
			JPANEL_NOVAVENDA.getTextFieldValorRecebido().requestFocus();
		} else {
			confirmarDadosVenda();
			JPANEL_NOVAVENDA.getBtnFinalizarVenda().requestFocus();
		}
	}
	
	private void validaFocusKeyValorRecebido() {
		if (validaValorRecebido()) {
			JPANEL_NOVAVENDA.getBtnFinalizarVenda().requestFocus();
			calculaTroco();
			confirmarDadosVenda();
		} else {
			JPANEL_NOVAVENDA.getTextFieldValorRecebido().requestFocus();
		}
	}


	
	// FUNCOES LOGICAS  -------------------------------------------------------------------------------------------------------
	public void iniciaVenda() {
		JPANEL_NOVAVENDA.getTextFieldCodigoProduto().requestFocus();

		int codigo = ((VendaDAO) vendaDAO).buscarCodigoVenda() + 1;
		JPANEL_NOVAVENDA.getSpinnerQuantidade().setValue(1);
		JPANEL_NOVAVENDA.getTextFieldDesconto().setText("0");;
		JPANEL_NOVAVENDA.getSpinnerParcelas().setValue(0);
		retornaData();		
		venda = new Venda();
		venda.setIdVenda(codigo);
		venda.setDataVenda(JPANEL_NOVAVENDA.getTextFieldData().getText());
	}
	
	private void retornaData() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
		JPANEL_NOVAVENDA.getTextFieldData().setText(formatarDate.format(data));
	}
	
	private Produto buscarProduto() {
		if (!JPANEL_NOVAVENDA.getTextFieldCodigoProduto().getText().isBlank()) {
			Produto produto = produtoDAO.buscarProdutoVenda(JPANEL_NOVAVENDA.getTextFieldCodigoProduto().getText());
			
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
	
	private void retornaDadosProduto() {
		Produto produto = buscarProduto();
		JPANEL_NOVAVENDA.getTextFieldTipo().setText(produto.getTipo());
		JPANEL_NOVAVENDA.getTextFieldModelo().setText(produto.getModelo());
		JPANEL_NOVAVENDA.getTextFieldTamanho().setText(produto.getTamanho());
		JPANEL_NOVAVENDA.getTextFieldQuantidade().setText(String.valueOf(produto.getQuantidade()));
		JPANEL_NOVAVENDA.getTextFieldPrecoUnitario().setText(String.valueOf(produto.getPrecoVenda()));
		JPANEL_NOVAVENDA.getTextAreaObservacao().setText(produto.getObservacao());
		JPANEL_NOVAVENDA.getBtnCancelar().setEnabled(true);
	}
	
	private void inserirItemVenda() {
		Produto produto = buscarProduto();
		if (produto != null && validaSpinnerQuantidade()) {
			adicionaItemVenda(produto);
		}
	}
	
	private void adicionaItemVenda(Produto produto) {
		itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);
		itemVenda.setVenda(venda);
		itemVenda.setQuantidadeComprada(Integer.parseInt(JPANEL_NOVAVENDA.getSpinnerQuantidade().getValue().toString()));
		itemVenda.setValorTotal(itemVenda.getValorUnitario() * itemVenda.getQuantidadeComprada());
		total = total + itemVenda.getValorTotal();
		JPANEL_NOVAVENDA.getTextFieldCodigoProduto().setText("");
		JPANEL_NOVAVENDA.getTextFieldSubTotal().setText(String.valueOf(total));
		JPANEL_NOVAVENDA.getTextFieldValorTotal().setText(String.valueOf(total));
		itensVenda.add(itemVenda);
		limparDadosProduto();
		listarItensVenda();
	}

	private void listarItensVenda() {		
		ArrayList<Object> linhasObject = new ArrayList<>();
		for (ItemVenda item : itensVenda) {
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
		JPANEL_NOVAVENDA.getTable().setModel(tabelaModel);	
		ajustarTabela();
	}
	
	private void confirmarExclusãoItemVenda() {
		String teste = JPANEL_NOVAVENDA.getTextFieldCodigoProduto().getText();
		int conf = JOptionPane.showConfirmDialog(null, "CONFIRMAR EXCLUSÃO DE " + teste + " ?" , "Confirmar",
				JOptionPane.YES_NO_OPTION);
		if (conf == JOptionPane.YES_OPTION) {
			removerItemVenda();
		}
	}
	
	private void removerItemVenda() {
		atualizarVenda();
		itensVenda.remove(JPANEL_NOVAVENDA.getTable().getSelectedRow());
		listarItensVenda();
		JPANEL_NOVAVENDA.getBtnRemoverItem().setEnabled(false);
	}

	private void atualizarVenda() {
		Float valorProduto = Float.parseFloat(JPANEL_NOVAVENDA.getTable().getValueAt(JPANEL_NOVAVENDA.getTable().getSelectedRow(), 6).toString());
		total -= valorProduto;
		JPANEL_NOVAVENDA.getTextFieldSubTotal().setText(String.valueOf(total));
		JPANEL_NOVAVENDA.getTextFieldValorTotal().setText(String.valueOf(total));
		if (validaDesconto()) {
			calcularDesconto();
		}
	}
	
	private void calcularDesconto() {
		if (JPANEL_NOVAVENDA.getCheckboxPorcentagem().isSelected()) {
			descontoPorcentagem();
		} else {
			descontoReais();
		}
	}
	
	private void descontoReais() {
		float desconto = Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldDesconto().getText().toString());
		float novoTotal = total - desconto;
		JPANEL_NOVAVENDA.getTextFieldValorTotal().setText(String.valueOf(novoTotal));
	}

	private void descontoPorcentagem() {
		int porcentagem = Integer.parseInt(JPANEL_NOVAVENDA.getTextFieldDesconto().getText().toString());
		float novoTotal = total - ((total * porcentagem) / 100);
		JPANEL_NOVAVENDA.getTextFieldValorTotal().setText(String.valueOf(novoTotal));
	}
	
	private void calculaTroco() {
		if (!JPANEL_NOVAVENDA.getTextFieldValorRecebido().getText().isBlank()) {
			float troco = Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldValorRecebido().getText().toString()) - Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldValorTotal().getText().toString());
			JPANEL_NOVAVENDA.getTextFieldTroco().setText(String.valueOf(troco));
		}
	}
	
	private void finalizarVenda() {
		Object[] options = { "CONFIRMAR", "Cancelar" };
		int teste = JOptionPane.showOptionDialog(null, "CONFIRMAR VENDA?", "Confirmar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (teste == 0) {
			try {
				venda.setValorVenda(Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldSubTotal().getText()));
				
				if (JPANEL_NOVAVENDA.getCheckboxPorcentagem().isSelected()) {
					venda.setDescontoPorcentagem(Integer.parseInt(JPANEL_NOVAVENDA.getTextFieldDesconto().getText()));
				} else {
					venda.setDescontoReais(Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldDesconto().getText()));
				}
				venda.setParcelas(Integer.parseInt(JPANEL_NOVAVENDA.getSpinnerParcelas().getValue().toString()));
				venda.setValorFinal(Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldValorTotal().getText()));
				venda.setFormaPagamento(JPANEL_NOVAVENDA.getComboBoxFormaPagamento().getSelectedItem().toString());
				vendaDAO.inserirVenda(venda);
				salvarItensVenda(itensVenda);
				atualizarQuantiaProduto(itensVenda);
				limparDados();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private boolean confirmarDadosVenda() {
		if (itensVenda.size() >= 1 && total > 0 && validaDesconto() && validaParcelas()) {
			if ((JPANEL_NOVAVENDA.getComboBoxFormaPagamento().getSelectedItem().equals("Dinheiro") || JPANEL_NOVAVENDA.getComboBoxFormaPagamento().getSelectedItem().equals("Dinheiro/Cartão")) && !validaTroco()) {
				JPANEL_NOVAVENDA.getTextFieldValorRecebido().requestFocus();
				JPANEL_NOVAVENDA.getBtnFinalizarVenda().setEnabled(false);
				return false;
			} else {
				JPANEL_NOVAVENDA.getBtnFinalizarVenda().setEnabled(true);
				return true;
			}
		} else {
			return false;
		}
	}


	private static void salvarItensVenda(ArrayList<ItemVenda> lista) {
		for (ItemVenda item : lista) {
			itemVendaDAO.inserirItemVenda(item);
		}		
	}
	
	private static void atualizarQuantiaProduto(ArrayList<ItemVenda> lista) {
		for (ItemVenda item : lista) {
			produtoDAO.diminuirQtdProduto(item.getProduto().getCodigoProduto(), item.getQuantidadeComprada());
		}
	}
	
	// FUNCOES DE VALIDACAO  ------------------------------------------------------------------------------------------------------
	private boolean validaCodigoProduto() {
		if(!JPANEL_NOVAVENDA.getTextFieldCodigoProduto().getText().isBlank() && 
			JPANEL_NOVAVENDA.getTextFieldCodigoProduto().getText().length() >= 8) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validaSpinnerQuantidade() {
		if (Integer.parseInt(JPANEL_NOVAVENDA.getSpinnerQuantidade().getValue().toString())  >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validaDesconto() {
		if (!JPANEL_NOVAVENDA.getTextFieldDesconto().getText().isBlank() && 
			JPANEL_NOVAVENDA.getTextFieldDesconto().getText().length() >= 1 && 
			Integer.parseInt(JPANEL_NOVAVENDA.getTextFieldDesconto().getText().toString()) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validaParcelas() {
		if (Integer.parseInt(JPANEL_NOVAVENDA.getSpinnerQuantidade().getValue().toString())  >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validaValorRecebido() {
		if (!JPANEL_NOVAVENDA.getTextFieldValorRecebido().getText().isBlank() && 
				JPANEL_NOVAVENDA.getTextFieldValorRecebido().getText().length() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validaTroco() {
		if (!JPANEL_NOVAVENDA.getTextFieldTroco().getText().isBlank() && Float.parseFloat(JPANEL_NOVAVENDA.getTextFieldTroco().getText()) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	// FUNCOES DE ESTILIZACAO -------------------------------------------------------------------------------------------
	private void limparDados() {
		limparCamposVenda();
		itensVenda.clear();
		limparDadosProduto();
		listarItensVenda();
		iniciaVenda();
	}
	
	private void limparCamposVenda() {
		JPANEL_NOVAVENDA.getTextFieldData().setText("");
		JPANEL_NOVAVENDA.getTextFieldCodigoProduto().setText("");
		JPANEL_NOVAVENDA.getSpinnerQuantidade().setValue(0);
		JPANEL_NOVAVENDA.getTextFieldSubTotal().setText("");
		JPANEL_NOVAVENDA.getTextFieldDesconto().setText("");
		JPANEL_NOVAVENDA.getCheckboxPorcentagem().setSelected(false);
		JPANEL_NOVAVENDA.getSpinnerParcelas().setValue(0);
		JPANEL_NOVAVENDA.getTextFieldValorTotal().setText("");
		JPANEL_NOVAVENDA.getComboBoxFormaPagamento().setSelectedIndex(0);
		JPANEL_NOVAVENDA.getTextFieldValorRecebido().setText("");
		JPANEL_NOVAVENDA.getTextFieldTroco().setText("");
	}

	
	private void limparDadosProduto() {
		JPANEL_NOVAVENDA.getTextFieldTipo().setText("");
		JPANEL_NOVAVENDA.getTextFieldModelo().setText("");
		JPANEL_NOVAVENDA.getTextFieldTamanho().setText("");
		JPANEL_NOVAVENDA.getTextFieldQuantidade().setText("");
		JPANEL_NOVAVENDA.getTextFieldPrecoUnitario().setText("");
		JPANEL_NOVAVENDA.getTextAreaObservacao().setText("");
	}

	private void ajustarTabela() {
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int numCol = 0; numCol < JPANEL_NOVAVENDA.getTable().getColumnCount(); numCol++) {
			JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(numCol).setCellRenderer(cellRender);
		}
		JPANEL_NOVAVENDA.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPANEL_NOVAVENDA.getTable().setRowHeight(25);
		JPANEL_NOVAVENDA.getTable().setGridColor(Color.pink);
		ajustarColunasTabela();
		
	}

	private void ajustarColunasTabela() {
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(0).setMaxWidth(80);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(3).setMaxWidth(60);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(4).setMaxWidth(60);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(5).setMaxWidth(60);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(6).setMaxWidth(60);
		
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(0).setMinWidth(80);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(3).setMinWidth(60);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(4).setMinWidth(60);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(5).setMinWidth(60);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(6).setMinWidth(60);
		
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(0).setResizable(false);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(3).setResizable(false);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(4).setResizable(false);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(5).setResizable(false);
		JPANEL_NOVAVENDA.getTable().getColumnModel().getColumn(6).setResizable(false);
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}

}