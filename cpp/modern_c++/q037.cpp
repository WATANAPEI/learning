//Use this compile command:
//g++ -std=c++1z filename -lstdc++fs
#include <iostream>
#include <string>
#include <regex>
#include <iostream>
#include <experimental/filesystem>

using namespace std;
namespace fs = std::experimental::filesystem;

vector<string> myFind(const fs::path &path, const string &regexString) {

    //fs::path path(pathString);
    regex re;
    try {
        regex reTemp(regexString);
        re = reTemp;
    } catch(regex_error &e) {
        cout << "***************"  << e.what() << "***************"  << endl;
        cout << "input valid regular expression.\n";
    }

    vector<fs::directory_entry> filterdEntries;
    vector<string> result;

    auto dirItr = fs::directory_iterator(path);
    //for(const fs::directory_entry & x: fs::directory_iterator(path)) {
    //    cout << x.path() << endl;
    //}
    copy_if(fs::begin(dirItr), fs::end(dirItr), back_inserter(filterdEntries), [re](auto dirEntry) {
            return regex_search(dirEntry.path().native(), re);
            });
    transform(filterdEntries.begin(), filterdEntries.end(), back_inserter(result), [](auto a) {
            return a.path();
            });

    return result;
}

int main() {
    //string pathString, regexString;
    string regexString;
    fs::path path;
    cout << "input file path: " << endl;
    /*
    cin >> pathString;
    if(pathString == ".") {
        pathString = fs::current_path();
    }
    */
    cin >> path;

    cout << "input file name pattern: " << endl;
    cin >> regexString;

    vector<string> result = myFind(path, regexString);

    for(auto e: result){
        cout << e << endl;
    }


}
