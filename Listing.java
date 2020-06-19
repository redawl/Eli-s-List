/*
Listing.java holds the definition and implementation of the base class Listing,
which LLL_node is derived from. It contains a title, a description, a rate, and a name.
It can display, set, edit and compare its title and rate to other listings and strings.
 */
import java.io.*;
public abstract class Listing {
    //private fields
    protected String title;
    protected String description;
    protected int rate;
    protected String name;

    //default constructor
    public Listing(){
        this.rate = 0;
    }

    //copy constructor
    public Listing(Listing a_listing){
        this.title = a_listing.title;
        this.description = a_listing.description;
        this.rate = a_listing.rate;
        this.name = a_listing.name;
    }

    //display
    public void display(){
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Pay: $" + rate + "/hr");
        System.out.println("Name of provider: " + name);
    }

    //set, makes user update every field
    public void set() throws IOException, NumberFormatException {
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Title: ");
        title = in.readLine();
        System.out.print("Enter description: ");
        description = in.readLine();
        System.out.print("Enter pay rate: ");
        try {
            rate = Integer.parseInt(in.readLine());
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input. Rate set to zero...");
        }
        System.out.print("Enter name of provider: ");
        name = in.readLine();
    }

    //edit, gives the user options of which fields they want to edit
    public void edit() throws IOException {

        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Would you like to change the title?");
        System.out.print("(y/n): ");
        char choice = (char) in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new title: ");
            title = in.readLine();
            System.out.println("Title was changed...");
        }
        else{
            System.out.println("Title was not changed...");
        }
        choice = 'n';
        System.out.println("Would you like to change the description?");
        System.out.print("(y/n): ");
        choice = (char)in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new description: ");
            description = in.readLine();
            System.out.println("Description was changed...");
        }
        else{
            System.out.println("Description was not changed...");
        }

        choice = 'n';
        System.out.println("Would you like to change rate of pay?");
        System.out.print("(y/n): ");
        choice = (char)in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new pay rate:");
            try {
                rate = Integer.parseInt(in.readLine());
            }
            catch(NumberFormatException e) {
                System.out.println("Pay rate changed...");
            }
        }
        else{
            System.out.println("Pay rate was not changed...");
        }
        choice = 'n';

        System.out.println("Would you like to change the name of the provider?");
        System.out.print("(y/n): ");
        choice = (char)in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new name: ");
            name = in.readLine();
            System.out.println("Name was changed...");
        }
        else{
            System.out.println("Name was not changed...");
        }
    }

    //compares title to passed in listings title
    public int compare_title(Listing a_listing){
        return title.compareTo(a_listing.title);
    }

    //compares title to passed in string
    public int compare_title(String to_compare){
        return title.compareTo(to_compare);
    }

    //compares rate to passed in listings rate
    public int compare_rate(Listing a_listing){
        if(rate == a_listing.rate)
            return 0;
        else if(rate > a_listing.rate)
            return 1;
        else
            return -1;
    }

    public int compare_rate(int rate){
        if(this.rate == rate){
            return 0;
        }
        else if(this.rate > rate){
            return 1;
        }
        else
            return -1;
    }

    //saves the fields to output
    public void save(PrintStream output){
        output.println(title);
        output.println(description);
        output.println(rate);
        output.println(name);
    }

    //loads data in input to the fields
    public void load(BufferedReader input) throws IOException, NumberFormatException {
        title = input.readLine();
        description = input.readLine();
        try {
            rate = Integer.parseInt(input.readLine());
        }
        catch(NumberFormatException e){
            System.out.println("Invalid Input. Setting rate to 0.");
        }
        name = input.readLine();
    }
}
