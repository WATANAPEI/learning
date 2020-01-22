#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q071)

add_executable(q071 q071.cpp)

target_compile_options(q071 PUBLIC -Wall -pthread)
target_compile_features(q071 PUBLIC cxx_std_17)
