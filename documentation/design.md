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
    I --> J[Profile]
    I --> K[About]
    I --> L[Version]
    I --> M[Terms]
    I --> N[SignOut]
    E --> O[SubTrips]
```

```mermaid
classDiagram
    direction BT
    
    class MainActivity {
        +onCreate()
    }
    
    class NavGraph {
        +composable("login")
        +composable("main")
        +composable("subtrips")
        +composable("about")
        +composable("terms")
        +composable("version")
        +composable("settings")
    }
    
    class TravelApp {
        +selectedScreen: TravelMode
        +showSettingsMenu: Boolean
    }
    
    class TravelMode {
        <<enumeration>>
        HOME
        TRIP
        ITINERARY
        USER_PREFERENCE
    }
    
    class TripViewModel {
        -repository: TripRepository
        +trips: StateFlow~List~Trip~~
        +addTrip(Trip)
        +deleteTrip(Int)
    }
    
    class UserPreferencesViewModel {
        -sharedPrefsManager: SharedPrefsManager
        +darkThemeEnabled: Boolean
        +language: String
        +updateLanguage(String)
    }
    
    MainActivity --> NavGraph
    NavGraph --> TravelApp
    TravelApp --> TripViewModel
    TravelApp --> UserPreferencesViewModel
    UserPreferencesViewModel --> SharedPrefsManager
    TripViewModel --> TripRepository
    TripRepository <|.. TripRepositoryImpl
    TripRepositoryImpl --> TripDatabase
    TripDatabase --> Trip
    Trip --> SubTrip
```mermaid
erDiagram
    TRIP ||--o{ SUBTRIP : contains
    
    TRIP {
        int id PK
        string title
        string description
        datetime created_at
    }
    
    SUBTRIP {
        int id PK
        int parentTripId FK
        string title
        string description
    }
```

```mermaid
sequenceDiagram
    participant Activity as MainActivity
    participant Nav as NavController
    participant ViewModel as TripViewModel
    participant Repo as TripRepository
    participant DB as TripDatabase
    
    Activity->>Nav: setContent(NavGraph)
    Nav->>ViewModel: init()
    ViewModel->>Repo: loadTrips()
    Repo->>DB: getTrips()
    DB-->>Repo: TripEntities
    Repo-->>ViewModel: Mapped Trips
    ViewModel-->>Nav: Update UI State
```

```mermaid
flowchart LR
    subgraph UI
        A[MainActivity] --> B[NavGraph]
        B --> C[TravelApp]
        C --> D[Composables]
    end
    
    subgraph Business_Logic
        D --> E[ViewModels]
        E --> F[Repositories]
    end
    
    subgraph Data
        F --> G[Room Database]
        F --> H[SharedPreferences]
        G --> I[Trip/SubTrip Entities]
        H --> J[User Settings]
    end
```

```mermaid
pie
    title Componentes por Capa
    "UI (Compose)" : 45
    "Lógica (ViewModels)" : 30
    "Datos (Room/SharedPrefs)" : 25
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

4. **Persistencia de Datos**:
   - Room Database para Trips/SubTrips
   - SharedPreferences para configuración de usuario
   - Integración con Hilt para DI
