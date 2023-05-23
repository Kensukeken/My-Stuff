#include <iostream>
using namespace std;

// Function signatures
double deposit(double saveBal);
double withdraw(double saveBal);
void printBalance(double saveBal);

int main() {
  string name = "Haya";
  double savings = 1000;
  int choice1, choice2;
  bool access = true;

  while (access) {
    cout << name << ", how can we help you today?\n";
    cout << "Enter the number for your choice:\n";
    cout << "1. Deposit\n";
    cout << "2. Withdraw\n";
    cout << "3. Print balance of all accounts\n";

    cin >> choice1; // User inputs the choice

    if (choice1 == 1) {
      cout << "You chose to deposit.\n";
      savings = deposit(savings); // Call deposit function and update savings balance
    }
    else if (choice1 == 2) {
      cout << "You chose to withdraw.\n";
      savings = withdraw(savings); // Call withdraw function and update savings balance
    }
    else if (choice1 == 3) {
      cout << "You chose to print balances.\n";
      printBalance(savings); // Call printBalance function to display savings balance
    }
    else {
      cout << "Invalid choice.\n"; // Displayed when an invalid choice is entered
    }

    cout << "Would you like to do another transaction?\n";
    cout << "Enter 1 for yes.\n";
    cout << "Enter 2 for no.\n";
    cin >> choice2; // User inputs choice for another transaction

    if (choice2 == 1) {
      cout << "You chose yes!\n"; // Displayed if the user chooses to continue
    }
    else if (choice2 == 2) {
      cout << "You chose no.\n";
      access = false; // Set access variable to false to exit the while loop
      cout << "Have a nice rest of the day!\n" << endl; // Display farewell message
    }
  }

  return 0; // Exit the program
}

double deposit(double saveBal) {
  double depositAmt;
  cout << "Enter the amount you would like to deposit: $";
  cin >> depositAmt; // User inputs the deposit amount
  saveBal += depositAmt; // Add the deposit amount to the savings balance
  cout << "Your new savings balance is: $" << saveBal << "\n"; // Display the updated savings balance
  return saveBal; // Return the new savings balance
}

double withdraw(double saveBal) {
  double withdrawAmt;
  cout << "Enter the amount you would like to withdraw: $";
  cin >> withdrawAmt; // User inputs the withdrawal amount

  if (withdrawAmt > saveBal) {
    cout << "You do not have enough funds for this withdrawal.\n"; // Displayed if withdrawal amount exceeds savings balance
    return saveBal; // Return the original savings balance
  }

  saveBal -= withdrawAmt; // Subtract the withdrawal amount from the savings balance
  cout << "Your new savings balance is: $" << saveBal << "\n"; // Display the updated savings balance
  return saveBal; // Return the new savings balance
}

void printBalance(double saveBal) {
  cout << "Account Balance:\n";
  cout << "Savings balance: $" << saveBal << "\n"; // Display the savings balance
}
