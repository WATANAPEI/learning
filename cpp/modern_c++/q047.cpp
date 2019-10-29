#include <deque>
#include <iostream>
#include <mutex>
#include <vector>
#include <iterator>
#include <thread>
#include <numeric>


using namespace std;

template <typename T>
class double_buffer
{
    typedef T value_type;
    typedef T & reference;
    typedef T const & const_reference;
    typedef T* pointer;

    vector<T> rdbuf;
    vector<T> wrbuf;
    mutable mutex mt;

public:
    explicit double_buffer(size_t const size) : rdbuf(size), wrbuf(size) { }
    size_t size() { return rdbuf.size(); }
    void write(T const * const ptr, size_t const size) {
        unique_lock<mutex> lock(mt);
        auto length = min(size, wrbuf.size());
        copy(ptr, ptr + length, begin(wrbuf));
        wrbuf.swap(rdbuf);
    }

    template <class Output>
    void read(Output it) const {
        unique_lock<mutex> lock(mt);
        copy(cbegin(rdbuf), cend(rdbuf), it);
    }
    pointer data()  const
    {
        unique_lock<mutex> lock(mt);
        return rdbuf.data();
    }

    reference operator[](size_t const pos) {
        unique_lock<mutex> lock(mt);
        return rdbuf[pos];
    }

    const_reference operator[](size_t const pos) const {
        unique_lock<mutex> lock(mt);
        return rdbuf[pos];
    }

    void swap(double_buffer other) {
        swap(rdbuf, other.rdbuf);
        swap(wrbuf, other.wrbuf);
    }
};

template <typename T>
void print_buffer(double_buffer<T> const & buf) {
    buf.read(ostream_iterator<T>(cout, " "));
    cout << endl;
}

int main () {
    double_buffer<int> buf(10);
    thread t([&buf]() {
            for(int i = 1; i < 1000; i += 10) {
            int data[10] = {};
            iota(begin(data), end(data), i);
            buf.write(data, std::size(data));

            using namespace chrono_literals;
            this_thread::sleep_for(100ms);
            }
    });

    auto start = chrono::system_clock::now();
    do
    {
        print_buffer(buf);
        using namespace chrono_literals;
        this_thread::sleep_for(150ms);
    } while (chrono::duration_cast<chrono::seconds>(
                chrono::system_clock::now() - start).count() < 12);
    t.join();

}

