/*
Eli Burch
05/29/2020
Program #4
CS202
Main.java is the main driver for program #4. It loads from input.txt, displays the correct menus for the user,
and calls the correct functions the user chooses. It also loads and saves the deletion and search histories to history.txt
*/
import java.io.*;//For input and output

public class Main {
    public static void main(String [] args) throws IOException, NumberFormatException {
        BufferedReader input;//for loading info from input.txt
        input = new BufferedReader(new FileReader("input.txt"));

        Tree client = new Tree();//this will hold the users data
        client.load(input);//get listings from file

        BufferedReader in;//for getting info from the user
        in = new BufferedReader(new InputStreamReader(System.in));

        History client_history = new History();//Holds the search and deletion histories
        client_history.load("history.txt");//loads the histories from history.txt
        int option = -1;//for user menu choices
        System.out.println("Welcome to ElisList!");

        System.out.println("Are you a:");
        System.out.println("1. Provider");
        System.out.println("2. Customer");
        System.out.print("Enter choice >>");
        try {//error handling
            option = Integer.parseInt(in.readLine());
        }catch(NumberFormatException e){
            System.out.println("Invalid Input. Quitting...");
        }
        if(option == 1){
            while(option != 5){
                System.out.println("1. Add Listing");
                System.out.println("2. Edit Current Listing");
                System.out.println("3. Remove Listing");
                System.out.println("4. Display All Listings");
                System.out.println("5. Quit");
                try {//error handling
                    option = Integer.parseInt(in.readLine());
                }
                catch(NumberFormatException e){
                    option = 10;//make it so the next menu doesn't run
                }
                switch (option){
                    case 1://add
                        System.out.println("1. Add a yard work service");
                        System.out.println("2. Add a tutoring service");
                        System.out.println("3. Add a miscellaneous service");
                        option = Integer.parseInt(in.readLine());
                        LLL_node to_add = null;
                        switch(option){
                            case 1:
                                to_add = new Yardwork();
                                break;
                            case 2:
                                to_add = new Tutoring();
                                break;
                            case 3:
                                to_add = new Misc();
                                break;
                            default:
                                break;
                        }
                        if(to_add != null) {
                            to_add.set();
                            client.add(to_add);
                        }
                        break;
                    case 2://edit
                        System.out.print("Please enter title of listing to edit: ");
                        String temp = in.readLine();
                        LLL_node to_edit = client.retrieve(temp);
                        if(to_edit != null)
                            to_edit.edit();
                        break;
                    case 3://delete
                        System.out.print("Enter the title of the Listing you want to delete: ");
                        temp = in.readLine();
                        client_history.add_deleted(client.retrieve(temp));
                        if(client.remove(temp) == true){
                           System.out.println(temp + " was deleted...");
                        }
                        break;
                    case 4://display
                        client.display();
                        break;
                    case 5://quit and save
                        //user wants to quit
                        System.out.println("Quitting...");
                        PrintStream output;//saving client to input.txt
                        output = new PrintStream(new FileOutputStream("input.txt"));
                        client.save(output);
                        output.flush();
                        output.close();

                        client_history.save("history.txt");//save histories to history.txt
                        break;
                    default://if user chooses a number that is not a valid option
                        System.out.println("Invalid option. Please enter valid option: ");
                        break;
                }
            }
        }
        else if(option == 2){//customer menu

            option = -1;//for input from the user
            while(option != 6) {
                System.out.println("1. View all listings");
                System.out.println("2. View all listings with same hourly rate");
                System.out.println("3. View history of searched listings");
                System.out.println("4. View recently Deleted");
                System.out.println("5. Search for a listing by title");
                System.out.println("6. Quit");
                try {//error handling
                    option = Integer.parseInt(in.readLine());
                } catch (NumberFormatException e) {
                    option = 10;//make sure nothing in the next menu runs
                }

                switch (option) {
                    case 1://display all
                        client.display();
                        break;
                    case 2://display by rate
                        System.out.println("What rate do you want to view?: ");
                        try {
                            client.display_by_rate(Integer.parseInt(in.readLine()));
                        }
                        catch(NumberFormatException e){
                            System.out.println("There are no listings with that rate!");
                        }
                        break;
                    case 3://display history of retrieved listings
                        client_history.display_retrieved();
                        break;
                    case 4://display history of deleted listings
                        client_history.display_deleted();
                        break;
                    case 5://search for a listing by title
                        String temp;
                        System.out.print("What listing do you want to search");
                        temp = in.readLine();
                        LLL_node retrieved = client.retrieve(temp);
                        retrieved.display();

                        LLL_node new_node = null;//add the listing that was retrieved to the retrieved listing history
                        if(retrieved instanceof Yardwork){
                            new_node = new Yardwork((Yardwork)retrieved);
                        }
                        else if(retrieved instanceof Tutoring){
                            new_node = new Tutoring((Tutoring)retrieved);
                        }
                        else{
                            new_node = new Misc((Misc)retrieved);
                        }
                        client_history.add_retrieved(new_node);
                        break;
                    case 6://quit
                        System.out.println("Quitting...");
                        PrintStream output;//save client to input.txt
                        output = new PrintStream(new FileOutputStream("input.txt"));
                        client.save(output);
                        output.flush();
                        output.close();
                        client_history.save("history.txt");//save history to history.txt
                        break;
                    default:
                        System.out.println("Invalid option. Please Enter a valid option.");
                        break;
                }
            }
        }
    }


}
