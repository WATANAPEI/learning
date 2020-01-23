#include <iostream>
#include <cassert>
#include <chrono>
#include <ctime>
#include <iomanip>
#include <vector>
#include <memory>

using namespace std;

class Message
{
public:
    enum Type
    {
        Align,
        PushBack,
        PopBack,
        Clear
    };

    Message(Type type, size_t index)
        : type(type), p(chrono::system_clock::now()), index(index)
    {
        time_t t = chrono::system_clock::to_time_t(p);
        lt = localtime(&t);
    }
    tm *GetLocalTime()
    {
        return lt;
    }
    Type GetType()
    {
        return type;
    }
    size_t GetIndex()
    {
        return index;
    }

private:
    Type type;
    chrono::system_clock::time_point p;
    tm *lt;
    size_t index;
};
ostream& operator<<(ostream& os, Message::Type t)
{
    if(t == Message::Type::Align)
    {
        os << "Align";
    } else if (t == Message::Type::PushBack)
    {
        os << "PushBack";
    } else if( t== Message::Type::PopBack)
    {
        os << "PopBack";
    } else if ( t == Message::Type::Clear)
    {
        os << "Clear";
    }
    return os;
}

class Observer
{
public:
    Observer()
        :name("anonymus") {};
    explicit Observer(string name)
        :name(name) {}
    void notifiy(Message &m)
    {
        cout << "From: " << name << endl;
        cout << "Message Type: " << m.GetType() << endl;
        cout << "Time: " << put_time(m.GetLocalTime(), "%c") << endl;
        cout << "Index: " << m.GetIndex() << endl;
    }
private:
    string name;

};

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
        unique_ptr<Message> m(new Message(Message::PushBack, last));
        notifyAll(m.get());

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
            unique_ptr<Message> m(new Message(Message::Clear, i));
            notifyAll(m.get());
        }
    }
    T pop_back()
    {
        unique_ptr<Message> m(new Message(Message::PopBack, last));
        notifyAll(m.get());
        return array[--last];
    }

    T operator [](size_t n)
    {
        return array[n];
    }
    void subscribe(Observer o)
    {
        observerList.push_back(o);
    }
    void notifyAll(Message *m)
    {
        for(auto e: observerList)
        {
            e.notifiy(*m);
        }
    }

private:
    size_t _size;
    size_t last;
    T *array;
    vector<Observer> observerList;

};

int main()
{
    {
        PublishVector<int> pv;
        Observer o;
        pv.subscribe(o);
        pv.push_back(10);
        pv.push_back(20);
        cout << pv[0] << endl;
        assert(pv.size() == 2);
        assert(pv.pop_back() == 20);
    }

    {
        PublishVector<double> pv2(20);
        Observer o1("observer 1");
        Observer o2("observer 2");
        assert(pv2.empty() == true);
        assert(pv2.size() == 0);
        pv2.push_back(30);
        pv2.subscribe(o1);
        pv2.push_back(40);
        pv2.subscribe(o2);
        pv2.push_back(50);
    }



}
