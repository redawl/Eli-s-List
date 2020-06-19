/*
Misc.java holds the implementation of the Misc class, which is derived from LLL_node. It has a category, and an additional_info.
It can display, set, edit, save, and load
 */
import java.io.*;

public class Misc extends LLL_node{
    private String category;//holds the misc category
    private String additional_info;//holds any additional info the provider might want to convey to the client

    //default constructor
    public Misc(){}

    //copy constructor
    public Misc(Misc a_misc){
        super(a_misc);
        category = a_misc.category;
        additional_info = a_misc.additional_info;
    }

    //display
    public void display(){
        super.display();
        System.out.println("Category: " + category);
        System.out.println("Additional Info: " + additional_info);
    }

    //set, gives user no choice in which fields get edited
    public void set() throws IOException {
        super.set();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter category: ");
        category = in.readLine();
        System.out.print("Enter addition info: ");
        additional_info = in.readLine();
    }

    //edit, user gets to choose which fields to edit
    public void edit() throws IOException {
        super.edit();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Would you like to change the category?");
        System.out.print("(y/n): ");
        char choice = (char) in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new category: ");
            category = in.readLine();
            System.out.println("Category changed...");
        }
        else{
            System.out.println("Category was not changed...");
        }
    }

    //save
    public void save(PrintStream output){
        super.save(output);
        output.println(category);
        output.println(additional_info);
    }

    //load
    public void load(BufferedReader input) throws IOException {
        super.load(input);
        category = input.readLine();
        additional_info = input.readLine();
    }
}
