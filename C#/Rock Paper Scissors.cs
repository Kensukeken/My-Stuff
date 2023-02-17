using System;

class Program {
    static void Main(string[] args) {
        Random random = new Random();
        int wins = 0;
        int losses = 0;
        int ties = 0;

        Console.WriteLine("Welcome to Rock Paper Scissors!");

        while (true) {
            Console.WriteLine();
            Console.WriteLine("Wins: " + wins + ", Losses: " + losses + ", Ties: " + ties);
            Console.Write("Enter your move (rock, paper, or scissors): ");
            string playerMove = Console.ReadLine().ToLower();

            if (playerMove != "rock" && playerMove != "paper" && playerMove != "scissors") {
                Console.WriteLine("Invalid move. Please try again.");
                continue;
            }

            int computerMove = random.Next(3); // 0 = rock, 1 = paper, 2 = scissors
            string computerMoveStr = "";

            switch (computerMove) {
                case 0:
                    computerMoveStr = "rock";
                    break;
                case 1:
                    computerMoveStr = "paper";
                    break;
                case 2:
                    computerMoveStr = "scissors";
                    break;
            }

            Console.WriteLine("Computer move: " + computerMoveStr);

            if (playerMove == computerMoveStr) {
                Console.WriteLine("It's a tie!");
                ties++;
            } else if ((playerMove == "rock" && computerMoveStr == "scissors") || (playerMove == "paper" && computerMoveStr == "rock") || (playerMove == "scissors" && computerMoveStr == "paper")) {
                Console.WriteLine("You win!");
                wins++;
            } else {
                Console.WriteLine("You lose!");
                losses++;
            }
        }
    }
}
