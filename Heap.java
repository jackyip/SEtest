public class Heap  
{  

    private HeapNode[] data;  
    private int elementN;  

    public Heap(){  
        this(20);  
    }  
      
    public Heap(int capacity){  
        data = new HeapNode[capacity];  
        elementN = 0;  
    }  
 
    public boolean isEmpty(){  
        return elementN == 0;  
    }  
 
    public void addElement(int Step,int nextChar,String Name,double Reliability,double Price)  
    {  
    	double key=Reliability-Price/100;
        HeapNode heapNode = new HeapNode(key, Name);
        heapNode.setReliability(Reliability);
        heapNode.setPrice(Price);
        heapNode.setnextChar(nextChar);
        heapNode.setStep(Step);
        if (elementN >= data.length){  
            HeapNode[] newArray = new HeapNode[data.length * 2];  
            System.arraycopy(data, 0, newArray, 0, data.length);  
            data = newArray;  
        }  
        int currentIndex = elementN;  
        data[elementN++] = heapNode;  
        while (currentIndex > 0){  
            if (data[currentIndex].key <= data[(currentIndex - 1) / 2].key){  
                break;  
            } 
            else{  
                swapNode(currentIndex, (currentIndex - 1) / 2);  
                currentIndex = (currentIndex - 1) / 2;  
            }  
        }  
    }  
      
    public HeapNode removeRoot()  
    {  
        HeapNode temp = null;  
        if (!isEmpty()){  
            temp = data[0];  
            if (elementN > 1){  
                data[0] = data[elementN - 1];  
                reHeap(elementN - 2);  
            }  
            else{  
                data[0] = null;  
            }  
            elementN--;  
        }  
        return temp;  
    }  
 
    private void reHeap(int lastIndex)  
    {  
        int currentIndex = 0;  
        while (currentIndex * 2 + 1 <= lastIndex){  
            if (currentIndex * 2 + 2 <= lastIndex){  
                if (data[currentIndex * 2 + 2].key >= data[currentIndex * 2 + 1].key) {  
                    if (data[currentIndex].key <= data[currentIndex * 2 + 2].key){  
                        swapNode(currentIndex, currentIndex * 2 + 2);  
                        currentIndex = currentIndex * 2 + 2;  
                    }
                    else{  
                        break;  
                    }  
                }  
                else{  
                    if (data[currentIndex].key <= data[currentIndex * 2 + 1].key){  
                        swapNode(currentIndex, currentIndex * 2 + 1);  
                        currentIndex = currentIndex * 2 + 1;  
                    } 
                    else{  
                        break;  
                    }  
                }  
            }  
            else{  
                if ( data[currentIndex].key <= data[currentIndex * 2 + 1].key){  
                    swapNode(currentIndex, currentIndex * 2 + 1);  
                    currentIndex = currentIndex * 2 + 1;  
                }   
                else{  
                    break;  
                }  
            }  
        }  
    }  
      
 
    private void swapNode(int index1, int index2){  
        HeapNode node = data[index1];  
        data[index1] = data[index2];  
        data[index2] = node;  
    }  

    public int getSize(){  
        return elementN;  
    }  
 
    public HeapNode getRoot(){  
        HeapNode root = null;  
        if (!isEmpty()){  
            root = data[0];  
        }  
        return root;  
    }  
      
 
    public void displayHeap(){  
        if (!isEmpty()){  
            for (int i = 0; i <= elementN - 1; i++){  
                System.out.println(data[i].toString());  
            }  
        }  
    }  
      

    public void sort(){  
        if (elementN > 1){  
            for (int i = elementN; i > 1; i--){  
                swapNode(0, i - 1);  
                reHeap(i - 2);  
            }  
        }  
    }  
}  