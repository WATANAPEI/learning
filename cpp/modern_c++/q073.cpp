#include <cereal/cereal.hpp>
#include <cereal/archives/xml.hpp>
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
    const string star;
    const string name;

    void save(cereal::XMLOutputArchive &ar) {
        ar.appendAttribute("star", star.data());
        ar.appendAttribute("name", name.data());
    }

    void load(cereal::XMLInputArchive &ar) {
        ar(star,name);
    }
};
struct Director {
    const string name;

    void save(cereal::XMLOutputArchive &ar) {
        ar.appendAttribute("name", name.data());
    }

    void load(cereal::XMLInputArchive &ar) {
        ar(name);
    }
};
struct Writer {
    const string name;

    void save(cereal::XMLOutputArchive &ar) {
        ar.appendAttribute("name", name.data());
    }
    void load(cereal::XMLInputArchive &ar) {
        ar(name);
    }
};

struct Movie {
    const unsigned int id;
    const string title;
    const unsigned int year;
    const unsigned int length;
    vector<Role> cast;
    vector<Director> directors;
    vector<Writer> writers;

    void save(cereal::XMLOutputArchive &ar) {
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

    void load(cereal::XMLInputArchive &ari) {
        ari(directors);
    }

};

int main() {
    {
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
        stringstream ss;
//        cereal::JSONOutputArchive archive(ss);
        cereal::XMLOutputArchive::Options option = cereal::XMLOutputArchive::Options::Default();
        option.sizeAttributes(false);
        cereal::XMLOutputArchive archive(ss, option);

        archive(cereal::make_nvp("movie", movie));
        cout << ss.str() << endl;
    }

    {
        vector<Director> directors;
        string xmlPath = "/home/baystars/string/learning/cpp/modern_c++/q073.xml";
        std::ifstream is(xmlPath);
        cereal::XMLInputArchive ari(is);
        ari(directors);
        cout << directors.at(0).name << endl;

    }


}
