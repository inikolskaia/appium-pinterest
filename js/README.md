# Appium Pinterest
This project contains test scripts fot Pinterest app on Android implemented in JavaScript with Mocha framework. 
## Installation
In general, after git clone, you'll need to run npm install:
```
npm install
```
Which installs all dependencies from package.

OR manually:

1. NodeJS - https://nodejs.org/download/
2. Appium - http://appium.io
3. WD.js - https://github.com/admc/wd
4. Chai - http://chaijs.com/guide/installation/
5. Chai As Promised - https://github.com/domenic/chai-as-promised
6. Mocha - http://mochajs.org/

## Usage
These test scripts are made to run against TestObject mobile device.
To run the tests:
```
mocha features/pin.js
```
The script will automatically install its app and run the test cases.
## Tests
✓ Registration by email

✓ Pick that interesting

✓ Create a board to pin things to

✓ Click the Search menu, and search for something

✓ Like and Pin something

✓ Navigate back to Profile

✓ Should fail to find Waldo

✓ Delete the Board

✓ Unlike the Pin