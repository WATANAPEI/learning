// use command below to compile
// g++ -pthread -std=c++1z q062.cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <thread>
#include <random>
#include <chrono>

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
    vector<thread> threads;
    auto first = begin(v);
    auto last = first;
    for(size_t i = 0; i < n_thread; i++) {
        first = last;
        last = (i == n_thread-1)? end(v): first + v.size() / n_thread;
        threads.emplace_back([first, last, &min, &max]() {
                min.push_back(*min_element(first, last));
                max.push_back(*max_element(first, last));
                        });
    }
    for(size_t i = 0; i < n_thread; i++) {
        threads[i].join();
    }
    return make_pair(*min_element(min.begin(), min.end()), *max_element(max.begin(), max.end()));
}


int main() {
    const size_t count = 1'000'000;
    using type = int;
    vector<type> data(count);

    random_device rd;
    mt19937 mt;
    auto seed_data = array<int, mt19937::state_size>{};
    generate(begin(seed_data), end(seed_data), ref(rd));
    seed_seq seq(begin(seed_data), end(seed_data));
    mt.seed(seq);
    uniform_int_distribution<> ud(1, 100);

    generate_n(begin(data), count, [&mt, &ud]() {
            return ud(mt);
            });

    //vector<type> v1{1, 4, 2, 12, 5, 9, 0, -3, 4,5};
    //vector<type> v2{1.3, 4.0, 0.2, 0.12, -5.2, 9.9, 0.0, -3, 4.2,5.9};
    auto start = chrono::system_clock::now();
    pair<type, type> result = pminmax(data);
    auto end = chrono::system_clock::now();
    auto t1 = chrono::duration_cast<chrono::milliseconds>(end - start);
    cout << "time: " << t1.count() << "ms" << endl;
    cout << "min: " << result.first << ", max: " << result.second << endl;


}
