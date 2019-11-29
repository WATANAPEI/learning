#include <iostream>
#include <vector>
#include <memory>
#include <assert.h>
#include <algorithm>
#include <cctype>
#include <string>

using namespace std;

class password_validator
{
public:
    virtual bool validate(string_view password) = 0;
    virtual ~password_validator() {};
};

class length_validator final : public password_validator
{
public:
    explicit length_validator(unsigned int min_length) : length(min_length) {}

    virtual bool validate(string_view password) override{
        return password.length() >= length;
    }
private:
    unsigned int length;
};

class password_validator_decorator : public password_validator
{
public :
    explicit password_validator_decorator(
                unique_ptr<password_validator> validator) : inner(move(validator))
    {
    }

    virtual bool validate(string_view password) override
    {
        return inner->validate(password);
    }
private:
    unique_ptr<password_validator> inner;
};

class digit_password_validator final : public password_validator_decorator
{
    public:
        explicit digit_password_validator(
                unique_ptr<password_validator> validator) : password_validator_decorator(
                    move(validator)) {}
        virtual bool validate(string_view password) override {
            if(!password_validator_decorator::validate(password)){
                return false;
            }

            return password.find_first_of("0123456789") != string::npos;
        }
};

class case_password_validator final : public password_validator_decorator
{
public:
    explicit case_password_validator(unique_ptr<password_validator> validator) : password_validator_decorator(move(validator)) {}
    virtual bool validate(string_view password) override {
        if(!password_validator_decorator::validate(password)) {
            return false;
        }

        bool haslower = false;
        bool hasupper = false;
        for(size_t i = 0; i < password.length() && !(hasupper && haslower); ++i) {
            if(islower(password[i])) haslower = true;
            else if(isupper(password[i])) hasupper = true;
        }

        return haslower && hasupper;
    }
};

class symbol_password_validator final : public password_validator_decorator
{
public:
    explicit symbol_password_validator(unique_ptr<password_validator> validator) : password_validator_decorator(move(validator)) {}
    virtual bool validate(string_view password) override
    {
        if (!password_validator_decorator::validate(password)) {
            return false;
        }
        return password.find_first_of("!@#$%^&*(){}[]?<>") != string::npos;
    }
};

int main () {
    {
        auto validator1 = make_unique<digit_password_validator>(
                make_unique<length_validator>(8));
        assert(validator1->validate("abc123!@#"));
        assert(!validator1->validate("abcde!@#"));
    }

    {
        auto validator2 = make_unique<symbol_password_validator>(
                make_unique<case_password_validator>(
                    make_unique<digit_password_validator>(
                        make_unique<length_validator>(8))));
        assert(validator2->validate("Abc123!@#"));
        assert(!validator2->validate("Abc1234567"));
    }
}

