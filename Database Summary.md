---
date: 2026-01-25T16:46
tags:
  - serious_games
cssclasses:
  - center-images
  - center-titles
  - rounded-images
  - note
---
## <mark style="background: #FF5582A6;">Create SQL</mark>

```SQL title="Create Sentence db"
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
FOREIGN KEY (cuenta) REFERENCES cuenta(id) ON UPDATE CASCADE ON DELETE RESTRICT);

CREATE TABLE botella(
id varchar(9) NOT NULL PRIMARY KEY,
tamanio float CHECK (tamanio >=0.5 AND tamanio <= 1.5) NOT NULL ,
bolso varchar(9) NOT NULL,
FOREIGN KEY (bolso) REFERENCES bolso(id)ON UPDATE CASCADE ON DELETE RESTRICT);

CREATE TABLE sensores(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
tipo text NOT NULL,
estado boolean NOT NULL,
intervalo int CHECK (intervalo >0) NOT NULL);

CREATE TABLE bolso_sensores(
bolso varchar(9) NOT NULL,
sensor int AUTO_INCREMENT NOT NULL,
FOREIGN KEY (bolso) REFERENCES bolso(id) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (sensor) REFERENCES sensores(id) ON UPDATE CASCADE ON DELETE RESTRICT,
PRIMARY KEY (bolso, sensor));

CREATE TABLE medidas(
id varchar(255) NOT NULL PRIMARY KEY,
valor float NOT NULL);

CREATE TABLE sensores_medidas(
medida varchar(255) NOT NULL,
sensor int NOT NULL,
FOREIGN KEY (sensor) REFERENCES sensores(id) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (medida) REFERENCES medidas(id) ON UPDATE CASCADE ON DELETE RESTRICT,
PRIMARY KEY (sensor, medida));
```
<div class="page-break" style="page-break-before: always;"></div>

## <mark style="background: #FF5582A6;">Entity - Relation Model</mark>

![[99 - Meta/attachments/MER_Serious.jpg|760x900]]

<div class="page-break" style="page-break-before: always;"></div>

## <mark style="background: #FF5582A6;">Relational Model</mark>

