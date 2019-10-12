#include <iostream>
#include "mylib.h"

using namespace std;



int calc_lcm(int x, int y) {
    if ( x <= 0 || y <= 0 ) {
        cout << "input numbers must be positive integers\n";
    }
    sort(x, y);
    int gcd = calc_gcd(x, y);
    return  x * y / gcd;
}

int main() {
    int x, y;
    cin >> x >> y;

    int gcd = calc_gcd(x, y);
    cout << "gcd: " << gcd << endl;

    int lcm = calc_lcm(x, y);
    cout << "lcm: " << lcm << endl;
}
