package com.mygdx.pirategame.Menu;
public class LinkList{
    public Node value;
    public LinkList next;
    public LinkList(){
        //
    }

    public LinkList(Node node){
        this.value = node;
    }

    public void Add(Node node){
        if(this.value==null){
            this.value=node;
        }else if(this.next==null){
            this.next = new LinkList(node);
        }else{
            this.next.Add(node);
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
        }else if(this.next==null){
            return(null);
        }else{
            return(this.next.Get(index-1));
        }
    }
}