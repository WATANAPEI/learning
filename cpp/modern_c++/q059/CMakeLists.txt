#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q059)

add_executable(q059 q059.cpp)

target_compile_options(q059 PUBLIC -Wall)
target_compile_features(q059 PUBLIC cxx_std_17)
