INSERT INTO categories (name, is_active)
VALUES ('Organic Produce', 1);


INSERT INTO users (name, email, password, role, address, phone, is_active)
VALUES
    ('manager 1', 'm@m', '123', 'MANAGER', '123 Farm St', '555-0101', 1),
    ('buyer 1', 'b@b', '123', 'BUYER', '456 Market Rd', '555-0102', 1),
    ('seller 1', 's1@s1', '123', 'SELLER', '789 Farm Ave', '555-0103', 1),
    ('seller 2', 's2@s2', '123', 'SELLER', '012 Orchard Ln', '555-0104', 1);


INSERT INTO produces (name, user_id, description, price, quantity, img_url, category_id, is_active, preorder_startdate, preorder_enddate)
VALUES
    ('Organic Corn', 3, 'Fresh organic corn', 5.00, 100, 'corn.jpg', 1, 1, '2025-07-01 00:00:00', '2025-07-31 23:59:59'), 
    ('Organic Pumpkins', 3, 'Sweet organic pumpkins', 10.00, 50, 'pumpkin.jpg', 1, 1, NULL, NULL), 
    ('Organic Tomatoes', 4, 'Juicy organic tomatoes', 3.00, 200, 'tomato.jpg', 1, 1, NULL, NULL); 


INSERT INTO discount_codes (code, type, value, max_uses, used_count, valid_from, valid_until, produce_id, is_active)
VALUES
    ('CORN30', 'PERCENTAGE', 30.00, 100, 1, '2025-07-01 00:00:00', '2025-07-31 23:59:59', 1, 1);


INSERT INTO orders (user_id, order_date, discount_code, total_amount, discount_amount, final_amount, is_preorder, payment_date, payment_method, status, is_active)
VALUES
    (2, '2025-07-07 20:12:00', 'CORN30', 10.00, 3.00, 7.00, 1, '2025-07-07 20:15:00', 'E_WALLET', 'PAID', 1), 
    (2, '2025-07-07 20:20:00', NULL, 16.00, 0.00, 16.00, 0, '2025-07-07 20:22:00', 'CASH', 'PAID', 1); 


INSERT INTO order_details (order_id, produce_id, quantity, unit_price)
VALUES
    (1, 1, 2, 5.00), 
    (2, 2, 1, 10.00), 
    (2, 3, 2, 3.00); 


INSERT INTO user_items (user_id, produce_id, quantity, is_active)
VALUES
    (2, 1, 1, 1); 


INSERT INTO reviews (order_id, user_id, produce_id, rating, comment, is_active)
VALUES
    (1, 2, 1, 4, 'Delicious organic corn, worth the wait!', 1);