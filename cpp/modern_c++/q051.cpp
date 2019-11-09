#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

bool starts_with(string_view str, string_view c) {
    return str.find(c) == 0;
}

void normalize_numbers(vector<string> & numbers, string const & country_code) {
    transform(
            cbegin(numbers), cend(numbers),
            begin(numbers),
            [country_code] (string const & number) {
                string result;
                if(number.size() > 0) {
                    if(number[0] == '0') {
                        result = "+" + country_code + number.substr(1);
                    } else if (starts_with(number, country_code)) {
                        result = "+" + number;
                    } else if (starts_with(number, "+" + country_code)) {
                        result = number;
                    } else {
                        result = "+" + country_code + number;
                    }
                }
                result.erase(
                        remove_if(begin(result), end(result),
                            [](const char ch) {
                                return isspace(ch);
                            }),
                        end(result));
                return result;
            });
}

int main() {
    vector<string> list {
        "07555 123456",
        "07555123456",
        "+44 7555 123456",
        "44 7555 123456",
        "7555 123456"
    };
    string country_code = "44";
    normalize_numbers(list, country_code);
    for(auto e: list) {
        cout << e << endl;
    }

}
