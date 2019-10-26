#include <iostream>
#include <sstream>
#include <iterator>
#include <iostream>
#include <vector>

using namespace std;

template <typename Iter>
string join_strings(Iter begin, Iter end, char const * const separator) {
    ostringstream oss;
    copy(begin, end - 1, ostream_iterator<string>(oss, separator));
    oss << *(end - 1);
    return oss.str();
}

template <typename C>
string join_strings(C const & c, char const * const separator) {
    if (c.size()  == 0) {
        return {};
    }
    return join_strings(cbegin(c), cend(c), separator);

}

int main() {
    vector<string> in{ "this", "is", "a", "test"};
    //vector<string> in = {};
    //string delim = " ";
    string out = join_strings(in, " ");
    cout << out << endl;

}
