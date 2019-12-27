#include <iostream>
#include <experimental/filesystem>

using namespace std;
namespace fs = std::experimental::filesystem;

class MyLog {
public:
    MyLog()
     : ostr(std::cout){}
    explicit MyLog(ostream &ostr)
        : ostr(ostr) {}


private:
    ostream &ostr;
};

int main() {
    string str("test log");
    {
        try{
            MyLog log;
        } catch (MyLogError e) {
            cout << e.what() << endl;
        };
        log.append(str);
        log.append("aaaa");
    }
    {
        MyLog log2;
        Path path("./log.txt");
        try{
            log2.create(path);
        } catch (MyLogError e) {
            cout << e.what();
        }
        log2.append("sfagdeag\n");
        log2.append("ihiuuuu");
        log2.save();
    }

}
