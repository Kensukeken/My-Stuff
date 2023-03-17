#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

int main()
{
    // define character set of password
    string charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]";

    // seed the random number generator
    srand(static_cast<unsigned int>(time(nullptr)));

    // randomly generate a password of length 12
    string password;
    for (int i = 0; i < 12; ++i)
    {
        int index = rand() % charset.length();
        password += charset[index];
    }

    // output the generated password
    cout << "Randomly generated password: " << password << endl;

    return 0;
}
