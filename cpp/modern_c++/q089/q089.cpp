#include <iostream>
#include <cassert>
#include <sstream>

using namespace std;

string vigenereCipher(string const& in, string const& key) {
    string result;
    size_t strLength = key.size();
    for(size_t i = 0; i < in.size(); ++i) {
        result.push_back(static_cast<char>((in.at(i) - 'A' + key.at(i % strLength) - 'A') % 26 + 'A'));
    }
    return result;
}

int main() {
    string in;
    cout << "input string:";
    cin >> in;
    string key("ARM");
    string out = vigenereCipher(in, key);
    cout << "in: " << in << endl;
    cout << "out: " << out << endl;
    assert(vigenereCipher("CODE", "ARM") == "CFPE");
}
