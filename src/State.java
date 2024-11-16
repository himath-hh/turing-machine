import java.util.HashMap;

/**
 * @author Himath Helessage
 * the State class represents a state in the turing machine with transitions
 */
public class State {
    //hashmap of transitions for one state
    HashMap<Character, String[]> table;

    //constructor for a state that contains a hashmap of transitions
    public State() {
        table = new HashMap<>();
    }

    /**
     * adds a transition to a state
     * @param read read character in a transition
     * @param write write character in a transition
     * @param move next direction of movement
     * @param state the next state to transition in to
     */
    public void addTransition(char read, String write, String move, String state) {
        table.put(read, new String[]{write, move, state});
    }

    /**
     * fetches the transition associated with the read character from the state
     * @param read read character in a transition
     * @return string array with write, move direction and next state
     */
    public String[] getTransition(char read) {
        return table.get(read);
    }
}
