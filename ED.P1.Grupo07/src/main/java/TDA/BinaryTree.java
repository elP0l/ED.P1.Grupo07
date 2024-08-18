/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author vecto
 */
public class BinaryTree <E> {
    private NodeBinaryTree<E> root;

    public BinaryTree() {
        this.root=null;
    }
    
    public BinaryTree(NodeBinaryTree<E> root) {
        this.root=root;
    }
    
    public void recorrerPreorden(){
        if (!this.isEmpty()) {
            System.out.println(this.root.getContent());
            if (root.getLeft()!=null) {
                root.getLeft().recorrerPreorden(); 
            }
            if (root.getRight()!=null) {
                root.getRight().recorrerPreorden(); 
            }
        }
    }
    
    public void recorrerPostorden(){
        if (!this.isEmpty()){
            if (root.getLeft()!=null){
                root.getLeft().recorrerPostorden(); 
            }
            if (root.getRight()!=null){
                root.getRight().recorrerPostorden(); 
            }
            System.out.println(this.root.getContent());
        }
    }
    
    public void recorrerEnorden(){
        
        if (!this.isEmpty()){
            if (root.getLeft()!=null){
                root.getLeft().recorrerEnorden(); 
            }
            System.out.println(this.root.getContent());
            if (root.getRight()!=null){
                root.getRight().recorrerEnorden(); 
            }
            
        }
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean isLeaf(){
        if (!this.isEmpty()){
            return root.getLeft() == null && root.getRight() == null;
        }
        return false;
    }
    
    public NodeBinaryTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeBinaryTree<E> root) {
        this.root = root;
    }
    public int countDescendantsRecursive(){
        if(!this.isEmpty()){
            int lDescendants = 0;
            int rDescendants = 0;
            if (this.getRoot().getLeft() != null) {
                lDescendants = 1 + this.getRoot().getLeft().countDescendantsRecursive();
            }
            if (this.getRoot().getRight() != null) {
                rDescendants = 1 + this.getRoot().getRight().countDescendantsRecursive();
            }
            return lDescendants + rDescendants;
        } else {
            return 0;
        }
    }    
    public int countDescendantsIterative(){
        if(!this.isEmpty()){
            Stack<NodeBinaryTree> pilaArboles = new Stack<>();
            pilaArboles.push(root);
            int count = 0;
            while(!pilaArboles.isEmpty()){
                NodeBinaryTree node = pilaArboles.pop();
                if(node.getLeft()!=null){
                    pilaArboles.push(node.getLeft().getRoot());
                    count++;
                }
                if(node.getRight()!=null){
                    pilaArboles.push(node.getRight().getRoot());
                    count++;
                }
            }
            return count;
        }else{
            return 0;
        }
    }
    public NodeBinaryTree findParentRecursive(NodeBinaryTree rootsito,NodeBinaryTree nodoBuscado){
        
        if (rootsito == null || rootsito == nodoBuscado) {
            return null;
        }

        if ((rootsito.getLeft()!=null && rootsito.getLeft().getRoot().equals(nodoBuscado)) ||
            (rootsito.getRight()!=null && rootsito.getRight().getRoot().equals(nodoBuscado))) {
            return rootsito;
        }

        NodeBinaryTree padre = null;
        if (rootsito.getLeft() != null) {
            padre = findParentRecursive(rootsito.getLeft().getRoot(), nodoBuscado);
        }
        if (padre == null && rootsito.getRight() != null) {
            padre = findParentRecursive(rootsito.getRight().getRoot(), nodoBuscado);
        }

        return padre;

    }
    public NodeBinaryTree findParentIterative(NodeBinaryTree nodoBuscado){
        if(this.isEmpty() || nodoBuscado==root){
            return null;
        }
        Stack<NodeBinaryTree> pilaArboles = new Stack<>();
        pilaArboles.push(root);
        while(!pilaArboles.isEmpty()){
            NodeBinaryTree nodoPadre = pilaArboles.pop();
            
                if((nodoPadre.getLeft()!=null && nodoPadre.getLeft().getRoot().equals(nodoBuscado)) || (nodoPadre.getRight()!=null && nodoPadre.getRight().getRoot().equals(nodoBuscado))){
                    return nodoPadre;
                }
                if(nodoPadre.getLeft()!=null){
                    pilaArboles.push(nodoPadre.getLeft().getRoot());
                }
                if(nodoPadre.getRight()!=null){
                    pilaArboles.push(nodoPadre.getRight().getRoot());
                }
            
        }
        return null;
    }
    public int countLevelsRecursive(){
        
        if (this.isEmpty()) {
            return 0;
        }
        if (this.getRoot().getLeft()==null || this.getRoot().getRight()==null) {
            return 1;
        }
        int leftLevels = this.getRoot().getLeft().countLevelsRecursive();
        int rightLevels = this.getRoot().getRight().countLevelsRecursive();
        return Math.max(leftLevels, rightLevels) + 1;
        
    }
    public int countLevelsIterative() {
        if (root == null) {
            return 0;
        }
        int levels = 0;
        Queue<NodeBinaryTree<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            levels++;
            for (int i = 0; i < levelSize; i++) {
                NodeBinaryTree<E> current = queue.poll();
                if (current.getLeft() != null) {
                    queue.add(current.getLeft().getRoot());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight().getRoot());
                }
            }
        }

        return levels;
    }
    public boolean isLeftyRecursive() {
        if (this == null) {
            return true;
        }
        if (this.isLeaf()) {
            return true;
        }
        if (root.getLeft() == null) {
            return false;
        }
        boolean left = root.getLeft().isLeftyRecursive();
        boolean right = true;
        if (root.getRight() != null) {
            right = root.getRight().isLeftyRecursive();
        }
        if (left && right) {
            int izq = root.getLeft().countDescendantsIterative()+1;
            int dere = 0;
            if(root.getRight()!=null){
                dere = root.getRight().countDescendantsIterative();       
            }
            if (izq > dere) {   
                return true;
            }
        }
        return false;
    }
    public boolean isLeftyIterative() {
        if (this == null || this.isEmpty()) {
            return true;
        }
        if(this.isLeaf()){
            return true;
        }
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            BinaryTree<E> current = queue.poll();
            if (current.isLeaf()) {
                continue;
            }

            BinaryTree<E> leftSubtree = current.getRoot().getLeft();
            BinaryTree<E> rightSubtree = current.getRoot().getRight();

            if (leftSubtree == null) {
                return false;
            }
            if (leftSubtree != null) {
                queue.add(leftSubtree);
            }
            if (rightSubtree != null) {
                queue.add(rightSubtree);
            }
        }

        int izq = root.getLeft().countDescendantsIterative()+1;
        int dere = 0;
        if(root.getRight()!=null){
            dere = root.getRight().countDescendantsIterative();
        }
        return izq > dere;
    }
    public boolean isIdenticalRecursive(NodeBinaryTree<E> node1, NodeBinaryTree<E> node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (!node1.getContent().equals(node2.getContent())) {
            return false;
        }
        NodeBinaryTree<E> left1 = null;
        NodeBinaryTree<E> right1 = null;
        NodeBinaryTree<E> left2 = null;
        NodeBinaryTree<E> right2 = null;
        if (node1.getLeft() != null) {
            left1 = node1.getLeft().getRoot();
        }
        if (node1.getRight() != null) {
            right1 = node1.getRight().getRoot();
        }
        if (node2.getLeft() != null) {
            left2 = node2.getLeft().getRoot();
        }
        if (node2.getRight() != null) {
            right2 = node2.getRight().getRoot();
        }
        return isIdenticalRecursive(left1, left2) && isIdenticalRecursive(right1, right2);
    }
    public boolean isIdenticalIterative(BinaryTree<E> tree1, BinaryTree<E> tree2) {
       if (tree1.isEmpty() && tree2.isEmpty()) {
           return true;
       }
       if (tree1.isEmpty() || tree2.isEmpty()) {
           return false;
       }
       Stack<NodeBinaryTree<E>> s1 = new Stack<>();
       Stack<NodeBinaryTree<E>> s2 = new Stack<>();
       s1.push(tree1.getRoot());
       s2.push(tree2.getRoot());
       while (!s1.isEmpty() && !s2.isEmpty()) {
           NodeBinaryTree<E> node1 = s1.pop();
           NodeBinaryTree<E> node2 = s2.pop();
           if (node1 == null && node2 == null) {
               continue; 
           }
           if (node1 == null || node2 == null) {
               return false; 
           }
           if (!node1.getContent().equals(node2.getContent())) {
               return false; 
           }
           if (node1.getLeft() != null) {
               s1.push(node1.getLeft().getRoot());
           } else {
               s1.push(null);
           }
           if (node1.getRight() != null) {
               s1.push(node1.getRight().getRoot());
           } else {
               s1.push(null);
           }
           if (node2.getLeft() != null) {
               s2.push(node2.getLeft().getRoot());
           } else {
               s2.push(null);
           }
           if (node2.getRight() != null) {
               s2.push(node2.getRight().getRoot());
           } else {
               s2.push(null);
           }
       }
       return s1.isEmpty() && s2.isEmpty();
    }
    public String largestValueOfEachLevelRecursive(){
        if (!this.isEmpty()) {
            if (this.isLeaf()) {
                return this.root.getContent().toString();
            }

            String LevelMax = this.root.getContent().toString() + " ";
            String left = "";
            String right = "";
            
            if (this.root.getLeft() != null) {
                left = this.root.getLeft().largestValueOfEachLevelRecursive();
            }            
            if (this.root.getRight() != null) {
                right = this.root.getRight().largestValueOfEachLevelRecursive();
            }
            
            String[] Maxleft = left.trim().split("\\s+");
            String[] MaxRight = right.trim().split("\\s+");
            
            int maxLength = Math.max(Maxleft.length, MaxRight.length);
            for (int i = 0; i < maxLength; i++) {
                int leftVal = (i < Maxleft.length && !Maxleft[i].isEmpty()) ? Integer.parseInt(Maxleft[i]) : Integer.MIN_VALUE;
                int rightVal = (i < MaxRight.length && !MaxRight[i].isEmpty()) ? Integer.parseInt(MaxRight[i]) : Integer.MIN_VALUE;
                if (i > 0) {
                    LevelMax += " ";
                }
                LevelMax += Math.max(leftVal, rightVal);
            }
            return LevelMax.trim();
        }
        return "";
       
    }
    public String largestValueOfEachLevelIterative(){
        String levels = "";
        
        Queue<BinaryTree<E>> q = new LinkedList<>();
        
        if(!this.isEmpty()){
            q.offer(this);
            
            while (!q.isEmpty()) {
                int levelSize = q.size();
                int Max = Integer.MIN_VALUE;
                for (int i = 0; i < levelSize; i++) {
                    BinaryTree<E> currentTree = q.poll();
                    if(Integer.parseInt(currentTree.getRoot().getContent().toString())>Max){
                        Max = Integer.parseInt(currentTree.getRoot().getContent().toString());
                    }
                    if (currentTree.root.getLeft() != null) {
                        q.offer(currentTree.root.getLeft());
                    }
                    if (currentTree.root.getRight() != null) {
                        q.offer(currentTree.root.getRight());
                    }     
                }
                levels += Max+" ";
            }
        }
        return levels;
    }
    public int countNodesWithOnlyChildRecursive(NodeBinaryTree<E> node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if ((node.getLeft() != null && node.getRight() == null) ||(node.getLeft() == null && node.getRight() != null)) {
            count++;
        }
        if (node.getLeft() != null) {
            count += countNodesWithOnlyChildRecursive(node.getLeft().getRoot());
        }
        if (node.getRight() != null) {
            count += countNodesWithOnlyChildRecursive(node.getRight().getRoot());
        }
        return count;
    }
    public int countNodesWithOnlyChildIterative() {
        if (this.isEmpty()) {
            return 0;
        }
        int count = 0;
        Stack<NodeBinaryTree<E>> stack = new Stack<>();
        stack.push(this.root);
        while (!stack.isEmpty()) {
            NodeBinaryTree<E> node = stack.pop();
            if ((node.getLeft() != null && node.getRight() == null) ||(node.getLeft() == null && node.getRight() != null)) {
                count++;
            }
            if (node.getRight() != null) {
                stack.push(node.getRight().getRoot());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft().getRoot());
            }
        }
        return count;
    }
    public boolean isHeightBalancedRecursive() {
        if (this.isEmpty()) return true; 
        boolean leftBalanced = (this.root.getLeft() == null) || this.root.getLeft().isHeightBalancedRecursive();
        boolean rightBalanced = (this.root.getRight() == null) || this.root.getRight().isHeightBalancedRecursive();
        int levelLeft = 0;
        int levelRight = 0;
        if (this.root.getLeft() != null) {
            levelLeft = this.root.getLeft().countLevelsRecursive();
        }
        if (this.root.getRight() != null) {
            levelRight = this.root.getRight().countLevelsRecursive();
        }
        if (Math.abs(levelLeft - levelRight) > 1) {
            return false;
        }
        return leftBalanced && rightBalanced;
    }
    public boolean isHeightBalancedIterative(){
        Queue<BinaryTree<E>> qu1 = new LinkedList<>();
        if(!this.isEmpty()){
            qu1.offer(this);
            while(!qu1.isEmpty()){
                BinaryTree<E> tree = qu1.poll();
                int leveliz=0;
                int leveldere=0;
                if(tree.getRoot().getLeft()!=null){
                    leveliz = tree.getRoot().getLeft().countLevelsIterative();
                    qu1.offer(tree.getRoot().getLeft());
                }
                if(tree.getRoot().getRight()!=null){
                    leveldere = tree.getRoot().getRight().countLevelsIterative();
                    qu1.offer(tree.getRoot().getRight());
                }
                if (Math.abs(leveliz - leveldere) > 1){
                    return false;
                }
            }
            return true;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinaryTree<?> other = (BinaryTree<?>) obj;
        return Objects.equals(this.root, other.root);
    }
    
    
    
}
