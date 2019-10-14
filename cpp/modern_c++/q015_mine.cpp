#include <iostream>
#include <vector>

using namespace std;

class ipv4 {
private:
    vector<int> addr;
public:
    void setIp(vector<int> ip) {
            addr = ip;
        }
    string to_string() {
        string s;
        for(size_t i = 0; i < addr.size(); ++i) {
            s.append(std::to_string(addr.at(i)));
            if ( i != addr.size() -1) {
                s.append(".");
            }
        }
        return s;
    }
    vector<int> to_vector(string str) {

        string sub;
        vector<int> ip_v(4);

        for(auto e : str) {
            if ( e != '.') {
                sub.push_back(e);
            } else {
                ip_v.push_back(stoi(sub));
                sub.clear();
            }
        }
        ip_v.push_back(stoi(sub));
        return ip_v;
    }

    ipv4 operator >>(std::istream str) {
        setIp(to_vector(str));
    }

};


int main() {
    string input;
    cout << "input ipv4 address" << endl;
    ipv4 myip;
    cin >> myip;

    myip.setIp(ip_v);
    cout << "ip: " << endl;
    cout << myip.to_string() << endl;
}
