-- INDEXES


-- System Domain

-- System Admin
-- No additional indexes required.


-- Payment
CREATE INDEX idx_payment_status
ON payment(status);


-- SubscriptionPlan
-- No additional indexes required.


-- Module
-- No additional indexes required.



-- Location Domain

-- Country
-- No additional indexes required.


-- State
CREATE INDEX idx_state_country_id
ON state(country_id);


-- City
CREATE INDEX idx_city_state_id
ON city(state_id);


-- Area
CREATE INDEX idx_area_city_id
ON area(city_id);



-- Identity Domain

-- User
-- No additional indexes required.


-- UserRole
-- No additional indexes required.
-- Unique index already exists:
-- UNIQUE(user_id, role_id)


-- UserAddress
CREATE INDEX idx_user_address_user_id
ON user_address(user_id);

CREATE INDEX idx_user_address_area_id
ON user_address(area_id);



-- Business Domain

-- Business
CREATE INDEX idx_business_category_id
ON business(category_id);

CREATE INDEX idx_business_created_by_user_id
ON business(created_by_user_id);

CREATE INDEX idx_business_discovery
ON business(
    category_id,
    verification_status,
    is_active
);


-- BusinessCategory
-- No additional indexes required.


-- BusinessAddress
CREATE INDEX idx_business_address_business_id
ON business_address(business_id);

CREATE INDEX idx_business_address_area_id
ON business_address(area_id);


-- BusinessDocument
CREATE INDEX idx_business_document_business_id
ON business_document(business_id);


-- BusinessRole
CREATE INDEX idx_business_role_business_id
ON business_role(business_id);

CREATE INDEX idx_business_role_user_id
ON business_role(user_id);


-- BusinessSubscription
CREATE INDEX idx_business_subscription_business_id
ON business_subscription(business_id);

CREATE INDEX idx_business_subscription_plan_id
ON business_subscription(plan_id);

CREATE INDEX idx_business_subscription_payment_id
ON business_subscription(payment_id); -- FK


-- BusinessSubscriptionHistory
CREATE INDEX idx_business_subscription_history_business_id
ON business_subscription_history(business_id);

CREATE INDEX idx_business_subscription_history_plan_id
ON business_subscription_history(plan_id);

CREATE INDEX idx_business_subscription_history_payment_id
ON business_subscription_history(payment_id); -- FK


-- BusinessModule
CREATE INDEX idx_business_module_business_id
ON business_module(business_id);


-- BusinessRegistrationRequest
CREATE INDEX idx_brr_user
ON business_registration_request(submitted_by_user_id);

CREATE INDEX idx_brr_status
ON business_registration_request(status);


-- BusinessRegistrationDocument
CREATE INDEX idx_brd_request
ON business_registration_document(
    business_registration_request_id
);


-- BusinessRegistrationRequestedModules
-- No additional indexes required.



-- Product Commerce Domain

-- Product
CREATE INDEX idx_product_business
ON product(business_id);


-- Inventory
-- No additional indexes required.
-- Unique index already exists:
-- UNIQUE(product_id)


-- ProductOrder
CREATE INDEX idx_product_order_business
ON product_order(business_id);

CREATE INDEX idx_product_order_customer
ON product_order(customer_id);

CREATE INDEX idx_product_order_payment_id
ON product_order(payment_id); -- FK

CREATE INDEX idx_product_order_business_status
ON product_order(
    business_id,
    order_status
);

CREATE INDEX idx_product_order_customer_status
ON product_order(
    customer_id,
    order_status
);

-- OrderItem
-- No additional indexes required.



-- Service Domain

-- Service
CREATE INDEX idx_service_business
ON service(business_id);


-- Appointment
CREATE INDEX idx_appointment_business
ON appointment(business_id);

CREATE INDEX idx_appointment_customer
ON appointment(customer_id);

CREATE INDEX idx_appointment_payment_id
ON appointment(payment_id); --FK

CREATE INDEX idx_appointment_customer_date
ON appointment(
    customer_id,
    appointment_date
);

CREATE INDEX idx_appointment_business_date
ON appointment(
    business_id,
    appointment_date
);

-- AppointmentService
-- No additional indexes required.



-- Marketing Domain

-- Story
-- No additional indexes required.

-- Advertisement
-- No additional indexes required.

-- Achievement
-- No additional indexes required.


-- Communication Domain

-- Inquiry
-- No additional indexes required.