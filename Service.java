
public class Service{
    private char Name;
    private int ID;
    private double Reliability;
    private double Price;
    private double Q;
    public void setName(char name){
    	this.Name=name;
    }
    public char getName(){
    	return Name;
    }
    public void setID(int id){
    	this.ID=id;
    }
    public int getID(){
    	return ID;
    }
    public void setReliability(double reliability){
    	this.Reliability=reliability;
    }
    public double getReliability(){
    	return Reliability;
    }
    public void setPrice(double price){
    	this.Price=price;
    }
    public double getPrice(){
    	return Price;
    }
    public void setQ(){
    	this.Q=this.Reliability-this.Price/100;
    }
    public double getQ(){
    	return Q;
    }
}