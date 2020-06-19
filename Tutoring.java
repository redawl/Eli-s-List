/*
Tutoring.java holds the implementation of Tutoring, which is derived from LLL_node. I has a topic and a grade level.
 It can display, edit, set, save, and load.
 */
import java.io.*;

public class Tutoring extends LLL_node{
    private String topic;//What topic the tutor is offering
    private int grade_level;//what grade level the tutor can teach up to

    //default constructor
    public Tutoring(){}

    //copy constructor
    public Tutoring(Tutoring a_tutor){
        super(a_tutor);
        topic = a_tutor.topic;
        grade_level = a_tutor.grade_level;
    }

    //display
    public void display(){
        super.display();
        System.out.println("Topic: " + topic);
        System.out.println("Grade Level: " + grade_level);
    }

    //edit, user can choose which fields they want to change
    public void edit() throws IOException, NumberFormatException {
        super.edit();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        char choice = 'n';
        System.out.println("Would you like to change the topic?");
        System.out.print("(y/n): ");
        choice = (char) in.read();

        if(choice == 'y'){
            System.out.print("Enter new topic: ");
            topic = in.readLine();
            System.out.println("Topic was changed...");
        }
        else{
            System.out.println("Topic was not changed...");
        }

        choice = 'n';

        System.out.println("Would you like to change grade level?: ");
        System.out.print("(y/n): ");
        choice = (char) in.read();

        if(choice == 'y'){
            System.out.print("Enter new grade level: ");
            grade_level = Integer.parseInt(in.readLine());
            System.out.println("Grade level was changed...");
        }
        else{
            System.out.println("Grade level was not changed...");
        }
    }

    //set, user must change every field
    public void set() throws IOException, NumberFormatException {
        super.set();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter topic: ");
        topic = in.readLine();
        System.out.println("Enter grade level: ");
        try {
            grade_level = Integer.parseInt(in.readLine());
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input. Grade level set to 0...");
        }
    }

    //save
    public void save(PrintStream output){
        super.save(output);//call based class save
        output.println(topic);
        output.println(grade_level);
    }

    //load
    public void load(BufferedReader input) throws IOException {
        super.load(input);
        topic = input.readLine();
        try {
            grade_level = Integer.parseInt(input.readLine());
        }
        catch(NumberFormatException e){
            System.out.print("Invalid input. Setting grade level to zero");
        }
    }
}
