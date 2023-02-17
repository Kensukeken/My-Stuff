#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <algorithm>
using namespace std;

const int MAX_WRONG = 8;

vector<string> words = {"BINARY", "COMPUTER", "PROGRAM", "ALGORITHM", "VARIABLE", "FUNCTION", "OBJECT", "MEMORY", "DATABASE", "OPERATOR"};

string chooseWord() {
    srand(time(NULL));
    int index = rand() % words.size();
    return words[index];
}

string generateHint(string word, const vector<bool>& guessed) {
    string hint = word;

    for (int i = 0; i < word.size(); i++) {
        if (!guessed[i]) {
            hint[i] = '-';
        }
    }

    return hint;
}

char getGuess() {
    char guess;
    cout << "Enter your guess: ";
    cin >> guess;
    guess = toupper(guess); // Convert to uppercase
    return guess;
}

bool isGuessCorrect(char guess, const string& word, vector<bool>& guessed) {
    bool correct = false;

    for (int i = 0; i < word.size(); i++) {
        if (word[i] == guess) {
            guessed[i] = true;
            correct = true;
        }
    }

    return correct;
}

int main() {
    srand(time(NULL)); // Seed the random number generator

    string word = chooseWord();
    vector<bool> guessed(word.size(), false);
    int wrong = 0;

    cout << "Welcome to Hangman!" << endl;

    while (wrong < MAX_WRONG && count(guessed.begin(), guessed.end(), false) > 0) {
        cout << "You have " << MAX_WRONG - wrong << " incorrect guesses left." << endl;
        cout << "Current hint: " << generateHint(word, guessed) << endl;

        char guess = getGuess();

        if (isGuessCorrect(guess, word, guessed)) {
            cout << "Correct guess!" << endl;
        } else {
            cout << "Wrong guess!" << endl;
            wrong++;
        }
    }

    if (wrong == MAX_WRONG) {
        cout << "Sorry, you lost! The word was " << word << "." << endl;
    } else {
        cout << "Congratulations! You won the game!" << endl;
        cout << "The word was " << word << "." << endl;
    }

    return 0;
}
