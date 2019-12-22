#include <vector>
#include <iostream>
#include <sstream>
#include <date/date.h>
#include <random>

using namespace std;

enum Sex {
    male,
    female
};

class ICommand {
public:
    virtual ~ICommand(){};
    virtual int getRandomMin() const = 0;
    virtual int getRandomMax()  const = 0;
    virtual int getMaleNumber() const = 0;
    virtual int getFemaleNumber() const = 0;
};

class Southeria : public ICommand {
public:
    ~Southeria(){};
    int getRandomMin() const {
        return 1000;
    }
    int getRandomMax() const {
        return 9999;
    }
    int getMaleNumber() const {
        return 2;
    }
    int getFemaleNumber() const {
        return 1;
    }
};

class Northeria : public ICommand {
public:
    ~Northeria(){};
    int getRandomMin() const {
        return 10000;
    }
    int getRandomMax() const {
        return 99999;
    }
    int getMaleNumber() const {
        return 7;
    }
    int getFemaleNumber() const {
        return 9;
    }
};

int sexToNum(ICommand *command, Sex const &sex) {
    int result;
    if(sex == Sex::male) {
        result = command->getMaleNumber();
    } else if (sex == Sex::female) {
        result = command->getFemaleNumber();
    }
    return result;
}

int createDate(date::year_month_day birthday) {
    int y = int(birthday.year());
    unsigned int m = unsigned(birthday.month());
    unsigned int d = unsigned(birthday.day());
    return y*10000 + m*100 + d;
}

int createRandom(ICommand *command) {
    int randomMin = command->getRandomMin();
    int randomMax = command->getRandomMax();
    random_device rd;
    //TODO: check proper initialize method for seed_seq
    seed_seq s = {rd(), rd()};
    mt19937 e(s);
    uniform_int_distribution<int> d(randomMin, randomMax);
    return d(e);
}

int checkSum(int s, int d, int r) {
    int result = 0;
    stringstream ss("");
    ss << s << d << r;
    //cout << "ss: " << ss.str() << endl;
    size_t length = ss.str().length();
    while(length > 0) {
        char c = ss.get();
        result += (c - '0') * length;
        --length;
     //   cout << "c: " << c << endl;
     //   cout << "length: " << length << endl;
     //   cout << "result: " << result << endl;

    }
    ss.clear();
    return result;
}

string makeSSN(int s, int d, int r, int c) {
    stringstream ss;
    ss << s << d << r << c;
    return ss.str();
}


int main() {
    ICommand *command = new Northeria();
    Sex s = Sex::female;
    auto birthday = date::sys_days{date::year{2090} / date::month{6} / date::day{21}};
    int sexNum = sexToNum(command, s);
    int d = createDate(birthday);
    int r = createRandom(command);
    int c = checkSum(sexNum, d, r);
    string ssn = makeSSN(sexNum, d, r, c);
    //cout << checkSum(1, 20171201, 34895) << endl;
    cout << "sexNum: " << sexNum << endl;
    cout << "d: " << d << endl;
    cout << "r: " << r << endl;
    cout << "c: " << c << endl;
    cout << "ssn: " << ssn << endl;
    assert(checkSum(1, 20171201, 34895) == 230);
}
