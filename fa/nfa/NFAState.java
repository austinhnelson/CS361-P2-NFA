package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import fa.State;
//import fa.dfa.DFAState;

/**
 * Represents a NFA State and
 * its transitions to other states
 * 
 * @authors patricksantana austinnelson
 *
 */
public class NFAState extends State {

	private HashMap<Character, LinkedHashSet<NFAState>> delta;// delta - NOTE: each DFA state corresponds to a set of
	// NFA states
	private boolean isFinal;// remembers its type

	/**
	 * Default constructor
	 * 
	 * @param name the state name
	 */
	public NFAState(String name) {
		initDefault(name);
		isFinal = false;
	}

	/**
	 * Overloaded constructor that sets the state type
	 * 
	 * @param name    the state name
	 * @param isFinal the type of state: true - final, false - nonfinal.
	 */
	public NFAState(String name, boolean isFinal) {
		initDefault(name);
		this.isFinal = isFinal;
	}

	/**
	 * 
	 * @param name the state name
	 */
	private void initDefault(String name) {
		this.name = name;
		delta = new HashMap<Character, LinkedHashSet<NFAState>>();
	}

	/**
	 * Accessor for the state type
	 * 
	 * @return true if final and false otherwise
	 */
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * Add the transition from <code> this </code> object
	 * 
	 * @param onSymb  the alphabet symbol
	 * @param toState to NFA state
	 */
	public void addTransition(char onSymb, NFAState toState) {
		LinkedHashSet<NFAState> val = delta.get(onSymb);
		if (val == null) {
			val = new LinkedHashSet<>();
		}
		val.add(toState);
		delta.put(onSymb, val);
	}

	/**
	 * Retrieves the state that <code>this</code> transitions to
	 * on the given symbol
	 * 
	 * @param symb - the alphabet symbol
	 * @return the new state
	 */
	public LinkedHashSet<NFAState> getTo(char symb) {
		// LinkedHashSet<NFAState> ret = delta.get(symb);
		// if (ret == null) {
		// System.err.println("ERROR: DFAState.getTo(char symb) returns null on " + symb
		// + " from " + name);
		// System.exit(2);
		// }
		return delta.get(symb);
	}

	/**
	 * Acquires transitions (delta) from hashmap
	 * 
	 * @return transitions
	 */
	public HashMap<Character, LinkedHashSet<NFAState>> getTransitions() {
		return delta;
	}

}
