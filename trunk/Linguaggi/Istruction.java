public class Istruction{


String text;
String shape;
String  typeIstr; //do,while,for,if,init
String myGroup; //se shape = rombo allora devo inserire un gruppo di nome mygroup

public Istruction(String text,String shape,String typeIstr)
{
this.text=text;
this.shape=shape;
this.typeIstr=typeIstr;
this.myGroup=null;
}

public Istruction(String text,String shape,String typeIstr,String myGroup)
{
this.text=text;
this.shape=shape;
this.typeIstr=typeIstr;
this.myGroup=myGroup;
}


public String getMyGroup()
{
return this.myGroup;
}

public void setMyGroup(String myGroup)
{
this.myGroup=myGroup;
}
public String getText()
{return this.text;}
public String getShape()
{return this.shape;}
public String getTypeIstr()
{
return this.typeIstr;
}


}//End of istruction
