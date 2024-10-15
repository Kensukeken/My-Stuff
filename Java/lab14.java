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
