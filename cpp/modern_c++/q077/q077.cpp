#include <iostream>
#include <PDFWriter/PDFWriter.h>
#include <PDFWriter/PDFPage.h>
#include <PDFWriter/PageContentContext.h>
#include <PDFWriter/AbstractContentContext.h>
#include <PDFWriter/PDFUsedFont.h>
#include <iomanip>
#ifdef _WIN32
static std::string const fonts_dir = R"(c:\windows\fonts\)";
#elif defined (__APPLE__)
static std::string const fonts_dir = R"(/Library/Fonts)";
#else
static std::string const fonts_dir = R"(/usr/local/src/pngwriter/fonts/)";
#endif


using namespace std;

struct casting_role {
    string actor;
    string role;
};

struct movie {
    unsigned int id;
    string title;
    int year;
    unsigned int length;

    vector<casting_role> cast;
    vector<string> directors;
    vector<string> writers;
};

using movie_list = vector<movie>;


void print_pdf(movie_list const &movies, string const &path) {
    int const height = 842;
    int const width = 595;
    int const left = 60;
    int const top = 770;
    int const right = 535;
    int const bottom = 60;
    int const line_height = 28;

    PDFWriter pdf;

    pdf.StartPDF(path.c_str(), ePDFVersion13);
    auto font = pdf.GetFontForFile(fonts_dir + "FreeMonoBold.ttf");
    AbstractContentContext::GraphicOptions pathStrokeOptions(
            AbstractContentContext::eStroke,
            AbstractContentContext::eRGB,
            0xff000000,
            1);

    PDFPage *page = nullptr;
    PageContentContext *context = nullptr;
    int index = 0;
    for (size_t i = 0; i < movies.size(); ++i) {
        index = i %25;
        if (index == 0){
            if(page != nullptr) {
                DoubleAndDoublePairList pathPoints;
                pathPoints.push_back(DoubleAndDoublePair(left, bottom));
                pathPoints.push_back(DoubleAndDoublePair(right, bottom));
                context->DrawPath(pathPoints, pathStrokeOptions);

                pdf.EndPageContentContext(context);
                pdf.WritePageAndRelease(page);
            }

            page = new PDFPage();
            page->SetMediaBox(PDFRectangle(0, 0, width, height));
            context = pdf.StartPageContentContext(page);

            {
                DoubleAndDoublePairList pathPoints;
                pathPoints.push_back(DoubleAndDoublePair(left, top));
                pathPoints.push_back(DoubleAndDoublePair(right, top));
                context->DrawPath(pathPoints, pathStrokeOptions);
            }
        }

        if(i == 0) {
            AbstractContentContext::TextOptions const textOptions(
                    font, 26, AbstractContentContext::eGray, 0);
            context->WriteText(left, top + 15, "List of movies", textOptions);
        }

        auto textw = 0;
        {
            AbstractContentContext::TextOptions const textOptions(
                    font, 20, AbstractContentContext::eGray, 0);
            context->WriteText(left, top - 20 - line_height * index, movies[i].title, textOptions);
            auto textDimensions = font->CalculateTextDimensions(movies[i].title, 20);
            textw = textDimensions.width;
        }

        {
            AbstractContentContext::TextOptions const textOptions(
                    font, 16, AbstractContentContext::eGray, 0);
            context->WriteText(left + textw + 5, top - 20 - line_height * index, "(" + to_string(movies[i].year) + ")", textOptions);
            stringstream s;
            s << movies[i].length / 60 << ':' << std::setw(2) << setfill('0') << movies[i].length % 60;

            context->WriteText(right - 30, top - 20 - line_height * index, s.str(), textOptions);
        }
    }
    DoubleAndDoublePairList pathPoints;
    pathPoints.push_back(DoubleAndDoublePair(left, top - line_height * (index + 1)));
    pathPoints.push_back(DoubleAndDoublePair(right, top - line_height * (index + 1)));
    context->DrawPath(pathPoints, pathStrokeOptions);

    if(page != nullptr) {
        pdf.EndPageContentContext(context);
        pdf.WritePageAndRelease(page);
    }

    pdf.EndPDF();
}

int main() {
    movie_list const movies{
        {1, "The Matrix", 1999, 136 },
        {2, "Forest Gump", 1994, 142 },
        {3, "L.A. Confidential", 1997, 138 },
        {4, "Shutter Island", 2010, 138 },
    };

    print_pdf(movies, "movies.pdf");

}
