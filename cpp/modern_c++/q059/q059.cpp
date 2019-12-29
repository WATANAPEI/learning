#include <iostream>
#include <vector>
#include <algorithm>
#include <sstream>
#include <random>
#include <iomanip>

using namespace std;

string generateRandomStr(int length) {
    static const char alphas[] = "abcdefghijklmnopqrstuvwxyz";
    stringstream sstr;
    random_device seed_gen;
    std::mt19937 engine(seed_gen());
    uniform_int_distribution<> dist(0, sizeof(alphas)-2);
    for(int i = 0; i < length; ++i) {
        int random_int = dist(engine);
        sstr << alphas[random_int];
    }
    //cout << "random string: " << sstr.str() << endl;
    return sstr.str();
}
char generateRandomChar() {
    static const char alphas[] = "abcdefghijklmnopqrstuvwxyz";
    random_device seed_gen;
    std::mt19937 engine(seed_gen());
    uniform_int_distribution<> dist(0, sizeof(alphas)-2);
    int random_int = dist(engine);
    return alphas[random_int];
}

vector<string> copyStr(string const& str, int length) {
    vector<string> result;
    for(int i = 0; i< length; ++i) {
        result.push_back(str);
    }
    return result;
}

vector<string> changeStr(vector<string> const &v) {
    vector<string> result;
    random_device seed_gen;
    std::mt19937 engine(seed_gen());
    uniform_int_distribution<> dist(0, 99);
    for(auto str: v) {
        //cout << left << setw(10) << "before: " <<  str <<endl;
        for(size_t i = 0; i< str.length(); ++i) {
            int randomInt = dist(engine);
            if(randomInt < 5) {
                str.at(i) = generateRandomChar();
            }
        }
        //cout << left << setw(10) << "after: " <<  str <<endl;
        result.push_back(str);
    }
    return result;
}

int calcScore(string const &s, string const &t) {
    int score = 0;
    if(s.length() < t.length()) {
        for(auto sItr = s.begin(), tItr = t.begin(); sItr != s.end(); ++sItr, ++tItr) {
            if(*sItr == *tItr) ++score;
        }
    } else {
        for(auto sItr = s.begin(), tItr = t.begin(); tItr != t.end(); ++sItr, ++tItr) {
            if(*sItr == *tItr) ++score;
        }
    }
    return score;
}

string chooseNearestStr(vector<string> const &v, string const &target){
    int score = 0;
    int maxScore = 0;
    string result = v.at(0);
    for(auto str: v) {
        score = calcScore(str, target);
        //cout << "str: " << str << ", score: " << score << endl;
        if(maxScore < score) {
            result = str;
            maxScore = score;
        }
    }
    return result;
}

int main() {
    string target("methinksitislikeaweasel");
    string str_before = generateRandomStr(target.length());
    string str_after;
    int count = 0;
    int score = 0;
    int copyNum = 100;
    do {
        ++count;
        cout << left << setw(5) << count << " before: " << str_before;
        vector<string> copied_str = copyStr(str_before, copyNum);
        vector<string> changed_str = changeStr(copied_str);
        str_after = chooseNearestStr(changed_str, target);
        score = calcScore(target, str_after);
        cout << " after: " << str_after << " -- score: " << score << endl;
        str_before = str_after;
        //if(count == 2) {
        //    str = target;
        //}
    } while(str_after != target);
}
