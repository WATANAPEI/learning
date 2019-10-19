#include <iostream>
#include <vector>
#include <iomanip>
using namespace std;

struct line {
    string name;
    int id;
    string status;
    string account;
    unsigned int size;
    string platform;
};

int main() {

    vector<line> l;

    while(1) {
        line tmp;
        if(cin.eof()) {
            break;
        }
        cin >> tmp.name >> tmp.id >> tmp.status >>
            tmp.account >> std::right >> tmp.size >> tmp.platform;
        l.push_back(tmp);
    }

    cout << "****************************" << endl;
    for(line e : l) {
        cout << e.name << "\t" << e.id<< "\t"  << e.status << "\t" <<
            e.account << "\t" << std::right << "\t" << e.size << "\t" << std::left << e.platform << endl;

    }


}
