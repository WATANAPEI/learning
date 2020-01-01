#include <iostream>
#include <cassert>
using namespace std;

//TODO operator overload
class Currency {
private:
    string unit;
    int amount;
public:
    Currency(string unit, int amount)
        : unit(unit), amount(amount) {}
    const string getUnit() const {
        return unit;
    }
    int getAmount() const {
        return amount;
    }
};

bool operator == (Currency &lhs, Currency &rhs) {
    if(lhs.getUnit() == rhs.getUnit() && lhs.getAmount() == rhs.getAmount()) {
        return true;
    } else {
        return false;
    }
};

bool operator != (Currency &lhs, Currency &rhs) {
    return lhs == rhs ? false: true;
};
bool operator < (Currency &lhs, Currency &rhs) {
    if(lhs.getUnit() == rhs.getUnit() && lhs.getAmount() < rhs.getAmount()) {
        return true;
    } else {
        return false;
    }
};

bool operator > (Currency &lhs, Currency &rhs) {
    if(lhs.getUnit() == rhs.getUnit() && lhs.getAmount() > rhs.getAmount()) {
        return true;
    } else {
        return false;
    }
};

bool operator >= (Currency &lhs, Currency &rhs) {
    return lhs < rhs ? false: true;
};

bool operator <= (Currency &lhs, Currency &rhs) {
    return lhs > rhs ? false: true;
};

class Item {
private:
    string name;
    Currency price;
public:
    Item(string name, Currency price)
        : name(name), price(price) {};
    Currency getPrice() const {
        return price;
    }
    string getName() const {
        return name;
    }
};

class Authorizer{
public:
    virtual ~Authorizer(){};
    virtual Currency getAuthorizableCurrency() const = 0;
    virtual bool canAuthorize(Item &item) = 0;
};

class Employee : public Authorizer {
private:
    static const int authorizableAmount = 1000;
    string name;
    Currency currency;

public:
    Employee(string name, string unit)
        : name(name), currency(unit, authorizableAmount){}
    Currency getAuthorizableCurrency() const override {
        return currency;
    }
    bool canAuthorize(Item &item) override {
        Currency price = item.getPrice();
        return price < currency ? true: false;
    }

};

class SectionChief : public Authorizer {
private:
    static const int authorizableAmount = 10000;
    string name;
    Currency currency;

public:
    SectionChief(string name, string unit)
        : name(name), currency(unit, authorizableAmount){}
    Currency getAuthorizableCurrency() const override {
        return currency;
    }
    bool canAuthorize(Item &item) override {
        Currency price = item.getPrice();
        return price < currency ? true: false;
    }

};

class HeadOfDepartment : public Authorizer {
private:
    static const int authorizableAmount = 100000;
    string name;
    Currency currency;

public:
    HeadOfDepartment(string name, string unit)
        : name(name), currency(unit, authorizableAmount){}
    Currency getAuthorizableCurrency() const override {
        return currency;
    }
    bool canAuthorize(Item &item) override {
        Currency price = item.getPrice();
        return price < currency ? true: false;
    }

};

class President : public Authorizer {
private:
    static const int authorizableAmount = -1;
    string name;
    Currency currency;

public:
    President(string name, string unit)
        : name(name), currency(unit, authorizableAmount){}
    Currency getAuthorizableCurrency() const override {
        return currency;
    }
    bool canAuthorize(Item &item) override {
        return true;
    }

};


int main() {
    //Employee employee("Ann", "JPY");
    Authorizer *employee = new Employee("Ann", "JPY");
    Authorizer *sectionChief = new SectionChief("Ben", "JPY");
    Authorizer *headOfDepartment = new HeadOfDepartment("Charley", "JPY");
    Authorizer *president = new President("Dot", "JPY");
    Item pen("pen", Currency("JPY", 500));
    assert(employee->canAuthorize(pen) == true);
    assert(sectionChief->canAuthorize(pen) == true);
    assert(headOfDepartment->canAuthorize(pen) == true);
    assert(president->canAuthorize(pen) == true);

    Item chair("chair", Currency("JPY", 5000));
    assert(employee->canAuthorize(chair) == false);
    assert(sectionChief->canAuthorize(chair) == true);
    assert(headOfDepartment->canAuthorize(chair) == true);
    assert(president->canAuthorize(chair) == true);

    Item desk("desk", Currency("JPY", 50000));
    assert(employee->canAuthorize(desk) == false);
    assert(sectionChief->canAuthorize(desk) == false);
    assert(headOfDepartment->canAuthorize(desk) == true);
    assert(president->canAuthorize(desk) == true);

    Item building("building", Currency("JPY", 100000000));
    assert(employee->canAuthorize(building) == false);
    assert(sectionChief->canAuthorize(building) == false);
    assert(headOfDepartment->canAuthorize(building) == false);
    assert(president->canAuthorize(building) == true);
}
