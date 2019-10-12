#include <iostream>
#include "mylib.h"
#include <bitset>

using namespace std;

template <size_t N>
std::bitset<N> calc_greycode(std::bitset<N> b) {
    return b ^ (b >> 1);

}

int main() {
    const size_t upper_digit = 5;
    using bit = bitset<upper_digit>;

    size_t i = 0;
    while(!bit(i).all()) {
        cout << i << ":\t " <<  bit(i) << " / " << calc_greycode(bit(i)) << endl;
        ++i;
    }
    cout << i << ":\t " <<  bit(i) << " / " << calc_greycode(bit(i)) << endl;
}
