For number 4, you can declare a void function called greet like this:

void greet() {
  cout << "Hello, welcome to my program!" << endl;
}

Then, you can call the function in main() like this:

int main() {
  greet();
  return 0;
}

For number 5, you can declare an integer and a float like this:

int num1 = 5;
float num2 = 5.5;

Then, you can declare a void function called printNum like this:

void printNum(int numInt, float numFloat) {
  cout << "Integer: " << numInt << endl;
  cout << "Float: " << numFloat << endl;
}

You can call the function in main() like this:

int main() {
  printNum(num1, num2);
  return 0;
}

For number 6, you can declare two integers like this:

int num1 = 2;
int num2 = 5;

Then, you can declare a function called addNum that takes two parameters and returns an integer like this:

int addNum(int numInt1, int numInt2) {
  return numInt1 + numInt2;
}

You can call the function in main() like this:

int main() {
  int sum = addNum(num1, num2);
  cout << "The sum is: " << sum << endl;
  return 0;
}

For number 7, you can declare a double pointer variable with a null value like this:

double *pDouble = nullptr;

Then, you can create a double variable with the value of 11 like this:

double doubleVar = 11;

Next, you can assign the address of the doubleVar variable to the pDouble pointer like this:

pDouble = &doubleVar;

Finally, use the pDouble pointer to change the value of doubleVar and display it like this:

*pDouble = 7.5;
cout << "New value of doubleVar: " << doubleVar << endl;

For dynamic memory allocation, it depends on what you want to achieve. You can allocate memory for an array using new like this:

int *myArray = new int[5];

This creates an integer array with 5 elements and myArray points to the first element. You can access the elements like you would with a regular array.

When you are done with the array, you should deallocate the memory using delete[] like this:

delete [] myArray;
