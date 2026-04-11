## User Identity Architecture

### Status

Database design phase.

---

### Context

While designing the platform database, a major question emerged:

How should people be represented inside the system?

The platform currently supports:

- Customers
- Business Owners
- System Administrators

Future requirements may include:

- Multiple owners for a business
- Business managers
- Business staff members
- One person owning multiple businesses
- Business owners acting as customers

The identity model must support these scenarios without requiring database redesign.

---

### Initial Assumption

Separate entities were considered for:

- Customer
- Business Owner
- System Administrator

At first this appeared logical because different applications may exist for different users.

Examples:

- Customer Application
- Business Owner Dashboard
- Admin Portal

---

### Observation

Applications and identities are separate concerns.

A single individual may simultaneously be:

- Customer
- Business Owner

Example:

A restaurant owner may browse businesses, compare products, book appointments, and submit inquiries just like any other customer.

Creating multiple accounts for the same individual introduces unnecessary complexity.

---

### Problems With Separate Identity Models

Using separate identity tables creates several challenges:

- Duplicate accounts
- Duplicate credentials
- Duplicate profile management
- Difficult role transitions
- Complicated account linking

The same person may need multiple accounts despite being a single user.

---

### Realization

Business ownership is not a user type.

Business ownership is a relationship between:

- User
- Business

The same user may:

- Own multiple businesses
- Co-own businesses
- Manage businesses
- Act as a customer

Ownership should therefore be modeled separately from identity.

---

### Decision

The platform will adopt a unified user model.

A user represents a real person.

Roles determine platform permissions.

Examples:

- CUSTOMER
- BUSINESS_OWNER
- SYSTEM_ADMIN

A user may possess multiple roles simultaneously.

---

### Business Ownership Decision

Ownership will be modeled through a business-user relationship.

Examples:

- OWNER
- CO_OWNER
- MANAGER
- STAFF

This supports:

- Multiple owners
- Multiple businesses per owner
- Business teams
- Future permission expansion

without database redesign.

---

### Proposed Core Entities

- User
- Role
- UserRole
- Business
- BusinessUser

---

### Benefits

- Single login system
- Single profile per person
- Cleaner authentication
- Flexible authorization
- Future-proof ownership model
- Support for multiple business stakeholders

---

### Current Status

Future database design will proceed using a unified user identity model.