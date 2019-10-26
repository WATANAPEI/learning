#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

string concat(vector<string> in, char delim) {
    stringstream ss;
    for(auto itr = in.begin(); itr != in.end(); itr++) {
        ss << *itr;
        if(itr != in.end() -1) {
            ss << delim;
        }
    }
    return ss.str();
}

int main() {
    vector<string> in = { "this", "is", "a", "test"};
    //vector<string> in = {};
    char delim = ' ';
    string out = concat(in, delim);
    cout << out << endl;

}
