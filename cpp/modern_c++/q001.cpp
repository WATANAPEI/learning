#include <iostream>
using namespace std;

int fizzbuzz(int n) {
    int sum = 0;
    for(int i=0; i<n; ++i) {
        if((i % 3 == 0) || (i % 5 == 0))
            sum += i;
    }
    return sum;
}

int main () {
    int n;
    cin >> n;
    if (n<0) {
        cout << "argument should be positive integer\n";
        return 1;
    }
    int result = fizzbuzz(n);
    cout << "result: " << result << endl;
}
