#!/usr/bin/env bash
echo "
 verbose
 open ftp.walkerit.ch
 user public Public8852
 ascii
 put order-ftp.xml
 bye
" | ftp -n > ftp_$$.log








# * FTP-Server: ftp.walkerit.ch
# * username: public
# * password: Public8852