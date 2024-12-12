La idea del proyecto sería la de una aplicación que muestre información del videojuego Phasmophobia. A través de esta API se puede acceder a información básica como los fantasmas (su nombre, pruebas y descripción) o gestionar los logros que puedes obtener in-game.


Tabla 1: Usuarios

```sql
CREATE TABLE Users(
  id NUMBER PRIMARY KEY,
  username VARCHAR2(15) NOT NULL,
  password VARCHAR2(15) NOT NULL,
  role VARCHAR2(10) NOT NULL
)
```


Tabla 2: Fantasmas

```sql
CREATE TABLE Ghosts(
  id NUMBER PRIMARY KEY,
  name VARCHAR2(15),
  description VARCHAR2(200)
)
```

Table 3: Pruebas

```sql
CREATE TABLE Evidences(
  id NUMBER PRIMARY KEY,
  name VARCHAR2(15) NOT NULL
)
```

Tabla 4: Tabla cruzada pruebas_fantasmas (One to Many)

```sql
CREATE TABLE Ghost_Evidences (
    ghost_id NUMBER,
    evidence_id NUMBER,
    PRIMARY KEY (ghost_id, evidence_id),
    FOREIGN KEY (ghost_id) REFERENCES Ghosts(id),
    FOREIGN KEY (evidence_id) REFERENCES Evidences(id)
);
```

Tabla 5: Logros

```sql
CREATE TABLE Achievements(
    id_achv NUMBER PRIMARY KEY,
    name VARCHAR2(15) NOT NULL,
    description VARCHAR2(200),
    achieved BOOLEAN DEFAULT FALSE
)
```


Tabla 6: Tabla cruzada logros_usuarios  (One to Many)

```sql
CREATE TABLE User_Achievements (
    user_id NUMBER,
    achievement_id NUMBER,
    achieved BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (user_id, achievement_id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (achievement_id) REFERENCES Achievements(id)
);
```

Modelo entidad-relación (WIP):
