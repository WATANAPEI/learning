#include <iostream>
#include <PDFWriter/PDFWriter.h>

using namespace std;

int main() {
    PDFWriter pdfwriter;
    pdfwriter.StartPDF("./test.pdf", ePDFVersion13);
    pdfwriter.EndPDF();
    cout << "test" << endl;

}
