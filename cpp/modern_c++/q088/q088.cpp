#include <iostream>
#include <sstream>
#include <cassert>

using namespace std;

char doCaesarEncrypt(char c, int rotate) {
    char result;
    result = ( c - 'A' + rotate ) % 26 + 'A';
    return result;

}

string caesarCipher(string in) {
    stringstream sstr(in);
    string result;
    char c;
    while(!sstr.eof()) {
        c = sstr.get();
        if(isupper(c)) {
            c = doCaesarEncrypt(c, 5);
        }

        result.push_back(c);
    }
    return result;
}


int main() {

    string in;
    cout << "input string:";
    cin >> in;
    string out = caesarCipher(in);
    cout << "in: " << in << endl;
    cout << "out: " << out << endl;
//    assert(caesarCipher("666 TEST MUST BE unDONE") == "TEST MUST BE DONE");

}
