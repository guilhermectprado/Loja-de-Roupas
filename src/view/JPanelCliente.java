package view;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

public class JPanelCliente extends JPanel {
	private JPanel jpanel;

	public JPanelCliente() {
		iniciarJPanel();
	}

	public void iniciarJPanel() {
		jpanel = new JPanel();
		jpanel.setBorder(null);
		jpanel.setBackground(SystemColor.menu);
		jpanel.setBounds(0, 0, 1130, 620);
		jpanel.setLayout(null);
		
		JTextField textFieldFornecedor = new JTextField();
		textFieldFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldFornecedor.setColumns(10);
		textFieldFornecedor.setBounds(140, 20, 280, 30);
		jpanel.add(textFieldFornecedor);
		
		JLabel lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNome.setBounds(20, 20, 120, 30);
		jpanel.add(lblNome);
		
		JLabel lblObservacao = new JLabel("Observação");
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblObservacao.setBounds(800, 20, 120, 30);
		jpanel.add(lblObservacao);
		
		JTextArea textAreaObservacao = new JTextArea();
		textAreaObservacao.setLineWrap(true);
		textAreaObservacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAreaObservacao.setBounds(920, 20, 200, 150);
		jpanel.add(textAreaObservacao);
		
		JButton btnInserirProduto = new JButton("Inserir");
		btnInserirProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserirProduto.setBounds(20, 250, 100, 30);
		jpanel.add(btnInserirProduto);
		
		JButton btnEditarProduto = new JButton("Editar");
		btnEditarProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditarProduto.setBounds(130, 250, 100, 30);
		jpanel.add(btnEditarProduto);
		
		JButton btnExcluirProduto = new JButton("Excluir");
		btnExcluirProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluirProduto.setBounds(240, 250, 100, 30);
		jpanel.add(btnExcluirProduto);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.setBounds(610, 250, 100, 30);
		jpanel.add(btnBuscar);
		
		JTextField textFieldBusca = new JTextField();
		textFieldBusca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBusca.setBounds(390, 250, 220, 30);
		jpanel.add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpar.setBounds(955, 250, 155, 30);
		jpanel.add(btnLimpar);
		
		JButton btnGerarCdigo = new JButton("Gerar Código");
		btnGerarCdigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGerarCdigo.setBounds(790, 250, 155, 30);
		jpanel.add(btnGerarCdigo);
		
		JTable table = new JTable();
		table.setBorder(new CompoundBorder());
		table.setBounds(20, 300, 1090, 300);
		jpanel.add(table);
	}
	
	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}

}
