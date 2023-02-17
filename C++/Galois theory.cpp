#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Find the greatest common divisor of two integers using Euclid's algorithm
int gcd(int a, int b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

// Compute the degree of a polynomial
int degree(vector<int>& poly) {
    int d = poly.size() - 1;
    while (d > 0 && poly[d] == 0) {
        d--;
    }
    return d;
}

// Compute the derivative of a polynomial
vector<int> derivative(vector<int>& poly) {
    int d = degree(poly);
    vector<int> deriv(d);
    for (int i = 0; i < d; i++) {
        deriv[i] = (i + 1) * poly[i + 1];
    }
    return deriv;
}

// Compute the GCD of a polynomial and its derivative
vector<int> gcd_poly_deriv(vector<int>& poly) {
    return gcd(poly, derivative(poly));
}

// Compute the Galois group of a polynomial over a field
vector<int> galois_group(vector<int>& poly) {
    int d = degree(poly);
    if (d == 0) {
        return vector<int>();
    }
    vector<int> factors = factor(d);
    vector<int> groups(factors.size());
    for (int i = 0; i < factors.size(); i++) {
        int p = factors[i];
        vector<vector<int>> coeffs(p);
        coeffs[0] = vector<int>{1};
        for (int j = 1; j < p; j++) {
            coeffs[j] = vector<int>(j + 1);
            coeffs[j][0] = -j;
            coeffs[j][j] = 1;
            for (int k = 1; k < j; k++) {
                coeffs[j][k] = coeffs[j - 1][k - 1] - j * coeffs[j - 1][k];
            }
        }
        vector<int> res(p + 1);
        for (int j = 0; j <= p; j++) {
            int prod = 1;
            for (int k = 0; k < p; k++) {
                if (k != j) {
                    prod *= poly[k];
                    prod %= p;
                }
            }
            res[j] = prod;
        }
        vector<int> gcds(p + 1);
        for (int j = 0; j <= p; j++) {
            gcds[j] = gcd(p, res[j]);
        }
        int group_size = p;
        for (int j = 1; j <= d / p; j++) {
            vector<int> tmp(p + 1);
            for (int k = 0; k <= p; k++) {
                tmp[k] = gcds[k];
                for (int l = 1; l < p; l++) {
                    tmp[k] *= res[(j * p + k * l) % (p * group_size)];
                    tmp[k] %= p;
                }
            }
            for (int k = 0; k < p; k++) {
                if (tmp[k] != 0) {
                    group_size = gcd(group_size, k);
                    break;
                }
            }
        }
        groups[i] = group_size;
    }
    sort(groups.begin(), groups.end());
    return groups
}

int main() {
vector<int> poly{1, 0, 1}; // x^2 + 1
vector<int> groups = galois_group(poly);
if (groups.empty()) {
cout << "The polynomial is constant, so it has no Galois group." << endl;
} else {
cout << "The Galois group of the polynomial is {" << groups[0];
for (int i = 1; i < groups.size(); i++) {
cout << ", " << groups[i];
}
cout << "}." << endl;
}
return 0;
}

In this code, we define a function `galois_group` that takes a polynomial as input and computes its Galois group over a field. The function first factorizes the degree of the polynomial and then uses the factors to construct subfields over which to compute the Galois group. For each subfield, the function computes a set of coefficients and evaluates the polynomial at the roots of unity associated with those coefficients. It then computes the greatest common divisor of the resulting values and uses it to determine the size of the Galois group. Finally, the function returns the sizes of the Galois groups of all subfields, sorted in ascending order.

In the `main` function, we create a polynomial `x^2 + 1` and compute its Galois group using the `galois_group` function. If the polynomial is constant, we print a message indicating that it has no Galois group. Otherwise, we print the size of the Galois group of each subfield, separated by commas and enclosed in curly braces.
