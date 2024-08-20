# Spring book

```bash
docker run --name mysql-contacts -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345678 -d mysql
```

```sql
create schema contacts_db;
use contacts_db;

DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SELECT * FROM contacts;
```
