package GeneSequenceComparison;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 基因比较显示面板
 * 
 * @author Mr.洛洛
 * @time 20160111
 */

public class GeneComparisonFrame extends JFrame{
	/**
	 * 版本ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * blank
	 */
	private static final String blank = "     ";
	
	/**
	 * UI组件
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
		// 准备UI
		this.prepareUI();
		// 添加事件
		this.addActions();
	}
	
	// 准备UI界面
	private void prepareUI() {
		setLayout(new BorderLayout());
		
		//panel_rec
		this.scoreMatrix = new ScoreMatrixPanel();
		add(this.scoreMatrix, BorderLayout.CENTER);
		
		// panel
		this.panel = new JPanel();
		this.panel.setLayout(new GridLayout(3, 1));
		// 设置边框
		this.panel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		
		// panel1
		this.panel1 = new JPanel();
		// 设置画布布局
		this.panel1.setLayout(new GridLayout(3, 2));
		JLabel label = new JLabel(blank + "选项: ps(基因序列号必须是大写  'A' 'T' 'C' 'G' 字符组成的哦)");
		label.setForeground(Color.red);
		this.panel1.add(label);
		this.panel1.add(new JLabel("时间复杂度为：O(mn)"));
		
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
		JLabel result = new JLabel(blank + "结果:");
		result.setForeground(Color.blue);
		this.panel2.add(result);
		this.panel2.add(new JLabel());
		
		this.lab_FirstGeneAligment = new JLabel(blank + blank + "基因1序列排列：");
		this.panel2.add(this.lab_FirstGeneAligment);
		this.lab_SecondGeneAligment = new JLabel("基因2序列排列：");
		this.panel2.add(this.lab_SecondGeneAligment);
		this.lab_LCS = new JLabel(blank + blank + "最长公共子序列：");
		this.panel2.add(this.lab_LCS);
		this.lab_Value = new JLabel("匹配分值:");
		this.panel2.add(this.lab_Value);
		this.panel.add(this.panel2);
		
		// panel3
		this.panel3 = new JPanel();
		this.panel3.setLayout(new GridLayout(3, 1));
		this.panel3.add(new JLabel());
		JPanel  temp = new JPanel();
		temp.setLayout(new GridLayout(1, 5));
		temp.add(new JLabel());
		this.btn_Comparison = new JButton("计算相似值");
		this.btn_Comparison.setBackground(Color.orange);
		this.btn_Comparison.setForeground(Color.blue);
		temp.add(this.btn_Comparison);
		temp.add(new JLabel());
		this.btn_Reset = new JButton("重置基因序列");
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
	 * 添加点击事件处理
	 */
	private void addActions() {
		// 提交按钮
		this.btn_Comparison.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(text_FirstGene.getText().length() == 0 || text_SecondGene.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "基因序列号不能为空！！！");
				}else {
					gene1 = new Gene(text_FirstGene.getText());
					gene2 = new Gene(text_SecondGene.getText());
					if(gene1.checkGeneFormat() && gene2.checkGeneFormat()) {
						btn_Comparison.setEnabled(false);
						btn_Reset.setEnabled(true);
						genePanel = new GeneComparsionPanel(text_FirstGene.getText(), text_SecondGene.getText());
						lab_FirstGeneAligment.setText(blank + blank + "基因1序列排列：" + genePanel.gene1.toString());
						lab_SecondGeneAligment.setText("基因2序列排列：" + genePanel.gene2.toString());
						lab_LCS.setText(blank + blank + "最长公共子序列：" + genePanel.geneLCS.toString());
						lab_Value.setText(String.valueOf("匹配分值：" + genePanel.value));
						scoreMatrix.setPoints(genePanel.getPoints());
						scoreMatrix.setArrayResult(genePanel.getArrayResult());
					}else {
						JOptionPane.showMessageDialog(null, "基因序列号输入有误!!!");
					}
				}
			}
		});
		
		// 重置按钮
		this.btn_Reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_Comparison.setEnabled(true);
				btn_Reset.setEnabled(false);
				text_FirstGene.setText("");
				text_SecondGene.setText("");
				lab_FirstGeneAligment.setText(blank + blank + "基因1序列排列：");
				lab_SecondGeneAligment.setText("基因2序列排列：");
				lab_LCS.setText(blank + blank + "最长公共子序列：");
				lab_Value.setText("匹配分值：");
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
