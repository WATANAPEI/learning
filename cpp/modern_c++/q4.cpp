#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool is_prime(int n) {
    int target = n;
    int remaining = -1;
    for (int i = 1; i < n-1; ++i) {
        remaining = target % (target - i);
        if (remaining == 0) {
            return false;
        }
    }
    return true;
}

int q4() {
    int n;
    cin >> n;

    if ( n <= 0 ) {
        cout << "Input must be an positive integer\n";
        return -1;
    }

    for (int i = 1; i < n; i++) {
        if (is_prime(n - i)) {
            cout << "largest prime is " << n - i << endl;
            return 0;
        }
    }
}

int q5() {
    int n;
    cin >> n;

    if ( n <= 0 ) {
        cout << "Input must be an positive integer\n";
        return -1;
    }

    vector<int> primes;
    for (int i = 1; i < n; i++) {
        if (is_prime(n - i)) {
            primes.push_back(n-i);
            cout << "prime number is found: " << n - i << endl;
        }
    }
    if (primes.size() == 0) {
        cout << "prime number was not found" << endl;
        return 0;
    }
    vector<pair<int, int>> sexy_prime;
    for (size_t i = 0; i < primes.size()-1; ++i) {
        for (size_t j = i+1; j < primes.size(); ++j) {
            if (primes.at(i) - 6 == primes.at(j) ) {
                sexy_prime.push_back(make_pair(primes.at(i), primes.at(j)));
                //cout << "i: " << primes.at(i) << endl;
                //cout << "j: " << primes.at(j) << endl;
            }
        }
    }
    //output
    if (sexy_prime.size() != 0) {
        for_each(sexy_prime.begin(), sexy_prime.end(), [](auto pair) {
                cout << "sexy prime number is found: " << endl;
                cout << pair.first << ", " << pair.second << endl;
            }
        );
    } else {
        cout << "sexy prime number is not found\n";
    }
}


int main() {
    return q5();

}
