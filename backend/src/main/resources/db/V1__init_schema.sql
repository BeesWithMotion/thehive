CREATE TABLE boards (
    board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_abbreviation VARCHAR(255) NOT NULL UNIQUE,
    board_name VARCHAR(255) NOT NULL UNIQUE,
    board_description VARCHAR(255) NOT NULL
);

CREATE TABLE threads (
    thread_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_abbreviation VARCHAR(255) NOT NULL,
    thread_title VARCHAR(255) NOT NULL,
    thread_author VARCHAR(255) NOT NULL,
    thread_description TEXT NOT NULL,
    thread_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_threads_board
                     FOREIGN KEY (board_abbreviation)
                     REFERENCES boards(board_abbreviation)
                     ON DELETE CASCADE
);

CREATE TABLE posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    thread_id BIGINT NOT NULL,
    post_content TEXT NOT NULL,
    post_author VARCHAR(255) NOT NULL,
    post_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_posts_thread
                     FOREIGN KEY (thread_id)
                     REFERENCES threads(thread_id)
                   ON DELETE CASCADE
);

CREATE TABLE images (
    image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    original_file_name VARCHAR(255) NOT NULL,
    stored_file_name VARCHAR(255) NOT NULL UNIQUE,
    content_type VARCHAR(100) NOT NULL,
    file_size BIGINT NOT NULL,
    upload_date_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_images_post
                    FOREIGN KEY (post_id)
                    REFERENCES posts(post_id)
                    ON DELETE CASCADE
);