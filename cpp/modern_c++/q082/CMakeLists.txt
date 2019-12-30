#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q082)

# target_include_directories(q082 PUBLIC /usr/local/src/freetype-2.10.1/include)
include_directories(/usr/local/src/freetype-2.10.1/include)
# target_include_directories(q082 PUBLIC /usr/local/src/pngwriter/src)
include_directories(/usr/local/src/pngwriter/src)
add_executable(q082 q082.cpp)
link_directories(/usr/local/src/pngwriter-build)
target_link_libraries(q082 PUBLIC PNGwriter)
target_link_libraries(q082 PUBLIC freetype)
target_link_libraries(q082 PUBLIC png16)
target_link_libraries(q082 PUBLIC z)

target_compile_options(q082 PUBLIC -Wall)
target_compile_features(q082 PUBLIC cxx_std_17)
