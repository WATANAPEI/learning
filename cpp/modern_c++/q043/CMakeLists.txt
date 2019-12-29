#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q043)

add_definitions(-DINSTALL=/usr/local/src/date)
include_directories(/usr/local/src/date/include/date)
include_directories(/usr/local/src/curl/include)

add_executable(q043 /usr/local/src/date/src/tz.cpp q043.cpp)
target_link_libraries(q043 PUBLIC curl)

target_compile_options(q043 PUBLIC -Wall)
target_compile_features(q043 PUBLIC cxx_std_17)
