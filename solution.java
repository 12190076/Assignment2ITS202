
import java.util.*;

public class solution<Key extends Comparable<Key>, Value>  {
    private Node root;
    int size = 0;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
        
    }//Initializes an empty symbol table.
    public solution() {
        root = null;
    }

     // Returns true if this symbol table is empty.
    public boolean isEmpty() {
        if(size == 0){
        	return true;
        }
        return false;
    }

    
    //Returns the number of key-value pairs in this symbol table.
    public int size() 
    {
      return size;
    }

    
    //Returns the value associated with the given key.
     /**return the value associated with the given key if the key is in the symbol table
    and  
     */
    public Value get(Key key) {
    	if(key == null){     //if the key is not in the symbol table it will throw illegalArgunmentException
	        throw new IllegalArgumentException("argument to get value() is null");
	    }
        Node cpm = root;  //input key will compare with  root of the tree  and also with the left and right key of the root
        while(cpm.key != key){
        	int cmp = key.compareTo(cpm.key);
        	if(cmp < 0){
        		cpm = cpm.left;
        	}
	        else if(cmp > 0){
	        	cpm = cpm.right;
	        }
        }
        if(cpm.key == key){            /**if key is equal to any other key in the tree it will 
        								replace only the value and key remain same*/
        	System.out.println(cpm.val);
        }
        return cpm.val;
    }

    
    //Inserts the specified key-value pair into the symbol table
   
    //  Deletes the specified key (and its associated value) from this symbol table
    public void put(Key key, Value val) {
        Node newNode = new Node(key,val);
        if(root == null){
        	root = newNode;
        }
        else{
        	Node curNode = root;		 // value with the new value if the symbol table already contains the specified key.
        	Node parent;
        	while(true){
        		parent = curNode;
        		int cmp = key.compareTo(curNode.key);
        		if(cmp < 0){
        			curNode = curNode.left;
        			if(curNode == null){
        				parent.left = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        		else if(cmp > 0){
        			curNode = curNode.right;
        			if(curNode == null){
        				parent.right = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        	}
        }
        size = size + 1;		//increase the size
    }


    public Key min() {
       if(isEmpty()){
       	throw new NoSuchElementException("There is no element in the tree");   // will print if symbol table is empty
       }
       else{
       		Node curNode = root;
       		while(curNode.left != null){		
       			curNode = curNode.left;
       		}
       		return curNode.key;			//Returns the smallest key in the symbol table.
       }
    } 
  

    public Key floor(Key key) {
    	if(isEmpty()){			
			throw new NoSuchElementException("calls floor() with empty symbol table"); // will print if symbol table is empty
		}
		Node x = root;
		Node z = null;
		while(x != null){
			z = x;
			int cmp = key.compareTo(z.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp == 0){
				return z.key;
			}
			if(cmp < 0){
				x = z.left;
			}
			//It will  check floor of given key in right side
			else if(cmp > 0){
				x = z.right;
				
				int cm = key.compareTo(x.key);//it will returns previous z as floor of given key as it is less the right z
				if(cm < 0 ){
					return z.key;
				}
				else{	//This is for if the key is still greater then right z
					x = z.right;
				} 
			}
		}
		return z.key;
    } 

    public Key select(Key key) {
    	if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");// will print if symbol table is empty
		}
		Node N_root = root;
		Node x = null;
		while(N_root != null){
			x = N_root;
			int cmp = key.compareTo(x.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp > 0){
				N_root = x.right;
			}
			//it will checking  for floor of given key in left side
			else if(cmp < 0){
				N_root = x.left;
				//This condition checks if key is greater than left x then it returns the N_root 
				int cm = key.compareTo(x.key);
				if(cm > 0 ){
					return N_root.key;
				}
				//This is for if the key is still less then left x
				else{
					N_root = x.left;
				} 
			}
		}
		return x.key;
    }


    	/**  		Returns all keys in the symbol table in the given range,  
    				lo minimum endpoint
  	 				hi maximum endpoint
   					return all keys in the symbol table between lo and hi
    	*/
    public Iterable<Key> keys(Key lo, Key hi) {
    	Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);
        return queue;
     
    } 

    private void keys(Node x,Queue<Key> queue, Key lo, Key hi) { 
    	if (x == null)  
        return;  
    
        Node curNode = x;
        int cm = lo.compareTo(curNode.key);
        int cmp = hi.compareTo(curNode.key);
          
      
        while (curNode != null) {  
      
            if (curNode.left == null)  
            {   
                if (cm <= 0 && cmp >= 0)  queue.offer(curNode.key);    
                curNode = curNode.right;  
            }  
      
            else {  
                Node pre = curNode.left;    
                while (pre.right != null && pre.right != curNode)  
                    pre = pre.right;  
      
                if (pre.right == null)  
                {  
                    pre.right = curNode;  
                    curNode = curNode.left;  
                }  
                else {  
                    pre.right = null;    
                    if (cm <= 0 && cmp >= 0)  queue.offer(curNode.key);   
                    curNode = curNode.right;  
                }  
            }
        }
        
    }
  
    public static void main(String[] args) { 
        solution <String, Integer> obj = new solution <String, Integer>();
        obj.put("ABDUL",1);
        obj.get("ABDUL");
        obj.put("HRITHIK",2);
        obj.put("SAI",3);
        obj.put("SAMAL",6);
        obj.get("SAI");
        obj.put("TASHI",4);
        System.out.println("Size is: " + obj.size());
        System.out.println("The Min key is: " + obj.min());
        System.out.println(obj.floor("HRITHIK"));
        System.out.println(obj.floor("HAHA"));
       System.out.println(obj.select("HRITHIKH"));

       for(String s: obj.keys("ABDUL","TASHI")){
            System.out.print(s+" ");
        }
        System.out.println();
        obj.put("CHIMI",5);
        obj.put("SAMAL",4);
        obj.get("SAMAL");
        obj.put("NIMA",7);
        obj.get("CHIMI");

        System.out.println("The Size is: " + obj.size());
        System.out.println(obj.floor("CHIMI"));
        obj.put("SONAM",8);
        System.out.println(obj.keys("ABDUL","TASHI"));
    }
}
