# 📚 Jetpack Compose Pokémon Library

An elegant, Android-native **Pokémon Library App** built with **Jetpack Compose**, featuring a random Pokémon generator and a searchable Pokédex with detailed Pokémon info — all backed by local storage for smooth offline access.

---

## 🧩 About the App

This mobile app brings the world of Pokémon to your fingertips:

- 🎲 **Randomizer** – Discover a new Pokémon every time you tap.  
- 📋 **All Pokémon Screen** – Browse or search the full Pokédex.  
- 📖 **Details View** – View stats, types, and evolutions.  
- 💾 **Offline Support** – Pokémon data is stored locally using RoomDB, so you can browse even without an internet connection.

---

## 🛠️ Tech Stack

- **Jetpack Compose** – Declarative UI framework for modern Android apps.  
- **Jetpack Navigation** – For clean and manageable screen navigation.  
- **Retrofit** – To fetch Pokémon data from the [PokeAPI](https://pokeapi.co/).  
- **RoomDB** – Local database to cache Pokémon data for fast and offline access.  
- **Coil** – Image loading library to fetch and display Pokémon sprites.  
- **Material 3** – Sleek and responsive design system.

---

## 📱 Screenshots & Features

### 🏠 Main Screen – Randomizer

<p align="center">
  <img src="screenshots/494356001_1369085034366725_6204314868962329093_n.jpg" width="250"/>
  <img src="screenshots/494360258_686167054318088_1495701296423828544_n.jpg" width="250"/>
  <img src="screenshots/494357504_1404878704034228_5967385705310574548_n.jpg" width="250"/>
</p>

- Roll to reveal a **random Pokémon**.  
- Displays name, image in a stylish layout.

---

### 📄 Pokémon Details Screen

<p align="center">
  <img src="screenshots/494360235_728352996196863_7783770273121195276_n.jpg" width="250"/>
  <img src="screenshots/494356399_9950696288378989_8830066771313108294_n.jpg" width="250"/>
</p>

- Shows **type**, **base stats**, **image**, and **evolutions**.  
- Clean layout for fast lookups.

---

### 📋 All Pokémon (Pokédex) Screen

<p align="center">
  <img src="screenshots/494357965_559098883910449_235413113180363228_n.jpg" width="250"/>
</p>

- Browse the **entire Pokédex**.  
- Search by name with offline support.

---

## 🚀 Future Improvements

- Filter by type (e.g., Grass, Electric, Ghost).  
- Add a "Favorites" feature with persistent storage.  
- Shiny variant toggle.  
- Dark/light mode toggle 🌗.  
