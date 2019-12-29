//compile command
// g++ -std=c++1z -c q043.cpp /usr/local/src/date/src/tz.cpp -I/usr/local/src/date/include/date/ -I/usr/local/src/curl/include
// g++ -o q043 q043.o tz.o -L/usr/lib/x86_64-linux-gnu -lcurl
// also, check ldconfig whether it's latest version or not
#include <iostream>
#include <chrono>
#include "date.h"
#include "tz.h"

using namespace std;

int main() {

    auto t = date::make_zoned(date::current_zone(), chrono::system_clock::now());
    cout << t << "\n";
}
