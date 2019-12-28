//use compile command below
// g++ -std=c++1z filename -lstdc++fs

#include <iostream>
#include <memory>
#include <fstream>
#include <experimental/filesystem>
#include <date/date.h>
#include <chrono>


using namespace std;
using namespace date;
namespace fs = experimental::filesystem;

class MyLog {
private:
    date::sys_seconds now;
    string filename;
    fs::path tmpPath;
    ofstream output;
public:
    MyLog()
    : now(date::floor<chrono::seconds>(chrono::system_clock::now())),
        filename(string(date::format("%Y%m%d%H%M%S", now).append(".log"))),
                tmpPath(filename), output(tmpPath) {}
    explicit MyLog(const string &str)
    : now(date::floor<chrono::seconds>(chrono::system_clock::now())),
        filename(string(str).append(".log")),
                tmpPath(filename), output(tmpPath) {}
    void append(const string &str) {
        output << str;
    }
    void save(string const &str) {
        fs::path persistPath(str);
        fs::path persistDirectory = persistPath.parent_path();
        fs::create_directories(persistDirectory);
        output.flush();
        fs::copy_file(tmpPath, persistPath, fs::copy_options::overwrite_existing);
    }
    ~MyLog() {
        output.flush();
        fs::remove(filename);
    }

};


int main() {
    {
        string str("test log");
        MyLog log;
        log.append(str);
        log.append("aaaa");
        log.save("tmp/test1.log");
        log.append("bbb");
    }
    MyLog log2("test2.txt");
    log2.append("sfagdeag\n");
    log2.append("ihiuuuu");
    //log2.save("tmp/test2.log");

}
