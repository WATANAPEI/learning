#include <iostream>
#include <vector>

using namespace std;

vector<string> filter_tel(vector<string> const & v, string country_code) {
    vector<string> result;
    int len = country_code.length();
    for(auto e : v) {
        if(e.substr(0, len) == country_code || e.substr(0, len+1) == "+" + country_code) {
            result.push_back(e);
        }
    }

    return result;

}

int main() {
    vector<string> tel {
        "81-3-4324-3234",
        "0312345678",
        "03-2982-3290",
        "+81-3-2354-3156",
        "+265-99-2349-4325"
    };
    vector<string> result = filter_tel(tel, "81");
    for(auto e: result) {
        cout << e << endl;
    }

}
