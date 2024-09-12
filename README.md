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
- [License](#license)

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
[Insert Class Diagram Here]

### Database Design

The system uses a MySQL database named `e_channeling` to store persistent data. Below are the SQL scripts to create database and the necessary tables:

-- Create the database
CREATE DATABASE e_channeling;

-- Use the database
USE e_channeling;

-- Create the Doctor table
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
