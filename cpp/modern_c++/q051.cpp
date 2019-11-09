#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

vector<string> add_country_number(vector<string> const & list, string country_code) {
    vector<string> result;
    stringstream ss;
    for(auto itr = list.begin(); itr != list.end(); itr++) {
        ss.str("");
        string tmp = *itr;
        for(auto c = tmp.begin(); c != tmp.end(); c++) {
            if(c == tmp.begin() && *c != '+') {
                ss << '+';
            }
            if(c == tmp.begin() && *c == '0') {
                ss << country_code;
                continue;
            }
            if(!isspace(*c)) {
                ss << *c;
            }
        }
        result.push_back(ss.str());
    }
    return result;

}

int main() {
    vector<string> list {
        "07555 123456",
        "07555123456",
        "+44 7555 123456",
        "44 7555 123456",
        "7555 123456"
    };
    string country_code = "44";
    vector<string> result = add_country_number(list, country_code);
    for(auto e: result) {
        cout << e << endl;
    }

}
