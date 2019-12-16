//use command below to compile and link
//g++ filneame -std=c++1z -lzip
#ifdef _MSC_VER
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#endif
#include <iostream>
#include <vector>
#include <regex>
#include <ZipLib/ZipFile.h>
#include <ZipLib/ZipArchive.h>
#include <fstream>
#include <experimental/filesystem>

using namespace std;
namespace fs = experimental::filesystem;
static const string zipFileName = "c.zip";

int main() {
    regex re;
    string regexString;
    cout << "input file pattern: " << endl;
    cin >> regexString;
    try{
        regex regexTemp(regexString);
        re = regexTemp;
    } catch(regex_error &e) {
        cout << e.code() << endl;
        return -1;
    }
    cout << "regexString: " << regexString << endl;

    vector<ZipArchiveEntry> filteredFiles;

    auto archive = ZipFile::Open(zipFileName);
    size_t entries = archive->GetEntriesCount();
    for(size_t i = 0; i < entries; i++) {
        auto entry = archive->GetEntry(int(i));
        string entryName = entry->GetFullName();
        //cout << entryName << endl;
        if(regex_match(entryName, re)) {
            cout << "matched: " << entryName << endl;
        }
    }
    //cout << "entries count: " << entries << endl;

}
