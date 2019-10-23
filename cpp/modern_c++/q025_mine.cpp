#include <iostream>
#include <vector>
#include <cctype>

using namespace std;

//TODO: deal with successive space
vector<string> tokenize(string str) {
    vector<string> token;
    string tmp;
    for(auto itr = str.begin(); itr != str.end(); itr++) {
        if(*itr == ' ') {
            token.push_back(tmp);
            tmp.clear();
        } else {
            tmp += *itr;
        }
    }
    token.push_back(tmp);
    return token;

}

string to_chapital(string str) {
    char c = str.at(0);
    const char tmp = static_cast<const char>(toupper(c));
    return string(&tmp) + str.erase(0, 1);
}

int main() {
    string test = "this is test sentense.";
    vector<string> tokens = tokenize(test);
    string result;
    for(auto e: tokens) {
        result += to_chapital(e);
        result += " ";
    }
    cout << result << endl;
}
