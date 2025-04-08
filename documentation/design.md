classDiagram
    direction TB
    
    class MainActivity {
        +onCreate()
    }
    
    class NavGraph {
        +composable routes
    }
    
    class TripViewModel {
        -repository: TripRepository
        +trips: StateFlow<List<Trip>>
        +addTrip()
        +deleteTrip()
    }
    
    class TripRepository {
        <<interface>>
        +getTrips() Flow<List<Trip>>
        +addTrip()
        +deleteTrip()
    }
    
    class TripRepositoryImpl {
        -tripDao: TripDao
        -subTripDao: SubTripDao
        +getTrips()
    }
    
    class TripDatabase {
        +tripDao()
        +subTripDao()
    }
    
    class Trip {
        +id: Int
        +title: String
        +description: String
        +subTrips: List<SubTrip>
    }
    
    class SubTrip {
        +id: Int
        +parentTripId: Int
        +title: String
        +description: String
    }
    
    MainActivity --> NavGraph
    NavGraph --> TripViewModel
    TripViewModel --> TripRepository
    TripRepository <|.. TripRepositoryImpl
    TripRepositoryImpl --> TripDatabase
    TripDatabase --> Trip
    Trip --> SubTrip
    
erDiagram
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
        string location
    }

erDiagram
    TRIP ||--o{ SUBTRIP : "contiene"
    TRIP {
        int id
    } 
    SUBTRIP {
        int parentTripId
    }
    SUBTRIP }o--|| TRIP : "pertenece a"

 flowchart TD
    A[Login] --> B[Home]
    B --> C[Trips]
    B --> D[Settings]
    C --> E[SubTrips]
    D --> F[About]
    D --> G[Terms]
    D --> H[Version]

flowchart LR
    subgraph UI
        A[Activities] --> B[Composables]
        B --> C[ViewModels]
    end

    subgraph Domain
        C --> D[Repositories]
    end

    subgraph Data
        D --> E[Local DB]
        D --> F[Preferences]
    end

    E --> G[Room]
    F --> H[DataStore]

mindmap
  root(com.example.travelworld)
    --> MainActivity
    --> MyApp
    --> NavGraph
    --> data
        --> local
        --> repository
        --> model
    --> domain
        --> model
        --> repository
    --> ui
        --> view
        --> viewmodel
    --> utils

sequenceDiagram
    participant UI as Pantalla
    participant VM as TripViewModel
    participant Repo as TripRepository
    participant DB as Room

    UI->>VM: loadTrips()
    VM->>Repo: getTrips()
    Repo->>DB: tripDao.getAll()
    DB-->>Repo: List<TripEntity>
    Repo->>Repo: mapToDomain()
    Repo-->>VM: List<Trip>
    VM-->>UI: update UI State

sequenceDiagram
    participant UI as Settings
    participant VM as PrefsViewModel
    participant Prefs as SharedPrefs
    participant Util as LangUtil

    UI->>VM: updateLanguage("es")
    VM->>Prefs: setLanguage("es")
    Prefs->>Util: changeLanguage(context, "es")
    Util-->>UI: updateConfiguration()

pie
    title Stack Tecnológico
    "Jetpack Compose" : 35
    "Room Database" : 25
    "Hilt DI" : 20
    "Navigation" : 15
    "DataStore" : 5

graph LR
    A[Compose] --> B[ViewModel]
    B --> C[Coroutines]
    C --> D[Room]
    D --> E[Hilt]
    E --> F[Navigation]
