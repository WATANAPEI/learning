#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q064)

find_package(Threads REQUIRED)
add_executable(q064 q064.cpp)

target_compile_options(q064 PUBLIC -Wall)
target_compile_features(q064 PUBLIC cxx_std_17)
target_link_libraries(q064 Threads::Threads)
