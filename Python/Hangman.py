import random

# List of possible words to choose from
words = ["apple", "banana", "cherry", "orange", "pineapple", "grape", "kiwi", "watermelon"]

# Function to get a random word from the list
def get_word():
    return random.choice(words)

# Function to display the hangman
def display_hangman(guesses):
    stages = [  # the different stages of hangman
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |     / \\
                   -
                """,
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |     / 
                   -
                """,
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |      
                   -
                """,
                """
                   --------
                   |      |
                   |      O
                   |     \\|
                   |      |
                   |     
                   -
                """,
                """
                   --------
                   |      |
                   |      O
                   |      |
                   |      |
                   |     
                   -
                """,
                """
                   --------
                   |      |
                   |      O
                   |    
                   |      
                   |     
                   -
                """,
                """
                   --------
                   |      |
                   |      
                   |    
                   |      
                   |     
                   -
                """
    ]
    return stages[guesses]

# Function to play the game
def play_hangman():
    word = get_word()  # get a random word
    word_letters = set(word)  # get the set of unique letters in the word
    guessed_letters = set()  # set to store the letters guessed by the player
    guesses = 6  # number of incorrect guesses allowed

    while len(word_letters) > 0 and guesses > 0:
        # display the hangman and the letters guessed so far
        print(display_hangman(guesses))
        print("Guessed letters: ", " ".join(guessed_letters))

        # get the current word with letters replaced by underscores for the letters not yet guessed
        word_list = [letter if letter in guessed_letters else "_" for letter in word]
        print(" ".join(word_list))

        # ask the player to guess a letter
        guess = input("Guess a letter: ").lower()

        if guess in guessed_letters:
            print("You already guessed that letter. Try again.")
        elif guess in word_letters:
            print("Good job! The letter '", guess, "' is in the word.")
            guessed_letters.add(guess)
            word_letters.remove(guess)
        else:
            print("Sorry, the letter '", guess, "' is not in the word.")
            guessed_letters.add(guess)
            guesses -= 1

    # game over
    if guesses == 0:
        print(display_hangman(guesses))
        print("You ran out of guesses. The word was '", word, "'. Better luck next time!")
    else:
        print("Congratulations! You guessed the word '", word, "'!")

# start the game
play_hangman()
