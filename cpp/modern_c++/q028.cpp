#include <iostream>
#include <iterator>

using namespace std;

template <class Elem>
using tstring = basic_string<Elem, char_traits<Elem>, allocator<Elem>>;

template <class Elem>
using tstringstream = basic_stringstream<Elem, char_traits<Elem>, allocator<Elem>>;

template <class Elem>
tstring<Elem> palindrome(tstring<Elem> const & str) {
    tstring<Elem> rev;

    for(auto itr = str.begin(); itr != str.end(); itr++) {
        rev.push_back(*itr);
        cout << *itr << endl;
    }

    for(int i = 0; i < str.size()-1; ++i) {
        for(int j = 2; j <= str.size()-i; ++j) {
            cout << str.substr(i, j) << endl;

        }
    }


}

int main() {
    tstring<char> str = "tests";
    tstring<char> result = palindrome(str);

}
