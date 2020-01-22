#include <iostream>
#include <cassert>

using namespace std;

template <typename T>
class PublishVector
{
public:
    PublishVector()
        : _size(10), last(0)
    {
        array = new T[10];
    }
    PublishVector(size_t n)
        : _size(n), last(0)
    {
        array = new T[n];
        last = 0;
    }
    ~PublishVector() {
        delete array;
    }
    void push_back(T e)
    {
        array[last] = e;
        ++last;
        if(_size == last)
        {
            T tmp[last];
            for(size_t i = 0; i < last; ++i)
            {
                tmp[i] = array[i];
            }
            delete array;
            array = new T[last*2];
            for(size_t i = 0; i < last; ++i)
            {
                array[i] = tmp[i];
            }
        }

    }
    size_t size()
    {
        return last;
    }
    bool empty()
    {
        return last == 0;
    }
    void clear()
    {
        for(int i = 0; i < array.size(); ++i)
        {
            array[i].remove();
        }
    }
    T pop_back()
    {
        return array[--last];
    }

    T operator [](size_t n)
    {
        return array[n];
    }


private:
    size_t _size;
    size_t last;
    T *array;

};

int main()
{
    PublishVector<int> pv;
    pv.push_back(10);
    pv.push_back(20);
    cout << pv[0] << endl;
    assert(pv.size() == 2);
    assert(pv.pop_back() == 20);

    PublishVector<double> pv2(20);
    assert(pv2.empty() == true);
    assert(pv2.size() == 0);


}
