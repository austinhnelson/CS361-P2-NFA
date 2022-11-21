package fa.nfa;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

/**
 * Contains NFA data and converts NFA to DFA
 * 
 * @authors patricksantana austinnelson
 *
 */
public class NFA implements NFAInterface {

    /* Declaring machine variables */
    private NFAState start = null;
    private Set<NFAState> states;
    private Set<Character> ordAbc;
    DFA dfa = null;

    /**
     * Creates new NFA, default constructor
     */
    public NFA() {
        states = new LinkedHashSet<NFAState>();
        ordAbc = new LinkedHashSet<Character>();
    }

    @Override
    public void addFinalState(String nextToken) {
        NFAState stateToAdd = new NFAState(nextToken, true);
        states.add(stateToAdd);

    }

    @Override
    public void addStartState(String name) {
        NFAState s = checkIfExists(name);
        if (s == null) {
            s = new NFAState(name);
            addState(s);
        } else {
            System.out.println("WARNING: A state with name " + name + " already exists in the NFA");
        }
        start = s;
    }

    private void addState(NFAState s) {
        states.add(s);

    }

    @Override
    public void addState(String name) {
        NFAState s = checkIfExists(name);
        if (s == null) {
            s = new NFAState(name);
            addState(s);
        } else {
            System.out.println("WARNING: A state with name " + name + " already exists in the NFA");
        }
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        NFAState from = checkIfExists(fromState);
        NFAState to = checkIfExists(toState);
        if (from == null) {
            System.err.println("ERROR: No NFA state exists with name " + fromState);
            System.exit(2);
        } else if (to == null) {
            System.err.println("ERROR: No NFA state exists with name " + toState);
            System.exit(2);
        }
        from.addTransition(onSymb, to);

        if (!ordAbc.contains(onSymb)) {
            ordAbc.add(onSymb);
        }

    }

    @Override
    public DFA getDFA() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // Get all e transitions out of desired state
        Set<NFAState> returnVal = s.getTo('e');
        // Check to make sure there are actually states
        if (returnVal == null) {
            return null;
        }
        // Loop through all the states to check for more e transitions
        for (NFAState itr : returnVal) {
            returnVal.addAll(eClosure(itr, returnVal));
        }
        return returnVal;
    }

    /**
     * Private helper method to implement DFS
     * with epsilon transitions
     * 
     * @param s      state concerned with
     * @param result result set of states
     * @return result set of states (appended with any more found)
     */
    private Set<NFAState> eClosure(NFAState s, Set<NFAState> result) {
        // Make sure there is a valid epsilon transition
        if (s.getTo('e') != null) {
            // Get all epsilon transitions
            Set<NFAState> r = s.getTo('e');
            // Iterate through all occurences
            for (NFAState itr : r) {
                // Depth-first search the set
                eClosure(itr, result);
                // Add state to result
                result.add(itr);
            }
        } else {
            return result;
        }
        return result;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTo(onSymb);
    }

    @Override
    public Set<? extends State> getStates() {
        return states;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        Set<NFAState> finalStates = new LinkedHashSet<NFAState>();
        for (NFAState itr : states) {
            if (itr.isFinal()) {
                finalStates.add(itr);
            }
        }
        return finalStates;
    }

    @Override
    public State getStartState() {
        return start;
    }

    @Override
    public Set<Character> getABC() {
        return ordAbc;
    }

    /**
     * Check if a state already exists
     * 
     * @param name name of state to find
     * @return return null if no state exists, or NFAState object otherwise
     */
    private NFAState checkIfExists(String name) {
        NFAState ret = null;
        for (NFAState s : states) {
            if (s.getName().equals(name)) {
                ret = s;
                break;
            }
        }
        return ret;
    }

    /**
     * For debugging purposes
     * 
     * @return NFA output
     */
    public String toString() {
        String output = "Initial State: " + start.toString();
        output += "\nFinal States: ";
        String allStates = "\nStates: ";
        String ab = "\nAlphabet: ";
        for (NFAState itr : states) {
            if (itr.isFinal()) {
                output += itr.toString() + " ";
            }
            allStates += itr.toString() + " ";
        }
        for (Character itr : ordAbc) {
            ab += itr.toString() + " ";
        }

        output += allStates;
        output += ab;

        // create transition table
        output += "\ndelta =\n" + String.format("%10s", "");
        for (char c : ordAbc) {
            output += String.format("%10s", c);
        }
        output += "\n";
        for (NFAState state : states) {
            output += String.format("%10s", state.toString());
            for (char c : ordAbc) {
                if (state.getTo(c) != null) {
                    output += String.format("%10s", state.getTo(c).toString());
                } else {
                    output += "\t    ";
                }
            }
            output += "\n";
        }

        // testing eClosure
        Set<NFAState> mystates = null;
        for (NFAState itr : states) {
            if (itr.getName().equals("4")) {
                mystates = eClosure(itr);
            }
        }

        output += mystates.toString();
        return output;
    }

}
