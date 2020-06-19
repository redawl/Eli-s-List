/*
History.java holds the implementation of an array of linked lists. Index 0 is a history of all retrieved listings, and
index 1 holds a history of all deleted listings
 */
import java.io.*;

public class History {
    private static final int MAX_SIZE = 2;//size of the array of linked lists
    private static final int DELETED = 1;//index for list of deleted listings
    private static final int RETRIEVED = 0;//index for list of retrieved listings
    LLL_node [] list;//array of linked lists
    private int deleted_size;//size of index 1
    private int retrieved_size;//size of index 0

    //default constructor
    public History() {
        list = new LLL_node[MAX_SIZE];
        for(int i = 0; i < MAX_SIZE; i++){
            list[i] = null;
        }
        deleted_size = 0;
        retrieved_size = 0;
    }

    //copy constructor
    public History(History to_copy){
        for(int i = 0;i < MAX_SIZE; i++){
            list[i] = to_copy.list[i];
        }

        deleted_size = to_copy.deleted_size;
        retrieved_size = to_copy.retrieved_size;
    }

    //add_deleted
    public void add_deleted(LLL_node to_add){
        if(to_add != null) {
            if (list[DELETED] == null)
                list[DELETED] = to_add;
            else {
                LLL_node temp = list[DELETED];
                to_add.connect_next(temp);
                list[DELETED] = to_add;
                if (deleted_size > 10)
                    remove_end(list[DELETED]);
            }
            deleted_size++;
        }
    }

    //add_retrieved
    public void add_retrieved(LLL_node to_add){
        if(list[RETRIEVED] == null)
            list[RETRIEVED] = to_add;
        else{
            LLL_node temp = list[RETRIEVED];
            to_add.connect_next(temp);
            list[RETRIEVED] = to_add;
            if(retrieved_size > 10)
                remove_end(list[RETRIEVED]);
        }
        retrieved_size++;
    }

    //remove_end, for removing the last node in a list if the list has 11 items
    public void remove_end(LLL_node head){
        if(head.go_next().go_next() != null){
            remove_end(head.go_next());
        }
        else{
            head.connect_next(null);
        }
    }

    //display retrieved
    public void display_retrieved(){
        if (list[RETRIEVED] == null) {
            System.out.println("No listings have been searched!");
        }
        else{
            System.out.println("The last ten searched listings: ");
            display(list[RETRIEVED]);
        }
    }

    //display_deleted
    public void display_deleted(){
        if (list[DELETED] == null) {
            System.out.println("No listings have been deleted!");
        }
        else{
            System.out.println("The last ten deleted listings: ");
            display(list[DELETED]);
        }
    }

    //display, for displaying a linked list
    private void display(LLL_node head){
        if(head != null){
            head.display();
            display(head.go_next());
        }
    }

    //save, for saving lists to a file
    public void save(String file) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream(file));

        save(list[RETRIEVED], out);
        out.println("!!");
        save(list[DELETED], out);
    }

    //save recursive
    public void save(LLL_node head, PrintStream out){
        if(head != null){
            if(head instanceof Yardwork){
                out.println("Yardwork");
            }
            else if(head instanceof Tutoring){
                out.println("Tutoring");
            }
            else if(head instanceof Misc){
                out.println("Misc");
            }
            head.save(out);
            save(head.go_next(), out);//recurse
        }
    }

    //load, for loading info from a file
    public void load(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String trash;
        trash = in.readLine();
        if(trash != null) {
            while (trash.equals("!!") != true) {
                LLL_node to_add = null;
                if (trash.equals("Yardwork")) {
                    to_add = new Yardwork();
                } else if (trash.equals("Tutoring")) {
                    to_add = new Tutoring();
                } else if (trash.equals("Misc")) {
                    to_add = new Misc();
                }
                if (to_add != null) {
                    to_add.load(in);
                    add_retrieved(to_add);

                    trash = in.readLine();
                }
            }

            trash = in.readLine();
            while (trash != null) {
                LLL_node to_add = null;
                if (trash.equals("Yardwork")) {
                    to_add = new Yardwork();
                } else if (trash.equals("Tutoring")) {
                    to_add = new Tutoring();
                } else if (trash.equals("Misc")) {
                    to_add = new Misc();
                }

                if (to_add != null) {
                    to_add.load(in);
                    add_deleted(to_add);
                    trash = in.readLine();
                }
            }
        }
    }
}
