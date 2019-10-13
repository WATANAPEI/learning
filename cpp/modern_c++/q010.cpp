#include <iostream>
#include "mylib.h"
#include <bitset>
#include <cmath>

using namespace std;

template <size_t N>
unsigned int calc_decimal(bitset<N> b) {
    unsigned int result = 0;
    for(unsigned int i = 0; i <= b.size(); i++) {
        result += b[i] * pow(2, i);
    }
    return result;
}

template <size_t N>
bitset<N> calc_greycode(bitset<N> b) {
    return b ^ (b >> 1);

}

template <size_t N>
bitset<N> calc_binary(bitset<N> g) {
    bitset<N> b(N);
    // 1 digit
    b[N-1] = g[N-1];
    //cout << "g[N-1] : " << g[N-1] << endl;
    // 2 or more digit
    for(int i = N - 2; i >= 0; --i) {
        //cout << "i: " << i << " ";
        //cout << "b[i]: " << b[i] << ", b[i+1]: " << b[i+1] << ", g[i]: " << g[i] << endl;
        b[i] = b[i+1] ^ g[i];
    }
    return b;
}


int main() {
    const size_t upper_digit = 5;
    using bit = bitset<upper_digit>;

    size_t i = 0;
    while(!bit(i).all()) {
        cout << i << ":\t " <<  bit(i) << " / " << calc_greycode(bit(i)) << "\t" << calc_binary(calc_greycode(bit(i))) << "\t" << calc_decimal(calc_binary(calc_greycode(bit(i)))) << endl;
        ++i;
    }
    cout << i << ":\t " <<  bit(i) << " / " << calc_greycode(bit(i)) << "\t" << calc_binary(calc_greycode(bit(i))) << "\t" << calc_decimal(calc_binary(calc_greycode(bit(i)))) <<  endl;
}
