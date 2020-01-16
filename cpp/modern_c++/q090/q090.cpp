#include <iostream>
#include <map>
#include <iomanip>
#include <bitset>

using namespace std;

static std::map<unsigned int, char> dict({
        make_pair(0, 'A'), make_pair(1, 'B'), make_pair(2, 'C'), make_pair(3, 'D'),
        make_pair(4, 'E'), make_pair(5, 'F'), make_pair(6, 'G'), make_pair(7, 'H'),
        make_pair(8, 'I'), make_pair(9, 'J'), make_pair(10, 'K'), make_pair(11, 'L'),
        make_pair(12, 'M'), make_pair(13, 'N'), make_pair(14, 'O'), make_pair(15, 'P'),
        make_pair(16, 'Q'), make_pair(17, 'R'), make_pair(18, 'S'), make_pair(19, 'T'),
        make_pair(20, 'U'), make_pair(21, 'V'), make_pair(22, 'W'), make_pair(23, 'X'),
        make_pair(24, 'Y'), make_pair(25, 'Z'), make_pair(26, 'a'), make_pair(27, 'b'),
        make_pair(28, 'c'), make_pair(29, 'd'), make_pair(30, 'e'), make_pair(31, 'f'),
        make_pair(32, 'g'), make_pair(33, 'h'), make_pair(34, 'i'), make_pair(35, 'j'),
        make_pair(36, 'k'), make_pair(37, 'l'), make_pair(38, 'm'), make_pair(39, 'n'),
        make_pair(40, 'o'), make_pair(41, 'p'), make_pair(42, 'q'), make_pair(43, 'r'),
        make_pair(44, 's'), make_pair(45, 't'), make_pair(46, 'u'), make_pair(47, 'v'),
        make_pair(48, 'w'), make_pair(49, 'x'), make_pair(50, 'y'), make_pair(51, 'z'),
        make_pair(52, '0'), make_pair(53, '1'), make_pair(54, '2'), make_pair(55, '3'),
        make_pair(56, '4'), make_pair(57, '5'), make_pair(58, '6'), make_pair(59, '7'),
        make_pair(60, '8'), make_pair(61, '9'), make_pair(62, '+'), make_pair(63, '/'),

        });

string base64Encrypt(string const &phrase) {
    string result("");
    for(auto e: phrase) {
        result.append(bitset<8>(static_cast<unsigned int>(e)).to_string());
    }

    cout << result << endl;
    return result;
}

string base64Decrypt(string const &code) {
    string result;
    result = code;
    return result;
}

int main() {
    string phrase("ABCDEFG");
    cout << "phrase: " << phrase << endl;
    string code = base64Encrypt(phrase);
    cout << "code: " << code << endl;
    string decoded = base64Decrypt(code);
    cout << "decoded phrase: " << decoded << endl;

}
