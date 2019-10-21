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

template <class Compare, typename T>
T minimum(Compare compare, T const a, T const b) {
    return compare(a, b)? a : b;

}

template <class Compare, typename T1, typename... Args>
T1 mininum(Compare compare, T1 t, Args... args) {
    return minimum(compare, t, minimum(compare, args...));
}
int main() {
    auto x = mymin(5, 4, 2, 1);
    cout << x << endl;

}
