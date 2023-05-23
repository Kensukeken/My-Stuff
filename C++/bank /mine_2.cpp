#include <iostream>
using namespace std; 

//Add function signatures above the main program
double deposit(double saveBal);
double withdraw(double saveBal);
void printBalance(double saveBal);

int main() {
  //All the variables you need
  string name = "Haya"; //Add your name
  double savings = 1000; 
  int choice1,choice2;  
  bool access = true; //Needs to be equal to true

  while(access){
    cout << name << " how can we help you today? \n"; 
    cout << "Enter the # for your choice \n"; 
    cout << "1. Deposit \n";
    cout << "2. Withdraw \n"; 
    cout << "3. Print balance of all accounts.\n"; 

    cin >> choice1;
    //Have the user cin into the choice1 variable 

    //Fill in the if statement arguements that are checking the value of choice1
    if(choice1 == 1){
      cout << "You chose to Deposit. \n"; 
      //Call your deposit function and have the value to return to the savings variable
    }
   else if(choice2 == 2){
      cout << "You chose to Withdraw. \n"; 
      //Call your withdraw function and have the value to return to the savings variable
    }
    else if(choice1 == 3){
      cout << "You chose to print Balances. \n"; 
      //Call your print balance function 
      printBalance(savings);
    } else {
      cout << "Invalid choice.\n";
  }
    cout << "Would you like to do another transaction?\n"; 
    cout << "Enter 1 for yes.\n";
    cout << "Enter 2 for no.\n";  
    //Have the user cin into the choice2 variable
    cin >> choice2;

    //Fill in the if statement arguements that are checking the value of choice2
    if(choice2 == 1){
      cout << "You chose yes!\n";
    }
    else if(choice2 == 2){
      cout << "You chose no.\n";
      //Make the access variable false; 
      access = false;
      cout << "Have a nice rest of the day!\n" <<endl; 
    }
    return 0;
  }
}

//All your function code is below
double deposit(double saveBal){
  double depositAmt;  
  cout << "Enter the $ amount you would like to deposit.\n"; 
  //Have the user cin into the depositAmt variable 
  cin >> depositAmt;
  //Add the deposit amount to your savings balance  
  saveBal += depositAmt;
  cout << "Your new savings balance is: " << saveBal << "\n"; 

  //return your new savings balance 
  return saveBal;
}

double withdraw(double saveBal){
  double withdrawAmt;  
  cout << "Enter the $ amount you would like to withdraw.\n"; 
  //Have the user cin into the withdrawAmt variable  
  cin >> withdrawAmt;

  //Fill in the if statement arguement to check if the
  //withdraw amount is greater than your savings balance
  if(withdrawAmt > saveBal){
    cout << "You do not have enough funds for this withdraw. \n"; 
    //return the original savings balance
    return saveBal;
  }

  //Subtract the withdraw amount from your savings balance 
  saveBal -= withdrawAmt;
  cout << "Your new savings balance is: " << saveBal << "\n"; 

  //return your new savings balance
  return saveBal;
}

void printBalance(double saveBal){
  cout << "Account Balance \n"; 
  //Print out a message showing the savings balance
  cout << "Savings balance: $" << saveBal << "\n";
  cin >> saveBal;
}
