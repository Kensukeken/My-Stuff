#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
using namespace std;

string chooseWord() {
    const int NUM_WORDS = 5;
    string words[NUM_WORDS] = {"jumble", "quick", "fox", "lazy", "dog"};

    srand(time(NULL));
    int index = rand() % NUM_WORDS;
    return words[index];
}

string jumbleWord(string word) {
    string jumbledWord = word;
    int length = jumbledWord.size();

    for (int i = 0; i < length; i++) {
        int index1 = rand() % length;
        int index2 = rand() % length;
        char temp = jumbledWord[index1];
        jumbledWord[index1] = jumbledWord[index2];
        jumbledWord[index2] = temp;
    }

    return jumbledWord;
}

int main() {
    srand(time(NULL)); // Seed the random number generator

    string originalWord = chooseWord();
    string jumbledWord = jumbleWord(originalWord);

    cout << "Welcome to Word Jumble!" << endl;
    cout << "Unscramble the letters to make a word." << endl;
    cout << "The jumbled word is: " << jumbledWord << endl;

    string guess;
    cout << "Enter your guess: ";
    cin >> guess;

    while (guess != originalWord) {
        cout << "Sorry, that's not it. Try again." << endl;
        cout << "The jumbled word is: " << jumbledWord << endl;
        cout << "Enter your guess: ";
        cin >> guess;
    }

    cout << "Congratulations! You unscrambled the word!" << endl;

    return 0;
}
