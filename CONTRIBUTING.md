# Guía de Contribución 📝

¡Bienvenido/a al proyecto **Travel Planner**! Sigue estos pasos para contribuir de manera efectiva.

---

## 🌿 Estrategia de Ramas

### Ramas Principales
1. **`main`**
    - **Producción**: Contiene el código estable y probado.
    - *Restricción*: Solo se actualiza mediante PRs aprobados desde `master`.

2. **`master`**
    - **Desarrollo**: Rama base para integrar nuevas funcionalidades.
    - *Flujo*: Todas las ramas de usuario se fusionan aquí.

### Ramas de Usuario
- **Formato**: `usuario/<tu-nombre>/<tipo>-<descripción>`  
  Ejemplos:
    - `usuario/maria/feature-mapas`
    - `usuario/carlos/fix-login`

---

## 🚀 Pasos para Contribuir

### 1. Clona el Repositorio
```bash
git clone https://github.com/tu-organizacion/travel-planner.git
cd travel-planner
```
### 2. Actualiza la Rama master

```bash
git checkout master
git pull origin master
```

### 3. Crea tu Rama de Usuario
```bash
git checkout -b usuario/<tu-nombre>/<descripción>
```

### 4. Desarrolla y Haz Commits
Ejemplo de commit:
```bash
git add .
git commit -m "feat: Agrega pantalla de itinerario (#12)"
```
### 5. Sincroniza con master (opcional)
```bash
git pull origin master  # Resuelve conflictos si los hay
```
### 6. Sube tu Rama

```bash
git push origin usuario/<tu-nombre>/<descripción>
```
### 7. Abre un Pull Request (PR)
Destino: master

Descripción: Incluye:

Objetivo del PR.

Capturas de pantalla (si aplica).

Issues relacionados (ej: Closes #12).
