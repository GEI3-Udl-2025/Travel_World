# TravelWorld - Documentación Completa

```mermaid
flowchart TD
    A[Login] --> B[Main]
    B --> C[TravelApp]
    C --> D{{Pantallas Principales}}
    D --> E[Trips]
    D --> F[Home]
    D --> G[Itinerary]
    D --> H[User Preferences]
    C --> I{{Menú Configuración}}
    I --> J[About]
    I --> K[Version]
    I --> L[Profile]
    I --> M[Settings]
    I --> N[Terms]
    E --> O[SubTrips]
```

```mermaid
graph TD
    S[Settings Menu] --> A[AboutScreen]
    S --> B[VersionScreen]
    S --> C[ProfileScreen]
    S --> D[SettingsScreen]
    S --> E[TermsConditionsScreen]
    style S fill:#4CAF50,stroke:#388E3C
```

## Key Features:
1. **4 Pantallas Principales**:
    - Viajes (Trips)
    - Inicio (Home)
    - Itinerario (Itinerary)
    - Preferencias (User Preferences)

2. **Menú de Configuración con 5 opciones**:
    - About (información de la app)
    - Version (detalles de versión)
    - Profile (gestión de usuario)
    - Settings (configuración general)
    - Terms & Conditions (legal)

3. **Flujo de Navegación**:
    - Login → Main (Home) → (4 pantallas principales)
    - Acceso directo a SubTrips desde Trips
    - Menú desplegable con 5 opciones de configuración


