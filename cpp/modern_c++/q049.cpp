#include <iostream>
#include <regex>
#include <vector>
#include <algorithm>
#include <iomanip>

using namespace std;

void text_hist(string const & str) {
    vector<string> result;
    string tmp = str;
    transform(cbegin(tmp), cend(tmp), begin(tmp), ::tolower);
    auto itr = tmp.cbegin();
    auto end = tmp.cend();

    regex re(R"([a-zA-Z])");
    smatch m;
    map<string, size_t> hist;
    while(regex_search(itr, end, m, re)) {
        result.push_back(m.str());
        hist[m.str()]++;
        //cout << m.str() << " " << hist[m.str()] << endl;
        itr = m[0].second;
    }
    size_t strlen = result.size();
    for(auto e : hist) {
        cout << e.first << " " << setprecision(3) << (double(e.second)/double(strlen))*100 << "%" << endl;
    }



};

int main() {
    string text = "aa90bs32+-f2FF";
    text_hist(text);

}
