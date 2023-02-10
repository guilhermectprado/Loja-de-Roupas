package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import controller.ControlaMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.CardLayout;
import java.awt.Cursor;

public class JFrameMenu extends JFrame {
	private ControlaMenu controlaMenu;
	private static Connection connection;
	
	private boolean darkVenda = true;
	private boolean darkEstoque = false;
	private boolean darkFornecedor = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMenu frame = new JFrameMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrameMenu() {
		controlaMenu = new ControlaMenu(this);
		connection = controlaMenu.conexaoBanco();
		iniciarMenu();
	}
	
	private void iniciarMenu() {
		getContentPane().setBackground(SystemColor.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1380, 740);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		
		Panel panelLogo = new Panel();
		panelLogo.setBounds(0, 0, 240, 60);
		panelLogo.setBackground(new Color(255, 102, 102));
		getContentPane().add(panelLogo);
		panelLogo.setLayout(null);
		
		JLabel lblBellaCris = new JLabel("BellaCris");
		lblBellaCris.setBounds(0, 0, 240, 60);
		panelLogo.add(lblBellaCris);
		lblBellaCris.setForeground(new Color(255, 255, 255));
		lblBellaCris.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBellaCris.setHorizontalAlignment(SwingConstants.CENTER);
		
		Panel sidebar = new Panel();
		sidebar.setBackground(new Color(255, 102, 102));
		sidebar.setBounds(0, 60, 240, 645);
		sidebar.setLayout(null);
		getContentPane().add(sidebar);
													
		Panel panelVenda = new Panel();
		panelVenda.setLayout(null);
		panelVenda.setBackground(new Color(100, 100, 100));
		panelVenda.setBounds(0, 0, 240, 60);
		sidebar.add(panelVenda);
		
		JLabel lblVenda = new JLabel("Vendas");
		lblVenda.setIconTextGap(15);
		lblVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVenda.setIcon(new ImageIcon(JFrameMenu.class.getResource("icons/cart-variant.png")));
		lblVenda.setForeground(new Color(255, 255, 255));
		lblVenda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVenda.setBounds(20, 0, 220, 60);
		panelVenda.add(lblVenda);
		
		Panel panelProdutos = new Panel();
		panelProdutos.setLayout(null);
		panelProdutos.setBackground(new Color(255, 102, 102));
		panelProdutos.setBounds(0, 60, 240, 60);
		sidebar.add(panelProdutos);
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setIconTextGap(15);
		lblProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblProdutos.setIcon(new ImageIcon(JFrameMenu.class.getResource("icons/shopping-outline.png")));
		lblProdutos.setForeground(new Color(255, 255, 255));
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProdutos.setBounds(20, 0, 220, 60);
		panelProdutos.add(lblProdutos);
		
		Panel panelFornecedor = new Panel();
		panelFornecedor.setLayout(null);
		panelFornecedor.setBackground(new Color(255, 102, 102));
		panelFornecedor.setBounds(0, 120, 240, 60);
		sidebar.add(panelFornecedor);
		
		JLabel lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setIconTextGap(15);
		lblFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFornecedor.setIcon(new ImageIcon(JFrameMenu.class.getResource("icons/card-account-outline.png")));
		lblFornecedor.setForeground(Color.WHITE);
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFornecedor.setBounds(20, 0, 220, 60);
		panelFornecedor.add(lblFornecedor);
		
		Panel painelBase = new Panel(new CardLayout());
		painelBase.setBackground(new Color(255, 255, 255));
		painelBase.setBounds(240, 0, 1124, 700);
		getContentPane().add(painelBase);
		
		JPanelVenda painelVenda = new JPanelVenda(connection);
		painelBase.add(painelVenda.getJpanel(), "pVenda");
		
		JPanelProduto painelProdutos = new JPanelProduto(connection);
		painelBase.add(painelProdutos.getJpanel(), "pProdutos");
		
		JPanelFornecedor painelFornecedores = new JPanelFornecedor(connection);
		painelBase.add(painelFornecedores.getJpanel(), "pFornecedor");
		
		lblVenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (!darkVenda) {
						panelProdutos.setBackground(new Color(255, 102, 102));
						darkEstoque = false;
						
						panelFornecedor.setBackground(new Color(255, 102, 102));
						darkFornecedor = false;
						
						panelVenda.setBackground(new Color(100, 100, 100));
						darkVenda = true;
						
						((CardLayout) painelBase.getLayout()).show(painelBase, "pVenda");
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		});
		
		lblProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					if (!darkEstoque) {
						panelProdutos.setBackground(new Color(100, 100, 100));
						darkEstoque = true;
						
						panelFornecedor.setBackground(new Color(255, 102, 102));
						darkFornecedor = false;
						
						panelVenda.setBackground(new Color(255, 102, 102));
						darkVenda = false;
						
						((CardLayout) painelBase.getLayout()).show(painelBase, "pProdutos");						
					}
					
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		lblFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (!darkFornecedor) {
						panelProdutos.setBackground(new Color(255, 102, 102));
						darkEstoque = false;
						
						panelFornecedor.setBackground(new Color(100, 100, 100));
						darkFornecedor = true;
						
						panelVenda.setBackground(new Color(255, 102, 102));
						darkVenda = false;
						
						((CardLayout) painelBase.getLayout()).show(painelBase, "pFornecedor");						
					}
					
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
						
	}
}
