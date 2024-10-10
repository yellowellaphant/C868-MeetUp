# MeetUp - A Meeting Management Application
Created for Software Capstone Project (WGU class C868)

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Installation](#installation)
5. [Usage](#usage)
   - [Logging In](#logging-in)
   - [Navigating the Application](#navigating-the-application)
   - [Managing Customers](#managing-customers)
   - [Managing Meetings](#managing-meetings)
   - [Generating Reports](#generating-reports)
6. [Unit Test Plan](#unit-test-plan)
7. [Source Code](#source-code)
8. [License](#license)

## Introduction
MeetUp is a desktop-based meeting management application designed to facilitate the scheduling and tracking of meetings and customer profiles. It allows users to efficiently manage their appointments, search for customers and meetings, and generate reports. The application features a user-friendly interface and robust functionality, making it suitable for various professional environments.

## Features
- User login with validation
- Customer profile management (add, update, delete)
- Meeting management (schedule, update, delete)
- Search functionality for customers and meetings
- Reports on meeting types and totals

## Requirements
- IntelliJ IDEA Community 2023 or higher
- MySQL Server and MySQL Workbench version 8.0 or higher
- JDK version 17 or higher
- JavaFX version 17 or higher
- MySQL Connector-J version 8.3 or higher

## Installation
1. **Download and Install Software:**
   - IntelliJ IDEA Community version 2023.2.2+
   - MySQL Server and MySQL Workbench version 8.0+
   - JDK version 17+
   - JavaFX version 17+
   - MySQL Connector-J version 8.3+

2. **Set Up MySQL Workbench:**
   - Create a new connection with the appropriate credentials.
   - Populate the database with the provided DDL and DML scripts.

3. **Setting Up the Application:**
   - Unzip the `C868-MeetUp` file and open it in IntelliJ IDEA.
   - Configure the MySQL connector in the project settings.
   - Locate and run the `Main` class in the project.

## Usage

### Logging In
To access the application, use the following credentials:
- **Username:** `admin`
- **Password:** `admin`

Upon successful login, you will receive an alert regarding upcoming appointments.

### Navigating the Application
Once logged in, you will be directed to the Main Directory screen, where you can navigate to different sections of the application using the available buttons.

### Managing Customers
- **Add Customer:** Input customer details in the "Add Customer" screen and save.
- **Update Customer:** Select a customer, edit their details, and save changes.
- **Delete Customer:** Select a customer to permanently remove their profile.

### Managing Meetings
- **Add Meeting:** Input meeting details in the "Add Meeting" screen and save.
- **Update Meeting:** Select a meeting, edit its details, and save changes.
- **Delete Meeting:** Select a meeting to permanently remove it.

### Generating Reports
Access the Reports section from the Main Directory to view meeting totals and details associated with contacts and users.

## Unit Test Plan
The application includes a unit test plan for the login functionality, ensuring that users can only access the system with valid credentials. Testing results are documented in a `login_activity.txt` file for review.

## Source Code
The source code is included in the attached `C868-MeetUp` file. 
