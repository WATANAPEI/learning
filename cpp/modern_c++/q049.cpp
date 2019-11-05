#include <iostream>
#include <regex>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <numeric>
#include <string>

using namespace std;

map<char, double> analyze_text(string_view text) {
    map<char, double> freq;
    for (char ch = 'a'; ch <= 'z'; ch++) {
        freq[ch] = 0;
    }

    for(auto ch: text) {
        if (isalpha(ch)) {
            freq[tolower(ch)]++;
        }
    }

    auto total = accumulate(
            cbegin(freq), cend(freq),
            0ULL,
            [](auto const sum, auto const & kvp) {
                return sum + static_cast<unsigned long long>(kvp.second);
                });

    for_each(
            begin(freq), end(freq),
            [total](auto & kvp) {
                kvp.second = (100.0 * kvp.second) / static_cast<double>(total);
                });

    return freq;

}

int main() {
    auto result = analyze_text(R"(Lorem ipsum dolor sit amet, consectetur adipiscing elit,)"
            R"(sed do eiusmod tempor incididut ut labore et dolare magna aliqua. )");

    for (auto const & [ch, rate]: result) {
        std::cout << ch << " : "
            << fixed
            << setw(5) << setfill(' ')
            << setprecision(2) << rate << endl;
    }
}

