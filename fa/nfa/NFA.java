package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;


    mport fa.dfa.DFA;
        

    *

    * 
         @author patricksantana austinnels

    u

    /* Defining machine variables */
        rivate NFAState startState;

    p

    /**
        * Creates a new NFA, default const

    p

    	alphabet = new Linke
        
        
    @Override
	public void addState(String nextToken) {
		NFAState stateToAdd = new NFAState(nextToken);
		states.add(stateToAdd);
		
	}

	@Override
	public void addStartState(String startStateName) {
		NFAState stateToAdd = new NFAState(startStateName);
		states.add(stateToAdd);
		startState = stateToAdd;
		
	}

	@Override
	public void addFinalState(String nextToken) {
		NFAState stateToAdd = new NFAState(nextToken, true);
		states.add(stateToAdd);
		
	}

	@Override
	public void addTransition(String valueOf, char c, String valueOf2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		Set<NFAState> finalStates = new LinkedHashSet<NFAState>();
		for(NFAState itr : states){
			if(itr.isFinal()){
				finalStates.add(itr);
			}
		}
		return finalStates;
	}

	@Override
	public State getStartState() {
		return startState;
	}

	@Override
	public Set<Character> getABC() {
		return alphabet;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DFA getDFA() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns output for NFA
	 */
	public String toString(){
		return "";
	}

}
