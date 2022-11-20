package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.FAInterface;
import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

/**
 * Contains NFA data and converts NFA to DFA
 * @authors
 *
 */
public class NFA implements FAInterface, NFAInterface{
	
	private Set<NFAState> states = new LinkedHashSet<NFAState>();
	private NFAState start = null;
	private Set<Character> ordAbc = new LinkedHashSet<Character>();
	

	public void addFinalState(String nextToken) {
		// TODO Auto-generated method stub
		
	}

	public void addStartState(String startStateName) {
		// TODO Auto-generated method stub
		
	}

	public void addState(String nextToken) {
			
	}

	public void addTransition(String valueOf, char c, String valueOf2) {
		// TODO Auto-generated method stub
		
	}

	public DFA getDFA() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Set<NFAState> eClosure(NFAState s) {
		return null;
	}
	
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return null;
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getStartState() {
		return start;
	}

	@Override
	public Set<Character> getABC() {
		return ordAbc;
	}

}
