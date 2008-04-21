package blocks;

import java.awt.Color;

public class IOBlock extends GeneralBlock{
	
	private static final Color actionBlockColor = Color.BLUE;
	
	public IOBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,actionBlockColor,text);
	}
	
}
