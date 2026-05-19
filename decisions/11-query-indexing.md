## Query Analysis for Indexing

### Status
Database Optimization Phase

---

### Purpose

Indexes should exist because the application frequently executes specific queries.

This document identifies the most common queries expected in the system. These queries were used to determine the indexing strategy.

---

## System Domain

### SystemAdmin

Common Queries:

* Login using email
* Fetch administrator profile
* List active administrators

---

### Payment

Common Queries:

* Fetch payment using Razorpay Order ID
* Fetch payment using Razorpay Payment ID
* Verify payment status
* Retrieve payment invoice

---

### SubscriptionPlan

Common Queries:

* List active subscription plans
* View plan details

---

### Module

Common Queries:

* List all available modules
* View module details

---

## Location Domain

### Country

Common Queries:

* List supported countries

---

### State

Common Queries:

* Fetch states belonging to a country

---

### City

Common Queries:

* Fetch cities belonging to a state

---

### Area

Common Queries:

* Search areas within a city
* Load area suggestions while searching
* Retrieve area coordinates

---

## Identity Domain

### User

Common Queries:

* Login using email or phone number
* Fetch user profile
* Verify account status

---

### UserRole

Common Queries:

* Retrieve roles assigned to a user
* Check user permissions

---

### UserAddress

Common Queries:

* Fetch saved addresses of a user
* Retrieve default address
* Update address information

---

## Business Domain

### Business

Common Queries:

* Discover businesses by category
* Discover verified businesses
* Discover active businesses
* Open business profile using slug
* Fetch businesses created by a user

---

### BusinessCategory

Common Queries:

* List available business categories
* Select category during registration

---

### BusinessAddress

Common Queries:

* Retrieve business address
* Find businesses within an area
* Find nearby businesses

---

### BusinessDocument

Common Queries:

* Retrieve verification documents
* Review uploaded business documents

---

### BusinessRole

Common Queries:

* Retrieve users associated with a business
* Retrieve businesses associated with a user
* Check business permissions

---

### BusinessSubscription

Common Queries:

* Retrieve active subscription
* Check subscription validity

---

### BusinessSubscriptionHistory

Common Queries:

* View subscription history of a business

---

### BusinessModule

Common Queries:

* Retrieve modules assigned to a business

---

### BusinessRegistrationRequest

Common Queries:

* List pending registration requests
* View registration request details

---

### BusinessRegistrationDocument

Common Queries:

* Retrieve uploaded registration documents

---

### BusinessRegistrationRequestedModules

Common Queries:

* Retrieve requested modules for a registration request

---

## Product Commerce Domain

### Product

Common Queries:

* Retrieve products of a business
* Search products
* View product details

---

### Inventory

Common Queries:

* Check available stock

---

### ProductOrder

Common Queries:

* Retrieve orders of a business
* Retrieve orders of a customer
* Track order status
* View order history

---

### OrderItem

Common Queries:

* Retrieve products belonging to an order

---

## Service Domain

### Service

Common Queries:

* Retrieve services offered by a business
* View service details

---

### Appointment

Common Queries:

* Retrieve appointments of a business
* Retrieve appointments of a customer
* View upcoming appointments
* View appointment history

---

## Marketing Domain

### Story

Common Queries:

* View active stories of a business

---

### Advertisement

Common Queries:

* View active advertisements

---

### Achievement

Common Queries:

* View achievements of a business

---

## Communication Domain

### Inquiry

Common Queries:

* Retrieve inquiries received by a business
* Retrieve inquiries submitted by a customer
* Track inquiry status

---

### Outcome

The indexing strategy was derived from the above query patterns.
