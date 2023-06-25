#include <iostream>
#include <cmath>
using namespace std;
int main() {
  // Define the function to integrate.
  double f(double x) {
    return x * x;
  }
  // Calculate the area under the curve.
  double area = integrate(f, 0, 1);
  // Print the area.
  cout << "The area under the curve is " << area << endl;
  return 0;
}
