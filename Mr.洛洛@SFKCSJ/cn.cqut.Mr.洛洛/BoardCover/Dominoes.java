package BoardCover;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 骨牌实例
 * 
 * @author Mr.洛洛
 * @time 20160113
 */

public class Dominoes {
	private int tr = 0, tc =0,dx = 40; 
	
	public Dominoes(){}
	
	/**
	 * 绘图骨牌
	 */
	public void paintChessBoard(int t1, int t2, int dr,int dc, int size, Graphics g,int width) {
		dx =(int) width/size;
		drawDominoes(t1,t2,dr,dc,size,g);
	}
	
	/**
	 * 描绘某一个骨牌
	 * @param 起点坐标X
	 * @param 起点坐标Y
	 * @param 特殊坐标X
	 * @param 特殊坐标Y
	 * @param 棋盘大小
	 * @param 绘图工具
	 */
	public void drawDominoes(int t1, int t2, int dr,int dc, int size, Graphics g){
		if(size == 1) return;
		size = size/2;
		
		// 产生的一个随机颜色用于填充骨牌
		Color color = new Color((int)(Math.random()*256),
		(int)(Math.random()*256),(int)(Math.random()*256));  
  
		// 判断特殊方格是否在左上角
		inTopLeftCorner(t1, t2, dr, dc, size, g, color);
		
		// 判断特殊方格是否在右上角
		inTopRightCorner(t1, t2, dr, dc, size, g, color);
		
		// 判断特殊方格是否在左下角
		inBottomLeftCorner(t1, t2, dr, dc, size, g, color);
				
		// 判断特殊方格是否在右下角
		inBottomRightCorner(t1, t2, dr, dc, size, g, color);
	}
	
	// 判断特殊方格是否在左上角的情况描绘骨牌
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
	
	// 判断特殊方格是否在右上角的情况描绘骨牌
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
	
	// 判断特殊方格是否在左下角的情况描绘骨牌
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
		
	// 判断特殊方格是否在右下角的情况描绘骨牌
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
