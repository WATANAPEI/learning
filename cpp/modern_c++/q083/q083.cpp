#include <iostream>
#include "ft2build.h"
#include "pngwriter.h"

using namespace std;

void paintBackGroundColor(pngwriter *p) {
    int width = p->getwidth();
    int height = p->getheight();
    for(int i = 0; i < width; i++) {
        for(int j = 0; j < height; ++j) {
            p->plot(i, j, (double)i / width, (double)j / height, (double)(i+j) / (width+height)) ;
        }
    }

}

void create_pic(int const width, int const height, string const &filepath) {
    pngwriter p{width, height, 0, filepath.c_str() };
    paintBackGroundColor(&p);

    p.close();

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

    create_pic(width, height, filepath);
}
