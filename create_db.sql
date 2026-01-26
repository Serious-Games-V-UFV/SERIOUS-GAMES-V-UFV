CREATE DATABASE serious_games;
USE  serious_games;

CREATE TABLE cuenta(
dni VARCHAR(9) NOT NULL PRIMARY KEY,
id INT UNSIGNED AUTO_INCREMENT UNIQUE KEY,
email VARCHAR(255) UNIQUE NOT NULL,
    CHECK (email LIKE '%_@_%.__%'),
nombre TEXT NOT NULL,
apellido1 TEXT NOT NULL,
apellido2 TEXT DEFAULT NULL,
peso DECIMAL(10,2) UNSIGNED NOT NULL,
altura SMALLINT UNSIGNED NOT NULL,
prefijo VARCHAR(6) NOT NULL,
telefono VARCHAR(9) NOT NULL,
direccion VARCHAR(255) DEFAULT NULL,
codigo_verif VARCHAR(100) DEFAULT NULL,
codigo_recup VARCHAR(100) DEFAULT NULL,
codigo_elim VARCHAR(100) DEFAULT NULL,
estado ENUM('activo', 'pendiente', 'bloqueado', 'eliminado') NOT NULL DEFAULT 'pendiente',
rol ENUM('usuario','finanzas','desarrollo','sistemas','marketing','imagen','comercio') NOT NULL DEFAULT 'usuario', 
last_login DATETIME DEFAULT NULL);


CREATE TABLE bolso(
id varchar(9) NOT NULL PRIMARY KEY,
color text NOT NULL,
f_compra DATE DEFAULT NULL,
cuenta varchar(9) NOT NULL,
FOREIGN KEY (cuenta) REFERENCES cuenta(dni)ON UPDATE CASCADE ON DELETE RESTRICT);

CREATE TABLE productos (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(150) NOT NULL,
slug VARCHAR(180) NOT NULL UNIQUE, -- URL dinamica
descripcion TEXT NOT NULL,
descripcion_corta VARCHAR(255) NULL,
precio DECIMAL(10,2) NOT NULL DEFAULT 0.00,
moneda CHAR(3) NOT NULL DEFAULT 'EUR',
es_gratis BOOLEAN NOT NULL DEFAULT FALSE,
estado ENUM('borrador', 'publicado', 'oculto', 'archivado') NOT NULL DEFAULT 'borrador',
stock INT UNSIGNED NULL, -- NULL = ilimitado (digital)
url_imagen VARCHAR(255) NULL,
categoria VARCHAR(100) NOT NULL,
subido_por INT UNSIGNED NOT NULL,
fecha_publicacion DATETIME NULL,
fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
fecha_eliminacion DATETIME NULL);

CREATE TABLE opciones_producto (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_producto INT UNSIGNED NOT NULL,
nombre_opcion VARCHAR(100) NOT NULL,
option_value VARCHAR(150) NOT NULL,
precio_opcion DECIMAL(10,2) NOT NULL DEFAULT 0.00,
stock INT UNSIGNED NULL, -- NULL = ilimitado
activo BOOLEAN NOT NULL DEFAULT TRUE,
fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (id_producto) REFERENCES productos(id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE pedidos (
id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_usuario INT UNSIGNED NOT NULL,
numero_pedido VARCHAR(30) NOT NULL UNIQUE,
status ENUM('pendiente','pagado','procesando','completado','cancelado','reembolsado') NOT NULL DEFAULT 'pendiente',
subtotal DECIMAL(10,2) NOT NULL DEFAULT 0.00,
impuestos DECIMAL(10,2) NOT NULL DEFAULT 0.00,
total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
moneda CHAR(3) NOT NULL DEFAULT 'EUR',

-- Datos de facturación/envío
billing_name VARCHAR(200) NULL,
billing_email VARCHAR(190) NULL,
billing_phone VARCHAR(25) NULL,
billing_address VARCHAR(300) NULL,
notas VARCHAR(500) NULL,

-- Pago
metodo_pago VARCHAR(30) NULL,         -- 'stripe','paypal','transfer', etc.
estado_pago ENUM('unpaid','authorized','paid','failed','refunded') NOT NULL DEFAULT 'unpaid',
referencia_pago VARCHAR(120) NULL,     -- id externo
fecha_pago DATETIME NULL,
fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
fecha_cancelacion DATETIME NULL,
FOREIGN KEY (id_usuario) REFERENCES cuenta(id) ON UPDATE CASCADE ON DELETE RESTRICT);


CREATE TABLE descuentos (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
codigo VARCHAR(50) NOT NULL UNIQUE,
nombre VARCHAR(120) NOT NULL,
descripcion VARCHAR(255) NULL,
tipo ENUM('percent','fixed') NOT NULL, -- porcentaje o fijo
valor DECIMAL(10,2) NOT NULL,
cantidad_minima_para_descuento DECIMAL(10,2) NULL,        -- mínimo para aplicar
topo_descuento DECIMAL(10,2) NULL,    -- tope si es %
fecha_comienzo DATETIME NULL,
fecha_expiracion DATETIME NULL,
activo BOOLEAN NOT NULL DEFAULT TRUE,
limite_uso INT UNSIGNED NULL,             -- total usos (NULL = ilimitado)
limite_uso_por_persona INT UNSIGNED NULL,    -- por usuario (NULL = ilimitado)
fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);

CREATE TABLE detalles_pedido (
id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_pedido BIGINT UNSIGNED NOT NULL,
id_producto INT UNSIGNED NOT NULL,
nombre_producto VARCHAR(150) NOT NULL,
version_pedido VARCHAR(50) NULL,
precio_unitario DECIMAL(10,2) NOT NULL,   -- precio base del producto
precio_opcion DECIMAL(10,2) NOT NULL DEFAULT 0.00,
descuento VARCHAR(50),
cantidad INT UNSIGNED NOT NULL DEFAULT 1,
precio_total DECIMAL(10,2) NOT NULL,
fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (id_pedido) REFERENCES pedidos(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (id_producto) REFERENCES productos(id) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (descuento) REFERENCES descuentos(codigo) ON UPDATE CASCADE ON DELETE RESTRICT);

CREATE TABLE categorias (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_padre INT UNSIGNED NULL,          -- para subcategorías
nombre VARCHAR(120) NOT NULL,
slug VARCHAR(150) NOT NULL UNIQUE,
descripcion VARCHAR(255) NULL,
activo BOOLEAN NOT NULL DEFAULT TRUE,
sort_order INT UNSIGNED NOT NULL DEFAULT 0,
fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (id_padre) REFERENCES categorias(id) ON UPDATE CASCADE ON DELETE SET NULL);

CREATE TABLE producto_categoria (
id_producto INT UNSIGNED NOT NULL,
id_categoria INT UNSIGNED NOT NULL,
PRIMARY KEY (id_producto, id_categoria),
FOREIGN KEY (id_producto) REFERENCES productos(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (id_categoria) REFERENCES categorias(id) ON UPDATE CASCADE ON DELETE CASCADE);


CREATE TABLE botella(
id varchar(9) NOT NULL PRIMARY KEY,
tamanio DECIMAL(10,2) CHECK (tamanio >=0.5 AND tamanio <= 1.5) NOT NULL ,
bolso varchar(9) NOT NULL,
FOREIGN KEY (bolso) REFERENCES bolso(id)ON UPDATE CASCADE ON DELETE RESTRICT);

CREATE TABLE sensores(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
tipo text NOT NULL,
estado boolean NOT NULL,
intervalo INT UNSIGNED NOT NULL);

CREATE TABLE bolso_sensores(
bolso varchar(9) NOT NULL,
sensor int AUTO_INCREMENT NOT NULL,
FOREIGN KEY (bolso) REFERENCES bolso(id) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (sensor) REFERENCES sensores(id) ON UPDATE CASCADE ON DELETE RESTRICT,
PRIMARY KEY (bolso, sensor));

CREATE TABLE medidas(
id varchar(255) NOT NULL PRIMARY KEY,
valor DECIMAL(10,2) NOT NULL);

CREATE TABLE sensores_medidas(
medida varchar(255) NOT NULL,
sensor int NOT NULL,
FOREIGN KEY (sensor) REFERENCES sensores(id) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (medida) REFERENCES medidas(id) ON UPDATE CASCADE ON DELETE RESTRICT,
PRIMARY KEY (sensor, medida));
