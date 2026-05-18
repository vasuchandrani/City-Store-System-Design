-- IDENTITY DOMAIN


-- SYSTEM DOMAIN

-- SYSTEM ADMIN

CREATE TABLE public.system_admin (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL UNIQUE,

    password_hash VARCHAR(500) NOT NULL,

    is_super_admin BOOLEAN NOT NULL DEFAULT FALSE,

    last_login_at TIMESTAMP,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- PAYMENT

CREATE TABLE public.payment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    razorpay_order_id VARCHAR(255),
    razorpay_payment_id VARCHAR(255),
    razorpay_signature TEXT,

    amount DECIMAL(12,2) NOT NULL,
    currency VARCHAR(20) NOT NULL,

    payment_method VARCHAR(50),
    status VARCHAR(50) NOT NULL,

    invoice_url TEXT,

    paid_at TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- SUBSCRIPTION PLAN

CREATE TABLE public.subscription_plan (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name VARCHAR(150) NOT NULL,
    description TEXT,

    monthly_price DECIMAL(12,2),
    yearly_price DECIMAL(12,2),

    duration_days INTEGER NOT NULL,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- MODULE

CREATE TABLE public.module (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name VARCHAR(150) NOT NULL,
    description TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);





--   LOCATION DOMAIN
  
-- COUNTRY

CREATE TABLE public.country (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name VARCHAR(150) NOT NULL,
    code VARCHAR(20) NOT NULL UNIQUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- STATE

CREATE TABLE public.state (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    country_id BIGINT NOT NULL,

    name VARCHAR(150) NOT NULL,
    code VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_state_country
        FOREIGN KEY (country_id)
        REFERENCES public.country(id)
);

-- CITY

CREATE TABLE public.city (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    state_id BIGINT NOT NULL,

    name VARCHAR(150) NOT NULL,
    code VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_city_state
        FOREIGN KEY (state_id)
        REFERENCES public.state(id)
);

-- AREA

CREATE TABLE public.area (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    city_id BIGINT NOT NULL,

    name VARCHAR(150) NOT NULL,

    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_area_city
        FOREIGN KEY (city_id)
        REFERENCES public.city(id)
);


-- USER DOMAIN

-- USER

CREATE TABLE public.users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL UNIQUE,

    password_hash VARCHAR(500) NOT NULL,

    last_login_at TIMESTAMP,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--ROLE

CREATE TABLE public.role (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- USER ROLE

CREATE TABLE public.user_role (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    assigned_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id)
        REFERENCES public.users(id),

    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id)
        REFERENCES public.role(id)
);

-- USER ADDRESS

CREATE TABLE public.user_address (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    user_id BIGINT NOT NULL,

    label VARCHAR(100) NOT NULL,

    address_line_1 VARCHAR(255) NOT NULL,
    address_line_2 VARCHAR(255),

    landmark VARCHAR(255),

    area_id BIGINT NOT NULL,

    pincode VARCHAR(20),

    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),

    is_default BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_address_user
        FOREIGN KEY (user_id)
        REFERENCES public.users(id),

    CONSTRAINT fk_user_address_area
        FOREIGN KEY (area_id)
        REFERENCES public.area(id)
);


--   BUSINESS DOMAIN
  
-- BUSINESS CATEGORY

CREATE TABLE public.business_category (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    name VARCHAR(150) NOT NULL UNIQUE,
    description TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- BUSINESS

CREATE TABLE public.business (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_name VARCHAR(255) NOT NULL,
    business_slug VARCHAR(255) NOT NULL UNIQUE,

    description TEXT,

    logo_url TEXT,
    cover_image_url TEXT,

    category_id BIGINT NOT NULL,
    branch_name VARCHAR(255),

    created_by_user_id BIGINT NOT NULL,

    primary_phone_number VARCHAR(20) NOT NULL,
    primary_email VARCHAR(255) NOT NULL,

    website_url TEXT,

    establishment_year INTEGER,

    verification_status VARCHAR(50) NOT NULL,

    verified_at TIMESTAMP,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    account_holder_name VARCHAR(255),
    bank_name VARCHAR(255),
    account_number VARCHAR(100),
    ifsc_code VARCHAR(50),
    upi_id VARCHAR(255),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_business_category
        FOREIGN KEY (category_id)
        REFERENCES public.business_category(id),

    CONSTRAINT fk_business_created_by
        FOREIGN KEY (created_by_user_id)
        REFERENCES public.users(id)
);

-- BUSINESS ADDRESS

CREATE TABLE public.business_address (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    address_line_1 VARCHAR(255) NOT NULL,
    address_line_2 VARCHAR(255),

    landmark VARCHAR(255),

    area_id BIGINT NOT NULL,

    pincode VARCHAR(20),

    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_business_address_business
        UNIQUE (business_id),

    CONSTRAINT fk_business_address_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_business_address_area
        FOREIGN KEY (area_id)
        REFERENCES public.area(id)
);

-- BUSINESS DOCUMENT

CREATE TABLE public.business_document (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    document_name VARCHAR(255) NOT NULL,
    document_type VARCHAR(100) NOT NULL,

    file_url TEXT NOT NULL,

    issued_date DATE,
    expiry_date DATE,

    uploaded_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_business_document_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id)
);

-- BUSINESS ROLE

CREATE TABLE public.business_role (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    role_name VARCHAR(100) NOT NULL,

    assigned_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_business_role_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_business_role_user
        FOREIGN KEY (user_id)
        REFERENCES public.users(id)
);

-- BUSINESS SUBSCRIPTION

CREATE TABLE public.business_subscription (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    plan_id BIGINT NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    status VARCHAR(50) NOT NULL,

    payment_id BIGINT,

    invoice_url TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_business_subscription_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_business_subscription_plan
        FOREIGN KEY (plan_id)
        REFERENCES public.subscription_plan(id),

    CONSTRAINT fk_business_subscription_payment
        FOREIGN KEY (payment_id)
        REFERENCES public.payment(id)
);
  
-- BUSINESS SUBSCRIPTION HISTORY

CREATE TABLE public.business_subscription_history (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    plan_id BIGINT NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    status VARCHAR(50) NOT NULL,

    payment_id BIGINT,

    invoice_url TEXT,

    archived_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_business_subscription_history_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_business_subscription_history_plan
        FOREIGN KEY (plan_id)
        REFERENCES public.subscription_plan(id),

    CONSTRAINT fk_business_subscription_history_payment
        FOREIGN KEY (payment_id)
        REFERENCES public.payment(id)
);

-- BUSINESS MODULE

CREATE TABLE public.business_module (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    module_id BIGINT NOT NULL,

    assigned_by_admin_id BIGINT NOT NULL,

    assigned_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_business_module_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_business_module_module
        FOREIGN KEY (module_id)
        REFERENCES public.module(id),

    CONSTRAINT fk_business_module_admin
        FOREIGN KEY (assigned_by_admin_id)
        REFERENCES public.system_admin(id)
);

-- BUSINESS REGISTRATION REQUEST

CREATE TABLE public.business_registration_request (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    submitted_by_user_id BIGINT NOT NULL,

    business_name VARCHAR(255) NOT NULL,

    category_id BIGINT,
    category_name VARCHAR(255),

    description TEXT,

    contact_person_name VARCHAR(255) NOT NULL,

    contact_phone_number VARCHAR(20) NOT NULL,
    contact_email VARCHAR(255) NOT NULL,

    status VARCHAR(50) NOT NULL,

    rejection_reason TEXT,

    submitted_at TIMESTAMP NOT NULL,
    reviewed_at TIMESTAMP,

    CONSTRAINT fk_brr_user
        FOREIGN KEY (submitted_by_user_id)
        REFERENCES public.users(id),

    CONSTRAINT fk_brr_category
        FOREIGN KEY (category_id)
        REFERENCES public.business_category(id)
);

-- BUSINESS REGISTRATION DOCUMENT

CREATE TABLE public.business_registration_document (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_registration_request_id BIGINT NOT NULL,

    document_name VARCHAR(255) NOT NULL,
    document_type VARCHAR(100) NOT NULL,

    file_url TEXT NOT NULL,

    uploaded_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_brd_request
        FOREIGN KEY (business_registration_request_id)
        REFERENCES public.business_registration_request(id)
);

-- BUSINESS REGISTRATION REQUESTED MODULES

CREATE TABLE public.business_registration_requested_modules (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_registration_request_id BIGINT NOT NULL,

    module_id BIGINT NOT NULL,

    requested_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_brrm_request
        FOREIGN KEY (business_registration_request_id)
        REFERENCES public.business_registration_request(id),

    CONSTRAINT fk_brrm_module
        FOREIGN KEY (module_id)
        REFERENCES public.module(id)
);


--   PRODUCT COMMERCE DOMAIN

-- PRODUCT

CREATE TABLE public.product (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    name VARCHAR(255) NOT NULL,
    description TEXT,

    sku VARCHAR(150),

    price DECIMAL(12,2) NOT NULL,
    discount_price DECIMAL(12,2),

    image_url TEXT,

    status VARCHAR(50) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id)
);

-- INVENTORY

CREATE TABLE public.inventory (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    product_id BIGINT NOT NULL,

    available_quantity INTEGER NOT NULL,
    reserved_quantity INTEGER NOT NULL DEFAULT 0,

    low_stock_threshold INTEGER,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id)
        REFERENCES public.product(id)
);

-- PRODUCT ORDER

CREATE TABLE public.product_order (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,

    order_number VARCHAR(100) NOT NULL,

    total_amount DECIMAL(12,2) NOT NULL,

    order_status VARCHAR(50) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,

    payment_id BIGINT,

    placed_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_order_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_product_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.users(id),

    CONSTRAINT fk_product_order_payment
        FOREIGN KEY (payment_id)
        REFERENCES public.payment(id)
);

-- ORDER ITEM

CREATE TABLE public.order_item (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL,

    unit_price DECIMAL(12,2) NOT NULL,
    total_price DECIMAL(12,2) NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES public.product_order(id),

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES public.product(id)
);


--   SERVICE DOMAIN

-- SERVICE

CREATE TABLE public.service (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    name VARCHAR(255) NOT NULL,
    description TEXT,

    duration_minutes INTEGER NOT NULL,

    price DECIMAL(12,2) NOT NULL,

    status VARCHAR(50) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_service_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id)
);

-- APPOINTMENT

CREATE TABLE public.appointment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,

    appointment_date DATE NOT NULL,

    appointment_start_time TIME NOT NULL,
    appointment_end_time TIME NOT NULL,

    status VARCHAR(50) NOT NULL,

    payment_id BIGINT,

    notes TEXT,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_appointment_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_appointment_service
        FOREIGN KEY (service_id)
        REFERENCES public.service(id),

    CONSTRAINT fk_appointment_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.users(id),

    CONSTRAINT fk_appointment_payment
        FOREIGN KEY (payment_id)
        REFERENCES public.payment(id)
);


--   MARKETING DOMAIN
    
-- STORY

CREATE TABLE public.story (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    media_url TEXT NOT NULL,

    caption TEXT,

    expires_at TIMESTAMP NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_story_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id)
);

-- ADVERTISEMENT

CREATE TABLE public.advertisement (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    title VARCHAR(255) NOT NULL,

    description TEXT,

    media_url TEXT,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_advertisement_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id)
);

-- ACHIEVEMENT

CREATE TABLE public.achievement (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,

    title VARCHAR(255) NOT NULL,

    description TEXT,

    media_url TEXT,

    certificate_url TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_achievement_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id)
);

--   COMMUNICATION DOMAIN

-- INQUIRY

CREATE TABLE public.inquiry (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    business_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,

    subject VARCHAR(255) NOT NULL,

    message TEXT NOT NULL,

    status VARCHAR(50) NOT NULL,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_inquiry_business
        FOREIGN KEY (business_id)
        REFERENCES public.business(id),

    CONSTRAINT fk_inquiry_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.users(id)
);