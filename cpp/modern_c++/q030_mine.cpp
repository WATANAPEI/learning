#include <iostream>
#include <vector>
#include <sstream>
#include <iterator>
#include <algorithm>

using namespace std;

template <typename T>
T parse(string s) {
    T result;
    //result.push_back("test");
    auto itr = s.begin();
    stringstream sstr("");
    auto out_itr = ostream_iterator<char>(sstr, "");
    vector<string> v_delim{"://", ":", "/"};

    for(auto e : v_delim) {
        size_t pos = s.find(e);
        copy(itr, itr+pos, out_itr);
        result.push_back(sstr.str());
        advance(itr, pos+e.length());
    }

    return result;
}

int main() {
    string t1 = "http://test-domain.com:3000/dir1/dir2/index.html";
    string t2 = "https://test-domain.com/dir1/index.php?query=desu";
    string t3 = "https://test-domain.com/main.htm#nokotta";
    string t4 = "https://test-domain.com:8080/dir3/main.html?query=sumo&place=kokugikan#nokotta";
    vector<string> token1 = parse<vector<string>>(t1);
    vector<string> token2 = parse<vector<string>>(t2);
    vector<string> token3 = parse<vector<string>>(t3);
    vector<string> token4 = parse<vector<string>>(t4);
    for(auto e : token1) {
        cout << e << endl;
    }
    for(auto e : token2) {
        cout << e << endl;
    }
    for(auto e : token3) {
        cout << e << endl;
    }
    for(auto e : token4) {
        cout << e << endl;
    }


}
