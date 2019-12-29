//compile command
// g++ -std=c++1z -c q043.cpp /usr/local/src/date/src/tz.cpp -I/usr/local/src/date/include/date/ -I/usr/local/src/curl/include
// g++ -o q043 q043.o tz.o -L/usr/lib/x86_64-linux-gnu -lcurl
// also, check ldconfig whether it's latest version or not
#include <iostream>
#include <chrono>
#include "date.h"
#include "tz.h"
#include <iomanip>

using namespace std;

struct user
{
    string Name;
    date::time_zone const * Zone;

    explicit user(std::string_view name, std::string_view zone)
        : Name{name.data()}, Zone(date::locate_zone(zone.data())) {}

};

template<class Duration, class TimeZonePtr>
void print_meeting_times(
        date::zoned_time<Duration, TimeZonePtr> const &time,
        vector<user> const &users)
{
    cout << std::left << std::setw(15) << setfill(' ') << "Local time: " << time << endl;

    for(auto const & user: users) {
        cout << left << setw(15) << setfill(' ') << user.Name << date::zoned_time<Duration, TimeZonePtr>(user.Zone, time)
            << endl;
    }
}

int main() {

    vector<user> users{
        user{"aaa", "Europe/Budapest"},
        user{"Jens", "Europe/Berlin"},
        user{"Jane", "America/New_York"}
    };

    unsigned int h, m;
    cout << "Hour: "; cin >> h;
    cout << "Minutes: "; cin >> m;

    date::year_month_day today = date::floor<date::days>(chrono::system_clock::now());
    auto localtime = date::zoned_time<std::chrono::minutes>(
            date::current_zone(),
            static_cast<date::local_days>(today) + chrono::hours{ h } + chrono::minutes{ m });
    print_meeting_times(localtime, users);

}
