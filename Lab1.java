import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Lab1 {
	static double targetR;
	static double targetP;
	static char[] process;
	static boolean[] processFlag=new boolean[14];
	static int amountOfService;
	public static void main(String[] args) throws IOException { 	
		//*input the data and process the data
		//*only run a time	
		AllServices.setSize(14, 500);
		AllServices.inputService(new String("SERVICE.TXT"));
		//*input the case
		String str1,str2,str3,str5;
		Scanner inREQ=new Scanner(new FileReader("REQ.TXT"));
		File outResult=new File("RESULT.txt");
		FileOutputStream FOS = new FileOutputStream(outResult);
		BufferedWriter buffWriter=new BufferedWriter(new OutputStreamWriter(FOS));
		Scanner inProcess=new Scanner(new FileReader("PROCESS.TXT"));
		while (inREQ.hasNextLine()&&inProcess.hasNextLine()){
			str1=inREQ.nextLine();
			str2=inProcess.nextLine();
			str3=str2;
			String[] splitStr=str1.split("\\(|\\,|\\)");
			String[] splitStr1;
			targetR=Double.parseDouble(splitStr[1]);
			targetP=Double.parseDouble(splitStr[2]);
			str2=str2.replace('(', ',');
			str2=str2.replace(')',',');
			str2=str2.replaceAll(",,,",",");
			splitStr=str2.split(",");
			process=new char[splitStr.length-1];
			for (int i=0;i<14;i++){
				processFlag[i]=false;
			}
			for (int i=1;i<splitStr.length;i++){
				process[i-1]=splitStr[i].charAt(0);
				processFlag[((int)process[i-1])-65]=true;
			}
			amountOfService=0;
			for (int i=0;i<14;i++){
				if (processFlag[i]) amountOfService++;
			}
			AllServices.servicesFilter(targetR,targetP,14,500);
			HeapNode max=getResult();
			String str4=max.getName();
			splitStr1=str4.split(" ");
			int[] xFlag={0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			for(int i=0;i<str3.length();i++){
				if(str3.charAt(i)<91 && str3.charAt(i)>64){
					str5=String.valueOf(str3.charAt(i));
					for(int x=0;x<splitStr1.length;x++){
						if(splitStr1[x].charAt(0)==str3.charAt(i)){
							if(xFlag[x]==0){
								str3=str3.replaceAll(str5,splitStr1[x]);
								xFlag[x]=1;
							}
	                       break;
						}
					}
					
			     }
			}
			
			System.out.println(str3+","+"Reliability="+max.getReliability()+",Price="+max.getPrice()+",Q="+(max.getReliability()-max.getPrice()/100));
			buffWriter.write(str3+","+"Reliability="+max.getReliability()+",Price="+max.getPrice()+",Q="+(max.getReliability()-max.getPrice()/100)+'\n');
			buffWriter.newLine();
		}
		buffWriter.close();
		inREQ.close();
		inProcess.close();
	}
	
	public static HeapNode getResult(){
		Heap heap=new Heap();
		int now=0;
		for(int i=0;i<14;i++){
			if (processFlag[i]==false)
				continue;
			else{
				now=i;
				break;
			}
		}
		for(int i=0;i<=499;i++){
			if(AllServices.flag[now][i]==true){
				String str = String.valueOf(AllServices.services[now][i].getName())+'-' +String.valueOf(AllServices.services[now][i].getID());
				heap.addElement(0,now,str,AllServices.services[now][i].getReliability(),AllServices.services[now][i].getPrice());
			}
		}

		HeapNode max = heap.getRoot();
		double MaxQ=0;
		while(!heap.isEmpty()){
			HeapNode temp=heap.getRoot();
			//System.out.println("方案："+temp.Name+"  Q: "+temp.getName());
			heap.removeRoot();
			int k=temp.getStep();
			double R=temp.getReliability();
			double P=temp.getPrice();
			int nowChar=temp.getnextChar();
			String str1=temp.getName();
			if(k==amountOfService-1){
				if(MaxQ < (R-P/100)){
					MaxQ=R-P/100;
					max=temp;
					//System.out.println("方案："+max.Name+"  Q: "+MaxQ);
				};
			}
			else if(k<amountOfService-1){
				if(R-P/100<=MaxQ|| R< targetR ||P>targetP) continue;
				else{
					for(int i=nowChar+1;i<14;i++){
						if (processFlag[i]!=true)
							continue;
						else{
							now=i;
							break;
						}
					}

					for (int i=0;i<=499;i++){
						if(AllServices.flag[now][i]==true){
							String str2 =str1+ " "+AllServices.services[now][i].getName()+"-" +AllServices.services[now][i].getID();
							heap.addElement(k+1,now,str2,R*AllServices.services[now][i].getReliability(),P+AllServices.services[now][i].getPrice());	
						}
					}
				}
			}
			//System.out.println("方案："+max.getName()+"  R: "+max.getReliability()+"  P: "+max.getPrice()+"  Q: "+MaxQ);
			//System.out.println();

		}
		//System.out.println("方案："+max.getName()+"  R= "+max.getReliability()+"  P= "+max.getPrice()+"  Q= "+MaxQ);
	//	System.out.println();
		return max;
	}
	//test input function
	public static void testinput(){
		for (int i=0;i<=13;i++){
			for (int j=0;j<=499;j++){
				if (AllServices.flag[i][j]){
					System.out.print(AllServices.services[i][j].getName()+" ");
					System.out.print(AllServices.services[i][j].getID()+" ");
					System.out.print(AllServices.services[i][j].getReliability()+" ");
					System.out.println(AllServices.services[i][j].getPrice());
				}

			}
		}
	}
}

