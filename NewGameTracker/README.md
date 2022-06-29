The following application allows for the tracking of basic stats in a rugby league match.
The git clone should contain a build.gradle, src, README.md and a JAR executable.

How To Use:

    - Playing Roster Page:
        - When launching, the user must enter the players number (as it is on their jersey) and their name.
        - This is anticipated for a squad of 20, if there is less than 20 in a squad you may fill in the remaining spots with fake values -
            the only condition is that the field on the left (that say "player #") needs to be an integer of some sort.
        - If a number is left blank it will not proceed and will change to red, this is to prevent accidently forgetting a player


    - Home Page
        - Top Section
            - This section contains the score and a timer - set to 35 minutes as that is the half length
            - Once the timer reaches 0, it can be reset by toggling the time
            - The toggle time button can be used to pause and continue the timer at any time

        - Left Section
            - This section is dedicated to team statistics, un/completed sets, penalties for/against and scores
            - To increment a a statistic click the add button located to the left of the desired title, this will update the number
            - To change the score, enter in the desired amount and click update, this will be successful if the score in the top section updates
                note that this is a set/overwrite score function - if the score is currently 4  and 2 points are scored, you must enter the number 6 as the new score
            - If you do not enter in a number and press update, the field outline will turn red

        - Right Section
            - The right section is a game log, ensure the time is active before you start adding stats to ensure the game log has accurate data
            - When team stats are updated (i.e. score, penalties or completed sets) they will be noted down along with the time they occured (counting up)

        - Middle Section
            - This section relates to individual player stats
            - To add a stat, you must enter the player number in the field, and change the drop down box to the stat you want to use
            - For example, the player in jersey 10 mad a hit up, so enter the number 10, change the drop down to hit up, and press submit 
            - If successful, the number should be cleared from the text field 
            - If no number is entered, the text field will be highlighted red
            - It is unlikley that you will be able to track every listed stat for a whole game as usually there are people dedicated to a spectific stat
                incrementing stats by player number has proven to be the quickest way to stay on top of stat tracking - but I reccomend choosing 1 or 2 key stats to track

        - Bottom Section
            - This section allows for graphical representation of stats as well as a button to end the game.
            - To graph player stats, choose from the drop down box the type of stat you will like graphed, then press the "Graph Player Stats" button, this 
                will generate a popout with the graph of that statistic
            - The "Graph Game Stats" button will graph the game stats (penalties and completed sets)
            - To end a game, ensure you enter a file name in the text field, this is because all the info will be added to that file and saved under that name
                if no file name is entered an error box will appear prompting to do so.
            - Once you press end game, the score, team stats and each individuals stats will be written to the file with the heading specified, along with the game log, this will also close the program.

        
    
