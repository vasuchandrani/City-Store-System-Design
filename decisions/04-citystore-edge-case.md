## CityStore Naming & Business Model Edge Cases

### Status
Under Evaluation

---

## Context

After redefining the platform from CityShop to CityStore, the product direction became much stronger.

The platform was no longer focused on delivery.

Instead, it focused on helping people discover local stores, compare products and services, view catalogs, check pricing, book appointments, and communicate with store owners.

The new idea solved several real-world problems:

- Product price comparison across local stores
- Service discovery
- Appointment booking
- Store visibility
- Store-owned delivery options

The customer value proposition became significantly stronger.

However, while reviewing the idea, an important concern was raised.

---

## Problem Identified

A friend questioned the fundamental assumption behind the platform.

The question was simple:

> "Why are you calling everything a store?"

Not every business/profession is a store.

Examples:

- Hospitals are not stores
- Clinics are not stores
- Lawyer offices are not stores
- Chartered Accountant offices are not stores
- Coaching centers are not stores
- Software agencies are not stores

The platform was attempting to solve a city-wide business/professions discovery problem.

But the name and data model were centered around stores.

This introduced a mismatch between the product vision and the platform terminology.

---

## Second Problem Identified

Different businesses operate in completely different ways.

For example:

### Grocery Shop

Offers:

- Products
- Inventory
- Orders

Needs:

- Stock management
- Product catalog

---

### Salon

Offers:

- Services
- Products

Needs:

- Appointment booking
- Product catalog
- Staff management

---

### Hospital

Offers:

- Services

Needs:

- Doctor profiles
- Appointment booking
- Schedules

---

### Lawyer Office

Offers:

- Consultation

Needs:

- Inquiry management
- Appointment booking

---

### Restaurant

Offers:

- Food

Needs:

- Menu
- Table booking
- Delivery orders

---

## Third Problem Identified

Businesses are not strictly product-based or service-based.

Many businesses are hybrids.

Examples:

### Salon

Provides:

- Haircut services
- Beauty services
- Hair-care products

---

### Fitness Center

Provides:

- Membership services
- Supplements

---

### Clinic

Provides:

- Consultation services
- Medicines
- Health packages

---

The assumption that every business belongs to a single category was incorrect.

Many businesses naturally span multiple business models.

---

## Realization

The problem being solved is not:

> "How do we put every store online?"

The real problem is:

> "How do we make every local business discoverable online?"

These are fundamentally different problems.

---

## Design Concern

If the platform continues using a rigid store-centric architecture:

- New business types will continuously create exceptions
- New tables will be required for every profession
- Scaling the platform across industries will become difficult
- Product terminology will become increasingly inaccurate

This indicates that the platform architecture may need to evolve beyond the concept of a store.

---

## Need to answer following

1. What is the correct entity?

   - Store?
   - Business?
   - Provider?
   - Organization?

2. How platform can support all of the professions with their requirements?

3. How can the system remain flexible enough to support future business types?

4. What should be the name of product? :)

---

## Outcome

The business discovery vision remains valuable.
However, the platform can no longer assume:

- Every business is a store
- Every business sells either product or service
- Every business follows the same workflow

This decision marks the point where the architecture must evolve from a store-centric model toward a more flexible business-centric model.

Further research is required before proceeding with database design.