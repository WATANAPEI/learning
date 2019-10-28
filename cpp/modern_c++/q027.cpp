#include <iostream>
#include <vector>
#include <iterator>

using namespace std;

template <class Elem>
using tstring = basic_string<Elem, char_traits<Elem>, allocator<Elem>>;

template <class Elem>
using tstringstream = basic_stringstream<Elem, char_traits<Elem>, allocator<Elem>>;

template <class Elem>
vector<Elem> tokenize(Elem const & in, Elem const & delim) {
    vector<Elem> result;
    vector<int> delim_pos;
    size_t prev_pos = 0;
    for(auto e : delim) {
        string::size_type pos = in.find(e);
        while(pos != string::npos) {
        delim_pos.push_back(pos);
        pos = in.find(e, pos+1);
    }

        while(pos != string::npos) {
            result.push_back(in.substr(prev_pos, pos-1));
            prev_pos = pos;
            pos = in.find(e, pos+1);
        }
    return result;

};

int main() {
    string str = "this is an example.";
    string delim = ",.! ";
    vector<string> result = tokenize(str, delim);
    for(auto e: result) {
        cout << e << " ";
    }
    cout << endl;
    //copy(result.begin(), result.end(), ostream_iterator<string>(cout, " "));


}
