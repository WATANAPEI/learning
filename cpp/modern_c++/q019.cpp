#include <iostream>
#include <vector>
#include <iterator>
#include <iostream>
#include <list>

using namespace std;

template <typename C, typename... Args>
void push_back(C & c, Args&&... args) {
    (c.push_back(args), ...);
}

int main() {
    vector<int> v;
    push_back(v, 1, 2, 3, 4);
    copy(cbegin(v), cend(v), ostream_iterator<int>(cout, " "));

    list<int> l;
    push_back(l, 5, 6, 7, 8);
    copy(cbegin(l), cend(l), ostream_iterator<int>(cout, " "));
}
