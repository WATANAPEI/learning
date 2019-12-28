cmake_minimum_required (VERSION 3.7.0)
project(q043)

add_definitions(-DINSTALL=/usr/local/src/date)
include_directories(/usr/local/src/date/include/date)
include_directories(/usr/local/src/date/include)
include_directories(/usr/local/src/curl/include/curl)
include_directories(/usr/local/src/curl/include)


# set(CMAKE_TOOLCHAIN_FILE /usr/local/src/vcpkg/scripts/buildsystems/vcpkg.cmake)

add_executable(q043 /usr/local/src/date/src/tz.cpp q043.cpp)
find_package(CURL)
if(CURL_FOUND)
    include_directories(${CURL_INCLUDE_DIRS})
    target_link_libraries(q043 ${CURL_LIBRARIES})
endif(CURL_FOUND)
# target_link_libraries(q043 PUBLIC curl)

target_compile_options(q043 PUBLIC -Wall)
target_compile_features(q043 PUBLIC cxx_std_17)
