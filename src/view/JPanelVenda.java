package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelVenda extends JPanel {
	private JPanel jpanel;
	private Panel panel, panelNovaVenda, panelListarVendas, painelBase;
	private JLabel lblNovaVenda, lblListarVendas;
	
	private Connection connection;
	private JPanelNovaVenda painelNovaVenda;
	private JPanelListarVendas painelListarVendas;
	
	private boolean darkNovaVenda = true;
	private boolean darkListarVendas = false;
	
	public JPanelVenda(Connection connection) {
		this.connection = connection;
		iniciarJPanel();
	}
	
	private void iniciarJPanel(){
		jpanel = new JPanel();
		jpanel.setBorder(null);
		jpanel.setBackground(SystemColor.menu);
		jpanel.setBounds(0, 0, 1130, 700);
		jpanel.setLayout(null);
		
		panel = new Panel();
		panel.setBackground(new Color(255, 102, 102));
		panel.setBounds(0, 0, 1130, 60);
		panel.setLayout(null);
		jpanel.add(panel);
		
		panelNovaVenda = new Panel();
		panelNovaVenda.setLayout(null);
		panelNovaVenda.setBackground(new Color(100, 100, 100));
		panelNovaVenda.setBounds(0, 0, 240, 60);
		panel.add(panelNovaVenda);
		
		lblNovaVenda = new JLabel("Nova Venda");
		lblNovaVenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovaVenda.setIconTextGap(15);
		lblNovaVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNovaVenda.setIcon(new ImageIcon(JPanelVenda.class.getResource("/view/icons/cart-plus-custom.png")));
		lblNovaVenda.setForeground(new Color(255, 255, 255));
		lblNovaVenda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNovaVenda.setBounds(0, 0, 240, 60);
		panelNovaVenda.add(lblNovaVenda);
		
		panelListarVendas = new Panel();
		panelListarVendas.setLayout(null);
		panelListarVendas.setBackground(new Color(255, 102, 102));
		panelListarVendas.setBounds(240, 0, 240, 60);
		panel.add(panelListarVendas);
		
		lblListarVendas = new JLabel("Listar Vendas");
		lblListarVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListarVendas.setIconTextGap(15);
		lblListarVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblListarVendas.setIcon(new ImageIcon(JPanelVenda.class.getResource("/view/icons/clipboard-list-outline-custom.png")));
		lblListarVendas.setForeground(new Color(255, 255, 255));
		lblListarVendas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListarVendas.setBounds(0, 0, 240, 60);
		panelListarVendas.add(lblListarVendas);
		
		painelBase = new Panel();
		painelBase.setBounds(0, 60, 1130, 740);
		painelBase.setLayout(new CardLayout(0, 0));
		jpanel.add(painelBase);
		
		painelNovaVenda = new JPanelNovaVenda(this.connection);
		painelBase.add(painelNovaVenda.getJpanel(), "pNovaVenda");
		
		painelListarVendas = new JPanelListarVendas(this.connection);
		painelBase.add(painelListarVendas.getJpanel(), "pListarVendas");
		
		lblNovaVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (!darkNovaVenda) {
						panelListarVendas.setBackground(new Color(255, 102, 102));
						darkListarVendas = false;
						panelNovaVenda.setBackground(new Color(100, 100, 100));
						darkNovaVenda = true;
						((CardLayout) painelBase.getLayout()).show(painelBase, "pNovaVenda");
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		lblListarVendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (!darkListarVendas) {
						panelListarVendas.setBackground(new Color(100, 100, 100));
						darkListarVendas = true;
						
						panelNovaVenda.setBackground(new Color(255, 102, 102));
						darkNovaVenda = false;
						((CardLayout) painelBase.getLayout()).show(painelBase, "pListarVendas");
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public JPanel getJpanel() {
		return jpanel;
	}
}