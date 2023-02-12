#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void playGame(int lowerLimit, int upperLimit) {
    int secretNumber, playerGuess;
    int guessCount = 0;
    srand(time(0));
    secretNumber = rand() % (upperLimit - lowerLimit + 1) + lowerLimit;

    cout << "I'm thinking of a number between " << lowerLimit << " and " << upperLimit << "." << endl;
    cout << "Can you guess what it is?" << endl;

    do {
        cout << "Enter your guess: ";
        cin >> playerGuess;
        guessCount++;

        if (playerGuess < secretNumber) {
            cout << "Too low. Try again." << endl;
        } else if (playerGuess > secretNumber) {
            cout << "Too high. Try again." << endl;
        }
    } while (playerGuess != secretNumber);

    cout << "Congratulations! You guessed the number in " << guessCount << " tries." << endl;
}

int main() {
    int lowerLimit = 1, upperLimit = 100;
    char playAgain = 'y';

    cout << "Welcome to the guessing game!" << endl;
    cout << "Do you want to play with the default range of 1 to 100?" << endl;
    cout << "Enter 'y' for yes or 'n' for no: ";
    cin >> playAgain;

    if (playAgain == 'n') {
        cout << "Enter the lower limit: ";
        cin >> lowerLimit;
        cout << "Enter the upper limit: ";
        cin >> upperLimit;
    }

    do {
        playGame(lowerLimit, upperLimit);
        cout << "Do you want to play again? Enter 'y' for yes or 'n' for no: ";
        cin >> playAgain;
    } while (playAgain == 'y');

    cout << "Thanks for playing! Goodbye." << endl;
    return 0;
}
