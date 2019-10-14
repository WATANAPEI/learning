#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

int main() {
    unsigned int row = 10;
    unsigned int col = 20;
    vector<vector<unsigned int>> tri(row, vector<unsigned int>(col, 0));

    tri.at(0).at(0) = 1;
    for(unsigned int i = 1; i < row; ++i) {
        tri.at(i).at(0) = 1;
        for(unsigned int j = 1; j < col; ++j) {
            tri.at(i).at(j) = tri.at(i-1).at(j-1) + tri.at(i-1).at(j);
        }
    }

    for(unsigned int i = 0; i < row; ++i) {
        for(unsigned int j = 0; j < row - i; ++j) {
            cout << "  ";
        }
        for(unsigned int j = 0; j < col; ++j) {
            if(tri.at(i).at(j) != 0) {
                cout << setw(4) << tri.at(i).at(j) << " ";
            }
        }
        cout << endl;
    }

}
