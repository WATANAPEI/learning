#include <iostream>
#include <stack>
#include <vector>
#include <array>

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

template<class RandomItr, class Compare>
RandomItr partitionc(RandomItr first, RandomItr last, Compare comp) {
    auto pivot = *first;
    auto i = first + 1;
    auto j = last - 1;
    while (i <= j) {
        while(i <= j && comp(*i, pivot)) i++;
        while(i <= j && comp(*j, pivot)) j--;
        if (i < j) std::iter_swap(i,j);
    }
    std::iter_swap(i - 1, first);
    return i - 1;
}

template <class RandomItr>
void quicksorti(RandomItr first, RandomItr last) {
    std::stack<std::pair<RandomItr, RandomItr>> st;
    st.push(make_pair(first, last));
    while (!st.empty()){
        auto const [first, second] = st.top();
        st.pop();

        if(second - first < 2) continue;
        auto const p = partition(first, second);
        st.push(make_pair(first, p));
        st.push(make_pair(p+1, second));
    }
}


template <class RandomItr>
void quicksort(RandomItr first, RandomItr last) {
    if (first < last ) {
        auto p = partition(first, last);
        quicksort(first, p);
        quicksort(p+1, last);
    }
}

template <class RandomItr, class Compare>
void quicksort(RandomItr first, RandomItr last, Compare comp) {
    if (first < last ) {
        auto p = partitionc(first, last, comp);
        quicksort(first, p, comp);
        quicksort(p+1, last, comp);
    }
}



int main () {
    vector<int> v1{1, 3, 5,2, 3,11,4};
    quicksort(v1.begin(), v1.end());

    array<int, 5> a1{1,2,5,67, 4};
    quicksort(a1.begin(), a1.end());

    int b[] { 3, 5, 11, 4};
    quicksort(begin(b), end(b));

    vector<double> v2 { 1.3, 5.0, 3.2};
    quicksort(v2.begin(), v2.end(), greater_equal<>());

}
