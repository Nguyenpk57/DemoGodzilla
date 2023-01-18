create database godzilladbdemo;
CREATE USER 'godzillauserdemo'@'%' identified by 'godzillapassdemo';
grant all privileges on godzilladbdemo.* to godzillauserdemo@'%';
flush privileges;

create database godzilladbdemo3;
CREATE USER 'godzillauserdemo3'@'%' identified by 'godzillapassdemo3';
grant all privileges on godzilladbdemo3.* to godzillauserdemo3@'%';
flush privileges;