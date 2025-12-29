# Travel Agency Management System ✈️

A console-based travel agency management system built with Spring Boot that allows users to create and manage travel packages with automated discount calculations and customer loyalty rewards.

## Features

### 📦 Travel Package Management
- **Manual Package Creation**: Interactive console interface to create custom travel packages
- **Auto-Random Generation**: Automatically generate random travel packages for testing
- **Multiple Reservations**: Support for multiple reservations per travel package
- **Formatted Receipts**: Beautiful, formatted receipts with customer and reservation details

### 💰 Discount System
The system automatically calculates and applies multiple types of discounts:

- **Full Package Discount (10%)**: Applied when a reservation includes hotel, meals, flights, and transport
- **Hotel/Flight Discount (5%)**: Applied when a package contains 2+ hotel reservations OR 2+ flight reservations
- **Cashback Discount (10%)**: Applied to customers who have earned cashback from previous purchases

### 🎁 Customer Loyalty Program
- Customers who purchase 2 or more travel packages earn 5% cashback
- Cashback is automatically applied to their next purchase
- Customer data persists across sessions using `LinkedHashSet` to maintain order and prevent duplicates

### 📊 Analytics
- View all customers and their cashback status
- View all travel packages with detailed breakdowns
- Calculate profit average across all packages

## Technology Stack

- **Java 17**
- **Spring Boot 4.0.1**
- **Maven** for dependency management
- **Java Collections Framework** (LinkedHashSet, List, Stream API)

## Project Structure

```
src/main/java/com/example/agenciadeviagens/
├── models/
│   ├── Customer.java           # Customer entity with ID and cashback
│   ├── Reservation.java        # Individual reservation with services
│   └── TravelPackage.java      # Travel package with discounts
├── services/
│   ├── Actions.java            # Interface for package creation
│   ├── AutoRandomCreation.java # Random package generator
│   ├── ManualPackageCreator.java # Manual package creation
│   ├── CustomerServices.java   # Customer CRUD operations
│   └── Discounts.java          # Discount calculation engine
├── helper/
│   ├── Dummies.java            # Data management and dummy data
│   ├── IdCreator.java          # Sequential ID generator
│   ├── Inputs.java             # User input utilities
│   └── Randoms.java            # Random data generators
├── utils/
│   └── ReceiptFormatter.java   # Receipt formatting utilities
└── AppRunner.java              # Main application entry point
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd AgenciaDeViagens
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

## Usage

When you start the application, you'll see an interactive menu:

```
✈️ Travel Agency
>>>>>> Menu <<<<<<<
1. Manually create a new Travel Package
2. Run auto-random Travel Package creation
3. Print customers
4. Print Travel Packages
5. Print Travel Packages Profit Average
6. Exit
```

### Creating a Manual Travel Package

1. Select option `1` from the menu
2. Enter customer name (existing or new)
3. Specify number of reservations
4. For each reservation, indicate:
   - Hotel included? (yes/no)
   - Meals included? (yes/no)
   - Flights included? (yes/no)
   - Transport included? (yes/no)
   - Total price
5. The system automatically calculates applicable discounts
6. A formatted receipt is displayed

### Auto-Random Package Creation

Select option `2` to automatically generate a random travel package with:
- Random customer selection from existing customers
- Random number of reservations (1-3)
- Random services for each reservation
- Random pricing

## Discount Calculation Logic

The discount system uses a **base-price calculation approach**:

1. All discounts are calculated from the **original total price**
2. Individual discounts are tracked separately:
   - `discountAppliedByFullTravelPackage`
   - `discountAppliedByHotelFlight`
   - `discountAppliedByCashback`
3. Total discount is the sum of all applicable discounts
4. Final price = Original price - Total discount

This ensures accurate discount calculations without compounding effects.

## Key Design Patterns

### LinkedHashSet for Customer Management
The application uses `LinkedHashSet<Customer>` to:
- Prevent duplicate customers
- Maintain insertion order
- Allow efficient lookups

### Interface-Based Design
The `Actions` interface provides a contract for both manual and automatic package creation, with shared default methods for common operations.

### Stream API
Extensive use of Java Streams for:
- Filtering reservations by type
- Calculating totals
- Customer lookups
- Cashback eligibility checks

## Data Models

### Customer
- `id`: Unique identifier (auto-generated)
- `name`: Customer name
- `cashback`: Available cashback percentage (0.0 - 0.05)

### Reservation
- `hotel`: Boolean flag
- `meal`: Boolean flag
- `flight`: Boolean flag
- `transport`: Boolean flag
- `total`: Reservation price
- `discount`: Applied discount amount

### TravelPackage
- `customer`: Associated customer
- `total`: Final price after discounts
- `reservations`: List of reservations
- `discountAppliedByCashback`: Cashback discount amount
- `discountAppliedByHotelFlight`: Hotel/flight discount amount
- `discountAppliedByFullTravelPackage`: Full package discount amount
- `totalDiscount`: Sum of all discounts

## Receipt Format

The system generates beautifully formatted receipts with:
- Customer information
- Detailed reservation breakdown
- Subtotal and discount information
- Cashback availability
- Final total with currency formatting
- ANSI color codes for enhanced readability

## Future Enhancements

Potential improvements for the system:
- Database persistence (currently in-memory)
- REST API endpoints
- Web interface
- Payment processing integration
- Date-based reservations
- Destination management
- Multi-currency support
- Advanced reporting and analytics

## License

This project is available for educational purposes.

## Author

Developed as a Spring Boot learning project demonstrating:
- Object-oriented design principles
- Spring dependency injection
- Java collections framework
- Business logic implementation
- Console-based user interaction
