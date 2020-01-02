#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q044)

add_executable(q044 q044.cpp)

target_compile_options(q044 PUBLIC -Wall -pthread)
target_compile_features(q044 PUBLIC cxx_std_17)
