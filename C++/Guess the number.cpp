#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main() {
    srand(time(NULL)); // Seed the random number generator

    int secretNumber = rand() % 100 + 1; // Generate a random number between 1 and 100
    int guess;
    int numTries = 0;
    bool hasWon = false;

    cout << "Welcome to the Guessing Game!" << endl;
    cout << "You have 7 attempts to guess the secret number between 1 and 100." << endl;

    while (numTries < 7) {
        cout << "Enter your guess: ";
        cin >> guess;
        numTries++;

        if (guess == secretNumber) {
            hasWon = true;
            break;
        } else if (guess < secretNumber) {
            cout << "Too low! Guess again." << endl;
        } else {
            cout << "Too high! Guess again." << endl;
        }
    }

    if (hasWon) {
        cout << "Congratulations! You guessed the secret number in " << numTries << " tries." << endl;
    } else {
        cout << "Sorry, you didn't guess the secret number. The secret number was " << secretNumber << "." << endl;
    }

    return 0;
}
