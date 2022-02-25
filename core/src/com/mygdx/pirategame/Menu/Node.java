package com.mygdx.pirategame;

public class Node{
    public int nodeCode;
    public LinkList branch;
    public int branchCounter;
    public Node(int nodeCode){
        this.nodeCode = nodeCode;
        this.branch = new LinkList();
        this.branchCounter = 0;
    }
    public Node GetLeft(){
        return(this.branch.Get(0));
    }
    public Node GetRight(){
        return(this.branch.Get(this.branch.GetLen()-1));
    }

    public Node GetBranch(int branchNumber){
        return(this.branch.Get(branchNumber));
    }

    public Node GetNextBranch(){
        Node returnValue = this.branch.Get(this.branchCounter);
        this.branchCounter++;
        if(this.branchCounter==this.branch.GetLen()){
            this.branchCounter=0;
        }
        return(returnValue);
    }

    public void AddBranch(int nodeCode){
        Node newNode = new Node(nodeCode);
        this.branch.Add(newNode);
    }

}

