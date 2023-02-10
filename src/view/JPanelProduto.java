package view;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JSpinner;
import utils.UpperCaseDocument;
import controller.ControlaProduto;
import java.sql.Connection;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class JPanelProduto extends JPanel {
	private JPanel jpanel;
	private JLabel lblCodigo, lblTipo, lblModelo, lblTamanho, lblQuantidade, lblPrecoPago, lblPrecoVenda, lblFornecedor, lblObservacao;
	private JTextField textFieldCodigo, textFieldTipo, textFieldModelo, textFieldTamanho, textFieldBuscaProduto, textFieldPrecoPago, textFieldPrecoVenda;
	private JSpinner spinnerQuantidade;
	private JComboBox comboBoxFornecedores;
	private JTextArea textAreaObservacao;
	private JButton btnNovoProduto, btnInserirProduto, btnSalvarProduto, btnExcluirProduto, btnBuscarProduto, btnLimpar;
	private JScrollPane scrollPane;
	private JTable table;
	private ControlaProduto controlaProduto;

	public JPanelProduto(Connection connection) {
		controlaProduto = new ControlaProduto(this, connection);
		iniciarJPanel();
	}

	public void iniciarJPanel() {
		jpanel = new JPanel();
		jpanel.setBorder(null);
		jpanel.setBackground(SystemColor.menu);
		jpanel.setBounds(0, 0, 1124, 705);
		jpanel.setLayout(null);
//		jpanel.addFocusListener(controlaProduto);

		lblCodigo = new JLabel("Código*");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCodigo.setBounds(20, 20, 120, 30);
		jpanel.add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldCodigo.setBackground(Color.WHITE);
		textFieldCodigo.setEnabled(false);
		textFieldCodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(140, 20, 200, 30);
		textFieldCodigo.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldCodigo);
		
		lblTipo = new JLabel("Tipo*");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipo.setBounds(390, 20, 120, 30);
		jpanel.add(lblTipo);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTipo.setBackground(Color.WHITE);
		textFieldTipo.setEnabled(false);
		textFieldTipo.setToolTipText("Camiseta, Calça, Blusa, Vestido");
		textFieldTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTipo.setColumns(10);
		textFieldTipo.setBounds(510, 20, 200, 30);
		textFieldTipo.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldTipo);
		
		lblModelo = new JLabel("Modelo*");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModelo.setBounds(790, 20, 120, 30);
		jpanel.add(lblModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldModelo.setBackground(Color.WHITE);
		textFieldModelo.setEnabled(false);
		textFieldModelo.setToolTipText("Social, Estampado, Canelado\r\n");
		textFieldModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(910, 20, 200, 30);
		textFieldModelo.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldModelo);
		
		lblTamanho = new JLabel("Tamanho*");
		lblTamanho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTamanho.setBounds(20, 80, 120, 30);
		jpanel.add(lblTamanho);
		
		textFieldTamanho = new JTextField();
		textFieldTamanho.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTamanho.setBackground(Color.WHITE);
		textFieldTamanho.setEnabled(false);
		textFieldTamanho.setToolTipText("PP, P, M, G, GG, XG\r\n38, 40, 42, 44, 46, 48");
		textFieldTamanho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTamanho.setColumns(10);
		textFieldTamanho.setBounds(140, 80, 200, 30);
		textFieldTamanho.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldTamanho);
		
		lblQuantidade = new JLabel("Quantidade*");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantidade.setBounds(390, 80, 120, 30);
		jpanel.add(lblQuantidade);
		
		spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		spinnerQuantidade.setBackground(Color.WHITE);
		spinnerQuantidade.setEnabled(false);
		spinnerQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerQuantidade.setBounds(510, 80, 200, 30);
		jpanel.add(spinnerQuantidade);
		
		lblPrecoPago = new JLabel("Preço Pago*");
		lblPrecoPago.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecoPago.setBounds(790, 80, 120, 30);
		jpanel.add(lblPrecoPago);
		
//		DecimalFormat decimal = new DecimalFormat("###,00");
//        NumberFormatter numFormatter = new NumberFormatter(decimal);
//        numFormatter.setFormat(decimal);
//        numFormatter.setAllowsInvalid(false);
//        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        
		textFieldPrecoPago = new JTextField();
		textFieldPrecoPago.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldPrecoPago.setBackground(Color.WHITE);
//		textFieldPrecoPago.setFormatterFactory(dfFactory);
		textFieldPrecoPago.setEnabled(false);
		textFieldPrecoPago.setToolTipText("R$ 00.00");
		textFieldPrecoPago.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPrecoPago.setColumns(10);
		textFieldPrecoPago.setDocument(new UpperCaseDocument());
		textFieldPrecoPago.setBounds(910, 80, 200, 30);
		jpanel.add(textFieldPrecoPago);
		
		lblPrecoVenda = new JLabel("Preço Venda*");
		lblPrecoVenda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecoVenda.setBounds(20, 140, 120, 30);
		jpanel.add(lblPrecoVenda);
        
		textFieldPrecoVenda = new JTextField();
		textFieldPrecoVenda.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldPrecoVenda.setBackground(Color.WHITE);
//		textFieldPrecoVenda.setFormatterFactory(dfFactory);
		textFieldPrecoVenda.setEnabled(false);
		textFieldPrecoVenda.setToolTipText("R$ 00.00");
		textFieldPrecoVenda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPrecoVenda.setColumns(10);
		textFieldPrecoVenda.setBounds(140, 140, 200, 30);
		textFieldPrecoVenda.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldPrecoVenda);
		
		lblFornecedor = new JLabel("Fornecedor*");
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFornecedor.setBounds(390, 140, 120, 30);
		jpanel.add(lblFornecedor);
		
		comboBoxFornecedores = new JComboBox();
		comboBoxFornecedores.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		comboBoxFornecedores.setBackground(Color.WHITE);
		comboBoxFornecedores.setEnabled(false);
		comboBoxFornecedores.setBounds(510, 140, 200, 30);
		jpanel.add(comboBoxFornecedores);
		
		lblObservacao = new JLabel("Observação");
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblObservacao.setBounds(790, 140, 120, 30);
		jpanel.add(lblObservacao);
		
		textAreaObservacao = new JTextArea();
		textAreaObservacao.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textAreaObservacao.setBackground(Color.WHITE);
		textAreaObservacao.setEnabled(false);
		textAreaObservacao.setLineWrap(true);
		textAreaObservacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAreaObservacao.setBounds(910, 140, 200, 60);
		textAreaObservacao.setDocument(new UpperCaseDocument());
		jpanel.add(textAreaObservacao);
		
		btnNovoProduto = new JButton("Novo");
		btnNovoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNovoProduto.setBounds(20, 250, 100, 30);
		btnNovoProduto.addActionListener(controlaProduto);
		jpanel.add(btnNovoProduto);
		
		btnInserirProduto = new JButton("Inserir");
		btnInserirProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserirProduto.setEnabled(false);
		btnInserirProduto.setBounds(130, 250, 100, 30);
		btnInserirProduto.addActionListener(controlaProduto);
		jpanel.add(btnInserirProduto);
		
		btnSalvarProduto = new JButton("Salvar");
		btnSalvarProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalvarProduto.setEnabled(false);
		btnSalvarProduto.setBounds(240, 250, 100, 30);
		btnSalvarProduto.addActionListener(controlaProduto);
		jpanel.add(btnSalvarProduto);
		
		btnExcluirProduto = new JButton("Excluir");
		btnExcluirProduto.setEnabled(false);
		btnExcluirProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluirProduto.setBounds(350, 250, 100, 30);
		btnExcluirProduto.addActionListener(controlaProduto);
		jpanel.add(btnExcluirProduto);
		
		btnBuscarProduto = new JButton("Buscar");
		btnBuscarProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscarProduto.setBounds(780, 250, 100, 30);
		btnBuscarProduto.addActionListener(controlaProduto);
		jpanel.add(btnBuscarProduto);
		
		textFieldBuscaProduto = new JTextField();
		textFieldBuscaProduto.setBackground(Color.WHITE);
		textFieldBuscaProduto.setBorder(new LineBorder(new Color(255, 102, 102)));
		textFieldBuscaProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBuscaProduto.setBounds(525, 250, 245, 30);
		textFieldBuscaProduto.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldBuscaProduto);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpar.setBounds(1005, 250, 100, 30);
		btnLimpar.addActionListener(controlaProduto);
		jpanel.add(btnLimpar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 300, 1085, 390);
		jpanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(controlaProduto);
		scrollPane.setViewportView(table);
	}
	
	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}

	public JTextField getTextFieldCodigo() {
		return textFieldCodigo;
	}

	public void setTextFieldCodigo(JTextField textFieldCodigo) {
		this.textFieldCodigo = textFieldCodigo;
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

	public JSpinner getSpinnerQuantidade() {
		return spinnerQuantidade;
	}

	public void setSpinnerQuantidade(JSpinner spinnerQuantidade) {
		this.spinnerQuantidade = spinnerQuantidade;
	}

	public JTextField getTextFieldPrecoPago() {
		return textFieldPrecoPago;
	}

	public void setTextFieldPrecoPago(JTextField textFieldPrecoPago) {
		this.textFieldPrecoPago = textFieldPrecoPago;
	}

	public JTextField getTextFieldPrecoVenda() {
		return textFieldPrecoVenda;
	}

	public void setTextFieldPrecoVenda(JTextField textFieldPrecoVenda) {
		this.textFieldPrecoVenda = textFieldPrecoVenda;
	}

	public JComboBox getComboBoxFornecedores() {
		return comboBoxFornecedores;
	}

	public void setComboBoxFornecedores(JComboBox comboBoxFornecedores) {
		this.comboBoxFornecedores = comboBoxFornecedores;
	}

	public JTextArea getTextAreaObservacao() {
		return textAreaObservacao;
	}

	public void setTextAreaObservacao(JTextArea textAreaObservacao) {
		this.textAreaObservacao = textAreaObservacao;
	}

	public JButton getBtnNovoProduto() {
		return btnNovoProduto;
	}

	public void setBtnNovoProduto(JButton btnNovoProduto) {
		this.btnNovoProduto = btnNovoProduto;
	}

	public JButton getBtnInserirProduto() {
		return btnInserirProduto;
	}

	public void setBtnInserirProduto(JButton btnInserirProduto) {
		this.btnInserirProduto = btnInserirProduto;
	}

	public JButton getBtnSalvarProduto() {
		return btnSalvarProduto;
	}

	public void setBtnSalvarProduto(JButton btnSalvarProduto) {
		this.btnSalvarProduto = btnSalvarProduto;
	}

	public JButton getBtnExcluirProduto() {
		return btnExcluirProduto;
	}

	public void setBtnExcluirProduto(JButton btnExcluirProduto) {
		this.btnExcluirProduto = btnExcluirProduto;
	}

	public JButton getBtnBuscarProduto() {
		return btnBuscarProduto;
	}

	public void setBtnBuscarProduto(JButton btnBuscarProduto) {
		this.btnBuscarProduto = btnBuscarProduto;
	}

	public JTextField getTextFieldBuscaProduto() {
		return textFieldBuscaProduto;
	}

	public void setTextFieldBuscaProduto(JTextField textFieldBuscaProduto) {
		this.textFieldBuscaProduto = textFieldBuscaProduto;
	}

	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	public void setBtnLimpar(JButton btnLimpar) {
		this.btnLimpar = btnLimpar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
