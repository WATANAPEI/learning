#include <iostream>
#include <string>

using namespace std;

string make_roman(unsigned int num) {
    //return u8"I \U00002160\U00002160\U00002161\n";
    unsigned int tmp = num;
    unsigned int num_1k = tmp / 1000;
    tmp -= num_1k * 1000;
    unsigned int num_500 = tmp / 500;
    tmp -= num_500 * 500;
    unsigned int num_100 = tmp / 100;
    tmp -= num_100 * 100;
    unsigned int num_50 = tmp / 50;
    tmp -= num_50 * 50;
    unsigned int num_10 = tmp / 10;
    tmp -= num_10 * 10;
    unsigned int num_1 = tmp;
    string result;
    for(int i=0; i<num_1k;i++) {
        result.append("M");
    }
    for(int i=0; i<num_500;i++) {
        result.append("D");
    }
    for(int i=0; i<num_100;i++) {
        result.append("C");
    }
    for(int i=0; i<num_50;i++) {
        result.append("L");
    }
    for(int i=0; i<num_10;i++) {
        result.append("X");
    }
    if(num_1 == 9) result.append("IX");
    else if (num_1 == 8) result.append("VIII");
    else if (num_1 == 7) result.append("VII");
    else if (num_1 == 6) result.append("VI");
    else if (num_1 == 5) result.append("V");
    else if (num_1 == 4) result.append("IV");
    else if (num_1 == 3) result.append("III");
    else if (num_1 == 2) result.append("II");
    else if (num_1 == 1) result.append("I");
    return result;

}

int main() {
    unsigned int n;
    cin >> n;

    if (n < 0) {
        cout << "input must be positive integer\n";
        return 0;
    } else {
        cout << "input :" << n << endl;
    }

    string result = make_roman(n);

    cout << "result: " << result << endl;

}
