#include <vector>
#include <iostream>
#include <regex>

using namespace std;

string transformDate(string const &s)
{
    string result;
    string pattern(R"((\d{1,2})[.-](\d{1,2})[.-](\d{4}))");
    regex re(pattern);
    smatch m;
    if(regex_match(s, m, re)) {
        for(size_t i = 0; i < m.size(); i++) {
            if(stoi(m.str(3)) < 0) {
                result += "invalid year. year must be positive integer\n";
            }
            if(stoi(m.str(2)) < 0 || stoi(m.str(2)) > 12) {
                result += "invalid month. month must be between 1 and 12\n";
            }
            if(stoi(m.str(1)) < 0 || stoi(m.str(1)) > 31){
                result += "invalid day. day must be between 1 and 31\n";
            }
            if(result != "") {
                return result;
            }

            result = m.str(3) + "-" + m.str(2) + "-" + m.str(1) + "\n";
        }
    } else {
        //cout << "not found" << endl;
        result = "not found\n";
    }
    //result = "2019-12-21";
    return result;
}

int main() {
    string test("31.12.2019");
    string test2("11-11-2011");
    string test3("41-12-2011");
    string result = transformDate(test);
    string result2 = transformDate(test2);
    string result3 = transformDate(test3);
    cout << "result: " << result << endl;
    cout << "result2: " << result2 << endl;
    cout << "result3: " << result3 << endl;

}
