# utilitysoftwareservices
Mars Explorer Simulator(Simple Version)
===================

Description
-----------

- The application is a simulation of a mars explorer moving on a square tabletop,
  of dimensions 5 units x 5 units.
- The explorer is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement that would result in the
  explorer falling from the table must be prevented, however further valid
  movement commands must still be allowed.
- The explorer only can move to the sorrounding units vertically or horizontally.

Create an application that can read in commands of the following (textual) form:

PLACE X,Y
MOVE X,Y
REPORT

To run project without IDE

Open File ~src/mars/explorer/simulator/MarsExplorerSimulator.java
Remove package mars.explorer.simulator;

Open Command Prompt 

cd ~src/mars/explorer/simulator/

javac MarsExplorerSimulator.java

java MarsExplorerSimulator
