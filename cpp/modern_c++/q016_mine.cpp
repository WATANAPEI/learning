#include <iostream>
#include <sstream>
#include <array>
#include <vector>

using namespace std;

class ipv4 {
    std::array<uint8_t, 4> data;
public:
    constexpr ipv4() : ipv4(0, 0, 0, 0) {}
    constexpr ipv4(uint8_t const a, uint8_t const b, uint8_t const c, uint8_t const d):
        data({a, b, c, d}) {}
    explicit constexpr ipv4(unsigned long a)
        : ipv4(static_cast<uint8_t>((a >> 24) & 0xFF),
                static_cast<uint8_t>((a >> 16) & 0xFF),
                static_cast<uint8_t>(( a >> 8) & 0xFF),
                static_cast<uint8_t>(a & 0xFF)) {}
    ipv4(ipv4 const & other) noexcept : data(other.data) {}
    ipv4 & operator=(ipv4 const & other) noexcept
    {
        data = other.data;
        return *this;
    }

    string to_string() const {
        std::stringstream sstr;
        sstr << *this;
        return sstr.str();
    }

    constexpr unsigned long to_ulong() const noexcept
    {
        return
            (static_cast<unsigned long>(data[0]) << 24) |
            (static_cast<unsigned long>(data[1]) << 16) |
            (static_cast<unsigned long>(data[2]) << 8) |
            static_cast<unsigned long>(data[3]);
    }

    friend std::ostream& operator<<(std::ostream& os, const ipv4& a)
    {
        os << static_cast<int>(a.data[0]) << '.'
            << static_cast<int>(a.data[1]) << '.'
            << static_cast<int>(a.data[2]) << '.'
            << static_cast<int>(a.data[3]);
        return os;
    }

    friend std::istream& operator>>(std::istream &is, ipv4& a)
    {
        char d1, d2, d3;
        int b1, b2, b3, b4;

        is >> b1 >> d1 >> b2 >> d2 >> b3 >> d3 >> b4;
        if(d1 == '.' && d2 == '.' && d3 == '.')
            a = ipv4(b1, b2, b3, b4);
        else
            is.setstate(std::ios_base::failbit);
        return is;
    }

    friend vector<ipv4> operator-(ipv4& a, ipv4 &b) {
        unsigned long upper = max(a.to_ulong(), b.to_ulong());
        unsigned long lower = min(a.to_ulong(), b.to_ulong());

        vector<ipv4> list;
        for(unsigned long i = lower + 1; i < upper; i++) {
            list.push_back(ipv4(i));
        }
        return list;
    }
};

int main() {
    ipv4 ip1, ip2;
    std::cout << "input ip address 1" << endl;
    std::cin >> ip1;
    std::cout << "input ip address 2" << endl;
    std::cin >> ip2;

    vector<ipv4> iplist = ip1 - ip2;
    std::cout << "ip address in the range above:" << std::endl;
    for(ipv4 e : iplist) {
        std::cout << e.to_string() << std::endl;
    }
}
