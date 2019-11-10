#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
using ll = long long int;
vector<pair<ll, int>> memo;

vector<ll> calc_collatz(ll input) {
    vector<ll> result;
    result.push_back(input);
    auto last = *(result.end() -1);
    //cout << "calc: " << input << endl;
    while(last != 1) {
        if(last % 2 == 0) {
            result.push_back(last / 2);
        } else {
            result.push_back(last * 3 + 1);
        }
        //cout << "pushed: " << last << endl;
        last = *(result.end() - 1);
    }
    return result;
}

ll calc_collatz_length(ll input) {
    vector<ll> collatz_seq = calc_collatz(input);
    int size = collatz_seq.size();
    memo.push_back(make_pair(input,size));
    return size;

}


vector<ll> get_longest_collatz(ll n) {
    vector<ll> longest;
    vector<ll> temp;
    size_t length = 0;
    size_t max_length = 1;
    for(int i = 2; i <= n; i++) {
        temp = calc_collatz(i);
        length = temp.size();
        if(max_length < length) {
            longest = temp;
            max_length = length;
        }
    //cout <<"max length: " << max_length << endl;
    //cout << "length: " << length << endl;
    }
    return longest;
}


int main() {
    ll input;
    cout << "input integer: ";
    cin >> input;
    vector<ll> result = get_longest_collatz(input);
    for(auto e : result) {
        cout << e << ", ";
    }
    cout << endl;
    cout << "max length: " << result.size() << endl;

}
