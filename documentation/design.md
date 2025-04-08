```mermaid
graph TD
    A[MainActivity] --> B[MainScreen]
    B --> C[NavGraph]
    C --> D[LoginScreen]
    C --> E[HomeScreen]
    C --> F[ProfileScreen]
    C --> G[AddTripScreen]
    C --> H[ItinerariosScreen]
    C --> I[TripDetailScreen]
    C --> J[AboutScreen]
    C --> K[TermsConditionsScreen]
    C --> L[VersionScreen]
    C --> M[SettingsScreen]
    
    E --> N[MenuScreen]
    N --> O[TripScreen]
    N --> P[ItineraryScreen]
    N --> Q[UserPreferenceScreen]
    
    style A fill:#f9f,stroke:#333
    style B fill:#bbf,stroke:#333
    style C fill:#abc,stroke:#333


### design.md

```markdown
## Data Model

```mermaid
classDiagram
    class Trip {
        +Int id
        +String destination
        +Date startDate
        +Date endDate
        +Double price
    }

    class User {
        +Int id
        +String name
        +String email
        +List<Trip> trips
    }

    class Itinerary {
        +Int id
        +Trip trip
        +List<Activity> activities
    }

    class Activity {
        +Int id
        +String name
        +String location
        +DateTime time
        +String notes
    }

    Trip "1" -- "1..*" Itinerary : has
    User "1" -- "0..*" Trip : owns
    Itinerary "1" -- "1..*" Activity : contains

    flowchart TB
    Login -->|Success| Home
    Home --> Profile
    Home --> AddTrip
    Home --> Itinerarios
    Itinerarios --> TripDetail
    Home -->|Settings Menu| About
    Home -->|Settings Menu| Terms
    Home -->|Settings Menu| Version
    Home -->|Settings Menu| Settings
