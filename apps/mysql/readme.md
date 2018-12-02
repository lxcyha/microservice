update user set  host='%'  where user='root';

ALTER USER 'root' IDENTIFIED WITH mysql_native_password BY '123456';