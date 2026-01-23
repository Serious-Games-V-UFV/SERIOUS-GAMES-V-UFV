---
Modified:
  - viernes 23 ene. 2026 14:14:41
Created: viernes 23 ene. 2026 12:36:48
---

```SQL title="create_database"
CREATE DATABASE serious_games;
USE  serious_games;
CREATE TABLE cuenta(
id varchar(9) NOT NULL PRIMARY KEY,
email VARCHAR(255) UNIQUE NOT NULL,
    CHECK (email LIKE '%_@_%.__%'),
nombre text NOT NULL,
peso float NOT NULL,
altura int CHECK (altura >= 1 AND altura <= 999) NOT NULL,
pswd varchar(255) NOT NULL);

CREATE TABLE bolso(
id varchar(9) NOT NULL PRIMARY KEY,
color text NOT NULL,
f_compra DATE DEFAULT NULL,
cuenta varchar(9) NOT NULL,
FOREIGN KEY (cuenta) REFERENCES cuenta(id));


```