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

    %% ===== T2.2 UI Components (from your files) =====
    subgraph UI_Flow["UI Implementation (TripScreen.kt/SubTripScreen.kt)"]
        TS -->|"TripCard.kt"| TC
        TC -->|"SubTrip List"| STL
        ATD -->|"Form:\n- Destination\n- Dates\n- Notes"| TS
        ASD -->|"Form:\n- Title\n- Date/Time\n- Location"| STL
    end

    %% ===== T2.3 Data Flow (from your ViewModels) =====
    subgraph Data_Flow["Dynamic Updates (TripViewModel.kt/SubTripViewModel.kt)"]
        TVM[TripViewModel] -->|"State:\ntrips: List<Trip>"| TS
        STVM[SubTripViewModel] -->|"State:\nsubTrips: List<SubTrip>"| STL
        ATD -->|"onConfirm()"| TVM
        ETD -->|"updateTrip()"| TVM
        ASD -->|"addSubTrip()"| STVM
        TVM -->|"Triggers\nRecomposition"| TS
    end

    %% ===== File References =====
    click TS "TripScreen.kt"
    click STL "SubTripScreen.kt"
    click TVM "TripViewModel.kt"
    click STVM "SubTripViewModel.kt"
    click TC "TripCard.kt"

    %% ===== Styling =====
    style U fill:#ff9,stroke:#333
    style UI_Flow fill:#e6f3ff,stroke:#0066cc
    style Data_Flow fill:#e6ffe6,stroke:#009900
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

Implementation Notes:
T2.1 User Interaction (From TripScreen.kt):

Users see trips in a LazyColumn 

Each TripCard is expandable to show SubTrip list

FloatingActionButton triggers AddTripDialog 

T2.2 UI Flow:

// From TripScreen.kt
AddTripDialog(
    onDismiss = { showDialog = false },
    onConfirm = { newTrip ->
        viewModel.addTrip(newTrip) // Updates state
    }
)
T2.3 Dynamic Updates:

// TripViewModel.kt
fun addTrip(trip: Trip) {
    _trips.add(trip)
    // Automatically triggers recomposition
}
Data Validation (From SubTripScreen.kt lines 130-160):

Date/time format checking

Required field validation

Error message display
