-- Admin users
INSERT INTO user (name, email, password, address, phone_number, role, total_spent) VALUES ('John Admin', 'admin@agristore.com', 'admin123', '123 Main St, City Center', '0123456789', 'ADMIN', 0.0),
('Sarah Manager', 'manager@agristore.com', 'manager123', '456 Oak Ave, Downtown', '0123456790', 'ADMIN', 0.0);

-- Seller users
INSERT INTO user (name, email, password, address, phone_number, role, total_spent) VALUES
('Mike Farmer', 'mike.farmer@email.com', 'seller123', '789 Farm Road, Rural Area', '0123456791', 'SELLER', 0.0),
('Lisa Orchards', 'lisa.orchards@email.com', 'seller123', '321 Orchard Lane, Countryside', '0123456792', 'SELLER', 0.0),
('Tom Vegetables', 'tom.veggies@email.com', 'seller123', '654 Green Valley, Farm District', '0123456793', 'SELLER', 0.0);

-- Buyer users
INSERT INTO user (name, email, password, address, phone_number, role, total_spent) VALUES
('Alice Johnson', 'alice.johnson@email.com', 'buyer123', '111 Elm St, Suburb', '0123456794', 'BUYER', 150.50),
('Bob Smith', 'bob.smith@email.com', 'buyer123', '222 Pine Ave, Residential', '0123456795', 'BUYER', 89.75),
('Carol Brown', 'carol.brown@email.com', 'buyer123', '333 Cedar Blvd, City Heights', '0123456796', 'BUYER', 234.25),
('David Wilson', 'david.wilson@email.com', 'buyer123', '444 Maple Dr, Uptown', '0123456797', 'BUYER', 67.00),
('Emma Davis', 'emma.davis@email.com', 'buyer123', '555 Birch St, Midtown', '0123456798', 'BUYER', 0.0);