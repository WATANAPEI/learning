#include <iostream>
#include <vector>
#include <cassert>
#include <algorithm>

using namespace std;

template <class T, class Compare = std::less<typename vector<T>::value_type>>
class priority_queue {
    using value_type = typename vector<T>::value_type;
    using size_type = typename vector<T>::size_type;
    using reference = typename vector<T>::reference;
    using const_reference = typename vector<T>::const_reference;

    vector<T> data;
    Compare comparer;

public:

    void push(value_type const & value) {
        data.push_back(value);
        push_heap(begin(data), end(data), comparer);
    }
    void pop() {
        pop_heap(begin(data), end(data), comparer);
        data.pop_back();

    }
    const_reference top() const {
        return data.front();
    }
    void swap(priority_queue & other) noexcept {
        swap(data, other.data);
        swap(comparer, other.comparer);
    }
    size_t size() {
        return data.size();
    }
    bool empty() {
        return data.empty();
    }
};

template <class T, class Compare>
void swap(priority_queue<T, Compare>& lhs,
          priority_queue<T, Compare>& rhs)
          noexcept(noexcept(lhs.swap(rhs)))
{
    lhs.swap(rhs);
}

int main () {
    priority_queue<int> q;
    for (int i : {1, 5, 3, 1, 13, 21, 9}) {
        q.push(i);
    }

    assert(!q.empty());
    assert(q.size() == 7);

    while(!q.empty()) {
        cout << q.top() << endl;
        q.pop();
    }

}
