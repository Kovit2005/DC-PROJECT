-- Flyway migration: create trains and bookings tables and seed some trains
CREATE TABLE IF NOT EXISTS trains (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    route VARCHAR(255) NOT NULL,
    seats_available INT NOT NULL,
    price NUMERIC(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    train_id BIGINT REFERENCES trains(id),
    seats INT NOT NULL,
    total_price NUMERIC(12,2),
    booked_at TIMESTAMP
);

INSERT INTO trains (name, route, seats_available, price) VALUES
('InterCity 100', 'CityA - CityB', 200, 29.99),
('Coastal Express', 'CityB - CityC', 150, 39.50),
('Mountain Local', 'CityC - CityD', 80, 19.75)
ON CONFLICT DO NOTHING;
