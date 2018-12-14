Test project for file storing and retrievieng

Goal:
give a function a hash, and get a stream to file
give a function a file, it will store it
don't worry how it stores and retrieves it

There probably should be some factory method creating this dispatcher objects basing on given arguments
- disk function will get path to directory storing this files (also default for current directory)
- mail function will get get some mail address and login data
- maybe ftp function
- maybe some ssh function?