#include <iostream>
#include <vector>
#include <sqlite_modern_cpp.h>

using namespace std;

struct casting_role
{
    string actor;
    string role;
};

struct movie
{
    unsigned int id;
    string title;
    int year;
    unsigned int length;
    vector<casting_role> cast;
    vector<string> directors;
    vector<string> writers;
};

using movie_list = vector<movie>;

void print_movies(movie const &m)
{
    cout << "[" << m.id << "]" << m.title << " (" << m.year << ") " << m.length << "min" << endl;
    cout << " directed by: ";
    for(auto const &d : m.directors) cout << d << ", ";
    cout << endl;
    cout << " written by: ";
    for(auto const &w : m.writers) cout << w << ", ";
    cout << endl;
    cout << " cast: ";
    for(auto const &r : m.cast)
        cout << r.actor << " (" << r.role << "),";
    cout << endl << endl;
}

vector<string> get_directors(sqlite3_int64 const movie_id, sqlite::database &db)
{
    vector<string> result;
    db << R"( select p.name from directors as d
                join persons as p on d.personid = p.rowid
                where d.movieid = ?;)"
        << movie_id
        >> [&result](string const name)
        {
            result.emplace_back(name);
        };
    return result;
}

vector<string> get_writers(sqlite3_int64 const movie_id, sqlite::database &db)
{
    vector<string> result;
    db << R"( select p.name from writers as w
                join persons as p on w.personid = p.rowid
                where w.movieid = ?;)"
        << movie_id
        >> [&result](string const name)
        {
            result.emplace_back(name);
        };
    return result;
}

vector<casting_role> get_cast(sqlite3_int64 const movie_id, sqlite::database &db)
{
    vector<casting_role> result;
    db << R"( select p.name, c.role from casting as c
                join persons as p on c.personid = p.rowid
                where c.movieid = ?;)"
        << movie_id
        >> [&result](string const name, string role)
        {
            result.emplace_back(casting_role{name, role});
        };
    return result;
}

movie_list get_movies(sqlite::database &db)
{
    movie_list movies;

    db << R"(select rowid, * from movies;)"
        >> [&movies, &db](sqlite3_int64 const rowid, string const &title, int const year, int const length)
        {
            movies.emplace_back(movie{
                    static_cast<unsigned int>(rowid),
                    title,
                    year,
                    static_cast<unsigned int>(length),
                    get_cast(rowid, db),
                    get_directors(rowid, db),
                    get_writers(rowid, db)
                    });
        };
    return movies;
}

int main()
{
    try
    {
        sqlite::database db(R"(cppchallenger85.db)");
        auto const movies = get_movies(db);
        for ( auto const &m : movies)
            print_movies(m);
    }
    catch (sqlite::sqlite_exception const &e)
    {
        cerr << e.get_code() << ": " << e.what() << " during " << e.get_sql() << endl;
    }
    catch (std::exception const &e)
    {
        cerr << e.what() << endl;
    }

}
