create table Loans
(
    id          int AUTO_INCREMENT PRIMARY KEY,
    member_id   int  not null,
    book_id     int  not null,
    loan_date   date not null,
    return_date date not null,

    FOREIGN KEY (member_id) REFERENCES Members (id),
    FOREIGN KEY (book_id) REFERENCES Books (id)
);

insert into Books (title, author, publisher, isbn, publication_year, genre, available, price)
values ('How To Live Better', 'Dylan Devlin', 'Pearsons', 672100101, 2024, 'Lifestyle', 50, 14.99),
       ('The Crazy Dog by Callum Camwell', 'Callum Camwell', 'Penguin', 13681386, 2020, 'Children', 100, 9.99),
       ("Belfast in the 80's", 'Georgia Grimstill', 'Belfast Books', 1892982, 1992, 'History', 80, 24.99);

insert into Loans (member_id, book_id, loan_date, return_date)
values (1, 2, current_date(), '2024-12-21'),
       (1, 1, current_date(), '2024-11-20'),
       (3, 1, current_date(), '2024-11-20'),
       (2,3, current_date(), '2025-01-02');

select group_concat(Books.title separator ', ') as `Book Title`, concat(Members.first_name, ' ', Members.last_name) as `Name`
from Loans
         join Books on book_id = Books.id
         join Members on member_id = Members.id
group by Members.id;

select concat(Members.first_name, ' ', Members.last_name) as `Name`, group_concat(Books.title separator ', ') as `Books`
from Loans
         join Books on book_id = Books.id
         join Members on member_id = Members.id
where return_date is null or return_date > current_date()
group by member_id;

select count(book_id) as `Number of Loans`, Books.publisher as `Publisher`
from Loans
         join Books on book_id = Books.id
group by book_id
order by book_id asc
    limit 1;

select count(book_id) as `Number of Loans`, Books.publisher as `Publisher`
from Loans
         join Books on book_id = Books.id
group by book_id
order by book_id desc
    limit 1;

select Books.title as `Book Title`, case when available > 0 then 'Available' else 'Not Available' end as 'Availability'
from Books;