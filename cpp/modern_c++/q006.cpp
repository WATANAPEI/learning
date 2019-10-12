#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include "mylib.h"

int q6() {
    int n;
    std::cin >> n;

    if ( n <= 0 ) {
        std::cout << "Input must be an positive integer\n";
        return -1;
    }
    for(int i = 1; i < n; i++) {
        std::vector<int> divisors = calc_divisor(i);
        int sum_divisors = accumulate(divisors.begin(), divisors.end(), 0);
     //   std::cout << "sum: " << sum_divisors << std::endl;
        if (sum_divisors > i) {
            std::cout << "excessive numner: " << i << std::endl;
            std::cout << "excessive : " << sum_divisors - i << std::endl;
        }
    }

}

int main() {
    return q6();

}
