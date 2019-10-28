@echo off
mkdir buildspace
copy .\bin\Crypt.class buildspace > nul
copy MANIFEST.MF buildspace > nul
cd buildspace
jar -cvmf MANIFEST.MF Cipher_Custom.jar Crypt.class > nul
move Cipher_Custom.jar ../build > nul
cd ..
rmdir buildspace /S /Q
