# Conway's Game of Life
    This file contains code for Conway's Game of Life.
    Functions define the rules for a system of cells that
    are contained in a grid, where cells can live and die.
    This allows for wonderful patterns to be formed.

    As explained by Robert Heaton on 
    https://robertheaton.com/2018/07/20/project-2-game-of-life/,
    "It is played on a 2-D grid. Each square in the grid contains a cell, 
    and each cell starts 
    the game as either "alive" or "dead". Play proceeds in rounds. During each 
    round, each cell 
    looks at its 8 immediate neighbors and counts up the number of them that 
    are currently alive.
    The cell then updates its own liveness according to 4 rules:

    1. Any live cell with 0 or 1 live neighbors becomes dead, because of 
    underpopulation
    2. Any live cell with 2 or 3 live neighbors stays alive, because its 
    neighborhood is just right
    3. Any live cell with more than 3 live neighbors becomes dead, because of 
    overpopulation
    4. Any dead cell with exactly 3 live neighbors becomes alive, by 
    reproduction"

    This code is designed to run in the OS terminal.

## Getting Started
### Prerequisites
### Installing
## Running the tests
### Break down into end to end tests
### And coding style tests
## Deployment
## Built With
## Contributing
## Versioning
## Authors
* **Isaac Cornelius** 
* **[Gershon Fosu](https://github.com/SZOKOZ)**
## License
## Acknowledgements
Thanks to Gershon for heping me with the copyState function :)
