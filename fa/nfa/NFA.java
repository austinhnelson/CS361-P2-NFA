package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;

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
    public void addStartState(String startStateName) {
        NFAState stateToAdd = new NFAState(startStateName);
        states.add(stateToAdd);
        start = stateToAdd;
    }

    @Override
    public void addState(String nextToken) {
        NFAState stateToAdd = new NFAState(nextToken);
        states.add(stateToAdd);
    }

    @Override
    public void addTransition(String valueOf, char c, String valueOf2) {
        NFAState from = checkIfExists(valueOf);
        NFAState to = checkIfExists(valueOf2);
        if (from == null) {
            System.err.println("ERROR: No NFA state exists with name " + valueOf);
            System.exit(2);
        } else if (to == null) {
            System.err.println("ERROR: No NFA state exists with name " + valueOf2);
            System.exit(2);
        }
        from.addTransition(c, to);

        if (!ordAbc.contains(c)) {
            ordAbc.add(c);
        }

    }

    @Override
    public DFA getDFA() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        return null;
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

}
