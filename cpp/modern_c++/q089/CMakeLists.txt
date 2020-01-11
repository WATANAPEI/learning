#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q089)

add_executable(q089 q089.cpp)

target_compile_options(q089 PUBLIC -Wall)
target_compile_features(q089 PUBLIC cxx_std_17)
