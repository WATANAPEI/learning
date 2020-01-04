#include <iostream>
#include <random>
#include <mutex>
#include <thread>
#include <vector>

using namespace std;

class logger
{
protected:
    logger() = default;
public:
    static logger &instance() {
        static logger lg;
        return lg;
    }

    logger(logger const &) = delete;
    logger& operator=(logger const &) = delete;

    void log(string_view const &message) {
        unique_lock<mutex> lock(mt);
        cout << "LOG: " << message << endl;
    }
private:
    mutex mt;
};

int main() {

    vector<thread> modules;

    for(int id = 1; id < 5; id++) {
        modules.emplace_back([id] (){
                random_device rd;
                mt19937 mt(rd());
                uniform_int_distribution<> ud(100, 1000);

                logger::instance().log("module" + to_string(id) + " started");
                this_thread::sleep_for(chrono::milliseconds(ud(mt)));
                logger::instance().log("module" + to_string(id) + " finished");
        });
    }
    for(auto &m: modules){
        m.join();
    }
}

