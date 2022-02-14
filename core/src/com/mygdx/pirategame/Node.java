public class Node{
    public int nodeCode;
    public LinkedList branch;
    public int branchCounter;
    public Node(int nodeCode){
        this.nodeCode = nodeCode;
        this.branch = new LinkedList();
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


protected class LinkedList{
    public Node value;
    public LinkedList next;
    public LinkedList(){
        //
    }

    public LinkedList(Node node){
        this.value = node;
    }

    public void Add(Node node){
        if(this.value==null){
            this.value=node;
        }else{
            next = new LinkedList(node);
        }
    }

    public int GetLen(){
        if(this.next==null){
            return(1);
        }else{
            return(1+this.next.GetLen());
        }
    }
    
    public Node Get(int index){
        if(index==0){
            return(this.value);
        }else if(index==1 && this.next==null){
            return(null);
        }else{
            return(this.next.Get(index-1));
        }
    }
}