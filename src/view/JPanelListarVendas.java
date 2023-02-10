package view;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import controller.ControlaListarVendas;
import utils.UpperCaseDocument;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

public class JPanelListarVendas extends JPanel {
	private ControlaListarVendas controlaListarVendas;
	private JPanel jpanel;
	private JLabel lblData, lblVendasDoDia, lblItensDaVenda, lblQtdDev, lblQtdProd, lblCodProduto, lblQtdTroca, lblTipo, lblModelo, lblTamanho, lblPrecoUnitario, lblObservacao, lblValorDevolucao;
	private JTextField textFieldCodigoProduto, textFieldTipo, textFieldModelo, textFieldTamanho, textFieldPrecoUnit, textFieldQtdProd, textFieldValorHaver;
	private JSpinner spinnerDev, spinnerTroca;
	private JTextArea textAreaObservacao;
	private JScrollPane scrollPaneVendas, scrollPaneItensVendas, scrollPaneItensTroca;
	private JTable tableVendas, tableItensVenda, tableItensTroca;
	private JButton btnBuscarVendas, btnTrocarProduto, btnAdd, btnRemover, btnFinalizar, btnCancelar;
	private JDateChooser dateChooser;
	
	public JPanelListarVendas(Connection connection) {
		controlaListarVendas = new ControlaListarVendas(this, connection);
		iniciarJPanel();
	}
	
	public JPanelListarVendas() {
		setBackground(new Color(255, 255, 255));
		iniciarJPanel();
	}

	private void iniciarJPanel() {
		jpanel = new JPanel();
		jpanel.setBorder(null);
		jpanel.setBackground(Color.WHITE);
		jpanel.setBounds(0, 0, 1130, 640);
		jpanel.setLayout(null);
		
		lblData = new JLabel("Informe uma data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblData.setBounds(20, 10, 170, 30);
		jpanel.add(lblData);
		
		dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		dateChooser.setBounds(200, 10, 150, 30);
		jpanel.add(dateChooser);
		
		btnBuscarVendas = new JButton("Buscar");
		btnBuscarVendas.setIconTextGap(15);
		btnBuscarVendas.setIcon(new ImageIcon(JPanelListarVendas.class.getResource("/view/icons/shopping-search-outline-custom.png")));
		btnBuscarVendas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscarVendas.setBounds(370, 10, 120, 30);
		btnBuscarVendas.addActionListener(controlaListarVendas);
		jpanel.add(btnBuscarVendas);
		
		lblVendasDoDia = new JLabel("Vendas do dia");
		lblVendasDoDia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVendasDoDia.setBounds(20, 70, 120, 30);
		jpanel.add(lblVendasDoDia);
		
		scrollPaneVendas = new JScrollPane();
		scrollPaneVendas.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		scrollPaneVendas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneVendas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneVendas.setBounds(20, 100, 700, 210);
		jpanel.add(scrollPaneVendas);
		
		tableVendas = new JTable();
		tableVendas.addMouseListener(controlaListarVendas);
		scrollPaneVendas.setViewportView(tableVendas);
		
		lblItensDaVenda = new JLabel("Itens da Venda");
		lblItensDaVenda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItensDaVenda.setBounds(20, 330, 120, 30);
		jpanel.add(lblItensDaVenda);
		
		scrollPaneItensVendas = new JScrollPane();
		scrollPaneItensVendas.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		scrollPaneItensVendas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneItensVendas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneItensVendas.setBounds(20, 360, 700, 210);
		jpanel.add(scrollPaneItensVendas);
		
		tableItensVenda = new JTable();
		tableItensVenda.addMouseListener(controlaListarVendas);
		scrollPaneItensVendas.setViewportView(tableItensVenda);
		
		btnTrocarProduto = new JButton("Trocar Produto");
		btnTrocarProduto.setEnabled(false);
		btnTrocarProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTrocarProduto.setBounds(20, 590, 150, 40);
		btnTrocarProduto.addActionListener(controlaListarVendas);
		btnTrocarProduto.addKeyListener(controlaListarVendas);
		jpanel.add(btnTrocarProduto);
		
		lblQtdDev = new JLabel("QTD");
		lblQtdDev.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdDev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQtdDev.setBounds(220, 590, 60, 40);
		jpanel.add(lblQtdDev);
		
		spinnerDev = new JSpinner();
		spinnerDev.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerDev.setBackground(Color.WHITE);
		spinnerDev.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		spinnerDev.setEnabled(false);
		spinnerDev.addKeyListener(controlaListarVendas);
		spinnerDev.setBounds(290, 590, 60, 40);
		jpanel.add(spinnerDev);
		
		lblCodProduto = new JLabel("COD Produto");
		lblCodProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCodProduto.setBounds(750, 10, 120, 30);
		jpanel.add(lblCodProduto);
		
		textFieldCodigoProduto = new JTextField();
		textFieldCodigoProduto.setBackground(Color.WHITE);
		textFieldCodigoProduto.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldCodigoProduto.setDisabledTextColor(Color.black);
		textFieldCodigoProduto.setEnabled(false);
		textFieldCodigoProduto.addKeyListener(controlaListarVendas);
		textFieldCodigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCodigoProduto.setColumns(10);
		textFieldCodigoProduto.setBounds(870, 10, 120, 30);
		jpanel.add(textFieldCodigoProduto);
		
		lblQtdTroca = new JLabel("QTD");
		lblQtdTroca.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdTroca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQtdTroca.setBounds(1000, 10, 60, 30);
		jpanel.add(lblQtdTroca);
		
		spinnerTroca = new JSpinner();
		spinnerTroca.setBackground(Color.WHITE);
		spinnerTroca.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		spinnerTroca.setEnabled(false);
		spinnerTroca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerTroca.setBounds(1060, 10, 60, 30);
		spinnerTroca.addKeyListener(controlaListarVendas);
		jpanel.add(spinnerTroca);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipo.setBounds(750, 60, 180, 30);
		jpanel.add(lblTipo);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setBackground(Color.WHITE);
		textFieldTipo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTipo.setDisabledTextColor(Color.black);
		textFieldTipo.setEnabled(false);
		textFieldTipo.setForeground(Color.BLACK);
		textFieldTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTipo.setColumns(10);
		textFieldTipo.setBounds(750, 90, 180, 30);
		jpanel.add(textFieldTipo);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModelo.setBounds(940, 60, 180, 30);
		jpanel.add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setBackground(Color.WHITE);
		textFieldModelo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldModelo.setDisabledTextColor(Color.black);
		textFieldModelo.setEnabled(false);
		textFieldModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(940, 90, 180, 30);
		jpanel.add(textFieldModelo);
		
		lblTamanho = new JLabel("TAM");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTamanho.setBounds(750, 130, 60, 30);
		jpanel.add(lblTamanho);
		
		textFieldTamanho = new JTextField();
		textFieldTamanho.setBackground(Color.WHITE);
		textFieldTamanho.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTamanho.setDisabledTextColor(Color.black);
		textFieldTamanho.setEnabled(false);
		textFieldTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTamanho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTamanho.setColumns(10);
		textFieldTamanho.setBounds(750, 160, 60, 30);
		jpanel.add(textFieldTamanho);
		
		lblPrecoUnitario = new JLabel("R$ UNIT");
		lblPrecoUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecoUnitario.setBounds(890, 130, 100, 30);
		jpanel.add(lblPrecoUnitario);
		
		textFieldPrecoUnit = new JTextField();
		textFieldPrecoUnit.setBackground(Color.WHITE);
		textFieldPrecoUnit.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldPrecoUnit.setDisabledTextColor(Color.black);
		textFieldPrecoUnit.setEnabled(false);
		textFieldPrecoUnit.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrecoUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPrecoUnit.setColumns(10);
		textFieldPrecoUnit.setBounds(890, 160, 100, 30);
		jpanel.add(textFieldPrecoUnit);
		
		lblQtdProd = new JLabel("QTD");
		lblQtdProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdProd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQtdProd.setBounds(1060, 130, 60, 30);
		jpanel.add(lblQtdProd);
		
		textFieldQtdProd = new JTextField();
		textFieldQtdProd.setBackground(Color.WHITE);
		textFieldQtdProd.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldQtdProd.setDisabledTextColor(Color.black);
		textFieldQtdProd.setEnabled(false);
		textFieldQtdProd.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQtdProd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldQtdProd.setColumns(10);
		textFieldQtdProd.setBounds(1060, 160, 60, 30);
		jpanel.add(textFieldQtdProd);
		
		lblObservacao = new JLabel("Observação");
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblObservacao.setBounds(750, 200, 150, 30);
		jpanel.add(lblObservacao);
		
		textAreaObservacao = new JTextArea();
		textAreaObservacao.setBackground(Color.WHITE);
		textAreaObservacao.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textAreaObservacao.setDisabledTextColor(Color.black);
		textAreaObservacao.setEnabled(false);
		textAreaObservacao.setLineWrap(true);
		textAreaObservacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAreaObservacao.setBounds(750, 230, 370, 80);
		textAreaObservacao.setDocument(new UpperCaseDocument());
		jpanel.add(textAreaObservacao);
		
		btnAdd = new JButton("+ ADD");
		btnAdd.setEnabled(false);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.addActionListener(controlaListarVendas);
		btnAdd.addKeyListener(controlaListarVendas);
		btnAdd.setBounds(1020, 320, 100, 30);
		jpanel.add(btnAdd);
		
		scrollPaneItensTroca = new JScrollPane();
		scrollPaneItensTroca.setBorder(new LineBorder(new Color(255, 102, 102)));
		scrollPaneItensTroca.setBounds(750, 360, 370, 110);
		jpanel.add(scrollPaneItensTroca);
		
		tableItensTroca = new JTable();
		tableItensTroca.addMouseListener(controlaListarVendas);
		scrollPaneItensTroca.setViewportView(tableItensTroca);
		
		lblValorDevolucao = new JLabel("De Haver    - R$");
		lblValorDevolucao.setForeground(Color.RED);
		lblValorDevolucao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorDevolucao.setBounds(750, 520, 220, 50);
		jpanel.add(lblValorDevolucao);
		
		textFieldValorHaver = new JTextField();
		textFieldValorHaver.setBackground(Color.WHITE);
		textFieldValorHaver.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldValorHaver.setDisabledTextColor(Color.black);
		textFieldValorHaver.setEnabled(false);
		textFieldValorHaver.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldValorHaver.setForeground(Color.RED);
		textFieldValorHaver.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textFieldValorHaver.setColumns(10);
		textFieldValorHaver.setBounds(970, 520, 150, 50);
		jpanel.add(textFieldValorHaver);
		
		btnFinalizar = new JButton("Finalizar Troca");
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFinalizar.setEnabled(false);
		btnFinalizar.addActionListener(controlaListarVendas);
		btnFinalizar.addKeyListener(controlaListarVendas);
		btnFinalizar.setBounds(750, 590, 150, 40);
		jpanel.add(btnFinalizar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(controlaListarVendas);
		btnCancelar.addKeyListener(controlaListarVendas);
		btnCancelar.setBounds(970, 590, 150, 40);
		jpanel.add(btnCancelar);
		
		btnRemover = new JButton("Remover Item");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemover.setEnabled(false);
		btnRemover.addKeyListener(controlaListarVendas);
		btnRemover.addActionListener(controlaListarVendas);
		btnRemover.setBounds(750, 469, 150, 30);
		jpanel.add(btnRemover);

	}

	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}



	public JTextField getTextFieldCodigoProduto() {
		return textFieldCodigoProduto;
	}

	public void setTextFieldCodigoProduto(JTextField textFieldCodigoProduto) {
		this.textFieldCodigoProduto = textFieldCodigoProduto;
	}

	public JSpinner getSpinnerDev() {
		return spinnerDev;
	}

	public void setSpinnerDev(JSpinner spinnerDev) {
		this.spinnerDev = spinnerDev;
	}

	public JSpinner getSpinnerTroca() {
		return spinnerTroca;
	}

	public void setSpinnerTroca(JSpinner spinnerTroca) {
		this.spinnerTroca = spinnerTroca;
	}

	public JTextField getTextFieldTipo() {
		return textFieldTipo;
	}

	public void setTextFieldTipo(JTextField textFieldTipo) {
		this.textFieldTipo = textFieldTipo;
	}

	public JTextField getTextFieldModelo() {
		return textFieldModelo;
	}

	public void setTextFieldModelo(JTextField textFieldModelo) {
		this.textFieldModelo = textFieldModelo;
	}

	public JTextField getTextFieldTamanho() {
		return textFieldTamanho;
	}

	public void setTextFieldTamanho(JTextField textFieldTamanho) {
		this.textFieldTamanho = textFieldTamanho;
	}

	public JTextField getTextFieldPrecoUnit() {
		return textFieldPrecoUnit;
	}

	public void setTextFieldPrecoUnit(JTextField textFieldPrecoUnit) {
		this.textFieldPrecoUnit = textFieldPrecoUnit;
	}

	public JTextField getTextFieldQtdProd() {
		return textFieldQtdProd;
	}

	public void setTextFieldQtdProd(JTextField textFieldQtdProd) {
		this.textFieldQtdProd = textFieldQtdProd;
	}

	public JTextField getTextFieldValorHaver() {
		return textFieldValorHaver;
	}

	public void textFieldValorHaver(JTextField textFieldValorHaver) {
		this.textFieldValorHaver = textFieldValorHaver;
	}

	public JTextArea getTextAreaObservacao() {
		return textAreaObservacao;
	}

	public void setTextAreaObservacao(JTextArea textAreaObservacao) {
		this.textAreaObservacao = textAreaObservacao;
	}

	public JTable getTableVendas() {
		return tableVendas;
	}

	public void setTableVendas(JTable tableVendas) {
		this.tableVendas = tableVendas;
	}

	public JTable getTableItensVenda() {
		return tableItensVenda;
	}

	public void setTableItensVenda(JTable tableItensVenda) {
		this.tableItensVenda = tableItensVenda;
	}

	public JTable getTableItensTroca() {
		return tableItensTroca;
	}

	public void setTableItensTroca(JTable tableItensTroca) {
		this.tableItensTroca = tableItensTroca;
	}

	public JButton getBtnBuscarVendas() {
		return btnBuscarVendas;
	}

	public void setBtnBuscarVendas(JButton btnBuscarVendas) {
		this.btnBuscarVendas = btnBuscarVendas;
	}

	public JButton getBtnTrocarProduto() {
		return btnTrocarProduto;
	}

	public void setBtnTrocarProduto(JButton btnTrocarProduto) {
		this.btnTrocarProduto = btnTrocarProduto;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}

	public void setBtnRemover(JButton btnRemover) {
		this.btnRemover = btnRemover;
	}

	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}

	public void setBtnFinalizar(JButton btnFinalizar) {
		this.btnFinalizar = btnFinalizar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}
	
	

}
