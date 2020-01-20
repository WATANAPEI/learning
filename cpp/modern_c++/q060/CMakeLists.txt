#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q060)
add_executable(q060 q060.cpp)

target_compile_options(q060 PUBLIC -Wall)
target_compile_features(q060 PUBLIC cxx_std_17)

project(q060_ex)
add_executable(q060_ex q060_ex.cpp)

target_compile_options(q060_ex PUBLIC -Wall)
target_compile_features(q060_ex PUBLIC cxx_std_17)
