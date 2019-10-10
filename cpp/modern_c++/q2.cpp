#include <iostream>

using namespace std;

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
        cout << "input numbers must be positive integers\n";
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
