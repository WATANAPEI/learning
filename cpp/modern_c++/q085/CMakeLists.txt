#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q085)
project(q085_ex)

include_directories(/usr/local/src/sqlite-amalgamation-3310100)
include_directories(/usr/local/src/sqlite_modern_cpp/hdr)
add_executable(q085 q085.cpp)
add_executable(q085_ex q085_ex.cpp)

target_compile_options(q085 PUBLIC -Wall PUBLIC -g)
target_compile_features(q085 PUBLIC cxx_std_17)
target_link_libraries(q085 PUBLIC sqlite3)

target_compile_options(q085_ex PUBLIC -Wall PUBLIC -g)
target_compile_features(q085_ex PUBLIC cxx_std_17)
target_link_libraries(q085_ex PUBLIC sqlite3)
