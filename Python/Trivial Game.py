name = input("What is your name? ")
age = int(input("What is your age? "))

print("Hello, " + name + "!")
print("In 10 years, you will be " + str(age + 10) + " years old.")

import sys

# Hello Welcome to my Project for a trivia game/quiz. =)
print("\033[0;35mWelcome to Trivia Quiz! In this quiz, you'll be able to answer a few maths questions.\n") # This line represents the welcome =)

userinput = "Yes"
while True:
    userAnswer = input("Are you ready?\n(Yes/No)\n")
    if userAnswer == userinput or userAnswer.lower() == "yes":
        print("\033[0;32mLet's start the quiz!\n")
        break
    elif userAnswer.lower() == "no":
        print("\033[0;31mHow would you see the questions if you answer with 'No'?.\nIf you wish to view the questions, please enter 'Yes'. =)\n")
    else:
        print("\033[0;31mHow would you see the questions if you answer with 'No'?.\nif you wish to view the questions, please enter 'Yes'. =)\n") # This only works if the person doesn't write "Yes" or "yes".

print("\n\n")

score = 0 # Added this integer to score the person score based on the question.

# Question Number 1
print("\033[0;33mQuestion 1")
print("\033[0;37mWhat is 60+9?\n")
correctAnswer1 = "69" # That's only the correct answer.
userAnswer1 = input()
if userAnswer1 == correctAnswer1:
    print("\033[0;32mCorrect!")
    score += 1
else:
    print("\033[0;31mWrong the correct answer is 69.\n")
    print("\033[0;32mExplanation: 60+9 is a simple mathematical operation known as addition. When you add 60 and 9 together, the result is 69. Therefore, 60+9=69.")
print(f"\033[0;37m\nYour score {score} out 6\n")

print("\n\n")

# Question Number 2

print("\033[0;33mQuestion 2")
print("\033[0;37mSolve - 15 + (-5x) = 0\n")
correctAnswer2 = "-3"
userAnswer2 = input()
if userAnswer2 == correctAnswer2:
    print("\033[0;32mCorrect!")
    score += 1
else:
    print("\033[0;31mWrong the correct answer is -3.\n")
    print("\033[0;32mExplanation: -15 + (-5x) = 0 can be simplified to -5x = 15 by combining like terms and adding 15 to both sides. Dividing both sides by -5 gives the solution x = -3.")
print(f"\033[0;37m\nYour score {score} out 6\n")
print("\n\n")

# Question Number 3

print("\033[0;33mQuestion 3")
print("\033[0;37mLook at this series: 22, 21, 23, 22, 24, 23, … What number should come next?\n")
print("\nA.21\nB.22\nC.23\nD.24\nE.25\n")
correctAnswer3 = "E"
userAnswer3 = input().upper()
if userAnswer3 == correctAnswer3:
    print("\nCorrect!")
    score += 1
else:
    print("\033[0;31mWrong the correct answer is E")
    print("\033[0;32mExplanation: The next number in the series is 25, obtained by adding 2 to the previous number (23).")
print(f"\033[0;37m\nYour score {score} out of 6\n")

# Question Number 4

print("\033[0;33mQuestion 4")
print("\033[0;37mHow many feet are in a mile?\n")
correctAnswer4 = "5280"
userAnswer4 = input()
if userAnswer4 == correctAnswer4:
    print("\033[0;32mCorrect!")
    score += 1
else:
    print("\033[0;31mWrong the correct answer is 5280")
    print("\033[0;32mExplanation: There are 5280 feet in a mile.")
print(f"\033[0;37m\nYour score {score} out of 6\n")

# Question Number 5

print("\033[0;33mQuestion 5")
print("\033[0;37mA ship anchored in a port has a ladder which hangs over the side. The length of the ladder is 200cm, the distance between each rung is 20cm and the bottom rung touches the water.\nThe tide rises at a rate of 10cm an hour. When will the water reach the fifth rung?\n");
print("A.20cm\nB.200cm\nC.Never\nD.I don’t know\n");
correctAnswer5 = "C"
userAnswer5 = input()
if userAnswer5 == correctAnswer5:
    print("\033[0;32mCorrect!")
    score += 1
else:
    print("\033[0;31mWrong the correct answer is 3.14159")
    print("\033[0;32mExplanation: The height of the fifth rung above the water is 80cm. As the tide rises at 10 cm per hour, it will take 12 hours (120cm/10cm per hour) for the water to reach the fifth rung.")
print("\033[0;37m\nYour score {score} out of 6\n")


# Question Number 6

print("\033[0;33mQuestion 6")
print("\033[0;37mWhat is the Integral of cos(x)/(x^2+1)?")
print("\nA.pi over e\nB.ln(pi over 2)\nC.ln\nD.I don't know")

correct_answer = "A"
user_answer = input().upper()

if user_answer == correct_answer:
    print("\033[0;32mCorrect!")
    score += 1
else:
    print("\033[0;31mThat was close! The correct answer is A")
    print("Explanation: Using complex analysis and the residue theorem, we can evaluate the integral of cos(x)/(x^2 + 1) from negative infinity to infinity. We first consider a function f(z) with simple poles at i and -i in the upper half-plane. By evaluating the integral along a semi-circular contour, we can take the residue of f(z) at i and obtain the value of the integral. The final result is π/e.")

print("\033[0;37m\nYour score {} out of 6.\n".format(score))

# Here's the final gives the score on how they did.

final_score = int(score / 6.0 * 100)
correct = score
wrong = 6 - score

# The score is displayed as a percentage and the number of correct and wrong answers are also shown.

if final_score >= 80:
    print("Congratulations!🥳 You passed! Great job!")
elif final_score >= 50:
    print("You were close. Keep practicing!🔥")
else:
    print("Sorry, you failed. Keep studying and practicing.")

print("You got {} right and {} wrong. Your score is {}.".format(correct, wrong, final_score))
