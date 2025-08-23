-- Inserción de datos en la tabla Permission
INSERT INTO permission (permission_id, name, description)
VALUES (1, 'READ', 'Permission to read data'),
       (2, 'WRITE', 'Permission to write data');

-- Inserción de datos en la tabla Role
INSERT INTO role_table (role_id, name, description)
VALUES (1, 'ROLE_ADMIN', 'Administrator role'),
       (2, 'ROLE_USER', 'Regular user role');

-- Inserción de datos en la tabla permit_listing
INSERT INTO permit_listing (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);

-- Inserción de datos en la tabla User
INSERT INTO user_table(id, name, email, password, phone, date, role_id)
VALUES (1, 'Alice', 'alice@example.com', '$2y$10$QEHdXo95mFYJW68j9PMsie4hxiaWbu7LYMhRH.t5kKTS6g1V26MEe', '123456789', '2024-01-01', 1),
       (2, 'Bob', 'bob@example.com', '$2y$10$QEHdXo95mFYJW68j9PMsie4hxiaWbu7LYMhRH.t5kKTS6g1V26MEe', '987654321', '2024-01-02', 2);


-- Inserción de datos en la tabla Favorite
INSERT INTO favorite (favorite_id, user_id)
VALUES (1, 1),
       (2, 2);


-- Inserción de datos en la tabla ShoppingCart
INSERT INTO shopping_cart (shopping_cart_id, user_id)
VALUES (1, 1),
       (2, 2);


-- Inserción de datos en la tabla History
INSERT INTO history (history_id, user_id)
VALUES (1, 1),
       (2, 2);

UPDATE user_table
SET history_id = 1,
    favorite_id = 1,
    shopping_cart_id = 1
WHERE id = 1;

UPDATE user_table
SET history_id = 2,
    favorite_id = 2,
    shopping_cart_id = 2
WHERE id = 2;



INSERT INTO category (category_id, name, description)
VALUES (1, 'Electrónica', 'Dispositivos electrónicos y gadgets'),
       (2, 'Hogar', 'Artículos para el hogar y cocina'),
       (3, 'Ropa', 'Prendas de vestir para todas las edades'),
       (4, 'Libros', 'Libros de ficción, no ficción y educativos'),
       (5, 'Juguetes', 'Juguetes para niños y niñas de todas las edades');

INSERT INTO product (product_id, name, description, price, date, location, status)
VALUES (1, 'Smartphone Galaxy A54', 'Smartphone con pantalla AMOLED, cámara triple y batería de larga duración.', 359.99, CURRENT_DATE, 'Ciudad de México', 'Disponible'),
       (2, 'Licuadora Oster', 'Licuadora de vidrio resistente con múltiples velocidades.', 79.99, CURRENT_DATE, 'Guadalajara', 'Disponible'),
       (3, 'Camisa Casual Hombre', 'Camisa de algodón 100%, ideal para uso diario.', 24.50, CURRENT_DATE, 'Monterrey', 'Disponible'),
       (4, 'Cien Años de Soledad', 'Novela icónica de Gabriel García Márquez.', 12.90, CURRENT_DATE, 'Bogotá', 'Disponible'),
       (5, 'LEGO Star Wars', 'Set de construcción LEGO con temática de Star Wars.', 89.00, CURRENT_DATE, 'Lima', 'Disponible');

INSERT INTO product_category (category_id, product_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (1, 4),
       (5, 5);

INSERT INTO image (image_id, product_id, image)
VALUES (1, 1, 'decode(01020304, hex)'),
       (2, 2, 'decode(0A0B0C0D, hex)'),
       (3, 3, 'decode(1A1B1C1D, hex)'),
       (4, 4, 'decode(2A2B2C2D, hex)'),
       (5, 5, 'decode(3A3B3C3D, hex)'),
       (6, 1, 'decode(01020314, hex)');

INSERT INTO orders (order_id, order_number, history_id, order_status, date)
VALUES (1, 1, 1, 'PENDING', CURRENT_DATE),
       (2, 1, 2, 'PENDING', CURRENT_DATE),
       (3, 2, 1, 'PENDING', CURRENT_DATE),
       (4, 2, 2, 'PENDING', CURRENT_DATE),
       (5, 3, 1, 'PENDING', CURRENT_DATE);

INSERT INTO transaction (transaction_id, user_id, status, price, payment_method, amount, product_id, order_id)
VALUES (1, 2, 'Completado', 359.99, 'Tarjeta de crédito', 1, 1, 1),
       (2, 1, 'Completado', 79.99, 'PayPal', 1, 2, 2),
       (3, 2, 'Completado', 24.50, 'Transferencia', 2, 3, 3),
       (4, 1, 'Completado', 12.90, 'Tarjeta de débito', 1, 4, 4),
       (5, 2, 'Completado', 89.00, 'Efectivo', 1, 5, 5);


INSERT INTO review (review_id, transaction_id, points, description, date)
VALUES (1, 1, 4.5, 'Excelente calidad, llegó a tiempo.', CURRENT_DATE),
       (2, 2, 4.0, 'Buen producto, pero el empaque estaba dañado.', CURRENT_DATE),
       (3, 3, 5.0, 'Muy buena relación calidad-precio.', CURRENT_DATE),
       (4, 4, 3.5, 'El libro llegó con las esquinas dobladas.', CURRENT_DATE),
       (5, 5, 4.8, '¡A mi hijo le encantó el set de LEGO!', CURRENT_DATE);

INSERT INTO cart_listing (cart_listing_id, shopping_cart_id, product_id, cuantity)
VALUES (1, 1, 1, 2),
       (2, 1, 2, 1),
       (3, 2, 3, 5);

INSERT INTO inventory (inventory_id, user_id, product_id, stock)
VALUES (1, 2, 1, 10),
       (2, 1, 2, 15),
       (3, 2, 3, 8),
       (4, 1, 4, 22),
       (5, 2, 5, 11);

INSERT INTO favorite_listings (favorite_id, product_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);

INSERT INTO notification (notifications_id, user_id, date, description)
VALUES (1, 1, '2025-05-01', 'Tu publicación ha sido aprobada.'),
       (2, 1, '2025-05-05', 'Has recibido una nueva oferta.'),
       (3, 1, '2025-05-10', 'Tu artículo fue marcado como vendido.');

INSERT INTO room (room_id, user_id, peer_user_id)
VALUES (1, 1, 2);


INSERT INTO message (message_id, room_id, message, date)
VALUES (1, 1, 'Hola, ¿sigue disponible el producto?', '2025-05-01'),
       (2, 1, 'Sí, aún está disponible.', '2025-05-02');

SELECT setval('permission_seq', (SELECT MAX(permission_id) FROM permission));
SELECT setval('role_table_seq', (SELECT MAX(role_id) FROM role_table));
SELECT setval('user_table_seq', (SELECT MAX(id) FROM user_table));
SELECT setval('category_seq', (SELECT MAX(category_id) FROM category));
SELECT setval('favorite_seq', (SELECT MAX(favorite_id) FROM favorite));
SELECT setval('history_seq', (SELECT MAX(history_id) FROM history));
SELECT setval('image_seq', (SELECT MAX(image_id) FROM image));
SELECT setval('message_seq', (SELECT MAX(message_id) FROM message));
SELECT setval('notification_seq', (SELECT MAX(notifications_id) FROM notification));
SELECT setval('orders_seq', (SELECT MAX(order_id) FROM orders));
SELECT setval('product_seq', (SELECT MAX(product_id) FROM product));
SELECT setval('review_seq', (SELECT MAX(review_id) FROM review));
SELECT setval('room_seq', (SELECT MAX(room_id) FROM room));
SELECT setval('shopping_cart_seq', (SELECT MAX(shopping_cart_id) FROM shopping_cart));
SELECT setval('transaction_seq', (SELECT MAX(transaction_id) FROM transaction));
SELECT setval('cart_listing_seq', (SELECT MAX(cart_listing_id) FROM cart_listing));
SELECT setval('inventory_seq', (SELECT MAX(inventory_id) FROM inventory));
