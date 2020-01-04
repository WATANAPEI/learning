#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q065)

find_package(Threads REQUIRED)
add_executable(q065 q065.cpp)

target_compile_options(q065 PUBLIC -Wall)
target_compile_features(q065 PUBLIC cxx_std_17)
target_link_libraries(q065 Threads::Threads)

project(q065_ex)

find_package(Threads REQUIRED)
add_executable(q065_ex q065_ex.cpp)

target_compile_options(q065_ex PUBLIC -Wall)
target_compile_features(q065_ex PUBLIC cxx_std_17)
target_link_libraries(q065_ex Threads::Threads)
