#include <iostream>
#include <cctype>
#include "q068.h"
#include <memory>

class LengthValidator::Impl {
public:
    unsigned int mLength;
};

LengthValidator::LengthValidator(int length = 10) : mImpl(new LengthValidator::Impl())
{
    mImpl->mLength = length;
};

LengthValidator::~LengthValidator()
{
    std::cout << "Destracted" << std::endl;
    //delete mImpl;
    //mImpl = NULL;
};

unsigned int LengthValidator::getLength() const
{
    return mImpl->mLength;
};

LengthValidator::LengthValidator(const LengthValidator &lengthValidator) : mImpl(new LengthValidator::Impl()) {
    mImpl->mLength = lengthValidator.getLength();
};

LengthValidator &LengthValidator::operator=(const LengthValidator &lengthValidator) {
    if(this != &lengthValidator) {
        //mImpl.release();
        mImpl->mLength = lengthValidator.getLength();
    }
    return *this;
};

bool LengthValidator::validate(const std::string &str) const {
    return str.length() > mImpl->mLength;
};

ValidateDecorator::ValidateDecorator(std::unique_ptr<Validatable> validator) : inner(std::move(validator)) {
};

bool ValidateDecorator::validate(const std::string &str) const {
    return inner->validate(str);
};

bool SymbolValidator::validate(const std::string &str) const {
    for(auto e: str) {
        if(ispunct(e)) return true;
    }
    return false;
};

bool NumberValidator::validate(const std::string &str) const {
    for(auto e: str) {
        if(isdigit(e)) return true;
    }
    return false;
};


bool CapitalValidator::validate(const std::string &str) const {
    for(auto e: str) {
        if(isupper(e)) return true;
    }
    return false;
};

int main() {
    std::string test("fewf8fdsaf");
    LengthValidator lengthValidator(9);
    LengthValidator lengthValidator10(lengthValidator);
    LengthValidator lengthValidator11(11);
    lengthValidator11 = lengthValidator10;
    std::cout << std::boolalpha << lengthValidator.validate(test) << std::endl;

}
