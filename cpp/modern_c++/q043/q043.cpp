//compile command
//g++ -std=c++1z filename -l /usr/local/include
#include <iostream>
#include <chrono>
#include "date.h"
#include "tz.h"

using namespace std;

int main() {

    auto t = date::make_zoned(date::current_zone(), chrono::system_clock::now());
    //cout << t << "\n";
}
