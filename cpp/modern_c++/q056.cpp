#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>

using namespace std;

struct book {
    int id;
    string title;
    string author;
};

template <typename T, typename A, typename F,
         typename R = typename decay<typename result_of<
         typename decay<F>::type&(
                 typename vector<T, A>::const_reference)>::type>::type>
vector<R> select(vector<T, A> const & c, F&& f){
    vector<R> v;
    transform(cbegin(c), cend(c),
            back_inserter(v),
            forward<F>(f));
    return v;
}


int main() {
    vector<book> books {
        {101, "The C++ Programming Language", "Stroustrup"},
            {203, "Effective Modern C++", "Scott Mayers"},
            {404, "The modern c++ Programming cookbook", "Marius Bancia"}
    };
    auto titles = select(books, [](book const &b) { return b.title; });
    for(auto const & title: titles) {
        cout << title << endl;
    }

}
