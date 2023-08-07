# TimeTracker
A minecraft server plugin, which keeps track of the playtime you have on the server and notifies you at specified times.

## Functionalities
Notifications can be send personally (only to you) or globally to all players that are currently online.
Players can disable messages or mute the sounds individually, which will then be stored in the files
* *DisabledPlayers.txt*
* *MutedPlayers.txt*
Further functionalities are explained in the **Commands** section.

## Minecraft Version:
Currently working for Minecraft 1.20.1

## Usage
Update the following files
* *GlobalTimes.txt*
* *PersonalTimes.txt*
With the times you want to be notified at.
Update the Conifgfile to dynamically adjust the sound to be played, along with the volume and the pitch (speed).

## Commands
**/timetracker info**: sends info about personal settings as well as serverwide settings to the command sender
**/timetracker disable/enable**: disables and enables the messages sent to the player (*personal settings*)
**/timetracker mute/unmute**: mutes/ unmutes the sound of the notifications (*personal settings*)
**/timetracker moderate info**: sends more specific infos (as sound, volume and pitch) to the command sender
**/timetracker moderate dis/enable**: dis/enables serverwide notifications (*global settings*)
**/timetracker moderate un/mute**: un/mutes serverwide sounds (*global settings*)
**/timetracker moderate reload**: reloads all files (config, times and players)

### Remark
Players need **op** rights to issue the *moderate* command section.
