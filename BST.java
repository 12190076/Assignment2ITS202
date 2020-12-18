import java.util.*;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;         

    private class Node {
        private Key key;        
        private Value val;        
        private Node left, right;
        int size;      
        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size =0;
        }
    }
    public int size(){
        return size(root);    
    }
    private int size(Node x) {
        if(x!=null){
            return x.size;
        }
        return 0;  
    }
    public Value get(Key key) {
        return get(root,key);    
    }
    private Value get(Node x, Key key) {

        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        
        if (cmp < 0)
            return get(x.left,key);
        else if (cmp > 0)
            return get(x.right,key);
        
        return x.val;
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val,1);        
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
        {
            x.val = val;
            return x;
        } 
        else if (cmp < 0)
        {
            x.left = put(x.left, key, val);
        } 
        else if (cmp > 0) 
        {
            x.right = put(x.right, key, val);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }   
    // private Node put(Node x, Key key, Value val) {
        
    // }
    public Key min() {
        if (root==null) {
            return null;
        }
        return min(root).key;
       
    } 

    private Node min(Node x) { 
        if (x.left==null) {
            return x;
            
        }
         return min(x.left);
    } 
    public Key floor(Key key) {
        Node n=floor(root,key);
       if (n==null) {
        System.out.println("floor value does not exist");
        }
        else{
            return key;            
        }
        return n.key;
        
    }
    private Node floor(Node x, Key key){
        if (x == null) return null;
        
        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            return floor(x.left, key);
        else if (cmp > 0) {
            Node t = floor(x.right, key);
            if (t == null) 
                return x;
            else
                return t;
        }
        return x;

    } 

    // private Node floor(Node x, Key key) {
    //     return null;
       
    // } 


   public Key select(int rank) {
        return select(root, rank);
    }

    private Key select(Node x, int rank) {
        if (x == null) return null;
        
        int t = size(x.left);
        if(rank < t) return select(x.left, rank);
        else if (rank > t)
            return select(x.right, rank-t-1);
        return x.key;
    }


    public void delete(Key key){
        root=delete(root,key);
    }


    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        
        if (cmp > 0) 
        {
            x.right = delete(x.right, key);
        } else if (cmp < 0)
        {
            x.left = delete(x.left, key);
        } 
        else 
        {
            
            if (x.left == null) 
                return x.right;
            else if (x.right == null) 
                return x.left;
            else {
                
                Node t = x.right;
                x = min(t.right);
                x.right = delete(t.right,key);
                x.left = t.left; 
            }
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> link=new LinkedList<Key>();
        keys(root,link,lo,hi);
        return link;
     
    } 

    private void keys(Node x,LinkedList<Key> link, Key lo, Key hi) {
    if(x==null) return;
    int clo=lo.compareTo(x.key);
    int chi=hi.compareTo(x.key);

    if(clo<0) keys(x.left,link,lo,hi);
    if(clo<= 0 && chi >=0) link.add(x.key);
    if(chi>0) keys(x.right,link,lo,hi); 
        
    }
    public static void main(String[] args) { 

        BST <String, Integer> bst=new BST<String, Integer>();
        bst.put("Ada",1);
        bst.put("Ballerina",3);
        System.out.println(bst.get("Ada"));
        bst.put("Html",5);
        bst.put("Java",7);
        System.out.println(bst.get("Java"));
        System.out.println(bst.size());
        System.out.println(bst.min());
        System.out.println(bst.floor("Ballerina"));
        System.out.println(bst.select(3));
        System.out.println(bst.keys("Ada","Java"));
        bst.put("Java",8);
        bst.put("Dart",9);
        System.out.println(bst.get("Java"));
        System.out.println(bst.size());
       // System.out.println(bst.delete());
        System.out.println(bst.keys("Ballerina","Java"));
        bst.delete("Java");



       
       
    }
}


