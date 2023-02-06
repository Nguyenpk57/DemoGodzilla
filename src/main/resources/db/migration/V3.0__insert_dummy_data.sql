
INSERT INTO godzilladbdemo.product ( product_code,product_name) VALUES ('PC0001', 'case');
INSERT INTO godzilladbdemo.product ( product_code,product_name) VALUES ('PC0002', 'computer');
INSERT INTO godzilladbdemo.product ( product_code,product_name) VALUES ('PC0003', 'mouse');
INSERT INTO godzilladbdemo.product ( product_code,product_name) VALUES ('PC0004', 'computer 2');

INSERT INTO godzilladbdemo.category ( category_name, slug) VALUES ('category 1', 'category_1');
INSERT INTO godzilladbdemo.category ( category_name, slug) VALUES ('category 2', 'category_2');

INSERT INTO godzilladbdemo.product_category ( product_id, category_id) VALUES (1, 1);
INSERT INTO godzilladbdemo.product_category ( product_id, category_id) VALUES (2, 1);
INSERT INTO godzilladbdemo.product_category ( product_id, category_id) VALUES (2, 2);