package fa.nfa;

import java.util.HashMap;
import fa.State;

/**
 * Represents a NFA state and
 * its transitions to other states
 * 
 * @author patricksantana austinnelson
 */
public class NFAState extends State {

    private boolean isFinal;

    HashMap<Character, NFAState> transitionMap;

    /**
     * Creates a new DFAState - default constructor
     * 
     * @param name
     */
    public NFAState(String name) {
        this.name = name;
        isFinal = false;
        transitionMap = new HashMap<Character, NFAState>();
    }

    /**
     * Overload construction to set final state
     * 
     * @param name
     * @param isFinal whether or not it is a final state
     */
    public NFAState(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    /**
     * Add a transition to the state
     * 
     * @param k       alphabet symbol
     * @param toState the state referencing
     */
    public void addTransition(char k, NFAState toState) {
        transitionMap.put(k, toState);
    }

    /**
     * Returns final state
     * 
     * @return whether or not state is final
     */
    public boolean isFinal() {
        return isFinal;
    }

}
