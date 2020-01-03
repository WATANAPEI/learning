#include <iostream>
#include <thread>
#include <mutex>


using namespace std;

class PLog {
public:
    static void write(string msg) {
        cout << "test" << endl;

    }

};

int main() {
    string msg("test message");
    auto thread1 = std::thread([&]() {PLog::write(msg);});
    auto thread2 = std::thread([&]() {PLog::write(msg);});
    thread1.join();
    thread2.join();

}
