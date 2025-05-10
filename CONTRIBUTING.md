# Guía de Contribución

¡Bienvenido/a al proyecto **Travel Planner**! Sigue estos pasos para contribuir de manera efectiva.

---

## Estrategia de Ramas

### Ramas Principales
1. **`main`**
    - **Producción**: Contiene el código estable y probado.
    - *Restricción*: Solo se actualiza mediante PRs aprobados desde `SprinX-Main`.
      
2. **`SprintX-main`**
    - **Producción**: Contiene el código estable y probado de cada Sprint.
    - *Flujo*: Todas las ramas de usuario de cada Sprint respectivo se fusionan aquí.
    - *Restricción*: Solo se actualiza mediante PRs aprobados desde `SprinX-<Usuario>`.

2. **`Sprinx-<Usuario>`**
    - **Desarrollo**: Rama base para integrar nuevas funcionalidades.
    - *Flujo*: X indica el número de Sprint en desarrollo y <Usuario> el usuario desarrollador.

### Ramas de Usuario
- **Formato**: `SprintX-<tu-nombre>/<tipo>-<descripción>(opcional)`  
  Ejemplos:
    - `Sprint1-Xiaolong/feature-mapas`
    - `Sprint3-Jan`
    - `Sprint4-Xiaolong/test`

---

## Pasos para Contribuir

### 1. Clona el Repositorio
```bash
git clone https://github.com/tu-organizacion/travel-planner.git
```
### 2. Actualiza la Rama master

```bash
git checkout master
git pull origin master
```

### 3. Crea tu Rama de Usuario
```bash
git checkout -b SprintX-<tu-nombre>/<descripción>
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
git push origin SprintX-<tu-nombre>/<descripción>
```
### 7. Abre un Pull Request (PR)
- **Destino**: `SprintX-main`
- **Descripción**: Incluye:
  - **Objetivo del PR**: Explica brevemente qué cambios introduces y por qué son necesarios.
  - **Capturas de pantalla** (si aplica): Adjunta imágenes que muestren los cambios visuales o funcionales.
  - **Issues relacionados**: Vincula los issues que resuelves con la sintaxis `Closes #<número>` o `Fixes #<número>`.  
    Ejemplo: `Closes #12`.
