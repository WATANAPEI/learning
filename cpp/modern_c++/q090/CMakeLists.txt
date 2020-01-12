#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q090)

add_executable(q090 q090.cpp)

target_compile_options(q090 PUBLIC -Wall)
target_compile_features(q090 PUBLIC cxx_std_17)
