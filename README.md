# cellsociety
## Cosette Goldstein, Georgia Tse, Robert Vann
Empty repository for CellSociety project


In the XML file, the tags must be consolidated on one line to prevent extra whitespace from messing up the parsing.

###Simulation
####Variety of Grid Location Shapes
* We implemented both the square and triangular grid location shapes in our assignment, with each implementation found
in the RectangularGridView class and TriangularGridView class, respectively. Each class extends DisplayGrid, which 
updates the grid that is seen by the user (and is called in the View class). Since the square and triangular shapes of
grid locations are similar (with the same number of neighbors), it was easy to use a majority of the same methods to 
display and update the grid, with the only differing methods relating to drawing the shapes to be displayed (as drawing
a Rectangle object and Polygon object with 3 points that flips include different methods). Additionally, we added Toggle
Buttons to give the user the ability to choose which shape to display before loading the new simulation.

* We did not get a chance to implement the hexagonal grid location shapes, but we discussed how it would be accomplished.
There would need to be a method that draws the hexagonal shape like we wrote for rectangular and triangular. Additionally,
the rows and columns would visually look a little different, and calculating neighbors would require a method that added only
the 6 relevant neighbors for the given shape.

####Variety of Grid Edge Types
* We implemented both the finite and toroidal grid edge types, in a similar way to the shapes above. This was done by editing
the method that got the cell's neighbors (the finite implementation) to include cells on the opposite side of the grid if it
were on the border of the grid so that every cell has a full neighborhood. We changed this in the Grid class by having an 
abstract method, and extended the Grid class to create subclasses called FiniteGrid and ToroidalGrid.

* We did not implement the infinite grid edge type, but a similar method would be used. For an unbounded grid, another subclass
called UnboundedGrid would be a subclass of Grid in the model package. It would have full neighborhoods like toroidal, but 
instead of wrapping around, the grid would just extend outwards. This was difficult to wrap our heads around, as we used arrays
and ArrayLists which are ordered to store cell states, and the fact that what was once at position (0,0) could move to a location
that has negative indices could cause problems. Therefore, there would have to be a dynamic solution as in how to view the scrolled 
grid and store the information in cells that are not necessarily visible.

####Additional Simulations
* We did not have a chance to design these simulations, but they would be modeled primarily after the simulations from sprint 1.
Additional subclasses of the Simulation class in the Model would need to be created just like the other simulations; however, 
these new simulations use locations of cells(known as patches of ground in the descriptions of the simulations) in addition to state 
of the CellObject above it. This would require default values in the resource bundle, and if statements in the controller.

###Configuration
####Error Checking
* To implement error checking, we added try and catch methods to the controller. In the loadFile method, we added specific events 
that would notify the user and stop loading the file if no simulation name were given or invalid state values were given. To check 
for invalid cell states or out of bounds cells, we added try and catch statements to the the XML parser class. Finally, to ensure that 
default values were given when no parameters were specified, we first created a resource files that contained default values for each 
simulation, then we created a method called validate parameters in the controller that went through and filled any empty spots in the 
attribute map.

####Initial Configuration
* We implemented the initial configuration by specific locations and states through an XML file parser (XMLParser class). The file provided
the size of the grid, colors to be represented by each state (in a color map), states for each location in the grid, the name of the 
simulation, and the author in the XML file. The parser interpreted the information, and if something was missing, it threw an exception. 
Otherwise, it put the information into a Packager to be used by the View and Controller.

*We did not implement randomly populating the grid, because sending back the information given by the user in the View to the Controller 
to send to the Model required going through many different classes and we decided that it made the rest of our code more confusing. However,
the implementation would have been that user input in the View (indicated by pushing the toggle button for randomizing the grid) would have
created a random grid based off of colors in the ViewPackager, and the controller would translate the colors of the grid into states to send
back to the model. Then, the model would have control of the grid to update it for the simulation.

####'Styled' Simulations
* We implemented a toggle bar in order to allow the user to style the simulation, which includes choosing the shape of the grid locations,
the edge type, and whether there were grid lines in the visualization. The ToggleBox class was implemented in the View. If we were to add special
colors/shapes/images for the patch states, we would implement the resource bundle to be able to offer a range of options to be represented in
a properties file.

###Visualization
* We implemented a graphical representation of the different populations shown in the grid with an XYChart to create a line graph. The ViewPackager
passed in information about the different colors and populations of different species in the grid. It updates with every generation in the grid,
with the code being in the PopulationGraph class.

* We discovered how to use setOnMouseClicked to register when cells were touched, and we would use this capability to let the user change the state
of the cell by clicking on the shapes in the representation on the screen. Additional code in the controller would be necessary to update the initial
states of the simulations if this capability was used. Similarly, a browser window could pop up and allow users to change parameters of the simulations,
and the information would be sent from the View to the Controller in order to reconstruct the simulation. In order to allow users to store the information
they used to design the initial states and save them, we would need to construct a free hierarchy of nodes, which is the opposite process of the XMLParser.