import java.util.Random;

public class lab14 {

    public static void main(String[] args) {
        playCraps();
    }

    /** Function to simulate the game */
    public static void playCraps() {
        Random rand = new Random();

        int roll1 = rollDice(rand); // Roll the first die
        int roll2 = rollDice(rand); // Roll the second die
        int sum = roll1 + roll2;    // Calculate the sum of both dice

        System.out.println("You rolled " + roll1 + " + " + roll2 + " = " + sum);

        // Check the initial result using if-else
        if (sum == 2) {
            System.out.println("You lose"); // Craps (you lose)
        } else if (sum == 3) {
            System.out.println("You lose"); // Craps (you lose)
        } else if (sum == 12) {
            System.out.println("You lose"); // Craps (you lose)
        } else if (sum == 7) {
            System.out.println("You win"); // Natural (you win)
        } else if (sum == 11) {
            System.out.println("You win"); // Natural (you win)
        } else {
            // If the sum is 4, 5, 6, 8, 9, or 10, establish the point
            int point = sum;
            System.out.println("Point is " + point);

            // Keep rolling until you roll the point or 7
            while (true) {
                roll1 = rollDice(rand);
                roll2 = rollDice(rand);
                sum = roll1 + roll2;

                System.out.println("You rolled " + roll1 + " + " + roll2 + " = " + sum);

                if (sum == point) {
                    System.out.println("You win");
                    break;
                } else if (sum == 7) {
                    System.out.println("You lose");
                    break;
                }
            }
        }
    }

    /** Function to roll a die (random number between 1 and 6) */
    public static int rollDice(Random rand) {
        return rand.nextInt(6) + 1;
    }
}

`````````````````````````````````````````````````````````````````````````````````````````````````````

public class Main {
    public static void main(String[] args) {
        int rollDice = Roll();
        int result = checkResult(rollDice);

        if (isWin(result)) {
            gameResult(1);
        } else if (isLose(result)) {
            gameResult(0);
        } else {
            keepRolling(result);
        }
    }

    public static int rollDie() {
        return (int) (1 + Math.random() * 6);
    }

    public static int Roll() {
        int die1 = rollDie();
        int die2 = rollDie();
        int rollRice = die1 + die2;
        System.out.println("You rolled " + die1 + " + " + die2 + " = " + rollRice);
        return rollRice;
    }

    public static int checkResult(int rollRice) {
        if (rollRice == 2) {
            return 0;
        } else if (rollRice == 3) {
            return 0;
        } else if (rollRice == 12) {
            return 0;
        } else if (rollRice == 7) {
            return 1;
        } else if (rollRice == 11) {
            return 1;
        } else {
            return rollRice;
        }
    }

    public static void gameResult(int result) {
        if (result == 0) {
            System.out.println("You lose");
        } else if (result == 1) {
            System.out.println("You win");
        }
    }

    public static boolean isWin(int result) {
        return result == 1;
    }

    public static boolean isLose(int result) {
        return result == 0;
    }

    public static void keepRolling(int point) {
        int result;
        boolean continueRolling = true;

        do {
            result = Roll(); 
            if (result == point) {
                continueRolling = false; 
            }

            if (result == 7) {
                continueRolling = false;
            }

        } while (continueRolling);
        if (result == 7) {
            gameResult(0);
        } else {
            gameResult(1);
        }
    }
}
`````````````````````````````````````````````````````````````````````````````````````````
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random(); 
        int rollDice = roll(rand);
        int result = checkResult(rollDice);

        if (isWin(result)) {
            gameResult(1);
        } else if (isLose(result)) {
            gameResult(0);
        } else {
            keepRolling(result, rand); 
        }
    }

    /** Method to roll one die using Random */
    public static int rollDie(Random rand) {
        return rand.nextInt(6) + 1; // Generates a number between 1 and 6
    }

    /** Method to roll two dice and return the sum */
    public static int roll(Random rand) {
        int die1 = rollDie(rand); 
        int die2 = rollDie(rand); 
        int rollSum = die1 + die2; 
        System.out.println("You rolled " + die1 + " + " + die2 + " = " + rollSum);
        return rollSum;
    }

    /** Method to check if you win or lose */
    public static int checkResult(int rollSum) {
        switch (rollSum) {
            case 2:
            case 3:
            case 12:
                return 0;
            case 7:
            case 11:
                return 1; 
            default:
                return rollSum; 
        }
    }

    /** Method to print if you win or lose */
    public static void gameResult(int result) {
        if (result == 0) {
            System.out.println("You lose");
        } else if (result == 1) {
            System.out.println("You win");
        }
    }

    /** Method to check if it's a win */
    public static boolean isWin(int result) {
        return result == 1;
    }

    /** Method to check if it's a loss */
    public static boolean isLose(int result) {
        return result == 0;
    }

    /** Method to keep rolling until you win or lose */
    public static void keepRolling(int point, Random rand) { // Pass the Random object to keepRolling
        int result;
        boolean continueRolling = true;

        do {
            result = roll(rand); 
            if (result == point) {
                continueRolling = false; 
            }

            if (result == 7) {
                continueRolling = false;
            }

        } while (continueRolling);
        if (result == 7) {
            gameResult(0);
        } else {
            gameResult(1);
        }
    }
}
