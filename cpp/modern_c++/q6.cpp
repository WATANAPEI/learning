#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>

using namespace std;

void dump(vector<int> v) {
    cout << "v: ";

    if (v.size() != 0) {
        for_each(v.begin(), v.end(), [](int e) {
               cout << e << " ";
               }
               );
        cout << endl;
    } else {
        cout << "no divisor\n";
    }
}

vector<int> calc_divisor(int n) {
    vector<int> divisors;
    for(int i = 1; i < n; ++i) {
        if (n % i == 0) {
            divisors.push_back(i);
        }
    }
    //cout << "num: " << n << endl;
    //dump(divisors);
    return divisors;
}

int q6() {
    int n;
    cin >> n;

    if ( n <= 0 ) {
        cout << "Input must be an positive integer\n";
        return -1;
    }
    for(int i = 1; i < n; i++) {
        vector<int> divisors = calc_divisor(i);
        int sum_divisors = accumulate(divisors.begin(), divisors.end(), 0);
     //   cout << "sum: " << sum_divisors << endl;
        if (sum_divisors > i) {
            cout << "excessive numner: " << i << endl;
            cout << "excessive : " << sum_divisors - i << endl;
        }
    }

}

int main() {
    return q6();

}
