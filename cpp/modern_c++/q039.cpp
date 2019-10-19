#include <vector>
#include <chrono>
#include <iostream>
#include <functional>
#include <thread>

using namespace std;

template<typename Time = chrono::microseconds, typename Clock = chrono::high_resolution_clock>
struct perf_timer {

    template<typename F, typename... Args>
    static Time duration(F && f, Args... args) {
        auto start = Clock::now();
        std::invoke(std::forward<F>(f), std::forward<Args>(args)...);
        auto end = Clock::now();

        return chrono::duration_cast<Time>(end - start);

    }
};

void f()
{
    std::this_thread::sleep_for(2s);
}

void g(int const /*a*/, int const /*b*/) {
    std::this_thread::sleep_for(1s);
}

int main() {
    auto t1 = perf_timer<chrono::microseconds>::duration(f);
    auto t2 = perf_timer<chrono::milliseconds>::duration(g, 1, 2);

    cout << "total: " << chrono::duration<double, std::nano>(t1 + t2).count() << endl;


}

