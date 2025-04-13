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
classDiagram
    %% ===== Core Application Structure =====
    class MainActivity {
        +onCreate()
    }
    
    class MyApp {
        +HiltAndroidApp
    }
    
    class NavGraph {
        +setupNavigation()
    }

    %% ===== Screen Components =====
    class LoginScreen
    class MainScreen
    class TripScreen
    class SubTripScreen
    class UserPreferencesScreen
    class SettingsScreen
    class AboutScreen
    class TermsConditionsScreen
    class ProfileScreen
    class VersionScreen

    %% ===== Data Models =====
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

    %% ===== ViewModels =====
    class TripViewModel
    class SubTripViewModel
    class UserPreferencesViewModel
    class VersionViewModel

    %% ===== Data Layer =====
    class TripRepository {
        <<Interface>>
        +getTrips()
        +addTrip()
        +updateTrip()
        +deleteTrip()
    }
    
    class TripRepositoryImpl {
        -trips: MutableList~Trip~
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

    %% ===== DI & Utilities =====
    class AppModule {
        +provideSharedPrefs()
        +provideTripRepository()
    }
    
    class LanguageChangeUtil {
        +changeLanguage()
        +getLanguageCode()
    }
    
    class AppInfo {
        +versionName: String
        +versionCode: Int
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
    NavGraph --> UserPreferencesScreen
    NavGraph --> SettingsScreen
    NavGraph --> AboutScreen
    NavGraph --> TermsConditionsScreen
    NavGraph --> ProfileScreen
    NavGraph --> VersionScreen
    
    TripScreen --> TripViewModel
    SubTripScreen --> SubTripViewModel
    UserPreferencesScreen --> UserPreferencesViewModel
    VersionScreen --> VersionViewModel
    
    TripViewModel --> TripRepository
    SubTripViewModel --> TripRepository
    TripRepository <|.. TripRepositoryImpl
    
    UserPreferencesViewModel --> SharedPrefsManager
    SharedPrefsManager --> LanguageChangeUtil
    SharedPrefsManager --> DataStoreManager
    
    TripRepositoryImpl --> Trip
    Trip --> SubTrip
    
    AppModule --> SharedPrefsManager
    AppModule --> TripRepositoryImpl
    
    MainScreen --> TripCard
    TripScreen --> TripCard

    note for Trip "Contains list of SubTrips"
    note for TripRepository "Central data operations interface"
    note for SharedPrefsManager "Manages user preferences"
    note for NavGraph "Handles all navigation routes"
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


