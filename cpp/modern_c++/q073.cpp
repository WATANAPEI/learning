#include <cereal/cereal.hpp>
#include <cereal/archives/xml.hpp>
#include <vector>
#include <tuple>
#include <sstream>
#include <cereal/types/vector.hpp>
#include <cereal/types/tuple.hpp>

using namespace std;

struct Movie {
    unsigned int id;
    string title;
    unsigned int year;
    unsigned int length;
    vector<string> directors;
    vector<string> writers;
    vector<string> cast;
    tuple<string, string> castingRole;

    template <class Archive>
    void serialize(Archive &ar) {
        ar.appendAttribute("id", to_string(id).data());
        ar.appendAttribute("title", title.data());
        ar.appendAttribute("year", to_string(year).data());
        ar.appendAttribute("length", to_string(length).data());
        ar(
                CEREAL_NVP(directors)
                , CEREAL_NVP(writers)
                , CEREAL_NVP(cast)
                , CEREAL_NVP(castingRole)
                );
    }

};

int main() {
    vector<string> directors = {"satochi", "tsubasa"};
    vector<string> writers = {"aan", "tada"};
    vector<string> casts = {"deka"};
    tuple<string, string> castingRole = {make_tuple("ore", "torasan")};
    Movie movie{1, "Pokemon", 1999, 200, directors, writers, casts, castingRole};
    stringstream ss;
    {
        cereal::XMLOutputArchive archive(ss);
        archive(cereal::make_nvp("movie", movie));
    }
    cout << ss.str() << endl;

}
