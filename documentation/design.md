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
flowchart TD
    %% ===== Main Flow =====
    A[LoginScreen] -->|"Credentials valid"| B[MainScreen]
    B --> C[TravelApp]
    C --> D{{"Pantallas Principales\n(MainScreen.kt)"}}
    D --> E[TripScreen]
    D --> F[HomeScreen]
    D --> H[Itinerary]
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


    %% ===== Legend =====
    linkStyle 0 stroke:#27ae60,stroke-width:2px
    linkStyle 1,2,3,4 stroke:#2980b9,stroke-width:2px
    linkStyle 5,6,7,8,9 stroke:#c0392b,stroke-width:2px
    linkStyle 10,11,12,13 stroke:#f39c12,stroke-width:2px
    linkStyle 14,15 stroke:#9b59b6,stroke-width:2px
    linkStyle 16,17 stroke:#1abc9c,stroke-width:2px
```

```mermaid
flowchart TD
    %% ===== T2.1 User Interaction Structure =====
    U[User] -->|1. Views Trips| TS[TripScreen]
    TS -->|LazyColumn| TC[TripCard]
    TC -->|Expand| STL[SubTrip List]
    U -->|2. Clicks '+'| ATD[AddTripDialog]
    U -->|3. Edits Trip| ETD[EditTripDialog]
    U -->|4. Adds Activity| ASD[AddSubTripDialog]

    %% ===== T2.2 UI Components =====
    subgraph UI_Flow["UI Implementation"]
        TS -->|"TripCard.kt"| TC
        TC -->|"SubTrip List"| STL
        ATD -->|"Form Fields"| TS
        ASD -->|"Form Fields"| STL
    end

    %% ===== T2.3 Data Flow =====
    subgraph Data_Flow["Data Management"]
        TVM[TripViewModel] -->|"Live State"| TS
        STVM[SubTripViewModel] -->|"Live State"| STL
        ATD -->|"Saves Data"| TVM
        ETD -->|"Updates Data"| TVM
        ASD -->|"Saves Data"| STVM
    end

    %% ===== Styling =====
    style U fill:#f8f9fa,stroke:#6c757d,color:#212529
    style UI_Flow fill:#e7f5ff,stroke:#4dabf7,color:#1864ab
    style Data_Flow fill:#ebfbee,stroke:#40c057,color:#2b8a3e
    style TS,TC,STL fill:#ffffff,stroke:#495057,color:#212529
    style ATD,ETD,ASD fill:#fff3bf,stroke:#fcc419,color:#5f3dc4
    style TVM,STVM fill:#f3f0ff,stroke:#9775fa,color:#5f3dc4

    %% ===== Visual Optimization =====
    classDef default font-family:Helvetica,font-size:14px
    linkStyle default stroke:#adb5bd,stroke-width:1.5px
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


