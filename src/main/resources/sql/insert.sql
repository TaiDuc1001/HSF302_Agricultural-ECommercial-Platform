INSERT INTO categories (id, name, is_active)
VALUES (1, 'Organic Produce', TRUE);


INSERT INTO users (id, name, email, password, role, address, phone, is_active)
VALUES
    (1, 'manager 1', 'm@m', '123', 'MANAGER', '123 Farm St', '555-0101', TRUE),
    (2, 'buyer 1', 'b@b', '123', 'BUYER', '456 Market Rd', '555-0102', TRUE),
    (3, 'seller 1', 's1@s1', '123', 'SELLER', '789 Farm Ave', '555-0103', TRUE),
    (4, 'seller 2', 's2@s2', '123', 'SELLER', '012 Orchard Ln', '555-0104', TRUE);


INSERT INTO produces (id, name, user_id, description, price, quantity, img_url, category_id, is_active, preorder_startdate, preorder_enddate)
VALUES
    (1, 'Organic Corn', 3, 'Fresh organic corn', 5.00, 100, 'corn.jpg', 1, TRUE, '2025-07-01 00:00:00', '2025-07-31 23:59:59'), 
    (2, 'Organic Pumpkins', 3, 'Sweet organic pumpkins', 10.00, 50, 'pumpkin.jpg', 1, TRUE, NULL, NULL), 
    (3, 'Organic Tomatoes', 4, 'Juicy organic tomatoes', 3.00, 200, 'tomato.jpg', 1, TRUE, NULL, NULL); 


INSERT INTO discount_codes (code, type, value, max_uses, used_count, valid_from, valid_until, produce_id, is_active)
VALUES
    ('PORN30', 'PERCENTAGE', 30.00, 100, 1, '2025-07-01 00:00:00', '2025-07-31 23:59:59', 1, TRUE);


INSERT INTO orders (id, user_id, order_date, discount_code, total_amount, discount_amount, final_amount, is_preorder, payment_date, payment_method, status, is_active)
VALUES
    (1, 2, '2025-07-07 20:12:00', 'PORN30', 10.00, 3.00, 7.00, TRUE, '2025-07-07 20:15:00', 'E_WALLET', 'PAID', TRUE), 
    (2, 2, '2025-07-07 20:20:00', NULL, 16.00, 0.00, 16.00, FALSE, '2025-07-07 20:22:00', 'CASH', 'PAID', TRUE); 


INSERT INTO order_details (id, order_id, produce_id, quantity, unit_price)
VALUES
    (1, 1, 1, 2, 5.00), 
    (2, 2, 2, 1, 10.00), 
    (3, 2, 3, 2, 3.00); 


INSERT INTO user_items (id, user_id, produce_id, quantity, is_active)
VALUES
    (1, 2, 1, 1, TRUE); 


INSERT INTO reviews (id, order_id, user_id, produce_id, rating, comment, is_active)
VALUES
    (1, 1, 2, 1, 4.5, 'Delicious organic corn, worth the wait!', TRUE);