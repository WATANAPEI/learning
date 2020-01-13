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
    std::random_device rd;
    std::mt19937 eng;
    int width;
    int height;
    int background_color;
    char *filepath;
    unique_ptr<pngwriter> p;

public:
    chaptchaGenerator(int width, int height, int background_color, char *filepath)
        : width(width), height(height), background_color(background_color),
        filepath(filepath), p(new pngwriter(width, height, 0, filepath))
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

        char path[] = "/usr/local/src/pngwriter/fonts/FreeMonoBold.ttf";
        int pos = 0;
        char test = 'a';
        for(int i = 0; i < 5; i++) {
            pos = ud(eng);
            p->plot_text_utf8(path, 30, p->getwidth()/6* (i+1), p->getheight() / 2, 3.16 / pos, &test, 100, 100, 100);
        }
    }

    void addRandomLine(pngwriter *p) {
        uniform_int_distribution<> linePositionDist(height / 2 - 50, height / 2 + 50);
        uniform_real_distribution<> colorDist(0, 1);
        p->line(0, linePositionDist(eng), width, linePositionDist(eng), colorDist(eng), colorDist(eng),colorDist(eng));

    }

    void close() {
        p->close();
    }
};



int main() {
    int width = 0, height = 0;
    char* filepath{};
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
