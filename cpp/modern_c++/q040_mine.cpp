#include <chrono>
#include <iostream>
#include <ctime>
#include <iomanip>

using namespace std;
using namespace chrono;

template <typename T>
T diff_time(time_point<system_clock> a, time_point<system_clock> b) {
    return duration_cast<T>(a - b);
}

int main() {
    time_point<system_clock> now = system_clock::now();
    time_point<system_clock> p1 = now + seconds(10);

    time_t t = system_clock::to_time_t(now);
    const tm* lt = localtime(&t);

    seconds diff = diff_time<seconds>(p1, now);
    cout << "now: " << put_time(lt, "%c") << endl;
    cout << "duration: " << diff.count() << endl;

}
