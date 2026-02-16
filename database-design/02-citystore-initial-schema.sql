-- CityStore Database


-- CITY

CREATE TABLE public.city (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    state VARCHAR NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- STORE

CREATE TABLE public.store (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    description TEXT,
    address TEXT,
    category VARCHAR,
    type VARCHAR NOT NULL CHECK (type IN ('PRODUCT', 'SERVICE')),
    phone_no VARCHAR,
    banner_url TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    is_open BOOLEAN DEFAULT FALSE,
    is_verified BOOLEAN DEFAULT FALSE,
    city_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_store_city
        FOREIGN KEY (city_id)
        REFERENCES public.city(id)
);


-- CUSTOMER

CREATE TABLE public.customer (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    phone_no VARCHAR,
    address TEXT,
    city BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_customer_city
        FOREIGN KEY (city)
        REFERENCES public.city(id)
);


-- STORE OWNER

CREATE TABLE public.store_owner (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    phone_no VARCHAR,
    upi_id VARCHAR,
    gst_no VARCHAR,
    store_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_store_owner_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id)
);


-- STORE SUBSCRIPTION

CREATE TABLE public.store_subscription (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    store_owner_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_subscription_store_owner
        FOREIGN KEY (store_owner_id)
        REFERENCES public.store_owner(id)
);


-- STORE SETTINGS

CREATE TABLE public.store_settings (
    store_id BIGINT PRIMARY KEY,

    accepts_orders BOOLEAN DEFAULT FALSE,
    accepts_appointments BOOLEAN DEFAULT FALSE,

    provides_delivery BOOLEAN DEFAULT FALSE,

    minimum_order_amount NUMERIC,
    free_delivery_threshold NUMERIC,

    CONSTRAINT fk_settings_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id)
);


-- PRODUCT

CREATE TABLE public.product (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    store_id BIGINT NOT NULL,
    name VARCHAR NOT NULL,
    description TEXT,
    price NUMERIC NOT NULL CHECK (price >= 0),
    stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0),
    is_available BOOLEAN DEFAULT TRUE,
    low_quantity INTEGER DEFAULT 5 CHECK (low_quantity >= 0),
    stock_alert BOOLEAN DEFAULT FALSE,
    sold_quantity INTEGER DEFAULT 0 CHECK (sold_quantity >= 0),
    img_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id)
);


-- SERVICE

CREATE TABLE public.service (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    store_id BIGINT NOT NULL,
    name VARCHAR NOT NULL,
    description TEXT,
    price NUMERIC CHECK (price >= 0),
    duration_minutes INTEGER,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_service_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id)
);


-- ORDERS

CREATE TABLE public.orders (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    store_id BIGINT NOT NULL,

    status VARCHAR NOT NULL,

    total_amount NUMERIC NOT NULL CHECK (total_amount >= 0),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_orders_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.customer(id),

    CONSTRAINT fk_orders_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id)
);


-- ORDER ITEM

CREATE TABLE public.order_item (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL CHECK (quantity > 0),

    price_at_purchase NUMERIC NOT NULL CHECK (price_at_purchase >= 0),

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES public.orders(id),

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES public.product(id)
);


-- APPOINTMENT

CREATE TABLE public.appointment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    customer_id BIGINT NOT NULL,
    store_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,

    appointment_time TIMESTAMP NOT NULL,

    status VARCHAR NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_appointment_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.customer(id),

    CONSTRAINT fk_appointment_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id),

    CONSTRAINT fk_appointment_service
        FOREIGN KEY (service_id)
        REFERENCES public.service(id)
);


-- ADS

CREATE TABLE public.ads (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR NOT NULL,
    media_url TEXT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- STORE ADS

CREATE TABLE public.store_ads (
    store_id BIGINT NOT NULL,
    ads_id BIGINT NOT NULL,

    PRIMARY KEY (store_id, ads_id),

    CONSTRAINT fk_store_ads_store
        FOREIGN KEY (store_id)
        REFERENCES public.store(id),

    CONSTRAINT fk_store_ads_ads
        FOREIGN KEY (ads_id)
        REFERENCES public.ads(id)
);


-- INDEXES

CREATE INDEX idx_store_city
    ON public.store(city_id);

CREATE INDEX idx_product_store
    ON public.product(store_id);

CREATE INDEX idx_service_store
    ON public.service(store_id);

CREATE INDEX idx_order_customer
    ON public.orders(customer_id);

CREATE INDEX idx_order_store
    ON public.orders(store_id);

CREATE INDEX idx_order_item_order
    ON public.order_item(order_id);

CREATE INDEX idx_appointment_customer
    ON public.appointment(customer_id);

CREATE INDEX idx_appointment_store
    ON public.appointment(store_id);