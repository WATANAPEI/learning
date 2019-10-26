#include <iostream>

using namespace std;

template <typename C, typename T>
void multi_push(C container, T t) {
    container.push_back(t);
}

template <typename C, typename Head, typename... Tails>
void multi_push(C container, Head arg, Tails... args) {
    multi_push(container, arg, args...);
}

int main() {

}
