#include <iostream>
#include <vector>
#include <string>

using namespace std;

unsigned char* to_hex(vector<unsigned char> v, int to_uppercase = 0) {
    unsigned char * str[1];
    for(unsigned int i = 0; i < v.size(); i++) {
        str[i] = v.at(i);
    }
    str[v.size()] = '\0';
    return str;

}

int main() {
    vector<unsigned char> v {0xBA, 0xAD, 0xF0, 0x0d };
    unsigned char * str = to_hex(v);
    std::cout << str << std::endl;

}
