#include <iostream>
#include <cassert>
#include <sstream>

using namespace std;

string vigenereEncrypt(string const& in, string const& key) {
    string result;
    size_t strLength = key.size();
    for(size_t i = 0; i < in.size(); ++i) {
        result.push_back(static_cast<char>((in.at(i) - 'A' + key.at(i % strLength) - 'A') % 26 + 'A'));
    }
    return result;
}

string vigenereDecrypt(string const& in, string const& key) {
    string result;
    size_t strLength = key.size();
    for(size_t i = 0; i < in.size(); ++i) {
        if(in.at(i) - key.at(i % strLength) < 0) {
            result.push_back(static_cast<char>(((in.at(i) - 'A') - (key.at(i % strLength) - 'A')) + 26 + 'A'));
        } else {
            result.push_back(static_cast<char>(((in.at(i) - 'A') - (key.at(i % strLength) - 'A')) % 26 + 'A'));
        }
    }
    return result;
}
int main() {
    string phrase;
    cout << "input string:";
    cin >> phrase;
    string key("ARM");
    string code = vigenereEncrypt(phrase, key);
    cout << "phrase: " << phrase << endl;
    cout << "code: " << code << endl;
    string decodedPhrase = vigenereDecrypt(code, key);
    cout << "decodedPhrase: " << decodedPhrase << endl;
    assert(vigenereEncrypt("CODE", "ARM") == "CFPE");
    assert(vigenereDecrypt("CFPE", "ARM") == "CODE");

}
