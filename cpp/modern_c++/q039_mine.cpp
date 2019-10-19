#include <vector>
#include <chrono>
#include <iostream>
#include <functional>

using namespace std;

enum class precision { sec, millisec, microsec };

template<typename... Arg>
int exec_time(precision const unit, function<int(int, int, int)> f, const Arg... arg) {
    if(unit != precision::sec && unit != precision::millisec && unit != precision::microsec) {
        cerr << "precision specified is unknown\n";
        return -1;
    }
    auto start = chrono::system_clock::now();
    f(arg...);
    auto end = chrono::system_clock::now();
    if(unit == precision::sec) {
        auto duration = chrono::duration_cast<chrono::seconds>(end - start).count();
        return duration;
    } else if (unit == precision::millisec) {
        auto duration = chrono::duration_cast<chrono::milliseconds>(end - start).count();
        return duration;
    } else if (unit == precision::microsec) {
        auto duration = chrono::duration_cast<chrono::microseconds>(end - start).count();
        return duration;
    } else {
        return -1;
    }

}

int test_func(int a1, int a2, int a3) {
    return a1 + a2 + a3;
}

int main() {
    auto f = test_func;
    int a1 = 1;
    int a2 = 3;
    int a3 = 5;
    int time_executed = exec_time(precision::microsec, f, a1, a2, a3);
    cout << "execution time is " << time_executed << endl;

}
