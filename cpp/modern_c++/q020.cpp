#include <iostream>
#include <vector>

using namespace std;

template <class C, class T>
bool contains(C const & c, T const & value) {
    return cend(c) != find(cbegin(c), cend(c), value);
}

template <class C, class... T>
bool contains_any(C const & c, T &&... value) {
    return (... || contains(c, value));
}

template <class C, class... T>
bool contains_all(C const & c, T &&... value) {
    return (... && contains(c, value));
}

template <class C, class... T>
bool contains_none(C const & c, T &&... value) {
    return !contains_any(c, forward<T>(value)...);
}

int main() {

}
