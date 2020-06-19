/*
Tree.java holds the implementation for Tree, the red/black tree manager. It has a root and nil. It can add,
remove data from the linked list in a node, display in order, and load and save.
 */
import java.io.*;

public class Tree {
    private Balanced_node root;//This is the root of the RB tree
    private Balanced_node nil;//This is the RB version of null, which we have to create so we can set it to black

    //default constructor
    public Tree(){
        nil = new Balanced_node();//RB version of null is created
        nil.set_color(Color.BLACK);
        root = nil;//set root to nil
    }

    //rotate_left does a left rotation on the passed in node. It makes rotates right node the new parent node, and moves
    //everything around to make this work
    public void rotate_left(Balanced_node rotate){
        Balanced_node temp = rotate.go_right();//This will be the new parent
        rotate.connect_right(temp.go_left());
        if(temp.go_left() != nil){
            temp.go_left().connect_parent(rotate);
        }
        temp.connect_parent(rotate.go_parent());
        if(rotate.go_parent() == nil){
            this.root = temp;
        }
        else if(rotate == rotate.go_parent().go_left()){
            rotate.go_parent().connect_left(temp);
        }
        else{
            rotate.go_parent().connect_right(temp);
        }
        temp.connect_left(rotate);
        rotate.connect_parent(temp);
        //temp is now the new parent, with rotate as its left reference
    }

    //This will do the same as rotate_left, but backwards. rotates left reference will become the new parent
    public void rotate_right(Balanced_node rotate){
        Balanced_node temp = rotate.go_left();
        rotate.connect_left(temp.go_right());
        if(temp.go_right() != nil){
            temp.go_right().connect_parent(rotate);
        }
        temp.connect_parent(rotate.go_parent());
        if(rotate.go_parent() == nil){
            this.root = temp;
        }
        else if(rotate == rotate.go_parent().go_right()){
            rotate.go_parent().connect_right(temp);
        }
        else{
            rotate.go_parent().connect_left(temp);
        }
        temp.connect_right(rotate);
        rotate.connect_parent(temp);
    }

    //This fixes the added node to be the way it should be in a RB tree to keep it balanced
    public void add_fix(Balanced_node to_fix){
        if(to_fix.go_parent().get_color() == Color.RED){//if to_fix's parent is red
            if(to_fix.go_parent() == to_fix.go_parent().go_parent().go_left()){//if to_fix's parent is the left child
                Balanced_node uncle = to_fix.go_parent().go_parent().go_right();//uncle is right child

                if(uncle.get_color() == Color.RED){//if uncle is red
                    to_fix.go_parent().set_color(Color.BLACK);
                    uncle.set_color(Color.BLACK);
                    to_fix.go_parent().go_parent().set_color(Color.RED);
                    add_fix(to_fix.go_parent().go_parent());//call add_fix on grandparent
                }
                else{//if uncle is black
                    if(to_fix == to_fix.go_parent().go_right()){//if to_fix is right child
                        Balanced_node temp = to_fix.go_parent();
                        rotate_left(temp);//rotate temp
                        temp.go_parent().set_color(Color.BLACK);
                        temp.go_parent().go_parent().set_color(Color.RED);
                        rotate_right(temp.go_parent().go_parent());//rotate temps grandparent
                    }
                    else{//to_fix is left child
                        to_fix.go_parent().set_color(Color.BLACK);
                        to_fix.go_parent().go_parent().set_color(Color.RED);
                        rotate_right(to_fix.go_parent().go_parent());//rotate to_fix's grandparent
                    }
                }
            }
            else{//to_fix's parent is right child
                Balanced_node uncle = to_fix.go_parent().go_parent().go_left();//uncle is left child

                if(uncle.get_color() == Color.RED){//if uncle is red
                    to_fix.go_parent().set_color(Color.BLACK);
                    uncle.set_color(Color.BLACK);
                    to_fix.go_parent().go_parent().set_color(Color.RED);
                    add_fix(to_fix.go_parent().go_parent());//call add_fix on to_fix's grandparent
                }
                else{//if uncle is black
                    if(to_fix == to_fix.go_parent().go_left()){//if to_fix is left child
                        Balanced_node temp = to_fix.go_parent();
                        rotate_right(temp);//rotate temp
                        temp.go_parent().set_color(Color.BLACK);
                        temp.go_parent().go_parent().set_color(Color.RED);
                        rotate_left(temp.go_parent().go_parent());//rotate temps grandparent
                    }
                    else{//if to_fix is right child
                        to_fix.go_parent().set_color(Color.BLACK);
                        to_fix.go_parent().go_parent().set_color(Color.RED);
                        rotate_left(to_fix.go_parent().go_parent());//rotate to_fix's grandparent
                    }
                }
            }
        }
        else{//if to_fix's parent is black
            this.root.set_color(Color.BLACK);//set the roots color to BLACK
        }
    }

    //add
    public void add(LLL_node to_add){
        if(root == nil){//if tree is empty
            Balanced_node temp = new Balanced_node(to_add);
            temp.connect_left(nil);
            temp.connect_right(nil);
            temp.connect_parent(nil);
            root = temp;
            add_fix(root);
        }
        else{//if tree is not empty
            add(root, to_add);
        }
    }

    //add rec
    private void add(Balanced_node root, LLL_node to_add){
        if(root.compare_rate(to_add) == 0){//we want to add to_add to roots linked list
            root.add(to_add);
        }
        else {//we have to create new Balanced_node

            if (root.go_left() == nil) {
                if (root.compare_rate(to_add) > 0) {//if left reference is nil and we want to add to it
                    Balanced_node temp = new Balanced_node(to_add);
                    root.connect_left(temp);
                    temp.connect_parent(root);
                    temp.connect_left(nil);
                    temp.connect_right(nil);
                    add_fix(temp);
                }
            }
            else{//recurse
                if(root.compare_rate(to_add) > 0){
                    add(root.go_left(), to_add);
                }
            }

            if(root.go_right() == nil){
                if(root.compare_rate(to_add) < 0){//if right reference is nil and we want to add to it
                    Balanced_node temp = new Balanced_node(to_add);
                    root.connect_right(temp);
                    temp.connect_parent(root);
                    temp.connect_right(nil);
                    temp.connect_left(nil);
                    add_fix(temp);
                }
            }
            else{
                if(root.compare_rate(to_add) < 0){//we must recurse
                    add(root.go_right(), to_add);
                }
            }
        }
    }

    //display
    public void display(){
        if (root == nil) {
            System.out.println("There are no listings to display...");
        }
        else{
            display(root);
        }
    }

    //display rec
    private void display(Balanced_node root){
        if(root != nil){
            display(root.go_left());
            root.display();
            display(root.go_right());
        }
    }

    //display by rate
    public void display_by_rate(int rate){
        if(root == null){
            System.out.println("There are no listings to display!");
        }
        else{
            display_by_rate(root, rate);
        }
    }

    //display by rate recursive
    private void display_by_rate(Balanced_node root, int rate){
        if(root != null){
            if(root.compare_rate(rate) < 0){
                display_by_rate(root.go_left(), rate);//recurse
            }
            else if(root.compare_rate(rate) > 0){
                display_by_rate(root.go_right(), rate);//recurse
            }
            else{
                root.display();
            }
        }
    }

    //retrieve
    public LLL_node retrieve(String to_edit){
        if(root == nil){
            System.out.println("That Listing does not exist!");
            return null;
        }
        else{
            return retrieve(root, to_edit);
        }
    }

    //retrieve rec
    private LLL_node retrieve(Balanced_node root, String to_edit){
        if(root != nil){
            LLL_node to_retrieve = retrieve(root.go_left(), to_edit);
            if(to_retrieve != null){
                return to_retrieve;
            }
            else{
                to_retrieve = root.retrieve(to_edit);
                if(to_retrieve != null){
                    return to_retrieve;
                }
                else{
                    return retrieve(root.go_right(), to_edit);
                }
            }
        }
        else{
            return null;
        }
    }

    //save
    public void save(PrintStream output){
        save(root, output);
    }

    //save rec
    private void save(Balanced_node root, PrintStream output){
        if(root != nil){
            root.save(output);
            save(root.go_left(), output);
            save(root.go_right(), output);
        }

    }

    //load
    public void load(BufferedReader input) throws IOException {
            String trash = input.readLine();
            if(trash != null) {
                LLL_node to_add = null;
                if (trash.equals("Yardwork")) {
                    to_add = new Yardwork();
                } else if (trash.equals("Tutoring")) {
                    to_add = new Tutoring();
                } else if (trash.equals("Misc")) {
                    to_add = new Misc();
                }
                if (to_add != null) {
                    to_add.load(input);
                    add(to_add);

                    load(input);
                }
            }
    }

    //remove
    public boolean remove(String to_remove){
        if(root == nil){
            System.out.println("There is no listing with that title...");
            return false;
        }
        else{
            return remove(root, to_remove);
        }
    }

    //remove rec
    private boolean remove(Balanced_node root, String to_remove){
        if(root != nil){
            if(root.remove(to_remove) != true){
                if(remove(root.go_left(), to_remove) != true){
                    return remove(root.go_right(), to_remove);
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

}
