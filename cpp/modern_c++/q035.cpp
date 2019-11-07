//Use this compile command:
//g++ -std=c++1z filename -lstdc++fs

#include <iostream>
#include <experimental/filesystem>
#include <iomanip>
#include <numeric>

using namespace std;
namespace fs = std::experimental::filesystem;

uintmax_t get_directory_size(fs::path const & dir, bool const follow_symlinks = false) {
    auto iterator = fs::recursive_directory_iterator(
            dir,
            follow_symlinks ? fs::directory_options::follow_directory_symlink :
                              fs::directory_options::none);

    return accumulate(
            fs::begin(iterator), fs::end(iterator),
            0ULL,
            [] (std::uintmax_t const total, fs::directory_entry const & entry) {
                return total + (fs::is_regular_file(entry) ? fs::file_size(entry.path()) : 0ULL);
                });
}

int main () {
    string path;
    cout << "Path: ";
    cin >> path;
    cout << "Size: " << get_directory_size(path) << std::endl;
}
