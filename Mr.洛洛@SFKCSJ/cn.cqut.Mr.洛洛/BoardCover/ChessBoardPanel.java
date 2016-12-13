package BoardCover;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * 棋盘面板
 * 
 * @author Mr.洛洛
 * @time 20160113
 */
public class ChessBoardPanel extends JPanel{

	/**
	 * 版本ID
	 */
	private static final long serialVersionUID = 1L;
	
	// 是否可以描绘骨牌的标志
	private boolean canDrawe = false;

	// tr、tc表示棋盘在左上角的开始的在面板上的起始坐标点
	private int tr = 0, tc = 0;
	
	// 特殊方格所在的位置(默认在0行1列)
	private int sR = 0, specialPoC = 1;
	
	// 棋盘的级数
	private int level=2,size;
	
	// 棋盘中每个方格的宽高
	private int dx;
	
	public ChessBoardPanel() {
		level=2;
		size = (int) Math.pow(2,level);
		this.setBackground(Color.white);
	}
	
	/**
	 * 设置是否描绘骨牌
	 * @param flag
	 */
	public void setDraw(boolean flag) {
		this.canDrawe = flag;
	}
	
	//绘制棋盘中的方格(特殊方格)
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(canDrawe) {
			dx =(int)this.getSize().width/size;
			g.setColor(Color.white);
			g.drawLine(tr+ specialPoC*dx, tc + sR*dx, tr+ specialPoC*dx+dx, tc + sR*dx);
			g.drawLine(tr+ specialPoC*dx, tc + sR*dx, tr+ specialPoC*dx, tc + sR*dx+dx);
			g.setColor(Color.black);
			g.fillRect(tr+ specialPoC*dx,tc + sR*dx,dx,dx);
			//绘制其他方格以及用L型骨牌覆盖
			Dominoes chessBoard = new Dominoes();
			chessBoard.paintChessBoard(0,0,sR,specialPoC,size,g,this.getSize().width);
		}else {
			// 空白显示背景图片  
			Font font = new Font("楷体", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString("棋盘覆盖问题:", 20, 40);
			
			font = new Font("楷体", Font.BOLD, 14);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("在一个（k≥0）个方格组成的棋盘中，恰有一个方格与其他方格", 50, 80);
			g.drawString("不同，称该方格为特殊方格。显然，特殊方格在棋盘中可能出现的位", 20, 100);
			g.drawString("置有种，因而有4k种不同的棋盘，图a所示是k=2时16种棋盘中的一个。", 20, 120);
			g.drawString("棋盘覆盖问题（chess cover problem）要求用图 (b)所示的4种不同", 20, 140);
			g.drawString("形状的L型骨牌覆盖给定棋盘上除特殊方格以外的所有方格，且任何2个", 20, 160);
			g.drawString("L型骨牌不得重叠覆盖。", 20, 180);
			
			Image bg = new ImageIcon("chess1.png").getImage();
			g.drawImage(bg, 30, 210, getWidth()*4/5, 220, null);
			
			bg = new ImageIcon("chess3.png").getImage();
			g.drawImage(bg, 350, 185, getWidth()/4,  getWidth()/4, null);
		}
	}
	
	/**
	 * 画出棋盘布局的方法
	 * @param size
	 * @param special_x
	 * @param special_y
	 */
	public void paintChessBoard(String text1,String text2,String text3) {
		size = (int) Math.pow(2,Integer.parseInt(text1));
		sR = Integer.parseInt(text2);
		specialPoC = Integer.parseInt(text3);
		this.canDrawe = true;
		repaint();
	}
}
