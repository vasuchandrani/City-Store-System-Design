## Final Entity and Relationships

### ER - scatch

![Final ER Diagram](../assets/ER-scatch.jpeg)


### Changes

### Location Hierarchy

The platform will maintain the following location hierarchy:

```text
 Country
    ↓
  State
    ↓
   City
    ↓
   Area
```

Although the current platform primarily operates at the City and Area level, Country and State have been retained in the model for future scalability.

This decision supports:

* Expansion into multiple countries
* Future regulatory requirements
* Improved geographical analytics

---

### Subscription Architecture

The subscription model has been finalized as follows.

#### Active Subscriptions

```text
Business
    1 ---- * BusinessSubscription

SubscriptionPlan
    1 ---- * BusinessSubscription
```

BusinessSubscription represents currently active subscriptions.

---

#### Subscription History

```text
Business
    1 ---- * BusinessSubscriptionHistory

SubscriptionPlan
    1 ---- * BusinessSubscriptionHistory
```

BusinessSubscriptionHistory represents historical subscription records and renewal history.

This separation allows active subscriptions and historical subscription data to be managed independently.

---

## Final Entities

### Identity Domain

* User
* Role
* UserAddress

---

### Location Domain

* Country
* State
* City
* Area

---

### Business Domain

* Business
* BusinessCategory
* BusinessAddress
* BusinessRegistrationRequest
* SubscriptionPlan
* BusinessSubscription
* BusinessSubscriptionHistory

---

### Module Domain

* Module

---

### Product Commerce Domain

* Product
* Inventory
* ProductOrder

---

### Service Domain

* Service
* Appointment

---

### Marketing Domain

* Story
* Advertisement
* Achievement

---

### Communication Domain

* Inquiry

---

## Relationship Entities

* UserRole
* BusinessRole
* BusinessModule
* OrderItem

---

## Final Relationships

### User ↔ Role

Many-to-Many

Implemented Through:
UserRole

---

### User ↔ UserAddress

One-to-Many

---

### Country ↔ State

One-to-Many

---

### State ↔ City

One-to-Many

---

### City ↔ Area

One-to-Many

---

### Area ↔ UserAddress

One-to-Many

---

### Area ↔ BusinessAddress

One-to-Many

---

### User ↔ Business

Many-to-Many

Implemented Through:
BusinessRole

---

### BusinessCategory ↔ Business

One-to-Many

---

### User ↔ BusinessRegistrationRequest

One-to-Many

---

### BusinessRegistrationRequest ↔ Business

One-to-One

---

### Business ↔ BusinessAddress

One-to-Many

---

### Business ↔ Module

Many-to-Many

Implemented Through:
BusinessModule

---

### Business ↔ BusinessSubscription

One-to-Many

---

### SubscriptionPlan ↔ BusinessSubscription

One-to-Many

---

### Business ↔ BusinessSubscriptionHistory

One-to-Many

---

### SubscriptionPlan ↔ BusinessSubscriptionHistory

One-to-Many

---

### Business ↔ Product

One-to-Many

---

### Product ↔ Inventory

One-to-One

---

### User ↔ ProductOrder

One-to-Many

---

### Business ↔ ProductOrder

One-to-Many

---

### ProductOrder ↔ Product

Many-to-Many

Implemented Through:
OrderItem

---

### Business ↔ Service

One-to-Many

---

### User ↔ Appointment

One-to-Many

---

### Business ↔ Appointment

One-to-Many

---

### Service ↔ Appointment

One-to-Many

---

### Business ↔ Story

One-to-Many

---

### Business ↔ Advertisement

One-to-Many

---

### Business ↔ Achievement

One-to-Many

---

### User ↔ Inquiry

One-to-Many

---

### Business ↔ Inquiry

One-to-Many

---

## Next Step

Attribute Identification and Detailed Schema Design.
