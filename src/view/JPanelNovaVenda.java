package view;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import controller.ControlaNovaVenda;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class JPanelNovaVenda extends JPanel {
	private JPanel jpanel;
	private JLabel lblData, lblCodigoProduto, lblQuantidade, lblDesconto, lblParcela, lblFormaPagamento, lblValorTotal, lblSubTotal, lblValorRecebido, lblTroco;
	private JLabel lblTipo, lblModelo, lblTamanho, lblQtd, lblPrecoUnitario, lblObservacao;
	private JTextArea textAreaObservacao;
	private JTextField textFieldData, textFieldCodigoProduto, textFieldTipo, textFieldModelo, textFieldTamanho, textFieldPrecoUnitario, textFieldSubTotal;
	private JTextField textFieldDesconto, textFieldQuantidade, textFieldValorTotal, textFieldValorRecebido, textFieldTroco;
	private JSpinner spinnerQuantidade, spinnerParcelas;
	private JButton btnAdicionarItem, btnCancelar, btnFinalizarVenda, btnRemoverItem;
	private JScrollPane scrollPane;
	private JTable table;
	private JCheckBox checkboxPorcentagem;
	private JComboBox comboBoxFormaPagamento;
	private ControlaNovaVenda controlaNovaVenda;


	public JPanelNovaVenda(Connection connection) {
		controlaNovaVenda = new ControlaNovaVenda(this, connection);
		iniciarJPanel();
	}

	private void iniciarJPanel(){
		jpanel = new JPanel();
		jpanel.setBorder(null);
		jpanel.setBackground(Color.WHITE);
		jpanel.setBounds(0, 0, 1130, 690);
		jpanel.setLayout(null);
		
		lblCodigoProduto = new JLabel("Código do Produto");
		lblCodigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCodigoProduto.setBounds(20, 10, 160, 30);
		jpanel.add(lblCodigoProduto);
		
		textFieldCodigoProduto = new JTextField();
		textFieldCodigoProduto.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldCodigoProduto.setBackground(Color.WHITE);
		textFieldCodigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCodigoProduto.setColumns(10);
		textFieldCodigoProduto.setBounds(180, 10, 150, 30);
		textFieldCodigoProduto.addFocusListener(controlaNovaVenda);
		textFieldCodigoProduto.addKeyListener(controlaNovaVenda);
		jpanel.add(textFieldCodigoProduto);
		
		lblQuantidade = new JLabel("QTD");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantidade.setBounds(420, 10, 60, 30);
		jpanel.add(lblQuantidade);
		
		spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		spinnerQuantidade.setBackground(Color.WHITE);
		spinnerQuantidade.setBounds(480, 10, 50, 30);
		spinnerQuantidade.addKeyListener(controlaNovaVenda);
		jpanel.add(spinnerQuantidade);
				
		btnAdicionarItem = new JButton("Adicionar Item");
		btnAdicionarItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdicionarItem.setBounds(570, 10, 150, 30);
		btnAdicionarItem.addKeyListener(controlaNovaVenda);
		btnAdicionarItem.addActionListener(controlaNovaVenda);
		jpanel.add(btnAdicionarItem);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setEnabled(false);
		textFieldTipo.setDisabledTextColor(Color.black);
		textFieldTipo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTipo.setBackground(Color.WHITE);
		textFieldTipo.setForeground(new Color(0, 0, 0));
		textFieldTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTipo.setColumns(10);
		textFieldTipo.setBounds(20, 100, 200, 30);
		jpanel.add(textFieldTipo);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipo.setBounds(20, 70, 200, 30);
		jpanel.add(lblTipo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setEnabled(false);
		textFieldModelo.setDisabledTextColor(Color.black);
		textFieldModelo.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldModelo.setBackground(Color.WHITE);
		textFieldModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(240, 100, 200, 30);
		jpanel.add(textFieldModelo);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModelo.setBounds(240, 70, 200, 30);
		jpanel.add(lblModelo);
		
		textFieldTamanho = new JTextField();
		textFieldTamanho.setEnabled(false);
		textFieldTamanho.setDisabledTextColor(Color.black);
		textFieldTamanho.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTamanho.setBackground(Color.WHITE);
		textFieldTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTamanho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTamanho.setColumns(10);
		textFieldTamanho.setBounds(460, 100, 60, 30);
		jpanel.add(textFieldTamanho);
		
		lblTamanho = new JLabel("TAM");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTamanho.setBounds(460, 70, 60, 30);
		jpanel.add(lblTamanho);
		
		textFieldPrecoUnitario = new JTextField();
		textFieldPrecoUnitario.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldPrecoUnitario.setBackground(Color.WHITE);
		textFieldPrecoUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrecoUnitario.setEnabled(false);
		textFieldPrecoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPrecoUnitario.setColumns(10);
		textFieldPrecoUnitario.setBounds(540, 100, 100, 30);
		jpanel.add(textFieldPrecoUnitario);
		
		lblPrecoUnitario = new JLabel("R$ UNIT");
		textFieldPrecoUnitario.setDisabledTextColor(Color.black);
		lblPrecoUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrecoUnitario.setBounds(540, 70, 100, 30);
		jpanel.add(lblPrecoUnitario);
		
		lblQtd = new JLabel("QTD");
		lblQtd.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQtd.setBounds(660, 70, 60, 30);
		jpanel.add(lblQtd);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setDisabledTextColor(Color.black);
		textFieldQuantidade.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldQuantidade.setBackground(Color.WHITE);
		textFieldQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQuantidade.setEnabled(false);
		textFieldQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldQuantidade.setColumns(10);
		textFieldQuantidade.setBounds(660, 100, 60, 30);
		jpanel.add(textFieldQuantidade);
		
		lblObservacao = new JLabel("Observação");
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblObservacao.setBounds(20, 140, 150, 30);
		jpanel.add(lblObservacao);
		
		textAreaObservacao = new JTextArea();
		textAreaObservacao.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textAreaObservacao.setBackground(Color.WHITE);
		textAreaObservacao.setEnabled(false);
		textAreaObservacao.setDisabledTextColor(Color.black);
		textAreaObservacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAreaObservacao.setBounds(20, 170, 700, 30);
		jpanel.add(textAreaObservacao);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 220, 700, 360);
		jpanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(controlaNovaVenda);
		scrollPane.setViewportView(table);
		
		lblSubTotal = new JLabel("SubTotal            - R$");
		lblSubTotal.setForeground(Color.RED);
		lblSubTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSubTotal.setBounds(760, 170, 200, 30);
		jpanel.add(lblSubTotal);
		
		textFieldSubTotal = new JTextField();
		textFieldSubTotal.setEnabled(false);
		textFieldSubTotal.setDisabledTextColor(Color.black);
		textFieldSubTotal.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldSubTotal.setBackground(Color.WHITE);
		textFieldSubTotal.setForeground(Color.RED);
		textFieldSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSubTotal.setColumns(10);
		textFieldSubTotal.setBounds(960, 170, 150, 30);
		jpanel.add(textFieldSubTotal);
		
		lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblData.setBounds(970, 10, 50, 30);
		jpanel.add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setEnabled(false);
		textFieldData.setDisabledTextColor(Color.black);
		textFieldData.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldData.setBackground(Color.WHITE);
		textFieldData.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldData.setColumns(10);
		textFieldData.setBounds(1020, 10, 90, 30);
		jpanel.add(textFieldData);
		
		lblDesconto = new JLabel("Desconto");
		lblDesconto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDesconto.setBounds(760, 220, 120, 30);
		jpanel.add(lblDesconto);
		
		textFieldDesconto = new JTextField();
		textFieldDesconto.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldDesconto.setBackground(Color.WHITE);
		textFieldDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDesconto.addFocusListener(controlaNovaVenda);
		textFieldDesconto.addKeyListener(controlaNovaVenda);
		textFieldDesconto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldDesconto.setColumns(10);
		textFieldDesconto.setBounds(960, 220, 90, 30);
		jpanel.add(textFieldDesconto);
		
		checkboxPorcentagem = new JCheckBox("%");
		checkboxPorcentagem.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		checkboxPorcentagem.setBackground(Color.WHITE);
		checkboxPorcentagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkboxPorcentagem.setBounds(1050, 220, 60, 30);
		checkboxPorcentagem.addMouseListener(controlaNovaVenda);
		jpanel.add(checkboxPorcentagem);
		
		lblParcela = new JLabel("Parcelas");
		lblParcela.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblParcela.setBounds(760, 270, 120, 30);
		jpanel.add(lblParcela);
		
		lblFormaPagamento = new JLabel("Forma de Pagamento");
		lblFormaPagamento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFormaPagamento.setBounds(760, 320, 200, 30);
		jpanel.add(lblFormaPagamento);
		
		comboBoxFormaPagamento = new JComboBox();
		comboBoxFormaPagamento.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		comboBoxFormaPagamento.setBackground(Color.WHITE);
		comboBoxFormaPagamento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxFormaPagamento.setBounds(960, 320, 150, 30);
		comboBoxFormaPagamento.addItem("Crédito");
		comboBoxFormaPagamento.addItem("Débito");
		comboBoxFormaPagamento.addItem("Dinheiro");
		comboBoxFormaPagamento.addItem("Dinheiro/Cartão");
		comboBoxFormaPagamento.addItem("Pix");
		comboBoxFormaPagamento.addItem("Outros...");
		comboBoxFormaPagamento.addFocusListener(controlaNovaVenda);
		comboBoxFormaPagamento.addKeyListener(controlaNovaVenda);
		jpanel.add(comboBoxFormaPagamento);
		
		lblValorTotal = new JLabel("Valor Total     - R$");
		lblValorTotal.setForeground(Color.RED);
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorTotal.setBounds(760, 470, 200, 50);
		jpanel.add(lblValorTotal);
		
		textFieldValorTotal = new JTextField();
		textFieldValorTotal.setEnabled(false);
		textFieldValorTotal.setDisabledTextColor(Color.black);
		textFieldValorTotal.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldValorTotal.setBackground(Color.WHITE);
		textFieldValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldValorTotal.setForeground(new Color(255, 0, 0));
		textFieldValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textFieldValorTotal.setColumns(10);
		textFieldValorTotal.setBounds(960, 470, 150, 50);
		jpanel.add(textFieldValorTotal);
		
		lblValorRecebido = new JLabel("Valor Recebido    - R$");
		lblValorRecebido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorRecebido.setBounds(760, 370, 200, 30);
		jpanel.add(lblValorRecebido);
		
		textFieldValorRecebido = new JTextField();
		textFieldValorRecebido.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldValorRecebido.setBackground(Color.WHITE);
		textFieldValorRecebido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldValorRecebido.setColumns(10);
		textFieldValorRecebido.setBounds(960, 370, 150, 30);
		textFieldValorRecebido.addFocusListener(controlaNovaVenda);
		textFieldValorRecebido.addKeyListener(controlaNovaVenda);
		jpanel.add(textFieldValorRecebido);
		
		lblTroco = new JLabel("Troco                - R$");
		lblTroco.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTroco.setBounds(760, 420, 200, 30);
		jpanel.add(lblTroco);
		
		textFieldTroco = new JTextField();
		textFieldTroco.setEnabled(false);
		textFieldTroco.setDisabledTextColor(Color.black);
		textFieldTroco.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		textFieldTroco.setBackground(Color.WHITE);
		textFieldTroco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldTroco.setColumns(10);
		textFieldTroco.setBounds(960, 420, 150, 30);
		jpanel.add(textFieldTroco);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(controlaNovaVenda);
		btnCancelar.addKeyListener(controlaNovaVenda);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(940, 580, 180, 50);
		jpanel.add(btnCancelar);
		
		btnFinalizarVenda = new JButton("Finalizar Venda");
		btnFinalizarVenda.setEnabled(false);
		btnFinalizarVenda.addActionListener(controlaNovaVenda);
		btnFinalizarVenda.addKeyListener(controlaNovaVenda);
		btnFinalizarVenda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFinalizarVenda.setBounds(760, 580, 180, 50);
		jpanel.add(btnFinalizarVenda);
		
		spinnerParcelas = new JSpinner();
		spinnerParcelas.setBorder(new LineBorder(new Color(255, 102, 102), 1, true));
		spinnerParcelas.setBackground(Color.WHITE);
		spinnerParcelas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinnerParcelas.setBounds(960, 270, 60, 30);
		spinnerParcelas.addKeyListener(controlaNovaVenda);
		jpanel.add(spinnerParcelas);
		
		btnRemoverItem = new JButton("Remover Item");
		btnRemoverItem.setEnabled(false);
		btnRemoverItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoverItem.setBounds(20, 580, 180, 50);
		btnRemoverItem.addActionListener(controlaNovaVenda);
		btnRemoverItem.addKeyListener(controlaNovaVenda);
		jpanel.add(btnRemoverItem);
		


		// CHAMADOS DA CONTROLADORA
		controlaNovaVenda.iniciaVenda();
	}

	public void setTextAreaObservacao(JTextArea textAreaObservacao) {
		this.textAreaObservacao = textAreaObservacao;
	}

	public void setTextFieldData(JTextField textFieldData) {
		this.textFieldData = textFieldData;
	}

	public void setTextFieldCodigoProduto(JTextField textFieldCodigoProduto) {
		this.textFieldCodigoProduto = textFieldCodigoProduto;
	}

	public void setSpinnerQuantidade(JSpinner spinnerQuantidade) {
		this.spinnerQuantidade = spinnerQuantidade;
	}

	public void setTextFieldTipo(JTextField textFieldTipo) {
		this.textFieldTipo = textFieldTipo;
	}

	public void setTextFieldModelo(JTextField textFieldModelo) {
		this.textFieldModelo = textFieldModelo;
	}

	public void setTextFieldTamanho(JTextField textFieldTamanho) {
		this.textFieldTamanho = textFieldTamanho;
	}

	public void setTextFieldPrecoUnitario(JTextField textFieldPrecoUnitario) {
		this.textFieldPrecoUnitario = textFieldPrecoUnitario;
	}
	
	public void setTextFieldQuantidade(JTextField textFieldQuantidade) {
		this.textFieldQuantidade = textFieldQuantidade;
	}
	
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setTextFieldSubTotal(JTextField textFieldSubTotal) {
		this.textFieldSubTotal = textFieldSubTotal;
	}

	public void setTextFieldDesconto(JTextField textFieldDesconto) {
		this.textFieldDesconto = textFieldDesconto;
	}
	
	public void setCheckboxPorcentagem(JCheckBox checkboxPorcentagem) {
		this.checkboxPorcentagem = checkboxPorcentagem;
	}

	public void setTextFieldParcelas(JSpinner spinnerParcelas) {
		this.spinnerParcelas = spinnerParcelas;
	}
	
	public void setComboBoxFormaPagamento(JComboBox comboBoxFormaPagamento) {
		this.comboBoxFormaPagamento = comboBoxFormaPagamento;
	}

	public void setTextFieldValorTotal(JTextField textFieldValorTotal) {
		this.textFieldValorTotal = textFieldValorTotal;
	}

	public void setTextFieldValorRecebido(JTextField textFieldValorRecebido) {
		this.textFieldValorRecebido = textFieldValorRecebido;
	}

	public void setTextFieldTroco(JTextField textFieldTroco) {
		this.textFieldTroco = textFieldTroco;
	}

	public void setBtnAdicionarItem(JButton btnAdicionarItem) {
		this.btnAdicionarItem = btnAdicionarItem;
	}
	
	public void setBtnFinalizarVenda(JButton btnFinalizarVenda) {
		this.btnFinalizarVenda = btnFinalizarVenda;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	
	public void setBtnRemoverItem(JButton btnRemoverItem) {
		this.btnRemoverItem = btnRemoverItem;
	}

	
	






	public JTextField getTextFieldData() {
		return textFieldData;
	}

	public JTextField getTextFieldCodigoProduto() {
		return textFieldCodigoProduto;
	}

	public JSpinner getSpinnerQuantidade() {
		return spinnerQuantidade;
	}

	public JTextField getTextFieldTipo() {
		return textFieldTipo;
	}

	public JTextField getTextFieldModelo() {
		return textFieldModelo;
	}

	public JTextField getTextFieldTamanho() {
		return textFieldTamanho;
	}

	public JTextField getTextFieldPrecoUnitario() {
		return textFieldPrecoUnitario;
	}
	
	public JTextField getTextFieldQuantidade() {
		return textFieldQuantidade;
	}
	
	public JTextArea getTextAreaObservacao() {
		return textAreaObservacao;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTable() {
		return table;
	}
	
	public JTextField getTextFieldSubTotal() {
		return textFieldSubTotal;
	}

	public JTextField getTextFieldDesconto() {
		return textFieldDesconto;
	}

	public JCheckBox getCheckboxPorcentagem() {
		return checkboxPorcentagem;
	}

	public JSpinner getSpinnerParcelas() {
		return spinnerParcelas;
	}
	
	public JComboBox getComboBoxFormaPagamento() {
		return comboBoxFormaPagamento;
	}
	
	public JTextField getTextFieldValorTotal() {
		return textFieldValorTotal;
	}

	public JTextField getTextFieldValorRecebido() {
		return textFieldValorRecebido;
	}

	public JTextField getTextFieldTroco() {
		return textFieldTroco;
	}


	public JButton getBtnAdicionarItem() {
		return btnAdicionarItem;
	}
	
	public JButton getBtnFinalizarVenda() {
		return btnFinalizarVenda;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnRemoverItem() {
		return btnRemoverItem;
	}


	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}
}
