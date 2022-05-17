package form;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import controller.DadosInpeController;
import entity.DadosInpe;
import fila.Fila;
import net.miginfocom.swing.MigLayout;
import ordenacao.Bubble;
import ordenacao.Merge;
import pilha.PilhaTxt;
import table.DadosInpeModel;

public class DadosInpeForm extends JFrame {

	private JLabel lbAno, lbMes, lbKm; // JLabel pode exibir texto, imagem ou ambos
	public JTextField txtAno; // E para inserir uma linha de texto 
	public JTextField txtMes; // E para inserir uma linha de texto 
	public JTextField txtKm; // E para inserir uma linha de texto 
	private JPanel panelAdd, panelTable, panelButtons; //E um componente do pacote swing que tem como principal fun��o servir de conteiner para outros componentes
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnPilha, btnFila, btnMerge, btnBubble; // Cria um bot�o clicavel para fazer uma a��o
	public JTable table; // Representa uma tabela 
	private JScrollPane scrollPane; // Para fazer a rolagem da pagina 

	private List<DadosInpe> dadosinpeList; // Uma lista para importa dadosinpeList
	private Long idDadosinpe; // Para recuperar o id para ter acesso

	public DadosInpeForm() throws HeadlessException{ // Cabe�alho
		super ("Entrada Dados Inpe");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelAdd = new JPanel (new MigLayout()); // Iniciando o Jpanel com o parametro MigLayout
		panelAdd.setBorder(BorderFactory.createTitledBorder("Entrada Dados Inpe")); // Borda 
		panelAdd.setBounds(5, 0, 480, 100); // Posi��o e tamanho

		lbAno = new JLabel("Ano"); // Nome label
		lbMes = new JLabel("M�s"); // Nome label
		lbKm = new JLabel("Km�"); // Nome label

		txtAno = new JTextField(50); // Campo do texto 
		txtMes = new JTextField(50); // Campo do texto 
		txtKm = new JTextField(15); // Campo do texto 

		panelAdd.add(lbAno);
		panelAdd.add(txtAno, "span, growx"); // Para expandir o campo 

		panelAdd.add(lbMes);
		panelAdd.add(txtMes, "span, growx"); // Para expandir o campo 

		panelAdd.add(lbKm);
		panelAdd.add(txtKm, "wrap para"); // Envolver campo

		panelButtons = new JPanel(new MigLayout()); // Iniciando o Jpanel com o parametro MigLayout
		panelButtons.setBorder(BorderFactory.createEtchedBorder()); // Borda 
		panelButtons.setBounds(5, 105, 480, 40); // Posi��o e tamanho

		ClassLoader loader = getClass().getClassLoader(); // Imagem para os bot�es
		btnNew = new JButton(new ImageIcon(loader.getResource("img/new.png")));
		btnSave = new JButton(new ImageIcon(loader.getResource("img/save.png")));
		btnUpdate = new JButton(new ImageIcon(loader.getResource("img/edit.png")));
		btnRemove = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
		btnPilha = new JButton(new ImageIcon(loader.getResource("img/pilha.png")));
		btnFila = new JButton(new ImageIcon(loader.getResource("img/fila.png")));
		btnMerge = new JButton(new ImageIcon(loader.getResource("img/merge.png")));
		btnBubble= new JButton(new ImageIcon(loader.getResource("img/bubble.png")));

		// Nome para os bot�es aparece quando o mouse passa em cima   
		btnNew.setToolTipText("Novo"); 
		btnSave.setToolTipText("Salvar"); 
		btnUpdate.setToolTipText("Alterar");
		btnRemove.setToolTipText("Excluir");
		btnPilha.setToolTipText("Pilha");
		btnFila.setToolTipText("Fila");
		btnMerge.setToolTipText("Merge");
		btnBubble.setToolTipText("Bubble");

		// Posi��o dos bot�es
		panelButtons.add(btnPilha,"gapleft 15");
		panelButtons.add(btnFila);
		panelButtons.add(btnNew);
		panelButtons.add(btnSave);
		panelButtons.add(btnUpdate );
		panelButtons.add(btnRemove);
		panelButtons.add(btnMerge);
		panelButtons.add(btnBubble);

		panelTable = new JPanel (new MigLayout()); // Iniciando o Jpanel com o parametro MigLayout
		panelTable.setBorder(BorderFactory.createTitledBorder("Dados Inpe")); // Borda 
		panelTable.setBounds(5, 150, 480, 240); // Posi��o e tamanho

		table = new JTable(); // Tabela com os dados  

		scrollPane = new JScrollPane(table); // Barra de rolagem 

		panelTable.add(scrollPane); // A tabela recebendo a barra de rolagem 

		atualizarTable(); // Chama o m�todo para atualizar a tabela


		add(panelAdd); // Iniciar o panel
		add(panelButtons);// Iniciar botoes
		add(panelTable); // Iniciar tabela
		setMinimumSize(new Dimension(500,420)); // Tamanho da janela 
		setVisible(true); // Ficar visivel 

		// A��o para iniciar o m�todo pela bot�o Save
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSaveDadosInpe();		
				}
		});
		// A��o para iniciar o m�todo pela bot�o New
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onNovoDadosInpe();
			}
		});
		// A��o para iniciar o m�todo pela bot�o Remove
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRemoverDadoInpe();
			}
		});
		// A��o para iniciar o m�todo pela bot�o Upadate
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAlterarDadosinpe();
			}
		});
		// A��o para iniciar a classe e m�todo pela bot�o Pilha
		btnPilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PilhaTxt pi = new PilhaTxt();
				onSaveDadosInpe();
				try {
					pi.DadosinpePilha();
				} catch (SQLException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// A��o para iniciar a classe e m�todo pela bot�o Merge
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Merge me = new Merge();
				try {
					Merge.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		// A��o para iniciar a classe e m�todo pela bot�o Bubble
		btnBubble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bubble bu = new Bubble();
				try {
					Bubble.main(null);
				} catch (SQLException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// A��o para iniciar a classe e m�todo pela bot�o Fila
		btnFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fila fi = new Fila();
				onSaveDadosInpe();
				try {
					Fila.main(null);
				} catch (SQLException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}


	private void onRemoverDadoInpe() { // Metado para remover um item da tabela 
		int rowIndex = table.getSelectedRow(); // Selecionar o item 

		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o dado a ser removido!");
			return;
		}

		DadosInpe dadosinpe = new DadosInpeModel(dadosinpeList).get(rowIndex);

		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclus�o?", "Excluir Dado", JOptionPane.YES_NO_OPTION); // Confirmar Exclus�o
		if (confirm != 0) {
			return;
		}

		int result = new DadosInpeController().excluirDadosInpe(dadosinpe.getId());

		if (result == 1) {
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!"); // Confirma��o Exclus�o 
			atualizarTable(); // Chama o m�todo para atualizar a tabela
		} else {
			JOptionPane.showMessageDialog(this, "Tente novamente!"); // Erro na remo��o 
		}
	}

	private void onAlterarDadosinpe() { // Metado para alterar um item da tabela 
		int rowIndex = table.getSelectedRow(); // Selecionar o item

		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o dado a ser alterado!");
			return;
		}

		DadosInpe dadosinpe = new  DadosInpeModel(dadosinpeList).get(rowIndex); // Campos a serem alterados atrav�s do id  

		idDadosinpe = dadosinpe.getId(); 

		txtAno.setText(dadosinpe.getAno()); // Campos a serem alterados 
		txtMes.setText(dadosinpe.getMes()); // Campos a serem alterados
		txtKm.setText(dadosinpe.getKm()); // Campos a serem alterados

		enableFields(true);
	}
	private void onNovoDadosInpe() { // Metado para deixar os campos limpos  
		enableFields(true);
	}
	public void onSaveDadosInpe() { //Metado para savar um novo item da tabela 
		DadosInpe  da = new DadosInpe(); // Chama a classe Dadosinpe

		// Campos a serem preenchidos 
		if (txtAno.getText().length() > 0 && txtMes.getText().length() > 0 && txtKm.getText().length() > 0) {
			da.setAno(txtAno.getText());
			da.setMes(txtMes.getText());
			da.setKm(txtKm.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos s�o obrigat�rios!"); // Todos os campos devem estar preenchidos 
			return;
		}


		int result = 0;
		if (idDadosinpe == null) {
			result = new DadosInpeController().addDadosInpe(da); // Adiciona os dados nos campos 
		} else {
			da.setId(idDadosinpe);
			result = new DadosInpeController().alterarDadosInpe(da);
			idDadosinpe = null;
		}

		if (result == 1) {
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!"); 
			enableFields(false); // Metado para deixar os campos limpos 
			onCancelar();
			atualizarTable(); // Chama o m�todo para atualizar a tabela
		} else {
			JOptionPane.showMessageDialog(this, "Tente novamente!");
		}
	
	}
	 private void onCancelar() { // M�todo cancelar deixa limpo os campos  
	        txtAno.setText("");
	        txtMes.setText("");
	        txtKm.setText("");
	        enableFields(false);
	    }

	public void enableFields(boolean b) { // Metado para deixar os campos limpos  
		txtAno.setEnabled(b);
		txtMes.setEnabled(b);
		txtKm.setEnabled(b);
	}

	public void atualizarTable() { // Chama o m�todo para atualizar a tabela 
		dadosinpeList = new DadosInpeController().findDadosInpe();
		dadosinpeList = new DadosInpeController().findDadosInpe();
		if(dadosinpeList != null) {
			table.setModel(new DadosInpeModel (dadosinpeList));
			table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()); 
		}
	}
}
