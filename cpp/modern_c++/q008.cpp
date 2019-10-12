#include <iostream>
#include <vector>
#include "mylib.h"
#include <cmath>

int main() {
    for(int i = 100; i < 1000; ++i) {
        std::vector<int> digits = div_digit(i);
        int n = digits.size();
        if (( static_cast<int>(pow(digits.at(0), n))
                + static_cast<int>(pow(digits.at(1), n))
                + static_cast<int>(pow(digits.at(2), n)) ) == i) {
            std::cout << "narcisssistic num is " << i << std::endl;
        }
    }

}
