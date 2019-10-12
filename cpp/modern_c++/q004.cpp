#include <iostream>
#include <vector>
#include <algorithm>
#include "mylib.h"


int q4() {
    int n;
    std::cin >> n;

    if ( n <= 0 ) {
        std::cout << "Input must be an positive integer\n";
        return -1;
    }

    for (int i = 1; i < n; i++) {
        if (is_prime(n - i)) {
            std::cout << "largest prime is " << n - i << std::endl;
            return 0;
        }
    }
}

int q5() {
    int n;
    std::cin >> n;

    if ( n <= 0 ) {
        std::cout << "Input must be an positive integer\n";
        return -1;
    }

    std::vector<int> primes;
    for (int i = 1; i < n; i++) {
        if (is_prime(n - i)) {
            primes.push_back(n-i);
            std::cout << "prime number is found: " << n - i << std::endl;
        }
    }
    if (primes.size() == 0) {
        std::cout << "prime number was not found" << std::endl;
        return 0;
    }
    std::vector<std::pair<int, int>> sexy_prime;
    for (size_t i = 0; i < primes.size()-1; ++i) {
        for (size_t j = i+1; j < primes.size(); ++j) {
            if (primes.at(i) - 6 == primes.at(j) ) {
                sexy_prime.push_back(std::make_pair(primes.at(i), primes.at(j)));
                //cout << "i: " << primes.at(i) << endl;
                //cout << "j: " << primes.at(j) << endl;
            }
        }
    }
    //output
    if (sexy_prime.size() != 0) {
        for_each(sexy_prime.begin(), sexy_prime.end(), [](auto pair) {
                std::cout << "sexy prime number is found: " << std::endl;
                std::cout << pair.first << ", " << pair.second << std::endl;
            }
        );
    } else {
        std::cout << "sexy prime number is not found\n";
    }
}


int main() {
    return q5();

}
