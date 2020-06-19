/*
Yardwork.java holds the implementation for class Yardwork, which is derived from LLL_node. It has a tools, and a services.
You can display, set, edit, save and load using this class
 */
import java.io.*;//For I/O

public class Yardwork extends LLL_node{
    private String tools;//This holds the tools required for this yardwork service
    private String services;//This holds the services the provider can do

    //default constructor
    public Yardwork(){}

    //copy constructor
    public Yardwork(Yardwork a_yard){
        super(a_yard);
        tools = a_yard.tools;
        services = a_yard.services;
    }

    //display
    public void display(){
        super.display();
        System.out.println("Required tools: " + tools);
        System.out.println("Services included: " + services);
    }

    //set, user has to update every field
    public void set() throws IOException {
        super.set();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter tools needed: ");
        tools = in.readLine();
        System.out.print("Enter services provided: ");
        services = in.readLine();
    }

    //edit, user can choose which fields they want to update
    public void edit() throws IOException {
        super.edit();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        char choice;
        System.out.println("Would you like to change the needed tools?");
        System.out.print("(y/n):");
        choice = (char) in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new tools: ");
            tools = in.readLine();
            System.out.println("Tools were changed...");
        }
        else{
            System.out.println("Tools were not changed...");
        }

        choice = 'n';

        System.out.println("Would you like to change the services provided?");
        System.out.print("(y/n): ");
        choice = (char) in.read();
        in.readLine();

        if(choice == 'y'){
            System.out.print("Enter new provided services: ");
            services = in.readLine();
            System.out.println("Services were changed...");
        }
        else{
            System.out.println("Services were not changed...");
        }
    }

    //saves fields to output
    public void save(PrintStream output){
        super.save(output);
        output.println(tools);
        output.println(services);
    }

    //load, loads data from input into fields
    public void load(BufferedReader input) throws IOException {
        super.load(input);
        tools = input.readLine();
        services = input.readLine();
    }
}
