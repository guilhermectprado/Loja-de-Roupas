package controller;

import java.sql.Connection;
import model.ConexaoBD;
import view.JFrameMenu;

public class ControlaMenu  {
	
	public ControlaMenu(JFrameMenu JFRAME_MENU) {
	}
	
	public Connection conexaoBanco() {
		return ConexaoBD.conectar();
	}


}
