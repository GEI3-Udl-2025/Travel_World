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
stateDiagram-v2
[*] --> LoginScreen
LoginScreen --> MainScreen: Successful login

    state MainScreen {
        [*] --> TripTab
        TripTab --> TripList
        TripList --> AddTripDialog: "Add Trip" clicked
        TripList --> EditTripDialog: "Edit" clicked
        TripList --> SubTripScreen: Trip selected
        
        AddTripDialog --> TripList: Save
        EditTripDialog --> TripList: Update
        
        state SubTripScreen {
            [*] --> SubTripList
            SubTripList --> AddSubTripDialog: "Add Activity" clicked
            SubTripList --> EditSubTripDialog: "Edit" clicked
            
            AddSubTripDialog --> SubTripList: Save
            EditSubTripDialog --> SubTripList: Update
        }
    }
    
    state DataFlow {
        TripList --> TripViewModel: Load trips
        TripViewModel --> TripRepository: Fetch data
        TripRepository --> TripList: Update UI
        
        SubTripList --> SubTripViewModel: Load subtrips
        SubTripViewModel --> TripRepository: Fetch data
        TripRepository --> SubTripList: Update UI
    }
```

```mermaid
flowchart TD
    %% ===== Main Flow =====
    A[LoginScreen] -->|Successful login| B[MainScreen]
    B --> C[TravelApp]
    C --> D{{Pantallas Principales}}
    D --> E[TripScreen]
    D --> F[HomeScreen]
    D --> G[UserPreferencesScreen]
    C --> I{{Menú Configuración}}
    I --> J[AboutScreen]
    I --> K[VersionScreen]
    I --> L[ProfileScreen]
    I --> M[SettingsScreen]
    I --> N[TermsConditionsScreen]
    E --> O[SubTripScreen]

    %% ===== Component Relationships =====
    C -->|Manages| P[NavGraph]
    P -->|Routes| A
    P -->|Routes| B
    P -->|Routes| E
    P -->|Routes| O
    P -->|Routes| J
    P -->|Routes| K
    P -->|Routes| L
    P -->|Routes| M
    P -->|Routes| N

    %% ===== Data Flow =====
    E -->|Uses| Q[TripViewModel]
    O -->|Uses| R[SubTripViewModel]
    G -->|Uses| S[UserPreferencesViewModel]
    K -->|Uses| T[VersionViewModel]
    Q & R -->|Depends on| U[TripRepository]
    U -->|Implements| V[TripRepositoryImpl]
    S -->|Manages| W[SharedPrefsManager]
    W -->|Uses| X[LanguageChangeUtil]
    W -->|Alternative| Y[DataStoreManager]

    %% ===== Style =====
    style A fill:#9f9,stroke:#090
    style B fill:#9f9,stroke:#090
    style D fill:#bbf,stroke:#33f
    style I fill:#fbb,stroke:#f33
    style Q,R,S,T fill:#ff9,stroke:#990
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


