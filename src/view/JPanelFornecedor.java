package view;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controller.ControlaFornecedor;
import utils.UpperCaseDocument;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class JPanelFornecedor extends JPanel {
	private JPanel jpanel;
	private JLabel lblNome, lblCNPJ, lblTelefone, lblEndereco, lblCidade, lblEstado, lblObservacao;
	private JTextField textFieldNomeFornecedor, textFieldEndereco, textFieldCidade, textFieldBuscaFornecedor;
	private JFormattedTextField textFieldCNPJ, textFieldTelefone, textFieldEstado;
	private JTextArea textAreaObservacao;
	private JButton btnNovoFornecedor, btnInserirFornecedor, btnSalvarFornecedor, btnExcluirFornecedor, btnBuscarFornecedor, btnLimpar;
	private JTable table;
	private JScrollPane scrollPane;
	private MaskFormatter maskEstado, maskTelefone;
	private ControlaFornecedor controlaFornecedor;
	
	public JPanelFornecedor(Connection connection) {
		controlaFornecedor = new ControlaFornecedor(this, connection);
		iniciarJPanel();
	}

	public void iniciarJPanel() {
		jpanel = new JPanel();
		jpanel.setBorder(null);
		jpanel.setBackground(SystemColor.menu);
		jpanel.setBounds(0, 0, 1124, 705);
		jpanel.setLayout(null);
		
		lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNome.setBounds(20, 20, 120, 30);
		jpanel.add(lblNome);
		
		textFieldNomeFornecedor = new JTextField();
		textFieldNomeFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNomeFornecedor.setEnabled(false);
		textFieldNomeFornecedor.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldNomeFornecedor.setBackground(Color.WHITE);
		textFieldNomeFornecedor.setBounds(140, 20, 280, 30);
		jpanel.add(textFieldNomeFornecedor);
		
		lblCNPJ = new JLabel("CNPJ");
		lblCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCNPJ.setBounds(790, 20, 120, 30);
		jpanel.add(lblCNPJ);
		
		textFieldCNPJ = new JFormattedTextField();
		textFieldCNPJ.setBackground(Color.WHITE);
		textFieldCNPJ.setEnabled(false);
		textFieldCNPJ.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldCNPJ.setFocusLostBehavior(JFormattedTextField.PERSIST);
		textFieldCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCNPJ.setBounds(910, 20, 190, 30);
		textFieldCNPJ.addFocusListener(controlaFornecedor);
		jpanel.add(textFieldCNPJ);
		
		lblTelefone = new JLabel("Telefone*");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefone.setBounds(450, 20, 120, 30);
		jpanel.add(lblTelefone);
		
		try {
			maskTelefone = new MaskFormatter("(##) #####-####");
			textFieldTelefone = new JFormattedTextField(maskTelefone);
			textFieldTelefone.setBackground(Color.WHITE);
			textFieldTelefone.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
			textFieldTelefone.setEnabled(false);
			textFieldTelefone.setFocusLostBehavior(JFormattedTextField.PERSIST);
			textFieldTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textFieldTelefone.setBounds(570, 20, 190, 30);
			jpanel.add(textFieldTelefone);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEndereco.setBounds(20, 80, 120, 30);
		jpanel.add(lblEndereco);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBackground(Color.WHITE);
		textFieldEndereco.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldEndereco.setEnabled(false);
		textFieldEndereco.setToolTipText("Av. Florestal, 128");
		textFieldEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldEndereco.setBounds(140, 80, 280, 30);
		textFieldEndereco.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldEndereco);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCidade.setBounds(450, 80, 120, 30);
		jpanel.add(lblCidade);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setBackground(Color.WHITE);
		textFieldCidade.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldCidade.setEnabled(false);
		textFieldCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCidade.setBounds(570, 80, 190, 30);
		textFieldCidade.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldCidade);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setBounds(790, 80, 120, 30);
		jpanel.add(lblEstado);
		
		try {
			maskEstado = new MaskFormatter("UU");
			textFieldEstado = new JFormattedTextField(maskEstado);
			textFieldEstado.setBackground(Color.WHITE);
			textFieldEstado.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
			textFieldEstado.setEnabled(false);
			textFieldEstado.setFocusLostBehavior(JFormattedTextField.PERSIST);
			textFieldEstado.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textFieldEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textFieldEstado.setBounds(910, 80, 70, 30);
			textFieldEstado.setDocument(new UpperCaseDocument());
			jpanel.add(textFieldEstado);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblObservacao = new JLabel("Observação");
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblObservacao.setBounds(20, 140, 120, 30);
		jpanel.add(lblObservacao);
		
		textAreaObservacao = new JTextArea();
		textAreaObservacao.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textAreaObservacao.setBackground(Color.WHITE);
		textAreaObservacao.setEnabled(false);
		textAreaObservacao.setLineWrap(true);
		textAreaObservacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textAreaObservacao.setBounds(140, 140, 630, 60);
		textAreaObservacao.setDocument(new UpperCaseDocument());
		jpanel.add(textAreaObservacao);
		
		btnNovoFornecedor = new JButton("Novo");
		btnNovoFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNovoFornecedor.setBounds(20, 250, 100, 30);
		btnNovoFornecedor.addActionListener(controlaFornecedor);
		jpanel.add(btnNovoFornecedor);
		
		btnInserirFornecedor = new JButton("Inserir");
		btnInserirFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserirFornecedor.setEnabled(false);
		btnInserirFornecedor.setBounds(130, 250, 100, 30);
		btnInserirFornecedor.addActionListener(controlaFornecedor);
		jpanel.add(btnInserirFornecedor);
		
		btnSalvarFornecedor = new JButton("Salvar");
		btnSalvarFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalvarFornecedor.setEnabled(false);
		btnSalvarFornecedor.setBounds(240, 250, 100, 30);
		btnSalvarFornecedor.addActionListener(controlaFornecedor);
		jpanel.add(btnSalvarFornecedor);
		
		btnExcluirFornecedor = new JButton("Excluir");
		btnExcluirFornecedor.setEnabled(false);
		btnExcluirFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluirFornecedor.setBounds(350, 250, 100, 30);
		btnExcluirFornecedor.addActionListener(controlaFornecedor);
		jpanel.add(btnExcluirFornecedor);
		
		btnBuscarFornecedor = new JButton("Buscar");
		btnBuscarFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscarFornecedor.setBounds(780, 250, 100, 30);
		btnBuscarFornecedor.addActionListener(controlaFornecedor);
		jpanel.add(btnBuscarFornecedor);
		
		textFieldBuscaFornecedor = new JTextField();
		textFieldBuscaFornecedor.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldBuscaFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBuscaFornecedor.setBounds(525, 250, 245, 30);
		textFieldBuscaFornecedor.setDocument(new UpperCaseDocument());
		jpanel.add(textFieldBuscaFornecedor);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpar.setBounds(1005, 250, 100, 30);
		btnLimpar.addActionListener(controlaFornecedor);
		jpanel.add(btnLimpar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 300, 1085, 390);
		jpanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(controlaFornecedor);
		scrollPane.setViewportView(table);
		
	
		
		
	}

	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}

	public JButton getBtnInserirNovo() {
		return btnNovoFornecedor;
	}

	public void setBtnInserirNovo(JButton btnInserirNovo) {
		this.btnNovoFornecedor = btnInserirNovo;
	}

	public JTextField getTextFieldNomeFornecedor() {
		return textFieldNomeFornecedor;
	}

	public void setTextFieldNomeFornecedor(JTextField textFieldNomeFornecedor) {
		this.textFieldNomeFornecedor = textFieldNomeFornecedor;
	}

	public JFormattedTextField getTextFieldCNPJ() {
		return textFieldCNPJ;
	}

	public void setTextFieldCNPJ(JFormattedTextField textFieldCNPJ) {
		this.textFieldCNPJ = textFieldCNPJ;
	}
	
	public JFormattedTextField getTextFieldTelefone() {
		return textFieldTelefone;
	}

	public void setTextFieldTelefone(JFormattedTextField textFieldTelefone) {
		this.textFieldTelefone = textFieldTelefone;
	}

	public JTextField getTextFieldEndereco() {
		return textFieldEndereco;
	}

	public void setTextFieldEndereco(JTextField textFieldEndereco) {
		this.textFieldEndereco = textFieldEndereco;
	}

	public JTextField getTextFieldCidade() {
		return textFieldCidade;
	}

	public void setTextFieldCidade(JTextField textFieldCidade) {
		this.textFieldCidade = textFieldCidade;
	}

	public JFormattedTextField getTextFieldEstado() {
		return textFieldEstado;
	}

	public void setTextFieldEstado(JFormattedTextField textFieldEstado) {
		this.textFieldEstado = textFieldEstado;
	}

	public JTextArea getTextAreaObservacao() {
		return textAreaObservacao;
	}

	public void setTextAreaObservacao(JTextArea textAreaObservacao) {
		this.textAreaObservacao = textAreaObservacao;
	}

	public JButton getBtnInserirFornecedor() {
		return btnInserirFornecedor;
	}

	public void setBtnInserirFornecedor(JButton btnInserirFornecedor) {
		this.btnInserirFornecedor = btnInserirFornecedor;
	}

	public JButton getBtnSalvarFornecedor() {
		return btnSalvarFornecedor;
	}

	public void setBtnSalvarFornecedor(JButton btnSalvarFornecedor) {
		this.btnSalvarFornecedor = btnSalvarFornecedor;
	}

	public JButton getBtnExcluirFornecedor() {
		return btnExcluirFornecedor;
	}

	public void setBtnExcluirFornecedor(JButton btnExcluirFornecedor) {
		this.btnExcluirFornecedor = btnExcluirFornecedor;
	}

	public JButton getBtnBuscarFornecedor() {
		return btnBuscarFornecedor;
	}

	public void setBtnBuscarFornecedor(JButton btnBuscarFornecedor) {
		this.btnBuscarFornecedor = btnBuscarFornecedor;
	}

	public JTextField getTextFieldBuscaFornecedor() {
		return textFieldBuscaFornecedor;
	}

	public void setTextFieldBuscaFornecedor(JTextField textFieldBuscaFornecedor) {
		this.textFieldBuscaFornecedor = textFieldBuscaFornecedor;
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

	public JButton getBtnNovoFornecedor() {
		return btnNovoFornecedor;
	}

	public void setBtnNovoFornecedor(JButton btnNovoFornecedor) {
		this.btnNovoFornecedor = btnNovoFornecedor;
	}
};
