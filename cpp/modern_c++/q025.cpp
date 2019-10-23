#include <iostream>
#include <vector>
#include <cctype>
#include <cassert>
#include <sstream>

using namespace std;

template <class Elem>
using tstring = basic_string<Elem, char_traits<Elem>, allocator<Elem>>;

template <class Elem>
using tstringstream = basic_stringstream<Elem, char_traits<Elem>, allocator<Elem>>;

template <class Elem>
tstring<Elem> capitalize(tstring<Elem> const & text) {
    tstringstream<Elem> result;
    bool newWord = true;
    for (auto const ch : text) {
        newWord = newWord || isspace(ch) || ispunct(ch);
        if(isalpha(ch)) {
            if(newWord) {
                result << static_cast<Elem>(toupper(ch));
                newWord = false;
            } else {
                result << static_cast<Elem>(tolower(ch));
            }
        } else {
            result << ch;
        }
    }
    return result.str();
}

int main() {
    using namespace string_literals;
    assert("The C++ Challengers"s == capitalize("the c++ challengers"s));
    assert("This Is An Example, Should Work!"s == capitalize("THIS IS an ExamplE, should wORk!"s));
}

