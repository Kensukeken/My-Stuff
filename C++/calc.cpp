#include <iostream>
#include <iomanip>
using namespace std;

int add(int a, int b) {
  return a + b;
}

int subtract(int a, int b) {
  return a - b;
}

int multiply(int a, int b) {
  return a * b;
}

float divide(int a, int b) {
  return static_cast<float>(a) / b;
}

int main() {
  int numberOne, numberTwo;
  float result;
  string myChoice;

  cout << "Enter your 1st number: ";
  cin >> numberOne;
  cout << "Enter your 2nd number: ";
  cin >> numberTwo;

  cout << "What would you like to do with these numbers?\n";
  cout << "add\n";
  cout << "subtract\n";
  cout << "multiply\n";
  cout << "divide\n";
  cin >> myChoice;

  if (myChoice == "add") {
    cout << "You chose to add.\n";
    result = add(numberOne, numberTwo);
    cout << "Result: " << result << "\n";
  }
  else if (myChoice == "subtract") {
    cout << "You chose to subtract.\n";
    result = subtract(numberOne, numberTwo);
    cout << "Result: " << result << "\n";
  }
  else if (myChoice == "multiply") {
    cout << "You chose to multiply.\n";
    result = multiply(numberOne, numberTwo);
    cout << "Result: " << result << "\n";
  }
  else if (myChoice == "divide") {
    cout << "You chose to divide.\n";
    result = divide(numberOne, numberTwo);
    cout << "Result: " << setprecision(2) << result << "\n";
  }
}
