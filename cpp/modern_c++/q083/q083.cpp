#include <iostream>
#include "ft2build.h"
#include "pngwriter.h"
#include <random>
#include <array>
#include <algorithm>
#include <functional>
#include <memory>


using namespace std;

class chaptchaGenerator{

private:
    unique_ptr<pngwriter> p;
    std::random_device rd;
    std::mt19937 eng;

public:
    chaptchaGenerator(int width, int height, int background_color, string filepath)
        : p(new pngwriter(width, height, 0, filepath.c_str()))
    {
        auto seed_data = array<int, mt19937::state_size> {};
        generate(begin(seed_data), end(seed_data), ref(rd));
        std::seed_seq seq(cbegin(seed_data), cend(seed_data));
        eng.seed(seq);
    }

    void addBackGroundColor() {
        int width = p->getwidth();
        int height = p->getheight();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; ++j) {
                p->plot(i, j, (double)i / width, (double)j / height, (double)(i+j) / (width+height)) ;
            }
        }

    }
    void addRandomString() {
        char chars[]{"abcdefghijklmnopqrstuvwxyz"};
        const int length = size(chars)-1; // remove '\0' in the end position
        std::uniform_int_distribution<> ud(0, length-1);

        char path[] = "/usr/local/src/freetype-2.10.1/docs/reference/site/assets/fonts/specimen/FontAwesome.ttf";
        int pos = 0;
        char test = 'a';
        for(int i = 0; i < 5; i++) {
            pos = ud(eng);
            p->plot_text_utf8(path, 30, p->getwidth()/6* (i+1), p->getheight() / 2, 3.16 / pos, &test, 100, 100, 100);
        }
    }

    void close() {
        p->close();
    }
};


void addRandomLine(pngwriter *p) {

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


    chaptchaGenerator c(width, height, 0, filepath);
    c.addBackGroundColor();
    c.addRandomString();
    c.close();
}
