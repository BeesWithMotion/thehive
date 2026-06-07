INSERT INTO boards (board_name, board_abbreviation, board_description)
VALUES
    ('The Buzz', 'tb','What\'s currently buzzing'),
    ('Pollen Count', 'pc', 'Pollen count for the day'),
    ('Honey Advice', 'ha', 'Help honey grow'),
    ('Enemies', 'e', 'Plotting against rival hives');

# The autocomplete went crazy here, I just let it add a bunch of stuff
INSERT INTO threads (board_abbreviation, thread_title, thread_description, thread_author)
VALUES
    ('tb', 'What\'s buzzing?', 'What\'s currently buzzing', 'admin'),
    ('tb', 'There\'s a new hive!', 'There\'s a new hive in the area', 'admin'),
    ('pc', 'Pollen count for the day', 'Pollen count for the day', 'admin'),
    ('ha', 'Honey advice', 'Help honey grow', 'admin'),
    ('e', 'Enemies', 'Plotting against rival hives', 'admin'),
    ('tb', 'What\'s buzzing?', 'What\'s currently buzzing', 'user1'),
    ('tb', 'There\'s a new hive!', 'There\'s a new hive in the area', 'user2'),
    ('pc', 'Pollen count for the day', 'Pollen count for the day', 'user3'),
    ('ha', 'Honey advice', 'Help honey grow', 'user4'),
    ('e', 'Enemies', 'Plotting against rival hives', 'user5');
