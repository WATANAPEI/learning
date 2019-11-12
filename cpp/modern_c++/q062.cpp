#include <iostream>
#include <vector>
#include <algorithm>
#include <thread>

using namespace std;

template <typename T>
pair<T, T> minmax(vector<T> v) {
    pair<T, T> result;
    result.first = *min_element(v.begin(), v.end());
    result.second = *max_element(v.begin(), v.end());
    return result;
}

template <typename T>
pair<T, T> pminmax(vector<T> const & v) {
    vector<T> min;
    vector<T> max;
    size_t n_thread = 10;
    vector<thread> threads(n_thread);
    auto first = begin(v);
    auto last = first;
    for(int i = 0; i < n_thread; i++) {
        first = last;
        last = (i == n_thread-1)? end(v): first + v.size() / n_thread;
        threads.emplace_back([first, last, min, max]() {
                min.push_back(*min_element(first, last));
                max.push_back(*max_element(first, last));
                        });
    }
    for(int i = 0; i < n_thread; i++) {
        threads[i].join();
    }
    return make_pair(*min_element(min.begin(), min.end()), *max_element(max.begin(), max.end()));
}


int main() {
    using type = double;
    //vector<type> v1{1, 4, 2, 12, 5, 9, 0, -3, 4,5};
    vector<type> v2{1.3, 4.0, 0.2, 0.12, -5.2, 9.9, 0.0, -3, 4.2,5.9};
    pair<type, type> result = pminmax(v2);
    cout << "min: " << result.first << ", max: " << result.second << endl;


}
