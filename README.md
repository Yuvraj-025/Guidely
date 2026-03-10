# Travel Guide App

![Android](https://img.shields.io/badge/Platform-Android-green)
![Language](https://img.shields.io/badge/Language-Kotlin-blue)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-orange)
![Architecture](https://img.shields.io/badge/Clean%20Architecture-Implemented-success)
![Dependency Injection](https://img.shields.io/badge/DI-Hilt-yellow)
![Networking](https://img.shields.io/badge/Networking-Retrofit-red)

Travel Guide App is a modern Android application built with Kotlin using the MVVM design pattern and Clean Architecture principles. The application uses a Mock API to simulate backend responses and follows a Single Activity architecture for better navigation and modular UI design.

The project demonstrates modern Android development practices such as structured architecture, dependency injection, asynchronous programming, and modular UI management.

---

# Overview

This application allows users to explore travel destinations, search for guides, and manage trips in a structured and user-friendly interface.

The project focuses on:

- Clean Architecture implementation
- Separation of concerns
- Scalable project structure
- Modern Android Jetpack libraries
- Efficient network and database handling

---

# Screenshots

| Home Screen | Search Screen | Search Results Screen |
|-------------|---------------|-----------------------|
| home | search | search_results |


# Architecture

The project follows Clean Architecture combined with the MVVM pattern.

```
Presentation Layer
        |
        v
Domain Layer
        |
        v
Data Layer
```

This layered architecture improves maintainability, testing, and scalability by clearly separating responsibilities across different modules.

---

# Data Layer

The Data Layer is responsible for retrieving and managing application data. Data may come from a remote API or a local database.

Other layers cannot directly access data sources. All interactions go through repository classes.

Responsibilities of the Data Layer:

- Handling API requests
- Managing local database operations
- Providing immutable data models
- Ensuring consistent data flow

Repositories act as the single source of truth for the application.

---

# Domain Layer

The Domain Layer is the core of the application and contains the business logic.

It acts as a bridge between the Data and Presentation layers.

Responsibilities of the Domain Layer:

- Defining business rules
- Implementing Use Cases
- Transforming data for UI consumption

This layer remains independent from other layers so that changes in UI or data sources do not affect core logic.

---

# Presentation Layer

The Presentation Layer manages user interactions and UI rendering.

Components included in this layer:

- MainActivity
- Fragments
- ViewModels
- RecyclerView Adapters

Workflow:

1. The user interacts with the UI.
2. Fragments send events to the ViewModel.
3. ViewModel calls the appropriate UseCase in the Domain Layer.
4. UseCases retrieve data from repositories.
5. The UI updates based on the returned state.

ViewModels never directly communicate with data sources.

---

# Data Flow

```
Fragment
   |
   v
ViewModel
   |
   v
UseCase
   |
   v
Repository
   |
   v
API / Local Database
```

---

# Technologies Used

Android Jetpack

- Navigation Component
- Lifecycle
- DataBinding
- ViewModel

Dependency Injection

- Hilt

Networking

- Retrofit
- OkHttp
- Gson

Database

- Room

Concurrency

- Kotlin Coroutines

Image Handling

- Glide
- PhotoView

Other

- SplashScreen API

---

# Project Structure

```
TravelGuideApp
│
├── data
│   ├── repository
│   ├── datasource
│   └── models
│
├── domain
│   ├── model
│   └── usecase
│
├── presentation
│   ├── ui
│   ├── viewmodel
│   └── adapters
│
└── di
```

---

# Installation

Clone the repository

```
git clone https://github.com/Yuvraj-025/Guidely.git
```

Navigate to the project directory

```
cd Guidely
```

Open the project in Android Studio and run the application on an emulator or physical device.

---

# Future Improvements

- Add real backend API integration
- Implement user authentication
- Improve UI/UX animations
- Add offline synchronization
- Expand trip planning features

---

# License

This project is intended for educational and development purposes.

---

# Author

Yuvraj Singh

GitHub: https://github.com/Yuvraj-025
