#include <iostream>
#include "ft2build.h"
#include "pngwriter.h"

using namespace std;

void create_flag(int const width, int const height, string const &filepath) {
    pngwriter flag{width, height, 0, filepath.c_str() };
    for(int i = 0; i < width; i++) {
        for(int j = 0; j < height; ++j) {
            flag.plot(i, j, (double)i / width, (double)j / height, (double)(i+j) / (width+height)) ;
        }
    }

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
