#include <vector>
#include <iostream>
using namespace std;


template <typename T, size_t R, size_t C>
class array2d {
    vector<T> arr;
public:
    array2d(): arr(R*C) {};
    explicit array2d(initializer_list<T> l) : arr(l) {};
    T & operator()(size_t r, size_t c) {
        return arr[r*C + c];
    }
    T const & operator()(size_t r, size_t c) const {
        return arr[r*C + c];
    }
    T* data() {
        return arr.data();
    }
    T const * data() const {
        return arr.data();
    }
    T & at(size_t r, size_t c) {
        return arr.at(r*C + c);
    }
    T const & at(size_t r, size_t c) const {
        return arr.at(r*C + c);
    }
    void fill(T const & x) {
        std::fill(arr.begin(), arr.end(), x);
    }
    size_t size_row() const {
        return R;
    }
    size_t size_col() const {
        return C;
    }
    size_t size() const {
        return arr.size();
    }
    void swap(array2d & a) {
        arr.swap(a.arr);
    }

    T * begin() {
        return arr.data();
    }
    T const * cbegin() const {
        return arr.data();
    }
    T * end() {
        return arr.data() + arr.size();
    }
    T const * cend() const {
        return arr.data() + arr.size();
    }

};

int main() {
    array2d<int, 2, 3> a {1, 2, 3,4 , 5, 6};
    for (size_t i = 0; i < a.size_row(); ++i) {
        for (size_t j = 0; j < a.size_col(); ++j) {
            a(i, j) *= 2;
            cout << a(i, j) << endl;
        }
    }

    array2d<int, 2, 3> b;
    b.fill(1);

    a.swap(b);

    array2d<int, 2, 3> c(std::move(b));
}
