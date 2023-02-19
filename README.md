# NBAToday-Server

## About
An API server written with the Ktor framework.

It provides APIs that offer game data, player and team data, as well as user registration and login mechanisms.

## Technology
Here are the key technologies I've used:
- Ktor Server
- Ktor Client
- Exposed

## Route
- `game`
  - `scoreboard`: Get the scores and data for all games on a specific date. 
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |leagueID|String|O|"00"|
     |gameDate|String|X|"2023-2-20"|
  - `scoreboards`: Get scores and data for all games from a specific date up to X days later.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |leagueID|String|O|"00"|
     |year|Int|X|2023|
     |month|Int|X|2|
     |day|Int|X|20|
     |total|Int|O|7|
  - `schedule`: Get the schedule for the entire season.
  - `{id}`: Get data for a specific game.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |id|String|X|"0022200414"|
- `player`
  - `detail`: Get information and stats for a player.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |season|String|X|"2022-23"|
     |id|String|X|"1628997"|
- `team`
  - `{id}/players`: Get stats of all player for a specific team.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |season|String|X|"2022-23"|
     |id|String|X|"1610612739"|
  - `stats`: Get information and stats for all team or specific team.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |season|String|X|"2022-23"|
     |id|String|O|"1610612739"|
- `user`
  - `login`: Get user's access token and the amount of credits.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |account|String|X|"allen.du"|
     |password|String|X|"0000"|
  - `register`: Register a user and return user's access token and the amount of credits.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |account|String|X|"allen.du"|
     |password|String|X|"0000"|
  - `password`: Update a user's password.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |account|String|X|"allen.du"|
     |token|String|X|"XXXX"|
     |newPassword|String|X|"1111"|
  - `points`: Update a user's credits.
     |Parameter|Type|Nullable|Example|
     |:---:|:---:|:---:|:---:|
     |account|String|X|"allen.du"|
     |token|String|X|"XXXX"|
     |points|Long|X|100|
     
## NBA Api
All NBA data is retrieved from https://stats.nba.com/stats and https://cdn.nba.com.
     
     
     
     
     
     
