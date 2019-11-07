//Use this compile command:
//g++ -std=c++1z filename -lstdc++fs

#include <iostream>
#include <experimental/filesystem>
#include <iomanip>

using namespace std;
namespace fs = std::experimental::filesystem;

int main () {
    for(const fs::directory_entry &i : fs::recursive_directory_iterator(".")) {
        cout << setw(15) << left << i.path().filename().string();
        if(fs::is_regular_file(i)) {
            cout  << ": " << fs::file_size(i.path()) << " byte" << endl;
        } else {
            cout << ": is directory" << endl;
        }
    }



}
