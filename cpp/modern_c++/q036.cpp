//Use this compile command:
//g++ -std=c++1z q036.cpp -lstdc++fs

#include <iostream>
#include <experimental/filesystem>

using namespace std;
namespace fs = std::experimental::filesystem;
namespace ch = chrono;

template<typename Duration>
bool is_older_than(fs::path const & path, Duration const duration) {
    auto ftimeduration = fs::last_write_time(path).time_since_epoch();
    auto nowduration = (ch::system_clock::now() - duration).time_since_epoch();
    return ch::duration_cast<Duration>(nowduration - ftimeduration).count() > 0;
}

template<typename Duration>
void remove_file_older_than(fs::path const & path, Duration const duration) {
    try {
        if (fs::exists(path)) {
            if(is_older_than(path, duration)) {
                fs::remove(path);
            } else if (fs::is_directory(path)) {
                for (auto const & entry: fs::directory_iterator(path)) {
                    remove_files_older_than(entry.path(), duration);    }
            }
        }
    }
    catch(std::exception const & ex) {
        cerr << ex.what() << endl;
    }
}

int main () {
    fs::path path = "./";

}
