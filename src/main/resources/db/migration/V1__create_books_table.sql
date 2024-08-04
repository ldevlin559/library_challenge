create table Books (
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    author varchar(255) not null,
    publisher varchar(255) not null,
    isbn varchar(255) not null,
    publication_year YEAR not null,
    genre VARCHAR(255) NOT NULL,
    available TINYINT(1) not null default 1,
    price decimal(10,2) not null
);

insert into Books (title, author, publisher, isbn, publication_year, genre, available, price)
values ('Dr', 'Dylan Devlin', 'Pearsons', 672100101, 2024, 'Lifestyle', 50, 14.99)