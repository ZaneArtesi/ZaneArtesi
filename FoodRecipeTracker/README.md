The following is a program created as an assignment for university which utilises the food-database-api found at https://developer.edamam.com/food-database-api
to gather input data and the pastebin-api found at https://pastebin.com/doc_api to output data to.

The program allows you to search for food via the search panel and track the nutrients of the foods currently added, see below on how each aspect of the GUI works.

***

Running the Program:

    - To run in dummy version run gradle with the command run --args="offline offline" with the first offline being for input and second for output
    - The dummy version refers to no connection with the API's and will utilise dummy data to demonstrtate how the program should work
    - To run regularly run with run --args="online online" or by deafault gradle run will be online


Added Features:

    Music / Theme Song:
        - The theme song will be playing automatically on launch
        - Press toggle music button in top left to turn it on / off
        - Reference to song - https://pixabay.com/music/beautiful-plays-just-relax-11157
        - The song lasts 2:40, when finished it will automatically loop
        - If you stop the song and start it again, it will always start from the beginning

    Dark Mode:
        - The theme will be light automatically
        - To switch between modes press Change Lighting button in the top right
        - All pop outs will be dark with light writing in dark mode


Core Features:

    Search Function:
        - To begin, search your desired food in the top search function
        - Click cubmit button when you are happy with what you have entered
        - This should then populate the field under "Select a food from your search result"
        - To know when this function has finished running, the combo box will expand when filled - when finished it will return to the smaller size


    Choosing a specific type of field:
        - Once you have seached a food, this ComboBox will be populated with the search results (you will see it expand in size)
        - Choose the specific type of food you like then click submit
        - If no element is selected it will not do anything - (planning to implement an alert box)

    Choosing the Measure:
        - After doing the above a new screen will pop out
        - The top is populated with the base info of the type of food you choose
        - Choose a measurement from the ComboBox and then enter in the quantity (how much of that measurement you want) and leave a note (optional)
        - Click submit, this will popuate the bottom of the pop out window with all the information of what you entered - if you do not enter valid number it wont do anything
        - If you would like to add this to your actual list click Add Item on the right, if not click cancel

    Current Added Items:
        - Once you add an item, this field will be populated with your added foods

    Set Max:
        - Setting a max can only be done once one food has been added and not before
        - Choose the nutrient and the amount then click submit
        - This links with generate graph, if you would like to set a benchmark for a certain nutrient you can check to see if your ingredients have exceeded it

    Generate a graph:
        - To generate a graph click the generate graph button
        - This will display a graph only of the nutrient maxes you have added in comparison to the current level added for that nutrient
        - If you exceed your set max, only the percentage will be shown of how much that nutrient is e.g.
            set protein max to 50 but running total is 100, then you have gone 100% over and so it will display 200%

    Show Recipe:
        - Use the show recipe button in the bottom right to use this feature, this will show all the added items and any notes entered for that specific ingredient
        - Adding notes is optional - if the notes section is blank it is recorded as "no notes given"
        - The user cannot interact with the UI until the recipe window is closed

    OUTPUT to PasteBin:
        - To output, ensure that there is data in the system to be outputted - otherwise you will be prompted to do so
        - Click either long or short depending on what you prefer
        - The link will be updated below and available to copy
        - If running in dummy - we print to terminal what would have been included in the paste
