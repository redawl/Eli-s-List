/*
Balanced_node.java holds the implementation of Balanced_node. It has a head reference for the linked list inside of it,
a color, and a right left and parent reference.
 */
import java.io.*;

public class Balanced_node {
    private LLL_node head;//head contains the linked list of Listings, sorted by title
    private Color color;//holds the color of the node
    private Balanced_node right;//right node reference
    private Balanced_node left;//left node reference
    private Balanced_node parent;//parent node reference

    //default constructor
    public Balanced_node() {
        parent = null;
        left = null;
        right = null;
        color = Color.RED;//Each node added starts out as RED
        head = null;
    }

    //copy constructor
    public Balanced_node(LLL_node to_copy) {
        head = to_copy;

        parent = null;
        right = null;
        left = null;
        color = Color.RED;
    }

    //get_color
    public Color get_color() {
        return color;
    }

    //set_color
    public void set_color(Color color) {
        this.color = color;
    }

    //go_left
    public Balanced_node go_left() {
        return left;
    }

    //go_parent
    public Balanced_node go_parent() {
        return parent;
    }

    //go_right
    public Balanced_node go_right() {
        return right;
    }

    //connect_left
    public void connect_left(Balanced_node connection) {
        left = connection;
    }

    //connect_right
    public void connect_parent(Balanced_node connection) {
        parent = connection;
    }

    //connect_right
    public void connect_right(Balanced_node connection) {
        right = connection;
    }

    //display
    public void display() {
        if (head == null) {//if theres nothing in the linked list
            System.out.println("There are no listings to display...");
        } else {
            display(head);//call recursive display
        }
    }

    //display rec
    private void display(LLL_node head) {
        if (head != null) {
            head.display();//LLL_node display
            display(head.go_next());//recursive call
        }
    }

    //add
    public void add(LLL_node to_add){
        if(head == null){
            head = to_add;
        }
        else{
            add(head, to_add);//call recursive function
        }
    }

    //add rec
    private void add(LLL_node head, LLL_node to_add){
        if(head.go_next() != null){
            if(head.compare_title(to_add) > 0){
                add(head.go_next(), to_add);//recurse
            }
            else{
                to_add.connect_next(head.go_next());
                head.connect_next(to_add);
            }
        }
        else {
            head.connect_next(to_add);
        }
    }

    //compare_rate for LLL_node
    public int compare_rate(LLL_node to_compare){
        if(head == null) {//if head = null, you cant directly compare its rate, so we make decision based on whats in the right and left references
            if (left != null && right != null && left.compare_rate(to_compare) < 0 && right.compare_rate(to_compare) > 0) {
                return 0;
            } else if (left != null && left.compare_rate(to_compare) > 0) {
                return -1;
            } else if (right != null && right.compare_rate(to_compare) < 0) {
                return 1;
            }
            else{
                return 0;
            }
        }
        else {
            if (head.compare_rate(to_compare) == 0) {
                return 0;
            } else if (head.compare_rate(to_compare) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    //compare_rate for int
    public int compare_rate(int rate){
        return head.compare_rate(rate);
    }

    //retrieve
    public LLL_node retrieve(String to_edit){
        if(head == null){
            return null;
        }
        else{
            return retrieve(head, to_edit);//call recursive function
        }
    }

    //retrieve rec
    private LLL_node retrieve(LLL_node head, String to_edit){
        if(head != null){
            if(head.compare_title(to_edit) == 0){
                return head;
            }
            else{
                return retrieve(head.go_next(), to_edit);//recurse
            }
        }
        else{
            return null;
        }
    }

    //remove
    public boolean remove(String to_remove){
        if(head == null){
            return false;
        }
        else if(head.go_next() == null){
            head = null;
            return true;
        }
        else{
            return remove(head, to_remove);//call recursive function
        }
    }

    //remove
    private boolean remove(LLL_node head, String to_remove){
        if(head.go_next() != null){
            if(head.go_next().compare_title(to_remove) == 0){
                head.connect_next(head.go_next().go_next());
                return true;
            }
            else{
                return remove(head.go_next(), to_remove);//recurse
            }
        }
        else{
            return false;
        }
    }

    //save
    public void save(PrintStream output){
        save(head, output);//call recursive function
    }

    //save rec
    private void save(LLL_node head, PrintStream output){
        if(head != null){
            if(head instanceof Yardwork){
                output.println("Yardwork");
            }
            else if(head instanceof Tutoring){
                output.println("Tutoring");
            }
            else if(head instanceof Misc){
                output.println("Misc");
            }
            head.save(output);
            save(head.go_next(), output);//recurse
        }
    }
}
