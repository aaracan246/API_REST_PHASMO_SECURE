La idea del proyecto sería la de una aplicación que muestre información del videojuego Phasmophobia. A través de esta API se puede acceder a información básica como los fantasmas (su nombre, pruebas y descripción) o gestionar los logros que puedes obtener in-game.


Tabla 1: Usuarios

'''sql
CREATE TABLE Users(
username VARCHAR2(15),
password VARCHAR2(15),
role VARCHAR2(10)
)
'''

Tabla 2: Fantasmas

***sql
CREATE TABLE Ghosts(
id NUMBER PRIMARY KEY,
name VARCHAR2(15),
description VARCHAR2(200)
)
***

Table 3: Evidence

***sql
CREATE TABLE Evidences(
id NUMBER PRIMARY KEY,
name VARCHAR2(15) NOT NULL
)
***

Tabla 4: Logros
***sql
CREATE TABLE Achievements(
name VARCHAR2(15),
description VARCHAR(200),
achieved BOOLEAN DEFAULT FALSE
)
***
