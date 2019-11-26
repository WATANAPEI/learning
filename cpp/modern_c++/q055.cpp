#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

template<typename Input, typename Output>
void zip(Input begin1, Input end1, Input begin2, Input end2, Output result) {
    int min_distance = min(distance(begin1, end1), distance(begin2, end2));
    for(int i = 0; i < min_distance; i++) {
        result++ = (make_pair(*(begin1+i) , *(begin2+i)));
    }
}

template<typename T>
vector<pair<T, T>> zip(vector<T> v1, vector<T> v2) {
    vector<pair<T, T>> result;
    zip(v1.begin(), v1.end(), v2.begin(), v2.end(), back_inserter(result));
    return result;
}

int main() {
    vector<int> v11{1, 2,4, 5,2, 5,29,2};
    vector<int> v12{3, 2,1, 4, 2,1};

    vector<double> v21{3.2, 4.1, 2.2};
    vector<double> v22{3.1, 2.0, 1.1, 3.1};
    vector<pair<int, int>> result1 = zip(v11, v12);
    for(auto e : result1) {
        cout << e.first << ",  " << e.second << endl;
    }
    vector<pair<double, double>> result2 = zip(v21, v22);
    for(auto e : result2) {
        cout << e.first << ",  " << e.second << endl;
    }


}
