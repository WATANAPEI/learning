#include <iostream>
#include "ft2build.h"
#include "pngwriter.h"

using namespace std;

void create_flag(int const width, int const height, string const &filepath) {
    pngwriter flag{width, height, 0, filepath.c_str() };
    int const size = width / 3;
    //red
    flag.filledsquare(0, 0, size, 2 * size, 65535, 0, 0);
    //yellow
    flag.filledsquare(size, 0, 2 * size, 2 * size, 65535, 65535, 0);
    //blue
    flag.filledsquare(2 * size, 0, 3 * size, 2 * size, 0, 0, 65535);

    flag.close();

}

int main() {
    int width = 0, height = 0;
    string filepath;
    cout << "Width: ";
    cin >> width;
    cout << "Height: ";
    cin >> height;
    cout << "Output: ";
    cin >> filepath;

    create_flag(width, height, filepath);
}
