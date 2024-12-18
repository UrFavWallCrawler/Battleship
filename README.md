# Online Battleship (networking exercise)

This project implements an online, client-server based version
of the game of Battleship.
All game logic (the pregame waiting period before another player
joins, the phase where both players place their ships, and the main
turn-based shooting phase) is handled by the server; the client
application acts only as an interface, sending and recieving messages
to and from the server, and visualizing the replies in a human readable
way (the two grids that a player sees when playing battleship).

### Problems

This project was developed as part of an exercise in our TEPSIT class
at ITIS Marconi of Pontedera; as such there was a strict deadlline on
finishing the project.
Unfortunately, due to unforseen circumstances, we were unable to make
a working project in the time given; as things stand now, the code does
not reflect our initial ideas and does not implement the protocol descrbed
in the documentation; while the client application, and the server application do
compile, all the application don't collaborate eachother.
