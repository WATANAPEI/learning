#include <cereal/cereal.hpp>
#include <cereal/archives/xml.hpp>
#include <cereal/archives/json.hpp>
#include <vector>
#include <fstream>
#include <sstream>
#include <cereal/types/vector.hpp>
#define TO_STRING(VariableName) # VariableName

using namespace std;

struct Role {
    string star;
    string name;

    template <typename Archive>
    void save(Archive &ar) const {
        ar(CEREAL_NVP(star),CEREAL_NVP(name));
    }
    template <typename Archive>
    void load(Archive &ar) {
        ar(star, name);
    }

};
struct Director {
    string name;

    template <typename Archive>
    void save(Archive &ar) const {
        ar(CEREAL_NVP(name));
    }
    template <typename Archive>
    void load(Archive &ar) {
        ar(name);
    }
};
struct Writer {
    string name;

    template <typename Archive>
    void save(Archive &ar) const {
        ar(CEREAL_NVP(name));
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
    int length;
    vector<Role> cast;
    vector<Director> directors;
    vector<Writer> writers;

    template <typename Archive>
    void save(Archive &ar) const {
        ar(
                CEREAL_NVP(id)
                , CEREAL_NVP(title)
                , CEREAL_NVP(year)
                , CEREAL_NVP(length)
                , CEREAL_NVP(directors)
                , CEREAL_NVP(writers)
                , CEREAL_NVP(cast)
                );
    }
    template <typename Archive>
    void load(Archive &ar) {
        ar(id, title, year, length, directors, writers, cast);
        //ar(id, title, year, length);
    }

};

int main() {
        ofstream os("q075.json");
        //stringstream ss;
        //cereal::XMLOutputArchive::Options option = cereal::XMLOutputArchive::Options::Default();
        //option.sizeAttributes(false);
    {
        cereal::JSONOutputArchive archive(os);

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

        archive(cereal::make_nvp("movie", movie));
    }
    //cout << ss.str() << endl;
    {
        ifstream is("q075.json");
        cereal::JSONInputArchive archive(is);

        vector<Director> directors;
        vector<Writer> writers;
        vector<Role> cast;

        Movie movie;

        archive(cereal::make_nvp("movie", movie));
        cout << "id: " << movie.id << endl;
        cout << "title: " << movie.title << endl;
        cout << "length: " << movie.length << endl;
        cout << "year: " << movie.year << endl;
        for(auto e : movie.directors) {
            cout << "director's name: " << e.name << endl;
        }
        for(auto e : movie.writers) {
            cout << "writer's name: " << e.name << endl;
        }
        for(auto e : movie.cast) {
            cout << "cast: \n";
            cout << "star: " << e.star << endl;
            cout << "name: " << e.name << endl;
        }

    }

}
