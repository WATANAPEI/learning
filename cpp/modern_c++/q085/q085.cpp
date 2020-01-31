#include <iostream>
#include <sqlite_modern_cpp.h>

using namespace sqlite;
using namespace std;

int main()
{
    try{
        database db("dbfile.db");

        db << "drop table if exists movies";
        db << "drop table if exists persons";
        db << "drop table if exists directors";
        db << "drop table if exists writers";
        db << "drop table if exists casting";

        db <<
        "create table if not exists movies("
        " _id integer primary key autoincrement, title string, year interger, length interger);";
        cout << "created movies table" << endl;

        db <<
        "create table if not exists persons("
        " _id integer primary key autoincrement, name string);";
        cout << "created persons table" << endl;

        db <<
        "create table if not exists directors("
        " _id integer primary key autoincrement, movieid integer, personid interger);";
        cout << "created directors table" << endl;

        db <<
        "create table if not exists writers("
        " _id integer primary key autoincrement, movieid integer, personid interger);";
        cout << "created writers table" << endl;

        db <<
        "create table if not exists casting("
        " _id integer primary key autoincrement, movieid integer, personid interger, role string);";
        cout << "created casting table" << endl;

        string title("forest gump");
        int year = 2000;
        int length = 120;
        db << "insert into movies(title, year, length) values (?, ?, ?);"
            << title
            << year
            << length;
        int movieId = db.last_insert_rowid();
        cout << "movieId: " << movieId << endl;

        string personName("Lucus");

        db << "insert into persons(name) values (?);"
            << personName;
        int personId = db.last_insert_rowid();
        cout << "personId: " << personId << endl;

        db << "insert into directors(movieId, personId) values (?, ?);"
            << movieId
            << personId;
        cout << "inserted" << endl;

        db << "insert into writers(movieId, personId) values (?, ?);"
            << movieId
            << personId;
        cout << "inserted" << endl;

        string role("cameo");
        db << "insert into casting(movieId, personId, role) values (?, ?, ?);"
            << movieId
            << personId
            << role;
        cout << "inserted" << endl;

        db << "select title from movies;"
            >> [&](string title) {
                cout << "title: " << title << endl;
            };
    }
    catch(exception &e)
    {
        cout << e.what() << endl;
    }
}
