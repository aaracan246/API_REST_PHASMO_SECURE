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


Modelo entidad-relación (WIP):
