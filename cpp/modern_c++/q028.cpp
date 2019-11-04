#include <iostream>
#include <iterator>
#include <vector>
#include <cassert>
#include <string>

using namespace std;

string longest_palindrome(string_view str) {
    size_t const len = str.size();
    size_t longest_begin = 0;
    size_t max_len = 1;
    vector<bool> table(len * len, false);

    for (size_t i = 0; i < len; i++) {
        table[i * len + i] = true;
    }
    for (size_t i = 0; i < len - 1; i++) {
        if (str[i] == str[i+1]) {
            table[i * len + i + 1] = true;
            if (max_len < 2 ) {
                longest_begin = i;
                max_len = 2;
            }
        }
    }
    for (size_t k = 3; k <= len; k++) {
        for (size_t i = 0; i < len - k + 1; i++) {
            size_t j = i + k - 1;
            if (str[i] == str[j] && table[(i + 1) * len + j -1]) {
                table[i * len + j] = true;
                if (max_len < k ) {
                    longest_begin = i;
                    max_len = k;
                }
            }
        }
    }
    return string(str.substr(longest_begin, max_len));
}

int main() {
    using namespace string_literals;
    assert(longest_palindrome("sahararahnide") == "hararah");
    assert(longest_palindrome("level") == "level");
    assert(longest_palindrome("s") == "s");
}

