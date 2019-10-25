#include <fstream>
#include <iostream>

using namespace std;

int del_empline(string path) {
    std::fstream instream(path, instream.binary | instream.in | instream.out);
    string out_file_name = path + "_out";
    std::fstream ostream(out_file_name, ostream.binary | ostream.trunc | ostream.out | ostream.in);
    if(!instream.is_open()) {
        std::cout << "failed to open: " << path << endl;
        return -1;
    } else if(!ostream.is_open()) {
        std::cout << "failed to open: " << out_file_name << endl;
        return -1;
    } else {
        char c;
        bool new_line = true;
        string tmp;
        while(c = instream.get()) {
            if(c == EOF) {
                return 0;
            }
            if(c == '\n') {
                if(new_line) {
                    continue; // doesn't put this empty line
                } else {
                    new_line = true;
                }
            } else {
                if(c == ' ' && new_line) {
                    tmp += c;
                    continue;
                } else {
                    new_line = false;
                    if(!tmp.empty()) {
                        cout << tmp;
                        ostream << tmp;
                        tmp.clear();
                    }
                }
            }
            cout << c;
            ostream << c;
        }
    }
    return 0;

}

int main() {
    string filepath = "./test.txt";
    del_empline(filepath);

}
