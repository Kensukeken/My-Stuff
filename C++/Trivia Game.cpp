#include <iostream>
#include <stdio.h>
#include <string.h>

// Hello Welcome to my Project for a trivia game/quiz. =)
int main() {
  // I made my quiz about the math questions.
  printf("\033[0;35mWelcome to Trivia Quiz! In this quiz, you'll be able to answer a few maths questions.\n\n"); // This line represents the welcome =)

  char userinput[] = "Yes";
  char userAnswer[4];
  while (1) {
    printf("Are you ready?\n(Yes/No)\n");
    scanf("%s", userAnswer);
    if (strcmp(userAnswer, userinput) == 0 || strcmp(userAnswer, "yes") == 0) {
      printf("\033[0;32mLet's start the quiz!\n");
      break;

    } else if (strcmp(userAnswer, "No") == 0 || strcmp(userAnswer, "no") == 0) {
      printf("\033[0;31mHow would you see the questions if you answer with 'No'?.\nIf you wish to view the questions, please enter 'Yes'. =)\n");
    } else {
      printf("\033[0;31mHow would you see the questions if you answer with 'No'?.\nif you wish to view the questions, please enter 'Yes'. =)\n"); // This only works if the person doesn't write "Yes" or "yes".
    }
  }

  printf("\n\n");

  int score = 0; // Added this integer to score the person score based on the question.

  // Question Number 1
  printf("\033[0;33mQuestion 1\n");
  printf("\033[0;37mWhat is 60+9?\n");

  char correctAnswer1[] = "69"; // That's only the correct answer.
  char userAnswer1[3];
  scanf("%s", userAnswer1);
  if (strcmp(userAnswer1, correctAnswer1) == 0) {
    printf("\033[0;32mCorrect!\n");
    score++;
  } else {
    printf("\033[0;31mWrong the correct answer is 6.\n");
    printf("\033[0;32mExplanation: 60+9 is a simple mathematical operation known as addition. When you add 60 and 9 together, the result is 69. Therefore, 60+9=69.");
  }
  printf("\033[0;37m\nYour score %d out 6\n", score);

  printf("\n\n");

  // Question Number 2

  printf("\033[0;33mQuestion 2\n");
  printf("\033[0;37mSolve - 15 + (-5x) = 0\n");

  char correctAnswer2[] = "-3";
  char userAnswer2[3];
  scanf("%s", userAnswer2);
  if (strcmp(userAnswer2, correctAnswer2) == 0) {
    printf("\033[0;32mCorrect!\n");
    score++;
  } else {
    printf("\033[0;31mWrong the correct answer is -3\n");
    printf("\033[0;32mExplanation: -15 + (-5x) = 0 can be simplified to -5x = 15 by combining like terms and adding 15 to both sides. Dividing both sides by -5 gives the solution x = -3.");
  }
  printf("\033[0;37m\nYour score %d out 6\n", score);
  printf("\n\n");

  // Question Number 3

  printf("\033[0;33mQuestion 3\n");
  printf("\033[0;37mLook at this series: 22, 21, 23, 22, 24, 23, … What number should "
         "come next?\n");
  printf("\nA.21\nB.22\nC.23\nD.24\nE.25\n");
  
  char correctAnswer3[] = "E";
  char userAnswer3[3];
  scanf("%s", userAnswer3);
  if (strcmp(correctAnswer3, userAnswer3) == 0 || strcmp(userAnswer3, "e") == 0) {
  printf("\nCorrect!");
    score++;
} else{
    printf("\033[0;31mWrong the correct answer is E \n");
    printf("\033[0;32mExplanation: The next number in the series is 25, obtained by adding 2 to the previous number (23).");
  }
  printf("\033[0;37m\nYour score %d out 6\n", score);
  printf("\n\n");

  // Question Number 4
  printf("\033[0;33mQuestion 4\n");
  printf("\033[0;37mHow many feet are in a mile?\n");
  char correctAnswer4[] = "5280";
  char userAnswer4[3];
  scanf("%s", userAnswer4);
  if (strcmp(userAnswer4, correctAnswer4) == 0) {
    printf("\033[0;32mCorrect!\n");
    score++;
  } else {
    printf("\033[0;31mWrong the correct answer is 5280 \n");
    printf("\033[0;32mExplanation: There are 5280 feet in a mile.");
  }
  printf("\033[0;37m\nYour score %d out 6\n", score);
  printf("\n\n");

  // Question Number 5
  printf("\033[0;33mQuestion 5\n");
  printf("\033[0;37mA ship anchored in a port has a ladder which hangs over the side. The length of the ladder is 200cm, the distance between each rung is 20cm and the bottom rung touches the water.\nThe tide rises at a rate of 10cm an hour. When will the water reach the fifth rung?\n");
  printf("A.20cm\nB.200cm\nC.Never\nD.I don’t know\n");
  char correctAnswer5[] = "C";
  char userAnswer5[3];
  scanf("%s", userAnswer5);
  if (strcmp(userAnswer5, correctAnswer5) == 0 || strcmp(userAnswer5, "c") == 0) {
    printf("\033[0;32mCorrect!\n");
    score++;
  } else {
    printf("\033[0;31mWrong the correct answer is C \n");
    printf("\033[0;32mExplanation: The height of the fifth rung above the water is 80cm. As the tide rises at 10 cm per hour, it will take 12 hours (120cm/10cm per hour) for the water to reach the fifth rung.");
  }
  printf("\033[0;37m\nYour score %d out 6\n", score);
  printf("\n\n");

  // Question Number 6
  printf("\033[0;33mQuestion 6\n");
  printf("\033[0;37mWhat is the Integral of cos(x)/(x^2+1)?\n");
  printf("A.pi over e\nB.ln(pi over 2)\nC.ln\nD.I don't know\n");
  char correctAnswer6[] = "A";
  char userAnswer6[3];
  scanf("%s", userAnswer6);
  if (strcmp(userAnswer6, correctAnswer6) == 0 || strcmp(userAnswer6, "a") == 0){
    printf("\033[0;32mCorrect!\n");
    score++;
  } else {
    printf("\033[0;31mThat was close! The correct answer is A \n");
    printf("Explaination: Using complex analysis and the residue theorem, we can evaluate the integral of cos(x)/(x^2 + 1) from negative infinity to infinity. We first consider a function f(z) with simple poles at i and -i in the upper half-plane. By evaluating the integral along a semi-circular contour, we can take the residue of f(z) at i and obtain the value of the integral. The final result is π/e.");
  }
  printf("\033[0;37m\nYour score %d out 6.\n", score);
  printf("\n\n");

  // Here's the final gives the scoreon how they did.

  int finalScore = (int)(score / 6.0 * 100);

  int correct = score;
  int wrong = 6 - score; // The score is displayed as a percentage and the number of correct and wrong
  // answers are also shown.

  if (finalScore >= 80) { // If the score is 80 or above, the user is congratulated for passing the quiz.

    printf("Congratulations!🥳 You passed! Great job!\n");
  } else if (finalScore >= 50) { // If the score is between 50 and 79, the user is encouraged to keep practicing.

    printf("You were close. Keep practicing!🔥\n");
  } else { // If the score is below 50, the user is told they failed and encouraged to study and practice more.
    printf("Sorry, you failed. Keep studying and practicing.\n");
  }
  printf("You got %d right and %d wrong. Your score is %d.\n", correct, wrong, finalScore); // The code then provides feedback based on the final score.
}
