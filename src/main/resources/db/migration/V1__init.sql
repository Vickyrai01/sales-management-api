CREATE TABLE IF NOT EXISTS rol (
   `id` BIGINT NOT NULL AUTO_INCREMENT,
   `rol_name` ENUM('ADMIN', 'SELLER', 'STOCK_MANAGER') NULL DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `account_no_expired` BIT(1) NULL DEFAULT 1,
    `account_no_locked` BIT(1) NULL DEFAULT 1,
    `credentials_no_expired` BIT(1) NULL DEFAULT 1,
    `is_enabled` BIT(1) NULL DEFAULT 1,
    `password` VARCHAR(255) NULL DEFAULT NULL,
    `username` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_users_username (username));

CREATE TABLE IF NOT EXISTS user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES `user`(id),
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES rol(id));

CREATE TABLE IF NOT EXISTS `branch` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `direction` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `telephone` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `price` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_product_category_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_product_category_category FOREIGN KEY (category_id) REFERENCES category(id));

CREATE TABLE IF NOT EXISTS branch_stock (
    id BIGINT NOT NULL AUTO_INCREMENT,
    quantity INT NOT NULL,
    branch_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_branch_stock_branch_product (branch_id, product_id),
    CONSTRAINT fk_branch_stock_branch FOREIGN KEY (branch_id) REFERENCES branch(id),
    CONSTRAINT fk_branch_stock_product FOREIGN KEY (product_id) REFERENCES product(id));

CREATE TABLE IF NOT EXISTS sale (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATE NOT NULL,
    state ENUM('CANCELLED', 'CONFIRMED', 'CREATED', 'PAID') NOT NULL,
    total DOUBLE NULL DEFAULT NULL,
    branch_id BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_sale_branch FOREIGN KEY (branch_id) REFERENCES branch(id));

CREATE TABLE IF NOT EXISTS sale_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    price DOUBLE NULL DEFAULT NULL,
    quantity INT NULL DEFAULT NULL,
    total DOUBLE NULL DEFAULT NULL,
    product_id BIGINT NULL DEFAULT NULL,
    sale_id BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_sale_item_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_sale_item_sale FOREIGN KEY (sale_id) REFERENCES sale(id));