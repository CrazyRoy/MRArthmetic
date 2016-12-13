package GeneSequenceComparison;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * ����Ƚ���ʾ���
 * 
 * @author Mr.����
 * @time 20160111
 */

public class GeneComparisonFrame extends JFrame{
	/**
	 * �汾ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * blank
	 */
	private static final String blank = "     ";
	
	/**
	 * UI���
	 * @param args
	 */
	private ScoreMatrixPanel scoreMatrix;
	private GeneComparsionPanel genePanel;
	private JPanel panel;
	
	private JPanel panel1;
	private JLabel lab_FirstGene;	
	private JLabel lab_SecondGene;
	private JTextField text_FirstGene;
	private JTextField text_SecondGene;
	
	private JPanel panel2;
	private JLabel lab_FirstGeneAligment;
	private JLabel lab_SecondGeneAligment;
	private JLabel lab_LCS;
	private JLabel lab_Value;
	
	private JPanel panel3;
	private JButton btn_Comparison;
	private JButton btn_Reset;
	
	private Gene gene1, gene2;
	
	public GeneComparisonFrame() {
		// ׼��UI
		this.prepareUI();
		// ����¼�
		this.addActions();
	}
	
	// ׼��UI����
	private void prepareUI() {
		setLayout(new BorderLayout());
		
		//panel_rec
		this.scoreMatrix = new ScoreMatrixPanel();
		add(this.scoreMatrix, BorderLayout.CENTER);
		
		// panel
		this.panel = new JPanel();
		this.panel.setLayout(new GridLayout(3, 1));
		// ���ñ߿�
		this.panel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		
		// panel1
		this.panel1 = new JPanel();
		// ���û�������
		this.panel1.setLayout(new GridLayout(3, 2));
		JLabel label = new JLabel(blank + "ѡ��: ps(�������кű����Ǵ�д  'A' 'T' 'C' 'G' �ַ���ɵ�Ŷ)");
		label.setForeground(Color.red);
		this.panel1.add(label);
		this.panel1.add(new JLabel("ʱ�临�Ӷ�Ϊ��O(mn)"));
		
		this.lab_FirstGene = new JLabel(blank + "The First GeneSequence : ");
		this.panel1.add(this.lab_FirstGene);
		this.text_FirstGene = new JTextField("AGTGATG");
		this.panel1.add(this.text_FirstGene);
		
		this.lab_SecondGene = new JLabel(blank + "The Second GeneSequence : ");
		this.panel1.add(this.lab_SecondGene);
		this.text_SecondGene = new JTextField("GTTAG");
		this.panel1.add(this.text_SecondGene);
		this.panel.add(this.panel1);
		
		// panel2
		this.panel2 = new JPanel();
		this.panel2.setLayout(new GridLayout(3, 2));
		JLabel result = new JLabel(blank + "���:");
		result.setForeground(Color.blue);
		this.panel2.add(result);
		this.panel2.add(new JLabel());
		
		this.lab_FirstGeneAligment = new JLabel(blank + blank + "����1�������У�");
		this.panel2.add(this.lab_FirstGeneAligment);
		this.lab_SecondGeneAligment = new JLabel("����2�������У�");
		this.panel2.add(this.lab_SecondGeneAligment);
		this.lab_LCS = new JLabel(blank + blank + "����������У�");
		this.panel2.add(this.lab_LCS);
		this.lab_Value = new JLabel("ƥ���ֵ:");
		this.panel2.add(this.lab_Value);
		this.panel.add(this.panel2);
		
		// panel3
		this.panel3 = new JPanel();
		this.panel3.setLayout(new GridLayout(3, 1));
		this.panel3.add(new JLabel());
		JPanel  temp = new JPanel();
		temp.setLayout(new GridLayout(1, 5));
		temp.add(new JLabel());
		this.btn_Comparison = new JButton("��������ֵ");
		this.btn_Comparison.setBackground(Color.orange);
		this.btn_Comparison.setForeground(Color.blue);
		temp.add(this.btn_Comparison);
		temp.add(new JLabel());
		this.btn_Reset = new JButton("���û�������");
		this.btn_Reset.setBackground(Color.gray);
		this.btn_Reset.setForeground(Color.red);
		this.btn_Reset.setEnabled(false);
		temp.add(this.btn_Reset);
		temp.add(new JLabel());
		this.panel3.add(temp);
		this.panel.add(this.panel3);
		add(this.panel, BorderLayout.SOUTH);
	}
	
	/**
	 * ��ӵ���¼�����
	 */
	private void addActions() {
		// �ύ��ť
		this.btn_Comparison.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(text_FirstGene.getText().length() == 0 || text_SecondGene.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "�������кŲ���Ϊ�գ�����");
				}else {
					gene1 = new Gene(text_FirstGene.getText());
					gene2 = new Gene(text_SecondGene.getText());
					if(gene1.checkGeneFormat() && gene2.checkGeneFormat()) {
						btn_Comparison.setEnabled(false);
						btn_Reset.setEnabled(true);
						genePanel = new GeneComparsionPanel(text_FirstGene.getText(), text_SecondGene.getText());
						lab_FirstGeneAligment.setText(blank + blank + "����1�������У�" + genePanel.gene1.toString());
						lab_SecondGeneAligment.setText("����2�������У�" + genePanel.gene2.toString());
						lab_LCS.setText(blank + blank + "����������У�" + genePanel.geneLCS.toString());
						lab_Value.setText(String.valueOf("ƥ���ֵ��" + genePanel.value));
						scoreMatrix.setPoints(genePanel.getPoints());
						scoreMatrix.setArrayResult(genePanel.getArrayResult());
					}else {
						JOptionPane.showMessageDialog(null, "�������к���������!!!");
					}
				}
			}
		});
		
		// ���ð�ť
		this.btn_Reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_Comparison.setEnabled(true);
				btn_Reset.setEnabled(false);
				text_FirstGene.setText("");
				text_SecondGene.setText("");
				lab_FirstGeneAligment.setText(blank + blank + "����1�������У�");
				lab_SecondGeneAligment.setText("����2�������У�");
				lab_LCS.setText(blank + blank + "����������У�");
				lab_Value.setText("ƥ���ֵ��");
				scoreMatrix.setDrawArray(false);
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
}
