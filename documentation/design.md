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

## Screen Relationships

```mermaid
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
