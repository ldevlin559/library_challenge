create table Members
(
    id            int AUTO_INCREMENT PRIMARY KEY,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    address       varchar(255) not null,
    phone         varchar(255) not null,
    email         varchar(255) not null,
    register_date date         not null
);

insert into Members(first_name, last_name, address, phone, email, register_date)
VALUES ('Luke', 'Devlin', '38 Drummmond Pk', 07738424690, 'ldevlin@hotmail.com', current_date()),
       ('John', 'Hutchman', '123 Shantallow', 07732456789, 'johnh@gmail.com', CURRENT_DATE()),
       ('Megan', 'Doherty', '345 Culmore rd', 07756789033, 'megand@outlook.com', current_date());
