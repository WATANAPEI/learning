#include <iostream>
#include "date/date.h"
#include <chrono>
#include <vector>
#include <sstream>
#include <iomanip>

using namespace std;

int main() {
    /*
    auto today = date::year_month_day(date::floor<date::days>(chrono::system_clock::now()));
    cout << today << endl;
    auto year = today.year();
    auto month = today.month();
    auto lastday = date::year_month_day{year/month/date::last};
    cout << lastday << endl;
    auto tomorrow = date::year_month_day{year/month/4};
    cout << tomorrow << endl;
    cout << lastday.day() << endl;
    for (unsigned i = 1; i < static_cast<unsigned>(lastday.day());i++) {
        cout << i << endl;
    }
    */
    auto input_y = date::year(2020);
    auto input_m = date::month(2);
    auto lastday = date::year_month_day{input_y/input_m/date::last}.day();
    vector<string> day_vector;
    cout << " Mon Tue Wed Thu Fri Sat Sun\n";
    auto first_date = date::year_month_weekday{input_y/input_m/1}.weekday();
    stringstream sstr;
    sstr << setw(4) << right;
    if(first_date == date::Monday) {
        ;
    } else if(first_date == date::Tuesday) {
        sstr << string("    ");
    } else if(first_date == date::Wednesday) {
        sstr << string("        ");
    } else if(first_date == date::Thursday) {
        sstr << string("            ");
    } else if(first_date == date::Friday) {
        sstr << string("                ");
    } else if(first_date == date::Saturday) {
        sstr << string("                    ");
    } else if(first_date == date::Sunday) {
        sstr << string("                        ");
    }
    for(unsigned i = 1; i < static_cast<unsigned>(lastday) + 1; i++) {
        sstr << setw(4) << right << i;
        if(date::weekday(input_y/input_m/i) == date::Sunday) {
            sstr << endl;
        }
    }
    cout << sstr.str() << endl;



}
