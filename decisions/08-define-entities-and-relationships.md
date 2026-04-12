## Initial Entity and Relationships Identification

### Status
Database Design Phase

---

### Purpose

Identify the major entities currently present within the platform and discover the relationships between them before detailed schema design begins.

This document represents the initial entity discovery phase.

Entity validation and refinement will be performed in later design iterations.

---

### Identified Entities

Entities are grouped by domain.

---

### Identity Domain

#### User

Represents a person using the platform.

#### Role

Represents platform-wide authorization roles.

Examples:

* Customer
* Business Owner
* System Administrator

---

### Location Domain

#### City

Represents a city.

#### Area

Represents an area/locality within a city.

---

### Business Domain

#### Business

Represents a verified business operating on the platform.

#### Business Category

Represents the primary classification of a business.

Examples:

* Restaurant
* Clinic
* Hospital
* Salon
* Agency


#### Business Registration Request

Represents a business verification request submitted for approval.


#### Subscription Plan

Represents available subscription plans.


#### Business Subscription

Represents a business's subscription history.

---

### Module Domain

#### Module

Represents a platform capability.

Examples:

* Product Module
* Service Module
* Restaurant Module
* Story Module

---

### Product Commerce Domain

#### Product

Represents a product sold by a business.


#### Inventory

Represents inventory information for a product.


#### Product Order

Represents a customer order.


#### Order Item

Represents products included within an order.

---

### Service Domain

#### Service

Represents a service offered by a business.


#### Appointment

Represents a service booking.

---

### Marketing Domain

#### Story

Represents temporary business updates.


#### Advertisement

Represents promotional content.


#### Achievement

Represents certifications, awards, recognitions, and credibility indicators.

---

### Communication Domain

#### Inquiry

Represents customer-business communication.

---

### Identified Relationship Entities

The following entities primarily exist to represent relationships.

#### User Role

Connects:
* User
* Role


#### Business User

Connects:
* User
* Business

Represents ownership and management relationships.

Examples:

* Owner
* Co-Owner
* Manager
* Staff

---

#### Business Module

Connects:

* Business
* Module

Represents module assignment.

#### Order Item

Connects:

* Product Order
* Product

Represents products purchased within an order.

---

### Entity Relationships


### User ↔ Role

Relationship:
Many-to-Many

Implemented Through:
User Role

---

### User ↔ Business

Relationship:
Many-to-Many

Implemented Through:
Business User

---

### City ↔ Area

Relationship:
One-to-Many

---

### Area ↔ Business

Relationship:
One-to-Many

---

### Business Category ↔ Business

Relationship:
One-to-Many

---

### User ↔ Business Registration Request

Relationship:
One-to-Many

---

### Business Registration Request ↔ Business

Relationship:
One-to-One

---

### Subscription Plan ↔ Business Subscription

Relationship:
One-to-Many

---

### Business ↔ Business Subscription

Relationship:
One-to-Many

---

### Business ↔ Module

Relationship:
Many-to-Many

Implemented Through:
Business Module

---

### Business ↔ Product

Relationship:
One-to-Many

---

### Product ↔ Inventory

Relationship:
One-to-One

---

### User ↔ Product Order

Relationship:
One-to-Many

---

### Business ↔ Product Order

Relationship:
One-to-Many

---

### Product Order ↔ Product

Relationship:
Many-to-Many

Implemented Through:
Order Item

---

### Business ↔ Service

Relationship:
One-to-Many

---

### User ↔ Appointment

Relationship:
One-to-Many

---

### Business ↔ Appointment

Relationship:
One-to-Many

---

### Service ↔ Appointment

Relationship:
One-to-Many

---

### Business ↔ Story

Relationship:
One-to-Many

---

### Business ↔ Advertisement

Relationship:
One-to-Many

---

### Business ↔ Achievement

Relationship:
One-to-Many

---

### User ↔ Inquiry

Relationship:
One-to-Many

---

### Business ↔ Inquiry

Relationship:
One-to-Many

---

## Summary

### Primary Entities

* User
* Role
* City
* Area
* Business
* Business Category
* Business Registration Request
* Subscription Plan
* Business Subscription
* Module
* Product
* Inventory
* Product Order
* Service
* Appointment
* Story
* Advertisement
* Achievement
* Inquiry

---

### Relationship Entities

* User Role
* Business User
* Business Module
* Order Item

---

### Total Identified Entities

Primary Entities: 19

Relationship Entities: 4

Total: 23

---

### Next Step

Validate identified entities.

Determine:

* Missing entities
* Incorrect entities
* Relationship corrections
* Final aggregate boundaries

before database schema design begins.
