import java.util.*;
public class Mastermind {

	private ArrayList<Character> secret;
	/**
	 * This constructor generates a random 
	 * string to guess
	 */
	public Mastermind() {
		secret = new ArrayList<Character>();
		final String str = "RYBGP";
		Random ran = new Random();
		for(int i = 0; i < 4; i++) {
			this.secret.add(str.charAt(ran.nextInt(5)));
		}
		
		
		//System.out.println(secret);
		 
	}
	
	/**
	 * This is the game. This method check 
	 * given values then start the game!
	 * If the 4characters are right the game stop
	 * 
	 * @see {@link #isGoodGuess(ArrayList)}
	 * @see {@link #numAllRight(ArrayList)}
	 * @see {@link #numHalfRight(ArrayList)}
	 * 
	 */
	public void gameLoop() {
		ArrayList<Character> guess = new ArrayList<Character>();
		Scanner sc = new Scanner(System.in);
		int attempt = 0;
		do{
			System.out.println("enter your guess one letter on each line");
			/*
			 * Remove the old data in the array list
			 */
			for (int i = 0; i < guess.size(); i++) {
				guess.remove(guess.get(i));
				i=-1;
			}
			for (int i = 0; i < 4; i++) {
				guess.add(sc.next().toUpperCase().charAt(0));
			}
			/*
			 * the while loop avoid the user to go further
			 * if he/she doesn't provide correct values
			 */
			while(!this.isGoodGuess(guess)){
				/*
				 * We remove all the values in the array first
				 */
				for (int i = 0; i < guess.size(); i++) {
					guess.remove(guess.get(i));
					i=-1;
				}
				/*
				 * We ask the user for new values
				 * then grab these values!
				 */
				System.out.println("enter correct guess values");
				for (int i = 0; i < 4; i++) {
					guess.add(sc.next().toUpperCase().charAt(0));
				}
			}
				/*
				 * STUFF TO PRINT ON THE SCREEN
				 */
			for (int i = 0; i < guess.size(); i++) {
				System.out.print(guess.get(i));
			}
			System.out.print(" " + this.numAllRight(guess) + " right color/right position");
			System.out.println(", " + this.numHalfRight(guess) + " right color/wrong position");
			attempt++;
		}while(this.numAllRight(guess)!=4);
		System.out.print("you did " + attempt + " guesses!");
	}
	
	/**
	 * 
	 * @param guess the array to compare with the 
	 * secret code
	 * @return the number of elements in guess
	 * that has the same values than elements in secret 
	 * at the same position.
	 */
	public int numAllRight(ArrayList<Character> guess) {
		int cp = 0;
		for (int i = 0; i < guess.size(); i++) {
			if(guess.get(i)==this.secret.get(i)) {
				cp++;
			}
		}
		return cp;
	}
	/**
	 * 
	 * @param guess the array to compare with the 
	 * secret code.
	 * @return the number of elements in guess
	 * that has the same values than elements in secret 
	 * but not at the same position.
	 */
	public int numHalfRight(ArrayList<Character> guess) {
		
		ArrayList<Character> secretCopy = new ArrayList<Character>(secret);
		ArrayList<Character> guessCopy = new ArrayList<Character>(guess);
		/*
		 * Remove all the matching values of the array
		 */
		for (int i = 0; i < guessCopy.size(); i++) {	
			if(guessCopy.get(i)==secretCopy.get(i)) {
				guessCopy.remove(i);
				secretCopy.remove(i);
				i=-1;
			}
		}
		/*
		 * We check where guess and secret have same values 
		 * but different position. Since all the right values 
		 * same positions have been removed only the right values
		 * wrong positions and wrong values remain.
		 */
		int cp = 0;
		for (int i = 0; i < guessCopy.size(); i++) {
			
			boolean check = false;
			for (int j = 0; j < secretCopy.size(); j++) {
				/*
				 * Here we use a property of the operator OR
				 * to check if in all the values of the array
				 * there are matching values
				 */
				check |= guessCopy.get(i)==secretCopy.get(j);
			}
			if(check) {
				cp++;
			}
		}
		return cp;
	}
	/**
	 * 
	 * @param guess is the array to check
	 * @return true if all the elements in 
	 * the array are P,G,Y,B or R
	 */
	public boolean isGoodGuess(ArrayList<Character> guess) {
		boolean result = true;
		for (int i = 0; i < guess.size(); i++) {
			boolean check = (guess.get(i)=='R'||guess.get(i)=='Y'||guess.get(i)=='B'||guess.get(i)=='G'||guess.get(i)=='P');
			result &= check;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Mastermind game = new Mastermind();
		game.gameLoop();
	}

	
}
