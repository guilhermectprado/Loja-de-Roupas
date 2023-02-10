package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.dao.FornecedorDAO;
import model.dominio.Fornecedor;
import utils.Tabela;
import view.JPanelFornecedor;

public class ControlaFornecedor implements ActionListener, MouseListener, FocusListener {
	private final JPanelFornecedor JPANEL_FORNECEDOR;
	private static Fornecedor fornecedor;
	private static FornecedorDAO fornecedorDAO;
	private int idFornecedor;

	public ControlaFornecedor(JPanelFornecedor JPANEL_FORNECEDOR, Connection connection)  {
		this.JPANEL_FORNECEDOR = JPANEL_FORNECEDOR;
		try {
			fornecedorDAO = new FornecedorDAO(connection);
		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(JPANEL_FORNECEDOR.getBtnNovoFornecedor())) {
			inserirNovoFornecedor();
		} else if (e.getSource().equals(JPANEL_FORNECEDOR.getBtnInserirFornecedor())) {
			salvarFornecedor();
		} else if (e.getSource().equals(JPANEL_FORNECEDOR.getBtnSalvarFornecedor())) {
			atualizarFornecedor();
		} else if (e.getSource().equals(JPANEL_FORNECEDOR.getBtnExcluirFornecedor())) {
			excluirFornecedor();
		} else if (e.getSource().equals(JPANEL_FORNECEDOR.getBtnBuscarFornecedor())) {
			listarFornecedores();
		} else if (e.getSource().equals(JPANEL_FORNECEDOR.getBtnLimpar())) {
			limparCampos();
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(JPANEL_FORNECEDOR.getTextFieldCNPJ())) {
			adicionarMascaraCNPJ();
		}
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(JPANEL_FORNECEDOR.getTextFieldCNPJ())) {
			desativarMascaraCNPJ();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(JPANEL_FORNECEDOR.getTable())) {
			retornarDadosFornecedor();
		}
	}
	
	
	// FUNCOES LOGICAS ----------------------------------------------------------------------------------------------------------
	private void inserirNovoFornecedor() {
		ativarCampos();
		JPANEL_FORNECEDOR.getBtnNovoFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getBtnInserirFornecedor().setEnabled(true);
		JPANEL_FORNECEDOR.getBtnSalvarFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getBtnExcluirFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().requestFocus();
	}
	
	private void salvarFornecedor() {
		if(validarCamposPrincipais() && validarCNPJ() && validarCamposOpcionais()) {
			try {
				fornecedor = new Fornecedor();
				fornecedor.setNome(JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().getText());
				fornecedor.setCnpj(JPANEL_FORNECEDOR.getTextFieldCNPJ().getText());
				fornecedor.setTelefone(JPANEL_FORNECEDOR.getTextFieldTelefone().getText());
				fornecedor.setEndereco(JPANEL_FORNECEDOR.getTextFieldEndereco().getText());
				fornecedor.setCidade(JPANEL_FORNECEDOR.getTextFieldCidade().getText());
				fornecedor.setEstado(JPANEL_FORNECEDOR.getTextFieldEstado().getText());
				fornecedor.setObservacao(JPANEL_FORNECEDOR.getTextAreaObservacao().getText());		
				fornecedorDAO.inserirFornecedor(fornecedor);
				limparCampos();
				listarFornecedores();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
	}
	
	private void atualizarFornecedor() {
		if (validarCamposPrincipais() && validarCNPJ() && validarCamposOpcionais()) {
			try {
				fornecedor = new Fornecedor();
				fornecedor.setId(idFornecedor);
				fornecedor.setNome(JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().getText());
				fornecedor.setCnpj(JPANEL_FORNECEDOR.getTextFieldCNPJ().getText());
				fornecedor.setTelefone(JPANEL_FORNECEDOR.getTextFieldTelefone().getText());
				fornecedor.setEndereco(JPANEL_FORNECEDOR.getTextFieldEndereco().getText());
				fornecedor.setCidade(JPANEL_FORNECEDOR.getTextFieldCidade().getText());
				fornecedor.setEstado(JPANEL_FORNECEDOR.getTextFieldEstado().getText());
				fornecedor.setObservacao(JPANEL_FORNECEDOR.getTextAreaObservacao().getText());
				fornecedorDAO.atualizarFornecedor(fornecedor);
				limparCampos();
				listarFornecedores();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			};
		};
	}
	
	private void excluirFornecedor() {
		if (validarCamposPrincipais()) {
			try {
				fornecedorDAO.excluirFornecedor(idFornecedor);
				limparCampos();
				listarFornecedores();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			};
		};
	}
	

	private void listarFornecedores() {		
		if (!JPANEL_FORNECEDOR.getTextFieldBuscaFornecedor().getText().isEmpty()) {
			try {
				ArrayList<?> linhas = fornecedorDAO.buscarFornecedor(JPANEL_FORNECEDOR.getTextFieldBuscaFornecedor().getText());
				listarDadosTabela(linhas);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			try {
				ArrayList<?> linhas = fornecedorDAO.listarFornecedores();
				listarDadosTabela(linhas);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	private void listarDadosTabela(ArrayList<?> linhas) {
		String[] colunas = new String[] {"ID", "NOME", "TELEFONE", "CNPJ", "ENDEREÇO", "CIDADE", "ESTADO", "OBSERVAÇÃO"};
		Tabela tabelaModel = new Tabela(linhas, colunas);
		JPANEL_FORNECEDOR.getTable().setModel(tabelaModel);
		ajustarTabela();
	}
	
	private void retornarDadosFornecedor() {
		ativarCampos();
		JPANEL_FORNECEDOR.getBtnNovoFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getBtnInserirFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getBtnSalvarFornecedor().setEnabled(true);
		JPANEL_FORNECEDOR.getBtnExcluirFornecedor().setEnabled(true);
		
		idFornecedor = (int) JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 0);
		JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 1).toString());
		JPANEL_FORNECEDOR.getTextFieldTelefone().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 2).toString());
		JPANEL_FORNECEDOR.getTextFieldCNPJ().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 3).toString());
		JPANEL_FORNECEDOR.getTextFieldEndereco().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 4).toString());
		JPANEL_FORNECEDOR.getTextFieldCidade().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 5).toString());
		JPANEL_FORNECEDOR.getTextFieldEstado().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 6).toString());
		JPANEL_FORNECEDOR.getTextAreaObservacao().setText(JPANEL_FORNECEDOR.getTable().getValueAt(JPANEL_FORNECEDOR.getTable().getSelectedRow(), 7).toString());
	}

	// FUNCOES DE VALIDACAO DE CAMPOS --------------------------------------------------------------------------------------------------
	private boolean validarCamposPrincipais() {
		Border bordaErro = BorderFactory.createLineBorder(Color.blue);
		Border bordaPadrao = BorderFactory.createLineBorder(new Color(255, 102, 102), 1, true);
		
		if (validacaoNome(bordaErro, bordaPadrao) && validacaoTelefone(bordaErro, bordaPadrao)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validarCamposOpcionais() {
		Border bordaErro = BorderFactory.createLineBorder(Color.blue);
		Border bordaPadrao = BorderFactory.createLineBorder(new Color(255, 102, 102), 1, true);
		
		if (JPANEL_FORNECEDOR.getTextFieldEndereco().getText().isBlank() && JPANEL_FORNECEDOR.getTextFieldEstado().getText().isBlank() && JPANEL_FORNECEDOR.getTextFieldCidade().getText().isBlank()) {
			return true;
		} else {
			if (validacaoEndereco(bordaErro, bordaPadrao) && validacaoCidade(bordaErro, bordaPadrao) && validacaoEstado(bordaErro, bordaPadrao)) {
				return true;
			} else {
				return false;
			}
		}
	
	}
	
	private boolean validarCNPJ() {
		Border bordaErro = BorderFactory.createLineBorder(Color.blue);
		Border bordaPadrao = BorderFactory.createLineBorder(new Color(255, 102, 102), 1, true);
		
		if (!JPANEL_FORNECEDOR.getTextFieldCNPJ().getText().isBlank()) {
			if (validacaoCNPJ(bordaErro, bordaPadrao)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	private boolean validacaoNome(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().getText().isBlank() && JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().getText().length() >= 10) {
			JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().setBorder(bordaErro);
			JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().requestFocus();
			return false;
		}
	}
	
	private boolean validacaoCNPJ(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_FORNECEDOR.getTextFieldCNPJ().getText().isBlank() && !JPANEL_FORNECEDOR.getTextFieldCNPJ().getText().contains(" ") && JPANEL_FORNECEDOR.getTextFieldCNPJ().getText().length() == 18) {
			JPANEL_FORNECEDOR.getTextFieldCNPJ().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_FORNECEDOR.getTextFieldCNPJ().setBorder(bordaErro);
			JPANEL_FORNECEDOR.getTextFieldCNPJ().requestFocus();
			return false;
		}
	}
	
	private boolean validacaoTelefone(Border bordaErro, Border bordaPadrao) {

		if (!JPANEL_FORNECEDOR.getTextFieldTelefone().getText().toString().isBlank() && validaCharVazioTelefone() && JPANEL_FORNECEDOR.getTextFieldTelefone().getText().toString().length() == 15) {
			JPANEL_FORNECEDOR.getTextFieldTelefone().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_FORNECEDOR.getTextFieldTelefone().setBorder(bordaErro);
			JPANEL_FORNECEDOR.getTextFieldTelefone().requestFocus();
			return false;
		}
	}
	
	private boolean validaCharVazioTelefone() {
		Boolean CharAtAntes4 = false, CharAtDepois4 = false;

		if(JPANEL_FORNECEDOR.getTextFieldTelefone().getText().toString().charAt(4) == ' ') {
			for (int i = 0; i <= 3; i++) {
				if(JPANEL_FORNECEDOR.getTextFieldTelefone().getText().toString().charAt(i) != ' ') {
					CharAtAntes4 = true;
				} else {
					CharAtAntes4 = false;
				}
			}
				
			for (int i = 5; i <= 14; i++) {
				if(JPANEL_FORNECEDOR.getTextFieldTelefone().getText().toString().charAt(i) != ' ') {
					CharAtDepois4 = true;
				} else {
					CharAtDepois4 = false;
				}		
			}
		}
		
		if (CharAtAntes4 && CharAtDepois4) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validacaoEndereco(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_FORNECEDOR.getTextFieldEndereco().getText().isBlank()) {
			JPANEL_FORNECEDOR.getTextFieldEndereco().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_FORNECEDOR.getTextFieldEndereco().setBorder(bordaErro);
			JPANEL_FORNECEDOR.getTextFieldEndereco().requestFocus();
			return false;
		}
	}
	
	private boolean validacaoCidade(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_FORNECEDOR.getTextFieldCidade().getText().isBlank()) {
			JPANEL_FORNECEDOR.getTextFieldCidade().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_FORNECEDOR.getTextFieldCidade().setBorder(bordaErro);
			return false;
		}
	}
	
	private boolean validacaoEstado(Border bordaErro, Border bordaPadrao) {
		if (!JPANEL_FORNECEDOR.getTextFieldEstado().getText().isBlank() && JPANEL_FORNECEDOR.getTextFieldEstado().getText().length() == 2) {
			JPANEL_FORNECEDOR.getTextFieldEstado().setBorder(bordaPadrao);
			return true;
		} else {
			JPANEL_FORNECEDOR.getTextFieldEstado().setBorder(bordaErro);
			JPANEL_FORNECEDOR.getTextFieldEstado().requestFocus();
			return false;
		}
	}
	
	// FUNCOES DE ESTILIZACAO -----------------------------------------------------------------------------------
	public void ajustarTabela() {
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(0).setCellRenderer(cellRender);
		for (int numCol = 2; numCol < JPANEL_FORNECEDOR.getTable().getColumnCount(); numCol++) {
			JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(numCol).setCellRenderer(cellRender);
		}
		
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(0).setMaxWidth(20);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(1).setMaxWidth(250);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(2).setMaxWidth(120);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(3).setMaxWidth(140);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(4).setMaxWidth(250);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(5).setMaxWidth(120);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(6).setMaxWidth(60);
		JPANEL_FORNECEDOR.getTable().getColumnModel().getColumn(7).setMaxWidth(250);

		JPANEL_FORNECEDOR.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void limparCampos() {
		JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().setText("");
		desativarMascaraCNPJ();
		JPANEL_FORNECEDOR.getTextFieldCNPJ().setText("");
		JPANEL_FORNECEDOR.getTextFieldTelefone().setText("");
		JPANEL_FORNECEDOR.getTextFieldEndereco().setText("");
		JPANEL_FORNECEDOR.getTextFieldCidade().setText("");
		JPANEL_FORNECEDOR.getTextFieldEstado().setText("");
		JPANEL_FORNECEDOR.getTextAreaObservacao().setText("");
		JPANEL_FORNECEDOR.getTextFieldBuscaFornecedor().setText("");
		
		desativarCampos();
		
		JPANEL_FORNECEDOR.getBtnNovoFornecedor().setEnabled(true);
		JPANEL_FORNECEDOR.getBtnInserirFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getBtnSalvarFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getBtnExcluirFornecedor().setEnabled(false);
	}
	

	private void ativarCampos() {
		JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().setEnabled(true);
		JPANEL_FORNECEDOR.getTextFieldCNPJ().setEnabled(true);
		JPANEL_FORNECEDOR.getTextFieldTelefone().setEnabled(true);
		JPANEL_FORNECEDOR.getTextFieldEndereco().setEnabled(true);
		JPANEL_FORNECEDOR.getTextFieldCidade().setEnabled(true);
		JPANEL_FORNECEDOR.getTextFieldEstado().setEnabled(true);
		JPANEL_FORNECEDOR.getTextAreaObservacao().setEnabled(true);
	}
	
	private void desativarCampos() {
		JPANEL_FORNECEDOR.getTextFieldNomeFornecedor().setEnabled(false);
		JPANEL_FORNECEDOR.getTextFieldCNPJ().setEnabled(false);
		JPANEL_FORNECEDOR.getTextFieldTelefone().setEnabled(false);
		JPANEL_FORNECEDOR.getTextFieldEndereco().setEnabled(false);
		JPANEL_FORNECEDOR.getTextFieldCidade().setEnabled(false);
		JPANEL_FORNECEDOR.getTextFieldEstado().setEnabled(false);
		JPANEL_FORNECEDOR.getTextAreaObservacao().setEnabled(false);
	}
	
	private void adicionarMascaraCNPJ () {
		try {
			MaskFormatter maskCNPJ = new MaskFormatter("##.###.###/####-##");
			JPANEL_FORNECEDOR.getTextFieldCNPJ().setFormatterFactory(new DefaultFormatterFactory(maskCNPJ));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	
	}
	
	private void desativarMascaraCNPJ() {
		JPANEL_FORNECEDOR.getTextFieldCNPJ().setFormatterFactory(null);
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
