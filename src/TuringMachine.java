import java.util.HashMap;

/**
 * @author Himath Helessage
 * the TuringMachine class reads the tape and modifies it according to the *.atm file and provides an output
 */
public class TuringMachine {
    private HashMap<String, State> states;      //stores the states of the turing machine
    
    //stores the states of the turing machine
    private String initialState;
    private String currentState;
    private String haltingState;

    //constant values for transition array so no hard-code indices
    private static final int WRITE = 0;
    private static final int MOVE = 1;
    private static final int CURRENT_STATE = 2;

    //stores the blank character
    private String blank;
    
    //variables required for the tape
    private int tapeIndex;
    private StringBuilder tape;

    /**
     * constructor to create a new turing machine with the blank symbol and initial and halting states
     * @param blank blank character on the tape
     * @param initialState name of the start state
     * @param haltingState name of the end state
     */
    public TuringMachine(String blank, String initialState,String haltingState) {
        states = new HashMap<>();           //initializes the states

        //sets the initial and halting states
        this.initialState = initialState;
        this.haltingState = haltingState;

        this.blank = blank;
        this.tapeIndex = 0;                 //tape index is set to start of the tape
    }

    /**
     * sets the intial state of the turing machine
     * @param initialState starting state
     */
    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    /**
     * adds a new state to the turin machine
     * @param name name of the state to be added
     * @param state contains the state properties/transitions
     */
    public void addState(String name, State state) {
        states.put(name, state);
    }

    /**
     * processes the input like a turing machine and makes an output
     * @param input the value that will be produced
     * @return the result of the turing machine process
     */
    public String Read(String input) {
        //creates a tape as a StringBuilder
        tape = new StringBuilder(input);

        //sets the current state as the start state
        currentState = initialState;

        //loops while the halting state is not reached
        while (!currentState.equals(haltingState)) {
            char read;
            
            //if the index has gone over the length of the value, a blank character is read
            if (tapeIndex >= tape.length()) {
                read = blank.charAt(0);
            } else {
                read = tape.charAt(tapeIndex);                //reads the current tape value
            }

            //finds the state and its transitions and stores it in the actions array
            State state = states.get(currentState);
            String[] actions = state.getTransition(read);


            char write = actions[WRITE].charAt(0);      //writes the new character to the tape
            String move = actions[MOVE];                      //moves to the next character
            currentState = actions[CURRENT_STATE];            //changes the state

            //updates the tape with the write value
            tape.setCharAt(tapeIndex, write);

            //statement to move the tape right
            if (move.equals("Right")) {
                tapeIndex++;

                //appends a blank to the value in the tape if the tape has gone over the value
                if (tapeIndex >= tape.length()) {
                    tape.append(blank);
                }
            
            //statement to move the tape left
            } else if (move.equals("Left")) {
                tapeIndex--;

                //if the index moved too far left, inserts a blank before the tape
                if (tapeIndex < 0) {
                    tape.insert(0, blank);
                    tapeIndex = 0;                             //makes the new index the start/blank value in front
                }
            }
        }

        //returns the result of the turing machine operation while removing any blank characters
        return tape.toString().replaceAll(blank, "");
    }
}
