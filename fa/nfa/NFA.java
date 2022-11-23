package fa.nfa;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
        addState(stateToAdd);
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
        // Declare a queue structure for traversal
        // need the ability to add first and remove last
        LinkedList<HashSet<NFAState>> queue = new LinkedList<HashSet<NFAState>>();
        // keep track of visited sets
        HashSet<Set<NFAState>> visitedStatesSet = new HashSet<Set<NFAState>>();
        // begin by collecting start set of states
        HashSet<NFAState> q0Set = new HashSet<NFAState>();

        DFA dfa = new DFA();
        // final state flag
        boolean finalState = false;

        // Add q0 of NFA to Q' of DFA. Then find the transitions from this start state.
        q0Set.add(start);
        // add epsilon transitions from q0 to q0Set
        q0Set.addAll(eClosure(start, new HashSet<NFAState>()));

        // for states in q0Set, if state is final, set final state flag
        for (NFAState nfa : q0Set) {
            if (nfa.isFinal()) {
                finalState = true;
                // if final state flag set, add final states to dfa from q0Set
                dfa.addFinalState(q0Set.toString());
            }
        }

        // add states to dfa from q0Set
        dfa.addStartState(q0Set.toString());

        // System.out.println(dfa.toString());

        // add q0Set to beginning of queue
        queue.addFirst(q0Set);

        while (!queue.isEmpty()) {

            // remove last element from queue and store
            HashSet<NFAState> pos = queue.removeLast();

            // add removed state to HashSet visited states - check if already visited
            visitedStatesSet.add(pos);

            // for each char in input alphabet - all transitions
            for (Character c : ordAbc) {
                // System.out.println(c);

                // store state and associations
                HashSet<NFAState> stateSet = new HashSet<NFAState>();
                finalState = false;

                // for each nfa state at pos in hashset
                for (NFAState nfa : pos) {

                    // retrieve states transitioned to on the given symbol
                    HashSet<NFAState> transitionTo = nfa.getTo(c);
                    // System.out.println(transitions);

                    // if transitionTo state exists given NFA state
                    if (transitionTo != null) {

                        // for each state in transitionTo
                        for (NFAState t : transitionTo) {
                            // account for 'e' transitions given position in the NFA, recursively
                            eClosure(t, stateSet);

                            // add transitionTo state to stateSet
                            stateSet.add(t);

                            // check if to-state is a final state and set flag
                            if (t.isFinal()) {
                                finalState = true;
                            }
                        }
                    }
                }

                // if the state is the final state
                if (finalState == true) {
                    if (!visitedStatesSet.contains(stateSet) && !queue.contains(stateSet)) {
                        // add final state
                        dfa.addFinalState(stateSet.toString());
                    }

                    // add transitions to dfa
                    dfa.addTransition(pos.toString(), c, stateSet.toString());
                }

                // if the state is not the final state
                else {
                    if (!visitedStatesSet.contains(stateSet) && !queue.contains(stateSet)) {
                        // add states to dfa
                        dfa.addState(stateSet.toString());
                    }
                    // add transitions to dfa
                    dfa.addTransition(pos.toString(), c, stateSet.toString());
                }

                // set as first state set until a new one is added
                if (!visitedStatesSet.contains(stateSet) && !queue.contains(stateSet)) {
                    queue.addFirst(stateSet);

                }
            }
        }
        return dfa;
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
        if (s.getTo('e') != null && !result.contains(s)) {
            // Get all epsilon transitions
            Set<NFAState> r = s.getTo('e');
            // Iterate through all occurences
            for (NFAState itr : r) {
                // Add state to result
                result.add(itr);
                // Depth-first search the set
                eClosure(itr, result);
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
}
