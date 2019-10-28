#!/bin/sh
mkdir buildspace
cp ./bin/Crypt.class ./buildspace
cp ./MANIFEST.MF ./buildspace
cd buildspace
jar -cvmf MANIFEST.MF Cipher_Custom.jar Crypt.class
mv Cipher_Custom.jar ../build
cd ..
rm buildspace -r
