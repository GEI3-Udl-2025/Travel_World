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
graph TD
    A[LoginScreen] -->|Valid Credentials| B[MainScreen/TravelApp]
    B --> C[HomeScreen]
    B --> D[TripScreen]
    B --> E[ItineraryScreen]
    B --> F[UserPreferenceScreen]
    B --> G[SettingsMenu]
    G --> H[AboutScreen]
    G --> I[ProfileScreen]
    G --> J[TermsConditionsScreen]
    G --> K[VersionScreen]
    G --> L[SettingsScreen]
    D --> M[SubTripScreen]
    

    class A,B,C,D,E,F,G,H,I,J,K,L,M screen;
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


