// use command below to compile:
// g++ -pthread -std=c++1z q061.cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <thread>
#include <random>
#include <array>
#include <functional>
#include <chrono>

using namespace std;
using ll = long long int;

template <typename T, typename F>
vector<T> alter(vector<T> data, F&& f) {
    transform(
            begin(data), end(data), begin(data),
            forward<F>(f)
            );

    return data;
}

template<typename T, typename F>
vector<T> palter(vector<T> data, F&& f) {
    if(data.size() <= 10000) {
        transform(begin(data), end(data), begin(data),
                forward<F>(f)
                );
    } else {
        vector<thread> threads;
        int thread_count = thread::hardware_concurrency();
        auto first = begin(data);
        auto last = first;
        auto size = data.size() / thread_count;
        for(int i = 0; i < thread_count; i++) {
            first = last;
            last = i == thread_count -1 ? end(data) : first + size;

            threads.emplace_back([first, last, &f]() {
                    transform(first, last, first, forward<F>(f)
                            );
                    });
        }

        for(int i = 0; i < thread_count; i++) {
            threads[i].join();
        }
    }
}

template<typename RandomAccessIterator, typename F>
void ptransform(RandomAccessIterator begin, RandomAccessIterator end, F&& f) {
    auto size = distance(begin, end);
    if (size <= 10000) {
        transform(begin, end, begin, forward<F>(f));
    }
    else {
        vector<thread> threads;
        int const thread_count = 10;
        auto first = begin;
        auto last = first;
        size /= thread_count;
        for (int i = 0; i < thread_count; ++i) {
            first = last;
            if (i == thread_count - 1) {
                last = end;
            } else {
                advance(last, size);
            }
            threads.emplace_back([first, last, &f]() {
                    transform(first, last, first, forward<F>(f));
                    });
        }
        for ( auto & t: threads) {
            t.join();
        }

    }
}

template <typename T, typename F>
vector<T> palter2(vector<T> data, F&& f) {
    ptransform(begin(data), end(data), forward<F>(f));

    return data;
}


int main() {
    const size_t count = 100'000'000;
    vector<int> data(count);

    random_device rd;
    mt19937 mt;
    auto seed_data = array<int, mt19937::state_size> {};
    generate(begin(seed_data), end(seed_data), ref(rd));
    seed_seq seq(begin(seed_data), end(seed_data));
    mt.seed(seq);
    uniform_int_distribution<> ud(1, 100);

    generate_n(begin(data), count, [&mt, &ud]() {return ud(mt); });

    auto start = chrono::system_clock::now();
    auto r1 = alter(data, [](int const e) { return e*e; });
    auto end = chrono::system_clock::now();
    auto t1 = chrono::duration_cast<chrono::milliseconds>(end - start);
    cout << "time: " << t1.count() << "ms" << endl;

    start = chrono::system_clock::now();
    auto r2 = palter(data, [](int const e) { return e*e; });
    end = chrono::system_clock::now();
    auto t2 = chrono::duration_cast<chrono::milliseconds>(end - start);
    cout << "time: " << t2.count() << "ms" << endl;

    start = chrono::system_clock::now();
    auto r3 = palter2(data, [](int const e) { return e*e; });
    end = chrono::system_clock::now();
    auto t3 = chrono::duration_cast<chrono::milliseconds>(end - start);
    cout << "time: " << t3.count() << "ms" << endl;
}
