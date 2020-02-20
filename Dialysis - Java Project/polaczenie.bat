@echo off

Set "MyProcess=mysqld.exe"
echo "%MyProcess%"
tasklist /NH /FI "imagename eq %MyProcess%" 2>nul |find /i "%MyProcess%" >nul
If not errorlevel 1 (Echo "%MyProcess%" est en cours d^'execution) else (
net start /wait mysql
mysql -u root -pTutajWpiszSwojeHasłoDoBazy
\u TutajWpiszNazwęBazy
\r)
exit