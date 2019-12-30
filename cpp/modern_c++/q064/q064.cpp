#include <iostream>
#include <future>
#include <vector>
#include <array>
#include <sstream>
#include <iomanip>
#include <iterator>
#include <thread>

using namespace std;

template<class RandomItr>
RandomItr partition(RandomItr first, RandomItr last) {
    auto pivot = *first;
    auto i = first + 1;
    auto j = last - 1;
    while (i <= j) {
        while(i <= j && *i <= pivot) i++;
        while(i <= j && *j > pivot) j--;
        if (i < j) std::iter_swap(i,j);
    }
    std::iter_swap(i - 1, first);
    return i - 1;
}

template <class RandomItr>
void quicksort(RandomItr first, RandomItr last) {
    if (first < last ) {
        auto p = partition(first, last);
        quicksort(first, p);
        quicksort(p+1, last);
    }
}

template <class RandomItr>
void pquicksort(RandomItr first, RandomItr last) {
    if (first < last ) {
        auto const p = partition(first, last);
        if(last - first <= 100000)
        {
            pquicksort(first, p);
            pquicksort(p+1, last);
        }else{
            auto f1 = async(launch::async, [first, p] () {pquicksort(first, p);});
            auto f2 = async(launch::async, [last, p]() {pquicksort(p+1, last);});
            f1.wait();
            f2.wait();
        }
    }
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


template <typename C>
void PrintAns(C c) {
    string str(15, '*');
    cout << str << endl;
    copy(c.begin(), c.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    pquicksort(c.begin(), c.end());
    copy(c.begin(), c.end(), ostream_iterator<int>(cout, " "));
    cout << endl;

}

int main() {
    vector<int> v1{1, 3, 5,2, 3,11,4};
    array<int, 5> a1{1,2,5,67, 4};



    PrintAns<vector<int>>(v1);
    PrintAns<array<int, 5>>(a1);


}
