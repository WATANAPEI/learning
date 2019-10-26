#include <iostream>
#include <vector>

using namespace std;

template <typename T>
struct myque {
private:
    vector<T> data;

public:
    myque() {};

    void push(T const & e) {
        data.push_back(e);
        size_t my_idx = data.size() - 1;
        if ( my_idx > 0) {
            size_t p_idx = (my_idx - 1) / 2;
            while(data.at(p_idx) < data.at(my_idx)) {
                swap(data.at(p_idx), data.at(my_idx));
                my_idx = p_idx;
            }
        }
    }
    void pop() {
        data.erase(begin(data));
        *begin(data) = *end(data);
        size_t my_idx = 0;
        while((my_idx + 1) * 2 < data.size()) {
            T const & lhs = data.at(2 * my_idx + 1);
            T const & rhs = data.at(2 * my_idx + 2);
            if(lhs > rhs) {
                swap(data.at(my_idx), data.at(2 * my_idx + 1));
                my_idx = 2 * my_idx + 1;
            } else {
                swap(data.at(my_idx), data.at(2 * my_idx + 2 ));
                my_idx = 2 * my_idx + 2;
            }
        }

    }
    T const & top() const {
        return *cbegin(data);
    }
    T & top() {
        return *begin(data);
    }
    size_t size() {
        return data.size();
    }
    bool empty() {
        return data.empty();
    }


};

int main () {
    myque<int> que;
    que.push(2);
    que.push(5);
    que.push(10);
    cout << que.top() << endl;
    que.pop();
    cout << que.top() << endl;
    que.push(20);
    cout << que.top() << endl;
    que.push(5);
    cout << que.top() << endl;

}
