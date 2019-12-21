#include <iostream>
#include <vector>
#include <array>

using namespace std;
const int v = 6;
const int INF = 100'000'000;
typedef array<array<int, v>, v> dim2Array;

int calcDistance(const dim2Array &d, const int from, const int to) {
    return d.at(from).at(to);
}

int main() {
    //vector<vector<int>> d = vector(6, vector<int>(6));
    const dim2Array d{
        {{0, 7, 9, INF, INF, 14},
        {7, 0, 10, 15, INF, INF},
        {9, 10, 0, 11, INF, 2},
        {INF, 15, 11, 0, 6, INF},
        {INF, INF, INF, 6, 0, 9},
        {14, INF, 2, INF, 9, 0},}
    };
    dim2Array result;
    for(int i = 0; i < v; i++) {
        for(int j = 0; j < v; j++) {
            if(i == j) {
                result.at(i).at(j) = 0;
            } else {
                result.at(i).at(j) = INF;
            }
        }
    }
    for(unsigned int i = 0; i < v-1; i++) {
        for(unsigned int j = i+1; j<v; j++) {
            for(unsigned int k = 0; k < v; k++) {
                result.at(i).at(j) = min(result.at(i).at(j), calcDistance(d, i, k) + calcDistance(d, k, j));
            }
        }
    }
    for(int i = 0; i < v; i++) {
        for(int j = 0; j < v; j++) {
            cout << result.at(i).at(j) << ", ";
        }
        cout << endl;
    }

}
