import it.unibg.cs.flowchart2svg.Flowchart2SVG;
import it.unibg.cs.flowchart2svg.blocks.ActionBlock;
import it.unibg.cs.flowchart2svg.blocks.ControlBlock;
import it.unibg.cs.flowchart2svg.blocks.GeneralBlock;
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

//**********************
/*
 *
 * il problema è che nella nostra testa il punto (x,y) sta al centro in alto nel rettangolo qui
 * invece sta in alto a sx e questo scombussola tutti i conti
 * RISOLTO riga 125 b_x-b_w_l
 *
 * Stavo pensando che forse conviene fare i blocchi larghi 40 20+20 che corrisponde alla dimensione del bordo
 * perchè è solo il blocco init (più interno) che definisce la dimensione mentre if while e dowhile non aggiungono
 * larghezza
 */
public class flowchart {
	final static int blockWidth=70;
	final static int blockHeight=50;
	final static int border=20;
	final static int ifSpace=20;
	final static int blockDistance=15;


	static Vector <Group> group;

	double x,y;
	static public Group fatherTemp;

	int b_x,b_y,b_w,b_h,b_w_l;
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

		//gruppo solo istruzioni si chiama INIT
		//stampare tutte le istruzioni che hanno shape diverso da rombo
		//il gruppo init contiene blocchi e gruppi

		/*Group group_insert=new Group("g1", true, "center", "main", "init", null);
		group.add(group_insert);
		group.get(0).setIstr(new Istruction("ifdiprova","rombo","ciao","g2"));
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));

		group_insert=new Group("g2", true, "center", "while(io=io)", "if", "g1");
		group.add(group_insert);
		group.get(1).setIstr(new Istruction("ifdiprova","ciao","ciao"));
		group.get(1).setIstr(new Istruction("ifdiprova","rombo","ciao","g3"));
		group.get(1).setIstr(new Istruction("ifdiprova","rombo","ciao","g4"));

		group_insert=new Group("g3", true, "left", "if(io=io)", "init", "g2");
		group.add(group_insert);
		group.get(2).setIstr(new Istruction("bolcco","1ciao","1ciao"));
		group.get(2).setIstr(new Istruction("bolcco_q","12ciao","12ciao"));
		group.get(2).setIstr(new Istruction("bolcco_s","13ciao","13ciao"));
		group.get(2).setIstr(new Istruction("bolcco","1ciao","1ciao"));
		group.get(2).setIstr(new Istruction("bolcco_q","12ciao","12ciao"));
		group.get(2).setIstr(new Istruction("bolcco_s","13ciao","13ciao"));

		group_insert=new Group("g4", true, "right", "while(io=io)", "dowhile", "g2");
		group.add(group_insert);

		group_insert=new Group("g5", true, "center", "while(io=io)", "if", "g4");
		group.add(group_insert);
		//prova aggiunta istruzione
		//group.get(4).setIstr(new Istruction("ifdi","ciao","ciao"));

		group_insert=new Group("g6", true, "left", "while(io=io)", "init", "g5");
		group.add(group_insert);
		group.get(5).setIstr(new Istruction("bolcco_q","12ciao","12ciao"));
		group.get(5).setIstr(new Istruction("bolcco_s","13ciao","13ciao"));

		group_insert=new Group("g7", true, "right", "while(io=io)", "init", "g5");
		group.add(group_insert);
		group.get(6).setIstr(new Istruction("bolcco_q","12ciao","12ciao"));
		group.get(6).setIstr(new Istruction("ruby_q","12ciao","12ciao"));
		group.get(6).setIstr(new Istruction("bolcco_q","12ciao","12ciao"));
		group.get(6).setIstr(new Istruction("seby_q","12ciao","12ciao"));
		group.get(6).setIstr(new Istruction("seby_q","12ciao","12ciao"));
		group.get(6).setIstr(new Istruction("seby_q","12ciao","12ciao"));*/


		Group group_insert=new Group("g1", true, "center", "main", "init", null);
		group.add(group_insert);
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		group.get(0).setIstr(new Istruction("ifdiprova","rombo","ciao","g2"));
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));
		group.get(0).setIstr(new Istruction("last","1ciao","1ciao"));


		group_insert=new Group("g2", true, "center", "if(io=io)", "if", "g1");
		group.add(group_insert);


		group_insert=new Group("g3", true, "left", "", "init", "g2");
		group.add(group_insert);
		group.get(2).setIstr(new Istruction("whuileprova","gsfd","ciao"));
		group.get(2).setIstr(new Istruction("whuileprova","gsfd","ciao"));
		group.get(2).setIstr(new Istruction("whuileprova","gsfd","ciao"));

		group_insert=new Group("g4", true, "right", "while(io=io)", "init", "g2");
		group.add(group_insert);
		group.get(3).setIstr(new Istruction("mao","gsfd","ciao"));
		group.get(3).setIstr(new Istruction("ifdi2","rombo","ciao","g5"));
		group.get(3).setIstr(new Istruction("mao","gsfd","ciao"));


		group_insert=new Group("g5", true, "center", "if(io=io)", "dowhile", "g4");
		group.add(group_insert);


		group_insert=new Group("g6", true, "center", "", "init", "g5");
		group.add(group_insert);
		group.get(5).setIstr(new Istruction("whuileprova","gsfd","ciao"));
		group.get(5).setIstr(new Istruction("whuileprova","gsfd","ciao"));

		/*group_insert=new Group("g7", true, "right", "while(io=io)", "init", "g5");
		group.add(group_insert);
		group.get(6).setIstr(new Istruction("mao","gsfd","ciao"));
		group.get(6).setIstr(new Istruction("mao","gsfd","ciao"));*/


		//************* blocco riferimento assi *******************
		GeneralBlock a1 = new ActionBlock(300,30,30,30,"OR"); // 300= 300 + 0 X  ;  100 Y
		a1.setBlockColor(Color.BLUE);
		blocks.add(a1);

		//*********************************************************


		for(int i=0;i<group.size();i++)
		{


			//blockType: action , if , while , for , dowhile
			if((group.get(i)).getGroupType().equals("while") || (group.get(i)).getGroupType().equals("for")|| (group.get(i)).getGroupType().equals("dowhile"))
			{
				calculateXY(group.get(i));
				System.out.println("x: "+x+" y: "+y);
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
				System.out.println(group.get(i).getName()+" altezza con blokki e senza gruppi->"+group.get(i).getDimension().getdH());
			}

			//ricorsione
			ancestorsResize(group.get(i));
		}


		//aggiorno la posizione x dei blocchi visto che le dimensioni sono gia settate correttamente
		for(int j=0;j<group.size();j++)
		{
			System.out.println("******Padre: "+group.get(j).getName());
			for(int z=j+1;z<group.size();z++)
			{

				if(group.get(z).getFather().equals(group.get(j).getName())) //cerco i figli
				{
					System.out.println("****Figlio: "+group.get(z).getName());
					// modi di spostare la x in base alla posizione left,right o center rispetto al blocco padre
					if(group.get(z).getPosition().equals("right"))
					{
						System.out.println("prima->"+group.get(z).getDimension().getX());
						//setto la posizione x del figlio come la x del padre + metà della lunghezza destra del padre
						group.get(z).getDimension().setx(group.get(j).getDimension().getX()+(group.get(j).getDimension().getdR()/2));
						System.out.println("dopo->"+group.get(z).getDimension().getX());
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
				System.out.println("x: "+group.get(j).getDimension().getX()+"y: "+group.get(j).getDimension().getY());
			}

		}

		int l_x1 , l_y1, l_x2,l_y2,r_x1 , r_y1, r_x2,r_y2;


		for(int i=0;i<group.size();i++)
		{

			b_x=(int) group.get(i).getDimension().getX()+300;
			b_y=(int) group.get(i).getDimension().getY()+ 100;
			b_h=(int) group.get(i).getDimension().getdH();
			b_w_l =(int) (group.get(i).getDimension().getdL());
			b_w=(int) (group.get(i).getDimension().getdL()+group.get(i).getDimension().getdR());
			b_nome="gruppo "+(i+1);

			/*
			 * Implementazione grafica del contenuto dei gruppi 
			 */



		}
		System.out.println("--------------fine-------------");
		/*
		 * fine grafica gruppi
		 */

		for(int i=0;i<group.size();i++)
		{
			if(group.get(i).getGroupType().equals("init"))
			{
				System.out.println("+++ y di "+group.get(i).getName()+" : "+group.get(i).getDimension().getY());
				replaceGroup_y(group.get(i));
				System.out.println("+++ y di "+group.get(i).getName()+" : "+group.get(i).getDimension().getY());
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


			b_x=(int) group.get(i).getDimension().getX()+300;
			b_y=(int) group.get(i).getDimension().getY()+ 100;
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
							a1 = new ActionBlock(b_x-blockWidth/2,actual_y,blockWidth,blockHeight,((Istruction)(group.get(i).getIstr().get(h))).getText());
							blocks.add(a1);
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

			if(group.get(i).getGroupType().equals("while"))
			{
				int x1 , y1, x2,y2;
				a1 = new ControlBlock(b_x-blockWidth/2,b_y+blockDistance,blockWidth,blockHeight,group.get(i).getHeader());
				blocks.add(a1);
			}

			if(group.get(i).getGroupType().equals("dowhile"))
			{
				int x1 , y1, x2,y2;
				a1 = new ControlBlock(b_x-blockWidth/2,b_y+b_h-blockHeight,blockWidth,blockHeight,group.get(i).getHeader());
				blocks.add(a1);
				y1=b_y+b_h-blockHeight/2;
				x1=b_x-blockWidth/2;
				x2=b_x-b_w_l;
				links.add(new Line(x1,y1,x2,y1));
				y2=b_y;
				links.add(new Line(x2,y1,x2,y2));
				x1=x2+blockWidth;
				links.add(new Line(x2,y2,x1,y2));
				y1=y2+blockDistance;
				links.add(new Line(x1,y2,x1,y1));

			}

			//collegamenti tra i blocchi
			int x1=0 , y1=0, x2,y2=0;
			for(int j=0;j<group.get(i).getIstr().size();j++)
				{
					if(((Istruction)(group.get(i).getIstr().get(j))).getMyGroup()==null)  
					{
						
						if(j==0)
							{
							x1=b_x;
							y1=b_y;
							}
						else
							y1=y2+blockHeight;
						
						y2=y1+blockDistance;
						links.add(new Line(x1,y1,x1,y2));
						
					}
					else
					{
						if(j==0)
						{
						x1=b_x;
						y1=b_y;
						}
					else
						y1=(int) (y2+(stringToGroup(((Istruction)(group.get(i).getIstr().get(j))).getMyGroup())).getDimension().getdH());
						
						y2=y1+blockDistance;
						links.add(new Line(x1,y1,x1,y2));
					}
				}

		}

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

			System.out.println(fatherTemp.getGroupType());

			if(fatherTemp.getGroupType().equals("if"))
			{
				System.out.println("-------padre "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				/*for(int j=1;j<group.size();j++)
				{
					if(group.get(j).getFather().equals(fatherTemp.getName()))
					{
						if(fatherTemp.getDimension().getdH()<= group.get(j).getDimension().getdH())
						{
							y=group.get(j).getDimension().getY()+group.get(j).getDimension().getdH(); // facendo cosi tengo solo l'ultimo figlio (non faccio y+=)
							System.out.println("y-> "+y);
						}
					}
				}*/
				y=blockDistance+ifSpace+fatherTemp.getDimension().getY()+blockHeight/2;
				if(groups.getPosition().equals("right"))
					x=(blockWidth/2+groups.getDimension().getdL())+fatherTemp.getDimension().getX();
				if(groups.getPosition().equals("left"))
					x=(fatherTemp.getDimension().getX()-(blockWidth/2+groups.getDimension().getdR()));
			}

			if(fatherTemp.getGroupType().equals("for") || fatherTemp.getGroupType().equals("while"))
			{
				System.out.println("-------padre = "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				/*for(int j=1;j<group.size();j++)
				{
					if(group.get(j).getFather().equals(fatherTemp.getName()))
					{
						System.out.println("->figlio : "+group.get(j).getName());
						if(!groups.getName().equals(group.get(j).getName()))
						{
							y=group.get(j).getDimension().getY()+group.get(j).getDimension().getdH(); // facendo cosi tengo solo l'ultimo figlio (non faccio y+=)
							System.out.println("y-> "+y);
						}
						else
							j=group.size();
					}
				}*/

				y=(blockDistance*2)+fatherTemp.getDimension().getY()+blockHeight; //   blockDistance*2+blockHeight
				x=fatherTemp.getDimension().getX();
			}

			if(fatherTemp.getGroupType().equals("dowhile"))
			{
				System.out.println("-------padre = "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				/*for(int j=1;j<group.size();j++)
				{
					if(group.get(j).getFather().equals(fatherTemp.getName()))
					{
						System.out.println("->figlio : "+group.get(j).getName());
						if(!groups.getName().equals(group.get(j).getName()))
						{
							y=group.get(j).getDimension().getY()+group.get(j).getDimension().getdH(); // facendo cosi tengo solo l'ultimo figlio (non faccio y+=)
							System.out.println("y-> "+y);
						}
						else
							j=group.size();
					}
				}*/

				y=y+blockDistance+fatherTemp.getDimension().getY();
				x=fatherTemp.getDimension().getX();
			}

			if(fatherTemp.getGroupType().equals("init"))
			{
				System.out.println("-------padre = "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				for(int j=1;j<group.size();j++)
				{
					if(group.get(j).getFather().equals(fatherTemp.getName())) //gruppi con lo stesso padre
					{
						System.out.println("->figlio : "+group.get(j).getName());
						if(!groups.getName().equals(group.get(j).getName()))
						{
							y=group.get(j).getDimension().getY()+group.get(j).getDimension().getdH(); // facendo cosi tengo solo l'ultimo figlio (non faccio y+=)
							System.out.println("y-> "+y);
						}
						else
							j=group.size();
					}
				}
				y=y+fatherTemp.getDimension().getY(); //    +fatherTemp.getDimension().getdH()
				x=fatherTemp.getDimension().getX();
			}
		}
	}

	public static void ancestorsResize(Group group)
	{
		if(group.getFather()==null)
			return;
		fatherTemp=stringToGroup(group.getFather());

		System.out.println("Gruppo "+group.getName());

		if(group.getPosition().equals("left"))
		{
			System.out.println(group.getName()+")"+ fatherTemp.getDimension().getdL()+" + " + group.getDimension().getdL()+" + "+ group.getDimension().getdR());
			//vecchia versione
			//fatherTemp.getDimension().setdL(fatherTemp.getDimension().getdL()+group.getDimension().getdL()+group.getDimension().getdR());
			//nuova versione
			fatherTemp.getDimension().setdL(group.getDimension().getdL()+group.getDimension().getdR()+border);
			System.out.println("--> nuova Left"+fatherTemp.getDimension().getdL());

			if(fatherTemp.getGroupType().equals("init"))
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockDistance);
			else
				fatherTemp.getDimension().setdH(dimensioneBlocchi(fatherTemp)+dimensioneGruppi(fatherTemp)+blockHeight);

		}
		if(group.getPosition().equals("right"))
		{
			System.out.println(group.getName()+")"+ fatherTemp.getDimension().getdR()+" + " + group.getDimension().getdL()+" + "+ group.getDimension().getdR());
			//vecchia versione
			//fatherTemp.getDimension().setdR(fatherTemp.getDimension().getdR()+group.getDimension().getdL()+group.getDimension().getdR());
			//nuova versione
			fatherTemp.getDimension().setdR(group.getDimension().getdL()+group.getDimension().getdR()+border);
			//Aggiorno la X , e devo anche spostare tutti i figli dei figli
			//group.getDimension().setx(group.getDimension().getX()+group.getDimension().getdL()); //sposto la x
			System.out.println("--> nuova Right"+fatherTemp.getDimension().getdR());

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
			System.out.println("Padre left)"+fatherTemp.getDimension().getdL() +"Figlio Left" + group.getDimension().getdL());
			if(fatherTemp.getDimension().getdL()<=group.getDimension().getdL())
			{
				fatherTemp.getDimension().setdL(group.getDimension().getdL()+border);
				//devo aggiornare anche la X del padre
			}
			System.out.println("Padre right)"+fatherTemp.getDimension().getdR() +"Figlio Right" + group.getDimension().getdR());
			if(fatherTemp.getDimension().getdR()<=group.getDimension().getdR())
				fatherTemp.getDimension().setdR(group.getDimension().getdR()+border);

			System.out.println("--> nuova Left-Right"+(fatherTemp.getDimension().getdR()+fatherTemp.getDimension().getdL()));
		}

		ancestorsResize(fatherTemp);
	}



	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException {
		flowchart c=new flowchart();
	}

}
