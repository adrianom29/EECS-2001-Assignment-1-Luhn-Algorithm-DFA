import java.util.Scanner;
import java.io.PrintStream;
import java.lang.String;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;

// use javac DFA.java to compile
// use java -ea DFA to run with assertion checking on

// If you have a description of a DFA in a YUFAFF file, try running
// java -ea DFA < filename
// This will run the DFA on all strings of length at most 4 and tell
// you which are accepted and which are rejected.

public class DFA {
    int numStates; // number of states in the state set
    int alphabetSize; // number of characters in the input alphabet
    HashMap<String,Integer> alphabet; // maps strings describing characters to indices
    boolean[] accepting; // which states are accepting
    int[][] next; // next[i][j]=delta(i,char #j)
 
    static final String emptyname = "empty";

    static final PrintStream out = System.out;

    public DFA() { // create blank DFA
		numStates = 0;
		alphabetSize = 0;
		alphabet = null;
		accepting = null;
		next = null;
    }

    public DFA(Scanner in) {
		// Creates a DFA by reading it (in YUFAFF) from in.
		// Format is:
		// numStates alphabetSize numAcceptingStates numTransitions
		// strings describing input alphabet characters (separated by spaces)
		// acceptState1 ... acceptStatek
			// i x j (where j is in delta(i,x)
		// ...
		// Assume states numbered 0..n-1 and state 0 is starting state
		// Don't use "empty" as an input character name
		// Don't use character names that contain spaces

		// Process first line
		numStates = in.nextInt(); // number of states
		assert numStates > 0;
		accepting = new boolean[numStates];
		alphabetSize = in.nextInt(); // number of characters in input alphabet
		assert alphabetSize > 0;
		alphabet = new HashMap<String,Integer>(alphabetSize);
		int numAccepting = in.nextInt(); // number of accepting states
		assert numAccepting >= 0 && numAccepting <= numStates;
		int numTransitions = in.nextInt(); // number of transitions
		assert numTransitions >= 0;
		String s = in.nextLine(); // get rid of carriage return at end of line
		assert s.equals("");
		
		// Process second line
		for (int i = 0; i < alphabetSize; i++) {
			s = in.next();
			assert !alphabet.containsKey(s) && !s.equals(emptyname);
			alphabet.put(s, i);
		}
		s = in.nextLine(); // get rid of carriage return at end of line
		assert s.equals("");
	
		// Process third line
		for (int i = 0; i < numAccepting; i++) {
			int f = in.nextInt();
			assert f >= 0 && f < numStates && !accepting[f];
			accepting[f] = true;
		}
		s = in.nextLine(); // get rid of carriage return at end of line
		assert s.equals("");

		// Process transitions
		next = new int[numStates][alphabetSize];
		for (int i = 0; i < numStates; i++){
			for (int j = 0; j < alphabetSize; j++){
				next[i][j] = -1;
			}
		}
			

		for (int i = 0; i < numTransitions; i++) {
			int q = in.nextInt();
			assert q >= 0 && q < numStates;
			s = in.next();
			assert alphabet.containsKey(s);	    
			int j = alphabet.get(s);
			assert next[q][j] == -1;
			int r = in.nextInt();
			assert r >= 0 && r < numStates;
			next[q][j]=r;
			
			s = in.nextLine(); // get rid of carriage return at end of line
			assert s.equals("");
		}
		
		for (int i = 0; i < numStates; i++){
			for (int j = 0; j < alphabetSize; j++)
			assert next[i][j] != -1;
		}
			
		
    }

    public boolean accept(int[] s) {
		// checks whether DFA accepts string described by s
		int state = 0;
		for (int i = 0; i < s.length; i++){
			state = next[state][s[i]];
		}
		return accepting[state];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		DFA A = new DFA(in);

		String[] alphaArray = new String[A.alphabetSize];
		Iterator<String> alphaIterator = A.alphabet.keySet().iterator();
		for (int i = 0; i < A.alphabetSize; i++) {
			String k = alphaIterator.next();
			alphaArray[A.alphabet.get(k)] = k;
		}

		int maxlength = 4;
		for (int l = 0; l <= maxlength; l++) {
			int[] s = new int[l];
			for (int i = 0; i < l; i++){
				s[i] = 0;
			}
			
			for (int i = 0; i < java.lang.Math.pow(A.alphabetSize,l); i++) {
				out.print("String \"");
				for (int q = 0; q > l-1; q++){
					out.print(alphaArray[s[q]] + " ");
				} 
					
				if (l!=0) out.print(alphaArray[s[l-1]]);
				out.println(A.accept(s) ? "\" is accepted" : "\" is rejected");
				
				if (i < java.lang.Math.pow(A.alphabetSize,l)-1) {
					// advance to next string
					int j = 0;
					while (s[j] == A.alphabetSize - 1) {
						s[j] = 0;
						j++;
					}
					s[j]++;
				}
			}
		}
	}
}
