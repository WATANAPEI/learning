// use command below to compile
// g++ -pthread -std=c++1z q062.cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <thread>
#include <random>
#include <chrono>
#include <future>
#include <functional>
#include <cassert>

using namespace std;

template <typename Iter, typename F>
auto sprocess(Iter begin, Iter end, F&& f) {
    return forward<F>(f)(begin, end);
}

template <typename Iter>
auto smin(Iter begin, Iter end) {
    return sprocess(begin, end,
            [](auto b, auto e) { return *min_element(b, e); });
}

template<typename Iter>
auto smax(Iter begin, Iter end) {
    return sprocess(begin, end,
            [](auto b, auto e) { return *max_element(b, e); });
}

template <typename Iter, typename F>
auto pprocess(Iter begin, Iter end, F&& f) {
    auto size = distance(begin, end);
    if (size <= 10000) {
        return forward<F>(f)(begin, end);
    }
    else {
        int task_count = thread::hardware_concurrency();
        vector<future<typename iterator_traits<Iter>::value_type>> tasks;
        auto first = begin;
        auto last = first;
        size /= task_count;
        for (int i = 0; i < task_count; i++) {
            first = last;
            if (i == task_count - 1) last = end;
            else advance(last, size);

            tasks.emplace_back(async(
                        std::launch::async,
                        [first, last, &f]() {
                            return forward<F>(f)(first, last);
                            }));
        }
        vector<typename iterator_traits<Iter>::value_type> mins;
        for (auto & t: tasks) {
            mins.push_back(t.get());
        }
        return forward<F>(f)(std::begin(mins), std::end(mins));
    }
}

template<typename Iter>
auto pmin(Iter begin, Iter end){
    return pprocess(begin, end,
            [](auto b, auto e) { return *min_element(b, e); }
            );
}

template<typename Iter>
auto pmax(Iter begin, Iter end){
    return pprocess(begin, end,
            [](auto b, auto e) { return *max_element(b, e); }
            );
}

int main() {
    const size_t count = 1000000;
    vector<int> data(count);

    random_device rd;
    mt19937 mt;
    auto seed_data = array<int, mt19937::state_size> { };
    generate(begin(seed_data), end(seed_data), ref(rd));
    seed_seq seq(begin(seed_data), end(seed_data));
    mt.seed(seq);
    uniform_int_distribution<> ud(1,1000);

    generate_n(begin(data), count, [&mt, &ud]() {return ud(mt); });

    {
        cout << "minimum element" << endl;
        auto start = chrono::system_clock::now();
        auto r1 = smin(data.begin(), data.end());
        auto end = chrono::system_clock::now();
        auto t1 = chrono::duration_cast<chrono::milliseconds>(end - start);
        cout << "seq time: " << t1.count() << "ms" << endl;

        start = chrono::system_clock::now();
        auto r2 = pmin(data.begin(), data.end());
        end = chrono::system_clock::now();
        auto t2 = chrono::duration_cast<chrono::milliseconds>(end - start);
        cout << "seq time: " << t2.count() << "ms" << endl;

        assert(r1 == r2);
    }
    {
        cout << "maximum element" << endl;
        auto start = chrono::system_clock::now();
        auto r1 = smax(data.begin(), data.end());
        auto end = chrono::system_clock::now();
        auto t1 = chrono::duration_cast<chrono::milliseconds>(end - start);
        cout << "seq time: " << t1.count() << "ms" << endl;

        start = chrono::system_clock::now();
        auto r2 = pmax(data.begin(), data.end());
        end = chrono::system_clock::now();
        auto t2 = chrono::duration_cast<chrono::milliseconds>(end - start);
        cout << "seq time: " << t2.count() << "ms" << endl;

        assert(r1 == r2);
    }
}
