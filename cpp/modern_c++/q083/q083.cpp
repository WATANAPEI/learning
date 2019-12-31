#include <iostream>
#include "ft2build.h"
#include "pngwriter.h"
#include <random>
#include <array>
#include <algorithm>
#include <functional>


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

void addRandomString(pngwriter *p = nullptr) {
    const char chars[]{"abcdefghijklmnopqrstuvwxyz"};
    const int length = size(chars)-1; // remove '\0' in the end position
    std::random_device rd;
    std::mt19937 eng;
    auto seed_data = array<int, mt19937::state_size> {};
    generate(begin(seed_data), end(seed_data), ref(rd));
    std::seed_seq seq(cbegin(seed_data), cend(seed_data));
    eng.seed(seq);
    std::uniform_int_distribution<> ud(0, length-1);
    int pos = 0;
    for(int i = 0; i < 100; i++) {
        pos = ud(eng);
        cout << "pos: " << pos << endl;
        cout << "char: " << chars[pos] << endl;
    }
}

void create_pic(int const width, int const height, string const &filepath) {
    pngwriter p{width, height, 0, filepath.c_str() };
    paintBackGroundColor(&p);
    addRandomString(&p);

    p.close();

}

int main() {
    /*
    int width = 0, height = 0;
    string filepath;
    cout << "Width: ";
    cin >> width;
    cout << "Height: ";
    cin >> height;
    cout << "Output: ";
    cin >> filepath;

    create_pic(width, height, filepath);
    */
    addRandomString();
}
