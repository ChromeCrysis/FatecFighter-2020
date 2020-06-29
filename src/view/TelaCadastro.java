package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dao.ConnectionFactory;
import model.Jogador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaCadastro {

	JFrame frmFatecGame;
	JPasswordField txtConfirmaSenha;
	JTextField txtLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro window = new TelaCadastro();
					window.frmFatecGame.setVisible(true);
					window.frmFatecGame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastro() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFatecGame = new JFrame();
		frmFatecGame.setResizable(false);
		frmFatecGame.setTitle("Fatec - Arcade Game ");
		frmFatecGame.setBounds(100, 100, 600, 450);
		frmFatecGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFatecGame.getContentPane().setLayout(null);
		
		JPanel panelCadastrar = new JPanel();
		panelCadastrar.setBounds(100, 11, 377, 389);
		panelCadastrar.setBackground(new Color(0, 0, 90));
		frmFatecGame.getContentPane().add(panelCadastrar);
		panelCadastrar.setLayout(null);
		
		JPasswordField txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(255, 255, 255));
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSenha.setBounds(62, 179, 246, 16);
		txtSenha.setBackground(null);
		txtSenha.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		panelCadastrar.add(txtSenha);
		
		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setForeground(Color.WHITE);
		txtConfirmaSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		txtConfirmaSenha.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		txtConfirmaSenha.setBackground((Color) null);
		txtConfirmaSenha.setBounds(62, 228, 246, 16);
		panelCadastrar.add(txtConfirmaSenha);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
		lblSenha.setBounds(62, 155, 185, 14);
		panelCadastrar.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		lblConfirmarSenha.setForeground(Color.WHITE);
		lblConfirmarSenha.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
		lblConfirmarSenha.setBounds(62, 206, 185, 14);
		panelCadastrar.add(lblConfirmarSenha);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
		lblUsuario.setBounds(62, 104, 185, 14);
		panelCadastrar.add(lblUsuario);
		
		txtLogin = new JTextField();
		txtLogin.setForeground(Color.WHITE);
		txtLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		txtLogin.setDisabledTextColor(Color.WHITE);
		txtLogin.setColumns(10);
		txtLogin.setCaretColor(Color.BLACK);
		txtLogin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		txtLogin.setBackground((Color) null);
		txtLogin.setBounds(62, 129, 246, 16);
		panelCadastrar.add(txtLogin);
		
		/*
		 * Botão Cadastrar e método de cadastro
		 */
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Variáveis de conexão com o banco de dados
				 * 
				 * PreparedStatement é o a classe que possui o método de executar query SQL, foi passado como parâmetro o valor do campo de 
				 * login e o valor do campo de senha para serem inseridos na query.
				 * 
				 * Para executar a query e salvar no banco de dados utiliza-se o objeto stm com o método execute.
				 */
				Connection conn = new ConnectionFactory().getConnection();
					try {
						/*
						 * Método de Validação para cadastrar apenas quando todos os campos forem preenchidos
						 * Método de validação para senha e confirmar senha sejam iguais
						 */
						if (txtLogin.getText().equals("") || txtSenha.getText().equals("") || txtConfirmaSenha.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Preencha todos os campos!!!");
						}else if(txtSenha.getText().equals(txtConfirmaSenha.getText())){
							PreparedStatement stm = conn.prepareStatement("insert into JOGADOR(LOGIN_JOGADOR, SENHA_JOGADOR) values ('"+ txtLogin.getText() +"', '"+ txtSenha.getText() + "');");
							stm.execute();
							JOptionPane.showMessageDialog(null, "Jogador cadastrado com sucesso!");
						}
						else {
							JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
						}
					}catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar, tente novamente!!!");
					}
			}
		});
		
		
		btnCadastrar.setBackground(new Color(0, 102, 204));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
		btnCadastrar.setBounds(62, 285, 129, 23);
		panelCadastrar.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.frameFatecArcadeGame.setVisible(true);
				frmFatecGame.dispose();
				
			}
		});
		btnVoltar.setBackground(new Color(0, 102, 204));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
		btnVoltar.setBounds(219, 286, 89, 23);
		panelCadastrar.add(btnVoltar);
		
		JLabel lblTitle = new JLabel("Fatec Arcade Game");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblTitle.setBounds(62, 38, 246, 55);
		panelCadastrar.add(lblTitle);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\ander\\OneDrive\\Documentos\\Projetos Java\\PI_Front-End\\src\\res\\background.jpg"));
		lblBackground.setBounds(0, 0, 594, 421);
		frmFatecGame.getContentPane().add(lblBackground);
		
		
	}
}
