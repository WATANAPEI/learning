#include <iostream>
#include <sqlite_modern_cpp.h>

using namespace sqlite;
using namespace std;

class Movie
{
public:
    Movie(){}
    Movie(string title, int year, int length)
        : title(title), year(year), length(length){}
    string getTitle()
    {
        return title;
    }
    int getYear()
    {
        return year;
    }
    int getLength()
    {
        return length;
    }
    void setTitle(string t)
    {
        title = t;
    }
    void setYear(int y)
    {
        year = y;
    }
    void setLength(int l)
    {
        length = l;
    }
private:
    int rowId;
    string title;
    int year;
    int length;
};
ostream& operator<<(ostream& os, Movie &m)
{
    os << "Title: " << m.getTitle() << ", Year: " << m.getYear() << ", Length: " << m.getLength() << endl;
    return os;
}



class Mydb
{
public:
    Mydb(string dbname)
        : db(dbname)
    {}
    void init()
    {
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

    }

    int insert_movies(string title, int year, int length)
    {
        db << "insert into movies(title, year, length) values (?, ?, ?);"
            << title
            << year
            << length;
        int movieId = db.last_insert_rowid();
        cout << "movieId: " << movieId << endl;
        return movieId;
    }

    int insert_persons(string personName)
    {
        db << "insert into persons(name) values (?);"
            << personName;
        int personId = db.last_insert_rowid();
        cout << "personId: " << personId << endl;
        return personId;
    }

    int insert_directors(int movieId, int personId)
    {
        db << "insert into directors(movieId, personId) values (?, ?);"
            << movieId
            << personId;
        int directorId = db.last_insert_rowid();
        cout << "directorId: " << directorId << endl;
        return directorId;
    }

    int insert_writers(int movieId, int personId)
    {
        db << "insert into writers(movieId, personId) values (?, ?);"
            << movieId
            << personId;
        int writerId = db.last_insert_rowid();
        cout << "writerId: " << writerId << endl;
        return writerId;
    }

    int insert_casting(int movieId, int personId, string role)
    {
        db << "insert into casting(movieId, personId, role) values (?, ?, ?);"
            << movieId
            << personId
            << role;
        int castingId = db.last_insert_rowid();
        cout << "castingId: " << castingId << endl;
        return castingId;
    }

    Movie selectMovieById(int movieId)
    {
        Movie m;
        db << "select title, year, length from movies where _id = ?;"
            << movieId
            >> [&](string title, int year, int length) {
                m.setTitle(title);
                m.setYear(year);
                m.setLength(length);
            };
        return m;
    }

private:
    database db;
};

int main()
{
    try{
        Mydb db("dbfile.db");

        db.init();
        string title("forest gump");
        int year = 2000;
        int length = 120;
        int movieId = db.insert_movies(title, year, length);
        Movie m = db.selectMovieById(movieId);
        cout << m << endl;

    }
    catch(exception &e)
    {
        cout << e.what() << endl;
    }
}
