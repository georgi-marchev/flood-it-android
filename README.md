# Flood-It Game Clone

# Table of Contents

* [Overview](#overview)
* [Game Instructions](#game-instructions)
* [Installation](#installation)
* [Used Technologies](#used-technologies)

---

<a name="overview"></a>

# Overview

This project is a clone of the popular strategy game **Flood-It**, built as an Android application using Java. It is part of a university course focused on open-source mobile applications. The project contains both the frontend (Android app) and backend (a fork of [Flood-It Backend by Georgi Marchev](https://github.com/georgi-marchev/flood-it)).

The goal of the game is to flood the entire board with a single color in as few moves as possible. The player selects colors to expand their flood zone across the grid, aiming to fill the entire board with one color.

---

<a name="game-instructions"></a>

# Game Instructions

1. Start

The flood begins in the top-left corner of the board.

2. Choose a Color

Select one of the available colors in any of the cells on the board.

3. Flood

The chosen color will spread to the adjacent area that is already part of your flood, as long as the area is the same color as your selection. The flood will expand to all connected cells of that color, increasing the size of your flood zone.

4. Repeat

Continue selecting colors to flood more of the grid, expanding your single-color area.

5. Goal

Flood the entire grid with a single color in as few moves as possible.

---

<a name="installation"></a>

# Installation

To get the game up and running on your local machine or Android device, follow these steps:

1. Clone the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/georgi-marchev/flood-it-android
```

2. Open the Project in Android Studio

Open the project in Android Studio. If you don’t have it, you can download it from here
.

3. Install Dependencies

This project uses Gradle to manage dependencies. Once you’ve opened the project in Android Studio, it will automatically sync with the necessary dependencies. If it doesn’t, you can manually sync by selecting:

File > Sync Project with Gradle Files

4. Configure the Environment

SDK Version: Ensure that you have the appropriate Android SDK installed for API Level 24 and API Level 36.

The project is configured to use Java 17, so you need an Android Studio version with Java 17 compatibility.

5. Run the Application

Connect your Android device via USB or use an emulator.

In Android Studio, click the green Run button or use the shortcut Shift + F10 to build and launch the application.

6. Build APK (Optional)

If you prefer to generate an APK file, you can do so with the following steps:

Go to Build > Build Bundle / APK > Build APK in Android Studio.

Once built, you’ll be able to locate the APK in the output directory.

---

<a name="used-technologies"></a>

# Used Technologies

This project uses the following technologies:

## Frontend

Android application written in Java 17.

Built with Gradle for dependency management and build automation.

## Backend

The backend is completely written in Java 17.

## Development Environment

Android Studio for app development and debugging

Java 17 for the backend logic and app development

[Gradle Configuration](app/build.gradle)

The repository contains a Gradle Wrapper that can be used without the need for installation:
- [gradlew](gradlew) for Linux
- [gradlew.bat](gradlew.bat) for Windows





