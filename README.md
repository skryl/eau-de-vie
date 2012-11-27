# Life, distilled.

Distilling the game of life to perfection. My first clojure project.


# Usage

    Switches           Default  Desc
    --------           -------  ----
    -v, --version               Version of life to run
    -s, --size         50       Size of grid to use
    -g, --generations  1000     Number of generations to simulate
    -p, --print        false    Display the game output

Available algorithm versions

    1 - 2d vector, all calculations done on the fly
    2 - 1d vector, all calculations done on the fly
    3 - 1d vector with precomputed neighbor map

Run 1000 generations of a random 50 x 50 grid using v1 (headless) 
  
    lein run -v 1

Run 1000 generations of a random 50 x 50 grid using v2 and print to swing window

    lein run -v 2 -p

Run 100 generations of a random 20 x 20 grid using v3 and print to terminal

    lein run -v3 -s 20 -g 100 -p text


## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
