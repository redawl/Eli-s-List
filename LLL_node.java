/*
LLL_node.java holds the definition and implementation of class LLL_node, which is derived from Listing, and has Yardwork,
Tutoring, and Misc derived from it. It is used to build linked lists out of Listings.
It has a next reference, and allows you to go_next and connect_next.
 */

public class LLL_node extends Listing{
    private LLL_node next;//for traversing to the next LLL_node

    //default constructor
    public LLL_node(){
        super();
        next = null;
    }

    //copy constructor
    public LLL_node(LLL_node a_node){
        super(a_node);
        next = null;
    }

    //go_next()
    public LLL_node go_next(){
        return next;
    }

    //connect_next()
    public void connect_next(LLL_node connection){
        next = connection;
    }
}
