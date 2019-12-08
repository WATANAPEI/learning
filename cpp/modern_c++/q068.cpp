#include <iostream>
#include <cctype>
#include <memory>
#include <array>
#include <random>
#include <algorithm>

using namespace std;

class password_generator{
public:
    virtual string generate() = 0;
    virtual string allowed_chars() const = 0;
    virtual size_t length() const = 0;
    virtual void add(unique_ptr<password_generator> genarator) = 0;
    virtual ~password_generator() = default;

};

class basic_password_generator : public password_generator{
    size_t const len;
public:
    explicit basic_password_generator(size_t const len) noexcept : len(len) {}
    virtual string generate() override{
        throw runtime_error("Not implemented");
    }
    virtual void add(unique_ptr<password_generator>) override{
        throw runtime_error("Not implemented");
    }
    virtual size_t length() const noexcept override final {
        return len;
    }
};

class digit_generator : public basic_password_generator
{
public:
    explicit digit_generator(size_t const len) noexcept : basic_password_generator(len) {
    }

    virtual string allowed_chars() const override {
        return "0123456789";
    }
};

class symbol_generator : public basic_password_generator
{
public:
    explicit symbol_generator(size_t const len) noexcept
    : basic_password_generator(len){}
    virtual string allowed_chars() const override {
        return "!@#$%^&*(){}[]<>?";
    }
};

class upper_letter_generator : public basic_password_generator
{
public:
    explicit upper_letter_generator(size_t const len) noexcept
    : basic_password_generator(len) {}
    virtual string allowed_chars() const override {
        return "QBCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
};

class lower_letter_generator : public basic_password_generator
{
public:
    explicit lower_letter_generator(size_t const len) noexcept
     : basic_password_generator(len) {};
     virtual string allowed_chars() const override{
         return "abcdefghijklmnopqrstuvwxyz";
     }
};

class composite_password_generator : public password_generator
{
    virtual string allowed_chars() const override {
        throw runtime_error("not implemented");
    }
    virtual size_t length() const override {
        throw runtime_error("not implemented");
    }
    random_device rd;
    mt19937 eng;
    vector<unique_ptr<password_generator>> generators;
public:
    composite_password_generator()
    {
        auto seed_data = array<int, mt19937::state_size> {};
        std::generate(begin(seed_data), end(seed_data), ref(rd));
        seed_seq seq(cbegin(seed_data), cend(seed_data));
        eng.seed(seq);
    }
    virtual string generate() override {
        string password;
        for (auto & generator : generators){
            string chars = generator->allowed_chars();
            uniform_int_distribution<> ud(0, static_cast<int>(chars.length() - 1));
            for(size_t i = 0; i < generator->length(); ++i){
                password += chars[ud(eng)];
            }
        }

        shuffle(begin(password), end(password), eng);
        return password;
    }
    virtual void add(unique_ptr<password_generator> generator) override
    {
        generators.push_back(move(generator));
    }

};

int main() {
    composite_password_generator generator;
    generator.add(make_unique<symbol_generator>(2));
    generator.add(make_unique<digit_generator>(2));
    generator.add(make_unique<upper_letter_generator>(2));
    generator.add(make_unique<lower_letter_generator>(4));

    auto const password = generator.generate();
}
