#include <iostream>
#include <vector>

using namespace std;

class checkStrategy {
public:
    checkStrategy(){};
    virtual bool check(string s) = 0;
};

class checkLength: public checkStrategy {
    size_t len;
public:
    checkLength(int length): len(length) {

    }
    bool check(string s) override {
        cout << "length checking..." << endl;
        return s.length() >= len ? true: false;
    }

};

int main() {
    string pw;
    cin >> pw;
    vector<checkStrategy*> v;
    checkStrategy * cl = new checkLength(8);
    v.push_back(cl);

    for(checkStrategy * e : v) {
        if(!e->check(pw)) {
            cout << "something wrong.." << endl;
            return -1;
        }
    }
    cout << "passed!" << endl;
    return 0;

}
