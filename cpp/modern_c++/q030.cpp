#include <iostream>
#include <vector>
#include <sstream>
#include <iterator>
#include <algorithm>
#include <regex>
#include <optional>

using namespace std;

struct uri_parts {
    string protocol;
    string domain;
    optional<int> port;
    optional<string> path;
    optional<string> query;
    optional<string> frag;
};

optional<uri_parts> parse_uri(string uri) {
    regex rx(R"(^(\w+):\/\/([\w.-]+)(:(\d+))?)"
            R"(([\w\/\.]+)?(\?([\w=&]*)(#?(\w+))?)?$)");
    auto matches = smatch();
    if (regex_match(uri, matches, rx)) {
        if(matches[1].matched && matches[2].matched) {
            uri_parts parts;
            parts.protocol = matches[1].str();
            parts.domain = matches[2].str();
            if(matches[4].matched) {
                parts.port = stoi(matches[4]);
            }
            if(matches[5].matched) {
                parts.path = matches[5];
            }
            if(matches[7].matched) {
                parts.query = matches[7];
            }
            if(matches[9].matched) {
                parts.frag = matches[9];
            }
            return parts;
        }
    }
    return {};
}

int main() {
    string t1 = "http://test-domain.com:3000/dir1/dir2/index.html";
    string t2 = "https://test-domain.com/dir1/index.php?query=desu";
    string t3 = "https://test-domain.com/main.htm#nokotta";
    string t4 = "https://test-domain.com:8080/dir3/main.html?query=sumo&place=kokugikan#nokotta";
    auto p1 = parse_uri(t1);
    cout << boolalpha << p1.has_value() << endl;
    cout << p1->protocol << endl;
    cout << p1->domain << endl;
    if(p1->port.has_value())
        cout << p1->port.value() << endl;
    if(p1->path.has_value())
        cout << p1->path.value() << endl;
    if(p1->query.has_value())
        cout << p1->query.value() << endl;
    if(p1->frag.has_value())
        cout << p1->frag.value() << endl;

}
