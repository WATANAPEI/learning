#include <iostream>
#include <PDFWriter/PDFWriter.h>
#include <PDFWriter/PDFPage.h>
#include <PDFWriter/PageContentContext.h>
#include <PDFWriter/AbstractContentContext.h>


using namespace std;

int main() {
    PDFWriter pdfwriter;
    pdfwriter.StartPDF("/mnt/c/Users/Baystars/Downloads/test.pdf", ePDFVersion13);
    PDFPage *page = new PDFPage();
    page->SetMediaBox(PDFRectangle(0, 0, 595, 842));
    PageContentContext *contentContext = pdfwriter.StartPageContentContext(page);
    PDFUsedFont *font = pdfwriter.GetFontForFile("/usr/local/src/pngwriter/fonts/FreeMonoBold.ttf");
    contentContext->BT();
    contentContext->Tf(font, 18);
    contentContext->Tm(1,0,0,1,10,700);
    contentContext->Tj("List of movies");
    contentContext->Tf(font, 14);
    contentContext->Tm(1,0,0,1,10,660);
    contentContext->Tj("Matrix");
    contentContext->ET();
    pdfwriter.EndPageContentContext(contentContext);
    pdfwriter.WritePageAndRelease(page);
    pdfwriter.EndPDF();
    cout << "Finished" << endl;

}
