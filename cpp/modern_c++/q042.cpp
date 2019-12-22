#include <iostream>
#include <date/iso_week.h>
#include <date/date.h>

using namespace std;
using namespace date;

int main() {
    int y;
    unsigned int m, d;
    cout << "input year(yyyy):";
    cin >> y;
    cout << "input month(mm):";
    cin >> m;
    cout << "input day(dd):";
    cin >> d;
    if (m < 1 || m > 12 || d < 1|| d > 31) return 0;
    auto const dt = date::sys_days { year{y}/ month{m}/ day{d}};
    auto const s_dt = date::sys_days {year{y}/ month{1}/ day{1}};
    auto const ymd = iso_week::year_weeknum_weekday(dt);
    cout << dt << endl;
    cout << s_dt << endl;
    cout << "day_" << (dt - s_dt).count() + 1 << endl;
    cout << "week_" << static_cast<unsigned int>(ymd.weeknum()) << endl;

}
