#include <iostream>
#include <regex>

using namespace std;

bool check(string const & test) {
    bool result;
    regex re(R"(\w{3,4}-\w{2}\s\d{3})");
    if(regex_match(test, re)) {
        result = true;
    } else {
        result = false;
    }
    return result;
}

void extract(string const & str) {
    string result;
    regex re(R"(\w{3,4}-\w{2}\s\d{3})");
    smatch m;
    if(regex_search(str, m, re)) {
        for(auto e: m) {
            cout << e << endl;
        }
    }
}

int main() {
    string test = "EEE-SS 213";
    string test2 = "2EW2-RE D32";
    string test3 = "32E" + test + "33Q-";
    extract(test3);
    //bool result = check(test2);
    //cout << "result: " << result << endl;

}
