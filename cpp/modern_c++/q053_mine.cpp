#include <iostream>
#include <vector>
#include <numeric>
#include <iomanip>

using namespace std;

double calc_avg(vector<int> const & v) {
    double result;
    result = static_cast<double>(accumulate(v.cbegin(), v.cend(), 0.0) / v.size());
    return result;
}

int main() {
    vector<int> movie1 = {1, 5, 3, 5, 1, 9, 10, 8, 10, 2, 4, 2, 0, 0, 10, 3, 6, 2, 4, 0};
    vector<int> movie2 = {2, 9, 1, 1, 1, 9, 9, 3};
    vector<int> movie3 = {1, 2, 3, 5, 2, 9, 10, 8};
    double avg1 = calc_avg(movie1);
    double avg2 = calc_avg(movie2);
    double avg3 = calc_avg(movie3);
    cout << setprecision(1) << fixed << "avg1: " << avg1 << endl;
    cout << "avg2: " << avg2 << endl;
    cout << "avg3: " << avg3 << endl;


}

