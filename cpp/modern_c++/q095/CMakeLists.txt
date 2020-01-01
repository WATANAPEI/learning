#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q095)

find_package(Threads REQUIRED)
include_directories(/usr/local/src/asio-1.12.2/include)
add_executable(q095 q095.cpp)

target_compile_options(q095 PUBLIC -Wall -pthread)
target_compile_features(q095 PUBLIC cxx_std_17)
target_link_libraries(q095 Threads::Threads)
