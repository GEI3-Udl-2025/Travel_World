# Guía para Contribuir

¡Gracias por tu interés en contribuir a nuestro proyecto! Para garantizar un flujo de trabajo organizado y eficiente, sigue la estrategia de ramificación que se describe a continuación.

## Estrategia de Ramificación

Utilizamos una **estrategia de ramificación inspirada en Gitflow** para gestionar nuestro código. Aquí te explicamos cómo funciona:

### Ramas Principales
1. **`main`**:
    - Esta es la rama estable y lista para producción.
    - Solo se debe fusionar código estable, probado y aprobado en esta rama.
    - No se permiten commits directos a `main`.

2. **`develop`**:
    - Esta es la rama de integración para el desarrollo en curso.
    - Todas las ramas de características se fusionan en `develop` después de su revisión y pruebas.
    - Esta rama se utiliza para preparar la próxima versión.

### Ramas de Soporte
1. **Ramas de Características (Feature Branches)**:
    - Se crean a partir de: `develop`
    - Convención de nombres: `feature/<nombre-de-la-característica>` (ejemplo: `feature/autenticacion-usuario`)
    - Utiliza estas ramas para desarrollar nuevas características o mejoras.
    - Una vez que la característica esté completa, crea un Pull Request (PR) para fusionarla en `develop`.

2. **Ramas de Corrección de Errores (Bugfix Branches)**:
    - Se crean a partir de: `develop`
    - Convención de nombres: `bugfix/<descripcion-del-error>` (ejemplo: `bugfix/error-login`)
    - Utiliza estas ramas para corregir errores en el código.
    - Una vez corregido el error, crea un PR para fusionarlo en `develop`.

3. **Ramas de Lanzamiento (Release Branches)**:
    - Se crean a partir de: `develop`
    - Convención de nombres: `release/<version>` (ejemplo: `release/v1.2.0`)
    - Utiliza estas ramas para preparar una nueva versión.
    - Solo se deben hacer correcciones de errores y ajustes finales en esta rama.
    - Una vez lista, fusiona la rama de lanzamiento en `main` y `develop`.

4. **Ramas de Hotfix**:
    - Se crean a partir de: `main`
    - Convención de nombres: `hotfix/<descripcion-del-problema>` (ejemplo: `hotfix/error-critico-seguridad`)
    - Utiliza estas ramas para corregir problemas críticos en producción.
    - Una vez corregido el problema, fusiona la rama en `main` y `develop`.

### Pull Requests (PRs)
- Todos los cambios deben enviarse mediante un PR.
- Los PRs deben ser revisados por al menos otro miembro del equipo antes de fusionarse.
- Asegúrate de que tu rama esté actualizada con la rama objetivo antes de crear un PR.
- Incluye una descripción clara de los cambios y referencia los issues relacionados.

### Mensajes de Commit
- Escribe mensajes de commit claros y concisos.
- Usa el tiempo presente (ejemplo: "Agrega autenticación de usuario" en lugar de "Agregué autenticación de usuario").
- Referencia el número del issue o ticket si es aplicable (ejemplo: "Corrige error de login (#123)").

---

## Cómo Empezar
1. Haz un fork del repositorio y clónalo localmente.
2. Crea una nueva rama siguiendo las convenciones de nombres mencionadas.
3. Realiza tus cambios y haz commits con mensajes claros.
4. Sube tu rama a tu repositorio forkeado.
5. Abre un PR dirigido a la rama `develop`.

---

## Proceso de Revisión de Código
- Todos los PRs serán revisados por el equipo.
- Resuelve cualquier comentario o solicitud de cambios antes de fusionar.
- Una vez aprobado, tus cambios se fusionarán en la rama objetivo.

---

¡Gracias por contribuir! Si tienes alguna pregunta, no dudes en contactar al equipo.