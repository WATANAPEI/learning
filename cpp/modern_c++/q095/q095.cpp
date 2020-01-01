#define ASIO_STANDALONE
#include <iostream>
#include "asio.hpp"
#include <vector>

using namespace std;

vector<string> get_ip_address(string const &hostname)
{
    vector<string> ips;

    try{
        asio::io_context context;
        asio::ip::tcp::resolver resolver(context);
        auto endpoints = resolver.resolve(asio::ip::tcp::v4(), hostname.c_str(), "");

        for(auto const &e: endpoints) {
            ips.push_back(e.endpoint().address().to_string());
        }
    } catch(exception const&e) {
        cerr << "exception: " << e.what() << endl;
    }

    return ips;
}

int main() {
    auto ips = get_ip_address("packtpub.com");
    for(auto const &ip: ips)
        cout << ip << endl;
}
