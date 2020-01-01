#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q088)
include_directories(/usr/local/src/pngwriter/src)
add_executable(q088 q088.cpp)

target_compile_options(q088 PUBLIC -Wall)
target_compile_features(q088 PUBLIC cxx_std_17)
