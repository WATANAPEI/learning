#include <cereal/cereal.hpp>
#include <cereal/archives/xml.hpp>
#include <vector>

using namespace std;

struct Movie {
    unsigned int id;
    string title;
    unsigned int year;
    unsigned int screeningTime;
    vector<string> directors;
    vector<string> writers;
    vector<string> cast;
    pair<string, string> castingRole;

};

int main() {
    cereal::XMLOutputArchive archive(std::cout);
    Movie movie{1, "Pokemon", 1999, 200, vector{"satoshi", "tsubasa"}, vector{"aa" "bb"}, vector{"deka"}}
    archive( CEREAL_NVP(

}
