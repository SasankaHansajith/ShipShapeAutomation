CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    contact_info VARCHAR(255)
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    order_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE suppliers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    contact_info VARCHAR(255)
);

CREATE TABLE supplier_parts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT,
    part_name VARCHAR(100),
    stock INT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE inventory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    part_name VARCHAR(100),
    stock INT
);

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    role VARCHAR(50),
    schedule VARCHAR(255)
);

CREATE TABLE jobs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    description TEXT,
    scheduled_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE job_assignments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT,
    employee_id INT,
    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE sales_reports (
    id INT PRIMARY KEY AUTO_INCREMENT,
    report_date DATE,
    total_sales DECIMAL(10, 2)
);
