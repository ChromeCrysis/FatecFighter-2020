package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Import de outro Package
import dao.ConnectionFactory;
import model.Jogador;

//Import de outro Folder
import com.indie.main.*;

//Import SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaLogin {

	JFrame frameFatecArcadeGame;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	
	//Objeto da classe de conexão com o banco de dados
	Connection conn = new ConnectionFactory().getConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frameFatecArcadeGame.setVisible(true);
					window.frameFatecArcadeGame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
		//Abre conexão com o banco
		frameFatecArcadeGame.setSize(738, 500);
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameFatecArcadeGame = new JFrame();
		frameFatecArcadeGame.setResizable(false);
		frameFatecArcadeGame.setTitle("Fatec- Arcade Game");
		frameFatecArcadeGame.getContentPane().setBackground(new Color(240, 240, 240));
		frameFatecArcadeGame.setBounds(100, 100, 660, 403);
		frameFatecArcadeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFatecArcadeGame.getContentPane().setLayout(null);
		
		JPanel panelLoginArea = new JPanel();
		panelLoginArea.setForeground(new Color(0, 0, 0));
		panelLoginArea.setBackground(new Color(0, 0, 54));
		panelLoginArea.setBounds(20, 29, 300, 261);
		frameFatecArcadeGame.getContentPane().add(panelLoginArea);
		panelLoginArea.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setDisabledTextColor(new Color(255, 255, 255));
		txtUsuario.setCaretColor(new Color(0, 0, 0));
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsuario.setBounds(47, 88, 210, 20);
		txtUsuario.setBackground(null);
		txtUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		panelLoginArea.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(255, 255, 255));
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSenha.setBounds(47, 149, 210, 20);
		txtSenha.setBackground(null);
		txtSenha.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		panelLoginArea.add(txtSenha);
		
		
		/*
		 * Botão e método de Login
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*
					 * Abre a conexão com o banco de dados e executa a Query SQL para buscar os dados usando o campo de usuário e senha como
					 * parâmetro.
					 * o Result set rs.next busca o próximo registro
					 */
					PreparedStatement smt = conn.prepareStatement("SELECT * FROM JOGADOR WHERE LOGIN_JOGADOR='" + txtUsuario.getText()  + "'");
					ResultSet rs = smt.executeQuery();
					rs.next();
					/*
					 * se o campo usuário ou o campo senha estiverem vazios, irá aparecer a mensagem de erro
					 */
					if(txtUsuario.getText().equals("") || txtSenha.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Preencha todos os campos para fazer login!!!");
					}else if (rs.getString("SENHA_JOGADOR").equals(txtSenha.getText())) {
						JOptionPane.showMessageDialog(null, "Bem vindo"+ " "+ txtUsuario.getText() +" "+"ao Fatec Fighting!");
						Game telaGame = new Game();
						/*
						 * Se a senha existir na consulta vai redirecionar para a próxima tela
						 * mudar a tela para o frame inicial do jogo
						 */
						telaGame.setVisible(true);
						telaGame.start();
						frameFatecArcadeGame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Usuário ou Senha Inválidos, tente novamente!");
					}
					
				} catch (SQLException ex) {
					
					ex.printStackTrace();
				}
			}
		});
		btnLogin.setBackground(new Color(0, 102, 255));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBounds(47, 208, 89, 23);
		panelLoginArea.add(btnLogin);
		
		JButton btnCadastrar = new JButton("Cadastre-se");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.frmFatecGame.setVisible(true);
				frameFatecArcadeGame.dispose();
			}
		});
		btnCadastrar.setBackground(new Color(0, 102, 255));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBounds(142, 208, 115, 23);
		panelLoginArea.add(btnCadastrar);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setBounds(46, 135, 46, 14);
		panelLoginArea.add(lblSenha);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setBounds(47, 74, 126, 14);
		panelLoginArea.add(lblUsuario);
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblTitle = new JLabel("Fatec Fighter");
		lblTitle.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(47, 31, 210, 32);
		panelLoginArea.add(lblTitle);
		
		JLabel lblFatecLogo = new JLabel("");
		lblFatecLogo.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Documentos\\Projetos Java\\PI_Front-End\\src\\res\\fatec_logo.png"));
		lblFatecLogo.setBounds(20, 333, 300, 127);
		frameFatecArcadeGame.getContentPane().add(lblFatecLogo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Documentos\\Projetos Java\\PI_Front-End\\src\\res\\background.jpg"));
		lblBackground.setBounds(0, 0, 735, 471);
		frameFatecArcadeGame.getContentPane().add(lblBackground);
		
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}

	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
