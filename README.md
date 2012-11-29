# Life, distilled.

Distilling the game of life to perfection. My first clojure project.


# Usage

    Switches           Default  Desc
    --------           -------  ----
    -v, --version               Version of life to run
    -s, --size         50       Size of grid to use
    -g, --generations  1000     Number of generations to simulate
    -p, --print        false    Display the game output

## Algorithms

    Version 1 - 2d vector, all calculations done on the fly
    - Complexity:  O(n), n = grid size
    - Benchmark:   45ms / 100 x 100

    Version 2 - 1d vector, all calculations done on the fly
    - Complexity:  O(n), n = grid size
    - Benchmark:   15ms / 100 x 100

    Version 3 - 1d vector with precomputed neighbor coordinates
    - Complexity:  O(n), n = grid size
    - Benchmark:   5ms / 100 x 100

    Version 4 - sparse grid, all calculations done on the fly
    - Complexity:  O(n), n = number of live cells
    - Benchmark:   0 ~ 5ms / 100 x 100

    Version 5 - sparse grid with precomputed neighbor coordinates
    - Complexity:  O(n), n = number of live cells
    - Benchmark:   0 ~ 4ms / 100 x 100

    Version 6 - hashlife variation (incomplete)
    - Complexity:  ?
    - Benchmark:   ?

# Examples

Run 1000 generations of a random 50 x 50 grid using v1 (headless) 
  
    lein run -v 1

Run 1000 generations of a random 50 x 50 grid using v2 and print to swing window

    lein run -v 2 -p

Run 100 generations of a random 20 x 20 grid using v3 and print to terminal

    lein run -v 3 -s 20 -g 100 -p text


## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
