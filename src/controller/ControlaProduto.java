package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import view.JPanelProduto;
import java.sql.Connection;
import java.util.ArrayList;
import model.dao.FornecedorDAO;
import model.dao.ProdutoDAO;
import model.dominio.Fornecedor;
import model.dominio.Produto;
import utils.Tabela;

public class ControlaProduto implements ActionListener, MouseListener {
	private final JPanelProduto JPANEL_PRODUTOS;
	private static Produto produto;
	private static ProdutoDAO produtoDAO;
	private static FornecedorDAO fornecedorDAO;
	private boolean listaFornecedores = false;
	
	public ControlaProduto(JPanelProduto JPANEL_PRODUTOS, Connection connection) {
		this.JPANEL_PRODUTOS = JPANEL_PRODUTOS;
		try {
			produtoDAO = new ProdutoDAO(connection);
			fornecedorDAO = new FornecedorDAO(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(JPANEL_PRODUTOS.getBtnNovoProduto())) {
			inserirNovoProduto();
		} else if (e.getSource().equals(JPANEL_PRODUTOS.getBtnInserirProduto())) {
			salvarProduto();
		} else if (e.getSource().equals(JPANEL_PRODUTOS.getBtnSalvarProduto())) {
			atualizarProduto();
		} else if (e.getSource().equals(JPANEL_PRODUTOS.getBtnExcluirProduto())) {
			excluirProduto();
		} else if (e.getSource().equals(JPANEL_PRODUTOS.getBtnBuscarProduto())) {
			listarProdutos();
		} else if (e.getSource().equals(JPANEL_PRODUTOS.getBtnLimpar())) {
			limparCampos();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(JPANEL_PRODUTOS.getTable())) {
			retornarDadosProduto();
		}
	}
	
	
	// FUNCOES LOGICAS -------------------------------------------------------------------------------------------------
	private void inserirNovoProduto() {
		ativarCampos();
		listarFornecedoresComboBox();
		JPANEL_PRODUTOS.getBtnNovoProduto().setEnabled(false);
		JPANEL_PRODUTOS.getBtnInserirProduto().setEnabled(true);
		JPANEL_PRODUTOS.getBtnSalvarProduto().setEnabled(false);
		JPANEL_PRODUTOS.getBtnExcluirProduto().setEnabled(false);
		JPANEL_PRODUTOS.getTextFieldCodigo().requestFocus();
	}

	private void salvarProduto() {
		if(validarCampos()) {
			try {
				produto = new Produto();
				produto.setCodigoProduto(JPANEL_PRODUTOS.getTextFieldCodigo().getText());
				produto.setTipo(JPANEL_PRODUTOS.getTextFieldTipo().getText());
				produto.setModelo(JPANEL_PRODUTOS.getTextFieldModelo().getText());
				produto.setTamanho(JPANEL_PRODUTOS.getTextFieldTamanho().getText());
				produto.setQuantidade(Integer.parseInt(JPANEL_PRODUTOS.getSpinnerQuantidade().getValue().toString()));
				produto.setPrecoPago(Float.parseFloat(JPANEL_PRODUTOS.getTextFieldPrecoPago().getText()));
				produto.setPrecoVenda(Float.parseFloat(JPANEL_PRODUTOS.getTextFieldPrecoVenda().getText()));
				produto.setIdFornecedor(retornarIdFornecedor());
				produto.setObservacao(JPANEL_PRODUTOS.getTextAreaObservacao().getText());
				produtoDAO.inserirProduto(produto);
				limparCampos();
				listarProdutos();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	
	private void atualizarProduto() {
		if(validarCampos()) {
			try {
				produto = new Produto();
				produto.setCodigoProduto(JPANEL_PRODUTOS.getTextFieldCodigo().getText());
				produto.setTipo(JPANEL_PRODUTOS.getTextFieldTipo().getText());
				produto.setModelo(JPANEL_PRODUTOS.getTextFieldModelo().getText());
				produto.setTamanho(JPANEL_PRODUTOS.getTextFieldTamanho().getText());
				produto.setQuantidade(Integer.parseInt(JPANEL_PRODUTOS.getSpinnerQuantidade().getValue().toString()));
				produto.setPrecoPago(Float.parseFloat(JPANEL_PRODUTOS.getTextFieldPrecoPago().getText()));
				produto.setPrecoVenda(Float.parseFloat(JPANEL_PRODUTOS.getTextFieldPrecoVenda().getText()));
				produto.setIdFornecedor(retornarIdFornecedor());
				produto.setObservacao(JPANEL_PRODUTOS.getTextAreaObservacao().getText());
				produtoDAO.atualizarProduto(produto);
				limparCampos();
				listarProdutos();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	
	private void excluirProduto() {
		if (validarCampos()) {
			try {
				produtoDAO.excluirProduto(JPANEL_PRODUTOS.getTextFieldCodigo().getText());
				limparCampos();
				listarProdutos();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			};
		};
	}
	
	private void listarProdutos() {
		if (!JPANEL_PRODUTOS.getTextFieldBuscaProduto().getText().isEmpty()) {
			try {
				ArrayList<?> linhas = produtoDAO.buscarProduto(JPANEL_PRODUTOS.getTextFieldBuscaProduto().getText().toString());
				listarDadosTabela(linhas);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			try {
				ArrayList<?> linhas = produtoDAO.listarProdutos();
				listarDadosTabela(linhas);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void listarFornecedoresComboBox() {
		if(!listaFornecedores) {
			ArrayList<Fornecedor> fornecedores = fornecedorDAO.listarFornecedoresComboBoxProduto();
			for (Fornecedor fornecedor : fornecedores) {
				JPANEL_PRODUTOS.getComboBoxFornecedores().addItem(fornecedor.getNome());
			}
			listaFornecedores = true;
		}
	}
	
	private int retornarIdFornecedor() {
		String nomeFornecedor = JPANEL_PRODUTOS.getComboBoxFornecedores().getSelectedItem().toString();
		int idFornecedor = fornecedorDAO.buscarIdFornecedorProduto(nomeFornecedor);
		return idFornecedor;
	}

	private void listarDadosTabela(ArrayList<?> linhas) {
		String[] colunas = new String[] {"CÓDIGO", "TIPO", "MODELO", "TAMANHO", "QUANTIDADE", "PREÇO PAGO", "PREÇO VENDA","OBSERVAÇÃO", "FORNECEDOR"};
		Tabela tabelaModel = new Tabela(linhas, colunas);
		JPANEL_PRODUTOS.getTable().setModel(tabelaModel);
		ajustarTabela();
	}

	private void retornarDadosProduto() {
		ativarCampos();
		listarFornecedoresComboBox();
		JPANEL_PRODUTOS.getBtnNovoProduto().setEnabled(false);
		JPANEL_PRODUTOS.getBtnInserirProduto().setEnabled(false);
		JPANEL_PRODUTOS.getBtnSalvarProduto().setEnabled(true);
		JPANEL_PRODUTOS.getBtnExcluirProduto().setEnabled(true);
		
		JPANEL_PRODUTOS.getTextFieldCodigo().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 0).toString());
		JPANEL_PRODUTOS.getTextFieldTipo().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 1).toString());
		JPANEL_PRODUTOS.getTextFieldModelo().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 2).toString());
		JPANEL_PRODUTOS.getTextFieldTamanho().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 3).toString());
		JPANEL_PRODUTOS.getSpinnerQuantidade().setValue(Integer.parseInt(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 4).toString()));
		JPANEL_PRODUTOS.getTextFieldPrecoPago().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 5).toString());
		JPANEL_PRODUTOS.getTextFieldPrecoVenda().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 6).toString());
		JPANEL_PRODUTOS.getTextAreaObservacao().setText(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 7).toString());
		JPANEL_PRODUTOS.getComboBoxFornecedores().setSelectedItem(retornarNomeFornecedor());
	}
	
	private String retornarNomeFornecedor() {
		int idFornecedor = Integer.parseInt(JPANEL_PRODUTOS.getTable().getValueAt(JPANEL_PRODUTOS.getTable().getSelectedRow(), 8).toString());
		String nomeFornecedor = fornecedorDAO.buscarNomeFornecedorProduto(idFornecedor);
		return nomeFornecedor;
	}
	
	// FUNCOES DE VALIDACAO ---------------------------------------------------------------------------------
	private boolean validarCampos() {
		Border bordaErro = BorderFactory.createLineBorder(Color.blue);
		Border bordaPadrao = BorderFactory.createLineBorder(new Color(255, 102, 102), 1, true);
		
		if (validaCodigo(bordaErro, bordaPadrao) && validaTipo(bordaErro, bordaPadrao) && validaModelo(bordaErro, bordaPadrao) && validaTamanho(bordaErro, bordaPadrao) && validaQuantidade(bordaErro, bordaPadrao) 
				&& validaPrecoPago(bordaErro, bordaPadrao) && validaPrecoVenda(bordaErro, bordaPadrao) && validaFornecedor(bordaErro, bordaPadrao)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validaCodigo(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getTextFieldCodigo().getText().isBlank()) {
			JPANEL_PRODUTOS.getTextFieldCodigo().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getTextFieldCodigo().setBorder(bordaErro);
			JPANEL_PRODUTOS.getTextFieldCodigo().requestFocus();
			return false;
		}
	}

	private boolean validaTipo(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getTextFieldTipo().getText().isBlank()) {
			JPANEL_PRODUTOS.getTextFieldTipo().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getTextFieldTipo().setBorder(bordaErro);
			JPANEL_PRODUTOS.getTextFieldTipo().requestFocus();
			return false;
		}
	}

	private boolean validaModelo(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getTextFieldModelo().getText().isBlank()) {
			JPANEL_PRODUTOS.getTextFieldModelo().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getTextFieldModelo().setBorder(bordaErro);
			JPANEL_PRODUTOS.getTextFieldModelo().requestFocus();
			return false;
		}
	}

	private boolean validaTamanho(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getTextFieldTamanho().getText().isBlank() && JPANEL_PRODUTOS.getTextFieldTamanho().getText().length() > 0 && JPANEL_PRODUTOS.getTextFieldTamanho().getText().length() <= 3) {
			JPANEL_PRODUTOS.getTextFieldTamanho().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getTextFieldTamanho().setBorder(bordaErro);
			JPANEL_PRODUTOS.getTextFieldTamanho().requestFocus();
			return false;
		}
	}

	private boolean validaQuantidade(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getSpinnerQuantidade().getValue().equals(null) && !JPANEL_PRODUTOS.getSpinnerQuantidade().getValue().equals(0)) {
			JPANEL_PRODUTOS.getSpinnerQuantidade().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getSpinnerQuantidade().setBorder(bordaErro);
			JPANEL_PRODUTOS.getSpinnerQuantidade().requestFocus();
			return false;
		}
	}


	private boolean validaPrecoPago(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getTextFieldPrecoPago().getText().isBlank() && JPANEL_PRODUTOS.getTextFieldPrecoPago().getText().length() > 0) {
			JPANEL_PRODUTOS.getTextFieldPrecoPago().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getTextFieldPrecoPago().setBorder(bordaErro);
			JPANEL_PRODUTOS.getTextFieldPrecoPago().requestFocus();
			return false;
		}
	}


	private boolean validaPrecoVenda(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getTextFieldPrecoVenda().getText().isBlank() && JPANEL_PRODUTOS.getTextFieldPrecoVenda().getText().length() > 0) {
			JPANEL_PRODUTOS.getTextFieldPrecoVenda().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getTextFieldPrecoVenda().setBorder(bordaErro);
			JPANEL_PRODUTOS.getTextFieldPrecoVenda().requestFocus();
			return false;
		}
	}

	private boolean validaFornecedor(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_PRODUTOS.getComboBoxFornecedores().equals(null)) {
			JPANEL_PRODUTOS.getComboBoxFornecedores().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_PRODUTOS.getComboBoxFornecedores().setBorder(bordaErro);
			JPANEL_PRODUTOS.getComboBoxFornecedores().requestFocus();
			return false;
		}
	}


	// FUNCOES DE ESTILIZACAO ---------------------------------------------------------------------------------
	public void ajustarTabela() {
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPANEL_PRODUTOS.getTable().getColumnModel().getColumn(0).setCellRenderer(cellRender);
		for (int numCol = 2; numCol < JPANEL_PRODUTOS.getTable().getColumnCount(); numCol++) {
			JPANEL_PRODUTOS.getTable().getColumnModel().getColumn(numCol).setCellRenderer(cellRender);
		}
		
		JPANEL_PRODUTOS.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void limparCampos() {
		JPANEL_PRODUTOS.getTextFieldCodigo().setText("");
		JPANEL_PRODUTOS.getTextFieldTipo().setText("");
		JPANEL_PRODUTOS.getTextFieldModelo().setText("");
		JPANEL_PRODUTOS.getTextFieldTamanho().setText("");
		JPANEL_PRODUTOS.getSpinnerQuantidade().setValue(0);
		JPANEL_PRODUTOS.getTextFieldPrecoPago().setText("");
		JPANEL_PRODUTOS.getTextFieldPrecoVenda().setText("");
		JPANEL_PRODUTOS.getComboBoxFornecedores().setSelectedItem(0);
		JPANEL_PRODUTOS.getTextAreaObservacao().setText("");
		
		desativarCampos();
		
		JPANEL_PRODUTOS.getBtnNovoProduto().setEnabled(true);
		JPANEL_PRODUTOS.getBtnInserirProduto().setEnabled(false);
		JPANEL_PRODUTOS.getBtnSalvarProduto().setEnabled(false);
		JPANEL_PRODUTOS.getBtnExcluirProduto().setEnabled(false);
	}
	

	private void ativarCampos() {
		JPANEL_PRODUTOS.getTextFieldCodigo().setEnabled(true);
		JPANEL_PRODUTOS.getTextFieldTipo().setEnabled(true);
		JPANEL_PRODUTOS.getTextFieldModelo().setEnabled(true);
		JPANEL_PRODUTOS.getTextFieldTamanho().setEnabled(true);
		JPANEL_PRODUTOS.getSpinnerQuantidade().setEnabled(true);
		JPANEL_PRODUTOS.getTextFieldPrecoPago().setEnabled(true);
		JPANEL_PRODUTOS.getTextFieldPrecoVenda().setEnabled(true);
		JPANEL_PRODUTOS.getComboBoxFornecedores().setEnabled(true);
		JPANEL_PRODUTOS.getTextAreaObservacao().setEnabled(true);
	}
	
	private void desativarCampos() {
		JPANEL_PRODUTOS.getTextFieldCodigo().setEnabled(false);
		JPANEL_PRODUTOS.getTextFieldTipo().setEnabled(false);
		JPANEL_PRODUTOS.getTextFieldModelo().setEnabled(false);
		JPANEL_PRODUTOS.getTextFieldTamanho().setEnabled(false);
		JPANEL_PRODUTOS.getSpinnerQuantidade().setEnabled(false);
		JPANEL_PRODUTOS.getTextFieldPrecoPago().setEnabled(false);
		JPANEL_PRODUTOS.getTextFieldPrecoVenda().setEnabled(false);
		JPANEL_PRODUTOS.getComboBoxFornecedores().setEnabled(false);
		JPANEL_PRODUTOS.getTextAreaObservacao().setEnabled(false);
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

}
