#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

template<typename T>
vector<pair<T, T>> make_pairs(vector<T> & v) {
    vector<pair<T, T>> result;
    if(v.size() %2 == 1) {
        v.erase(v.end());
    }
    for(unsigned int i = 0; i < v.size(); i+= 2) {
        result.push_back(make_pair(v.at(i), v.at(i+1)));
    }
    return result;

}

int main() {
    vector<int> v{1, 4, 2, 21, 32, 49,-1, 4};
    vector<string> v2{"aaa", "test", "21gs", "9ss9", "fs02"};
    vector<pair<int, int>> result_v = make_pairs(v);
    vector<pair<string, string>> result_v2 = make_pairs(v2);
    for(auto e: result_v) {
        cout << e.first << ", " << e.second << endl;
    }
    for(auto e: result_v2) {
        cout << e.first << ", " << e.second << endl;
    }

}
