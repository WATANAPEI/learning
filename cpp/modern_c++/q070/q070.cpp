#include <iostream>

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
    cout << "can employee buy " << pen.getName() << "? -> "  << boolalpha << employee->canAuthorize(pen) << endl;
    cout << "can sectionChief buy " << pen.getName() << "? -> "  << boolalpha << sectionChief->canAuthorize(pen) << endl;
    cout << "can headOfDepartment buy " << pen.getName() << "? -> "  << boolalpha << headOfDepartment->canAuthorize(pen) << endl;
    cout << "can president buy " << pen.getName() << "? -> "  << boolalpha << president->canAuthorize(pen) << endl;

    Item chair("chair", Currency("JPY", 5000));
    cout << "can employee buy " << chair.getName() << "? -> "  << boolalpha << employee->canAuthorize(chair) << endl;
    cout << "can sectionChief buy " << chair.getName() << "? -> "  << boolalpha << sectionChief->canAuthorize(chair) << endl;
    cout << "can headOfDepartment buy " << chair.getName() << "? -> "  << boolalpha << headOfDepartment->canAuthorize(chair) << endl;
    cout << "can president buy " << chair.getName() << "? -> "  << boolalpha << president->canAuthorize(chair) << endl;
    Item desk("desk", Currency("JPY", 50000));
    cout << "can employee buy " << desk.getName() << "? -> "  << boolalpha << employee->canAuthorize(desk) << endl;
    cout << "can sectionChief buy " << desk.getName() << "? -> "  << boolalpha << sectionChief->canAuthorize(desk) << endl;
    cout << "can headOfDepartment buy " << desk.getName() << "? -> "  << boolalpha << headOfDepartment->canAuthorize(desk) << endl;
    cout << "can president buy " << desk.getName() << "? -> "  << boolalpha << president->canAuthorize(desk) << endl;

    Item building("building", Currency("JPY", 100000000));
    cout << "can employee buy " << building.getName() << "? -> "  << boolalpha << employee->canAuthorize(building) << endl;
    cout << "can sectionChief buy " << building.getName() << "? -> "  << boolalpha << sectionChief->canAuthorize(building) << endl;
    cout << "can headOfDepartment buy " << building.getName() << "? -> "  << boolalpha << headOfDepartment->canAuthorize(building) << endl;
    cout << "can president buy " << desk.getName() << "? -> "  << boolalpha << president->canAuthorize(building) << endl;
}
