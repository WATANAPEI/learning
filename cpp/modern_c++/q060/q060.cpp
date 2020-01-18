#include <iostream>
#include <chrono>
#include <thread>

using namespace std;

class Cell
{
    bool state;
public:
    Cell()
        :state(false) {}
    void SetAlive() {
        state = true;
    }
    void SetDead() {
        state = false;
    }
    bool GetState() const
    {
        return state;
    }
};


class Cells
{
    static const size_t row = 10;
    static const size_t col = 5;
    Cell c[row][col];

public:
    Cells(size_t row = 10, size_t col = 5)
    {
        //TODO allocate dynamic memory
    }
    void SetCellAlive(int x, int y)
    {
        c[x][y].SetAlive();
    }
    void SetCellDead(int x, int y)
    {
        c[x][y].SetDead();
    }
    int AliveNeighborNum(int x, int y) const
    {
        int checkNeighborNum = 8;
        int dx[checkNeighborNum] = {0, 1, 1, 1, 0, -1, -1, -1};
        int dy[checkNeighborNum] = {1, 1, 0, -1, -1, -1, 0, 1};
        int result = 0;
        for(int i = 0; i < checkNeighborNum; ++i)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx > (int)row-1 || ny > (int)col-1)
                continue;
            if(c[nx][ny].GetState() == true)
                ++result;
        }
        //cout << "x: " << x << ", y: " << y << " has " << result << " alive cells" << endl;
        return result;
    }
    void PassNextGen()
    {
        for(size_t i = 0; i < row; ++i)
        {
            for(size_t j = 0; j < col; ++j)
            {
                int n = AliveNeighborNum(i,j);
                if(c[i][j].GetState() == true)
                {
                    if(n < 2)
                        SetCellDead(i,j);
                    else if(n == 2 || n == 3)
                        SetCellAlive(i,j);
                    else if (n == 4)
                        SetCellDead(i,j);
                } else {
                    if(n == 3)
                        SetCellAlive(i,j);
                }
            }
        }
    }


    void PrintCells()
    {
        for(size_t i = 0; i < row; ++i)
        {
            for(size_t j = 0; j < col; ++j)
            {
                cout << c[i][j].GetState() << " ";
            }
            cout << endl;
        }
        cout << "******" << endl;
    }


};


int main()
{
    Cells c;
    c.SetCellAlive(1,1);
    c.SetCellAlive(1,2);
    c.SetCellAlive(1,3);
    c.SetCellAlive(5,2);
    cout << "Initial State" << endl;
    c.PrintCells();

    cout << "Start." << endl;
    for(int i = 0; i< 3; ++i)
    {
        c.PassNextGen();
        c.PrintCells();
        this_thread::sleep_for(chrono::milliseconds(500));
    }
    cout << "Finished." << endl;
}
