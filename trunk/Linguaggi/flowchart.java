import it.unibg.cs.flowchart2svg.Flowchart2SVG;
import it.unibg.cs.flowchart2svg.blocks.ActionBlock;
import it.unibg.cs.flowchart2svg.blocks.ControlBlock;
import it.unibg.cs.flowchart2svg.blocks.FinalBlock;
import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;
import it.unibg.cs.flowchart2svg.blocks.IOBlock;
import it.unibg.cs.flowchart2svg.blocks.InitialBlock;
import it.unibg.cs.flowchart2svg.labels.Label;
import it.unibg.cs.flowchart2svg.links.Arrow;
import it.unibg.cs.flowchart2svg.links.Line;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


import org.apache.batik.svggen.SVGGraphics2DIOException;

public class flowchart {
	final static int blockWidth=70;
	final static int blockHeight=50;
	final static int border=20;
	final static int ifSpace=20;
	final static int blockDistance=15;


	static Vector <Group> group;

	double x,y;
	static public Group fatherTemp;

	int b_x,b_y,b_w,b_h,b_w_l,shiftX,shiftY;
	String b_nome;
	/*
	 * Parte grafica...
	 */
	List<GeneralBlock> blocks = new LinkedList<GeneralBlock>();
	List<Line> links = new LinkedList<Line>();
	List<Label> labels = new LinkedList<Label>();



	public flowchart() throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException
	{

		group=new Vector<Group>();

		Group group_insert=new Group("g1", true, "center", "main", "init", null);
		group.add(group_insert);
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		group.get(0).setIstr(new Istruction("ifdiprova","rombo","ciao","g2"));
		group.get(0).setIstr(new Istruction("last","mao","1ciao"));
		
		//group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		//group.get(0).setIstr(new Istruction("last","romboide","1ciao"));
		//group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));


		group_insert=new Group("g2", true, "center", "if(io=io)", "if", "g1");
		group.add(group_insert);


		group_insert=new Group("g3", true, "left", "", "init", "g2");
		group.add(group_insert);
		group.get(2).setIstr(new Istruction("G3_1","gsfd","ciao"));
		//group.get(2).setIstr(new Istruction("g3_2","romboide","ciao"));
		//group.get(2).setIstr(new Istruction("g3_3","gsfd","ciao"));

		group_insert=new Group("g4", true, "right", "while(io=io)", "init", "g2");
		group.add(group_insert);
		group.get(3).setIstr(new Istruction("g4_1","gsfd","ciao"));
		/*group.get(3).setIstr(new Istruction("g4_2","romboide","ciao"));
		group.get(3).setIstr(new Istruction("ifdi2","rombo","ciao","g5"));



		group_insert=new Group("g5", true, "center", "while(io=io)", "while", "g4");
		group.add(group_insert);


		group_insert=new Group("g6", true, "center", "", "init", "g5");
		group.add(group_insert);
		//group.get(5).setIstr(new Istruction("ifdg7","rombo","ciao","g7"));
		group.get(5).setIstr(new Istruction("g6_1","gsfd","ciao"));
		group.get(5).setIstr(new Istruction("g6_2","gsfd","ciao"));*/


		/*group_insert=new Group("g7", true, "center", "dowhile", "dowhile", "g6");
		group.add(group_insert);


		group_insert=new Group("g8", true, "center", "", "init", "g7");
		group.add(group_insert);
		group.get(7).setIstr(new Istruction("G3_1","gsfd","ciao"));
		group.get(7).setIstr(new Istruction("g3_2","romboide","ciao"));
		group.get(7).setIstr(new Istruction("g3_3","gsfd","ciao"));*/

		GeneralBlock a1;

		for(int i=0;i<group.size();i++)
		{


			//blockType: action , if , while , for , dowhile
			if((group.get(i)).getGroupType().equals("while") || (group.get(i)).getGroupType().equals("for")|| (group.get(i)).getGroupType().equals("dowhile"))
			{
				calculateXY(group.get(i));
				(group.get(i)).setDimension((blockWidth/2)+border, (blockWidth/2)+border, blockDistance*4+blockHeight, x, y);
			}

			if((group.get(i)).getGroupType().equals("if"))
			{
				calculateXY(group.get(i));
				//dLEft 75  dRight 75; modifico (blockWidth/2)+border+ifSpace in (blockWidth/2)+border
				(group.get(i)).setDimension((blockWidth/2)+border, (blockWidth/2)+border, blockDistance+blockHeight/2, x, y);
			}

			if((group.get(i)).getGroupType().equals("init") )
			{
				calculateXY(group.get(i));

				int k=group.get(i).getIstr().size();
				int j=0;
				if(k!=0)
				{	
					for(int h=0;h<k;h++)
					{
						if(((Istruction)(group.get(i).getIstr().get(h))).getMyGroup()==null) 
							j++;
					}
				}

				(group.get(i)).setDimension((blockWidth/2)+border, (blockWidth/2)+border, (blockDistance*(j+1)+blockHeight*j), x, y);

			}

			//ricorsione
			ancestorsResize(group.get(i));
		}


		//aggiorno la posizione x dei blocchi visto che le dimensioni sono gia settate correttamente
		for(int j=0;j<group.size();j++)
		{

			for(int z=j+1;z<group.size();z++)
			{

				if(group.get(z).getFather().equals(group.get(j).getName())) //cerco i figli
				{

					// modi di spostare la x in base alla posizione left,right o center rispetto al blocco padre
					if(group.get(z).getPosition().equals("right"))
					{

						//setto la posizione x del figlio come la x del padre + metà della lunghezza destra del padre
						group.get(z).getDimension().setx(group.get(j).getDimension().getX()+(group.get(j).getDimension().getdR()/2));

					}
					if(group.get(z).getPosition().equals("left"))
					{
						//setto la posizione x del figlio come la x del padre + metà della lunghezza destra del padre
						group.get(z).getDimension().setx(group.get(j).getDimension().getX()-(group.get(j).getDimension().getdL()/2));
					}
					if(group.get(z).getPosition().equals("center"))
					{
						//setto la posizione x del figlio come la x del padre + metà della lunghezza destra del padre
						group.get(z).getDimension().setx(group.get(j).getDimension().getX());
					}
				}

			}

		}

		int l_x1 , l_y1, l_x2,l_y2,r_x1 , r_y1, r_x2,r_y2;
		//	setto lo shift per le x e y
		shiftY=100;
		shiftX=(int) (group.get(0).getDimension().getdL()+20);

		for(int i=0;i<group.size();i++)
		{

			b_x=(int) group.get(i).getDimension().getX()+shiftX;
			b_y=(int) group.get(i).getDimension().getY()+ shiftY;
			b_h=(int) group.get(i).getDimension().getdH();
			b_w_l =(int) (group.get(i).getDimension().getdL());
			b_w=(int) (group.get(i).getDimension().getdL()+group.get(i).getDimension().getdR());
			b_nome="gruppo "+(i+1);

			/*
			 * Implementazione grafica del contenuto dei gruppi 
			 */

		}

		/*
		 * fine grafica gruppi
		 */

		for(int i=0;i<group.size();i++)
		{
			if(group.get(i).getGroupType().equals("init"))
			{
				replaceGroup_y(group.get(i));
			}
		}

		//---stampa dimensioni -------
		for(int i=0;i<group.size();i++)
		{
			System.out.println("-- Gruppo "+(i+1));
			System.out.println("Altezza:"+ group.get(i).getDimension().getdH());
			System.out.println("Left:"+ group.get(i).getDimension().getdL());
			System.out.println("Right:"+ group.get(i).getDimension().getdR());
			System.out.println("X :"+ group.get(i).getDimension().getX());
			System.out.println("Y :"+ group.get(i).getDimension().getY());


			b_x=(int) group.get(i).getDimension().getX()+shiftX;
			b_y=(int) group.get(i).getDimension().getY()+ shiftY;
			b_h=(int) group.get(i).getDimension().getdH();
			b_w_l =(int) (group.get(i).getDimension().getdL());
			b_w=(int) (group.get(i).getDimension().getdL()+group.get(i).getDimension().getdR());
			b_nome="gruppo "+(i+1);

			/*
			 * Creo i blocchi. Specifico (x,y,w,h,testo)
			 */
			a1 = new ActionBlock(b_x-b_w_l,b_y,b_w,b_h,b_nome);
			if(i==0)
				a1.setBlockColor(Color.MAGENTA);
			if(i==1)
				a1.setBlockColor(Color.GREEN);
			if(i==2)
				a1.setBlockColor(Color.YELLOW);
			if(i==3)
				a1.setBlockColor(Color.RED);
			if(i==4)
				a1.setBlockColor(Color.CYAN);
			if(i==5)
				a1.setBlockColor(Color.GRAY);
			if(i==6)
				a1.setBlockColor(Color.PINK);
			blocks.add(a1);


			//devo solo instanziare i blocchi perchè il gruppo dove sono contenuti l'ho instanziato appena sopra
			if(group.get(i).getGroupType().equals("init"))
			{
				int k=group.get(i).getIstr().size();
				int actual_y=b_y+blockDistance;
				if(k!=0)
				{	
					for(int h=0;h<k;h++)
					{
						if(((Istruction)(group.get(i).getIstr().get(h))).getMyGroup()==null) 
						{
							if(((Istruction)(group.get(i).getIstr().get(h))).getShape().equals("romboide"))
								a1 = new IOBlock(b_x-blockWidth/2,actual_y,blockWidth,blockHeight,((Istruction)(group.get(i).getIstr().get(h))).getText());
							else
								a1 = new ActionBlock(b_x-blockWidth/2,actual_y,blockWidth,blockHeight,((Istruction)(group.get(i).getIstr().get(h))).getText());	
							blocks.add(a1);
							//linea sopra blocco
							links.add(new Line((b_x),actual_y,(b_x),actual_y-blockDistance));
							//linea sotto blocco
							links.add(new Line((b_x),actual_y+blockHeight,(b_x),actual_y+blockHeight+blockDistance));
							actual_y+=blockHeight+blockDistance;
						}
						else
						{
							actual_y+=(stringToGroup(((Istruction)(group.get(i).getIstr().get(h))).getMyGroup())).getDimension().getdH()+blockDistance;
						}
					}
				}
			}

			if(group.get(i).getGroupType().equals("if"))
			{
				int x1 , y1, x2,y2,x_temp;
				a1 = new ControlBlock(b_x-blockWidth/2,b_y+blockDistance,blockWidth,blockHeight,group.get(i).getHeader());
				blocks.add(a1);
				x1=x2=b_x;
				y1=b_y;
				y2=b_y+blockDistance;
				links.add(new Line(x1,y1,x2,y2));
				x1=b_x-blockWidth/2;
				y1=b_y+blockHeight/2+blockDistance;
				x2=x1-(int)((group.get(i).getDimension().getdL())/2)+blockWidth/2;
				y2=y1+blockHeight/2;
				links.add(new Line(x1,y1,x2,y1));
				links.add(new Line(x2,y1,x2,y2));

				y1=bottom_y(group.get(i),"left")+y2;
				y2=b_y+b_h;
				links.add(new Line(x2,y1,x2,y2));
				x_temp=x2;			
				x1=b_x+blockWidth/2;
				y1=b_y+blockHeight/2+blockDistance;
				x2=x1+(int)((group.get(i).getDimension().getdR())/2)-blockWidth/2;
				y2=y1+blockHeight/2;
				links.add(new Line(x1,y1,x2,y1));
				links.add(new Line(x2,y1,x2,y2));

				y1=bottom_y(group.get(i),"right")+y2;
				y2=b_y+b_h;
				links.add(new Line(x2,y1,x2,y2));
				links.add(new Line(x_temp,y2,x2,y2));
				//mancano collegamenti dai figli alla fine del blocco
			}

			if(group.get(i).getGroupType().equals("for")||group.get(i).getGroupType().equals("while"))
			{
				int x1 , y1, x2,y2;
				a1 = new ControlBlock(b_x-blockWidth/2,b_y+blockDistance,blockWidth,blockHeight,group.get(i).getHeader());
				blocks.add(a1);
				x1=b_x;
				y1=b_y;
				y2=y1+blockDistance;
				links.add(new Line(x1,y1,x1,y2));
				y2+=blockHeight/2;
				x1+=blockWidth/2;
				x2=(int) (b_x+group.get(i).getDimension().getdR());
				links.add(new Line(x1,y2,x2,y2));
				y1=(int) (b_y+group.get(i).getDimension().getdH());
				links.add(new Line(x2,y2,x2,y1));
				x1=b_x;
				links.add(new Line(x2,y1,x1,y1));
			}

			if(group.get(i).getGroupType().equals("dowhile"))
			{
				int x1 , y1, x2,y2;
				a1 = new ControlBlock(b_x-blockWidth/2,b_y+b_h-blockHeight,blockWidth,blockHeight,group.get(i).getHeader());
				blocks.add(a1);
				y1=b_y+b_h-blockHeight/2;
				x1=b_x-blockWidth/2;
				x2=(int) (b_x-group.get(i).getDimension().getdL());
				links.add(new Line(x1,y1,x2,y1));
				y2=b_y;
				links.add(new Line(x2,y1,x2,y2));
				x1=(int) (x2+group.get(i).getDimension().getdL());
				links.add(new Line(x2,y2,x1,y2));
				y1=y2+blockDistance;
				links.add(new Line(x1,y2,x1,y1));

			}

			//collegamenti tra i blocchi CHE NON VA UN OSTI CAZZO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			/*int x1=0 , y1=0, x2=0,y2=0;
			for(int j=0;j<group.get(i).getIstr().size();j++)
			{
				if(((Istruction)(group.get(i).getIstr().get(j))).getMyGroup()==null)  
				{

					if(j==0)
					{
						x1=b_x;
						y1=b_y;
						y2=y1+blockDistance;
					}
					else
					{y1=y2+blockHeight;
					y2=y1+blockDistance;}

					links.add(new Line(x1,y1,x1,y2));
					y1=y2+blockHeight;
					y2=y1+blockDistance;
					links.add(new Line(x1,y2,x1,y1));
					//y2-=blockHeight;
					//y1=y2-blockDistance-blockHeight;

				}
				else
				{
					if(j==0)
					{
						x1=b_x;
						y1=b_y;
					}
					else
						//non disegno però va bene cosi
						y1=(int) (y2+(stringToGroup(((Istruction)(group.get(i).getIstr().get(j))).getMyGroup())).getDimension().getdH());

					y2=y1+blockDistance;
					links.add(new Line(x1,y1,x1,y2));

				}
			}*/
			
		
		}
		//inserisco gli ovali inizio e fine

		a1=new InitialBlock((int)(group.get(0).getDimension().getX()+shiftX-blockWidth/2),(int)(group.get(0).getDimension().getY()-blockHeight-blockDistance+shiftY),blockWidth,blockHeight,"Inizio");
		blocks.add(a1);
		links.add(new Line((int)(group.get(0).getDimension().getX()+shiftX),(int)(group.get(0).getDimension().getY()-blockDistance+shiftY),(int)(group.get(0).getDimension().getX()+shiftX),(int)(group.get(0).getDimension().getY()+shiftY)));
		//blocco finale
		a1=new FinalBlock((int)(group.get(0).getDimension().getX()+shiftX-blockWidth/2),(int)(group.get(0).getDimension().getY()+group.get(0).getDimension().getdH()+blockDistance+shiftY),blockWidth,blockHeight,"Fine");
		blocks.add(a1);
		links.add(new Line((int)(group.get(0).getDimension().getX()+shiftX),(int)(group.get(0).getDimension().getY()+group.get(0).getDimension().getdH()+shiftY),(int)(group.get(0).getDimension().getX()+shiftX),(int)(group.get(0).getDimension().getY()+group.get(0).getDimension().getdH()+blockDistance+shiftY)));
		Flowchart2SVG.generateSVG(blocks,links,labels,"test");
	}


	public int bottom_y(Group father,String side)
	{
		for(int z=0;z<group.size();z++)
		{
			if(group.get(z).getFather()!=null)	
				if(group.get(z).getFather().equals(father.getName())) //cerco i figli
				{
					if(group.get(z).getPosition().equals(side))
						return (int) (group.get(z).getDimension().getdH());
				}
		}
		return 0;
	}

	public void replaceGroup_y(Group gruppo)
	{
		int actual_y=(int)gruppo.getDimension().getY()+blockDistance;
		//funzione ricorsiva che calcola la nuova Y
		for(int j=0;j<gruppo.getIstr().size();j++)
		{

			if(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()!=null)  //gruppo
			{
				(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup())).getDimension().sety(actual_y);
				if(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getGroupType().equals("if"))
				{
					//cerco i 2 figli e li aggiorno
					for(int z=j+1;z<group.size();z++)
					{
						if(group.get(z).getFather().equals(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getName())) //cerco i figli
							group.get(z).getDimension().sety(actual_y+blockHeight+blockDistance);
					}
				}
				if(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getGroupType().equals("for")||stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getGroupType().equals("while"))
				{
					//cerco il figlio e lo aggiorno
					for(int z=j+1;z<group.size();z++)
					{
						if(group.get(z).getFather().equals(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getName())) //cerco i figli
							group.get(z).getDimension().sety(actual_y+blockHeight+blockDistance);
					}
				}

				if(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getGroupType().equals("dowhile"))
				{

					//cerco il figlio e lo aggiorno
					for(int z=j+1;z<group.size();z++)
					{
						if(group.get(z).getFather().equals(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()).getName())) //cerco i figli
							group.get(z).getDimension().sety(actual_y+blockDistance);
					}
				}

				actual_y+=(stringToGroup(((Istruction)(gruppo.getIstr().get(j))).getMyGroup())).getDimension().getdH()+blockDistance;
			}
			else //istruzione
			{
				actual_y+=blockHeight+blockDistance;
			}
		}
		if(gruppo.getFather()!=null)
		{
			//ricorsione
			replaceGroup_y(stringToGroup(gruppo.getFather()));
		}
	}


	public static int dimensioneBlocchi(Group gruppo)
	{
		int actual_height=0;
		for(int j=0;j<gruppo.getIstr().size();j++)
		{

			if(((Istruction)(gruppo.getIstr().get(j))).getMyGroup()==null)  
			{
				actual_height+=blockHeight+blockDistance;
			}
		}
		return actual_height;
	}

	public static int dimensioneGruppi(Group gruppo)
	{
		int actual_height=0;
		//ricerca figli
		for(int z=0;z<group.size();z++)
		{
			if(group.get(z).getFather()!=null)
			{
				if(group.get(z).getFather().equals(gruppo.getName())) //cerco i figli
				{
					if(gruppo.getGroupType().equals("if"))
					{

						if(group.get(z).getDimension().getdH()+blockDistance>actual_height)
							actual_height=(int) (group.get(z).getDimension().getdH()+blockDistance);
					}
					else
						actual_height+=group.get(z).getDimension().getdH();
				}
			}
		}
		return (actual_height+blockDistance);
	}


	public static Group stringToGroup(String name)
	{
		for(int k=0;k<group.size();k++)
			if((group.get(k)).getName().equals(name))
				return group.get(k);
		return null;
	}

	public void  calculateXY(Group groups)
	{
		x=y=0;

		//posizione x, y per il gruppo iniziale
		if(groups.getFather()==null)
		{
			x=0;
			y=0;
		}
		else
		{
			fatherTemp=stringToGroup(groups.getFather());

			if(fatherTemp.getGroupType().equals("if"))
			{

				y=blockDistance+ifSpace+fatherTemp.getDimension().getY()+blockHeight/2;
				if(groups.getPosition().equals("right"))
					x=(blockWidth/2+groups.getDimension().getdL())+fatherTemp.getDimension().getX();
				if(groups.getPosition().equals("left"))
					x=(fatherTemp.getDimension().getX()-(blockWidth/2+groups.getDimension().getdR()));
			}

			if(fatherTemp.getGroupType().equals("for") || fatherTemp.getGroupType().equals("while"))
			{
				y=(blockDistance*2)+fatherTemp.getDimension().getY()+blockHeight; //   blockDistance*2+blockHeight
				x=fatherTemp.getDimension().getX();
			}

			if(fatherTemp.getGroupType().equals("dowhile"))
			{
				y=y+blockDistance+fatherTemp.getDimension().getY();
				x=fatherTemp.getDimension().getX();
			}

			if(fatherTemp.getGroupType().equals("init"))
			{
				//cerco le altezze dei figli e le sommo alla Y padre
				for(int j=1;j<group.size();j++)
				{
					if(group.get(j).getFather().equals(fatherTemp.getName())) //gruppi con lo stesso padre
					{
						if(!groups.getName().equals(group.get(j).getName()))
						{
							y=group.get(j).getDimension().getY()+group.get(j).getDimension().getdH(); // facendo cosi tengo solo l'ultimo figlio (non faccio y+=)
						}
						else
							j=group.size();
					}
				}
				y=y+fatherTemp.getDimension().getdH(); //    +fatherTemp.getDimension().getdH()
				x=fatherTemp.getDimension().getX();
			}
		}
	}

	public static void ancestorsResize(Group group)
	{
		if(group.getFather()==null)
			return;
		fatherTemp=stringToGroup(group.getFather());

		if(group.getPosition().equals("left"))
		{
			fatherTemp.getDimension().setdL(group.getDimension().getdL()+group.getDimension().getdR()+border);

			if(fatherTemp.getGroupType().equals("init"))
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockDistance);
			else
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockHeight);
		}
		if(group.getPosition().equals("right"))
		{
			fatherTemp.getDimension().setdR(group.getDimension().getdL()+group.getDimension().getdR()+border);
			//			setta le altezze
			if(fatherTemp.getGroupType().equals("init"))
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockDistance);
			else
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockHeight);
		}

		if(group.getPosition().equals("center"))
		{

			if(fatherTemp.getGroupType().equals("init"))
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockDistance);
			else
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockHeight);

			//controllo se la larghezza è adeguata

			if(fatherTemp.getDimension().getdL()<=group.getDimension().getdL())
			{
				fatherTemp.getDimension().setdL(group.getDimension().getdL()+border);
				//devo aggiornare anche la X del padre
			}

			if(fatherTemp.getDimension().getdR()<=group.getDimension().getdR())
				fatherTemp.getDimension().setdR(group.getDimension().getdR()+border);
		}

		ancestorsResize(fatherTemp);
	}



	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		flowchart c=new flowchart();
	}

}
