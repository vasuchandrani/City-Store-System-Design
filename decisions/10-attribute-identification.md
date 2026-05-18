## Attribute Identification

### Status
Database Design Phase

---

## Purpose

This document identifies the attributes required for each entity before physical database schema design begins.

The goal is to ensure that all necessary information is captured while avoiding premature database implementation decisions.

Data types, constraints, indexes, and SQL definitions will be finalized in later stages.

---

## Identity Domain

### User

Represents a platform user.

Attributes:

* id
* first_name
* last_name
* email
* phone_number
* password_hash
* last_login_at
* is_active
* created_at
* updated_at

---

### SystemAdmin

Represents an internal platform administrator.

Attributes:

* id
* first_name
* last_name
* email
* phone_number
* password_hash
* is_super_admin
* last_login_at
* is_active
* created_at
* updated_at

---

### Role

Represents a platform-wide role.

Attributes:

* id
* name
* description
* created_at

---

### UserAddress

Represents a saved user address.

Attributes:

* id
* user_id
* label
* address_line_1
* address_line_2
* landmark
* area_id
* pincode
* latitude
* longitude
* is_default
* created_at
* updated_at

---

### UserRole

Attributes:

* id
* user_id
* role_id
* assigned_at

---

## Location Domain

### Country

Attributes:

* id
* name
* code
* created_at

---

### State

Attributes:

* id
* country_id
* name
* code
* created_at

---

### City

Attributes:

* id
* state_id
* name
* code
* created_at

---

### Area

Attributes:

* id
* city_id
* name
* latitude
* longitude
* created_at

---

## Business Domain

### Business

Represents a verified business.

Attributes:

* id
* business_name
* business_slug
* description
* logo_url
* cover_image_url
* branch_name
* category_id
* created_by_user_id
* primary_phone_number
* primary_email
* website_url
* establishment_year
* verification_status
* verified_at
* is_active
* created_at
* updated_at

* account_holder_name
* bank_name
* account_number
* ifsc_code
* upi_id

---

### BusinessCategory

Attributes:

* id
* name
* description
* created_at

---

### BusinessAddress

Represents a business location.

Attributes:

* id
* business_id
* address_line_1
* address_line_2
* landmark
* area_id
* pincode
* latitude
* longitude
* created_at
* updated_at

---

### BusinessRegistrationRequest

Represents a pending registration request.

Attributes:

* id
* submitted_by_user_id
* business_name
* category_id
* category_name
* description
* contact_person_name
* contact_phone_number
* contact_email
* status
* rejection_reason
* submitted_at
* reviewed_at

---

### BusinessRegistrationDocument

* id
* business_registration_request_id
* document_name
* document_type
* file_url
* uploaded_at


### BusinessRegistrationRequestedModules

* id
* business_registration_request_id
* module_id
* requested_at

### BusinessDocument

* id
* business_id
* document_name
* document_type
* file_url
* issued_date
* expiry_date
* uploaded_at
* updated_at

### BusinessRole

Represents user membership inside a business.

Attributes:

* id
* business_id
* user_id
* role_name
* assigned_at

---

## Subscription Domain

### SubscriptionPlan

Attributes:

* id
* name
* description
* monthly_price
* yearly_price
* duration_days
* is_active
* created_at

---

### BusinessSubscription

Represents currently active subscriptions.

Attributes:

* id
* business_id
* plan_id
* start_date
* end_date
* status
* payment_id
* invoice_url
* created_at

---

### BusinessSubscriptionHistory

Represents historical subscription records.

Attributes:

* id
* business_id
* plan_id
* start_date
* end_date
* status
* payment_id
* invoice_url
* archived_at

---

## Module Domain

### Module

Attributes:

* id
* name
* description
* created_at

---

### BusinessModule

Attributes:

* id
* business_id
* module_id
* assigned_by_admin_id
* assigned_at

---

## Product Commerce Domain

### Product

Attributes:

* id
* business_id
* name
* description
* sku
* price
* discount_price
* image_url
* status
* created_at
* updated_at

---

### Inventory

Attributes:

* id
* product_id
* available_quantity
* reserved_quantity
* low_stock_threshold
* updated_at

---

### ProductOrder

Attributes:

* id
* business_id
* customer_id
* order_number
* total_amount
* order_status
* payment_status
* payment_id
* placed_at
* updated_at

---

### OrderItem

Attributes:

* id
* order_id
* product_id
* quantity
* unit_price
* total_price

---

## Service Domain

### Service

Attributes:

* id
* business_id
* name
* description
* duration_minutes
* price
* status
* created_at
* updated_at

---

### Appointment

Attributes:

* id
* business_id
* service_id
* customer_id
* appointment_date
* appointment_start_time
* appointment_end_time
* status
* payment_id
* notes
* updated_at
* created_at

---

## Marketing Domain

### Story

Attributes:

* id
* business_id
* media_url
* caption
* expires_at
* created_at

---

### Advertisement

Attributes:

* id
* business_id
* title
* description
* media_url
* start_date
* end_date
* created_at

---

### Achievement

Attributes:

* id
* business_id
* title
* description
* media_url
* certificate_url
* created_at

---

## Communication Domain

### Inquiry

Attributes:

* id
* business_id
* customer_id
* subject
* message
* status
* updated_at
* created_at

---

## Payment Domain

### Payment

* id
* razorpay_order_id
* razorpay_payment_id
* razorpay_signature
* amount
* currency
* payment_method
* status
* invoice_url
* paid_at
* updated_at
* created_at


## Next Step

Convert approved attributes into physical database schema definitions, constraints, foreign keys, indexes, and SQL scripts.
