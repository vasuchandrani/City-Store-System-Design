-- CityShop Database Schema -version 1.0


-- CITY

CREATE TABLE public.city (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    state VARCHAR NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- SHOP

CREATE TABLE public.shop (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    description TEXT,
    address TEXT,
    category VARCHAR,
    banner_url TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    is_open BOOLEAN DEFAULT FALSE,
    is_verified BOOLEAN DEFAULT FALSE,
    city_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_shop_city
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


-- DELIVERY PARTNER

CREATE TABLE public.delivery_partner (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    full_name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    phone_no VARCHAR,
    upi_id VARCHAR,
    vehicle_detail TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    city_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_delivery_city
        FOREIGN KEY (city_id)
        REFERENCES public.city(id)
);


-- PAYMENT

CREATE TABLE public.payment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    total_amount NUMERIC NOT NULL CHECK (total_amount > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.customer(id)
);


-- ORDERS

CREATE TABLE public.orders (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_id BIGINT,
    delivery_partner_id BIGINT,

    CONSTRAINT fk_orders_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.customer(id),

    CONSTRAINT fk_orders_payment
        FOREIGN KEY (payment_id)
        REFERENCES public.payment(id),

    CONSTRAINT fk_orders_delivery
        FOREIGN KEY (delivery_partner_id)
        REFERENCES public.delivery_partner(id)
);


-- ORDER SHOP

CREATE TABLE public.order_shop (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL,
    shop_id BIGINT NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_os_order
        FOREIGN KEY (order_id)
        REFERENCES public.orders(id),

    CONSTRAINT fk_os_shop
        FOREIGN KEY (shop_id)
        REFERENCES public.shop(id)
);


-- PRODUCT

CREATE TABLE public.product (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    shop_id BIGINT NOT NULL,
    name VARCHAR NOT NULL,
    description TEXT,
    price NUMERIC NOT NULL CHECK (price >= 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    is_available BOOLEAN DEFAULT TRUE,
    low_quantity INTEGER DEFAULT 5 CHECK (low_quantity >= 0),
    stock_alert BOOLEAN DEFAULT FALSE,
    sold_quantity INTEGER DEFAULT 0 CHECK (sold_quantity >= 0),
    img_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_shop
        FOREIGN KEY (shop_id)
        REFERENCES public.shop(id)
);


-- REVENUE

CREATE TABLE public.revenue (
    shop_id BIGINT NOT NULL,
    revenue_date DATE NOT NULL,
    revenue_per_day NUMERIC NOT NULL CHECK (revenue_per_day >= 0),

    PRIMARY KEY (shop_id, revenue_date),

    CONSTRAINT fk_revenue_shop
        FOREIGN KEY (shop_id)
        REFERENCES public.shop(id)
);


-- SHOPKEEPER

CREATE TABLE public.shopkeeper (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    phone_no VARCHAR,
    upi_id VARCHAR,
    gst_no VARCHAR,
    shop_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_shopkeeper_shop
        FOREIGN KEY (shop_id)
        REFERENCES public.shop(id)
);


-- SHOP SUBSCRIPTION

CREATE TABLE public.shop_subscription (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    shopkeeper_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_subscription_shopkeeper
        FOREIGN KEY (shopkeeper_id)
        REFERENCES public.shopkeeper(id)
);

-- ADS

CREATE TABLE public.ads (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR NOT NULL,
    media_url TEXT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SHOP ADS

CREATE TABLE public.shop_ads (
    shop_id BIGINT NOT NULL,
    ads_id BIGINT NOT NULL,

    PRIMARY KEY (shop_id, ads_id),

    CONSTRAINT fk_shop_ads_shop
        FOREIGN KEY (shop_id)
        REFERENCES public.shop(id),

    CONSTRAINT fk_shop_ads_ads
        FOREIGN KEY (ads_id)
        REFERENCES public.ads(id)
);


-- SHOP PAYMENT

CREATE TABLE public.shop_payment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    shop_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    amount NUMERIC NOT NULL CHECK (amount > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sp_shop
        FOREIGN KEY (shop_id)
        REFERENCES public.shop(id),

    CONSTRAINT fk_sp_customer
        FOREIGN KEY (customer_id)
        REFERENCES public.customer(id),

    CONSTRAINT fk_sp_order
        FOREIGN KEY (order_id)
        REFERENCES public.orders(id)
);


-- LIST PAYMENT

CREATE TABLE public.list_payment (
    payment_id BIGINT NOT NULL,
    shop_payment_id BIGINT NOT NULL,

    PRIMARY KEY (payment_id, shop_payment_id),

    CONSTRAINT fk_lp_payment
        FOREIGN KEY (payment_id)
        REFERENCES public.payment(id),

    CONSTRAINT fk_lp_shop_payment
        FOREIGN KEY (shop_payment_id)
        REFERENCES public.shop_payment(id)
);


-- LIST PRODUCT ORDER

CREATE TABLE public.list_product_order (
    order_shop_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (order_shop_id, product_id),

    CONSTRAINT fk_lpo_order_shop
        FOREIGN KEY (order_shop_id)
        REFERENCES public.order_shop(id),

    CONSTRAINT fk_lpo_product
        FOREIGN KEY (product_id)
        REFERENCES public.product(id)
);


-- INDEXES

CREATE INDEX idx_shop_city
    ON public.shop(city_id);

CREATE INDEX idx_product_shop
    ON public.product(shop_id);

CREATE INDEX idx_order_customer
    ON public.orders(customer_id);

CREATE INDEX idx_order_delivery_partner
    ON public.orders(delivery_partner_id);

CREATE INDEX idx_order_shop_order
    ON public.order_shop(order_id);

CREATE INDEX idx_order_shop_shop
    ON public.order_shop(shop_id);

CREATE INDEX idx_shopkeeper_shop
    ON public.shopkeeper(shop_id);

CREATE INDEX idx_shop_payment_order
    ON public.shop_payment(order_id);

CREATE INDEX idx_shop_payment_shop
    ON public.shop_payment(shop_id);

CREATE INDEX idx_customer_city
    ON public.customer(city);

CREATE INDEX idx_delivery_partner_city
    ON public.delivery_partner(city_id);