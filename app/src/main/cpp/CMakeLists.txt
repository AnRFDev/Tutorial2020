cmake_minimum_required(VERSION 3.4.1)
add_library( # Specifies the name of the library.
             fisher-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             fisher-lib.cpp )

#target_link_libraries( # Specifies the target library.
#        fisher-lib )