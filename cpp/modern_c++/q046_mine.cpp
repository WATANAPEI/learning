#include <iostream>
#include <deque>
#include <cassert>

using namespace std;

template <typename T>
class ring_buf {
private:
    using size_type = typename deque<T>::size_type;
    using value_type = typename deque<T>::value_type;
    using reference = typename deque<T>::reference;
    using const_reference = typename deque<T>::const_reference;
    using iterator = typename deque<T>::iterator;
    using const_iterator = typename deque<T>::const_iterator;
    deque<T> data;
    size_type cp;

public:

    ring_buf() = delete;
    ring_buf(size_type n): cp(n) {
        data = deque<T>();
    }
    bool empty() const {
        return data.empty();
    };
    bool full() const {
        return cp == data.size();
    };
    size_type const size() const {
        return cp;
    }
    size_type const capacity() const {
        return cp - data.size();
    }
    void push(value_type e) {
        if(!full()) {
            data.push_back(e);
            //cout << "pushed" << endl;
        } else {
            data.pop_front();
            data.push_back(e);
            //cout << "didn't push" << endl;
        }
    }
    void pop_front() {
        data.pop_front();
    }
    iterator begin() {
        return data.begin();
    }
    iterator end() {
        return data.end();
    }
    const_iterator cbegin() {
        return data.cbegin();
    }
    const_iterator cend() {
        return data.cend();
    }
};

int main() {
    ring_buf<int> a(10);
    a.push(32);
    a.push(5);
    assert(*a.begin() == 32);
    assert(*++a.begin() == 5);
    a.push(4);
    a.pop_front();
    assert(*a.begin() == 5);
    assert(*++a.begin() == 4);

}
