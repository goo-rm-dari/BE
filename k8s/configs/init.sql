CREATE SCHEMA IF NOT EXISTS `krampoline` DEFAULT CHARACTER SET utf8mb4;

GRANT ALL ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root' WITH GRANT OPTION;
GRANT ALL ON krampoline.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

USE `krampoline`;

CREATE TABLE plogging_record (
    plogging_record_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(255) NOT NULL,
    trash_count INT NOT NULL,
    total_calories DOUBLE NOT NULL,
    moving_time INT NOT NULL,
    moving_distance DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE moving_coordinate (
    moving_coordinate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plogging_record_id BIGINT,
    lat DOUBLE NOT NULL,
    lng DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_plogging_record
        FOREIGN KEY (plogging_record_id) 
        REFERENCES plogging_record(plogging_record_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE trash_coordinate (
    trash_coordinate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plogging_record_id BIGINT,
    lat DOUBLE NOT NULL,
    lng DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_plogging_record
        FOREIGN KEY (plogging_record_id) 
        REFERENCES plogging_record(plogging_record_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE member (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    platform VARCHAR(255),
    platform_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (email, platform)
);
