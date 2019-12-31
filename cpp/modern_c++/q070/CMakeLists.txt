#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q070)

add_executable(q070 q070.cpp)

target_compile_options(q070 PUBLIC -Wall PUBLIC -g)
target_compile_features(q070 PUBLIC cxx_std_17)
