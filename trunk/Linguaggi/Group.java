import java.util.Vector;

public class Group{

private String name;
private String position;
private String header; //testo rombo
private boolean openP;
private boolean isFinish;
private Dimension dim;
private String groupType;
private String father;
Vector<Istruction> istr=new  Vector<Istruction>();

public Group(String name, boolean openP,String position)
{
this.name=name;
this.openP=openP;
this.isFinish=false;
this.position=position;
this.father=null;
this.header=null;
this.dim=new Dimension(1,1,1,1,1);
}

public Group(String name, boolean openP,String position,String header)
{
this.name=name;
this.openP=openP;
this.isFinish=false;
this.position=position;
this.father=null;
this.header=header;
this.dim=new Dimension(1,1,1,1,1);
}

//--- definito da Alva ----
public Group(String name, boolean openP,String position,String header, String type, String father)
{
this.name=name;
this.openP=openP;
this.isFinish=false;
this.position=position;
this.father=father;
this.header=header;
this.groupType=type;
this.dim=new Dimension(15,15,15,15,15);
}

public String getName()
{
return this.name;
}

public String getHeader()
{return this.header;}

public boolean getOpenP()
{
return this.openP;
}
public String getFather()

{return this.father;}

public boolean getIsFinish()
{
return this.isFinish;
}

public String getGroupType()
{return this.groupType;}

public  void setIsFinish(boolean isFinish)
{this.isFinish=isFinish;}

public Dimension getDimension()
{
return this.dim;
}

void setTypeGroup(String groupType)
{
this.groupType=groupType;
}

public void setDimension(double dL,double dR,double dH,double x,double y)
{
this.dim=new Dimension(dL,dR,dH,x,y);
}

public void setHeader(String header)
{this.header=header;}


public void setIstr(Istruction istr)
{
this.istr.add(istr);
}
String getPosition()
{return this.position;}


public Vector  getIstr()
{
return this.istr;} 
}//End of class Group
