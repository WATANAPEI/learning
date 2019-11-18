#include <iostream>
#include <array>

using namespace std;

int main() {
    string test = "410109205X";
    array<int, 9> num;
    //cout << test.length() << endl;
    for(size_t i = 0; i < test.length() - 1; i++) {
        if(test.at(i) == 'X') {
            num.at(i) = 10;
        } else {
         num.at(i) = test.at(i) - '0';
        }
    }
    int sum = 0;
    for(size_t i = 0; i < 9; i++) {
        sum += num.at(i) * (10 - i);
        cout << sum << endl;
    }
    int result = 11 - sum % 11;
    cout << result << endl;
    if( *num.end() == result) {
        cout << "This number is ISBN\n";
    }else {
        cout << "This number is not ISBN\n";
    }
    /*
    for(auto e : num) {
        cout << e << endl;
    }
    */

}
