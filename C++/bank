#include <iostream>
using namespace std;

// Function signatures
double deposit(double saveBal);
double withdraw(double saveBal);
void printBalance(double saveBal);

int main() {
  // All the variables you need
  string name = "John"; // Add your name
  double savings = 1000;
  int choice1, choice2;
  bool access = true; // Needs to be equal to true

  while (access) {
    cout << name << ", how can we help you today?\n";
    cout << "Enter the number for your choice:\n";
    cout << "1. Deposit\n";
    cout << "2. Withdraw\n";
    cout << "3. Print balance of all accounts.\n";
    cin >> choice1;

    if (choice1 == 1) {
      cout << "You chose to deposit.\n";
      savings = deposit(savings);
    } else if (choice1 == 2) {
      cout << "You chose to withdraw.\n";
      savings = withdraw(savings);
    } else if (choice1 == 3) {
      cout << "You chose to print balances.\n";
      printBalance(savings);
    } else {
      cout << "Invalid choice.\n";
    }

    cout << "Would you like to do another transaction?\n";
    cout << "Enter 1 for yes.\n";
    cout << "Enter 2 for no.\n";
    cin >> choice2;

    if (choice2 == 1) {
      cout << "You chose yes!\n";
    } else if (choice2 == 2) {
      cout << "You chose no.\n";
      access = false;
      cout << "Have a nice rest of the day!\n" << endl;
    } else {
      cout << "Invalid choice.\n";
    }
  }
  
  return 0;
}

double deposit(double saveBal) {
  double depositAmt;
  cout << "Enter the $ amount you would like to deposit:\n";
  cin >> depositAmt;
  saveBal += depositAmt;
  cout << "Your new savings balance is: " << saveBal << "\n";
  return saveBal;
}

double withdraw(double saveBal) {
  double withdrawAmt;
  cout << "Enter the $ amount you would like to withdraw:\n";
  cin >> withdrawAmt;

  if (withdrawAmt > saveBal) {
    cout << "You do not have enough funds for this withdrawal.\n";
    return saveBal;
  }

  saveBal -= withdrawAmt;
  cout << "Your new savings balance is: " << saveBal << "\n";
  return saveBal;
}

void printBalance(double saveBal) {
  cout << "Account Balance:\n";
  cout << "Savings balance: $" << saveBal << "\n";
}
