#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>
#include <numeric>


using namespace std;

void char_permutation(string const & str) {
    int size = str.length();
    stringstream sstr;
    vector<int> v(size);
    iota(v.begin(), v.end(), 0);
    string notice = "*****original string:" + str + "*****";
    cout << notice << endl;
    do {
        sstr.str("");
        for(auto e : v) {
            sstr << str.at(e);
        }
        cout << sstr.str() << endl;
    } while(next_permutation(v.begin(), v.end()));
    notice.replace(5, 16 + str.size(), string(17 + str.size(), '*'));
    cout << notice << endl;

}


int main() {
    string test1("asd");
    string test2("bba");
    string test3("1253");
    char_permutation(test1);
    char_permutation(test2);
    char_permutation(test3);



}
