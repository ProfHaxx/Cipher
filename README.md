# Cipher Project
## Introduction
This simple software will allow you to encrypt strings and files using very simple algorithms to offer basic security. This project is based on demand. If someone wants me to update the code or simply make the encryption safer, e.g. by using better algorithms or by optimizing encryption / decryption time.

## Installation
**Note**: Java is required.

### Manual Build
```bash
# clone the repository
$ git clone https://github.com/ProfHaxx/Cipher.git

# change working directory
$ cd Cipher

# use a build script to build the latest version if not already built in /build
$ ./build.sh
# for Windows Users:
$ build
```

### Using Pre-Built Versions
You can checkout the build directory, where you can find the latest built versions of this Cipher Software

## How to use
```bash
# Encrypt a text line
$ java -jar Cipher_vx.x.jar encrypt text
# Plaintext: (Enter some plaintext here)
# Encryption Level(1-2): (Enter 1 or 2 here)
# [Encrypted line comes here]

# Decrypt a text line
$ java -jar Cipher_vx.x.jar decrypt text
# Encrypted Note: (Enter the encrypted line here)
# [Decrypted line comes here]

#Encrypt a file
$ java -jar Cipher_vx.x.jar encrypt file
# File Name: (Enter the file name here, must be in the same directory with the jar)
# Encryption Level(1-2): (Enter 1 or 2 here)
# Done!

#Decrypt a file
$ java -jar Cipher_vx.x.jar decrypt file
# Encrypted File: (Enter the file name here, must be in the same directory with the jar)
# Done!
```


## Collaborate
Anyone who wants to collaborate on this project can mail me (blanc.leedom@protonmail.com).

## Future Plans
Program a GUI for Cipher

Include DES-Algorithm

(Possibly) Include AES-Algorithm for password-hashing purposes.
