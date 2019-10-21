#include <iostream>
#include <functional>

using namespace std;

template <typename T>
T mymin(T const a, T const b) {
    return a > b ? b: a;
}

template <typename T1, typename... T>
T1 mymin(T1 a, T... args) {
    return mymin(a, mymin(args...));

}

template <typename T1, typename... T>
T1 mymin2(T1 a, T... args, function(f)(T1 x, T1 y){
}

int main() {
    auto x = mymin(5, 4, 2, 1);

}
