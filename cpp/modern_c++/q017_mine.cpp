#include <iostream>
using namespace std;

template <typename T, size_t N = 100>
class Container {
private:
    T storage[N];
    size_t n;

public:
    Container(size_t n, T init = 0) {
        Container::n = n;
        for(int i = 0; i < n; i++) {
            storage[i] = init;
        }
    }


    T at(unsigned int i) {
        return storage[i];
    }

    T * data() {
        return storage;
    }

    unsigned int size() {
        return n;
    }

    T* iterator() {
        return storage;
    }

    T* begin() {
        return storage;
    }

    T* end() {
        return &storage[n];
    }

    void swap(unsigned int a, unsigned int b) {
        T temp;
        temp = storage[a];
        storage[b] = storage[a];
        storage[a] = temp;
    }

};

int main() {
    Container<int, 20> m(10,5);
    cout << "m.at(0): " << m.at(0) << endl;
    cout << "m.size(): " << m.size() << endl;


}
