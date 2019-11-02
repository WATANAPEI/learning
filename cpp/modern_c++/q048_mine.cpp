#include <utility>
#include <vector>
#include <iostream>
#include <map>

using namespace std;

template <typename T>
map<T, size_t> freq_count(vector<T> v) {
    map<int, size_t> counter;
    for(auto itr=v.begin(); itr!= v.end(); itr++) {
        if(counter.find(*itr) == counter.end()) {
            counter.insert(make_pair(*itr, 1));
        } else {
            ++counter.at(*itr);
        }
    }

    return counter;
}

template <typename T>
map<T, size_t> get_max(map<T, size_t> const & a) {
    pair<T, size_t> tmp = *a.cbegin();
    map<T, size_t> result;
    for(auto e: a) {
        if(tmp.second < e.second) {
            result.clear();
            result.insert(e);
        } else if (tmp.second = e.second) {
            result.insert(e);
        }
    }
    return result;
}

int main() {
    vector<int> v = {1, 1, 3, 5, 8, 13, 3, 5, 8,8,5};
    map<int, size_t> counter = freq_count(v);
    for(auto e : counter) {
        cout << e.first << ' ' << e.second << endl;
    }

    cout << "*********************" << endl;
    map<int, size_t> max = get_max(counter);
    for(auto e : max) {
        cout << e.first << ' ' << e.second << endl;
    }

}
