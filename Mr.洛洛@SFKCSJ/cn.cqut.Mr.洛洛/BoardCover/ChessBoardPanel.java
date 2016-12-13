package BoardCover;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * �������
 * 
 * @author Mr.����
 * @time 20160113
 */
public class ChessBoardPanel extends JPanel{

	/**
	 * �汾ID
	 */
	private static final long serialVersionUID = 1L;
	
	// �Ƿ���������Ƶı�־
	private boolean canDrawe = false;

	// tr��tc��ʾ���������ϽǵĿ�ʼ��������ϵ���ʼ�����
	private int tr = 0, tc = 0;
	
	// ���ⷽ�����ڵ�λ��(Ĭ����0��1��)
	private int sR = 0, specialPoC = 1;
	
	// ���̵ļ���
	private int level=2,size;
	
	// ������ÿ������Ŀ��
	private int dx;
	
	public ChessBoardPanel() {
		level=2;
		size = (int) Math.pow(2,level);
		this.setBackground(Color.white);
	}
	
	/**
	 * �����Ƿ�������
	 * @param flag
	 */
	public void setDraw(boolean flag) {
		this.canDrawe = flag;
	}
	
	//���������еķ���(���ⷽ��)
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
			//�������������Լ���L�͹��Ƹ���
			Dominoes chessBoard = new Dominoes();
			chessBoard.paintChessBoard(0,0,sR,specialPoC,size,g,this.getSize().width);
		}else {
			// �հ���ʾ����ͼƬ  
			Font font = new Font("����", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString("���̸�������:", 20, 40);
			
			font = new Font("����", Font.BOLD, 14);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("��һ����k��0����������ɵ������У�ǡ��һ����������������", 50, 80);
			g.drawString("��ͬ���Ƹ÷���Ϊ���ⷽ����Ȼ�����ⷽ���������п��ܳ��ֵ�λ", 20, 100);
			g.drawString("�����֣������4k�ֲ�ͬ�����̣�ͼa��ʾ��k=2ʱ16�������е�һ����", 20, 120);
			g.drawString("���̸������⣨chess cover problem��Ҫ����ͼ (b)��ʾ��4�ֲ�ͬ", 20, 140);
			g.drawString("��״��L�͹��Ƹ��Ǹ��������ϳ����ⷽ����������з������κ�2��", 20, 160);
			g.drawString("L�͹��Ʋ����ص����ǡ�", 20, 180);
			
			Image bg = new ImageIcon("chess1.png").getImage();
			g.drawImage(bg, 30, 210, getWidth()*4/5, 220, null);
			
			bg = new ImageIcon("chess3.png").getImage();
			g.drawImage(bg, 350, 185, getWidth()/4,  getWidth()/4, null);
		}
	}
	
	/**
	 * �������̲��ֵķ���
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
