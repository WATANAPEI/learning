#include <iostream>
#include <vector>
#include <array>

using namespace std;

int main() {
    const int v = 6;
    //vector<vector<int>> d = vector(6, vector<int>(6));
    array<array<int, v>, v> d2{
        {{0, 7, 9, 0, 0, 14},
        {7, 0, 10, 15, 0, 0},
        {9, 10, 0, 11, 0, 2},
        {0, 15, 11, 0, 6, 0},
        {0, 0, 0, 6, 0, 9},
        {14, 0, 2, 0, 9, 0},}
    };


}
