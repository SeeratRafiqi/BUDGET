INSERT INTO users(first_name, last_name, email, phone, password, total_amount) VALUES
    ('John', 'Doe', 'john@gmail.com', 123456789, '$2a$12$JGriZzgFwZNEeuIzFcocjug9wb0/G0EJ1nco27FZoCvVLmfpfiiWe', 1000);
INSERT INTO users(first_name, last_name, email, phone, password, total_amount) VALUES
    ('Alice', 'Smith', 'alice@gmail.com', 987654321, '$2a$12$JGriZzgFwZNEeuIzFcocjug9wb0/G0EJ1nco27FZoCvVLmfpfiiWe', 750);
INSERT INTO users(first_name, last_name, email, phone, password, total_amount) VALUES
    ('Bob', 'Johnson', 'bob@gmail.com', 555123456, '$2a$12$JGriZzgFwZNEeuIzFcocjug9wb0/G0EJ1nco27FZoCvVLmfpfiiWe', 600);
INSERT INTO users(first_name, last_name, email, phone, password, total_amount) VALUES
    ('Sam', 'Alexis', 'imnotsam@gmail.com', 444123456, '$2a$12$JGriZzgFwZNEeuIzFcocjug9wb0/G0EJ1nco27FZoCvVLmfpfiiWe', 700);


INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('TRANSPORT ', '2024-06-12', '2024-06-30', 40);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('GROCERIES', '2024-06-12', '2024-06-30', 300);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('SHOPPING', '2024-06-01', '2024-06-30', 200);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('BILL', '2024-06-01', '2024-06-30', 100);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('RENT', '2024-06-01', '2024-06-30', 200);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('RENT', '2024-01-01', '2024-01-30', 200);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('UTILITY', '2024-01-02', '2024-01-25', 200);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('RENT', '2024-02-02', '2024-02-28', 200);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('RENT', '2024-03-02', '2024-03-28', 200);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('RENT', '2024-04-02', '2024-04-28', 200);



INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('SHOPPING', '2024-02-02', '2024-02-28', 300);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('VACATION', '2024-02-02', '2024-02-28', 400);
INSERT INTO groups (Category, Start_date, End_date, BAmount) VALUES ('RENT', '2024-01-02', '2024-01-28', 300);


update groups set user_id = 1
where Budget_Id = 1;

update groups set user_id = 1
where Budget_Id=2;

update groups set user_id = 1
where Budget_Id=3;

update groups set user_id = 1
where Budget_Id = 4;

update groups set user_id=2
where Budget_id = 11;

update groups set user_id = 2
where Budget_Id = 13;

update groups set user_id = 2
where Budget_Id = 12;





