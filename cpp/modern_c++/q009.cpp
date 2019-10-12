#include <iostream>
#include <algorithm>
#include "mylib.h"
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<pair<int, int>> result;
    for(int i = 2; i < n; i++) {
        int tmp = n;
        int count = 0;

        while (1) {
            if (tmp % i != 0) {
                break;
            } else {
                if (is_prime(i)) {
                    tmp /= i;
                    ++count;
                } else {
                    break;
                }
            }
        }
        if ( count != 0) {
            result.push_back(make_pair(i, count));
        }
    }
    cout << "result: " << endl;
    for_each(result.begin(), result.end(), [](auto e) {
            cout << e.first << "**" << e.second << endl;
            }
            );

}
