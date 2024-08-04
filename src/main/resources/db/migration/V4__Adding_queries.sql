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

select genre as `Genre`, count(*) as `Number of books in genre`
from Books
group by genre;

select (select Books.title
        from Loans
                 right join Books on Books.id = Loans.book_id
        where Loans.book_id is null) as `Books that have not been loaned`,
       (select concat(Members.first_name, ' ', Members.last_name)
        from Loans
                 right join Members on Members.id = Loans.member_id
        where Loans.member_id is null )as `Members that have not loaned a book`;

select avg(Books.price) as `Average price of books that have been published last year and have been loaned`
from Books
         left join Loans on Loans.book_id = Books.id
where book_id is not null and Books.Publication_year < year(current_date()) -1;

update Books
set price = price * 0.8
where year(current_date()) - publication_year > 5;

Update Members
set first_name = concat(upper(substring(first_name, 1, 1)), lower(substring(first_name, 2)));

Delete from Loans
where month(current_date()) - return_date >= 1;

create index idx_books_genre on Books(genre);
create index idx_books_author on Books(author);
create index idx_books_publisher on Books(publisher);

alter table Members
    add constraint unique_email Unique (email);