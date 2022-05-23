# Tanks
First try of making game using JavaFX.

Needs to run the Server java file firstly.

Client java file provides only additional controller.

# Genshin-DB

## Presentation 
https://www.canva.com/design/DAEyuhRNro8/W3Hfo4VlySZOF8uwkrVprg/view?utm_content=DAEyuhRNro8&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink

## Mockups
https://miro.com/app/board/uXjVOZsPjKE=/?invite_link_id=560788978137

## Project overview
Our database will be used by the web site or app to monitor some progress or statistics from the game `Genshin Impact`.
It is much easier to see it in this way than to enter the game and calculate it by yourself.
This DB is based on the game at whole, so there are:
- 4 servers, as in the game.
- 7 elements, same as in the game.
- 5 classes, same as in the game.
- 125 weapons, randomly filled by mockaroo.
- 117 mobs, randomly filled by mockaroo.
- 43 characters, randomly filled by mockaroo, but there are not any dendro characters as in the game.
Tables `Users`, `Players`, `Builds`, `Kills_log`, `Res_log` can have hundreds of thousands rows, but in our case we filled:
- 1000 users
- 1000 players
- 1000 builds
- 2006 kills' log (might be changed during the pj-defe)
- 3003 resources' log (might be changed during the pj-defe)

## Who will use it?
Users: players, admins.
Players: monitor their account via phone and see some statistics.
Admins: search for the users actions and see some trands.

## ER diagram
![](GenshinERDlast.png "It is beautiful~")

## Pages/Activities
Registration page: to input the new user.
Log in page: log in user.
Diary page: to see acquired resources log and total acquired resources for some month.
All characters page: shows all characters of the player.
Details of the character: shows the build of the player's character.
Killed mobs page: shows how many of mobs of each type player killed.

## SQL Views
`Kills_log_v_loot_res` view is used for the trigger `update_p_res`.
`player_builds_v` view is used to show the player characters' details.

## SQL Triggers
`update_p_res` trigger increases the player's resources according to the killed mob's drop.
`res_log_update` trigger saves player's resources before and after each time it changes.

## SQL Insert, Update, Delete functions
Registration a new user: Insert into Users values (id,first_name,last_name,email,login,password);
Inserst: used to fill all tables in the DB.
Delete: will be used for delation of an account.
Update: used for changing some personal information or in triggers.

## Background jobs
There are 5 queries created to support the 5 main users interfaces written in txt files.

# Planetarium
Project of creating front only site with using 3D objects.
A site that shows all information about space and planets.
