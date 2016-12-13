package BoardCover;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ����ʵ��
 * 
 * @author Mr.����
 * @time 20160113
 */

public class Dominoes {
	private int tr = 0, tc =0,dx = 40; 
	
	public Dominoes(){}
	
	/**
	 * ��ͼ����
	 */
	public void paintChessBoard(int t1, int t2, int dr,int dc, int size, Graphics g,int width) {
		dx =(int) width/size;
		drawDominoes(t1,t2,dr,dc,size,g);
	}
	
	/**
	 * ���ĳһ������
	 * @param �������X
	 * @param �������Y
	 * @param ��������X
	 * @param ��������Y
	 * @param ���̴�С
	 * @param ��ͼ����
	 */
	public void drawDominoes(int t1, int t2, int dr,int dc, int size, Graphics g){
		if(size == 1) return;
		size = size/2;
		
		// ������һ�������ɫ����������
		Color color = new Color((int)(Math.random()*256),
		(int)(Math.random()*256),(int)(Math.random()*256));  
  
		// �ж����ⷽ���Ƿ������Ͻ�
		inTopLeftCorner(t1, t2, dr, dc, size, g, color);
		
		// �ж����ⷽ���Ƿ������Ͻ�
		inTopRightCorner(t1, t2, dr, dc, size, g, color);
		
		// �ж����ⷽ���Ƿ������½�
		inBottomLeftCorner(t1, t2, dr, dc, size, g, color);
				
		// �ж����ⷽ���Ƿ������½�
		inBottomRightCorner(t1, t2, dr, dc, size, g, color);
	}
	
	// �ж����ⷽ���Ƿ������Ͻǵ����������
	private void inTopLeftCorner(int t1, int t2, int dr,int dc, int size, Graphics g, Color color) {
		if(dr<t1+size && dc<t2+size)
			drawDominoes(t1,t2,dr,dc,size,g);
		else{
			g.setColor(Color.white);
			g.drawLine(tr+(t2+size-1)*dx, tc+(t1+size-1)*dx, tr+(t2+size-1)*dx+dx,tc+(t1+size-1)*dx);
			g.drawLine(tr+(t2+size-1)*dx, tc+(t1+size-1)*dx, tr+(t2+size-1)*dx,tc+(t1+size-1)*dx+dx);
			
			g.setColor(color);
			g.fillRect(tr+(t2+size-1)*dx,tc+(t1+size-1)*dx,dx,dx);
			drawDominoes(t1,t2,t1+size-1,t2+size-1,size,g);
		}
	}
	
	// �ж����ⷽ���Ƿ������Ͻǵ����������
	private void inTopRightCorner(int t1, int t2, int dr,int dc, int size, Graphics g, Color color) {
		if(dr<t1+size && dc>=t2+size)
			drawDominoes(t1,t2+size,dr,dc,size,g);
		else{
			g.setColor(Color.white);
			g.drawLine(tr+(t2+size)*dx, tc+(t1+size-1)*dx, tr+(t2+size)*dx+dx,tc+(t1+size-1)*dx);
			g.drawLine(tr+(t2+size)*dx, tc+(t1+size-1)*dx, tr+(t2+size)*dx,tc+(t1+size-1)*dx+dx);
			
			g.setColor(color);
			g.fillRect(tr+(t2+size)*dx,tc+(t1+size-1)*dx,dx,dx);
			drawDominoes(t1,t2+size,t1+size-1,t2+size,size,g);
		}
	}
	
	// �ж����ⷽ���Ƿ������½ǵ����������
	private void inBottomLeftCorner(int t1, int t2, int dr,int dc, int size, Graphics g, Color color) {
		if(dr>=t1+size && dc<t2+size)
			drawDominoes(t1+size,t2,dr,dc,size,g);
		else{
			g.setColor(Color.white);
			g.drawLine(tr+(t2+size-1)*dx, tc+(t1+size)*dx, tr+(t2+size-1)*dx+dx,tc+(t1+size)*dx);
			g.drawLine(tr+(t2+size-1)*dx, tc+(t1+size)*dx, tr+(t2+size-1)*dx,tc+(t1+size)*dx+dx);
			
			g.setColor(color);
			g.fillRect(tr+(t2+size-1)*dx,tc+(t1+size)*dx,dx,dx);
			drawDominoes(t1+size,t2,t1+size,t2+size-1,size,g);
		}
	}
		
	// �ж����ⷽ���Ƿ������½ǵ����������
	private void inBottomRightCorner(int t1, int t2, int dr,int dc, int size, Graphics g, Color color) {
		if(dr>=t1+size && dc>=t2+size)
			drawDominoes(t1+size,t2+size,dr,dc,size,g);
		else{
			g.setColor(Color.white);
			g.drawLine(tr+(t2+size)*dx, tc+(t1+size)*dx, tr+(t2+size)*dx+dx,tc+(t1+size)*dx);
			g.drawLine(tr+(t2+size)*dx, tc+(t1+size)*dx, tr+(t2+size)*dx,tc+(t1+size)*dx+dx);
			
			g.setColor(color);
			g.fillRect(tr+(t2+size)*dx,tc+(t1+size)*dx,dx,dx);
			drawDominoes(t1+size,t2+size,t1+size,t2+size,size,g);
		}
	}
}
