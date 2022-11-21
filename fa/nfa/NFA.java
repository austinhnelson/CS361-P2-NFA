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

//Austin:
//      NFAState stateToAdd = new NFAState(name);
//      states.add(stateToAdd);
//      start = stateToAdd;
    	
    	NFAState s = checkIfExists(name);
		if(s == null){
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
//Austin:        
//		NFAState stateToAdd = new NFAState(name);
//      states.add(stateToAdd);
		NFAState s = checkIfExists(name);
		if( s == null){
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
    
    /**
	 * Traverses all epsilon transitions and determine
	 * what states can be reached from s through e
	 * @param s
	 * @return set of states that can be reached from s on epsilon trans.
	 */
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
