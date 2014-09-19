import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class AllServices {
    static Service[][] services;//record all services
    static boolean[][] flag;//true:the service can be chosen;false:the service is not a good choice
    static int x,y;
    public static void setSize(int x,int y){
    	services=new Service[x][y];
    	flag=new boolean[x][y];
    	for (int i=0;i<x;i++){
    		for (int j=0;j<y;j++){
    			services[i][j]=new Service();
    		}
    	}
    	AllServices.x=x;
    	AllServices.y=y;
    }
    public static void inputService(String path){
    	try {
			Scanner cin=new Scanner(new FileReader(path));
			String namestr;
			char name;
			int id;
			double reliability;
			double price;
			for (int i=0;i<=13;i++){
				for (int j=0;j<=499;j++){
					namestr=cin.next();
			        name=namestr.charAt(0);
			        id=Integer.parseInt(namestr.substring(2, namestr.length())) ;
			        cin.nextDouble();
			        reliability=cin.nextDouble();
			        cin.nextDouble();
			        price=cin.nextDouble();
			        services[i][j].setName(name);
			        services[i][j].setID(id);
			        services[i][j].setReliability(reliability);
			        services[i][j].setPrice(price);
			        services[i][j].setQ();
				}
			}		
			cin.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}  	
    }
    public static void servicesFilter(double targetR,double targetP,int x,int y){
        for (int i=0;i<x;i++){
        	for (int j=0;j<y;j++){
        		flag[i][j]=true;
        	}
        }
        for (int i=0;i<x;i++){
        	for (int j=0;j<y;j++){
        		if (flag[i][j]){
        			double r,p,r2,p2;
        			r=AllServices.services[i][j].getReliability();
        			p=AllServices.services[i][j].getPrice();
        			if (r<targetR||p>targetP){
        				flag[i][j]=false;
        			}
        			else{
        				for (int k=j+1;k<y;k++){
                            if (flag[i][k]){
                            	r2=AllServices.services[i][k].getReliability();
                            	p2=AllServices.services[i][k].getPrice();
                            	if (r>=r2&&p<=p2){
                            		flag[i][k]=false;
                            	}
                            	if (r<=r2&&p>=p2){
                            		flag[i][j]=false;
                            		break;
                            	}
                            }
        				}
        			}
        		}
        	}
        }
	}
    //reset all flags but keep the data
    public static void reset(){
        for (int i=0;i<x;i++){
        	for (int j=0;j<y;j++){
        		flag[i][j]=true;
        	}
        }
    }
}
