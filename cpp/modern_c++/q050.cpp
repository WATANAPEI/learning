#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

bool starts_with(string_view str, string_view prefix) {
    return str.find(prefix) == 0;
}

template<typename InputIt>
vector<string> filter_numbers(InputIt begin, InputIt end, string const & countryCode) {
    vector<string> result;
    copy_if(
            begin, end,
            back_inserter(result),
            [countryCode] (auto const & number) {
                return starts_with(number, countryCode) ||
                        starts_with(number, "+" + countryCode);
                        });
    return result;
}

vector<string> filter_numbers(vector<string> const & numbers,
                                string const & countryCode) {
    return filter_numbers(cbegin(numbers), cend(numbers), countryCode);
}

int main() {
    vector<string> tel {
        "81-3-4324-3234",
        "0312345678",
        "03-2982-3290",
        "+81-3-2354-3156",
        "+265-99-2349-4325"
    };

    auto result = filter_numbers(tel, "81");

    for(auto const & number : result) {
        cout << number << endl;
    }

}
