#include <sstream>
#include <vector>
#include <iomanip>
#include <array>
#include <iostream>

using namespace std;

template <typename Iter>
std::string bytes_to_hexstr(Iter begin, Iter end, bool const uppercase = false) {
    std::ostringstream oss;
    if (uppercase) oss.setf(std::ios_base::uppercase);
    for (; begin!= end; ++begin) {
        oss << std::hex << std::setw(2) << std::setfill('0') << static_cast<int>(*begin);
    }
    return oss.str();
}

vector<unsigned int> hexstr_to_bytes(std::string str) {
    std::ostringstream oss;
    vector<unsigned int> result;
    for(int i = 0; i < str.length(); ++++i) {
        cout << stoi(str.substr(i, 2), nullptr, 16) << ", ";
        result.push_back(stoi(str.substr(i, 2), nullptr, 16));
    }
    cout << endl;
    return result;
}

template <typename C>
std::string bytes_to_hexstr(C const & c, bool const uppercase = false) {
    return bytes_to_hexstr(std::cbegin(c), std::cend(c), uppercase);
}

int main() {
    string str = "BAADF00D";
    auto v = hexstr_to_bytes(str);
    for(auto e: v) {
        cout << e << ",";
    }
    cout << endl;
}
