#include <iostream>
#include <thread>


using namespace std;

int main() {
    auto thread1 = std::thread([](){ cout << "test" << endl;});
    thread1.join();

}
