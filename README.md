# CityRide Lite

CityRide Lite is a console-based public transport fare calculation system developed in Java.

## Features

- Add and remove journeys
- Zone-based fare calculation (1–5)
- Peak and Off-Peak pricing
- Passenger discounts (Adult, Student, Child, Senior Citizen)
- Daily fare caps
- Journey filtering (date, zone, passenger type, time band)
- Daily summary statistics
- Running totals by passenger type

## Technical Highlights

- Object-Oriented Design
- Separation of concerns (multiple classes)
- BigDecimal for precise financial calculations
- Input validation and error handling
- Recalculation logic after deletion (daily cap logic preserved)
- Clean modular structure

## Project Structure

- `CityRideLite` – Main entry point
- `CityRideState` – Stores program state
- `Journey` – Data model
- `FareCalculator` – Pricing logic
- `InputHelper` – Input validation
- `Money` – Financial rounding utilities
- `CityRideDataset` – Static pricing data
