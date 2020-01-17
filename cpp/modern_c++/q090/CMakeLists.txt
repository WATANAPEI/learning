#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q090_ex)

add_executable(q090_ex q090_ex.cpp)

target_compile_options(q090_ex PUBLIC -Wall)
target_compile_features(q090_ex PUBLIC cxx_std_17)
