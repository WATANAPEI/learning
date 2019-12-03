#include <iostream>
#include <vector>
#include <functional>

using namespace std;

struct book {
    int id;
    string title;
    string author;
};

template<typename T, typename E>
vector<E> select(vector<T> const & itr, std::function<E(const T)> f) {
    vector<E> result;
    //transform(itr.begin(), itr.end(), result.begin(), f);
    for(auto e: itr) {
        result.push_back(f(e));
    }
    return result;
}


int main() {
    vector<book> books {
        {101, "The C++ Programming Language", "Stroustrup"},
            {203, "Effective Modern C++", "Scott Mayers"},
            {404, "The modern c++ Programming cookbook", "Marius Bancia"}
    };
    auto titles = select(books, [](book const &b) { return b.title; });

}
