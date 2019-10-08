#include <iostream>
using namespace std;

int main() {
    string s;
    cin >> s;

    int count = 0;
    if ( s[0] == '1' ) ++count;
    if ( s[1] == '1' ) ++count;
    if ( s[2] == '1' ) ++count;
    cout << count << endl;

}
