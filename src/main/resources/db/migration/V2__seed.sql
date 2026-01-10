INSERT INTO rol (rol_name) VALUES  ('ADMIN');
INSERT INTO rol (rol_name) VALUES  ('SELLER');
INSERT INTO rol (rol_name) VALUES  ('STOCK_MANAGER');

INSERT INTO user ( account_no_expired, account_no_locked, credentials_no_expired, is_enabled, password, username)
    VALUES ( 1, 1, 1, 1, '$2a$10$lO8lxIM.QhmnZdqNTiHJHuakGrKMGdJMGd1ViXFxUCczu5581o2m2', 'admin');

INSERT INTO user ( account_no_expired, account_no_locked, credentials_no_expired, is_enabled, password, username)
    VALUES ( 1, 1, 1, 1, '$2a$10$lO8lxIM.QhmnZdqNTiHJHuakGrKMGdJMGd1ViXFxUCczu5581o2m2', 'seller');

INSERT INTO user ( account_no_expired, account_no_locked, credentials_no_expired, is_enabled, password, username)
    VALUES ( 1, 1, 1, 1, '$2a$10$lO8lxIM.QhmnZdqNTiHJHuakGrKMGdJMGd1ViXFxUCczu5581o2m2', 'stock_manager');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 3);

INSERT INTO branch (name, direction, telephone) VALUES
    ('Centro', 'Av. Principal 123', '+54 11 4000-0001'),
    ('Norte', 'Calle 9 #456', '+54 11 4000-0002'),
    ('Sur', 'Ruta 2 Km 35', '+54 11 4000-0003'),
    ('Oeste', 'Boulevard 77 890', '+54 11 4000-0004'),
    ('Este', 'Diagonal 1 234', '+54 11 4000-0005'),
    ('Barrio Jardin', 'Jardin 321', '+54 11 4000-0006'),
    ('Terminal', 'Estación Central 10', '+54 11 4000-0007'),
    ('Aeropuerto', 'Acceso Norte 1000', '+54 11 4000-0008');

INSERT INTO category (name) VALUES
    ('Bebidas'),
    ('Lácteos'),
    ('Panadería'),
    ('Carnes'),
    ('Frutas y Verduras'),
    ('Limpieza'),
    ('Higiene Personal'),
    ('Congelados'),
    ('Snacks'),
    ('Almacén'),
    ('Mascotas'),
    ('Electro');

INSERT INTO product (name, description, price) VALUES
    ('Agua mineral 1.5L', 'Agua sin gas 1.5 litros', 1200.00),
    ('Agua con gas 1.5L', 'Agua con gas 1.5 litros', 1300.00),
    ('Gaseosa cola 2.25L', 'Bebida gaseosa sabor cola', 2900.00),
    ('Jugo naranja 1L', 'Jugo listo para beber', 2100.00),
    ('Leche entera 1L', 'Leche entera larga vida', 1700.00),
    ('Leche descremada 1L', 'Leche descremada larga vida', 1700.00),
    ('Yogur vainilla 190g', 'Yogur sabor vainilla', 900.00),
    ('Manteca 200g', 'Manteca tradicional', 1600.00),
    ('Pan lactal', 'Pan lactal blanco', 2400.00),
    ('Pan integral', 'Pan lactal integral', 2700.00),
    ('Medialunas x6', 'Medialunas de manteca', 2200.00),
    ('Carne picada 1kg', 'Carne vacuna picada', 6200.00),
    ('Pechuga de pollo 1kg', 'Pechuga fresca', 7200.00),
    ('Milanesas de pollo 1kg', 'Milanesas rebozadas', 8500.00),
    ('Manzana roja 1kg', 'Manzana roja', 2800.00),
    ('Banana 1kg', 'Banana ecuatoriana', 2600.00),
    ('Tomate 1kg', 'Tomate perita', 3000.00),
    ('Lechuga', 'Lechuga criolla', 1600.00),
    ('Lavandina 1L', 'Lavandina concentrada', 1500.00),
    ('Detergente 750ml', 'Detergente limón', 1900.00),
    ('Jabón en polvo 3kg', 'Jabón en polvo para ropa', 9200.00),
    ('Shampoo 400ml', 'Shampoo uso diario', 3800.00),
    ('Acondicionador 400ml', 'Acondicionador nutritivo', 4200.00),
    ('Papel higiénico x4', 'Doble hoja', 2600.00),
    ('Toallitas húmedas', 'Pack 80 unidades', 2400.00),
    ('Helado 1L', 'Helado crema americana', 5900.00),
    ('Papas congeladas 1kg', 'Papas prefritas', 4800.00),
    ('Hamburguesas x4', 'Medallones congelados', 5200.00),
    ('Papas fritas 150g', 'Snack papas clásicas', 1800.00),
    ('Maní 100g', 'Maní tostado', 1200.00),
    ('Galletitas chocolate', 'Galletitas rellenas', 2100.00),
    ('Arroz 1kg', 'Arroz largo fino', 2200.00),
    ('Fideos 500g', 'Fideos spaghetti', 1600.00),
    ('Aceite 1.5L', 'Aceite de girasol', 4800.00),
    ('Atún en lata', 'Atún al natural', 2400.00),
    ('Alimento perro 3kg', 'Balanceado premium', 12000.00),
    ('Alimento gato 3kg', 'Balanceado premium', 11500.00),
    ('Arena sanitaria', 'Arena para gatos', 7800.00),
    ('Tostadora', 'Tostadora 2 panes', 26000.00),
    ('Pava eléctrica', 'Pava eléctrica 1.7L', 31000.00);


INSERT INTO product_category (product_id, category_id) VALUES
    (1, 1), (2, 1), (3, 1), (4, 1);

INSERT INTO product_category (product_id, category_id) VALUES
    (5, 2), (6, 2), (7, 2), (8, 2);

INSERT INTO product_category (product_id, category_id) VALUES
    (9, 3), (10, 3), (11, 3);
INSERT INTO product_category (product_id, category_id) VALUES
    (12, 4), (13, 4), (14, 4);

INSERT INTO product_category (product_id, category_id) VALUES
    (15, 5), (16, 5), (17, 5), (18, 5);

INSERT INTO product_category (product_id, category_id) VALUES
    (19, 6), (20, 6), (21, 6);

INSERT INTO product_category (product_id, category_id) VALUES
    (22, 7), (23, 7), (24, 7), (25, 7);

INSERT INTO product_category (product_id, category_id) VALUES
    (26, 8), (27, 8), (28, 8);

INSERT INTO product_category (product_id, category_id) VALUES
    (29, 9), (30, 9), (31, 9);

INSERT INTO product_category (product_id, category_id) VALUES
    (32, 10), (33, 10), (34, 10), (35, 10);

INSERT INTO product_category (product_id, category_id) VALUES
    (36, 11), (37, 11), (38, 11);

INSERT INTO product_category (product_id, category_id) VALUES
    (39, 12), (40, 12);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (120, 1, 1),(90, 1, 2),(80, 1, 3),(70, 1, 4),(60, 1, 5),
    (50, 1, 6),(40, 1, 7),(35, 1, 8),(55, 1, 9),(45, 1, 10),
    (65, 1, 11),(30, 1, 12),(25, 1, 13),(20, 1, 14),(90, 1, 15),
    (85, 1, 16),(75, 1, 17),(60, 1, 18),(70, 1, 19),(80, 1, 20);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (100, 2, 6),(95, 2, 7),(60, 2, 8),(80, 2, 9),(75, 2, 10),
    (55, 2, 11),(40, 2, 12),(35, 2, 13),(30, 2, 14),(50, 2, 15),
    (65, 2, 16),(70, 2, 17),(45, 2, 18),(85, 2, 19),(90, 2, 20),
    (60, 2, 21),(50, 2, 22),(45, 2, 23),(70, 2, 24),(80, 2, 25);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (60, 3, 11),(55, 3, 12),(50, 3, 13),(45, 3, 14),(80, 3, 15),
    (75, 3, 16),(65, 3, 17),(60, 3, 18),(70, 3, 19),(90, 3, 20),
    (55, 3, 21),(45, 3, 22),(40, 3, 23),(50, 3, 24),(60, 3, 25),
    (35, 3, 26),(30, 3, 27),(25, 3, 28),(95, 3, 29),(85, 3, 30);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (70, 4, 16),(65, 4, 17),(60, 4, 18),(75, 4, 19),(80, 4, 20),
    (55, 4, 21),(50, 4, 22),(45, 4, 23),(40, 4, 24),(60, 4, 25),
    (50, 4, 26),(45, 4, 27),(35, 4, 28),(90, 4, 29),(85, 4, 30),
    (60, 4, 31),(55, 4, 32),(50, 4, 33),(45, 4, 34),(40, 4, 35);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (70, 5, 21),(65, 5, 22),(60, 5, 23),(55, 5, 24),(80, 5, 25),
    (50, 5, 26),(45, 5, 27),(40, 5, 28),(90, 5, 29),(85, 5, 30),
    (60, 5, 31),(55, 5, 32),(50, 5, 33),(45, 5, 34),(40, 5, 35),
    (35, 5, 36),(30, 5, 37),(25, 5, 38),(10, 5, 39),(10, 5, 40);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (110, 6, 1),(90, 6, 3),(70, 6, 5),(60, 6, 7),(50, 6, 9),
    (40, 6, 11),(30, 6, 13),(20, 6, 15),(80, 6, 17),(60, 6, 19),
    (55, 6, 21),(45, 6, 23),(35, 6, 25),(25, 6, 27),(90, 6, 29),
    (70, 6, 31),(60, 6, 33),(50, 6, 35),(20, 6, 37),(8, 6, 40);


INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (100, 7, 2),(80, 7, 4),(70, 7, 6),(60, 7, 8),(50, 7, 10),
    (40, 7, 12),(35, 7, 14),(25, 7, 16),(85, 7, 18),(75, 7, 20),
    (55, 7, 22),(45, 7, 24),(35, 7, 26),(30, 7, 28),(95, 7, 30),
    (70, 7, 32),(60, 7, 34),(25, 7, 36),(20, 7, 38),(10, 7, 39);

INSERT INTO branch_stock (quantity, branch_id, product_id) VALUES
    (200, 8, 1),(200, 8, 3),(180, 8, 5),(160, 8, 9),(150, 8, 15),
    (140, 8, 16),(130, 8, 19),(120, 8, 20),(110, 8, 24),(100, 8, 29),
    (90, 8, 31),(80, 8, 32),(70, 8, 33),(60, 8, 34),(50, 8, 35),
    (45, 8, 26),(40, 8, 27),(35, 8, 28),(20, 8, 39),(20, 8, 40);

INSERT INTO sale (date, state, total, branch_id) VALUES
     ('2026-01-02', 'CREATED',   0, 1),
     ('2026-01-03', 'PAID',      0, 1),
     ('2026-01-03', 'CONFIRMED', 0, 2),
     ('2026-01-04', 'CANCELLED', 0, 2),
     ('2026-01-05', 'PAID',      0, 3),
     ('2026-01-05', 'CREATED',   0, 3),
     ('2026-01-06', 'CONFIRMED', 0, 4),
     ('2026-01-06', 'PAID',      0, 4),
     ('2026-01-07', 'PAID',      0, 5),
     ('2026-01-07', 'CREATED',   0, 5),
     ('2026-01-08', 'CONFIRMED', 0, 6),
     ('2026-01-08', 'PAID',      0, 6),
     ('2026-01-09', 'PAID',      0, 7),
     ('2026-01-09', 'CANCELLED', 0, 7),
     ('2026-01-10', 'PAID',      0, 8),
     ('2026-01-10', 'CONFIRMED', 0, 8);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (1200.00, 2, 2400.00, 1, 1),
    (1700.00, 1, 1700.00, 5, 1),
    (2400.00, 1, 2400.00, 9, 1);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (2900.00, 1, 2900.00, 3, 2),
    (900.00,  3, 2700.00, 7, 2),
    (1800.00, 2, 3600.00, 29, 2);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (6200.00, 1, 6200.00, 12, 3),
    (3000.00, 2, 6000.00, 17, 3);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (1500.00, 2, 3000.00, 19, 4),
    (1900.00, 1, 1900.00, 20, 4);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (7200.00, 1, 7200.00, 13, 5),
    (5200.00, 1, 5200.00, 28, 5),
    (2200.00, 2, 4400.00, 32, 5);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (1600.00, 3, 4800.00, 33, 6),
    (4800.00, 1, 4800.00, 34, 6),
    (2400.00, 1, 2400.00, 35, 6);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (2600.00, 2, 5200.00, 24, 7),
    (2400.00, 1, 2400.00, 35, 7),
    (2100.00, 1, 2100.00, 31, 7);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (5900.00, 1, 5900.00, 26, 8),
    (4800.00, 2, 9600.00, 27, 8);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (12000.00, 1, 12000.00, 36, 9),
    (7800.00,  1, 7800.00, 38, 9);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (2800.00, 2, 5600.00, 15, 10),
    (2600.00, 2, 5200.00, 16, 10),
    (1600.00, 1, 1600.00, 18, 10);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (1200.00, 5, 6000.00, 1, 11),
    (2100.00, 1, 2100.00, 4, 11);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (26000.00, 1, 26000.00, 39, 12),
    (31000.00, 1, 31000.00, 40, 12);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (2400.00, 2, 4800.00, 35, 13),
    (2200.00, 2, 4400.00, 32, 13),
    (1800.00, 1, 1800.00, 29, 13);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (8500.00, 1, 8500.00, 14, 14);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (1300.00, 2, 2600.00, 2, 15),
    (1700.00, 2, 3400.00, 6, 15),
    (2400.00, 1, 2400.00, 35, 15);

INSERT INTO sale_item (price, quantity, total, product_id, sale_id) VALUES
    (1900.00, 2, 3800.00, 20, 16),
    (3800.00, 1, 3800.00, 22, 16),
    (2400.00, 1, 2400.00, 35, 16);

-- Update totals (sum of sale_item.total)
UPDATE sale s
SET s.total = (
    SELECT COALESCE(SUM(si.total), 0)
    FROM sale_item si
    WHERE si.sale_id = s.id
);
