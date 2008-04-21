package blocks;

import java.awt.Color;

public class FinalBlock extends GeneralBlock{
	
	private static final Color actionBlockColor = Color.GREEN;
	
	public FinalBlock(int x, int y, int w, int h, String text) {
		super(x,y,w,h,actionBlockColor,text);
	}
	
}
