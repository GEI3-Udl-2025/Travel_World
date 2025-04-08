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
