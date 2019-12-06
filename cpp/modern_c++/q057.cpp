#include <iostream>
#include <vector>

using namespace std;

template<typename T>
void sort(T itr1, T itr2) {
    int ind = distance(itr1, itr2) % 100;



}

int main () {
    vector<int> v1{1, 3, 5,2, 3,11,4};
    vector<string> v2{"fsed", "e9wf", "14fK", "few"};

    sort(v1.begin(), v1.end());
}
