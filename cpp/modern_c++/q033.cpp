#include <iostream>
#include <vector>
#include <iomanip>
#include <algorithm>
using namespace std;

enum class procstatus {suspended, running};
enum class platforms {p32bit, p64bit};

struct procinfo {
    string name;
    int id;
    procstatus status;
    string account;
    unsigned int size;
    platforms platform;
};

string status_to_string(procstatus const status) {
    if (status == procstatus::suspended) return "suspended";
    else return "running";
}

string platform_to_string(platforms const platform) {
    if (platform == platforms::p32bit) return "p32bit";
    else return "p64bit";
}

void print_processes(vector<procinfo> p) {
    std::sort(p.begin(), p.end(), [](const procinfo & p1, const procinfo & p2) {
            return p1.name > p2.name;
            });
    for(const auto & e : p) {
        cout << std::left << std::setw(24) << setfill(' ') << e.name;
        cout << std::left << std::setw(12) << setfill(' ') << e.id;
        cout << std::left << std::setw(12) << setfill(' ') << status_to_string(e.status);
        cout << std::left << std::setw(12) << setfill(' ') << e.account;
        cout << std::right << std::setw(8) << setfill(' ') << e.size;
        cout << std::left << " " << std::setw(8) << setfill(' ') << platform_to_string(e.platform);
        cout << endl;
    }

}

int main() {

    vector<procinfo> l = {
        {"chrome", 2012, procstatus::running, "me", 290, platforms::p32bit},
        {"firefox", 2019, procstatus::suspended, "you", 3902, platforms::p64bit}
    };
    print_processes(l);

}
