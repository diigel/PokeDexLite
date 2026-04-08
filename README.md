# PokeDex Lite

PokeDex Lite is a modern, high-performance Android application designed for trainers to explore the Pokémon universe. The project serves as a showcase of **Clean Architecture**, **Domain-Driven Design (DDD)**, and **SOLID Principles** within a reactive **MVVM** environment.

## 🏗 Architecture & Design Patterns

The project is built on a foundation of **Clean Architecture** and **DDD** to ensure a highly maintainable, testable, and decoupled codebase.

### MVVM (Model-View-ViewModel)
The presentation layer strictly adheres to the MVVM pattern.
*   **View**: Jetpack Compose-based UI that observes state changes.
*   **ViewModel**: Manages UI state using `StateFlow` and handles user interactions by invoking Use Cases.
*   **Model**: Domain entities that represent the core business data.

### Domain-Driven Design (DDD)
The project structure is organized by domain concerns, utilizing:
*   **Entities**: Core data models in the `domain.model` package.
*   **Use Cases**: Encapsulated business logic in the `domain.usecase` package, ensuring each operation is independent and reusable.
*   **Repository Interfaces**: Defined in the domain layer to decouple business logic from data implementation.

### SOLID Principles
*   **Single Responsibility**: Each class (UseCases, Mappers, Repositories) has one focused responsibility.
*   **Open/Closed**: The system is open for extension via interfaces but closed for modification.
*   **Liskov Substitution**: Interface implementations (like Repositories) are interchangeable without breaking the system.
*   **Interface Segregation**: Clients only depend on the specific methods they require via granular Use Cases.
*   **Dependency Inversion**: High-level modules do not depend on low-level modules; both depend on abstractions (Hilt/Dagger facilitates this).

## 🛠 Tech Stack

| Category | Tools & Libraries |
| :--- | :--- |
| **Language** | Kotlin 2.1.10 (K2 Compiler ready) |
| **UI Framework** | Jetpack Compose (Material 3) |
| **Dependency Injection** | Hilt (Dagger-Hilt) 2.55 |
| **Networking** | Retrofit 2.11.0 + OkHttp 4.12.0 |
| **Image Loading** | Coil 3 (Multiplatform Standard) |
| **Persistence** | Couchbase Lite (NoSQL) 4.0.3 |
| **Concurrency** | Kotlin Coroutines & StateFlow |
| **Serialization** | Kotlinx Serialization 1.8.0 |

## 🚀 Key Features

*   **User Authentication**: Secure Login and Registration flow with session management via `SessionManager`.
*   **Dynamic Pokémon Discovery**: A paginated list of Pokémon with real-time search functionality.
*   **Detailed Stats**: Comprehensive detail view for each Pokémon, featuring high-quality sprites, ability lists, and type badges.
*   **Bottom Navigation**: A centralized `MainScreen` managing a multi-tabbed interface (PokéDex and Profile) with state preservation.
*   **Offline Support**: Integration of Couchbase Lite to ensure the app remains functional in low-connectivity environments.

## ⚙️ Build & Compatibility

*   **Android Gradle Plugin (AGP)**: 8.8.2
*   **Kotlin Version**: 2.1.10
*   **Java Toolchain**: JDK 21
*   **Target SDK**: 36 (Android 16 compatibility)
*   **Compose Compiler**: Integrated via the new Kotlin Gradle Plugin (v2.0+ standard).

## 🛠 Setup & Installation

### Prerequisites
*   Android Studio Ladybug (2024.2.1) or newer.
*   JDK 21.

### Steps
1.  **Clone the Repository**:
    ```sh
    git clone https://github.com/yourusername/PokeDexLite.git
    ```
2.  **Configuration**:
    The project uses a `key.properties` file in `/Document`. Ensure it contains:
    ```properties
    BASE_URL="https://pokeapi.co/api/v2/"
    IMAGE_URl="https://raw.githubusercontent.com/"
    IMAGE_PATH="PokeAPI/sprites/master/sprites/pokemon/"
    ```
3.  **Build & Run**: Sync Gradle and run on a device (API 24+).
