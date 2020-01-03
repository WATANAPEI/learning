#include <iostream>
#include <thread>
#include <mutex>
#include <sstream>

using namespace std;

class PLog {
    mutex mtx;

public:
    unique_lock<mutex> get_lock()
    {
        return unique_lock<mutex>(mtx);
    }

    void log(string msg) {
        unique_lock<mutex> lk = get_lock();
        cout << msg << endl;
    }
};

int main() {
    PLog p;
    stringstream sstr1;
    stringstream sstr2;
    auto thread1 = std::thread([&]() {
            for(int i = 0;i < 10; i++) {
                sstr1 << "thread1 i: " << i << endl;
                p.log(sstr1.str());
            }});
    auto thread2 = std::thread([&]() {
            for(int i = 0;i < 10; i++) {
                sstr2 << "thread2 i: " << i << endl;
                p.log(sstr2.str());
            }});
    thread1.join();
    thread2.join();

}
