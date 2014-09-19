public class HeapNode  
{  
	  String Name;
      double key;
      private int Step;
      private double Reliability;
      private double Price;
      private int nextChar;
      
      public void setReliability(double reliability){
      	this.Reliability=reliability;
      }
      public double getReliability(){
      	return Reliability;
      }
      public void setPrice(double price){
      	this.Price=price;
      }
      public void setStep(int Step){
        	this.Step=Step;
        }
      public double getPrice(){
      	return Price;
      }
      public int getStep(){
        	return Step;
        }
      public int getnextChar(){
      	return nextChar;
      }
      public void setnextChar(int nextChar){
        	this.nextChar=nextChar;
        }
      
    public HeapNode(double key, String Name){  
        this.key = key;  
        this.Name = Name;  
    }  
    
    public String getName(){
    	return Name;
    }
    public double getkey(){
    	return key;
    }
    
    public String toString(){  
        return "HeapNode [Name=" + Name + ", key=" + key + "]";  
    }  
}