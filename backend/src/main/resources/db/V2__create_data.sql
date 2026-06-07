INSERT INTO boards (board_name, board_abbreviation, board_description)
VALUES
    ('The Buzz', 'tb','What\'s currently buzzing'),
    ('Pollen Count', 'pc', 'Pollen count for the day'),
    ('Honey Advice', 'ha', 'Help honey grow'),
    ('Enemies', 'e', 'Plotting against rival hives');

# The autocomplete went crazy here, I just let it add a bunch of stuff
INSERT INTO threads (board_id, thread_title, thread_description, thread_author)
VALUES
    (1, 'What\'s buzzing?', 'What\'s currently buzzing', 'admin'),
    (1, 'There\'s a new hive!', 'There\'s a new hive in the area', 'admin'),
    (2, 'Pollen count for the day', 'Pollen count for the day', 'admin'),
    (3, 'Honey advice', 'Help honey grow', 'admin'),
    (4, 'Enemies', 'Plotting against rival hives', 'admin'),
    (1, 'What\'s buzzing?', 'What\'s currently buzzing', 'user1'),
    (1, 'There\'s a new hive!', 'There\'s a new hive in the area', 'user2'),
    (2, 'Pollen count for the day', 'Pollen count for the day', 'user3'),
    (3, 'Honey advice', 'Help honey grow', 'user4'),
    (4, 'Enemies', 'Plotting against rival hives', 'user5');
