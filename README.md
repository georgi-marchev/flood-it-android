This is a clone of the popular strategy mage Flood-It.

The backend of the game is a fork of https://github.com/georgi-marchev/flood-it.

This repo is an implementation of the game Flood-It, written as part of a university course on open
source mobile applications.

Start: The flood begins in the top-left corner of the board.
Choose a color: Select one of the colors available in any of the cells of the board.
Flood: The chosen color will spread to the adjacent area that is already part of your flood, as long
as that area is the same color as your choice. The flood will expand to include all connected cells
of that color, increasing the size of your flood zone.
Repeat: Continue to select colors to flood more of the grid, expanding your single-color area.
Goal: Flood the entire grid with a single color in as few moves as possible.