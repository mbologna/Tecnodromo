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
 * perchè è solo il blocco custom (più interno) che definisce la dimensione mentre if while e dowhile non aggiungono
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
	 * Parte grafica
	 */
	List<GeneralBlock> blocks = new LinkedList<GeneralBlock>();
	List<Line> links = new LinkedList<Line>();
	List<Label> labels = new LinkedList<Label>();



	public flowchart() throws UnsupportedEncodingException, FileNotFoundException, SVGGraphics2DIOException
	{

		group=new Vector<Group>();
		Group group_insert=new Group("g1", true, "center", "main", "custom", null);
		group.add(group_insert);
		group_insert=new Group("g2", true, "center", "while(io=io)", "if", "g1");
		group.add(group_insert);
		//prova aggiunta istruzione
		group.get(1).setIstr(new Istruction("ifdiprova","ciao","ciao"));
		group_insert=new Group("g3", true, "left", "if(io=io)", "action", "g2");
		group.add(group_insert);
		group_insert=new Group("g4", true, "right", "while(io=io)", "action", "g2");
		group.add(group_insert);

		group_insert=new Group("g5", true, "center", "while(io=io)", "custom", "g1");
		group.add(group_insert);
		group_insert=new Group("g6", true, "center", "while(io=io)", "while", "g1");
		group.add(group_insert);

		/*
		group_insert=new Group("g4", true, "left", "while(io=io)", "if", "g2");
		group.add(group_insert);
		group_insert=new Group("g4", true, "right", "for(io=io)", "if", "g2");
		group.add(group_insert);*/

		//************* blocco riferimento assi *******************
		GeneralBlock a1 = new ActionBlock(300,30,30,30,"OR"); // 300= 300 + 0 X  ;  100 Y
		a1.setBlockColor(Color.BLUE);
		blocks.add(a1);

		//*********************************************************


		for(int i=0;i<group.size();i++)
		{


			//blockType: action , if , while , for , dowhile
			if((group.get(i)).getGroupType().equals("while") || (group.get(i)).getGroupType().equals("for")|| (group.get(i)).getGroupType().equals("dowhile")|| (group.get(i)).getGroupType().equals("custom"))
			{
				calculateXY(group.get(i));
				//dLeft 55 dRight 55
				(group.get(i)).setDimension((blockWidth/2)+border, (blockWidth/2)+border, blockDistance*4+blockHeight, x, y);
			}

			if((group.get(i)).getGroupType().equals("if"))
			{
				calculateXY(group.get(i));
				//dLEft 75  dRight 75; modifico (blockWidth/2)+border+ifSpace in (blockWidth/2)+border
				(group.get(i)).setDimension((blockWidth/2)+border, (blockWidth/2)+border, blockDistance+blockHeight/2, x, y);
			}

			if((group.get(i)).getGroupType().equals("action") )
			{
				calculateXY(group.get(i));
				(group.get(i)).setDimension((blockWidth/2)+border, (blockWidth/2)+border, blockDistance*2+blockHeight, x, y);
			}

			//ricorsione
			ancestorsResize(group.get(i));
		}


		//aggiorno la posizione x dei blocchi visto che le dimensioni sono gia settate correttamente
		for(int j=1;j<group.size();j++)
		{
			System.out.println("Padre: "+group.get(j).getName());
			for(int z=j+1;z<group.size();z++)
			{

				if(group.get(z).getFather().equals(group.get(j).getName())) //cerco i figli
				{
					System.out.println("Figlio: "+group.get(z).getName());
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
			}
		}

		/*
		 * mancano i collegamenti tra i figli dello stesso padre
		 * e i collegamenti con e da il blocco iniziale
		 * mancano i collegamenti finali a un blocco
		 */

		//disegno i link

		int l_x1 , l_y1, l_x2,l_y2,r_x1 , r_y1, r_x2,r_y2;
		/*for(int j=1;j<group.size();j++)
		{
			for(int z=j+1;z<group.size();z++)
			{


				if(group.get(z).getFather().equals(group.get(j).getName())) //collego padri con figli
				{
					System.out.println("da "+group.get(j).getName()+" a "+group.get(z).getName());
					l_x1=(int)group.get(j).getDimension().getX()+300;
					l_y1=(int)group.get(j).getDimension().getY()+100;
					l_x2=(int)group.get(z).getDimension().getX()+300;
					l_y2=(int)group.get(z).getDimension().getY()+100;
					links.add(new Arrow(l_x1,l_y1,l_x2,l_y2));
				}
			}
		}*/

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
			blocks.add(a1);

			//prova collegamenti blocco if
			if(group.get(i).getGroupType().equals("if")){
				int x1 , y1, x2,y2;
				a1 = new ControlBlock(b_x-blockWidth/2,b_y+blockDistance,blockWidth,blockHeight,((Istruction)(group.get(i).getIstr().get(0))).getText());
				blocks.add(a1);
				x1=x2=b_x;
				y1=b_y;
				y2=b_y+blockDistance;
				links.add(new Line(x1,y1,x2,y2));
				x1=b_x-blockWidth/2;
				y1=b_y+blockHeight/2+blockDistance;
				x2=x1-(int)(group.get(i+1).getDimension().getdL());
				y2=y1+blockHeight/2;
				links.add(new Line(x1,y1,x2,y1));
				links.add(new Line(x2,y1,x2,y2));
				x1=b_x+blockWidth/2;
				y1=b_y+blockHeight/2+blockDistance;
				x2=x1+(int)(group.get(i+1).getDimension().getdR());
				y2=y1+blockHeight/2;
				links.add(new Line(x1,y1,x2,y1));
				links.add(new Line(x2,y1,x2,y2));
				//mancano collegamenti dai figli alla fine del blocco
			}



		}


		Flowchart2SVG.generateSVG(blocks,links,labels,"test");
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
				for(int j=1;j<group.size();j++)
				{
					if(group.get(j).getFather().equals(fatherTemp.getName()))
					{
						if(fatherTemp.getDimension().getdH()<= group.get(j).getDimension().getdH())
						{
							y=group.get(j).getDimension().getY()+group.get(j).getDimension().getdH(); // facendo cosi tengo solo l'ultimo figlio (non faccio y+=)
							System.out.println("y-> "+y);
						}
					}
				}


				y=y+blockDistance+ifSpace+fatherTemp.getDimension().getY()+blockHeight/2;
				if(groups.getPosition().equals("right"))
					x=(blockWidth/2+groups.getDimension().getdL())+fatherTemp.getDimension().getX();
				if(groups.getPosition().equals("left"))
					x=(fatherTemp.getDimension().getX()-(blockWidth/2+groups.getDimension().getdR()));
			}

			if(fatherTemp.getGroupType().equals("for") || fatherTemp.getGroupType().equals("while"))
			{
				System.out.println("-------padre = "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				for(int j=1;j<group.size();j++)
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
				}

				y=y+(blockDistance*2)+fatherTemp.getDimension().getY(); //   blockDistance*2+blockHeight
				x=fatherTemp.getDimension().getX();
			}

			if(fatherTemp.getGroupType().equals("dowhile"))
			{
				System.out.println("-------padre = "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				for(int j=1;j<group.size();j++)
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
				}

				y=y+blockDistance+fatherTemp.getDimension().getY();
				x=fatherTemp.getDimension().getX();
			}

			if(fatherTemp.getGroupType().equals("custom"))
			{
				System.out.println("-------padre = "+fatherTemp.getName()+"-----------");
				//cerco le altezze dei figli e le sommo alla Y padre
				for(int j=1;j<group.size();j++)
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
				}
				y=y+border+fatherTemp.getDimension().getY(); //    +fatherTemp.getDimension().getdH()
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
			fatherTemp.getDimension().setdL(fatherTemp.getDimension().getdL()+group.getDimension().getdL()+group.getDimension().getdR());
			System.out.println("--> nuova Left"+fatherTemp.getDimension().getdL());
			//- - - altezza
			if(fatherTemp.getDimension().getdH()<=group.getDimension().getdH())
				fatherTemp.getDimension().setdH(border+fatherTemp.getDimension().getdH()+group.getDimension().getdH());

		}
		if(group.getPosition().equals("right"))
		{
			System.out.println(group.getName()+")"+ fatherTemp.getDimension().getdR()+" + " + group.getDimension().getdL()+" + "+ group.getDimension().getdR());
			fatherTemp.getDimension().setdR(fatherTemp.getDimension().getdR()+group.getDimension().getdL()+group.getDimension().getdR());

			//Aggiorno la X , e devo anche spostare tutti i figli dei figli
			//group.getDimension().setx(group.getDimension().getX()+group.getDimension().getdL()); //sposto la x
			System.out.println("--> nuova Right"+fatherTemp.getDimension().getdR());
			//- - - altezza
			if(fatherTemp.getDimension().getdH()<=group.getDimension().getdH())
				fatherTemp.getDimension().setdH(border+fatherTemp.getDimension().getdH()+group.getDimension().getdH());


		}

		if(group.getPosition().equals("center"))
		{
			//System.out.println(group.getName()+")"+ fatherTemp.getDimension().getdR()+" + " + group.getDimension().getdL()+" + "+ group.getDimension().getdR());
			if(fatherTemp.getDimension().getdH()<=(group.getDimension().getdH()+group.getDimension().getY()))
				fatherTemp.getDimension().setdH(group.getDimension().getdH()+group.getDimension().getY());

			//controllo se la larghezza è adeguata
			System.out.println("Padre left)"+fatherTemp.getDimension().getdL() +"Figlio LEft" + group.getDimension().getdL());
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
