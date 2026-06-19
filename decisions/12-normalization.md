## Database Normalization Review

### Status
Database Design Validation Phase

---

### Purpose

This document validates that the CityStore database follows normalization principles and avoids unnecessary redundancy.

---

## First Normal Form (1NF)

Requirements:

* Every table has a primary key
* Attributes contain atomic values
* No repeating groups exist

Validation:

* Every table contains a unique identifier
* Multi-value data has been separated into relationship tables
* No column stores multiple values

Examples:

* UserRole
* BusinessRole
* BusinessModule
* OrderItem
* BusinessRegistrationRequestedModules

Result:

✓ Database satisfies First Normal Form.

---

## Second Normal Form (2NF)

Requirements:

* Database must satisfy 1NF
* Non-key attributes must depend on the entire primary key

Validation:

* Surrogate primary keys are used throughout the schema
* No partial dependencies exist

Examples:

* Product
* Business
* Service
* Appointment
* Payment

Result:

✓ Database satisfies Second Normal Form.

---

## Third Normal Form (3NF)

Requirements:

* Database must satisfy 2NF
* Non-key attributes must not depend on other non-key attributes

Validation:

- Location information has been separated into:
    - Country → State → City → Area

- Roles are separated from users.
- Business categories are separated from businesses.
- Modules are separated from businesses.
- Subscription plans are separated from subscriptions.

Result:

✓ Database satisfies Third Normal Form.

---

## Boyce-Codd Normal Form (BCNF)

Requirements:

Every determinant must be a candidate key.

Validation:

Many-to-many relationships have been separated into dedicated relationship tables.

Examples:

* UserRole
* BusinessRole
* BusinessModule
* OrderItem
* BusinessRegistrationRequestedModules

Business permissions, modules, subscriptions, and user assignments are represented through relationship tables rather than duplicated data.

Result:

✓ Database satisfies Boyce-Codd Normal Form (BCNF).

---

## Controlled Denormalization

A small amount of controlled flexibility exists during business onboarding.

Example:

BusinessRegistrationRequest contains:

* category_id
* category_name

This allows business owners to suggest categories not currently available in the system.

The final Business entity references only an approved category.

This does not violate the normalized production model.

---

## Conclusion

The CityStore database:

✓ Satisfies 1NF

✓ Satisfies 2NF

✓ Satisfies 3NF

✓ Satisfies BCNF

The schema is considered normalized, scalable, and suitable for production use while remaining flexible enough to support future business categories and modules.
