cmake_minimum_required(VERSION 3.4.1)

find_library( # Defines the name of the path variable that stores the
        # location of the NDK library.
        log-lib

        # Specifies the name of the NDK library that
        # CMake needs to locate.
        log )

add_library( # Specifies the name of the library.
             fisher-pole

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             tom.cpp jerry.cpp baseparam.cpp)

target_link_libraries( # Specifies the target library.
        fisher-pole

        # Links the log library to the target library.
        ${log-lib} )