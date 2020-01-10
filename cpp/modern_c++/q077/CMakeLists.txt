#check ldconfig if something goes wrong
# ex. update linker search path with ldconfig
cmake_minimum_required (VERSION 3.7.0)
project(q077)
add_executable(q077 q077.cpp)
target_include_directories(q077 PRIVATE /usr/local/src/pdfwriter)
target_include_directories(q077 PRIVATE /usr/local/src/pdfwriter/FreeType/include)

target_link_libraries(q077 PRIVATE PDFWriter)
target_link_libraries(q077 PRIVATE freetype)
target_link_libraries(q077 PRIVATE jpeg)
target_link_libraries(q077 PRIVATE png)
target_link_libraries(q077 PRIVATE tiff)
target_link_libraries(q077 PRIVATE z)
target_link_libraries(q077 PRIVATE libLibAesgm.a)


target_compile_options(q077 PUBLIC -Wall)
target_compile_features(q077 PUBLIC cxx_std_17)
