#include <cereal/cereal.hpp>
#include <cereal/archives/xml.hpp>
#include <cereal/types/memory.hpp>
#include <cereal/archives/json.hpp>
#include <vector>
#include <fstream>
#include <sstream>
#include <cereal/types/vector.hpp>
#define TO_STRING(VariableName) # VariableName

using namespace std;

struct Role {
    const string star;
    const string name;

    template <typename Archive>
    void load(Archive &ar) {
        ar(star, name);
    }

};
struct Director {
    const string name;

    template <typename Archive>
    void load(Archive &ar) {
        ar(name);
    }
};
struct Writer {
    const string name;

    template <typename Archive>
    void load(Archive &ar) {
        ar(name);
    }

};

struct Movie {
    unsigned int id;
    string title;
    unsigned int year;
    unsigned int length;

    template <class Archive>
    void load(Archive &ar) {
        ar(id, title, year, length);
    }

};

int main() {
    Movie movie;
    {
        ifstream is("q073.xml");
        cereal::XMLInputArchive archive(is);

//        cereal::JSONOutputArchive archive(ss);

        archive(cereal::make_nvp("movie", movie));
        cout << movie.id << endl;
        cout << movie.title << endl;
        cout << movie.year << endl;
        cout << movie.length << endl;
    }


}
