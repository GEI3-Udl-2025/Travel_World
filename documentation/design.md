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
    A[LoginScreen] -->|"Credentials valid"| B[MainScreen]
    B --> C[TravelApp]
    C --> D{{"Pantallas Principales\n(MainScreen.kt)"}}
    D --> E[TripScreen]
    D --> F[HomeScreen]
    D --> G[UserPreferencesScreen]
    C --> I{{"Menú Configuración\n(TopAppBar in MainScreen)"}}
    I --> J[AboutScreen]
    I --> K[VersionScreen]
    I --> L[ProfileScreen]
    I --> M[SettingsScreen]
    I --> N[TermsConditionsScreen]
    E --> O[SubTripScreen]

    %% ===== Data Connections =====
    E -->|"Uses"| P[TripViewModel]
    O -->|"Uses"| Q[SubTripViewModel]
    G -->|"Uses"| R[UserPreferencesViewModel]
    K -->|"Uses"| S[VersionViewModel]
    
    P & Q -->|"Depends on"| T[TripRepository]
    T -->|"Implemented by"| U[TripRepositoryImpl]
    R -->|"Manages"| V[SharedPrefsManager]
    V -->|"Uses"| W[LanguageChangeUtil]
    V -->|"Alternative"| X[DataStoreManager]

    %% ===== Style =====
    style A fill:#2ecc71,stroke:#27ae60
    style B fill:#2ecc71,stroke:#27ae60
    style D fill:#3498db,stroke:#2980b9
    style I fill:#e74c3c,stroke:#c0392b
    style P,Q,R,S fill:#f39c12,stroke:#d35400
    style T,U fill:#9b59b6,stroke:#8e44ad
    style V,W,X fill:#1abc9c,stroke:#16a085

    %% ===== Legend =====
    linkStyle 0 stroke:#27ae60,stroke-width:2px
    linkStyle 1,2,3,4 stroke:#2980b9,stroke-width:2px
    linkStyle 5,6,7,8,9 stroke:#c0392b,stroke-width:2px
    linkStyle 10,11,12,13 stroke:#f39c12,stroke-width:2px
    linkStyle 14,15 stroke:#9b59b6,stroke-width:2px
    linkStyle 16,17 stroke:#1abc9c,stroke-width:2px
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


