#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q066)

add_executable(q066 q066.cpp)

target_compile_options(q066 PUBLIC -Wall)
target_compile_features(q066 PUBLIC cxx_std_17)
