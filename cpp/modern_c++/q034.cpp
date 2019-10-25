#include <string>
#include <fstream>

using namespace std;

void remove_empty_lines(string path) {
    fstream infile(path, std::ios::in);
    if(!infile.is_open()) {
        throw runtime_error("cannot open input file: " + path + "\n");
    }
    string outfile_name = path + "_out";
    fstream outfile(outfile_name, std::ios::out | std::ios::trunc);
    if(!outfile.is_open()) {
        throw runtime_error("cannot open output file: " + outfile_name + "\n");
    }

    string line;
    while(getline(infile, line)){
        if (line.length() > 0 && line.find_first_not_of(' ') != line.npos) {
            outfile << line << endl;
        }
    }
    infile.close();
    outfile.close();
}

int main() {
    string test = "test.txt";
    remove_empty_lines(test);

}
