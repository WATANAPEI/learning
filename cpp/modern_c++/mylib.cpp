#include <iostream>
#include <vector>
#include "mylib.h"
#include <algorithm>
#include <numeric>

std::vector<int> calc_divisor(int n) {
    std::vector<int> divisors;
    for(int i = 1; i < n; ++i) {
        if (n % i == 0) {
            divisors.push_back(i);
        }
    }
    //std::cout << "num: " << n << std::endl;
    //dump(divisors);
    return divisors;
}

void dump(std::vector<int> v) {
    std::cout << "v: ";

    if (v.size() != 0) {
        for_each(v.begin(), v.end(), [](int e) {
                std::cout << e << " ";
               }
               );
        std::cout << std::endl;
    } else {
        std::cout << "no divisor\n";
    }
}

void sort(int& x, int& y) {
    if ( x < y ) {
        int tmp;
        tmp = x;
        x = y;
        y = tmp;
    }
}

int calc_gcd(int x, int y) {
    if ( x <= 0 || y <= 0 ) {
        std::cout << "input numbers must be positive integers\n";
    }

    sort(x, y);
    int remaining;
    do {
        remaining = x % y;
        x = y;
        y = remaining;
    } while (remaining != 0);
    return x;
}

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
