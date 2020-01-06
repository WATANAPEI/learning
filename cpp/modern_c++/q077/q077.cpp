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
    AbstractContentContext::TextOptions textOptions(font, 14, AbstractContentContext::eGray, 0);
    contentContext->WriteText(10, 100, "hello world", textOptions);
    pdfwriter.EndPageContentContext(contentContext);
    pdfwriter.WritePageAndRelease(page);
    pdfwriter.EndPDF();
    cout << "Finished" << endl;

}
