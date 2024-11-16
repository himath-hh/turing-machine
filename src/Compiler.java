import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Himath Helessage
 * The Compiler class reads an *.atm file and an input value and outputs the result
 */
public class Compiler {
    /**
     * This method is responsible for starting the Turing Machine and reading the *.atm file
     * @param fileName stores the name of the *.atm file
     * @return a turing machine with the states and transitions pulled from the *.atm file
     * @throws FileNotFoundException if the file does not exist
     */
    public static TuringMachine runTM(String fileName) throws FileNotFoundException {
        //reads the file using a scanner
        Scanner file = new Scanner(new File(fileName));
        
        TuringMachine turingMachine = null;     //creates a new empty turing machine
        String blank = "";                      //initializes blank character
        String start = "";                      //initializes start state
        
        //loops until the end of the file
        while (file.hasNextLine()) {
            //reads a line of the file and trims any trailing spaces
            String line = file.nextLine().trim();
            
            //skips empty lines, name and description
            if (line.isEmpty() || line.startsWith("Name:") || line.startsWith("Description")) {
                continue;
            
            //extracts blank value
            } else if (line.startsWith("Blank:")) {
                blank = line.substring(6).trim();

            //extracts start state
            } else if (line.startsWith("Start:")) {
                start = line.substring(6).trim();

            //a new state is found
            } else if (line.startsWith("$")) {

                String stateName = line.substring(1).trim();    //extracts the name of the state
                State state = new State();                                 //creates a new state
                
                //loops through a state
                while (file.hasNextLine()) {
                    line = file.nextLine().trim();

                    //exits when a blank line - end of state transitions reached
                    if (line.isEmpty()) {
                        break;
                    }

                    //splits a transition using blanks
                    String[] parts = line.split(" ");

                    //stores each of the parts in corresponding value strings
                    String read = parts[0].split(":")[1];
                    String write = parts[1].split(":")[1];
                    String move = parts[2].split(":")[1];
                    String nextState = parts[3].split(":")[1];

                    //adds the transition to the state
                    state.addTransition(read.charAt(0), write, move, nextState);
                }
                
                //initializes the turing machine with required fields
                if (turingMachine == null) {
                    turingMachine = new TuringMachine(blank, start, "Halt");
                }

                //adds the state extracted to the turing machine
                turingMachine.addState(stateName, state);
            }
        }
        
        if (turingMachine != null) {
            //sets the initial state to the extracted start value
            turingMachine.setInitialState(start);
        }

        file.close();
        return turingMachine;
    }

    /**
     * main method that runs the whole turing machine
     * @param args command-line arguments. 0 and 1 are passed as the *.atm file and input respectively
     */
    public static void main(String[] args) {
        try {
            String fileName = args[0];      //collects the file name as the first argument
            String number = args[1];        //collects the input as the second argument

            //initializes the turing machine with the file name provided
            TuringMachine turingMachine = runTM(fileName);

            //passes the input through the machine and outputs the result
            System.out.println(turingMachine.Read(number));

        } catch (FileNotFoundException e) {
            //outputs an error if the file is not found
            System.err.println("Error: File not found. " + e.getMessage());
        }
    }
}
