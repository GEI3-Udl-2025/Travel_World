# TravelWorld - Documentación Completa
```mermaid
classDiagram
    %% ===== Core Components =====
    class MainActivity {
        +onCreate()
    }

    class MyApp {
        +HiltAndroidApp
    }

    class NavGraph {
        +Composable navigation
    }

    %% ===== Screens =====
    class LoginScreen
    class MainScreen {
        +TravelMode enum
    }
    class TripScreen
    class ItineraryScreen
    class UserPreferenceScreen
    class SettingsScreen
    class AboutScreen
    class TermsConditionsScreen
    class ProfileScreen
    class VersionScreen
    class SubTripScreen

    %% ===== ViewModels =====
    class TripViewModel
    class SubTripViewModel
    class UserPreferencesViewModel
    class VersionViewModel

    %% ===== Data Layer =====
    class Trip {
        +id: Int
        +destination: String
        +startDate: String
        +endDate: String
        +note: String
    }

    class SubTrip {
        +parentTripId: Int
        +title: String
        +date: String
        +time: String
        +location: String
    }

    class TripRepository {
        <<Interface>>
        +getTrips()
        +addTrip()
        +deleteTrip()
    }

    class TripRepositoryImpl {
        -Trips: MutableList~Trip~
        -subTrips: MutableList~SubTrip~
    }

    class SharedPrefsManager {
        +userLanguage: String?
        +darkTheme: Boolean
    }

    class DataStoreManager {
        +userLanguageFlow: Flow~String~
        +isDarkThemeFlow: Flow~Boolean~
    }

    class AppInfo {
        +versionName: String
        +versionCode: Int
    }

    %% ===== DI & Utils =====
    class AppModule {
        +provideSharedPreferences()
        +provideTripRepository()
    }

    class LanguageChangeUtil {
        +changeLanguage()
        +getLanguageCode()
    }

    %% ===== UI Components =====
    class TripCard {
        +tripName: String
        +destination: String
        +dates: String
    }

    %% ===== Relationships =====
    MainActivity --> NavGraph
    MyApp --> MainActivity

    NavGraph --> LoginScreen
    NavGraph --> MainScreen
    NavGraph --> TripScreen
    NavGraph --> SubTripScreen
    NavGraph --> SettingsScreen
    NavGraph --> AboutScreen
    NavGraph --> TermsConditionsScreen
    NavGraph --> ProfileScreen
    NavGraph --> VersionScreen

    MainScreen --> TripScreen
    MainScreen --> ItineraryScreen
    MainScreen --> UserPreferenceScreen

    TripScreen --> TripViewModel
    SubTripScreen --> SubTripViewModel
    UserPreferenceScreen --> UserPreferencesViewModel
    VersionScreen --> VersionViewModel

    TripViewModel --> TripRepository
    SubTripViewModel --> TripRepository
    TripRepository <|-- TripRepositoryImpl

    UserPreferencesViewModel --> SharedPrefsManager
    SharedPrefsManager --> LanguageChangeUtil
    SharedPrefsManager --> DataStoreManager

    TripRepositoryImpl --> Trip
    Trip --> SubTrip

    AppModule --> SharedPrefsManager
    AppModule --> TripRepositoryImpl

    TripScreen --> TripCard
    MainScreen --> TripCard

    note for Trip "Main trip entity"
    note for SubTrip "Child activities of trips"
    note for TripRepository "Central data operations"
    note for SharedPrefsManager "Persists user preferences"
```

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
%% Itinerary Flow Diagram
flowchart TD
    %% ===== T2.1 Interaction Structure =====
    A[User] -->|"1. Views Trip List"| B[ItineraryScreen]
    B --> C[TripCard]
    C -->|"Expand/Collapse"| D[SubTrip List]
    A -->|"2. Clicks 'Add Trip'"| E[AddTripDialog]
    A -->|"3. Edits Trip"| F[EditTripDialog]
    A -->|"4. Adds Activity"| G[AddSubTripDialog]

    %% ===== T2.2 UI Components =====
    subgraph UI_Flow
        B -->|LazyColumn| C
        C -->|Dynamic Height| D
        E -->|Form| H["Inputs:\n- Destination\n- Dates\n- Notes"]
        G -->|Form| I["Inputs:\n- Title\n- Date/Location\n- Notes"]
    end

    %% ===== T2.3 Data Flow =====
    subgraph Data_Flow["Dynamic Updates (ViewModel)"]
        J[ItineraryViewModel] -->|"State Holder"| K["trips: List<Trip>"]
        K -->|"Observed by"| B
        E -->|"onConfirm"| J
        G -->|"onConfirm"| J
        J -->|"Triggers Recomposition"| B
    end

    %% ===== Legend =====
    style A fill:#ff9,stroke:#333
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


