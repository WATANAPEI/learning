#include <cereal/cereal.hpp>
#include <cereal/archives/xml.hpp>
#include <cereal/types/memory.hpp>
#include <cereal/archives/json.hpp>
#include <vector>
#include <fstream>
#include <tuple>
#include <sstream>
#include <cereal/types/vector.hpp>
#include <cereal/types/tuple.hpp>
#include <cereal/details/helpers.hpp>
#define TO_STRING(VariableName) # VariableName

using namespace std;

struct Role {
    string star;
    string name;

    template <typename Archive>
    void save(Archive &ar) const {
        ar.appendAttribute("star", star.data());
        ar.appendAttribute("name", name.data());
    }

    template <typename Archive>
    void load(Archive &ar) {
        ar(star,name);
    }
};
struct Director {
    string name;

    template <typename Archive>
    void save(Archive &ar) const {
        ar.appendAttribute("name", name.data());
    }

    template <typename Archive>
    void load(Archive &ar) {
        //ar.loadValue(name);
    }
};
struct Writer {
    string name;

    template <typename Archive>
    void save(Archive &ar) const {
        ar.appendAttribute("name", name.data());
    }
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
    vector<Role> cast;
    vector<Director> directors;
    vector<Writer> writers;
    Director director;

    /*
    Movie() {
        cast.reserve(10);
        directors.reserve(10);
        writers.reserve(10);
    }
    Movie(unsigned int id,
            string title,
            unsigned int year,
            unsigned int length,
            vector<Role> cast,
            vector<Director> directors,
            vector<Writer> writers):
    id(id), title(title), year(year), length(length),
    cast(cast), directors(directors), writers(writers) {
    }
    */

    template <typename Archive>
    void save(Archive &ar) const {
        ar.appendAttribute("id", to_string(id).data());
        ar.appendAttribute("title", title.data());
        ar.appendAttribute("year", to_string(year).data());
        ar.appendAttribute("length", to_string(length).data());
        ar(
                CEREAL_NVP(cast)
                , CEREAL_NVP(directors)
                , CEREAL_NVP(writers)
                );
    }

    template <typename Archive>
    void load(Archive &ar) {
        ar(directors);
        //ar.loadValue(id);
    }

};

int main() {
    //stringstream ss;
    {
        ofstream os("q073.xml");
        cereal::XMLOutputArchive::Options option = cereal::XMLOutputArchive::Options::Default();
        option.sizeAttributes(false);
        cereal::XMLOutputArchive archive(os, option);

        Director kon{name: "Kon Ichikawa"};
        Director yoji{name: "Yoji Yamada"};
        vector<Director> directors;
        directors.push_back(kon);
        directors.push_back(yoji);

        Writer koki{name: "Koki Mitani"};
        vector<Writer> writers;
        writers.push_back(koki);

        Role hiroki{star: "Hiroki Hasegawa", name: "Rando Yaguchi"};
        Role satomi{star: "Satomi Ishihara", name: "Koyoko Ann Paterson"};
        vector<Role> cast;
        cast.push_back(hiroki);
        cast.push_back(satomi);

        Movie movie{1, "Pokemon", 1999, 200, cast, directors, writers};
//        cereal::JSONOutputArchive archive(ss);
        //cereal::XMLOutputArchive::Options option = cereal::XMLOutputArchive::Options::Default();
        //option.sizeAttributes(false);
        //cereal::XMLOutputArchive archive(ss, option);

        archive(cereal::make_nvp("movie", movie));
    }
    {
        ifstream is("q073.xml");
        cereal::XMLInputArchive archive(is);

        Movie movie;

        archive(cereal::make_nvp("movie", movie));
        cout << movie.directors.at(0).name << endl;

    }

}
