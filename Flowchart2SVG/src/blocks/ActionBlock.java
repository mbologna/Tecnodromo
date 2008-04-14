package blocks;

import java.awt.Color;
import java.util.List;

public class ActionBlock extends GeneralBlock{
	
	private final Color actionBlockColor = Color.BLUE;
	
	public ActionBlock(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	public List getBlockDescr() {
		List l = super.getBlockCoord();
		l.add(Color.BLUE);
		
	}
}
