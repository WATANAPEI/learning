#include <sstream>
#include <vector>
#include <iomanip>
#include <array>
#include <iostream>
#include <string_view>
#include <cassert>

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

template <typename C>
std::string bytes_to_hexstr(C const & c, bool const uppercase = false) {
    return bytes_to_hexstr(std::cbegin(c), std::cend(c), uppercase);
}

unsigned char hexchr_to_int(const char chr) {
    if (chr>= '0' && chr <= '9') return chr - '0';
    if (chr >= 'A' && chr <= 'F') return chr - 'A' + 10;
    if (chr >= 'a' && chr <= 'f') return chr - 'a' + 10;
}

vector<unsigned char> hexstr_to_bytes(std::string str) {
    vector<unsigned char> result;
    for(size_t i = 0; i < str.size(); i += 2) {
        result.push_back(
                hexchr_to_int(str[i]) << 4 | hexchr_to_int(str[i+1])
                );
    }
    return result;
}

int main() {
    vector<unsigned char> expected { 0xBA, 0xAD, 0xF0, 0x0D, 0x42 };
    assert(hexstr_to_bytes("BAADF00D42") == expected);
}
