# CHAT SYSTEM JAVA
Consider this new Project structure which has the source folder with the postgres driver

## Details

This current version of the repo has a file structure implemented as per System Architecture [@Masengesho](https://github.com/donatien2020) suggested,
There is a sample for File Management of the messaging group:
Here are simple details

* ***src/client*** : This is where we have all our views that appear to the user of the app(Front-end).
* ***src/server/models*** : Here we define our db like classes that have setters and getters of all db properies.
* ***src/server/config*** : Our global project configurations.
* ***src/utils*** : Our Utilities, some common methods we may need and so on, ____You will also find the db migration file___.

## How to run/start
* *To start the server, Go to ***src/server*** and run ____ChatServer.java___.
* *To start the Terminal client, Go to ***src/client*** and run ____ChatClient.java___.
## Note
Remember to choose sdk while running this projects in you local machines ide. it gave me headache.

Demo Credentials
```
 username: test
 password: test
```
## Killer Hint
* There is a time when you clone on main and then it fails to run while it was running, Do you kno what delete the out folder which contains the production folder.
* This error is encountered mainly when you pull codes that haven't been compiled by the uploader. 
* So the production folder contains code which are not compiled, when you line 
* You encounter some errors like missing some classes that are properly imported or others. Try it!
