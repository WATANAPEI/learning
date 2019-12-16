//use command below to compile and link
//g++ filneame -std=c++1z -lzip
#ifdef _MSC_VER
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#endif
#include <iostream>
#include <regex>
#include <ZipLib/ZipFile.h>
#include <ZipLib/ZipArchive.h>
#include <fstream>

using namespace std;


int main() {
    string zipFileName = "c.zip";
    auto archive = ZipFile::Open(zipFileName);
    size_t entries = archive->GetEntriesCount();
    cout << "entries count: " << entries << endl;

}
