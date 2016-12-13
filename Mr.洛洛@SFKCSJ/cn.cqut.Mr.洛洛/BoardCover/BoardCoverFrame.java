package BoardCover;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoardCoverFrame extends JFrame{

	/**
	 * 版本ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * UI组件
	 */
	private ChessBoardPanel chessBoardPanel;
	private JPanel operationPanel;
	
	private JLabel lab_level;
	private JTextField text_level;
	
	private JLabel lab_specialLocation;
	private JLabel lab_specialX;
	private JLabel lab_specialY;
	private JTextField text_x;
	private JTextField text_y;
	
	private JButton btn_chess;
	private JButton btn_exit;
	private JButton lab_head;
	
	// 算法时间复杂度
	private JLabel lab_complexity;
	
	/**
	 * 构造方法
	 */
	public BoardCoverFrame() {
		// 准备UI
		prepareUI();
		// 添加事件监听
		addActions();
	}
	
	/**
	 * 准备UI
	 */
	private void prepareUI() {
		
		this.chessBoardPanel = new ChessBoardPanel();
		add(this.chessBoardPanel, BorderLayout.CENTER);
		
		this.operationPanel = new JPanel();
		this.operationPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		// 创建一个box
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(10));
		
		JPanel panel = new JPanel();
		ImageIcon head = new ImageIcon("icon.jpg");
		this.lab_head = new JButton();
		this.lab_head.setBackground(Color.white);
		this.lab_head.setBorder(null);
		this.lab_head.setIcon((Icon)head);
		this.lab_head.setBounds(5, 5, 20, 20);
		panel.add(this.lab_head);
		box.add(panel);
		
		panel = new JPanel();
		JLabel account = new JLabel("昵称：Mr.洛洛");
		account.setForeground(Color.blue);
		panel.add(account);
		box.add(panel);
		
		box.add(Box.createVerticalStrut(10));
		panel = new JPanel();
		this.lab_complexity = new JLabel("算法时间复杂度：");
		JLabel lab = new JLabel("O(4^K)");
		lab.setForeground(Color.red);
		panel.add(this.lab_complexity);
		panel.add(lab);
		box.add(panel);
		box.add(Box.createVerticalStrut(15));
		
		panel = new JPanel();
		this.lab_level = new JLabel("棋盘规模size：     ");
		this.lab_level.setForeground(Color.red);
		this.text_level = new JTextField("2", 5);
		panel.add(this.lab_level);
		panel.add(this.text_level);
		box.add(panel);
		box.add(Box.createVerticalStrut(15));
		
		panel = new JPanel();
		this.lab_specialLocation = new JLabel("特殊方格位置：         ");
		panel.add(this.lab_specialLocation);
		panel.add(new JLabel("      "));
		box.add(panel);
		
		panel = new JPanel();
		this.lab_specialX = new JLabel("X坐标：      ");
		this.lab_specialX.setForeground(Color.red);
		this.text_x = new JTextField("1", 5);;
		panel.add(this.lab_specialX);
		panel.add(this.text_x);
		box.add(panel);
		
		panel = new JPanel();
		this.lab_specialY = new JLabel("Y坐标：      ");
		this.lab_specialY.setForeground(Color.red);
		this.text_y = new JTextField("1", 5);
		panel.add(this.lab_specialY);
		panel.add(this.text_y);
		box.add(panel);
		box.add(Box.createVerticalStrut(15));
		
		panel = new JPanel();
		this.btn_chess = new JButton("显示棋盘覆盖");
		this.btn_chess.setForeground(Color.blue);
		this.btn_chess.setBackground(Color.orange);
		panel.add(this.btn_chess);
		box.add(panel);
		box.add(Box.createVerticalStrut(10));
		
		panel = new JPanel();
		this.btn_exit = new JButton("退出棋盘覆盖");
		this.btn_exit.setForeground(Color.red);
		this.btn_exit.setBackground(Color.gray);
		this.btn_exit.setEnabled(false);
		panel.add(this.btn_exit);
		box.add(panel);
		
		panel = new JPanel();
		this.operationPanel.add(box);
		add(this.operationPanel, BorderLayout.EAST);
	}
	
	/**
	 * 添加事件监听
	 */
	private void addActions() {
		this.lab_head.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "copyRight: 11303080304---罗建华");
			}
		});
		// 显示棋盘覆盖的点击事件
		this.btn_chess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(text_level == null || text_x == null || text_y == null || text_x.getText().trim().length() == 0 || text_y.getText().trim().length() == 0 || text_level.getText().trim().length() == 0 || (Integer.parseInt(text_level.getText().trim())<=0))  {
					JOptionPane.showMessageDialog(null, "您的输入有误！！！(不能为空哦)");
				}else {
					chessBoardPanel.paintChessBoard(text_level.getText().trim(), text_x.getText().trim(), text_y.getText().trim());
					btn_chess.setEnabled(false);
					btn_exit.setEnabled(true);
				}
			}
		});
		
		// 退出棋盘覆盖的点击事件
		this.btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_exit.setEnabled(false);
				btn_chess.setEnabled(true);
				chessBoardPanel.setDraw(false);
				chessBoardPanel.repaint();	// 重画
			}
		});
	}
}
