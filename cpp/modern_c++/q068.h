#include <string>
#include <memory>

class Validatable {
public:
    virtual bool validate(const std::string &str) const = 0;
    virtual ~Validatable() = default;
};

class LengthValidator : public Validatable  {
public:
    explicit LengthValidator(int length);
    ~LengthValidator();
    explicit LengthValidator(const LengthValidator &lengthValidator);
    LengthValidator  &operator= (const LengthValidator &lengthValidator);
    virtual bool validate(const std::string &str) const;
    unsigned int getLength() const;
private:
    class Impl;
    std::unique_ptr<Impl> mImpl;
};

class ValidateDecorator : public Validatable {
public:
    explicit ValidateDecorator(std::unique_ptr<Validatable> validator);
    virtual bool validate(const std::string &str) const;
private:
    std::unique_ptr<Validatable> inner;
};

class SymbolValidator : public ValidateDecorator {
public:
    explicit SymbolValidator(std::unique_ptr<Validatable> validator);
    virtual bool validate(const std::string &str) const override;
};

class NumberValidator : public ValidateDecorator {
public:
    explicit NumberValidator(std::unique_ptr<Validatable> validator);
    virtual bool validate(const std::string &str) const override;
};

class CapitalValidator : public ValidateDecorator {
public:
    bool validate(const std::string &str) const override;
};
