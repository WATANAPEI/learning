#include <vector>
#include <random>
#include <array>
#include <iostream>
#include <algorithm>
#include <memory>
#include <chrono>
#include <thread>

using namespace std;

class universe
{
    universe() = delete;
public:
    enum class seed { random, ten_cell_row};

    universe(size_t const width, size_t const height)
        : rows(height), columns(width), grid(width * height), dist(0, 4)
    {
        random_device rd;
        auto seed_data = array<int, mt19937::state_size>{};
        generate(begin(seed_data), end(seed_data), ref(rd));
        seed_seq seq(cbegin(seed_data), cend(seed_data));
        mt.seed(seq);
    }

    void run(seed const s, int const generations, chrono::milliseconds const ms = chrono::milliseconds(100))
    {
        reset();
        initialize(s);
        display();
        int i = 0;
        do
        {
            next_generation();
            display();

            using namespace chrono_literals;
            this_thread::sleep_for(ms);
        }while(i++ < generations || generations == 0);
    }
private:
    void next_generation()
    {
        vector<unsigned char> newgrid(grid.size());

        for(size_t r = 0; r < rows; ++r)
        {
            for(size_t c = 0; c < columns; ++c)
            {
                auto count = count_neighbors(r, c);
                if(cell(c, r) == alive)
                {
                    newgrid[r * columns + c] = (count == 2|| count == 3) ? alive: dead;
                }
                else
                {
                    newgrid[r * columns + c] =  ( count == 3) ? alive: dead;
                }
            }
        }
        grid.swap(newgrid);
    }

    void reset_display() const
    {
#ifdef _WIN32
        system("cls")
#endif
    }

    void display()
    {
        reset_display();
        for(size_t r = 0; r < rows; ++r)
        {
            for(size_t c = 0; c < columns; ++c)
            {
                cout << (cell(c, r) ? '*' : ' ');
            }
            cout << endl;
        }
    }

    void initialize(seed const s)
    {
        if ( s == seed::ten_cell_row)
        {
            for(size_t c = columns / 2 - 5; c < columns / 2 + 5; ++c)
            {
                cell(c, rows/2) = alive;
            }
        }
        else
        {
            for(size_t r = 0; r < rows; ++r)
            {
                for(size_t c = 0; c < columns; ++c)
                {
                    cell(c, r) = dist(mt) == 0 ? alive : dead;
                }
            }
        }
    }
    void reset()
    {
        for(size_t r = 0; r < rows; ++r)
        {
            for(size_t c = 0; c < columns; ++c)
            {
                cell(c, r) = dead;
            }
        }
    }
    int count_alive() const { return 0; }
    template<typename T1, typename... T>
    auto count_alive(T1 s, T... ts) const { return s + count_alive(ts...); }

    int count_neighbors(size_t const row, size_t const col)
    {
        if( row == 0 && col == 0)
            return count_alive(cell(1, 0), cell(1,1), cell(0,1));
        if( row == 0 && col == columns - 1)
            return count_alive(cell(columns - 2, 0), cell(columns - 2,1), cell(columns - 1,1));
        if( row == rows - 1 && col == 0)
            return count_alive(cell(0, rows - 2), cell(1,rows - 2), cell(1,rows - 1));
        if( row == rows - 1 && col == columns - 1)
            return count_alive(cell(columns - 1, rows - 2), cell(columns - 2,rows - 2), cell(columns - 2,rows - 1));
        if( row == 0 && col > 0 && col < columns - 1)
            return count_alive(cell(col - 1, 0), cell(col - 1,1), cell(col,1), cell(col + 1, 1), cell(col + 1, 0));
        if( row == rows - 1 && col > 0 && col < columns - 1)
            return count_alive(cell(col - 1, row), cell(col - 1,row - 1), cell(col,row - 1), cell(col + 1, row - 1), cell(col + 1, row));
        if( col == 0 && row > 0 && row < rows - 1)
            return count_alive(cell(0, row - 1), cell(1,row-1), cell(1, row), cell(1, row+1), cell(0, row+1));
        if( col == columns-1 && row > 0 && row < rows - 1)
            return count_alive(cell(col, row - 1), cell(col-1,row-1), cell(col-1, row), cell(col-1, row+1), cell(col, row+1));

        return count_alive(cell(col-1, row-1), cell(col, row-1),
                            cell(col+1, row-1), cell(col+1, row),
                            cell(col+1, row+1), cell(col, row+1),
                            cell(col-1, row+1), cell(col-1, row));
    }

    unsigned char &cell(size_t const col, size_t const row)
    {
        return grid[row*columns + col];
    }

private:
    size_t const rows;
    size_t const columns;
    vector<unsigned char> grid;
    unsigned char const alive = 1;
    unsigned char const dead = 0;
    uniform_int_distribution<> dist;
    mt19937 mt;
};


int main()
{
    using namespace std::chrono_literals;
    universe u(30, 20);
    u.run(universe::seed::random, 100, 100ms);
}
