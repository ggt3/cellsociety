#Cell Society Design Plan
##Cosette Goldstein, Georgia Tse, and Robert Vann
###Introduction
Our team is trying to write this program in order to make a system that separates what the user sees (the view) and how the model performs, connected by the controller in order to simulate different cell societies with different rules. The primary design goals of the project is to extend in a way that would allow the different types of grids to be easily created without hurting the function of other parts of the code. We want to close the individual parts of the design (Model, View, Controller) so they are not dependent on each other, but they can interact with each other. However, the implementation of the basic grid needs to be abstract enough to create new simulations easily without having to rework the existing simulations. This can be done with separating the rules and the grid so that different types of grids can be made with existing rules or vice versa. For the View, we can easily add or change features on the UI.

###Overview
The program will be divided up in a way that has separated Model, Controller, and View, each of which creates a separate realm of “labor” so to speak. We decided to divide it up into these three parts because we knew that there were specific functions for what the user saw and what actually occurs in the background (i.e. front end and back end respectively), and we wanted these functions to be closed off to the other ones where the methods in one realm can only ask the others for information and not directly modify it. 

*The Model* creates the infrastructure on which the simulation is built on; for example, cell objects and grid objects are defined within the model, as well as a general rules class and the specific logic of each individual simulation. It contains methods that create a grid object, filled with cell objects to be returned in the control. A method in the model would be to apply the rules to the cell objects within the grid. The reason why we have both cell objects and grid object that contains cell objects is to once again, keep certain aspects closed off. For example, a cell should know nothing of its neighbors, only information about itself (AKA its state, but not the state of neighboring cells). However, the grid would know about the current states of the neighbors, in order to evaluate the specific rules of the simulation. The Model will have a factory class that can create cells and grids. Another aspect of the model is the presence of Rules classes. Rules superclass contains overall methods, but is implemented in a way that is flexible to adding new rules in subclasses.

*The Controller* is the brain of the program, commanding model and view to create and display, respectively. Many methods in the Controller actually do the operations that are indicated by the user in the View. The user inputs the data in View by pressing a button or selecting a file to be loaded, and the Controller is in charge of parsing the data, as well as operating when the start, stop/pause, and speed buttons are pressed. Based off of the information from the parsed data, it will call for a grid instance to be created from the Model realm. The states of the current grid as well as the updated grid (after the rules were applied) are stored here. The Controller will pass in the current state of the grid and ask the Model to return a grid of updated state(getGrid()).When data is parsed, the Controller will store the rule type (name) and ask the model to return a grid based on the “seed” information, the locations of the initial cells specified in the XML file. The controller will call getGrid() at every frame speed the user has set. If the user pressed pause for example, the controller changes a “isRunning” boolean that tells itself to stop calling getGrid().
 
We wanted to create *the View* as isolated as possible. Like the Model, it should only create a window, or update a view of a grid, if the controller asks for it. The results of the methods of the View is what the user sees and uses, meaning that displays such as the grid, start/stop/change speed buttons, and text can be displayed and interacted with by the user.The View has its own factory methods such as makeButton or displayText that may only be called at initialization. The View gets updated (aka the user sees changes) after the Controller has called for a new grid and the Controller will call the View to updateView based on the new grid given by the Model.


![Overview](https://duke.box.com/shared/static/6yqs9j6v6z7ar35ml9rtav99i3rm1u1w.jpg)

###User Interface
The user interface is contained within an application window. Within that window is a grid and a string of buttons at the top. Play, Stop, Step, and Load buttons allow the user to set the simulation in motion and load a new simulation. The user can also adjust the speed of the simulation using the arrow buttons. Any error messages or other text will be displayed below the graph. The buttons will trigger calls to the controller.

![Cell Society UI](https://duke.box.com/shared/static/nkimaqcwmcjtttb10k5sdynqimpv7q69.jpeg)

###Design Details 
Some of the actual implementation has been considered in the overview, but to elaborate further we can consider cases of what our program might handle. From the beginning: the main will initialize a controller and the Controller will initialize a View (a window with user options). The user can load a file which is then passed to the Controller to parse the data. It will return an initial state of the grid (by passing in parameters and demanding from Model a grid) and call View to generate what the user sees. Then depending on if Play was pressed, the Controller would call on Model again to generate the next state. This cycle continues until Pause is pressed in the View. The speed at which the controller calls on the model can also be modified in the View. The speed will be an instance variable in the Controller. The abstraction of “speed” will just be how often getNextGridState() or updateView() is called. 

Creating cell objects (class) allows us to add features specific to that object easily such as a health status. In our classes and methods, we assume that the grid will be made of squares. A grid should be able to return a cell’s neighbors. Because we create new instances of a grid object, we assume that there doesn’t need to be a changeCellState() method because each time the Controller calls the Model to return a grid, the model will create a new grid and populate it will new cells that have the state determined by the rules.


###Design Considerations 
One of the most important problems we faced was how to organize the visualization and the simulation of the program, and we decided to use the Model View Controller design because it clearly separated the front and back end in a way that was extendable and fit conceptually. This design allows us to divide up the jobs of displaying the simulations, calculating the different states and provides a seamless connection between the two. Separating the implementation into these three parts also makes it easier to add features to the user interface or new rules to the simulation later, and ensuring that minimal parts of the code would be affected.

An additional design consideration was how to represent cells in the model. We wanted to create classes that would be easy to manipulate but also easily extendible. We considered the possibility of having a grid of a type of rule, or having a cell based on the type of rule. Currently we have the grid interacting with the rules, but not directly extending or modifying the rules. There will be methods in the rules class that will take in a grid and return a cell state.

Because the rules of the simulation relied on the states of a cell’s neighbor, we considered how we would store or retrieve the data of those cells. Originally we had a cell that contained information about it’s neighbors, but then we thought that the cell doesn’t need to know about it’s neighbors, or that a cell only has neighbors when it is placed into a grid, so this information should be determined at the grid level. This way, if the number of neighbors changed for a cell, the grid would handle neighbor calculations. 

An important design consideration was how to store the states of the grid as the simulation progressed. We decided to store different states of the model in the Controller. The Controller contains the different stages of the grid and calls on the model to generate a new state from any given current state. This allows us to have greater control over the simulation as the controller can be stopped, sped up, or slowed down easily.

###Team Responsibilities
Below is a general idea of how the project might be divided. We plan to pair program as much as possible.

 - Formatting the XML File - Robert, Georgia
 - Parsing XML File - Robert, Georgia
 - Creating User Interface - Cosette
 - Setting up timeline - Georgia
 - Implementing specific rule logic - Robert
 - Creating Controller Methods
 - Initializing model (Factory methods, cell classes, object classes) - Cosette


