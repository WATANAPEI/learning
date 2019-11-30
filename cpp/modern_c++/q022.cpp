#include <iostream>

using namespace std;
constexpr long double k = 273.15;
constexpr long double f = 211.95;

long double to_deg(long double in) {
    return in - k;
}
long double to_f(long double in) {
    return in - f;
}

long double operator "" _deg(long double in){
    return k + in;
}

long double operator "" _K(long double in) {
    return in;
}

long double operator "" _f(long double in) {
    return f + in;
}

int main() {
    long double kel = 0.0_K;
    long double cel = 0.0_deg;
    long double f = 0.0_f;
    cout << kel << endl;
    cout << cel << endl;
    cout << f << endl;
    cout << f + f << endl;
    cout << to_deg(f) << endl;
}
