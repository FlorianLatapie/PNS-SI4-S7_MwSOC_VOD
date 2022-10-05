# PNS-SI4-S7_MWSCO_VOD

## Subject
You can find the subject [here](./subject.md)
## Students
- [Ludovic BAILET](https://github.com/Ludovic-BAILET)
- [Florian LATAPIE](https://github.com/FlorianLatapie)

## How to run 
Using Java JDK 17.0

Use the two folders (Server_VOD & Client_VOD) as separate projects in your IDE. 
If you are using IntelliJ, you can open the two folders as two projects, the .run/*.xml describe the IDE configuration to run the project automatically (with command line arguments) using localhost and port 2001.
1. run `main.Main.main(String[] args);` from Server_VOD, Usage : `java -jar Server_VOD.jar <port>`
2. run `main.Main.main(String[] args);` from Client_VOD, Usage : `java -jar Client_VOD.jar <ip> <port>`

## Implemented bonus tasks
- [x] Catch and manage connection exceptions (write a message in the client console and asks the user to re-enter the credentials, both for sign in and login)
- [x] save credentials in a file (server-side) and read that file whenever the server is launched, so you could stop and restart your server without losing data.
