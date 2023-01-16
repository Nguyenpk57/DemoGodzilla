create database godzilladbdemo;
CREATE USER 'godzillauserdemo'@'%' identified by 'godzillapassdemo';
grant all privileges on godzilladbdemo.* to godzillauserdemo@'%';
flush privileges;