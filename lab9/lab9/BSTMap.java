package lab9;

import javax.crypto.SealedObject;
import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }

    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p==null){
            return null;
        }
        V re = null;
        int compare = key.compareTo(p.key);
        if(compare==0){
            re = p.value;
        }
        else if(compare>0){
            re = getHelper(key,p.right);
        }
        else{
            re = getHelper(key,p.left);
        }
        return re;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,this.root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        Node newnode;
        if (p==null){
            newnode = new Node(key,value);
        }
        else if (key.compareTo(p.key)>=0) {
            newnode = new Node(p.key, p.value);
            newnode.right = putHelper(key,value,p.right);
            newnode.left = p.left;
        }
        else{
            newnode = new Node(p.key,p.value);
            newnode.left = putHelper(key,value,p.left);
            newnode.right = p.right;
        }
        return newnode;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        size++;
        this.root = putHelper(key,value,this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private Set<K> keySetHelper(Node p){
        Set<K> set;
        set = new HashSet<>();
        if(p == null){
            return set;
        }
        Set<K> setleft;
        Set<K> setright;
        setleft = keySetHelper(p.left);
        setright = keySetHelper(p.right);
        set.addAll(setleft);
        set.addAll(setright);
        set.add(p.key);
        return set;
    }

    @Override
    public Set<K> keySet() {
        return keySetHelper(this.root);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node searchSubstitute(Node p){
        if (p.right==null){
            return p;
        }
        else{
            return searchSubstitute(p.right);
        }
    }
    private Node removeNode(Node p){
        if (p.left == null && p.right == null){
            return null;
        }
        if (p.left == null){
            return p.right;
        }
        else if (p.right == null){
            return p.left;
        }
        else {
            Node substitute = searchSubstitute(p.left);
            p.key = substitute.key;
            p.value = substitute.value;
            p.left = removeHelper(substitute.key,null,p.left,substitute);
            return p;
        }
    }
    private Node removeHelper(K key,V value,Node p,Node nodeToReturn){
        if (p==null)
            return null;
        int compare = key.compareTo(p.key);
        if (compare == 0){
            if (value != null && value != p.value){
                size--;
                p.right = removeHelper(key,value,p.right,nodeToReturn);
            }
            else{
                nodeToReturn.key = p.key;
                nodeToReturn.value = p.value;
                p = removeNode(p);
            }

        }
        else if(compare<0){
            p.left = removeHelper(key,value,p.left,nodeToReturn);
        }
        else{
            p.right = removeHelper(key,value,p.right,nodeToReturn);
        }
        return p;

    }
    @Override
    public V remove(K key){
        Node nodeToReturn = new Node(null,null);
        root = removeHelper(key,null,root,nodeToReturn);
        return nodeToReturn.value;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/

    @Override
    public V remove(K key, V value) {
        Node nodeToReturn = new Node(null,null);
        root = removeHelper(key,value,root,nodeToReturn);
        return nodeToReturn.value;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
