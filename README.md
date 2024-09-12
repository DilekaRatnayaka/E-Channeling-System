# E-channeling System

This project documents the design and development of an E-channeling system using Java Servlets and Java Server Pages (JSP). The system allows doctors and patients to interact with channeling schedules in a healthcare environment.

## Table of Contents
- [Introduction](#introduction)
- [Project Objectives](#project-objectives)
- [System Design](#system-design)
  - [Use Case Diagram](#use-case-diagram)
  - [Class Diagram](#class-diagram)
  - [Database Design](#database-design)
- [Features Implemented](#features-implemented)
  - [Doctor Features](#doctor-features)
  - [Patient Features](#patient-features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Screenshots](#screenshots)

## Introduction

The E-channeling system is developed as part of the Enterprise Application Development (EAD) course. The system is designed to allow doctors to manage channeling schedules and patients to book appointments based on availability.

## Project Objectives

The primary objectives of the E-channeling system include:
- Doctor registration, login, and schedule management.
- Patient registration, login, and appointment booking.

## System Design

### Use Case Diagram
[[Use Case Diagram](https://github.com/DilekaRatnayaka/E-Channeling-System/blob/main/images/use%20case%20diagram.png)]

### Class Diagram
[[Class Diagram](https://github.com/DilekaRatnayaka/E-Channeling-System/blob/main/images/class%20diagram.png)]

### Database Design

The system uses a MySQL database named `e_channeling` to store persistent data. Below are the SQL scripts to create database and the necessary tables:

## Database Schema

The E-Channeling System uses the following database schema. Below are the SQL commands to create the necessary tables.

### Create Database

```sql
CREATE DATABASE e_channeling;

USE e_channeling;

Create the Doctor table
CREATE TABLE Doctor (
    user_id VARCHAR(50) PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

-- Create the Patient table
CREATE TABLE Patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    nic VARCHAR(12) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Create the Schedule table
CREATE TABLE Schedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id VARCHAR(50),
    doctor_name VARCHAR(100),
    channeling_date DATE NOT NULL,
    channeling_time TIME NOT NULL,
    patient_limit INT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES Doctor(user_id),
    UNIQUE (doctor_id, channeling_date)
);

-- Create the Appointment table
CREATE TABLE Appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_id INT NOT NULL,
    patient_id INT NOT NULL,
    appointment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    UNIQUE (schedule_id, patient_id)
);

```

## Features Implemented

### Doctor Features
- **Doctor Registration**: 
  - Doctors can register by providing their user ID, name, password, phone number, and specialization.
- **Doctor Login**: 
  - Doctors can log in using their user ID and password. The system validates credentials before granting access.
- **Manage Personal Information**: 
  - After logging in, doctors can update their phone number and specialization.
- **Schedule Management**: 
  - Doctors can view all their existing schedules.
  - Doctors can add new channeling schedules by specifying the date, time, and patient limit.
  - Doctors can update or delete existing schedules. Each doctor is restricted to creating only one schedule per date.

### Patient Features
- **Patient Registration**: 
  - Patients can register by providing their NIC, name, phone number, and password.
- **Patient Login**: 
  - Patients can log in using their NIC and password. The system validates credentials before granting access.
- **Manage Personal Information**: 
  - After logging in, patients can update their phone number.
- **View Available Schedules**: 
  - Patients can view all available channeling schedules for different doctors.
- **Appointment Booking**: 
  - Patients can book an appointment for a selected schedule. The system ensures that the total number of appointments does not exceed the patient limit set by the doctor.

## Technologies Used
- **Java Servlets**: For server-side logic and handling HTTP requests.
- **JSP (Java Server Pages)**: For creating dynamic web content.
- **MySQL**: For the database management.
- **Apache Tomcat**: As the web server to deploy the web application.
- **NetBeans IDE**: For development and managing the project.

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/E-channeling-system.git
   cd E-channeling-system ```

2. **Install and Configure MySQL**

1. **Download and install MySQL**: 
   - Visit the [MySQL Official Website](https://dev.mysql.com/downloads/).
   - Download the installer for your operating system and follow the installation instructions.

2. **Start MySQL server**:
   - Open MySQL Workbench or your preferred MySQL client and start the server.

3. **Create the database and tables**:
   - Open a SQL query window in MySQL Workbench or your preferred SQL tool.
   - Run the SQL scripts provided in the [Database Design](#database-design) section to create the necessary database and tables.

3. **Configure Apache Tomcat**

1. **Download and install Apache Tomcat**:
   - Visit the [Tomcat Official Website](https://tomcat.apache.org/).
   - Download the appropriate version for your operating system and follow the installation instructions.

2. **Configure Tomcat to work with your IDE**:
   - **NetBeans**:
     - Open NetBeans IDE.
     - Go to `Tools` > `Servers`.
     - Click `Add Server` and select `Apache Tomcat`.
     - Follow the prompts to configure Tomcat with NetBeans.


## Screenshots

The E-Channeling System is designed for both doctors and patients, with separate web pages for each. For example, doctors and patients have separate login and signup pages, and certain functionalities are accessible only by doctors.

Home Page

Doctor Registration Page

Doctor Login Page

Doctor Profile Page

Update Doctor Information Page

Managing Schedules

Patient Registration Page

Patient Login Page

Patient Profile Page

Update Patient Profile Page
